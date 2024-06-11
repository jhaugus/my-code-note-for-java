package com.augus.y2024m6d9_week_game;

import java.util.Arrays;

public class FindKN100305 {
    private static final int mod = 1000000007;
    public int valueAfterKSeconds(int n, int k) {
        long[] arr = new long[n];
        Arrays.setAll(arr, i -> 1);

        while(k-- > 0){

            for(int i = 1; i < n; i++){
                arr[i] += arr[i-1];
                arr[i] %= mod;
            }

        }

        return (int)arr[n-1];
    }
}
