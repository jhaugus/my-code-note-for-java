package com.augus;
public class leetcode307{
    public static void main(String[] args) {
        int[] arr = {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16};
        TreeArr treeArr = new TreeArr(arr);
    }
}

class TreeArr {
    private int[] nums;
    private int[] tree;

    public TreeArr(int[] nums) {
        int n = nums.length;
        this.nums = nums;
        tree = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            tree[i] += nums[i - 1];
            int nxt = i + (i & -i); // 下一个关键区间的右端点
            if (nxt <= n) {
                tree[nxt] += tree[i];
            }
        }
    }

    public void update(int index, int val) {
        int delta = val - nums[index];
        nums[index] = val;
        for (int i = index + 1; i < tree.length; i += i & -i) {
            tree[i] += delta;
        }
    }

    private int prefixSum(int i) {
        int s = 0;
        for (; i > 0; i &= i - 1) { // i -= i & -i 的另一种写法
            s += tree[i];
        }
        return s;
    }

    public int sumRange(int left, int right) {
        return prefixSum(right + 1) - prefixSum(left);
    }
}

