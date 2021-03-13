package 算法.滑动窗口;

public class No53最大子序和 {
    public static void main(String[] args) {
        No53最大子序和 s = new No53最大子序和();
        s.maxSubArray(new int[]{-1, -1, -2, -2});
    }

    public int maxSubArray(int[] nums) {
        // 虽然说是动态规划，但是感觉可以用滑动窗口来整活
        int right = 1;
        int n = nums.length;
        int max = nums[0];
        int sum = nums[0];
        while (right < n) {
            sum += nums[right];
            if (sum < nums[right]) {
                sum = nums[right];
            }
            max = Math.max(sum, max);
            right++;
        }
        return max;
    }
}
