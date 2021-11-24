package 算法.数学.待分解;

import java.util.HashMap;
import java.util.Map;

public class 同积元组 {

    /**
     * 找规律的数学题，先把公式列出来，尝试暴力解，然后再优化
     * 1、暴力枚举
     * 2、数学优化，比如等式转换，提前存储结果
     * 3、逻辑优化，比如排序双指针优化暴力
     */
    public int tupleSameProduct(int[] nums) {
        // a*b=c*d，直接枚举
        int n = nums.length;
        Map<Integer, Integer> count = new HashMap<>();
        for (int i = 0; i < n; i++) {
            int x = nums[i];
            for (int j = i + 1; j < n; j++) {
                int y = nums[j];
                int key = x * y;
                count.put(key, count.getOrDefault(key, 0) + 1);
            }
        }
        int res = 0;
        for (Map.Entry<Integer, Integer> e : count.entrySet()) {
            int value = e.getValue();
            res += (value * (value - 1)) / 2;
        }
        return res * 8;
    }
}
