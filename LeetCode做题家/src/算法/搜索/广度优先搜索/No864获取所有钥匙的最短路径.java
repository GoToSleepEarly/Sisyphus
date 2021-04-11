package 算法.搜索.广度优先搜索;

import java.util.*;

public class No864获取所有钥匙的最短路径 {
    public int shortestPathAllKeys(String[] grid) {

        char[][] chars = new char[grid.length][grid[0].length()];
        int startX = 0;
        int startY = 0;
        int keysNum = 0;
        // 获取起点位置和钥匙数量
        for (int i = 0; i < grid.length; i++) {
            chars[i] = grid[i].toCharArray();
            for (int j = 0; j < grid[0].length(); j++) {
                if (chars[i][j] == '@') {
                    startX = i;
                    startY = j;
                }
                if (chars[i][j] >= 'a' && chars[i][j] <= 'f') {
                    keysNum++;
                }
            }
        }
        // 4个方向
        int[][] dirs = new int[][]{{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
        Queue<Point> queue = new LinkedList<>();
        // 走过的路
        Set<String> visited = new HashSet<>();
        // 初始点肯定没有钥匙
        Point start = new Point(startX, startY, 0);
        queue.add(start);
        visited.add(0 + " " + startX + " " + startY);
        int count = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                Point cur = queue.poll();
                if (cur.keys == ((1 << keysNum) - 1)) {
                    return count;
                }
                // 往4个方向探索
                for (int[] dir : dirs) {
                    int nextX = cur.x + dir[0];
                    int nextY = cur.y + dir[1];
                    int nextKeys = cur.keys;
                    // 边界值
                    if (nextX < 0 || nextX >= chars.length || nextY < 0 || nextY >= chars[0].length) {
                        continue;
                    }
                    // 获取该位上的值
                    char nextValue = chars[nextX][nextY];
                    // 墙壁
                    if (nextValue == '#') {
                        continue;
                    }
                    // 锁
                    if (nextValue >= 'A' && nextValue <= 'F' &&
                            ((nextKeys >> (nextValue - 'A') & 1) == 0)) {
                        continue;
                    }
                    // 钥匙
                    if (nextValue >= 'a' && nextValue <= 'f') {
                        nextKeys |= (1 << (nextValue - 'a'));
                    }

                    if (!visited.contains(nextKeys + " " + nextX + " " + nextY)) {
                        // 入队列
                        Point nexPoint = new Point(nextX, nextY, nextKeys);
                        queue.add(nexPoint);
                        visited.add(nextKeys + " " + nextX + " " + nextY);
                    }

                }
            }
            count++;
        }
        return -1;
    }

}

class Point {
    int x;
    int y;
    int keys;

    Point(int x, int y, int keys) {
        this.x = x;
        this.y = y;
        this.keys = keys;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Point point = (Point) o;
        return x == point.x &&
                y == point.y &&
                keys == point.keys;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y, keys);
    }
}