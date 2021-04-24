package 数据结构.堆;

import java.util.PriorityQueue;

public class No215数组中的第K个最大元素 {
    public static void main(String[] args) {
        new No215数组中的第K个最大元素().findKthLargest2(new int[]{3, 2, 1, 5, 6, 4}, 2);
    }

    public int findKthLargest(int[] nums, int k) {
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        for (int num : nums) {
            if (minHeap.size() < k) {
                minHeap.add(num);
            } else {
                if (num > minHeap.peek()) {
                    minHeap.poll();
                    minHeap.add(num);
                }
            }
        }
        return minHeap.peek();
    }

    public int findKthLargest2(int[] nums, int k) {
        return partition(nums, 0, nums.length - 1, nums.length - k);
    }

    private int partition(int[] nums, int left, int right, int k) {
        int pivot = nums[left];
        int start = left;
        int end = right;
        while (start < end) {
            while (start < end && nums[end] > pivot) {
                end--;
            }
            while (start < end && nums[start] <= pivot) {
                start++;
            }
            if (start < end) {
                int tmp = nums[start];
                nums[start] = nums[end];
                nums[end] = tmp;
            }
        }
        nums[left] = nums[start];
        nums[start] = pivot;
        if (start == k) {
            return nums[start];
        } else if (start < k) {
            return partition(nums, start + 1, right, k);
        } else {
            return partition(nums, left, start - 1, k);
        }
    }
}
