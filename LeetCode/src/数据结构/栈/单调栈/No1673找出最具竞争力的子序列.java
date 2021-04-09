package 数据结构.栈.单调栈;

import java.util.Deque;
import java.util.LinkedList;

public class No1673找出最具竞争力的子序列 {

    public int[] mostCompetitive(int[] nums, int k) {
        Deque<Integer> stack = new LinkedList<>();
        int n = nums.length;
        int count = n - k;
        for (int i = 0; i < n; i++) {
            while (count > 0 && !stack.isEmpty() && nums[i] < nums[stack.peek()]) {
                // 移除
                stack.poll();
                count--;
            }
            stack.addFirst(i);
        }
        while (count > 0) {
            stack.poll();
            count--;
        }
        int[] res = new int[stack.size()];
        int index = res.length - 1;
        while (!stack.isEmpty()) {
            res[index--] = nums[stack.poll()];
        }
        return res;
    }
}
