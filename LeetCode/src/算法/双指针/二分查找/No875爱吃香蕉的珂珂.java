package 算法.双指针.二分查找;

import java.util.Arrays;

public class No875爱吃香蕉的珂珂 {

    public int minEatingSpeed(int[] piles, int H) {
        int left = 0;
        int right = Arrays.stream(piles).max().getAsInt();
        while (left < right) {
            int mid = (left + right) >> 1;
            if (canEat(piles, mid, H)) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }

    private boolean canEat(int[] piles, int speed, int H) {
        int sum = 0;
        for (int pile : piles) {
            //向上取整
            sum += Math.ceil(pile * 1.0 / speed);
        }
        return sum <= H;
    }
}
