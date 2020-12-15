package 算法.贪心算法.分配问题;

import java.util.Arrays;

public class No455分发饼干 {

    class Solution {

        /**
         * 总结： 如果局部最优能推出全局最优，那么贪心就可以尝试
         *
         * @param g g
         * @param s s
         * @return res
         */
        public int findContentChildren(int[] g, int[] s) {
            // 排序
            Arrays.sort(g);
            Arrays.sort(s);

            // 下标
            int gIndex = 0;
            int sIndex = 0;

            // 结果
            int res = 0;
            // 固定s,找g，即每个饼干分给能满足的小孩就行
            while (gIndex < g.length && sIndex < s.length) {
                // 能满足，则分配
                if (g[gIndex] <= s[sIndex]) {
                    gIndex++;
                    res++;
                }
                sIndex++;
            }
            // 实际上，gIndex就是res，但是区分开来更符合思维
            return res;
        }
    }
}
