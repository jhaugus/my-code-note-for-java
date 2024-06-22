package com.augus.lingchashanaifu.classify.part2;

public class LeetCode2401 {

    public int longestNiceSubarray(int[] nums) {
        int ans = 0;
        for (int left = 0, right = 0, or = 0; right < nums.length; right++) {
            while ((or & nums[right]) > 0) // 有交集
                or ^= nums[left++]; // 从 or 中去掉集合 nums[left]
            or |= nums[right]; // 把集合 nums[right] 并入 or 中
            ans = Math.max(ans, right - left + 1);
        }
        return ans;
    }


}
