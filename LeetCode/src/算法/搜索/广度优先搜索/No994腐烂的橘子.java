package 算法.搜索.广度优先搜索;

import java.util.Deque;
import java.util.LinkedList;

public class No994腐烂的橘子 {

    public int orangesRotting(int[][] grid) {
        int[][] dirs = new int[][]{{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
        int count = 0;
        Deque<int[]> queue = new LinkedList<>();
        int m = grid.length;
        int n = grid[0].length;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) {
                    count++;
                } else if (grid[i][j] == 2) {
                    queue.add(new int[]{i, j});
                }
            }
        }
        if (count == 0) {
            return 0;
        }
        int day = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int[] curIndex = queue.poll();
                //System.out.println(curIndex[0]+" "+curIndex[1]+" "+count);
                for (int[] dir : dirs) {
                    int nextX = curIndex[0] + dir[0];
                    int nextY = curIndex[1] + dir[1];
                    if (isValidIndex(grid, nextX, nextY)) {
                        if (grid[nextX][nextY] == 1) {
                            grid[nextX][nextY] = 2;
                            count--;
                            queue.add(new int[]{nextX, nextY});
                        }
                    }
                }
            }
            day++;
        }
        return count == 0 ? day - 1 : -1;
    }

    private boolean isValidIndex(int[][] grid, int row, int col) {
        return row >= 0 && row < grid.length
                && col >= 0 && col < grid[0].length;
    }
}
