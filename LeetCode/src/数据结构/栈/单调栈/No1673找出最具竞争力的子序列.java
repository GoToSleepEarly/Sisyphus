package 数据结构.栈.单调栈;

import java.util.*;

public class No1673找出最具竞争力的子序列 {

    public int[] mostCompetitive(int[] nums, int k) {
        Deque<Integer> stack = new LinkedList<>();
        int[] res = new int[k];
        for (int i = 0; i < nums.length; i++) {
            int num = nums[i];
            while (!stack.isEmpty() && num < stack.peek()) {
                if (stack.size() + nums.length - i > k) {
                    stack.poll();
                } else {
                    break;
                }
            }
            stack.addFirst(num);
        }
        while (stack.size() > k) {
            stack.poll();
        }
        while (k > 0) {
            res[--k] = stack.pop();
        }

        return res;
    }
}
