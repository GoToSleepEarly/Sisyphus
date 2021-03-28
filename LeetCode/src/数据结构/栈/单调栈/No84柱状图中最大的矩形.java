package 数据结构.栈.单调栈;

import java.util.Deque;
import java.util.LinkedList;

public class No84柱状图中最大的矩形 {
    public static void main(String[] args) {
        No84柱状图中最大的矩形 s = new No84柱状图中最大的矩形();
        s.largestRectangleArea(new int[]{2, 1, 5, 6, 2, 3});
    }

    public int largestRectangleArea(int[] heights) {
        Deque<Integer> stack = new LinkedList<>();
        // 对于每一个数，找左右第一个比他小的
        int res = 0;
        for (int i = 0; i < heights.length; i++) {
            int height = heights[i];
            while (!stack.isEmpty() && height < heights[stack.peek()]) {
                // 左右已经找到
                int minHeight = heights[stack.poll()];
                int leftIndex = stack.isEmpty() ? -1 : stack.peek();
                int area = (i - leftIndex - 1) * minHeight;
                res = Math.max(area, res);
            }
            stack.addFirst(i);
        }
        //剩余的重新计算
        while (!stack.isEmpty()) {
            int tmp = heights[stack.pop()];
            int cur = stack.isEmpty() ? heights.length * tmp : tmp * (heights.length - stack.peek() - 1);
            res = Math.max(res, cur);
        }
        return res;
    }
}
