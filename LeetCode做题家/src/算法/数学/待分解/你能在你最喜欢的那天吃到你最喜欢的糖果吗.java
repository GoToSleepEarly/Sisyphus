package 算法.数学.待分解;

public class 你能在你最喜欢的那天吃到你最喜欢的糖果吗 {


    /**
     * 数学题+long溢出典型题目
     * 1、当天能吃的区间为[fD+1,(fD+1)*dc]
     * 2、fT代表的糖果的区间为[preSum[fT]+1,preSum[fT]];
     * 3、两个区间有交集，才代表能吃到
     * 4、int会溢出，统一用long
     */
    public boolean[] canEat(int[] candiesCount, int[][] queries) {
        int n = candiesCount.length;
        // 左侧前缀和
        long[] preSum = new long[n + 1];
        preSum[0] = 0;
        for (int i = 1; i < preSum.length; i++) {
            preSum[i] = preSum[i - 1] + candiesCount[i - 1];
        }
        boolean[] res = new boolean[queries.length];
        for (int i = 0; i < queries.length; i++) {
            int[] query = queries[i];
            int fT = query[0];
            int fD = query[1];
            int dC = query[2];
            long l1 = fD + 1;
            long r1 = (fD + 1L) * dC;
            long l2 = preSum[fT] + 1;
            long r2 = preSum[fT + 1];
            res[i] = !(r1 < l2 || l1 > r2);
        }
        return res;
    }
}
