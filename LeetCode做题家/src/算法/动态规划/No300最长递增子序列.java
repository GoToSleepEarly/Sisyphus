package 算法.动态规划;

import java.util.Arrays;

public class No300最长递增子序列 {
    public static void main(String[] args) {
        new No300最长递增子序列().lengthOfLIS(new int[]{4, 10, 4, 3, 8, 9});
    }

    public int lengthOfLIS(int[] nums) {
        int n = nums.length;
        int[] min = new int[n];
        Arrays.fill(min, Integer.MIN_VALUE);

        int res = -1;
        for (int num : nums) {
            // 比最大值还大，那么不需要替换
            if (res == -1) {
                min[++res] = num;
            } else if (min[res] < num) {
                min[++res] = num;
            }
            // 寻找第一个大于等于他的数
            else {
                int left = 0;
                int right = res;
                while (left < right) {
                    int mid = (left + right) / 2;
                    if (min[mid] > num) {
                        right = mid;
                    } else if (min[mid] < num) {
                        left = mid + 1;
                    } else {
                        right = mid;
                    }
                }
                min[left] = num;
            }
        }
        return res + 1;
    }
}
