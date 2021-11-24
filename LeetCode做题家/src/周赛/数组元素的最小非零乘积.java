package 周赛;

public class 数组元素的最小非零乘积 {

    static final long MOD = 1000000007;

    public int minNonZeroProduct(int p) {
        long a = (1L << p) - 1L;
        long b = a - 1L;
        long c = b / 2L;
        long ans = ((a % MOD) * pow(b % MOD, c)) % MOD;

        return (int) ans;
    }

    public long pow(long b, long c) {
        long ans = 1;
        while (c > 0) {
            if ((c & 1) == 1) {
                ans = (ans * b) % MOD;
            }
            c >>= 1;
            b = (b * b) % MOD;
        }
        return ans;
    }
}
