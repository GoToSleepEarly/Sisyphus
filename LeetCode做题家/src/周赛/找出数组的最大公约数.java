package 周赛;

import java.util.Arrays;

public class 找出数组的最大公约数 {

    public int findGCD(int[] nums) {
        int max = Arrays.stream(nums).max().getAsInt();
        int min = Arrays.stream(nums).min().getAsInt();
        return gcd(min,max);
    }

    public  int gcd(int m,int n) {
        if(m<n) {
            int k=m;
            m=n;
            n=k;
        }
        return m%n == 0?n:gcd(n,m%n);
    }
}
