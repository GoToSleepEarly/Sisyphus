package 算法.双指针.二分查找;

public class No35搜索插入位置 {

    public int searchInsert(int[] nums, int target) {
        int left = 0;
        // right是可以选到nums.length的
        int right = nums.length;
        while (left < right) {

            int mid = left + (right - left) / 2;

            // 三分天下
            if (nums[mid] == target) {
                return mid;
            } else if (nums[mid] < target) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        // 最后left == right了，做最后的判断即可
        return right;
    }
}
