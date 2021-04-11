package 算法.搜索.回溯;

import java.util.LinkedList;
import java.util.List;

public class No22括号生成 {

    List<String> res;

    public List<String> generateParenthesis(int n) {
        res = new LinkedList<>();
        dfs(n, n, new StringBuilder());
        return res;
    }

    private void dfs(int left, int right, StringBuilder sb) {

        if (left == 0 && right == 0) {
            res.add(sb.toString());
            return;
        }
        if (left < 0 || right < 0) {
            return;
        }
        // 选左括号
        if (left > 0) {
            sb.append("(");
            dfs(left - 1, right, sb);
            sb.deleteCharAt(sb.length() - 1);
        }
        // 选右括号
        if (right > left) {
            sb.append(")");
            dfs(left, right - 1, sb);
            sb.deleteCharAt(sb.length() - 1);
        }
    }
}
