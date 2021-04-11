package 算法.贪心算法.练习.基础难度;

import java.util.LinkedList;
import java.util.List;

public class No763划分字母区间 {
    public static void main(String[] args) {
        Solution s = new Solution();
        s.partitionLabels("ababcbacadefegdehijhklij");
    }

    static class Solution {
        /**
         * 提示比较明确，即每个字母只能出现在一个片段中
         * 那么我们每次往右扩散即可。
         *
         * @param S S
         * @return res
         */
        public List<Integer> partitionLabels(String S) {
            // 先处理异常情况
            if (S.length() == 0) {
                return new LinkedList<>();
            }
            // 最右下标表
            int[] lastIndex = new int[26];
            char[] charArray = S.toCharArray();
            for (int i = 0; i < charArray.length; i++) {
                // 出现就更新，肯定比已有值大
                int index = charArray[i] - 'a';
                lastIndex[index] = i;
            }

            // 开始贪心
            List<Integer> res = new LinkedList<>();
            int left = 0;
            int right = 0;

            // left和right都在变的情况，一般使用while更合适
            while (right < charArray.length) {
                int curLeft = left;
                while (curLeft <= right) {
                    right = Math.max(lastIndex[charArray[curLeft] - 'a'], right);
                    curLeft++;
                }
                res.add(right - left + 1);
                // 注意此处要更新
                left = right + 1;
                right = left;
            }

            return res;
        }
    }
}
