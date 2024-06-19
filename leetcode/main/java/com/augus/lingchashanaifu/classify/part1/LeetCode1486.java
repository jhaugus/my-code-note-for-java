package com.augus.lingchashanaifu.classify.part1;

public class LeetCode1486 {

    public static int xorOperation(int n, int start){
        int a = start / 2;
        int b = n & start & 1;  // 当n和start都为奇数时，b = 1
        return (xor(a + n - 1) ^ xor(a - 1) * 2 + 1);
    }

    // 计算从 0 到 n的异或和
    // 0 ^ 1 = 1 , ... ,  i ^ (i+1) = 1;
    // 令 n = 4k + (1,2,3,4)
    // n = 4k + 1时， (n+1)/2 = (4k+2)/2 = 2k+1，等于有奇数个 i ^ (i+1) = 1，所以等于 1
    // n = 4k + 2时,  单独拿出一个n，这样就简化为4k+1的情况，最后等于 n ^ 1，即 (4k+2)^1 = 4k+3 = n+1
    // n = 4k + 3时， (n+1)/2 = (4k+4)/2 = 2k+2，等于有偶数个 i ^ (i+1) = 1, 最后等于 1 ^ 1 = 0
    // n = 4k + 4时， 单独拿出一个n，这样就简化为4k+3的情况，最后等于 n ^ 0，即 n
//    public static int xor(int n){
//        return switch(n % 4){
//            case 0 -> n;
//            case 1 -> 1;
//            case 2 -> n + 1;
//            default -> 0;
//        };
//    }
    public static int xor(int n) {
        int result;
        switch (n % 4) {
            case 0:
                result = n;
                break;
            case 1:
                result = 1;
                break;
            case 2:
                result = n + 1;
                break;
            default:
                result = 0;
                break;
        }
        return result;
    }

}
