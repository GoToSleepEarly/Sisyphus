package 周赛;

public class 还原排列的最少操作步数 {
    public static void main(String[] args) {
        还原排列的最少操作步数 s = new 还原排列的最少操作步数();
        s.reinitializePermutation(4);
    }

    public int reinitializePermutation(int n) {
        int[] arr = new int[n];
        for (int i = 0; i < arr.length; i++) {
            if (i % 2 == 0) {
                arr[i] = i / 2;
            } else {
                arr[i] = n / 2 + (i - 1) / 2;
            }
        }
        int count = 0;
        int index = 1;
        do {
            index = arr[index];
            count++;
        } while (index != 1);
        return count;
    }
}
