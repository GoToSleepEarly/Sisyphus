package 算法.数学.待分解;

import java.util.Arrays;

public class 你完成的完整对局数 {

    /**
     * 坐标转换题
     * 1、常规的除法，每一部分表示为[nx,(n+1)x)，左闭右开。或者说是[x,2x-1]这个区间
     * 2、将我们所需的坐标，对应到区间上，即n与目标t的转换
     * 3、处理边界情况，即nx和(n+1)x属于当前部分还是下一部分
     */
    public int numberOfRounds(String startTime, String finishTime) {
        String[] startArr = startTime.split(":");
        String[] finishArr = finishTime.split(":");
        int startH = Integer.parseInt(startArr[0]);
        int startM = Integer.parseInt(startArr[1]);
        int finishH = Integer.parseInt(finishArr[0]);
        int finishM = Integer.parseInt(finishArr[1]);
        int s2 = startM % 15 == 0 ? startM / 15 : (startM / 15 + 1);
        int f2 = finishM / 15;
        int res = 0;
        finishM = f2 * 4;
        startM = s2 * 4;
        if (finishH < startH || (finishH == startH && finishM < startM)) {
            finishH += 24;
        }
        if (finishM < startM) {
            finishH -= 1;
            f2 += 4;
        }
        res += (finishH - startH) * 4 + f2 - s2;
        System.out.println(Arrays.asList(s2, f2, startH, finishH));
        // 防止12:01,12:02这样的特殊场景
        return Math.max(res, 0);
    }
}
