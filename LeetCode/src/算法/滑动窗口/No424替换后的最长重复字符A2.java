package 算法.滑动窗口;

public class No424替换后的最长重复字符A2 {


    public int characterReplacement(String s, int k) {
        int left = 0;
        int right = 0;
        int maxCount = 0;
        int[] count = new int[26];
        int res = 0;
        while (right < s.length()) {
            int curCount = count[s.charAt(right) - 'A'];
            curCount++;
            maxCount = Math.max(curCount, maxCount);
            right++;
            int len = right - left;
            // 注意判断是if还是while
            if (maxCount < len - k) {
                count[s.charAt(left) - 'A']--;
                left++;
            }
            res = Math.max(res, right - left);
        }
        return res;
    }

}
