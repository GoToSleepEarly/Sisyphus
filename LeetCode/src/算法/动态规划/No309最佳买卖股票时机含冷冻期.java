package 算法.动态规划;

public class No309最佳买卖股票时机含冷冻期 {
    public static void main(String[] args) {
        No309最佳买卖股票时机含冷冻期 s = new No309最佳买卖股票时机含冷冻期();
        s.maxProfit(new int[]{1, 2, 4});
    }

    public int maxProfit(int[] prices) {
        if (prices.length == 0) {
            return 0;
        }
        int[][] dp = new int[prices.length][2];
        dp[0][0] = -prices[0];
        dp[0][1] = 0;
        for (int i = 1; i < prices.length; i++) {
            if (i < 2) {
                dp[i][0] = Math.max(dp[i - 1][0], -prices[i]);
            } else {
                dp[i][0] = Math.max(dp[i - 1][0], dp[i - 2][1] - prices[i]);
            }
            dp[i][1] = Math.max(dp[i - 1][1], dp[i - 1][0] + prices[i]);
        }
        return dp[prices.length - 1][1];
    }

}
