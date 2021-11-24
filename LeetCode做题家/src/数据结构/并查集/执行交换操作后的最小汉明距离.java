package 数据结构.并查集;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class 执行交换操作后的最小汉明距离 {

    public int minimumHammingDistance(int[] source, int[] target, int[][] allowedSwaps) {
        int n = source.length;
        // 存放下标
        EasyUnionFind uf = new EasyUnionFind(n);
        for (int[] allowedSwap : allowedSwaps) {
            uf.union(allowedSwap[0], allowedSwap[1]);
        }
        // 方便最终查找
        uf.flat();
        // 根据uf分组
        Map<Integer, List<Integer>> blockMap = new HashMap<>();
        int[] parent = uf.getParent();
        for (int i = 0; i < n; i++) {
            blockMap.computeIfAbsent(parent[i], k -> new LinkedList<>()).add(i);
        }
        // 统计结果
        int res = 0;
        for (List<Integer> block : blockMap.values()) {
            Map<Integer, Integer> sourceCnt = block.stream().collect(Collectors.toMap(i -> source[i], v -> 1, (v1, v2) -> v1 + 1));
            Map<Integer, Integer> targetCnt = block.stream().collect(Collectors.toMap(i -> target[i], v -> 1, (v1, v2) -> v1 + 1));
            int dif = 0;
            for (Map.Entry<Integer, Integer> entry : targetCnt.entrySet()) {
                int num = entry.getKey();
                int cnt = entry.getValue();
                dif += Math.max(0, cnt - sourceCnt.getOrDefault(num, 0));
            }
            res += dif;
        }
        return res;
    }
}
