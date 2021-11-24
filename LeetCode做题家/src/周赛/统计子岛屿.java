package 周赛;

public class 统计子岛屿 {
    public static void main(String[] args) {
        int[][] grid1 = new int[][]{{1, 1, 1, 1, 0, 0}, {1, 1, 0, 1, 0, 0}, {1, 0, 0, 1, 1, 1}, {1, 1, 1, 0, 0, 1}, {1, 1, 1, 1, 1, 0}, {1, 0, 1, 0, 1, 0}, {0, 1, 1, 1, 0, 1}, {1, 0, 0, 0, 1, 1}, {1, 0, 0, 0, 1, 0}, {1, 1, 1, 1, 1, 0}};
        int[][] grid2 = new int[][]{{1, 1, 1, 1, 0, 1}, {0, 0, 1, 0, 1, 0}, {1, 1, 1, 1, 1, 1}, {0, 1, 1, 1, 1, 1}, {1, 1, 1, 0, 1, 0}, {0, 1, 1, 1, 1, 1}, {1, 1, 0, 1, 1, 1}, {1, 0, 0, 1, 0, 1}, {1, 1, 1, 1, 1, 1}, {1, 0, 0, 1, 0, 0}};
        new 统计子岛屿().countSubIslands(grid1, grid2);
    }

    int[][] g1;
    int[][] g2;
    int[][] visited;
    int[][] dirs;
    int term;

    public int countSubIslands(int[][] grid1, int[][] grid2) {
        g1 = grid1;
        g2 = grid2;
        dirs = new int[][]{{0, -1}, {0, 1}, {1, 0}, {-1, 0}};
        visited = new int[grid1.length][grid1[0].length];
        int count = 0;
        term = 0;
        for (int i = 0; i < grid1.length; i++) {
            for (int j = 0; j < grid1[0].length; j++) {
                if (grid2[i][j] == 1) {
                    term++;
                    if (dfs(i, j)) {
                        count++;
                    }
                }
            }
        }
        return count;
    }

    private boolean dfs(int i, int j) {
        if (g2[i][j] == 0) {
            return true;
        }
        if (g1[i][j] != 1) {
            return false;
        }
        if (visited[i][j] == term) {
            return true;
        }
        if (visited[i][j] != 0) {
            return false;
        }
        visited[i][j] = term;
        if (g2[i][j] == 1 && g1[i][j] == 1) {
            //往四个方向延伸
            boolean flag = true;
            for (int[] dir : dirs) {
                int nextX = dir[0] + i;
                int nextY = dir[1] + j;
                if (inArea(nextX, nextY)) {
                    flag = dfs(nextX, nextY);
                    if (!flag) {
                        return false;
                    }
                }
            }
            return true;
        }
        return false;
    }

    private boolean inArea(int i, int j) {
        return i >= 0 && i < g1.length && j >= 0 && j < g1[0].length;
    }
}
