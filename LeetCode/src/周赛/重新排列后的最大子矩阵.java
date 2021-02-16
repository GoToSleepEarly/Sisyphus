package 周赛;

import java.util.Arrays;

public class 重新排列后的最大子矩阵 {

    public static void main(String[] args) {
        重新排列后的最大子矩阵 s = new 重新排列后的最大子矩阵();
        s.largestSubmatrix(new int[][]{{0, 0, 1}, {1, 1, 1}, {1, 0, 1}});
    }

    public int largestSubmatrix(int[][] matrix) {
        // 非常有意思的贪心
        int m = matrix.length;
        int n = matrix[0].length;
        int[][] heights = new int[m][n];
        // 按照每一行当成底来贪心
        // 如果不能交换列，就是困难题——最大矩形
        // 初始i=0
        for (int j = 0; j < n; j++) {
            if (matrix[0][j] == 1) {
                heights[0][j] = 1;
            }
        }
        for (int i = 1; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == 1) {
                    heights[i][j] = heights[i - 1][j] + 1;
                } else {
                    heights[i][j] = 0;
                }
            }
        }
        // 对于每一行，排序后取最大就行了
        int res = 0;
        for (int i = 0; i < m; i++) {
            int[] curHeights = heights[i];
            Arrays.sort(curHeights);
            for (int j = 0; j < n; j++) {
                int minHeight = curHeights[j];
                res = Math.max(res, minHeight * (n - j));

            }
        }
        return res;
    }
}
