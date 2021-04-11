package 周赛;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class 同积元组 {

    public static void main(String[] args) {
        同积元组 s = new 同积元组();
        s.tupleSameProduct(new int[]{1, 2, 4, 5, 10});
    }

    public int tupleSameProduct(int[] nums) {
        if (nums.length < 4) {
            return 0;
        }
        Arrays.sort(nums);
        int n = nums.length;
        int res = 0;

        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                int sum = nums[i] * nums[j];
                map.put(sum, map.getOrDefault(sum, 0) + 1);
            }
        }
        for (int count : map.values()) {
            res += count * (count - 1) * 4;
        }
        return res;
    }
}
