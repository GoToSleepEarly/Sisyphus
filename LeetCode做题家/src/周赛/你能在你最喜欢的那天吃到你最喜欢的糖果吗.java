package 周赛;

public class 你能在你最喜欢的那天吃到你最喜欢的糖果吗 {
    public static void main(String[] args) {
        你能在你最喜欢的那天吃到你最喜欢的糖果吗 s = new 你能在你最喜欢的那天吃到你最喜欢的糖果吗();
        s.canEat(new int[]{7, 4, 5, 3, 8}, new int[][]{{2, 13, 1000000000}});
    }

    public boolean[] canEat(int[] candiesCount, int[][] queries) {
        int n = candiesCount.length;
        long[] minCnts = new long[n + 1];
        for (int i = 0; i < n; i++) {
            minCnts[i + 1] = minCnts[i] + candiesCount[i];
        }

        boolean[] ans = new boolean[queries.length];
        for (int i = 0; i < queries.length; i++) {
            int type = queries[i][0];
            long day = queries[i][1];
            long cap = queries[i][2];
            long min = day + 1;
            long max = min * cap;
            if (max <= minCnts[type] || min > minCnts[type + 1]) {
                continue;
            }
            ans[i] = true;
        }
        return ans;


    }
}
