package 算法.动态规划.背包问题.零一背包;

import java.util.Arrays;

public class No416分割等和子集 {

    // 优先考虑递归，然后转换成动态规划
    public boolean canPartition(int[] nums) {
        int sum = Arrays.stream(nums).sum();
        if (sum % 2 != 0) {
            return false;
        }
        int half = sum / 2;
        // 转换成0-1背包问题
        //dp[i][j]表示从数组的 [0, i] 这个子区间内挑选一些正整数，每个数只能用一次，使得这些数的和恰好等于 j。
        boolean[][] dp = new boolean[nums.length][half + 1];
        if (nums[0] <= half) {
            dp[0][nums[0]] = true;
        }
        for (int i = 1; i < nums.length; i++) {
            for (int j = 0; j <= half; j++) {
                if (j - nums[i] > 0) {
                    dp[i][j] = dp[i - 1][j - nums[i]] || dp[i - 1][j];
                } else {
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }
        return dp[nums.length - 1][half];
    }
}
