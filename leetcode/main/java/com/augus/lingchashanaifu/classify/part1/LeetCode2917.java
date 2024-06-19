package com.augus.lingchashanaifu.classify.part1;

public class LeetCode2917 {
    public int findKOr(int[] nums, int k) {
        int count = 0;
        int max = 0;
        for(int i = 0 ; i < nums.length; i++){
            max = Math.max(max, nums[i]);
        }
        int n = 0;
        while(max > 0){
            max >>= 1;
            n++;
        }


        int res = 0;
        for(int i = 0; i < n; i++){
            count = 0;
            for(int j = 0 ; j < nums.length; j++){
                if((nums[j] & 1) == 1) count++;
                if(nums[j] > 0) nums[j] >>= 1;
            }
            if(count >= k){
                res |= (1 << i);
            }

        }
        return res;
    }
}
