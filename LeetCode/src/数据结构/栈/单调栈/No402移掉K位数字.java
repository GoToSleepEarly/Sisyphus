package 数据结构.栈.单调栈;

import java.util.Deque;
import java.util.LinkedList;

public class No402移掉K位数字 {
    public static void main(String[] args) {
        new No402移掉K位数字().removeKdigits("9", 1);
    }

    public String removeKdigits(String num, int k) {
        Deque<Integer> stack = new LinkedList<>();
        int count = 0;
        for (int i = 0; i < num.length(); i++) {
            while (count < k && !stack.isEmpty() && num.charAt(i) < num.charAt(stack.peek())) {
                count++;
                stack.pollFirst();
            }
            stack.addFirst(i);
        }
        while (count < k) {
            stack.pollFirst();
            count++;
        }
        while (!stack.isEmpty() && num.charAt(stack.peekLast()) == '0') {
            stack.pollLast();
        }
        StringBuilder sb = new StringBuilder();
        while (!stack.isEmpty()) {
            sb.append(num.charAt(stack.pollLast()));
        }
        return sb.length() == 0 ? "0" : sb.toString();

    }
}
