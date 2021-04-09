package 数据结构.栈.单调栈;

import java.util.Deque;
import java.util.LinkedList;

public class 直方图的水量 {

    public int trap(int[] height) {
        // 下标
        Deque<Integer> stack = new LinkedList<>();
        int n = height.length;
        int res = 0;
        for (int i = 0; i < n; i++) {
            int h = height[i];
            while (!stack.isEmpty() && h >= height[stack.peek()]) {
                int p = height[stack.pop()];
                if (stack.isEmpty()) {
                    // 不操作
                    break;
                }
                res += (Math.min(h, height[stack.peek()]) - p) * (i - stack.peek() - 1);
            }
            stack.addFirst(i);
        }
        return res;
    }
}
