package 算法.双指针.滑动窗口;

import java.util.HashMap;
import java.util.Map;

public class No3无重复字符的最长子串 {

    public int lengthOfLongestSubstring(String s) {
        int left = 0;
        int right = 0;
        int res = 0;
        Map<Character, Integer> lastIndexMap = new HashMap<>();
        while (right < s.length()) {
            // 加入滑动窗口
            char cur = s.charAt(right);
            if (lastIndexMap.containsKey(cur)) {
                int lastIndex = lastIndexMap.get(cur);
                if (lastIndex >= left) {
                    left = lastIndex + 1;
                }
            }
            lastIndexMap.put(cur, right);
            res = Math.max(res, right - left + 1);
            right++;
        }
        return res;
    }
}
