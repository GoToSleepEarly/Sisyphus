package 算法.搜索.深度优先搜索;

public class No200岛屿数量 {

    int count;
    boolean[][] visited;
    int[][] dirs = new int[][]{{-1, 0}, {1, 0}, {0, 1}, {0, -1}};

    public int numIslands(char[][] grid) {
        count = 0;
        visited = new boolean[grid.length][grid[0].length];
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == '1' && !visited[i][j]) {
                    count++;
                    dfs(grid, i, j);
                }
            }
        }
        return count;
    }

    private void dfs(char[][] grid, int i, int j) {
        visited[i][j] = true;
        // 往4个方向拓展
        for (int[] dir : dirs) {
            int nextI = i + dir[0];
            int nextJ = j + dir[1];
            if (isValidIndex(grid, nextI, nextJ)
                    && grid[nextI][nextJ] == '1' && !visited[nextI][nextJ]) {
                dfs(grid, nextI, nextJ);
            }
        }
    }

    private boolean isValidIndex(char[][] grid, int i, int j) {
        return i >= 0 && i < grid.length
                && j >= 0 && j < grid[0].length;
    }
}
