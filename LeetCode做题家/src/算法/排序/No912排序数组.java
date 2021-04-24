package 算法.排序;

public class No912排序数组 {

    public int[] sortArray(int[] nums) {
        // 快排
        quickSort(nums, 0, nums.length - 1);
        return nums;
    }

    private void quickSort(int[] nums, int left, int right) {
        if (left >= right) {
            return;
        }
        //找到pivot，分割
        int pivotIndex = partition(nums, left, right);
        quickSort(nums, left, pivotIndex - 1);
        quickSort(nums, pivotIndex + 1, right);
    }

    private int partition(int[] nums, int left, int right) {
        int pivot = nums[left];
        int start = left;
        while (start < right) {
            // 从右往左找小于的值
            while (start < right && nums[right] > pivot) {
                right--;
            }
            while (start < right && nums[start] <= pivot) {
                start++;
            }
            // 如果不是因为相遇退出
            if (start < right) {
                int tmp = nums[start];
                nums[start] = nums[right];
                nums[right] = tmp;
            }
        }

        nums[left] = nums[start];
        nums[start] = pivot;
        return start;
    }

}
