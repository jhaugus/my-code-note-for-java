package com.augus.lingchashanaifu.classify.part1;

public class LeetCode461 {
    public int hammingDistance(int x, int y) {
        return Integer.bitCount(x ^ y);
    }
}
