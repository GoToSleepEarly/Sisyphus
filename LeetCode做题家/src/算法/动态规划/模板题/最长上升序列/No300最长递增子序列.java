package 算法.动态规划.模板题.最长上升序列;

import java.util.Arrays;

public class No300最长递增子序列 {

    public int lengthOfLIS(int[] nums) {
        int len = -1;
        int n = nums.length;
        int[] dp = new int[n];
        Arrays.fill(dp, Integer.MIN_VALUE);
        // 对于每一个数，更新dp
        for (int num : nums) {
            // 注意是 <,如果相等并不能增长，因为是递增
            if (len == -1 || dp[len] < num) {
                dp[++len] = num;
                continue;
            }
            // 否则，替换第一个比他大的元素
            int index = findFirst(dp, len, num);
            dp[index] = num;
        }
        // len是下标，要+1
        return len + 1;
    }

    private int findFirst(int[] dp, int len, int num) {
        int left = 0;
        int right = len;
        while (left < right) {
            int mid = (left + right) / 2;
            if (dp[mid] < num) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        return left;
    }

}
