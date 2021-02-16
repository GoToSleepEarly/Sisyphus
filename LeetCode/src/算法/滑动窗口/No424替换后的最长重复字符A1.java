package 算法.滑动窗口;


public class No424替换后的最长重复字符A1 {

    public int characterReplacement(String s, int k) {
        int left = 0;
        int right = 0;
        int max = 0;
        int[] count = new int[26];
        // 当前最多字符对应的数
        int majorCount = 0;
        while (right < s.length()) {
            char c = s.charAt(right);
            count[c - 'A']++;
            majorCount = Math.max(count[c - 'A'], majorCount);
            // 判断需要收缩
            while (majorCount + k < right - left + 1) {
                count[s.charAt(left) - 'A']--;
                left++;
                majorCount = count[argMax(count)];
            }

            max = Math.max(max, right - left + 1);
            right++;
        }

        return max;
    }

    public int argMax(int[] arr) {
        int maxIndex = 0;    //获取到的最大值的角标
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] > arr[maxIndex]) {
                maxIndex = i;
            }
        }
        return maxIndex;
    }
}
