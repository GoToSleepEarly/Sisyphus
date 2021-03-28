package 数据结构.栈.单调栈;

import java.util.Deque;
import java.util.LinkedList;

public class No739每日温度 {

    public int[] dailyTemperatures(int[] T) {
        Deque<Integer> stack = new LinkedList<>();
        int[] res = new int[T.length];
        for (int i = 0; i < T.length; i++) {
            int temp = T[i];
            while (!stack.isEmpty() && temp > T[stack.peek()]) {
                int index = stack.poll();
                res[index] = i - index;
            }
            stack.addFirst(i);
        }
        while (!stack.isEmpty()) {
            res[stack.poll()] = 0;
        }
        return res;
    }
}
