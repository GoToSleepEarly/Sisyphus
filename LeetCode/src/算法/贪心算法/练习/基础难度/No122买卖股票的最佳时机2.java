package 算法.贪心算法.练习.基础难度;

public class No122买卖股票的最佳时机2 {

    class Solution {
        /**
         * 1、利润最大，即每一笔最低点买，最高点卖
         * 2、一次只能有一笔交易，那么逢低买，逢高卖
         * 3、也就是只要跌了，那么之前一天就卖，这一天再重新入手，那么一定赚。
         *
         * @param prices prices
         * @return res
         */
        public int maxProfit(int[] prices) {
            // 异常情况
            if (prices.length == 0) {
                return 0;
            }
            int buy = prices[0];
            int res = 0;
            for (int i = 1; i < prices.length; i++) {
                // 跌了
                if (prices[i] < prices[i - 1]) {
                    res += prices[i - 1] - buy;
                    buy = prices[i];
                }
            }
            // 最后要卖
            res += prices[prices.length - 1] - buy;
            return res;
        }
    }
}
