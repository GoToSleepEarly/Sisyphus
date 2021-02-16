package 算法.动态规划;

public class No198打家劫舍 {

    class Solution {
        /**
         * 可以先从递归开始入手（从小到大叫枚举，从大到小才是递归）
         * 易得：f(n) = Math.max{(f(n-2)+num[i],f(n-1)}
         *
         * @param nums nums
         * @return res
         */
        public int rob(int[] nums) {
            // 异常情况
            if (nums == null || nums.length == 0) {
                return 0;
            }
            int n = nums.length;
            if(n == 1){
                return nums[0];
            }
            // 开始dp
            int[] dp = new int[n];
            dp[0] = nums[0];
            dp[1] = Math.max(nums[0],nums[1]);
            for (int i = 2; i < n; i++) {
                dp[i] = Math.max(dp[i - 2] + nums[i], dp[i - 1]);
            }
            return dp[n-1];
        }
    }
}
