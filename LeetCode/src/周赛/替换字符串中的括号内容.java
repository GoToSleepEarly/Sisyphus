package 周赛;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class 替换字符串中的括号内容 {

    public String evaluate(String s, List<List<String>> knowledge) {
        Map<String, String> map = new HashMap<>();
        for (List<String> strings : knowledge) {
            map.put(strings.get(0), strings.get(1));
        }
        StringBuilder res = new StringBuilder();
        int right = 0;
        while (right != s.length()) {
            if (s.charAt(right) == '(') {
                StringBuilder tmp = new StringBuilder();
                right++;
                while (s.charAt(right) != ')') {
                    tmp.append(s.charAt(right));
                    right++;
                }
                right++;
                String tmpRes = tmp.toString();
                res.append(map.getOrDefault(tmpRes, "?"));
            } else {
                res.append(s.charAt(right));
                right++;
            }
        }
        return res.toString();
    }
}
