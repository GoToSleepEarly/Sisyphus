package 周赛;

public class 有界数组中指定下标处的最大值 {

    public static void main(String[] args) {
        有界数组中指定下标处的最大值 s = new 有界数组中指定下标处的最大值();
        s.maxValue(3, 0, 815094800);
    }

    public int maxValue(int n, int index, int maxSum) {
        // 来一手二分查找
        int left = maxSum / n;
        int right = maxSum - n - 1;
        while (left < right) {
            int mid = left + (right - left + 1) / 2;
            if (canPut(n, index, maxSum, mid)) {
                left = mid;
            } else {
                right = mid - 1;
            }
        }
        return left;
    }

    private boolean canPut(int n, int index, int maxSum, int num) {
        long sum = cal(index + 1, num);
        if (sum > maxSum) {
            return false;
        }
        sum += cal(n - index, num) - num;
        return sum <= maxSum;
    }

    private long cal(int k, int num) {
        if (num >= k) {
            return ((long) (num + num - k + 1)) * k / 2;
        } else {
            return ((long) (1 + num) * num) / 2 + k - num;
        }
    }

}
