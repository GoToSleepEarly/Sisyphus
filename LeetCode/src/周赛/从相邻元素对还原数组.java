package 周赛;

import java.util.*;

public class 从相邻元素对还原数组 {

    public static void main(String[] args) {
        从相邻元素对还原数组 s = new 从相邻元素对还原数组();
        s.restoreArray(new int[][]{{2, 1}, {3, 4}, {3, 2}});
    }

    public int[] restoreArray(int[][] adjacentPairs) {
        // 拓扑排序
        int n = adjacentPairs.length + 1;
        Map<Integer, List<Integer>> next = new HashMap<>();
        for (int[] adj : adjacentPairs) {
            int x = adj[0];
            int y = adj[1];
            next.computeIfAbsent(x, (k) -> new LinkedList<>()).add(y);
            next.computeIfAbsent(y, (k) -> new LinkedList<>()).add(x);
        }
        // 随意找一个入度位1的
        int start = 0;
        for (Map.Entry<Integer, List<Integer>> entry : next.entrySet()) {
            if (entry.getValue().size() == 1) {
                start = entry.getKey();
                break;
            }
        }

        int[] res = new int[n];
        int index = 1;
        res[0] = start;
        Set<Integer> visited = new HashSet<>();
        while (index != n) {
            visited.add(start);
            List<Integer> nextIndexs = next.get(start);
            int nextIndex = 0;
            for (int nextI : nextIndexs) {
                if (visited.contains(nextI)) {
                    continue;
                }
                nextIndex = nextI;
            }
            res[index++] = nextIndex;
            start = nextIndex;
        }
        return res;
    }
}
