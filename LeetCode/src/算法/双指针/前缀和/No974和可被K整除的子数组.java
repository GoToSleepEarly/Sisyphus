package 算法.双指针.前缀和;

import java.util.HashMap;
import java.util.Map;

public class No974和可被K整除的子数组 {
    public static void main(String[] args) {
        System.out.println(-10%9);
    }
    public int subarraysDivByK(int[] A, int K) {
        // 坐标i的sum不包括i
        int[] preSum = new int[A.length + 1];
        for (int i = 1; i <= A.length; i++) {
            preSum[i] = preSum[i - 1] + A[i - 1];
        }
        int res = 0;
        // preSum[j]%k = preSum[i]%k j-i>=1
        // 下标Map
        Map<Integer, Integer> mod2CountMap = new HashMap<>();
        for (int i = 0; i < preSum.length; i++) {
            int curPreSum = preSum[i];
            int mod = (curPreSum % K + K) % K;
            if (mod2CountMap.containsKey(mod)) {
                res += mod2CountMap.get(mod);
            }
            mod2CountMap.merge(mod, 1, (o, n) -> o + 1);
        }
        return res;
    }
}
