package 数据结构.并查集;

import java.util.Arrays;
import java.util.Comparator;

public class No1579保证图可完全遍历 {

    public int maxNumEdgesToRemove(int n, int[][] edges) {
        UnionFind alice = new UnionFind(n + 1);
        UnionFind bob = new UnionFind(n + 1);
        int count = 0;
        // 假设3最先
        Arrays.sort(edges, Comparator.comparingInt(e -> -e[0]));
        for (int[] edge : edges) {
            int type = edge[0];
            int from = edge[1];
            int to = edge[2];
            if (type == 3) {
                // 都加入并查集
                if (alice.find(from) == alice.find(to) || bob.find(from) == bob.find(to)) {
                    count++;
                } else {
                    alice.union(from, to);
                    bob.union(from, to);
                }
            } else if (type == 2) {
                if (bob.find(from) == bob.find(to)) {
                    count++;
                } else {
                    bob.union(from, to);
                }
            } else {
                if (alice.find(from) == alice.find(to)) {
                    count++;
                } else {
                    alice.union(from, to);
                }
            }
        }
        if (alice.getCount() != 2 || bob.getCount() != 2) {
            return -1;
        }
        return count;
    }

    class UnionFind {
        private int[] id;
        private int count;
        private int[] sz;

        public UnionFind(int N) {
            count = N;
            id = new int[N];
            sz = new int[N];
            for (int i = 0; i < N; i++) {
                id[i] = i;
                sz[i] = 1;
            }
        }

        public int getCount() {
            return count;
        }

        public boolean connected(int p, int q) {
            return find(p) == find(q);
        }

        public int find(int p) {
            if (p != id[p])
                id[p] = find(id[p]);
            return id[p];
        }

        public void union(int p, int q) {
            int pRoot = find(p);
            int qRoot = find(q);

            if (pRoot == qRoot)
                return;

            if (sz[pRoot] < sz[qRoot]) {
                id[pRoot] = qRoot;
                sz[qRoot] += sz[pRoot];
            } else {
                id[qRoot] = pRoot;
                sz[pRoot] += sz[qRoot];
            }
            count--;
        }

    }
}
