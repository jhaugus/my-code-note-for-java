package com.augus.lingchashanaifu.classify.part1;

public class LeetCode2595 {


    // 0x5555 = 0101 0101 0101 0101
    public int[] evenOddBit(int n) {
        int mask = 0x5555;
        return new int[]{Integer.bitCount(n & mask), Integer.bitCount(n & (mask >> 1))};
    }


}
