package 算法.动态规划.背包问题.零一背包;

public class No474一和零 {

    int[][][] dp;

    public int findMaxForm(String[] strs, int m, int n) {
        dp = new int[strs.length][m + 1][n + 1];
        int len = strs.length;
        return dfs(strs, len - 1, m, n);
    }

    private int dfs(String[] strs, int index, int m, int n) {
        if (index < 0) {
            return 0;
        }
        if (dp[index][m][n] != 0) {
            return dp[index][m][n];
        }
        String cur = strs[index];
        int mCnt = 0;
        int nCnt = 0;
        for (char c : cur.toCharArray()) {
            if (c == '0') {
                mCnt++;
            } else {
                nCnt++;
            }
        }
        // 不选
        int max = dfs(strs, index - 1, m, n);
        if (mCnt <= m && nCnt <= n) {
            max = Math.max(dfs(strs, index - 1, m - mCnt, n - nCnt) + 1, max);
        }
        dp[index][m][n] = max;
        return max;
    }
}
