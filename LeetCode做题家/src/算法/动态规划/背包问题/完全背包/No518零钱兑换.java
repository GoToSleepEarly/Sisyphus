package 算法.动态规划.背包问题.完全背包;

public class No518零钱兑换 {
    public static void main(String[] args) {
        new No518零钱兑换().change(5, new int[]{1, 2, 5});
    }

    int[][] dp;

    /**
     * 0-1背包 dp[i][j]
     * 完全背包 dp[i] 其实ij也行
     */
    public int change(int amount, int[] coins) {
        if (coins.length == 0) {
            return amount == 0 ? 1 : 0;
        }
        dp = new int[coins.length][amount + 1];
        return dfs(amount, coins, coins.length - 1);
    }

    private int dfs(int amount, int[] coins, int index) {
        if (dp[index][amount] != 0) {
            return dp[index][amount];
        }
        if (index == 0) {
            dp[index][amount] = amount % coins[index] == 0 ? 1 : 0;
            return dp[index][amount];
        }
        int coin = coins[index];
        int sum = 0;
        int res = 0;
        while (sum <= amount) {
            res += dfs(amount - sum, coins, index - 1);
            sum += coin;
        }
        dp[index][amount] = res;
        return res;
    }

    public int change2(int amount, int[] coins) {
        int dp[] = new int[amount + 1];
        // 设置起始状态
        dp[0] = 1;

        for (int coin : coins) {
            // 记录每添加一种面额的零钱，总金额j的变化
            for (int j = 1; j <= amount; j++) {
                if (j >= coin) {
                    // 在上一钟零钱状态的基础上增大
                    // 例如对于总额5，当只有面额为1的零钱时，只有一种可能 5x1
                    // 当加了面额为2的零钱时，除了原来的那一种可能外
                    // 还加上了组合了两块钱的情况，而总额为5是在总额为3的基础上加上两块钱来的
                    // 所以就加上此时总额为3的所有组合情况
                    dp[j] = dp[j] + dp[j - coin];
                }
            }
        }
        return dp[amount];
    }
}
