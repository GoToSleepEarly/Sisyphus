package 周赛;

import java.util.LinkedList;
import java.util.List;

public class 统计按位或能得到最大值的子集数目 {

    int max = 0;
    int cnt = 0;

    public int countMaxOrSubsets(int[] nums) {
        dfs(0, nums, new LinkedList<>());
        return cnt;
    }

    private void dfs(int index, int[] nums, List<Integer> res) {
        if (index == nums.length) {
            if (res.size() == 0) {
                return;
            }
            int result = getORRes(res);
            if (result > max) {
                max = result;
                cnt = 1;
            } else if (result == max) {
                cnt++;
            }
            return;
        }
        int num = nums[index];
        res.add(num);
        dfs(index + 1, nums, res);
        res.remove(res.size() - 1);
        dfs(index + 1, nums, res);
    }

    private int getORRes(List<Integer> res) {
        int result = res.get(0);
        for (int i = 1; i < res.size(); i++) {
            result = result | res.get(i);
        }
        return result;
    }
}
