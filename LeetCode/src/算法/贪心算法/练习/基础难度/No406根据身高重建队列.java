package 算法.贪心算法.练习.基础难度;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class No406根据身高重建队列 {
    public static void main(String[] args) {
        Solution s = new Solution();
        int[][] people = new int[][]{{7, 0}, {4, 4}, {7, 1}, {5, 0}, {6, 1}, {5, 2}};
        s.reconstructQueue(people);
    }

    static class Solution {

        /**
         * 没看答案前的大笨蛋做法
         * 1、根据题意，按照ki进行排序
         * 2、从前往后遍历，直到>=hi的数超过ki
         * <p>
         * 排序nlogn+插入n^2
         * 肯定需要优化
         *
         * @param people people
         * @return res
         */
        public int[][] reconstructQueue(int[][] people) {
            // 排序可以优化
            Arrays.sort(people, (p1, p2) -> {
                if (p1[1] > p2[1]) {
                    return 1;
                } else if (p1[1] == p2[1]) {
                    return p2[0] - p1[0];
                } else {
                    return -1;
                }
            });

            List<int[]> res = new ArrayList<>();
            for (int[] p : people) {
                int larger = 0;
                int index = 0;
                // 即get又插入速度很慢
                for (; index < res.size(); index++) {
                    int[] cur = res.get(index);
                    if (cur[0] >= p[0]) {
                        larger++;
                    }
                    if (larger > p[1]) {
                        break;
                    }
                }
                res.add(index, p);
            }
            return res.toArray(new int[res.size()][2]);
        }

        /**
         * 按照高度降序
         * 1、高度排好后，后续矮的不管怎么插，都不影响已插入的顺序
         * 2、这样变成更矮的人找插入的地点。(小笨蛋做法如上，遍历找)
         * 3、这道题巧妙地点在于，当高个排好后，小个的下标就确认了。
         *
         * @param people people
         * @return res
         */
        public int[][] reconstructQueue2(int[][] people) {
            // 根据身高
            Arrays.sort(people, (o1, o2) -> o1[0] == o2[0] ? o1[1] - o2[1] : o2[0] - o1[0]);

            // 比p[i]高的人有ki个，那么直接插入第ki个位置即可
            LinkedList<int[]> list = new LinkedList<>();
            for (int[] i : people) {
                list.add(i[1], i);
            }

            return list.toArray(new int[list.size()][2]);

        }
    }
}

