package 算法.搜索.深度优先搜索.回溯;

import java.util.LinkedList;
import java.util.List;

public class No51N皇后 {

    List<List<String>> res;
    // 每一行放置的位置
    int[] rows;

    public List<List<String>> solveNQueens(int n) {
        res = new LinkedList<>();
        if (n == 0) {
            return res;
        }
        rows = new int[n];
        helper(0, n);
        return res;
    }

    private void helper(int row, int n) {
        if (row == n) {
            res.add(printNQueens());
            return;
        }
        for (int col = 0; col < n; col++) {
            if (canPut(row, col)) {
                rows[row] = col;
                helper(row + 1, n);
            }
        }
    }

    private List<String> printNQueens() {
        List<String> cur = new LinkedList<>();
        for (int row : rows) {
            StringBuilder sb = new StringBuilder();
            for (int j = 0; j < rows.length; j++) {
                if (row == j) {
                    sb.append('Q');
                } else {
                    sb.append('.');
                }
            }
            cur.add(sb.toString());
        }
        return cur;
    }

    private boolean canPut(int row, int col) {
        for (int i = 0; i < row; i++) {
            int preCol = rows[i];
            if (preCol == col || (row - i) == Math.abs(preCol - col)) {
                return false;
            }
        }
        return true;
    }
}
