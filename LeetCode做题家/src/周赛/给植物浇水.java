package 周赛;

public class 给植物浇水 {
    public int wateringPlants(int[] plants, int capacity) {
        int res = 0;
        int remain = capacity;
        for (int i = 0; i < plants.length; i++) {
            if (remain >= plants[i]) {
                remain -= plants[i];
                res++;
            } else {
                res += 2 * i;
                remain = capacity;
                i--;
            }
        }
        return res;
    }
}
