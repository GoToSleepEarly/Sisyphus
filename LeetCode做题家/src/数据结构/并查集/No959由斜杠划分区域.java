package 数据结构.并查集;

import java.util.HashSet;
import java.util.Set;

public class No959由斜杠划分区域 {

    public int regionsBySlashes(String[] grid) {
        // 又是并查集
        int m = grid.length;
        int n = grid[0].length();
        UnionFind unionFind = new UnionFind(m * n * 4);
        for (int i = 0; i < m; i++) {
            String s = grid[i];
            for (int j = 0; j < n; j++) {
                char c = s.charAt(j);
                int index = getIndex(i, j, m, n);
                if (c == '\\') {
                    unionFind.union(index + 1, index + 2);
                    unionFind.union(index, index + 3);
                } else if (c == '/') {
                    unionFind.union(index + 3, index + 2);
                    unionFind.union(index, index + 1);
                } else {
                    unionFind.union(index, index + 1);
                    unionFind.union(index, index + 2);
                    unionFind.union(index, index + 3);
                }

                // 和左边及上面联通
                if (j > 0) {
                    int left = getIndex(i, j - 1, m, n);
                    unionFind.union(left + 2, index);
                }
                if (i > 0) {
                    int up = getIndex(i - 1, j, m, n);
                    unionFind.union(up + 3, index + 1);
                }
            }
        }
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < unionFind.parent.length; i++) {
            set.add(unionFind.find(i));
        }
        return set.size();
    }

    public int getIndex(int row, int col, int m, int n) {
        return m * 4 * row + col * 4;
    }
}
