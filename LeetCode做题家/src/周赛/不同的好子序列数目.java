package 周赛;

import java.util.HashSet;
import java.util.Set;

public class 不同的好子序列数目 {

    char[] chars;
    int mod = (int) (1e9 + 7);
    int res;
    Set<String> set;

    public int numberOfUniqueGoodSubsequences(String binary) {
        res = 0;
        chars = binary.toCharArray();
        set = new HashSet<>();
        dfs(0, new StringBuilder(), false);
        return binary.contains("0") ? res + 1 : res;
    }

    private void dfs(int i, StringBuilder sb, boolean has) {
        if (i == chars.length) {
            if (sb.length() != 0 && set.add(sb.toString())) {
                res = (res + 1) % mod;
            }
            return;
        }
        char c = chars[i];
        if (has) {
            sb.append(c);
            dfs(i + 1, sb, true);
            sb.deleteCharAt(sb.length() - 1);
            dfs(i + 1, sb, true);
        } else {
            if (c == '1') {
                sb.append(c);
                dfs(i + 1, sb, true);
                sb.deleteCharAt(sb.length() - 1);
            } else {
                dfs(i + 1, sb, false);
            }
        }
    }

}
