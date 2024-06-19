package com.augus.lingchashanaifu.classify.part1;

public class LeetCode2220 {
    class Solution {
        public int minBitFlips(int start, int goal) {
            return Integer.bitCount(start ^ goal);
        }
    }
}
