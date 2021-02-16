package 算法.滑动窗口;

public class No76最小覆盖字串 {
    public static void main(String[] args) {
        No76最小覆盖字串 s = new No76最小覆盖字串();
        s.minWindow("acbbaca", "aba");
    }

    public String minWindow(String s, String t) {
        int left = 0;
        int right = 0;
        String res = null;
        // 'z'和'A'中间隔了其他字符
        int[] countT = new int['z' - 'A'];
        for (char c : t.toCharArray()) {
            countT[c - 'A']++;
        }
        int[] countS = new int[countT.length];
        // 一般频次的可以用diff来替代遍历
        int dif = t.length();
        while (right < s.length()) {
            // 右侧加上right
            int add = s.charAt(right) - 'A';
            countS[add]++;
            if (countS[add] == countT[add]) {
                //需要考虑重复的情况
                // 成功，则diff减去整体；失败，diff加上整体
                dif -= countT[add];
            }
            right++;
            //收缩左边left
            while (dif == 0) {
                // 满足条件先update结果
                if (res == null || res.length() > right - left) {
                    res = s.substring(left, right);
                }
                // 减去左侧
                int remove = s.charAt(left) - 'A';
                countS[remove]--;
                if (countS[remove] == countT[remove] - 1) {
                    dif += countT[remove];
                }
                left++;
            }
        }
        return res == null ? "" : res;

    }


}
