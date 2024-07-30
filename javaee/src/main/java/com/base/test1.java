package com.base;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;

public class test1 {

    public static void main(String[] args) {
        int[] nums1 = {3,4,6,5};
        int[] nums2 = {9,1,2,5,8,3};
        int k = 5;

        int[] res = new int[k];
        ArrayList<HashNode> hashNodes1 = new ArrayList<>();
        ArrayList<HashNode> hashNodes2 = new ArrayList<>();
        for(int i = 0 ; i < nums1.length; i++){
            hashNodes1.add(new HashNode(i, nums1[i]));
        }
        for(int i = 0 ; i < nums2.length; i++){
            hashNodes2.add(new HashNode(i, nums2[i]));
        }

        hashNodes1.sort(Comparator.comparing(HashNode::getVal).reversed());
        hashNodes2.sort(Comparator.comparing(HashNode::getVal).reversed());
        int index = 0;
        int left = 0 , right = 0;
        int count = k;
        while(true){
            while(count-- > 0){
                if(left >= nums1.length && right >= nums2.length){
                    if(hashNodes1.contains(res[0])) hashNodes1.remove(res[0]);
                    if(hashNodes2.contains(res[0])) hashNodes2.remove(res[0]);
                    res = new int[k];
                    index = 0;
                    count = k;
                    left = 0;
                    right = 0;
                    break;
                }
                int temp = 0;
                if(left < nums1.length && right < nums2.length && hashNodes1.get(left).val > hashNodes2.get(right).val){
                    temp = hashNodes1.get(left).val;
                    left++;
                }else if(left < nums1.length && right < nums2.length){
                    temp = hashNodes2.get(right).val;
                    right++;
                }
                res[index++] = temp;
            }
            if(index == k){
                break;
            }
        }



    }


}

class HashNode{
    int val;
    int index;

    public HashNode(int index, int val) {
        this.index = index;
        this.val = val;
    }


    public int getVal() {
        return val;
    }

    public int getIndex() {
        return index;
    }

    @Override
    public String toString() {
        return "HashNode{" +
                "val=" + val +
                ", index=" + index +
                '}';
    }
}


