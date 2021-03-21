package 算法.搜索.深度优先搜索;

public class No695岛屿的最大面积 {
    boolean[][] visited;
    int max;
    int[][] dirs;

    // 当然可以使用并查集，因为只需要最后结果的连通块
    public int maxAreaOfIsland(int[][] grid) {
        max = 0;
        int m = grid.length;
        int n = grid[0].length;
        visited = new boolean[m][n];
        dirs = new int[][]{{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
        // 对于每一个节点dfs
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 0 || visited[i][j]) {
                    continue;
                }
                max = Math.max(max, dfs(grid, i, j));
            }
        }
        return max;
    }

    private int dfs(int[][] grid, int row, int col) {
        int count = 1;
        visited[row][col] = true;
        // 上下左右去遍历
        for (int[] dir : dirs) {
            int nextRow = row + dir[0];
            int nextCol = col + dir[1];
            if (isUnvisitedIsland(grid, nextRow, nextCol)) {
                count += dfs(grid, nextRow, nextCol);
            }
        }
        return count;
    }

    private boolean isUnvisitedIsland(int[][] grid, int row, int col) {
        return row >= 0 && row < grid.length &&
                col >= 0 && col < grid[0].length &&
                grid[row][col] == 1 && !visited[row][col];
    }
}