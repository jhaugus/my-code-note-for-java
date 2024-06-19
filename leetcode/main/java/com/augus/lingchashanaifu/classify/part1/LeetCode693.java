package com.augus.lingchashanaifu.classify.part1;

public class LeetCode693 {
    public boolean hasAlternatingBits(int n) {
        int a = n ^ (n >> 1);
        return (a & (a + 1)) == 0;
    }

}
