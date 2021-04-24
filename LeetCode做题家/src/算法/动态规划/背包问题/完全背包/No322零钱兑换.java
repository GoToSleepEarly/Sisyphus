package 算法.动态规划.背包问题.完全背包;

import java.util.Arrays;

public class No322零钱兑换 {
    public static void main(String[] args) {
        new No322零钱兑换().coinChange(new int[]{1, 2, 5}, 11);
    }

    public int coinChange(int[] coins, int amount) {
        int[] dp = new int[amount + 1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;
        for (int i = 0; i <= amount; i++) {
            for (int coin : coins) {
                if (i >= coin && dp[i - coin] != Integer.MAX_VALUE) {
                    dp[i] = Math.min(dp[i - coin] + 1, dp[i]);
                }
            }
        }
        return dp[amount] == Integer.MAX_VALUE ? -1 : dp[amount];
    }
}
