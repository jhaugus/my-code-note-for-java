package com.augus.lingchashanaifu.classify.part1;

public class LeetCode231 {
    public boolean isPowerOfTwo(int n) {
        return n > 0 && (n & (n-1)) == 0;
    }
}
