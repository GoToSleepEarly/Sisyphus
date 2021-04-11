package 算法.贪心算法.练习.基础难度;

public class No605种花问题 {

    class Solution {
        /**
         * 按照题目描述从左至右判断即可，易得局部最优就是整体最优
         *
         * @param flowerbed flowerbed
         * @param n         n
         * @return res
         */
        public boolean canPlaceFlowers(int[] flowerbed, int n) {
            int valid = 0;
            for (int i = 0; i < flowerbed.length; i++) {
                if (flowerbed[i] == 1) continue;
                if (flowerbed.length > i + 1 && flowerbed[i + 1] == 1) continue;
                if (i == 0) {
                    valid++;
                    flowerbed[i] = 1;
                } else {
                    if (flowerbed[i - 1] == 0) {
                        valid++;
                        flowerbed[i] = 1;
                    }
                }
                if (valid == n) {
                    return true;
                }
            }
            return false;
        }
    }
}