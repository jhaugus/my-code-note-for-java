import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Solution {

    public static void main(String[] args) {
        int[] nums = {3,7,9,10,3,7,1,8,3,4};
        Solution solution = new Solution();
        int[] ints = solution.resultArray(nums);
        for(int i = 0 ; i < 5; i ++){
            System.out.println(ints[i]);
        }

    }

    public int[] resultArray(int[] nums){
        int[] sorted = nums.clone();
        Arrays.sort(sorted);
        int n = nums.length;

        List<Integer> a = new ArrayList<>(n);
        List<Integer> b = new ArrayList<>();

        a.add(nums[0]);
        b.add(nums[1]);

        Fenwick t1 = new Fenwick(n + 1);
        Fenwick t2 = new Fenwick(n + 1);

        t1.add(Arrays.binarySearch(sorted, nums[0]) + 1);
        t2.add(Arrays.binarySearch(sorted, nums[1]) + 1);


        for(int i = 2; i < nums.length; i++){
            int x = nums[i];
            int v = Arrays.binarySearch(sorted, x) + 1;
            int gc1 = a.size() - t1.pre(v);
            int gc2 = b.size() - t2.pre(v);
            if(gc1 > gc2 || gc1 == gc2 && a.size() <= b.size()){
                a.add(x);
                t1.add(v);
            }else{
                b.add(x);
                t2.add(v);
            }
        }
        a.addAll(b);
        for(int i = 0 ; i < n; i++){
            nums[i] = a.get(i);
        }
        return nums;
    }

}

class Fenwick{
    private final int[] tree;
    public Fenwick(int n){
        tree = new int[n];
    }


    // 把下标为 i 的元素增加 1
    public void add(int i){
        while(i < tree.length){
            tree[i]++;
            i += i & -i;
        }
    }
    // 返回下标在[1, i]的元素之和
    public int pre(int i){
        int res = 0;
        while(i > 0){
            res += tree[i];
            i &= i - 1;
        }
        return res;
    }

}