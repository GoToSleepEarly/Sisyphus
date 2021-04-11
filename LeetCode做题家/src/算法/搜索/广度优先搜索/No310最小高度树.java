package 算法.搜索.广度优先搜索;

import java.util.*;

public class No310最小高度树 {
    
    public List<Integer> findMinHeightTrees(int n, int[][] edges) {
        // 类似于拓扑排序
        // 越边缘的节点越不可能是根，所以我们依次砍掉
        // 当剩2个节点的时候，哪个都行；1个节点的时候，就是根
        int[] inDegrees = new int[n];
        Map<Integer, List<Integer>> nextMap = new HashMap<>();
        for (int[] edge : edges) {
            inDegrees[edge[0]]++;
            inDegrees[edge[1]]++;
            nextMap.computeIfAbsent(edge[0], k -> new LinkedList<>()).add(edge[1]);
            nextMap.computeIfAbsent(edge[1], k -> new LinkedList<>()).add(edge[0]);
        }
        Queue<Integer> queue = new LinkedList<>();
        // 入度为 1 的结点入队
        for (int i = 0; i < n; i++) {
            if (inDegrees[i] == 1) {
                queue.add(i);
            }
        }

        Set<Integer> remain = new HashSet<>();
        for (int i = 0; i < n; i++) {
            remain.add(i);
        }
        while (remain.size() > 2) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int remove = queue.poll();
                remain.remove(remove);
                List<Integer> nexts = nextMap.get(remove);
                for (int next : nexts) {
                    inDegrees[next]--;
                    if (inDegrees[next] == 1) {
                        queue.add(next);
                    }
                }
            }
        }
        return new LinkedList<>(remain);
    }
}
