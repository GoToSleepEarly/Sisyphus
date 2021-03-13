package 数据结构.并查集;

import java.util.HashMap;
import java.util.Map;

public class No765情侣牵手 {

    public int minSwapsCouples(int[] row) {
        int n = row.length / 2;
        UnionFind unionFind = new UnionFind(n);
        //本质上是考虑坐错的情况，我们发现男女坐反不影响结局
        // 如0213和0321,固定2个的情况下，交换一次即可。
        // 当坐错个数增多时，我们发现同一个连通块，交换n-1个就行啦。
        for (int i = 0; i < row.length; i = i + 2) {
            unionFind.union(row[i] / 2, row[i + 1] / 2);
        }
        Map<Integer, Integer> count = new HashMap<>();
        for (int i = 0; i < n; i++) {
            int p = unionFind.find(i);
            count.put(p, count.getOrDefault(p, 0) + 1);
        }
        int res = 0;
        for (int v : count.values()) {
            res += v - 1;
        }
        return res;
    }
}
