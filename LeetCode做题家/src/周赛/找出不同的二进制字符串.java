package 周赛;

import java.util.HashSet;
import java.util.Set;

public class 找出不同的二进制字符串 {

    public String findDifferentBinaryString(String[] nums) {
        Set<Integer> set = new HashSet<>();
        for (String num : nums) {
            set.add(Integer.parseInt(num, 2));
        }
        int n = nums.length;
        for (int i = 0; i <= (1 << n) - 1; i++) {
            if (!set.contains(i)) {
                return fillStr(Integer.toBinaryString(i), n);
            }
        }
        return "";
    }

    private String fillStr(String str, int n) {
        int len = str.length();
        int left = n - len;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < left; i++) {
            sb.append('0');
        }
        return sb.toString() + str;
    }
}
