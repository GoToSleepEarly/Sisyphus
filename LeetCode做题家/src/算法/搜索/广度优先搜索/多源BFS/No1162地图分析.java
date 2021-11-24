package 算法.搜索.广度优先搜索.多源BFS;

import java.util.Deque;
import java.util.LinkedList;

public class No1162地图分析 {

    public int maxDistance(int[][] grid) {
        // 最短路径，可以考虑bfs
        Deque<int[]> deque = new LinkedList<>();
        int n = grid.length;
        boolean[][] visited = new boolean[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                // 逆向考虑1到0
                if (grid[i][j] == 1) {
                    visited[i][j] = true;
                    deque.add(new int[]{i, j});
                }
            }
        }
        if (deque.isEmpty() || deque.size() == n * n) {
            return -1;
        }
        int cnt = 0;
        int[][] dirs = new int[][]{{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
        while (!deque.isEmpty()) {
            int size = deque.size();
            // 下一轮迭代
            for (int i = 0; i < size; i++) {
                int[] cur = deque.pollFirst();
                for (int[] dir : dirs) {
                    int nextI = cur[0] + dir[0];
                    int nextJ = cur[1] + dir[1];
                    if (nextI >= 0 && nextI < n
                            && nextJ >= 0 && nextJ < n
                            && grid[nextI][nextJ] == 0
                            && !visited[nextI][nextJ]) {
                        visited[nextI][nextJ] = true;
                        deque.addLast(new int[]{nextI, nextJ});
                    }
                }
            }
            cnt++;
        }
        return cnt - 1;
    }
}
