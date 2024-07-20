package com.augus.lottery.controller1;

import com.augus.controller.*;
import com.augus.controller2.CacheMgr;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

import javax.annotation.Resource;
import java.io.Serializable;
import java.text.ParseException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

@Slf4j
@Controller("/lottery2")
public class LotteryController2 {

    @Resource
    private BlackUserMapper blackUserMapper;
    @Resource
    private BlackIpMapper blackIpMapper;
    @Resource
    private CouponMapper couponMapper;
    @Resource
    private LotteryTimesMapper lotteryTimesMapper;
    @Resource
    private PrizeMapper prizeMapper;
    @Resource
    private ResultMapper resultMapper;



    @PostMapping(value = "/v2/get_lucky", consumes = "application/json; charset=utf-8")
    public ResponseEntity<LotteryResult> lotteryV2(@RequestHeader("X-User-Id") Long tokenUserID, @RequestBody LotteryReq req) {
        LotteryResult lotteryResult = null;
        try {
            Long userID = null;
            if (tokenUserID != null) {
                userID = tokenUserID;
            }else {
                userID = new Long(req.userId);
            }
            String userName = req.userName;
            String ip = req.ip;
            lotteryResult = lottery(userID, userName, ip);
        } catch (Exception e) {
            System.out.println("lottery err " + e.getMessage());
            return ResponseEntity.fail();
        }
        return ResponseEntity.resp(ResponseEnum.OK, lotteryResult);
    }

    @Resource
    RedissonClient redissonClient;
    public LotteryResult lottery(Long userID, String userName, String ip) throws ParseException {
        LotteryResult lotteryResult = new LotteryResult();
        lotteryResult.setUserId(userID);
        String lockKey = String.format(Constants.lotteryLockKeyPrefix+"%d", userID);
        RLock lock = redissonClient.getLock(lockKey);
        /*
         ******** 抽奖逻辑*******
         */
        if (lock.tryLock()) {
            log.info("get lock success!!!!!!!!");
            try {
                getLuckyV2(lotteryResult, userID, userName,ip);
            }finally {
                lock.unlock();
            }
        }
        return lotteryResult;
    }

    public void getLuckyV2(LotteryResult lotteryResult,Long userID, String userName, String ip) throws ParseException {
        /*
         ******** 抽奖逻辑*******
         */
        CheckResult checkResult = new CheckResult();
        // 1. 验证用户今日抽奖次数
        if (!checkUserDayLotteryTimesWithCache(userID)) {
            log.info("lotteryV1|CheckUserDayLotteryTimes failed，user_id：{}", userID);
            lotteryResult.setErrcode(ErrorCode.ERR_USER_LIMIT_INVALID);
            return;
        }
        // 2. 验证当天IP参与的抽奖次数
        if (!checkIpLimit(ip)) {
            log.info("lotteryV1|checkIpLimit failed，ip：{}", ip);
            lotteryResult.setErrcode(ErrorCode.ERR_IP_LIMIT_INVALID);
            return;
        }
        Date now = new Date();

        // 3. 验证IP是否在ip黑名单
        CheckResult ipCheckResult = checkBlackIpWithCache(now,ip);
        checkResult.setBlackIp(ipCheckResult.getBlackIp());
        if (!ipCheckResult.isOk()) {
            log.info("lotteryV1|checkBlackIp failed，ip：{}", ip);
            lotteryResult.setErrcode(ErrorCode.ERR_BLACK_IP);
            return;
        }

        // 4. 验证用户是否在用户黑名单
        CheckResult userCheckResult = checkBlackUserWithCache(now,userID);
        checkResult.setBlackUser(userCheckResult.getBlackUser());
        if (!userCheckResult.isOk()) {
            log.info("lotteryV1|checkBlackUser failed，user_id：{}", userID);
            lotteryResult.setErrcode(ErrorCode.ERR_BLACK_USER);
            return;
        }
        checkResult.setOk(true);

        // 5. 奖品匹配
        int prizeCode = UtilTools.getRandom(Constants.prizeCodeMax);
        log.info("lotteryV1|prizeCode===={}", prizeCode);
        LotteryPrizeInfo prize = getPrizeWithCache(now,prizeCode);
        if (prize == null)  {
            log.info("lotteryV1|getPrize null");
            lotteryResult.setErrcode(ErrorCode.ERR_NOT_WON);
            return;
        }

        if (prize.getPrizeNum() <=0) {
            log.info("lotteryV1|prize_num invalid,prize_id: {}", prize.getId());
            lotteryResult.setErrcode(ErrorCode.ERR_NOT_WON);
            return;
        }

        // 6. 剩余奖品发放
        if (!giveOutPrizeWithCache(prize.getId())) {
            log.info("lotteryV1|prize not enough,prize_id: {}", prize.getId());
            lotteryResult.setErrcode(ErrorCode.ERR_NOT_WON);
            return;
        }

        // 7. 发放优惠券
        if (prize.getPrizeType() == Constants.prizeTypeCouponDiff) {
            String code = prizeCodeDiffWithCache(prize.getId());
            if (code.isEmpty()) {
                log.info("lotteryV1|coupon code is empty: prize_id: {}", prize.getId());
                lotteryResult.setErrcode(ErrorCode.ERR_NOT_WON);
                return;
            }
            // 填充优惠券编码
            prize.setCouponCode(code);
        }
        lotteryResult.setErrcode(ErrorCode.SUCCESS);
        lotteryResult.setLotteryPrize(prize);
        // 8.记录中奖记录
        logLotteryResult(prize,now,userID,ip,userName,prizeCode);
        // 9. 大奖黑名单处理
        if (prize.getPrizeType() == Constants.prizeTypeEntityLarge) {
            LotteryUserInfo lotteryUserInfo = new LotteryUserInfo();
            lotteryUserInfo.setUserId(userID);
            lotteryUserInfo.setUserName(userName);
            lotteryUserInfo.setIp(ip);
            prizeLargeBlackLimit(now,checkResult.getBlackUser(),checkResult.getBlackIp(),lotteryUserInfo);
        }
    }


