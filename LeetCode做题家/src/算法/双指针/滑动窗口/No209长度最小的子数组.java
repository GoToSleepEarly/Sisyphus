package 算法.双指针.滑动窗口;

public class No209长度最小的子数组 {

    public int minSubArrayLen(int target, int[] nums) {
        int right = 0;
        int left = 0;
        int curSum = 0;
        int minLen = Integer.MAX_VALUE;
        while (right < nums.length) {
            curSum += nums[right];

            // 收缩左窗口
            while (curSum >= target) {
                curSum -= nums[left];
                minLen = Math.min(minLen, right - left + 1);
                left++;
            }
            right++;


        }
        return minLen == Integer.MAX_VALUE ? 0 : minLen;
    }
}
