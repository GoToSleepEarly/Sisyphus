package 算法.搜索.深度优先搜索;

public class No130被围绕的区域 {

    int[][] dirs;

    public void solve(char[][] board) {
        // 难点在于找寻起点
        int m = board.length;
        int n = board[0].length;
        boolean[][] isSafe = new boolean[m][n];
        boolean[][] visited = new boolean[m][n];
        dirs = new int[][]{{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
        for (int i = 0; i < n; i++) {
            dfs(board, isSafe, visited, 0, i);
            dfs(board, isSafe, visited, m - 1, i);
        }
        for (int j = 0; j < m; j++) {
            dfs(board, isSafe, visited, j, 0);
            dfs(board, isSafe, visited, j, n - 1);
        }

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                board[i][j] = isSafe[i][j] ? 'O' : 'X';
            }
        }
    }

    private void dfs(char[][] board, boolean[][] isSafe, boolean[][] visited, int row, int col) {
        if (!isValidIndex(board, row, col)) {
            return;
        }
        if (visited[row][col]) {
            return;
        }
        if (board[row][col] == 'X') {
            return;
        }
        isSafe[row][col] = true;
        visited[row][col] = true;
        for (int[] dir : dirs) {
            int nextRow = row + dir[0];
            int nextCol = col + dir[1];
            dfs(board, isSafe, visited, nextRow, nextCol);
        }
    }

    private boolean isValidIndex(char[][] board, int row, int col) {
        return row >= 0 && row < board.length
                && col >= 0 && col < board[0].length;
    }

}
