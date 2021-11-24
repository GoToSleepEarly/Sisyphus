package 算法.动态规划.模板题.最长上升序列;

import java.util.Arrays;

public class 找出到每个位置为止最长的有效障碍赛跑路线 {

    public int[] longestObstacleCourseAtEachPosition(int[] obstacles) {
        int n = obstacles.length;
        int[] res = new int[n];
        int[] dp = new int[n];
        int len = -1;
        Arrays.fill(dp, Integer.MIN_VALUE);
        for (int i = 0; i < n; i++) {
            if (len == -1 || dp[len] <= obstacles[i]) {
                len++;
                dp[len] = obstacles[i];
                res[i] = len + 1;
            } else {
                int upper = findUpper(dp, len, obstacles[i]);
                dp[upper] = obstacles[i];
                res[i] = upper + 1;
            }
        }
        return res;

    }

    private int findUpper(int[] dp, int len, int num) {
        int left = 0;
        int right = len;
        while (left < right) {
            int mid = (left + right) / 2;
            if (dp[mid] <= num) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        return left;
    }


}
