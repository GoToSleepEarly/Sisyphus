package 周赛;

import java.util.*;

public class 游戏中弱角色的数量 {
    public static void main(String[] args) {
        int[][] x = new int[][]
                {{86, 64}, {83, 93}, {62, 65}, {48, 87}, {70, 41}, {9, 14}, {38, 85}, {4, 43}, {22, 6}, {9, 28}, {97, 71}, {10, 86}, {52, 94}, {94, 11}, {83, 92}, {91, 87}, {46, 64}, {90, 99}, {30, 31}, {46, 13}, {38, 87}, {50, 7}, {43, 52}, {14, 72}, {94, 73}, {44, 1}, {93, 10}, {70, 74}, {100, 87}, {18, 6}, {35, 32}, {43, 56}, {23, 91}, {84, 72}, {34, 18}, {33, 13}, {48, 84}, {44, 87}, {49, 63}, {54, 72}, {33, 31}, {59, 16}, {15, 20}, {84, 77}, {6, 60}, {77, 27}, {94, 81}, {84, 34}, {25, 100}, {57, 68}, {60, 75}, {10, 27}, {99, 48}, {20, 23}, {87, 86}, {97, 58}, {51, 17}, {83, 75}, {19, 43}, {55, 12}, {24, 59}, {83, 83}, {16, 36}, {68, 90}, {73, 69}, {66, 46}, {96, 24}, {4, 62}, {56, 15}, {14, 97}, {96, 63}, {94, 87}, {34, 38}, {58, 70}, {16, 12}, {39, 83}, {62, 40}, {99, 9}, {66, 46}, {41, 24}, {35, 35}, {95, 51}, {5, 33}, {68, 15}, {8, 92}, {35, 76}, {58, 75}, {5, 46}, {13, 88}, {92, 24}, {24, 80}};

        new 游戏中弱角色的数量().test(x);
        new 游戏中弱角色的数量().numberOfWeakCharacters(x);
    }

    public int numberOfWeakCharacters(int[][] properties) {
        Set<Integer> del = new HashSet<>();
        Arrays.sort(properties, Comparator.comparingInt(k -> k[0]));
        int cnt = 0;
        TreeMap<Integer, Integer> treeMap = new TreeMap<>();
        int n = properties.length;
        for (int[] property : properties) {
            treeMap.put(property[1],
                    treeMap.getOrDefault(property[1], 0) + 1);
        }
        for (int i = 0; i < n; i++) {
            int curD = properties[i][1];
            // 把x值相同的删除
            if (del.add(properties[i][0])) {
                int next = i;
                while (next < n && properties[next][0] == properties[i][0]) {
                    treeMap.compute(properties[next][1], (k, v) -> {
                        if (v == null || v == 1) {
                            return null;
                        }
                        return v - 1;
                    });
                    next++;
                }
            }
            if (null != treeMap.higherKey(curD)) {
                cnt++;
            }
        }
        return cnt;
    }

    public int test(int[][] properties) {
        StringJoiner x = new StringJoiner(",", "", "");
        Arrays.sort(properties, Comparator.comparingInt(k -> k[0]));
        int cnt = 0;
        TreeMap<Integer, Integer> treeMap = new TreeMap<>();
        int n = properties.length;
        for (int[] property : properties) {
            treeMap.put(property[1],
                    treeMap.getOrDefault(property[1], 0) + 1);
        }
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (properties[j][0] == properties[i][0]) {
                    continue;
                }
                if (properties[j][1] - properties[i][1] > 0) {
                    cnt++;
                    x.add(Arrays.toString(properties[i]));
                    break;

                }
            }
        }
        System.out.println(x.toString());
        return cnt;
    }
}
