package 算法.搜索.回溯;

public class No79单词搜索 {

    boolean[][] visited;
    int[][] dirs;

    public boolean exist(char[][] board, String word) {
        int m = board.length;
        int n = board[0].length;
        visited = new boolean[m][n];
        dirs = new int[][]{{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {

                if (dfs(board, 0, i, j, word)) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean dfs(char[][] board, int index, int row, int col, String word) {
        // 不相等直接返回
        if (board[row][col] != word.charAt(index)) {
            return false;
        }
        if (index == word.length() - 1) {
            return true;
        }
        //相等，标记为已经访问过
        visited[row][col] = true;
        //否则左右上下都递归
        boolean isFind = false;
        for (int[] dir : dirs) {
            if (!isFind && isValidRowCol(board, row + dir[0], col + dir[1])) {
                isFind = dfs(board, index + 1, row + dir[0], col + dir[1], word);
            }
        }
        visited[row][col] = false;
        return isFind;
    }

    private boolean isValidRowCol(char[][] board, int row, int col) {
        int m = board.length;
        int n = board[0].length;
        return row >= 0 && row < m && col >= 0 && col < n && !visited[row][col];
    }


}
