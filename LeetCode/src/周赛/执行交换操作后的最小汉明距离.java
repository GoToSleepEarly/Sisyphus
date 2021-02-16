package 周赛;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class 执行交换操作后的最小汉明距离 {
    public static void main(String[] args) {
        执行交换操作后的最小汉明距离 s = new 执行交换操作后的最小汉明距离();
        s.minimumHammingDistance(new int[]{41, 37, 51, 100, 25, 33, 90, 49, 65, 87, 11, 18, 15, 18},
                new int[]{41, 92, 69, 75, 29, 13, 53, 21, 17, 81, 33, 19, 33, 32},
                new int[][]{{0, 11}, {5, 9}, {6, 9}, {5, 7}, {8, 13}, {4, 8}, {12, 7}, {8, 2}, {13, 5}, {0, 7}, {6, 4}, {8, 9}, {4, 12}, {6, 1}, {10, 0}, {10, 2}, {7, 3}, {11, 10}, {5, 2}, {11, 1}, {3, 0}, {8, 5}, {12, 6}, {2, 1}, {11, 2}, {4, 9}, {2, 9}, {10, 6}, {12, 10}, {4, 13}, {13, 2}, {11, 9}, {3, 6}, {0, 4}, {1, 10}, {5, 11}, {12, 1}, {10, 4}, {6, 2}, {10, 7}, {3, 13}, {4, 5}, {13, 10}, {4, 7}, {0, 12}, {9, 10}, {9, 3}, {0, 5}, {1, 9}, {5, 10}, {8, 0}, {12, 11}, {11, 4}, {7, 9}, {7, 2}, {13, 9}, {12, 3}, {8, 6}, {7, 6}, {8, 12}, {4, 3}, {7, 13}, {0, 13}, {2, 0}, {3, 8}, {8, 1}, {13, 6}, {1, 4}, {0, 9}, {2, 3}, {8, 7}, {4, 2}, {9, 12}});
    }

    public int minimumHammingDistance(int[] source, int[] target, int[][] allowedSwaps) {
        UnionFind unionFind = new UnionFind(source.length);
        for (int[] swap : allowedSwaps) {
            unionFind.union(swap[0], swap[1]);
        }
        unionFind.flat();
        int[] parent = unionFind.getParent();
        Map<Integer, List<Integer>> map = new HashMap<>();
        for (int i = 0; i < parent.length; i++) {
            map.computeIfAbsent(parent[i], a -> new LinkedList<>()).add(i);
        }
        Map<Integer, Integer> a = new HashMap<>();
        Map<Integer, Integer> b = new HashMap<>();
        int count = 0;
        for (Map.Entry<Integer, List<Integer>> entry : map.entrySet()) {
            for (int i : entry.getValue()) {
                a.put(source[i], a.getOrDefault(source[i], 0) + 1);
                b.put(target[i], b.getOrDefault(target[i], 0) + 1);
            }
            for (int i : a.keySet()) {
                count += Math.max(0, a.get(i) - b.getOrDefault(i, 0));
            }
            a.clear();
            b.clear();
        }
        return count;

    }
}

class UnionFind {
    int[] parent;

    UnionFind(int n) {
        parent = new int[n];
        for (int i = 0; i < n; i++) {
            parent[i] = i;
        }
    }

    int find(int index) {
        while (parent[index] != index) {
            index = parent[index];
        }
        return index;
    }

    void union(int indexA, int indexB) {
        int parentA = find(indexA);
        int parentB = find(indexB);
        // 挂在下面
        parent[parentB] = parentA;
    }

    void flat() {
        for (int i = 0; i < parent.length; i++) {
            parent[i] = find(i);
        }
    }

    int[] getParent() {
        return parent;
    }
}
