package 周赛;

public class 两个回文子序列长度的最大乘积 {

    int max = 0;

    public int maxProduct(String s) {
        dfs(0, s, new StringBuilder(), new StringBuilder());
        return max;
    }

    private void dfs(int i, String s, StringBuilder a, StringBuilder b) {
        if (i == s.length()) {
            if (a.length() == 0 || b.length() == 0) {
                return;
            }
            int xa = longestPalindromeSubseq(a.toString());
            int xb = longestPalindromeSubseq(b.toString());
            max = Math.max(max, xa * xb);
            return;
        }
        // 选
        a.append(s.charAt(i));
        dfs(i + 1, s, a, b);
        a.deleteCharAt(a.length() - 1);
        // 不选
        b.append(s.charAt(i));
        dfs(i + 1, s, a, b);
        b.deleteCharAt(b.length() - 1);
    }


    public int longestPalindromeSubseq(String s) {
        int n = s.length();
        int[][] dp = new int[n][n];
        for (int i = n - 1; i >= 0; i--) {
            dp[i][i] = 1;
            for (int j = i + 1; j < n; j++) {
                if (s.charAt(i) == s.charAt(j)) {
                    dp[i][j] = j - i >= 2 ? dp[i + 1][j - 1] + 2 : 2;
                } else {
                    dp[i][j] = Math.max(dp[i][j - 1], dp[i + 1][j]);
                }
            }
        }
        return dp[0][n - 1];
    }
}
