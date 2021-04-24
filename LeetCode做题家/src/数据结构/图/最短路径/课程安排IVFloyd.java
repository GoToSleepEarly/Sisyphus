package 数据结构.图.最短路径;

import java.util.LinkedList;
import java.util.List;

public class 课程安排IVFloyd {
    public List<Boolean> checkIfPrerequisite(int n, int[][] prerequisites, int[][] queries) {
        // Floyd算法
        boolean[][] graph = new boolean[n][n];
        // false为不通，true为通
        for (int[] pre : prerequisites) {
            graph[pre[0]][pre[1]] = true;
        }
        // 三次循环
        // 中转点
        for (int i = 0; i < n; i++) {
            // 后继
            for (int j = 0; j < n; j++) {
                // 前置
                for (int k = 0; k < n; k++) {
                    if (graph[i][j] && graph[k][i]) {
                        graph[k][j] = true;
                    }
                }
            }
        }
        List<Boolean> list = new LinkedList<>();
        for (int[] query : queries) {
            list.add(graph[query[0]][query[1]]);
        }
        return list;
    }
}
