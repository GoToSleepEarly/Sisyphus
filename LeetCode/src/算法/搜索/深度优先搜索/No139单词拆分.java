package 算法.搜索.深度优先搜索;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class No139单词拆分 {

    Map<String, Boolean> memo;

    public boolean wordBreak(String s, List<String> wordDict) {
        memo = new HashMap<>();
        return dfs(s, wordDict);
    }

    private boolean dfs(String s, List<String> wordDict) {
        if (memo.containsKey(s)) {
            return memo.get(s);
        }
        for (String word : wordDict) {
            if (s.startsWith(word)) {
                if (s.length() == word.length()) {
                    return true;
                }
                if (dfs(s.substring(word.length()), wordDict)) {
                    return true;
                }
            }

        }
        memo.put(s, false);
        return false;

    }
}
