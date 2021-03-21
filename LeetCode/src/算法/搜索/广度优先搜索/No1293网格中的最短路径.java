package 算法.搜索.广度优先搜索;

import java.util.LinkedList;
import java.util.Queue;

public class No1293网格中的最短路径 {

    public int shortestPath(int[][] grid, int k) {
        int[][] dirs = new int[][]{{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
        int m = grid.length;
        int n = grid[0].length;
        if (m == 1 && n == 1) {
            return 0;
        }
        boolean[][][] visited = new boolean[m][n][k + 1];
        int count = 0;
        Queue<int[]> queue = new LinkedList<>();
        // 已经访问
        visited[0][0][k] = true;
        queue.add(new int[]{0, 0, k});
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int[] curNode = queue.poll();
                int x = curNode[0];
                int y = curNode[1];
                int curK = curNode[2];
                if (x == m - 1 && y == n - 1) {
                    return count;
                }
                for (int[] dir : dirs) {
                    int nextX = x + dir[0];
                    int nextY = y + dir[1];
                    if (!isValidIndex(nextX, nextY, grid)) {
                        continue;
                    }

                    if (grid[nextX][nextY] == 0 && !visited[nextX][nextY][curK]) {
                        visited[nextX][nextY][curK] = true;
                        queue.add(new int[]{nextX, nextY, curK});
                    }
                    if (grid[nextX][nextY] == 1 && curK > 0
                            && !visited[nextX][nextY][curK - 1]) {
                        visited[nextX][nextY][curK - 1] = true;
                        queue.add(new int[]{nextX, nextY, curK - 1});
                    }
                }
            }
            count++;
        }
        return -1;
    }

    private boolean isValidIndex(int x, int y, int[][] grid) {
        return x >= 0 && x < grid.length
                && y >= 0 && y < grid[0].length;
    }

}
