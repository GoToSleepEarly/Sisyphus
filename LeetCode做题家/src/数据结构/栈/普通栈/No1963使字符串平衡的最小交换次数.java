package 数据结构.栈.普通栈;

import java.util.Deque;
import java.util.LinkedList;

public class No1963使字符串平衡的最小交换次数 {

    public int minSwaps(String s) {
        Deque<Character> stack = new LinkedList<>();
        for (char c : s.toCharArray()) {
            if (c == ']' && !stack.isEmpty() && stack.peekLast() == '[') {
                stack.pollLast();
                continue;
            }
            stack.addLast(c);
        }

        return (stack.size()/2+1)/2;
    }
}
