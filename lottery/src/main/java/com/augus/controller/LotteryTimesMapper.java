package com.augus.controller;


import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface LotteryTimesMapper {
    LotteryTimes getByUserIDAndDay(@Param("userID") Long userID, @Param("day") Long day);

    void save(@Param("lotteryTimes") LotteryTimes lotteryTimes);

    void update(@Param("lotteryTimes") LotteryTimes lotteryTimes);
}