    @Autowired
    protected CacheMgr cacheMgr;
    // 1. 验证用户今日抽奖次数
    // TODO 增加缓存业务
    public boolean checkUserDayLotteryTimesWithCache(Long userId){
        int userLotteryNum = cacheMgr.incrUserDayLotteryNum(userId);
        log.info("checkUserDayLotteryTimesWithCache|uid={}|userLotteryNum={}", userId, userLotteryNum);
        if (userLotteryNum > Constants.userPrizeMax) {
            return false; // 缓存验证没通过，直接返回
        }
        // 通过数据库验证，还要在数据库中做一次验证
        LotteryTimes userLotteryTimes = getUserCurrentLotteryTimes(userId);
        if (userLotteryTimes != null) {
            // 数据库验证今天的抽奖记录已经达到了抽奖次数限制，不能在抽奖
            if (userLotteryTimes.getNum() > Constants.userPrizeMax){
                // 缓存数据不可靠，不对，需要更新
                if (userLotteryTimes.getNum() > userLotteryNum) {
                    if (!cacheMgr.initUserLuckyNum(userId,userLotteryTimes.getNum())){
                        log.error("checkUserDayLotteryTimesWithCache|initUserLuckyNum error");
                    }
                }
                log.info("checkUserDayLotteryTimes|user_id: {}, lottery times: {}",userId,userLotteryTimes.getNum());
                return false;
            }else{
                int num = userLotteryTimes.getNum() + 1;
                userLotteryTimes.setNum(num);
                // 此时次数抽奖次数增加了，需要更新缓存
                if (userLotteryTimes.getNum() > userLotteryNum) {
                    // 缓存更新失败
                    if (!cacheMgr.initUserLuckyNum(userId,userLotteryTimes.getNum())){
                        log.error("checkUserDayLotteryTimesWithCache|initUserLuckyNum error");
                        return false;
                    }
                }
                // 更新数据库
                lotteryTimesMapper.update(userLotteryTimes);
            }
            return true;
        }
        // 新增一条抽奖记录
        Calendar calendar = Calendar.getInstance();
        int y = calendar.get(Calendar.YEAR);  // 获取当前年
        int m  = calendar.get(Calendar.MONTH) + 1; // 获取当前月
        int d = calendar.get(Calendar.DATE); // 获取当前日
        String strDay = String.format("%d%02d%02d", y, m, d);
        int day = Integer.parseInt(strDay);
        userLotteryTimes = new LotteryTimes();
        userLotteryTimes.setUserId(userId);
        userLotteryTimes.setDay(new Long(day));
        userLotteryTimes.setNum(1);
        userLotteryTimes.setSysCreated(calendar.getTime());
        userLotteryTimes.setSysUpdated(calendar.getTime());
        lotteryTimesMapper.save(userLotteryTimes);
        // 同步到缓存
        if (!cacheMgr.initUserLuckyNum(userId,userLotteryTimes.getNum())){
            log.error("checkUserDayLotteryTimesWithCache|initUserLuckyNum error");
            return false;
        }
        return true;
    }
    public LotteryTimes getUserCurrentLotteryTimes(Long userId) {
        Calendar calendar = Calendar.getInstance();
        int y = calendar.get(Calendar.YEAR);  // 获取当前年
        int m  = calendar.get(Calendar.MONTH) + 1; // 获取当前月
        int d = calendar.get(Calendar.DATE); // 获取当前日
        String strDay = String.format("%d%02d%02d", y, m, d);
        int day = Integer.parseInt(strDay);
        // TODO 根据用户id和天数，获取t_lottery_time中抽过奖的用户，如果用户还没有抽过奖，则返回空
        LotteryTimes lotteryTimes = lotteryTimesMapper.getByUserIDAndDay(userId, new Long((long)day));
        return lotteryTimes;
    }

