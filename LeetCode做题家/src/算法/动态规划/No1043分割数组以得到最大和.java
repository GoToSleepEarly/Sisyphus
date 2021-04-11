package 算法.动态规划;

public class No1043分割数组以得到最大和 {

    public static void main(String[] args) {
        No1043分割数组以得到最大和 s = new No1043分割数组以得到最大和();
        s.maxSumAfterPartitioning(new int[]{1, 15, 7, 9, 2, 5, 10}, 3);
    }

    public int maxSumAfterPartitioning(int[] arr, int k) {
        int[] dp = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            int maxInK = 0;
            for (int j = 1; j <= k && j <= i+1; j++) {
                maxInK = Math.max(maxInK, arr[i - j + 1]);
                if (i - j < 0) {
                    dp[i] = maxInK * j;
                } else {
                    dp[i] = Math.max(dp[i - j] + j * maxInK, dp[i]);
                }
            }
        }
        return dp[arr.length - 1];
    }

}
