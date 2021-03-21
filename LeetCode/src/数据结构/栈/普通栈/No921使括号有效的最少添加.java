package 数据结构.栈.普通栈;

import java.util.Stack;

public class No921使括号有效的最少添加 {

    public int minAddToMakeValid(String S) {
        char[] chars = S.toCharArray();
        Stack<Character> s = new Stack<>();
        for (char c : chars) {
            if (c == '(') {
                s.add(c);
            } else {
                if (!s.isEmpty() && s.peek() == '(') {
                    s.pop();
                } else {
                    s.add(')');
                }
            }
        }
        return s.size();
    }
}
