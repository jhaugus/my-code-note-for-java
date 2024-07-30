package com.base;

import java.util.ArrayList;
import java.util.List;

public class base2024729170022 {
    public static void main(String[] args) {
        int n = 3;
        int m = 2;
        System.out.println(countSequences(n, m));

        List<List<Integer>> res = new ArrayList<List<Integer>>();
        res.remove(0);
    }

    public static int countSequences(int n, int m) {
        // dp[i][k] 表示长度为 i，异或值为 k 的非递减序列的数量
        int[][] dp = new int[n + 1][m + 1];
        dp[0][0] = 1;  // 初始状态，空序列异或值为 0

        for (int i = 1; i <= n; i++) {
            // prefixSum[k] 表示前 i-1 个位置中异或值为 k 的序列数量的前缀和
            int[] prefixSum = new int[m + 1];
            prefixSum[0] = dp[i - 1][0];

            for (int k = 1; k <= m; k++) {
                prefixSum[k] = prefixSum[k - 1] + dp[i - 1][k];
            }

            for (int j = 0; j <= m; j++) {
                dp[i][j] = 0;
                for (int k = 0; k <= j; k++) {
                    if((j ^ k) <= j) dp[i][j] += dp[i - 1][j ^ k];
                }
            }
        }

        // 计算结果
        int result = 0;
        for (int j = 0; j <= m; j++) {
            result += dp[n][j];
        }

        return result;
    }
}
