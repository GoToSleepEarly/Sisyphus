package 算法.滑动窗口;

public class No978最长湍流子数组 {

    public int maxTurbulenceSize(int[] arr) {
        int left = 0;
        int right = 0;
        int max = 0;
        while (right < arr.length) {
            // 窗口内就1个数，直接右移
            // 窗口内2个数，判断不相等
            // 窗口内3个数以上，按照定义判断
            if (right - left == 1) {
                if (arr[right] == arr[right - 1]) {
                    left = right;
                }
            } else if (right - left >= 2) {
                // 当>=2时，需要判断是否符合条件并left
                int k = arr[right];
                int j = arr[right - 1];
                int i = arr[right - 2];
                if (!((i < j && j > k) || (i > j && j < k))) {
                    left = right - 1;
                }

            }
            right++;
            max = Math.max(max, right - left);

        }
        return max;
    }

}
