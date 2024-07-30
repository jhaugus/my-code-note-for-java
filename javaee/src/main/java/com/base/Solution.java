package com.base;

import java.util.ArrayList;

public class Solution {

    public int[] maxNumber(int[] nums1, int[] nums2, int k) {
        int[] res = new int[k];
        for (int i = 0; i < k; i++) {

            int a = i;
            int b = k - i;
            if (a > nums1.length || b > nums2.length) {
                continue;
            }
            int[] arr1 = removeKdigits(nums1, a);
            int[] arr2 = removeKdigits(nums2, b);
            int[] temp = merge(arr1, arr2);

            int num1 = getNumsCount(temp);
            int num2 = getNumsCount(res);
            if(num1 > num2){
                res = temp;
            }
        }
        return res;

    }

    public static int getNumsCount(int[] nums){
        int res = 0;
        for(int i = 0 ; i < nums.length; i++){
            res *= 10;
            res += nums[i];
        }
        return res;
    }
    public static int[] merge(int[] nums1, int[] nums2) {

        int index1 = 0;
        int index2 = 0;
        int[] res = new int[nums1.length + nums2.length];
        int k = 0;
        while(index1 < nums1.length && index2 < nums2.length) {
            if(nums1[index1] > nums2[index2]) {
                res[k++] = nums1[index1];
                index1++;
            }else{
                res[k++] = nums2[index2];
                index2++;
            }
        }
        while(index1 < nums1.length){
            res[k++] = nums1[index1++];
        }
        while(index2 <nums2.length){
            res[k++] = nums2[index2++];
        }
        return res;
    }

    public static int[] removeKdigits(int[] nums, int k) {
        ArrayList<Integer> list = new ArrayList<>();
        int count = k;
        for (int i = 0; i < nums.length; i++) {
            while (count > 0 && list.size() > 0 && list.get(list.size() - 1) < nums[i]) {
                list.remove(list.size() - 1);
                count--;
            }
            list.add(nums[i]);
        }
        int[] res = new int[list.size()];
        for(int i = 0 ; i < list.size(); i++) {
            res[i] = list.get(i);
        }
        return res;
    }
}

//    public String removeKdigits(String num, int k) {
//        StringBuilder stack = new StringBuilder();
//        int remain = num.length() - k;
//        for (char digit : num.toCharArray()) {
//            while (k > 0 && stack.length() > 0 && stack.charAt(stack.length() - 1) > digit) {
//                stack.deleteCharAt(stack.length() - 1);
//                k--;
//            }
//            stack.append(digit);
//        }
//
//        // Construct the final result by slicing the stack to the 'remain' length
//        String result = stack.substring(0, remain);
//
//        // Remove leading zeros
//        result = result.replaceFirst("^0+(?!$)", "");
//
//        return result.isEmpty() ? "0" : result;
//    }



