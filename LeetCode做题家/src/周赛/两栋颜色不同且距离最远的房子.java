package 周赛;

public class 两栋颜色不同且距离最远的房子 {

    public int maxDistance(int[] colors) {
        int n = colors.length;
        int max = 0;
        for (int i = 0; i < n; i++) {
            for (int j = i+1; j < n; j++) {
                if (colors[i] != colors[j]){
                    max = Math.max(j-i,max);
                }
            }
        }
        return max;
    }
}
