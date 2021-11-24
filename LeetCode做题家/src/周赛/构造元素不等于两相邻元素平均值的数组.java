package 周赛;

import java.util.Arrays;

public class 构造元素不等于两相邻元素平均值的数组 {

    public int[] rearrangeArray(int[] nums) {
        Arrays.sort(nums);
        int n = nums.length;
        for (int i = 0; i < nums.length; i++) {
            if (i + 1 == n) {
                continue;
            }
            int tmp = nums[i];
            nums[i] = nums[i + 1];
            nums[i + 1] = tmp;
            i++;
        }
        return nums;
    }
}
