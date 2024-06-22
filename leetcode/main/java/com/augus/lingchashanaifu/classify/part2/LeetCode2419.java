package com.augus.lingchashanaifu.classify.part2;

import java.util.Arrays;

public class LeetCode2419 {

    public int longestSubarray(int[] nums) {
        //找到最大值

        int max = 0;
        for(int i = 1 ; i < nums.length; i++){
            if(nums[i] > nums[max]) max = i;
        }
        // 更慢了
//        int max = Arrays.stream(nums).max().getAsInt();

        int cur = 0;
        int res = 0;
        for(int i = 0 ; i < nums.length; i++){
            if(nums[i] == nums[max]){
                cur++;
                res = Math.max(res, cur);
            }
            else cur = 0;
        }
        return res;

    }

}
