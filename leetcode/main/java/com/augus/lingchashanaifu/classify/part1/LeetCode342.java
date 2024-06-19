package com.augus.lingchashanaifu.classify.part1;

public class LeetCode342 {
    public boolean isPowerOfFour(int n) {
        return n > 0 && (n & (n-1)) == 0 && n % 3 == 1;
    }
}
