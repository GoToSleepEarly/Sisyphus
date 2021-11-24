package 算法.数学.待分解;

public class No1922统计好数字的数目 {

    long mod = (long) (1e9 + 7);

    public int countGoodNumbers(long n) {
        long even = n % 2 == 0 ? n / 2 : n / 2 + 1;
        long odd = n / 2;
        return (int) ((foo(5, even) * foo(4, odd)) % mod);
    }

    private long foo(int base, long cnt) {
        if (cnt == 1) {
            return base;
        }

        long half = foo(base, cnt / 2) % mod;
        if (cnt % 2 == 0) {
            return (half * half) % mod;
        }
        return (half * half * base) % mod;
    }
}
