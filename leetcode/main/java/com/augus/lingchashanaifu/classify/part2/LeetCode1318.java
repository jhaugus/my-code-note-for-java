package com.augus.lingchashanaifu.classify.part2;

public class LeetCode1318 {
    public int minFlips(int a, int b, int c) {
        int res = 0;

        while(c > 0 || a > 0 || b > 0){
            int aa = a & 1;
            int bb = b & 1;
            int cc = c & 1;

            if(cc == 1){
                if(aa == 0 && bb == 0) res++;
            }else{
                if(aa == 1 && bb == 1) res+=2;
                else if(aa == 1) res++;
                else if(bb == 1) res++;
            }

            a >>= 1;
            b >>= 1;
            c >>= 1;

        }
        return res;
    }
}
