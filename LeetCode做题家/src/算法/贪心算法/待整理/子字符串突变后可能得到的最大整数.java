package 算法.贪心算法.待整理;

public class 子字符串突变后可能得到的最大整数 {

    public String maximumNumber(String num, int[] change) {
        StringBuilder sb = new StringBuilder();
        int index = 0;
        // left part
        while (index < num.length()) {
            int before = num.charAt(index) - '0';
            int after = change[before];
            if (after > before) {
                break;
            }
            sb.append(before);
            index++;
        }
        // 开始变换
        while (index < num.length()) {
            int before = num.charAt(index) - '0';
            int after = change[before];
            if (after < before) {
                break;
            }
            sb.append(after);
            index++;
        }
        if (index < num.length()) {
            sb.append(num.substring(index));
        }
        return sb.toString();
    }
}
