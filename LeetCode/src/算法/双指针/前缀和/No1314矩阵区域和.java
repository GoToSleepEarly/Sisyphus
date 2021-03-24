package 算法.双指针.前缀和;

public class No1314矩阵区域和 {

    public int[][] matrixBlockSum(int[][] mat, int K) {
        // 二维前缀和
        int[][] preSum = new int[mat.length + 1][mat[0].length + 1];
        for (int i = 1; i < mat.length + 1; i++) {
            for (int j = 1; j < mat[0].length + 1; j++) {
                preSum[i][j] = preSum[i][j - 1] + preSum[i - 1][j]
                        - preSum[i - 1][j - 1] + mat[i - 1][j - 1];
            }
        }
        int[][] res = new int[mat.length][mat[0].length];
        for (int i = 0; i < mat.length; i++) {
            for (int j = 0; j < mat[0].length; j++) {
                // 此时坐标时包含该点的
                int row1 = Math.max(0, i - K);
                int col1 = Math.max(0, j - K);
                int row2 = Math.min(mat.length - 1, i + K);
                int col2 = Math.min(mat[0].length - 1, j + K);
                res[i][j] = preSum[row2+1][col2+1]-preSum[row1][col2+1]
                        -preSum[row2+1][col1]+preSum[row1][col1];
            }
        }
        return res;
    }
}
