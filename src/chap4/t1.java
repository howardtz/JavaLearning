package chap4;

public class t1 {
    public static void main(String[] args) {
        B bb = new B("GG");
        B.abc();
    }
}

class B {
    String name;
    static int b;

    static {
        b = 12;
        System.out.println("static代码块构造成功");
    }

    B(String name) {
        super();
        this.name = name;
        System.out.println("非static成员构造成功");
    }

    static void abc() {

    }

}

