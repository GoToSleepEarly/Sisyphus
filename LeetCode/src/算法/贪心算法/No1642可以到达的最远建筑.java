package 算法.贪心算法;

import java.util.Comparator;
import java.util.PriorityQueue;

public class No1642可以到达的最远建筑 {

    public static void main(String[] args) {
        No1642可以到达的最远建筑 s = new No1642可以到达的最远建筑();
        s.furthestBuilding(new int[]{4, 2, 7, 6, 9, 14, 12}, 5, 1);
    }

    public int furthestBuilding(int[] heights, int bricks, int ladders) {
        //经典优先队列贪心
        PriorityQueue<Integer> used = new PriorityQueue<>(Comparator.reverseOrder());
        int i = 0;
        for (; i < heights.length - 1; i++) {
            // 比下一个梯子高度大，不需要
            if (heights[i] < heights[i + 1]) {
                int delta = heights[i + 1] - heights[i];
                // 如果足够，就用砖块
                if (delta <= bricks) {
                    bricks -= delta;
                    used.add(delta);
                } else {
                    if (ladders == 0) {
                        return i;
                    }
                    // 使用梯子
                    // 没法置换，直接用梯子
                    if (used.isEmpty()) {
                        ladders--;
                        continue;
                    }
                    // 否则选择是否用梯子
                    int canGet = used.peek();
                    if (delta < canGet) {
                        used.poll();
                        bricks += canGet - delta;
                        used.add(delta);
                    }
                    ladders--;

                }
            }
        }
        return i;
    }
}
