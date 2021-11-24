package bean;

import org.springframework.stereotype.Repository;

@Repository
public class TestBean extends TestFather implements ITestBean {
    public static int defaultNum = init();

    String str = "...";

    public static int init() {
        System.out.println("TestBean has initialized sth");
        return 1;
    }

    public static void test() {
        System.out.println("test successfully");
    }

}
