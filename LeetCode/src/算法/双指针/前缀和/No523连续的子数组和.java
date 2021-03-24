package 算法.双指针.前缀和;

import java.util.HashMap;
import java.util.Map;

public class No523连续的子数组和 {
    public static void main(String[] args) {
        new No523连续的子数组和().checkSubarraySum(new int[]{0, 0}, -1);
    }

    public boolean checkSubarraySum(int[] nums, int k) {
        if (k == 0) {
            for (int i = 1; i < nums.length; i++) {
                if (nums[i - 1] == nums[i] && nums[i] == 0) {
                    return true;
                }
            }
            return false;
        }

        // 坐标i的sum不包括i
        int[] preSum = new int[nums.length + 1];
        for (int i = 1; i <= nums.length; i++) {
            preSum[i] = preSum[i - 1] + nums[i - 1];
        }
        // preSum[j]%k = preSum[i]%k j-i>=1
        // 下标Map
        Map<Integer, Integer> mod2IndexMap = new HashMap<>();
        for (int i = 0; i < preSum.length; i++) {
            int curPreSum = preSum[i];
            int mod = curPreSum % k;
            if (mod2IndexMap.containsKey(mod)) {
                if (i - mod2IndexMap.get(mod) > 1) {
                    return true;
                }
            }
            mod2IndexMap.merge(mod, i, (o, n) -> o);
        }
        return false;
    }
}
