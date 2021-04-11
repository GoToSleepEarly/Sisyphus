package 周赛;

public class Test {

    public static void main(String[] args) {
        A a = new C();
        a.foo();
    }

}

interface A {
    void foo();
}                                                                                                                                                                                                   

class B implements A {

    @Override
    public void foo() {
        System.out.println("A");
    }
}

class C extends B implements A {

}