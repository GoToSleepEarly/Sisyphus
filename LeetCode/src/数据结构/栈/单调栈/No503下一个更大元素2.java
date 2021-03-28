package 数据结构.栈.单调栈;

import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;

public class No503下一个更大元素2 {

    public int[] nextGreaterElementsUsingMod(int[] nums) {
        int n = nums.length;
        int[] ret = new int[n];
        Arrays.fill(ret, -1);
        Deque<Integer> stack = new LinkedList<Integer>();
        for (int i = 0; i < n * 2 - 1; i++) {
            while (!stack.isEmpty() && nums[stack.peek()] < nums[i % n]) {
                ret[stack.pop()] = nums[i % n];
            }
            stack.push(i % n);
        }
        return ret;
    }

    public int[] nextGreaterElements(int[] nums) {
        Deque<Integer> stack = new LinkedList<>();
        int[] res = new int[nums.length];
        // 遍历两次就行了
        for (int i = 0; i < nums.length; i++) {
            int num = nums[i];
            while (!stack.isEmpty() && nums[stack.peek()] < num) {
                int index = stack.poll();
                res[index] = num;
            }
            stack.addFirst(i);
        }
        // 第二遍继续搜索
        // 第二遍完全一样，除了不添加元素
        for (int i = 0; i < nums.length; i++) {
            int num = nums[i];
            while (!stack.isEmpty() && nums[stack.peek()] < num) {
                int index = stack.poll();
                res[index] = num;
            }
            // 第二遍不需要添加元素
        }
        while (!stack.isEmpty()) {
            res[stack.poll()] = -1;
        }
        return res;
    }
}
