package 周赛;

public class 比赛中的配对次数 {

    public int numberOfMatches(int n) {
        int res = 0;
        while (n != 1) {
            res += n / 2;
            n = n%2 ==1?(n/2)+1:n/2;
        }
        return res;
    }
}
