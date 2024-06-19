package com.augus.lingchashanaifu.classify.part2;

public class LeetCode2980 {
    public boolean hasTrailingZeros(int[] nums) {
        int even = nums.length;
        for (int x : nums) {
            even -= x % 2;
        }
        return even >= 2;
    }

}
