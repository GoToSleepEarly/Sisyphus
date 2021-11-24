package 周赛;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class 完成任务的最少工作时间段 {
    public static void main(String[] args) {
        new 完成任务的最少工作时间段().minSessions(new int[]{1, 2, 3}, 3);
    }

    int[] tasks;
    int sessionTime;
    int res;
    Set<String> set;

    public int minSessions(int[] tasks, int sessionTime) {
        this.tasks = tasks;
        this.sessionTime = sessionTime;
        this.res = Integer.MAX_VALUE;
        set = new HashSet<>();
        int[] pkg = new int[tasks.length];
        dfs(0, pkg);
        return res;
    }

    private void dfs(int index, int[] pkg) {
        String str = Arrays.toString(pkg);
        if (set.contains(str)) {
            return;
        }
        if (index == pkg.length) {
            int cnt = 0;
            for (int i : pkg) {
                if (i != 0) {
                    cnt++;
                }
            }
            res = Math.min(res, cnt);
            return;
        }
        int task = tasks[index];
        for (int i = 0; i < pkg.length; i++) {
            if (pkg[i] + task <= sessionTime) {
                pkg[i] += task;
                dfs(index + 1, pkg);
                pkg[i] -= task;
            }
            if (pkg[i] == 0) {
                break;
            }
        }
        set.add(str);
    }
}
