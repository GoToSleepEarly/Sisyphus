package 算法.双指针;

public class No415字符串相加 {

    public static void main(String[] args) {
        new No415字符串相加().addStrings("123","11");
    }
    public String addStrings(String num1, String num2) {
        int index1 = num1.length() - 1;
        int index2 = num2.length() - 1;
        StringBuilder res = new StringBuilder();
        int add = 0;
        while (index1 >= 0 || index2 >= 0) {
            int n1 = index1 >= 0 ? num1.charAt(index1) - '0' : 0;
            int n2 = index2 >= 0 ? num2.charAt(index2) - '0' : 0;
            int sum = n1 + n2 + add;
            if (sum >= 10) {
                add = 1;
                sum = sum % 10;
            } else {
                add = 0;
            }
            res.append(sum);
            index1--;
            index2--;
        }
        if (add == 1) {
            res.append("1");
        }
        return res.reverse().toString();
    }
}
