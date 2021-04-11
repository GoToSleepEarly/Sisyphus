package 周赛;

import java.util.Arrays;

public class 可以形成最大正方形的矩形数目 {

    public int countGoodRectangles(int[][] rectangles) {
        int n = rectangles.length;
        int[] maxLen = new int[n];
        for (int i = 0; i < n; i++) {
            int[] rec = rectangles[i];
            maxLen[i] = Math.min(rec[0], rec[1]);
        }
        Arrays.sort(maxLen);
        int max = maxLen[n - 1];
        int count = 0;
        for (int i = n - 1; i >= 0; i--) {
            if (maxLen[i] != max) {
                break;
            }
            count++;
        }
        return count;
    }
}
