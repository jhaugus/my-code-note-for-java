package com.augus;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//https://leetcode.cn/problems/count-pairs-of-connectable-servers-in-a-weighted-tree-network/description/?envType=daily-question&envId=2024-06-04
public class leetcode3067 {


    public int[] countPairsOfConnectableServers(int[][] edges, int signalSpeed) {
        // 1.初始化
        int n = edges.length + 1;
        List<int[]>[] g = new ArrayList[n];
        Arrays.setAll(g, i -> new ArrayList<>());
        for(int[] e: edges){
            int x = e[0];
            int y = e[1];
            int wt = e[2];
            g[x].add(new int[]{y, wt});
            g[y].add(new int[]{x, wt});
        }

        int[] ans = new int[n];
        for(int i = 0; i < n; i++){
            // 计算每个节点的满足条件的对数
            if(g[i].size() == 1){
                continue;
            }
            int sum = 0;
            // 计算某个节点，各个边的满足条件的 路径数
            for(int[] e : g[i]){
                int cnt = dfs(e[0], i, e[1], g, signalSpeed);
                ans[i] += cnt * sum;
                sum += cnt;
            }
        }
        return ans;
    }

    /**
     * 计算 fa 和 x 节点所连边的路径上有多少个符合的路径
     * @param x ：fa - x是一条边，
     * @param fa ：当前的出发节点
     * @param sum: 从i - x的路径上的权值
     * @param g ：拉链法表示树
     * @param signalSpeed ：sum必须要能够整除signalSpeed才算满足条件
     * @return
     */
    private int dfs(int x, int fa, int sum, List<int[]>[] g, int signalSpeed) {
        int cnt = sum % signalSpeed == 0 ? 1 : 0;
        for(int[] e: g[x]){
            int y = e[0];
            // 防止无向边重复走
            if(y != fa){
                cnt += dfs(y, x, sum + e[1], g, signalSpeed);
            }
        }
        return cnt;

    }
//
//    作者：灵茶山艾府
//    链接：https://leetcode.cn/problems/count-pairs-of-connectable-servers-in-a-weighted-tree-network/solutions/2664330/mei-ju-gen-dfs-cheng-fa-yuan-li-pythonja-ivw5/
//    来源：力扣（LeetCode）
//    著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。



}