    // 2. 验证当天IP参与的抽奖次数：
    @Resource
    private RedisUtil redisUtil;
    public boolean checkIpLimit(String ipStr) {
        long ip = UtilTools.ipToLong(ipStr);
        int i = new Long(ip % Constants.ipFrameSize).intValue();
        String key = String.format(Constants.ipLotteryDayNumPrefix+"%d", i);
        double ret = redisUtil.hincr(key,ipStr,1);
        if ((int)ret > Constants.ipLimitMax) {
            log.info("ip Limit exceeded, ret: {}",(int)ret);
            return false;
        }
        return true;
    }

    // 3. 验证IP是否在ip黑名单
    public CheckResult checkBlackIpWithCache(Date now,String ipStr) throws ParseException {
        CheckResult checkResult = new CheckResult();
        BlackIp blackIp = getBLackIpWithCache(ipStr);
        checkResult.setBlackIp(blackIp);
        if (blackIp == null || !blackIp.getIp().isEmpty()) {
            checkResult.setOk(true);
            return checkResult;
        }
        if (now.before(blackIp.getBlackTime())) {
            checkResult.setOk(false);
            return checkResult;
        }
        checkResult.setOk(true);
        return checkResult;
    }
    public BlackIp getBLackIpWithCache(String ip) throws ParseException {
        BlackIp blackIp = cacheMgr.getBlackIpByCache(ip);
        // 从缓存获取到数据
        if (blackIp != null) {
            return blackIp;
        }
        // 缓存没有获取到，从db获取
        blackIp = blackIpMapper.getByIP(ip);
        cacheMgr.setBlackIpByCache(blackIp);
        return blackIp;
    }

    // 4. 验证用户是否在用户黑名单
    public CheckResult checkBlackUserWithCache(Date now,Long userId) throws ParseException {
        CheckResult checkResult = new CheckResult();
        BlackUser blackUser = getBlackUserWithCache(userId);
        checkResult.setBlackUser(blackUser);
        if (blackUser != null && now.before(blackUser.getBlackTime())) {
            checkResult.setOk(false);
        }else{
            checkResult.setOk(true);
        }
        return checkResult;
    }
    public BlackUser getBlackUserWithCache(Long userId) throws ParseException {
        BlackUser blackUser = cacheMgr.getBlackUserByCache(userId);
        if (blackUser != null){
            return blackUser;
        }
        blackUser = blackUserMapper.getByUserId(userId);
        cacheMgr.setBlackUserByCache(blackUser);
        return blackUser;
    }

    // 5. 奖品匹配
    public LotteryPrizeInfo getPrizeWithCache(Date now,int prizeCode) throws ParseException {
        ArrayList<LotteryPrizeInfo> lotteryPrizeInfoList = getAllUsefulPrizesWithCache(now);
        for (LotteryPrizeInfo lotteryPrizeInfo : lotteryPrizeInfoList) {
            if (lotteryPrizeInfo.getPrizeCodeLow() <= prizeCode && lotteryPrizeInfo.getPrizeCodeHigh() >= prizeCode) {
                return lotteryPrizeInfo;
            }
        }
        return null;
    }
    public ArrayList<LotteryPrizeInfo> getAllUsefulPrizesWithCache(Date now) throws ParseException {
        ArrayList<Prize> prizeList = getAllUsefulPrizeListWithCache(now);
        if (prizeList == null || prizeList.isEmpty()) {
            return null;
        }
        ArrayList<LotteryPrizeInfo> lotteryPrizeInfoList = new ArrayList<LotteryPrizeInfo>();
        for (Prize prize : prizeList){
            String[] codes = prize.getPrizeCode().split("-");
            if (codes.length == 2) {
                String codeA = codes[0];
                String codeB = codes[1];
                int low = Integer.parseInt(codeA);
                int high = Integer.parseInt(codeB);
                if (high >= low && low >= 0 && high < Constants.prizeCodeMax) {
                    LotteryPrizeInfo lotteryPrizeInfo = new LotteryPrizeInfo();
                    lotteryPrizeInfo.setId(prize.getId());
                    lotteryPrizeInfo.setTitle(prize.getTitle());
                    lotteryPrizeInfo.setPrizeNum(prize.getPrizeNum());
                    lotteryPrizeInfo.setLeftNum(prize.getLeftNum());
                    lotteryPrizeInfo.setPrizeCodeLow(low);
                    lotteryPrizeInfo.setPrizeCodeHigh(high);
                    lotteryPrizeInfo.setImg(prize.getImg());
                    lotteryPrizeInfo.setDisplayOrder(prize.getDisplayOrder());
                    lotteryPrizeInfo.setPrizeType(prize.getPrizeType());
                    lotteryPrizeInfo.setPrizeProfile(prize.getPrizeProfile());
                    lotteryPrizeInfoList.add(lotteryPrizeInfo);
                }
            }
        }
        return lotteryPrizeInfoList;
    }
    public ArrayList<Prize> getAllUsefulPrizeListWithCache(Date now) throws ParseException {
        ArrayList<Prize> prizeList = getAllPrizeListWithCache();
        ArrayList<Prize> usefulPrizeList = new ArrayList<Prize>();
        for (Prize prize : prizeList) {
            if (prize.getId() > 0 && prize.getSysStatus() == 1 && prize.getPrizeNum() > 0 &&
                    prize.getPrizeBegin().before(now) && prize.getEndTime().after(now)) {
                usefulPrizeList.add(prize);
            }
        }
        return usefulPrizeList;
    }
    public ArrayList<Prize> getAllPrizeListWithCache() throws ParseException {
        ArrayList<Prize> prizeList = cacheMgr.getAllPrizesByCache();
        if (prizeList == null || prizeList.isEmpty()) {
            // 缓存没查到，从db获取
            prizeList = prizeMapper.getAll();
            // 从db获取到数据
            cacheMgr.setAllPrizesByCache(prizeList);
        }
        return prizeList;
    }

