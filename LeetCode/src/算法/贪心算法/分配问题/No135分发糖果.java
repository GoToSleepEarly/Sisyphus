package 算法.贪心算法.分配问题;

import java.util.Arrays;

public class No135分发糖果 {

    /**
     * 对于每个i,要满足:
     * 1、if ratings[i] > ratings[i+1] then candies[i] =candies[i]+1
     * 2、if ratings[i] > ratings[i-1] then candies[i] =candies[i-1]+1
     * 易知，如果ratings[i]同时大于左右侧，则取两侧最大值+1
     * 故，此题贪心分两次遍历，一次从左往右，一次从右往左，取最大值即可。
     *
     * @param ratings ratings
     * @return res
     */
    public int candy(int[] ratings) {
        int n = ratings.length;
        // 从左往右
        int[] left = new int[n];
        // 从右往左
        int[] right = new int[n];
        // 默认填充1
        Arrays.fill(left, 1);
        Arrays.fill(right, 1);
        //两次遍历，即条件1和条件2
        for (int i = 1; i < n; i++) {
            if (ratings[i] > ratings[i - 1]) {
                left[i] = left[i - 1] + 1;
            }
        }
        for (int i = n - 2; i >= 0; i--) {
            if (ratings[i] > ratings[i + 1]) {
                right[i] = right[i + 1] + 1;
            }
        }
        // 最后一次遍历，取结果max
        int res = 0;
        for (int i = 0; i < n; i++) {
            res += Math.max(left[i], right[i]);
        }
        return res;

    }
}
