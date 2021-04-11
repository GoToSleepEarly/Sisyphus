package 算法.双指针.滑动窗口;

import java.util.Arrays;

public class No1423可获得的最大点数 {

    public int maxScore(int[] cardPoints, int k) {
        int total = Arrays.stream(cardPoints).sum();
        int n = cardPoints.length;
        // 连续n-k个数最小
        int left = 0;
        int right = 0;
        int min = Integer.MAX_VALUE;
        int sum = 0;
        while (right < n) {
            sum += cardPoints[right];
            right++;
            if (right - left > n - k) {
                sum -= cardPoints[left];
                left++;
            }
            if (right - left >= n - k) {
                min = Math.min(min, sum);
            }

        }
        return total - min;
    }
}
