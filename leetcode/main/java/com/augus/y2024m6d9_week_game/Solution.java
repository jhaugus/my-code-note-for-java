package com.augus.y2024m6d9_week_game;

import java.util.Arrays;

public class Solution {
    public int maxTotalReward(int[] rewardValues) {
        Arrays.sort(rewardValues);


        // 贪心
        int n = rewardValues.length;
        boolean[] invited = new boolean[n];
        int max = rewardValues[n-1];

        for(int k = n - 2; k >= 0; k--){
            int result = rewardValues[n-1];
            int rep = result - 1;
            int right = k;
            int count = 0;
            int last = result;
            while(right >= 0){
                // 找下一个
                if(rewardValues[right] <= rep && rewardValues[right] < last && !invited[right]){
                    count++;
                    result += rewardValues[right];
                    rep -= rewardValues[right];
                    last = rewardValues[right];
                    if(count == 1){
                        invited[right] = true;
                    }
                }
                if(rep == 0) return result;
                right--;
            }
            max = Math.max(max, result);

        }
        return max;
    }


}
