package 周赛;

import java.util.HashSet;
import java.util.Set;

public class 字符串中不同整数的数目 {

    public static void main(String[] args) {
        字符串中不同整数的数目 s = new 字符串中不同整数的数目();
        s.numDifferentIntegers("xtimt5kqkz9osexe56ezwwninlyeeqsq5m99904os3ygs12t31n1et4uwzmt5kvv6teisobuxt10k33v1aaxysg4y8nsivxdp3fo9dr7x58m8uc4ofm41ai77u8cvzr5r3s97f5otns59ubqk57xwl00xsp9w2oodt6yxcbscloyr9c2su8gca1ly6rrjufm25luhxhesxwn7bk1as9na4cbabxk");
    }

    public int numDifferentIntegers(String word) {
        Set<String> set = new HashSet<>();
        word = word.replaceAll("[a-z]", " ");
        String[] nums = word.split(" ");
        for (String num : nums) {
            if (num.trim().equals("")) {
                continue;
            }
            // 去除前缀零
            int i = 0;
            for (; i < num.length(); i++) {
                if (num.charAt(i) != '0') {
                    break;
                }
            }
            if (i == num.length()) {
                set.add("0");
            } else {
                set.add(num.trim().substring(i));
            }
        }
        return set.size();
    }
}
