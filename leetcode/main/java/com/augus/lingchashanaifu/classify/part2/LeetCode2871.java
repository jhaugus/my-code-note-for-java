package com.augus.lingchashanaifu.classify.part2;

public class LeetCode2871 {

    public int maxSubarrays(int[] nums) {
        int cur = Integer.MAX_VALUE;
        int count = 0;
        for(int i = 0 ; i < nums.length; i++){
            cur &= nums[i];
            if(cur == 0){
                cur = Integer.MAX_VALUE;
                count++;
            }
        }
        if(count == 0) return 1;
        return count;
    }


}
