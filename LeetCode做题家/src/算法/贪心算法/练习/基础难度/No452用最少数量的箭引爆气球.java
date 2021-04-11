package 算法.贪心算法.练习.基础难度;

import java.util.Arrays;

public class No452用最少数量的箭引爆气球 {

    class Solution {
        /**
         * 此题可以按照左端排序，也可以按照右端排序
         * 大前提是每一个区间都要覆盖，那么贪心的核心在于如何选择初始点
         * 因为每一个区间都要选择，故优先考虑按左侧排序
         *
         * @param points points
         * @return int
         */
        public int findMinArrowShots(int[][] points) {
            // 习惯性先考虑异常情况
            if (points.length == 0) {
                return 0;
            }
            // 这题有坑，按照常规的减法会溢出
            Arrays.sort(points, (p1, p2) -> p1[0] != p2[0] ? Integer.compare(p1[0], p2[0]) : Integer.compare(p1[1], p2[1]));

            int[] cur = points[0];
            int res = 1;
            for (int i = 1; i < points.length; i++) {
                int[] tmp = points[i];
                // 没有交集
                if (tmp[0] > cur[1]) {
                    cur = tmp;
                    res++;
                } else {
                    cur[0] = Math.min(cur[0], tmp[0]);
                    cur[1] = Math.min(cur[1], tmp[1]);
                }
            }
            return res;
        }
    }
}