    // 6. 剩余奖品发放
    public boolean giveOutPrizeWithCache(Long prizeId) {
        int ret = prizeMapper.decrLeftNum(prizeId,1);
        if (ret <= 0){
            return false;
        }
        cacheMgr.updatePrizeByCache(prizeId);
        return true;
    }

    // 7. 发放优惠券
    // 带缓存的优惠券发奖，从缓存中拿出一个优惠券,要用缓存的话，需要项目启动的时候将优惠券导入到缓存
    public String prizeCodeDiffWithCache(Long prizeId) {
        String code = cacheMgr.getNextUsefulCouponByCache(prizeId);
        if (code.isEmpty()) {
            return "";
        }
        // 缓存中能够取到优惠券，在去更改db
        couponMapper.updateByCode(code);
        return code;
    }

    // 8.记录中奖记录
    public void logLotteryResult(LotteryPrizeInfo prize,Date now,Long userId,String ip,String userName,int prizeCode) {
        Result result = new Result();
        result.setPrizeId(prize.getId());
        result.setPrizeName(prize.getTitle());
        result.setPrizeType(prize.getPrizeType());
        result.setUserId(userId);
        result.setUserName(userName);
        result.setPrizeCode(prizeCode);
        result.setPrizeData(prize.getPrizeProfile());
        result.setSysCreated(now);
        result.setSysIp(ip);
        result.setSysStatus(1);
        resultMapper.save(result);
    }

    // 9. 大奖黑名单处理
    public void prizeLargeBlackLimit(Date now,BlackUser blackUser,BlackIp blackIp,LotteryUserInfo lotteryUserInfo){
        Calendar calendar = Calendar.getInstance(); // 当前时间
        calendar.setTime(now);
        calendar.add(Calendar.DATE,Constants.defaultBlackTime); // 当前时间黑名单限制之后的截止时间
        Date blackTime = calendar.getTime();
        if (blackUser == null || blackUser.getUserId() <= 0){
            // 黑名单不存在，新加入黑名单
            BlackUser blackUserInfo = new BlackUser();
            blackUserInfo.setUserId(lotteryUserInfo.getUserId());
            blackUserInfo.setUserName(lotteryUserInfo.getUserName());
            blackUserInfo.setBlackTime(blackTime);
            blackUserInfo.setSysCreated(now);
            blackUserInfo.setSysUpdated(now);
            blackUserMapper.save(blackUserInfo);
        }else{
            // 黑名单存在，更新黑名单截止时间
            blackUserMapper.updateBlackTimeByUserId(lotteryUserInfo.getUserId(),blackTime);
        }

        if (blackIp == null || blackIp.getIp().isEmpty()) {
            BlackIp blackIpInfo = new BlackIp();
            blackIpInfo.setIp(lotteryUserInfo.getIp());
            blackIpInfo.setBlackTime(blackTime);
            blackIpInfo.setSysCreated(now);
            blackIpInfo.setSysUpdated(now);
            blackIpMapper.save(blackIpInfo);
        }else{
            blackIpMapper.updateBlackTimeByIP(lotteryUserInfo.getIp(),blackTime);
        }
    }



}
