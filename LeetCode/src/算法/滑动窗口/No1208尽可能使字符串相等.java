package 算法.滑动窗口;

public class No1208尽可能使字符串相等 {

    public int equalSubstring(String s, String t, int maxCost) {
        char[] a = s.toCharArray();
        char[] b = t.toCharArray();
        int left = 0;
        int right = 0;
        int cost = 0;
        int max = 0;
        while (right < a.length) {
            // 先加
            cost += Math.abs(a[right] - b[right]);
            right++;
            // 发现加不进去，右缩
            while (cost > maxCost) {
                // 这可以提前计算
                cost -= Math.abs(a[left] - b[left]);
                left++;
            }
            max = Math.max(max, right - left);

        }
        return max;
    }
}
