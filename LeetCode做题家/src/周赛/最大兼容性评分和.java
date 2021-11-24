package 周赛;

import java.util.Arrays;

public class 最大兼容性评分和 {
    public static void main(String[] args) {
        new 最大兼容性评分和().maxCompatibilitySum(
                new int[][]{{1, 1, 0}, {1, 0, 1}, {0, 0, 1}},
                new int[][]{{1, 0, 0}, {0, 0, 1}, {1, 1, 0}}
        );
    }

    int max = 0;
    boolean[] vst;

    public int maxCompatibilitySum(int[][] students, int[][] mentors) {
        vst = new boolean[mentors.length];
        int[] mIndex = new int[mentors.length];
        // 暴力尝试一下
        dfs(mIndex, 0, vst, students, mentors);
        return max;
    }

    private void dfs(int[] mIndex, int index, boolean[] vst, int[][] students, int[][] mentors) {
        if (index == mIndex.length) {
            int curRes = cal(mIndex, students, mentors);
            max = Math.max(max, curRes);
            System.out.println(Arrays.toString(mIndex) + "  "+ max);
            return;
        }
        for (int i = 0; i < vst.length; i++) {
            boolean visited = vst[i];
            if (!visited) {
                mIndex[index] = i;
                vst[i] = true;
                dfs(mIndex, index+1, vst, students, mentors);
                vst[i] = false;
            }
        }
    }

    private int cal(int[] mIndex, int[][] students, int[][] mentors) {
        int res = 0;
        for (int i = 0; i < students.length; i++) {
            int[] mentor = mentors[mIndex[i]];
            int[] student = students[i];
            for (int j = 0; j < mentor.length; j++) {
                if (mentor[j] == student[j]) {
                    res++;
                }
            }
        }
        return res;
    }
}
