package 算法.搜索.深度优先搜索;

import java.util.LinkedList;
import java.util.List;

public class No417太平洋大西洋水流问题 {

    int[][] dirs;
    boolean[][] isPac;
    boolean[][] isAtl;

    //有向图一般不考虑并查集，且此题每个节点有个结果，和并查集性质不符
    public List<List<Integer>> pacificAtlantic(int[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return new LinkedList<>();
        }
        // 从四条边开始
        int m = matrix.length;
        int n = matrix[0].length;
        isPac = new boolean[m][n];
        isAtl = new boolean[m][n];
        dirs = new int[][]{{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
        for (int col = 0; col < n; col++) {
            dfs(matrix, 0, col, isPac);
        }
        for (int row = 0; row < m; row++) {
            dfs(matrix, row, 0, isPac);
        }
        for (int col = 0; col < n; col++) {
            dfs(matrix, m - 1, col, isAtl);
        }
        for (int row = 0; row < m; row++) {
            dfs(matrix, row, n - 1, isAtl);
        }
        List<List<Integer>> res = new LinkedList<>();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (isPac[i][j] && isAtl[i][j]) {
                    List<Integer> oneRes = new LinkedList<>();
                    oneRes.add(i);
                    oneRes.add(j);
                    res.add(oneRes);
                }
            }
        }
        return res;

    }

    private void dfs(int[][] matrix, int row, int col, boolean[][] ocean) {
        ocean[row][col] = true;
        for (int[] dir : dirs) {
            int nextRow = row + dir[0];
            int nextCol = col + dir[1];
            if (canFlow(matrix, nextRow, nextCol, matrix[row][col], ocean)) {
                dfs(matrix, nextRow, nextCol, ocean);
            }
        }
    }

    private boolean canFlow(int[][] matrix, int row, int col, int height, boolean[][] ocean) {
        return row >= 0 && row < matrix.length
                && col >= 0 && col < matrix[0].length
                && !ocean[row][col] && height <= matrix[row][col];
    }

}
