package 数据结构.图.拓扑排序;

import java.util.*;

public class 课程安排IV {

    public List<Boolean> checkIfPrerequisite(int n, int[][] prerequisites, int[][] queries) {
        // 拓扑排序
        Map<Integer, Set<Integer>> nextMap = new HashMap<>();
        int[] inDegree = new int[n];
        // 最终结果表
        boolean[][] res = new boolean[n][n];
        for (int i = 0; i < n; i++) {
            nextMap.put(i, new HashSet<>());
        }
        for (int[] pre : prerequisites) {
            nextMap.get(pre[0]).add(pre[1]);
            inDegree[pre[1]]++;
            // 可达
            res[pre[0]][pre[1]] = true;
        }
        Deque<Integer> queue = new LinkedList<>();
        // 初始化
        for (int i = 0; i < inDegree.length; i++) {
            if (inDegree[i] == 0) {
                queue.add(i);
            }
        }

        while (!queue.isEmpty()) {
            int cur = queue.pollFirst();
            Set<Integer> nextSet = nextMap.get(cur);

            for (int next : nextSet) {
                inDegree[next]--;
                if (inDegree[next] == 0) {
                    queue.addLast(next);
                }
                // 刷新结果表
                for (int i = 0; i < n; i++) {
                    if (res[i][cur]) {
                        res[i][next] = true;
                    }
                }
            }
        }
        List<Boolean> list = new LinkedList<>();
        for (int[] query : queries) {
            list.add(res[query[0]][query[1]]);
        }
        return list;
    }
}
