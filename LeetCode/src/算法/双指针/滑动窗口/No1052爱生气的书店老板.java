package 算法.双指针.滑动窗口;

public class No1052爱生气的书店老板 {
    
    public int maxSatisfied(int[] customers, int[] grumpy, int X) {
        int n = customers.length;
        int right = 0;
        int max = 0;
        int markIndex = 0;
        int cur = 0;
        while (right < n) {
            if (right < X) {
                cur += customers[right] * grumpy[right];
                max = cur;
                right++;
                continue;
            }
            cur += customers[right] * grumpy[right];
            cur -= customers[right - X] * grumpy[right - X];
            right++;
            if (cur > max) {
                markIndex = right - X;
                max = cur;
            }
        }
        int ans = 0;
        for (int i = 0; i < n; i++) {
            ans += customers[i] * (1 - grumpy[i]);
        }
        for (int i = markIndex; i < markIndex + X; i++) {
            if (grumpy[i] == 1) {
                ans += customers[i];
            }
        }
        return ans;
    }
}
