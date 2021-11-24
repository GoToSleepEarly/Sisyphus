package 算法.贪心算法.待整理;

import java.util.Arrays;

public class 重新排列后的最大子矩阵 {

    /**
     * 贪心题目，最重要的是思路，也就是如何分析
     * 题意分析:
     * S = a*h，两个因素都不可控，考虑降维，即固定 底 或 高，枚举结果
     * <p>
     * 思路为：
     * 枚举每一行作为结果的底部，判断maxH为多少
     * 由于列是能变换的，所以所有列是统一的，所以如果枚举列，行中1的连续性会被打破，不满足
     * <p>
     * 误区：
     * 渐进法枚举行之后，就考虑当前局部即可。即每一次枚举的行，都是最终结果的一部分
     * 对应此题就是该行上必须是连续的1，到了0就不行，因为不满足题目定义
     * 如果把0也考虑，那么将不满足枚举的前提（因为0不可能是结果要的矩阵）
     */
    public int largestSubmatrix(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;
        int[][] dp = new int[m][n];
        // 枚举每个位置上有多少个连续的1
        for (int col = 0; col < n; col++) {
            int cnt = 0;
            for (int row = 0; row < m; row++) {
                if (matrix[row][col] == 1) {
                    cnt++;
                } else {
                    cnt = 0;
                }
                dp[row][col] = cnt;
            }
        }
        int max = 0;
        // 枚举每一行当成底部
        for (int row = 0; row < m; row++) {
            Arrays.sort(dp[row]);
            for (int col = n - 1; col >= 0; col--) {
                max = Math.max(max, (n - col) * dp[row][col]);
            }
        }
        return max;
    }
}
