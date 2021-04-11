package 算法.贪心算法.练习.基础难度;

public class No665非递减数列 {

    class Solution {
        /**
         * 面向测试用例编程
         * 先考虑左侧一直是递增的，到i时不满足了，需要如何处理
         * 主要比较i-2和i的大小，要么改变i，要么改变i-1
         *
         * @param nums nums
         * @return res
         */
        public boolean checkPossibility(int[] nums) {
            if (nums == null || nums.length == 0 || nums.length == 1) {
                return true;
            }
            int count = 0;
            for (int i = 1; i < nums.length; i++) {
                if (nums[i] < nums[i - 1]) {
                    count++;

                    if (i - 2 >= 0 && nums[i - 2] > nums[i]) {
                        nums[i] = nums[i - 1];
                    } else {
                        nums[i - 1] = nums[i];
                    }
                }
            }
            return count <= 1;

        }
    }
}


