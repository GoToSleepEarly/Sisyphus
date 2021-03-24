package 算法.贪心算法.区间问题.差分;

public class No1094拼车 {

    public boolean carPooling(int[][] trips, int capacity) {
        // 本质是线段重叠和的问题
        // 差分数组或TreeMap都可
        int[] changes = new int[1001];
        for (int[] trip : trips) {
            int num = trip[0];
            int in = trip[1];
            int out = trip[2];
            changes[in] += num;
            changes[out] -= num;
        }
        int cur = 0;
        for (int change : changes) {
            cur += change;
            if (cur > capacity) {
                return false;
            }
        }
        return true;
    }
}
