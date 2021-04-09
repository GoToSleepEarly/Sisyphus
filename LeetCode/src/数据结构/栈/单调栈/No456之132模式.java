package 数据结构.栈.单调栈;

import java.util.Deque;
import java.util.LinkedList;

public class No456之132模式 {
    public boolean find132pattern(int[] nums) {
        int n = nums.length;
        int[] min = new int[n];
        min[0] = Integer.MAX_VALUE;
        // 对于每一个j，左侧最小的i
        for (int i = 1; i < n; i++) {
            min[i] = Math.min(nums[i - 1], min[i - 1]);
        }
        // 枚举k，遇到j判断是否满足
        Deque<Integer> stack = new LinkedList<>();
        for (int i = n - 1; i >= 0; i--) {
            while (!stack.isEmpty() && nums[i] > nums[stack.peek()]) {
                if (min[i] < nums[stack.poll()]) {
                    return true;
                }
            }
            stack.addFirst(i);
        }
        return false;
    }
}
