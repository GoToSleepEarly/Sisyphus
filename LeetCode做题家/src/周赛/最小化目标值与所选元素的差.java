package 周赛;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class 最小化目标值与所选元素的差 {

    public static void main(String[] args) {
        new 最小化目标值与所选元素的差().minimizeTheDifference(new int[][]{{4, 1, 2, 2, 2, 1},
                        {1, 1, 6, 5, 3, 3},
                        {3, 9, 1, 7, 10, 1},
                        {4, 3, 1, 1, 6, 10},
                        {2, 5, 6, 8, 1, 5},
                        {8, 9, 10, 1, 5, 7}},
                23);
    }


    int m;
    int n;
    int[][] mat;
    int res = Integer.MAX_VALUE;
    int target;
    Set<String> set = new HashSet<>();

    public int minimizeTheDifference(int[][] mat, int target) {
        this.m = mat.length;
        this.n = mat[0].length;
        this.mat = mat;
        this.target = target;
        for (int i = 0; i < m; i++) {
            Arrays.sort(mat[i]);
        }
        for (int j = 0; j < n; j++) {
            dfs(0, 0);
        }
        return res;
    }

    private void dfs(int i, int sum) {
        if (set.contains(i + " " + sum)) {
            return;
        }
        if (i == m) {
            res = Math.min(res, Math.abs(sum - target));
            return;
        }
        if (sum >= target) {
            dfs(i + 1, sum + mat[i][0]);
        } else {
            for (int col = 0; col < n; col++) {
                dfs(i + 1, sum + mat[i][col]);
            }
        }
        set.add(i + " " + sum);
    }


}
