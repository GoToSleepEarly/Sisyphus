package 算法.双指针.前缀和;

import java.util.HashMap;
import java.util.Map;

public class No1248统计优美子数组 {

    public int numberOfSubarrays(int[] nums, int k) {
        // 坐标i的sum不包括i
        int[] preSum = new int[nums.length + 1];
        for (int i = 1; i <= nums.length; i++) {
            if (nums[i-1] % 2 == 1) {
                preSum[i] = preSum[i - 1] + 1;
            } else {
                preSum[i] = preSum[i - 1];
            }
            System.out.println(preSum[i]);
        }
        int res = 0;
        Map<Integer, Integer> num2CountMap = new HashMap<>();
        for (int curPreSum : preSum) {
            if (num2CountMap.containsKey(curPreSum - k)) {
                res += num2CountMap.get(curPreSum - k);
            }
            num2CountMap.merge(curPreSum, 1, (o, n) -> o + 1);
        }
        return res;
    }
}
