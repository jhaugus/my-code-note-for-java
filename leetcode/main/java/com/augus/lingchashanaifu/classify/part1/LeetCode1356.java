package com.augus.lingchashanaifu.classify.part1;

import java.util.ArrayList;
import java.util.Comparator;

public class LeetCode1356 {
    public int[] sortByBits(int[] arr) {
        // 初始化
        // 0 <= arr[i] <= 10^4
        int[] bits = new int[10001];
        ArrayList<Integer> counts = new ArrayList<>();
        for(int i = 0 ; i < arr.length; i++){
            counts.add(arr[i]);
            bits[arr[i]] = Integer.bitCount(arr[i]);

        }

        counts.sort(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                if(bits[o1] != bits[o2]){
                    return bits[o1] - bits[o2];
                }else{
                    return o1 - o2;
                }
            }
        });
        int[] res = new int[arr.length];
        for(int i = 0; i < res.length; i++){
            res[i] = counts.get(i);
        }
        return res;


    }
}
