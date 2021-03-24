package 算法.贪心算法.区间问题.差分;

public class No1109航班预订统计 {
    public static void main(String[] args) {
        No1109航班预订统计 s = new No1109航班预订统计();
        s.corpFlightBookings(new int[][]{{1, 2, 10}, {2, 3, 20}, {2, 5, 25}}, 5);
    }

    public int[] corpFlightBookings(int[][] bookings, int n) {
        int[] changes = new int[n + 1];
        for (int[] booking : bookings) {
            int from = booking[0];
            int to = booking[1]+1;
            int num = booking[2];
            changes[from] += num;
            if (to < n + 1) {
                changes[to] -= num;
            }
        }
        int cur = 0;
        int[] res = new int[n];
        for (int i = 0; i < res.length; i++) {
            cur = cur + changes[i + 1];
            res[i] = cur;
        }
        return res;
    }
}
