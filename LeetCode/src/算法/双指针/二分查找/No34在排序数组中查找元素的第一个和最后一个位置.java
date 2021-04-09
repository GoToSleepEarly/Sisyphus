package 算法.双指针.二分查找;

public class No34在排序数组中查找元素的第一个和最后一个位置 {
    public int[] searchRange(int[] nums, int target) {
        if (nums.length == 0) {
            return new int[]{-1, -1};
        }
        int first = searchFirst(nums, target);
        if (first == -1) {
            return new int[]{-1, -1};
        } else {
            return new int[]{first, searchLast(nums, target)};
        }
    }

    private int searchFirst(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] == target) {
                // 仍然是可能选到的
                right = mid;
            } else if (nums[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return nums[left] == target ? left : -1;
    }

    private int searchLast(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        while (left < right) {
            int mid = left + (right - left + 1) / 2;
            if (nums[mid] == target) {
                // 仍然是可能选到的
                left = mid;
            } else if (nums[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return nums[left] ==
                target ? left : -1;
    }
}
