package 算法.双指针.二分查找;

public class No154寻找旋转排序数组中的最小值2 {

    public int findMin(int[] nums) {
        int left = 0;
        int right = nums.length - 1;
        while (left < right) {
            int mid = (right + left) / 2;
            if (nums[mid] == nums[right]) {
                right--;
            } else if (nums[mid] < nums[right]) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return nums[left];
    }
}
