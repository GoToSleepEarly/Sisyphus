package 数据结构.栈.普通栈;

import java.util.Set;
import java.util.Stack;

public class No150逆波兰表达式求值 {

    public int evalRPN(String[] tokens) {
        Set<String> operSet = Set.of("+", "-", "*", "/");
        Stack<String> stack = new Stack<>();
        for (String token : tokens) {
            if (operSet.contains(token)) {
                //取两位运算
                int right =Integer.parseInt(stack.pop());
                int left = Integer.parseInt(stack.pop());
                int res = switch (token) {
                    case "+" -> right + left;
                    case "-" -> left - right;
                    case "*" -> left * right;
                    default -> left / right;
                };
                stack.add(String.valueOf(res));
            } else {
                stack.add(token);
            }
        }
        return Integer.parseInt(stack.pop());
    }


}
