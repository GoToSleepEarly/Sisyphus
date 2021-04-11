package 算法.双指针.二分查找;

public class No153寻找旋转排序数组中的最小值 {
    public int findMin(int[] nums) {
        if (nums.length == 0) {
            return -1;
        }
        int left = 0;
        int right = nums.length - 1;
        while (left < right) {
            int mid = left + (right - left) / 2;
            // 有序
            if (nums[left] <= nums[mid] && nums[mid] <= nums[right]) {
                return nums[left];
            }

            if (nums[mid] >= nums[left]) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        return nums[right];
    }
}
