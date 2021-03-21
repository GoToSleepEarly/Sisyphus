package 周赛;

public class 最大升序子数组和 {

    public int maxAscendingSum(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }
        int right = 1;
        int max = nums[0];
        int sum = max;
        while (right < nums.length) {

            if (nums[right] > nums[right - 1]) {
                sum += nums[right];
            } else {
                sum = nums[right];
            }
            max = Math.max(sum, max);
            right++;
        }
        return max;
    }
}
