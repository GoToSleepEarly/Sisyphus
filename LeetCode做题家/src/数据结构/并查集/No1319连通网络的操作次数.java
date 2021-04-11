package 数据结构.并查集;

import java.util.HashSet;
import java.util.Set;

public class No1319连通网络的操作次数 {

    public int makeConnected(int n, int[][] connections) {
        // 1计算冗余  2计算连通块
        UnionFind unionFind = new UnionFind(n);
        int free = 0;
        for (int[] connection : connections) {
            int x = connection[0];
            int y = connection[1];
            if (!unionFind.union(x, y)) {
                free++;
            }
        }
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < n; i++) {
            set.add(unionFind.find(i));
        }
        int need = set.size() - 1;

        return free >= need ? need : -1;
    }
}
