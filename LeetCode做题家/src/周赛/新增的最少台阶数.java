package 周赛;

public class 新增的最少台阶数 {

    public static void main(String[] args) {
        new 新增的最少台阶数().addRungs(new int[]{3,4,6,7}, 2);
    }

    public int addRungs(int[] rungs, int dist) {
        int cur = 0;
        int cnt = 0;
        for (int rung : rungs) {
            if (cur >= rung) {
                continue;
            }
            if (cur + dist >= rung) {
                cur = rung;
                continue;
            }
            int tmp = 0;
            int gap = rung - cur - dist;
            if (gap % dist == 0) {
                tmp = gap / dist;
            } else {
                tmp = gap / dist + 1;
            }
            cnt += tmp;
            cur = rung;
        }
        return cnt;
    }
}
