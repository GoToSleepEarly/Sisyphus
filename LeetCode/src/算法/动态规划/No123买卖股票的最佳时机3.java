package 算法.动态规划;

public class No123买卖股票的最佳时机3 {

    public int maxProfit(int[] prices) {
        // 看了答案，需要再做一次
        int[][] dp = new int[prices.length][5];

        dp[0][0] = 0;
        dp[0][1] = -prices[0];
        dp[0][2] = 0;
        dp[0][3] = -prices[0];
        dp[0][4] = 0;

        for (int i = 1; i < prices.length; i++) {
            // 啥也不买，肯定0
            dp[i][0] = 0;
            // 买1，买1不变或者0到买1
            dp[i][1] = Math.max(dp[i - 1][0] - prices[i], dp[i - 1][1]);
            // 卖1，卖1不变或者买1到卖1
            dp[i][2] = Math.max(dp[i - 1][1] + prices[i], dp[i - 1][2]);
            // 买2，买2不变或者卖1到买2
            dp[i][3] = Math.max(dp[i - 1][2] - prices[i], dp[i - 1][3]);
            // 卖2，卖2不变或者买2到卖2
            dp[i][4] = Math.max(dp[i - 1][3] + prices[i], dp[i - 1][4]);
        }
        return dp[prices.length - 1][4];
    }
}
