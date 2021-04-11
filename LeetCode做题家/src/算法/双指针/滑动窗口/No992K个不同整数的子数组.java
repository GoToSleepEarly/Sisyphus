package 算法.双指针.滑动窗口;

import java.util.HashMap;
import java.util.Map;

public class No992K个不同整数的子数组 {

    public int subarraysWithKDistinct(int[] A, int K) {
        // f(k)-f(k-1)，太巧妙的解题思路
        return f(A, K) - f(A, K - 1);
    }

    private int f(int[] A, int K) {
        int left = 0;
        // right表示要取的数
        int right = 0;
        Map<Integer, Integer> map = new HashMap<>();
        int count = 0;
        while (right < A.length) {
            // 先直接添加
            if (!map.containsKey(A[right])) {
                map.put(A[right], 1);
            } else {
                map.put(A[right], map.get(A[right]) + 1);
            }
            right++;

            // 最多放K个，如果不满足要收缩
            while (map.size() > K) {
                map.put(A[left], map.get(A[left]) - 1);
                if (map.get(A[left]) == 0) {
                    map.remove(A[left]);
                }
                left++;
            }

            // 因为是最多放，那么只要满足条件，区间长度+right字符都能满足
            count += right - left;

        }
        return count;
    }
}
