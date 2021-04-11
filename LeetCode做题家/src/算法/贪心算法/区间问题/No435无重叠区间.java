package 算法.贪心算法.区间问题;

import java.util.Arrays;
import java.util.Comparator;

public class No435无重叠区间 {
    class Solution {
        /**
         * 区间一般按照left,right,gap三种方式贪心
         * 这题采用right,基本思路为：
         * 当right尽量小，则剩下的空间越大，那么重叠的可能越小
         * 即: 对于A,B区间,B[1]>A[1],
         * if 重叠 then 抛弃B(如果选择B只会让剩下的区间更小) else 不冲突，纳入B
         *
         * @param intervals intervals
         * @return res
         */
        public int eraseOverlapIntervals(int[][] intervals) {
            // 异常情况
            if (intervals.length == 0) {
                return 0;
            }
            Arrays.sort(intervals, Comparator.comparingInt(left -> left[1]));
            int[] cur = intervals[0];
            int res = 0;
            for (int i = 1; i < intervals.length; i++) {
                int[] tmp = intervals[i];
                // 如果不重叠，直接更新
                if (tmp[0] >= cur[1]) {
                    cur = tmp;
                }
                // 否则结果+1
                else {
                    res++;
                }
            }
            return res;
        }
    }
}
