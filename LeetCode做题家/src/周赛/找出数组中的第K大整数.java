package 周赛;

import java.math.BigInteger;
import java.util.Arrays;

public class 找出数组中的第K大整数 {

    public String kthLargestNumber(String[] nums, int k) {
        Arrays.sort(nums, (s1, s2) -> {
            BigInteger b1 = new BigInteger(s1);
            BigInteger b2 = new BigInteger(s2);
            return b1.compareTo(b2);
        });
        return nums[nums.length - k];
    }
}
