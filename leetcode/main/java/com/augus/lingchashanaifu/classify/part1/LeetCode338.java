package com.augus.lingchashanaifu.classify.part1;

public class LeetCode338 {

    public int[] countBits(int n) {
        int[] res = new int[n + 1];
        for(int i = 0 ; i < n + 1; i++){
            res[i] = Integer.bitCount(i);
        }
        return res;
    }
}
