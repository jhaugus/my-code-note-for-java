package com.augus;

public class LeetCode307 {
}


class NumArray {

    int[] arr;
    int[] tree;

    public NumArray(int[] nums) {
        int n = nums.length;
        this.arr = nums;
        tree = new int[n+1];
        for(int i = 1 ; i <= n; i++){
            tree[i] += nums[i-1];
            int next = i + (i & -i);
            if(next <= n){
                tree[next] += tree[i];
            }
        }
    }

    public void update(int index, int val) {
        int delta = val - arr[index];
        arr[index] = val;
        for(int i = index + 1; i < tree.length; i += i & -i){
            tree[i] += delta;
        }
    }

    public int sumRange(int left, int right) {
        return prefixSum(right + 1) - prefixSum(left);
    }

    private int prefixSum(int i){
        int s = 0;
        for(; i > 0; i &= i -1){  // i -= i & -1的另一种写法
            s += tree[i];
        }
        return s;
    }
}
