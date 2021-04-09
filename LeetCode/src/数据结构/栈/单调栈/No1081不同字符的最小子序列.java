package 数据结构.栈.单调栈;

import java.util.*;

public class No1081不同字符的最小子序列 {

    public String smallestSubsequence(String s) {
        //进入单调栈加了条件判断
        Deque<Character> stack = new LinkedList<>();
        int n = s.length();
        //计算每一个字符最后出现的位置
        Map<Character, Integer> lastAppear = new HashMap<>();
        for (int i = 0; i < n; i++) {
            lastAppear.merge(s.charAt(i), i, (i1, i2) -> i2);
        }
        Set<Character> visited = new HashSet<>();
        for (int i = 0; i < n; i++) {
            if (visited.contains(s.charAt(i))) {
                continue;
            }
            while (!stack.isEmpty()
                    && stack.peekFirst() > s.charAt(i)
                    && lastAppear.get(stack.peekFirst()) > i) {
                visited.remove(stack.pollFirst());
            }
            visited.add(s.charAt(i));
            stack.addFirst(s.charAt(i));
        }
        StringBuilder sb = new StringBuilder();
        while (!stack.isEmpty()) {
            sb.append(stack.pollLast());
        }
        return sb.toString();
    }
}
