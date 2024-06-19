package com.augus.lingchashanaifu.classify.part1;

public class LeetCode868 {
    public int binaryGap(int n) {
        if(n == 0) return 0;
        if(Integer.bitCount(n) == 1) return 0;

        // 先找到第一个1
        while(true){
            if((n & 1) == 1) break;
            n >>= 1;
        }
        int max = 0;
        int cur = 0;
        while(n > 0){
            if((n & 1) == 0){
                cur++;
            }
            else{
                max = Math.max(max, cur);
                cur = 1;
            }
            n >>= 1;
        }
        return max;
    }
}
