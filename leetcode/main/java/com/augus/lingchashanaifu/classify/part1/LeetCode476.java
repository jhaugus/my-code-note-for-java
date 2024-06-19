package com.augus.lingchashanaifu.classify.part1;

public class LeetCode476 {
    public int findComplement(int num) {
        int res = 0;
        for(int i = 0 ; num > 0; i++){
            int temp = num % 2;
            temp = temp == 1? 0:1;
            res += temp * Math.pow(2, i);
            num >>= 1;
        }
        return res;
    }
}
