package 算法.搜索.广度优先搜索;

import java.util.LinkedList;
import java.util.Queue;

public class No847访问所有节点的最短路径 {

    public static void main(String[] args) {
        No847访问所有节点的最短路径 s = new No847访问所有节点的最短路径();
        s.shortestPathLength(new int[][]{{1, 2}, {0}, {0}});
    }

    public int shortestPathLength(int[][] graph) {
        Queue<int[]> queue = new LinkedList<>();
        int n = graph.length;
        boolean[][] visited = new boolean[n][1 << n];
        for (int i = 0; i < n; i++) {
            queue.add(new int[]{i, 1 << i});
            visited[i][1 << i] = true;
        }
        int count = 0;
        int finish = (1 << n) - 1;
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int[] curNode = queue.poll();
                int curIndex = curNode[0];
                int curPath = curNode[1];
                if (curPath == finish) {
                    return count;
                }
                for (int next : graph[curIndex]) {
                    int nextPath = curPath | (1 << next);

                    if (visited[next][nextPath]) {
                        continue;
                    }
                    visited[next][nextPath] = true;
                    queue.add(new int[]{next, nextPath});
                }
            }
            count++;
        }
        return 0;
    }
}
