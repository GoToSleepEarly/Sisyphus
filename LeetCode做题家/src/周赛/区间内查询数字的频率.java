package 周赛;

import java.util.*;

public class 区间内查询数字的频率 {

    public long kMirror(int k, int n) {

        long[][] map = new long[10][31];
        map[2] = new long[]
                {1, 3, 5, 7, 9, 33, 99, 313, 585, 717, 7447, 9009, 15351, 32223, 39993, 53235, 53835, 73737, 585585, 1758571, 1934391, 1979791, 3129213, 5071705, 5259525, 5841485, 13500531, 719848917, 910373019, 939474939};
        map[3] = new long[]{1, 2, 4, 8, 121, 151, 212, 242, 484, 656, 757, 29092, 48884, 74647, 75457, 76267, 92929, 93739, 848848, 1521251, 2985892, 4022204, 4219124, 4251524, 4287824, 5737375, 7875787, 7949497, 27711772, 83155138};
        map[4] = new long[]{};
        map[5] = new long[]{};
        map[6] = new long[]{};
        map[7] = new long[]{};
        map[8] = new long[]{};
        map[9] = new long[]{};

        long[] cur = map[k];
        long res = 0;
        for (int i = 0; i <= n; i++) {
            res += cur[i];
        }
        return res;
    }

    public static void main(String[] args) {
        List<Long>[] res = new LinkedList[10];

        for (int i = 2; i <= 9; i++) {
            List<Long> curInteger = new LinkedList<>();
            // 前30个
            long cur = 1;
            while (curInteger.size() < 30) {
                // 10 进制
                String b = Long.toString(cur, 10);
                int left = 0;
                int right = b.length() - 1;
                boolean isMi = true;
                while (left < right) {
                    if (b.charAt(left) == b.charAt(right)) {
                        left++;
                        right--;
                    } else {
                        isMi = false;
                        break;
                    }
                }
                if (!isMi) {
                    cur++;
                    continue;
                }
                String x = Long.toString(cur, i);
                left = 0;
                right = x.length() - 1;
                isMi = true;
                while (left < right) {
                    if (x.charAt(left) == x.charAt(right)) {
                        left++;
                        right--;
                    } else {
                        isMi = false;
                        break;
                    }
                }
                if (isMi) {
                    curInteger.add(cur);
                }
                cur++;
            }
            res[i] = curInteger;
            System.out.println(curInteger);
        }
    }
}


class RangeFreqQuery {

    Map<Integer, List<Integer>> map;

    public RangeFreqQuery(int[] arr) {
        int n = arr.length;
        map = new HashMap<>();
        for (int i = 0; i < arr.length; i++) {
            // 一定是有序的
            map.computeIfAbsent(arr[i], k -> new ArrayList<>()).add(i);
        }
    }

    public int query(int left, int right, int value) {
        List<Integer> list = map.get(value);
        if (list == null) {
            return 0;
        }
        int leftIndex = doSearch1(list, left);
        int rightIndex = doSearch2(list, right);
        return rightIndex - leftIndex;
    }

    private int doSearch2(List<Integer> list, int target) {
        int left = -1;
        int right = list.size() - 1;
        while (left < right) {
            int mid = (left + 1 + right) / 2;
            if (list.get(mid) == target) {
                return mid;
            } else if (list.get(mid) > target) {
                right = mid - 1;
            } else {
                left = mid;
            }
        }
        return left;
    }

    private int doSearch1(List<Integer> list, int target) {
        int left = -1;
        int right = list.size() - 1;
        while (left < right) {
            int mid = (left + 1 + right) / 2;
            if (list.get(mid) == target) {
                right = mid - 1;
            } else if (list.get(mid) > target) {
                right = mid - 1;
            } else {
                left = mid;
            }
        }
        return left;
    }
}