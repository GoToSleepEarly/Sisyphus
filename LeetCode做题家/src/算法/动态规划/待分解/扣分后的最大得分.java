package 算法.动态规划.待分解;

import java.util.Arrays;

public class 扣分后的最大得分 {

    /**
     * 动态规划题目，最重要的是列出式子（抽出变量和常量），然后画表
     * 题意分析:
     * dp[i][j] = max(dp[i-1][j'] - abs(j'-j)) + dp[i][j]  0<=j<=n
     * 很初始的想法，然后超时了
     * <p>
     * 思路为：
     * 拿到式子后，继续分解
     * dp[i][j] = max(dp[i-1][j'] - (j'-j)) + dp[i][j]  j'<=j<=n
     * -------- = max(dp[i-1][j'] - (j-j')) + dp[i][j]  0<=j<j'
     * 提取并优化
     * dp[i][j] = max(dp[i-1][j'] + j') -j + dp[i][j]  0<=j<j' 习惯从小到大，从左到右
     * -------- = max(dp[i-1][j'] - j') +j + dp[i][j]  j'<=j<=n
     * 也就是说，对于i和j来说，其左侧的max值是一个常量，其右侧的max值也是一个常量
     * 所以，我们可以用dp表示结果，leftMax表示上一层左侧的max区域，rightMax表示上一层右侧的max区域
     * <p>
     * 误区：
     * 1、列出公式后不进行变量抽取
     * 2、不画图
     */
    public long maxPoints(int[][] points) {
        int m = points.length;
        int n = points[0].length;
        long[] dp = new long[n];
        long[] leftMax = new long[n];
        long[] rightMax = new long[n];
        // 初始化数据
        for (int j = 0; j < n; j++) {
            dp[j] = points[0][j];
        }
        for (int i = 0; i < n; i++) {
            leftMax[i] = i == 0 ? dp[0] : Math.max(leftMax[i - 1], dp[i] + i);
        }
        for (int i = n - 1; i >= 0; i--) {
            rightMax[i] = i == n - 1 ? dp[n - 1] - n + 1 : Math.max(rightMax[i + 1], dp[i] - i);
        }
        // 开始进行dp
        for (int i = 1; i < m; i++) {
            for (int j = 0; j < n; j++) {
                dp[j] = Math.max(leftMax[j] - j,
                        rightMax[j] + j) + points[i][j];
            }
            // 更新leftMax和rightMax
            for (int j = 0; j < n; j++) {
                leftMax[j] = j == 0 ? dp[0] : Math.max(leftMax[j - 1], dp[j] + j);
            }
            for (int k = n - 1; k >= 0; k--) {
                rightMax[k] = k == n - 1 ? dp[n - 1] - n + 1 : Math.max(rightMax[k + 1], dp[k] - k);
            }
        }
        return Arrays.stream(dp).max().getAsLong();
    }
}
