package com.augus.y2024m6d9_week_game;

public class FindK100325 {

    public int numberOfChild(int n, int k) {
        int flag = 0;
        // 偶数正、奇数反
        while(n - 1 < k){
            k -= (n-1);
            flag++;
        }

        if(flag % 2 == 0){
            return k;
        }else{
            return n - k - 1;
        }

    }
}
