package 周赛;

import java.util.Arrays;

public class 学生分数的最小差值 {

    public int minimumDifference(int[] nums, int k) {
        Arrays.sort(nums);
        int left = 0;
        int right = k - 1;
        int min = Integer.MAX_VALUE;
        while (right < nums.length) {
            min = Math.min(nums[right] - nums[left], min);
            right++;
            left++;
        }
        return min;
    }
}
