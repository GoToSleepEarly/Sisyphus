package 数据结构.并查集;

import java.util.HashSet;
import java.util.Set;

public class No947移除最多的同行或同列石头 {

    public int removeStones(int[][] stones) {
        int n = stones.length;
        UnionFind unionFind = new UnionFind(n);
        // 两两判断，构建并查集
        for (int i = 0; i < n; i++) {
            int[] stone1 = stones[i];
            for (int j = i + 1; j < n; j++) {
                int[] stone2 = stones[j];
                // 同行或同列
                if (stone1[0] == stone2[0] || stone1[1] == stone2[1]) {
                    unionFind.union(i, j);
                }
            }
        }
        // 因为N个点的连通块，总能通过移除N-1个点后剩下最后孤立的点
        // 计算连通块的数量，n-k即为答案
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < n; i++) {
            set.add(unionFind.find(i));
        }
        return n - set.size();
    }

}
