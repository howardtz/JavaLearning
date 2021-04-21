package chap4;

public class a {
    private int k;
    static int b;

    public static void main(String[] args) {
        a dc = new a(1);
        System.out.println(dc.getK());
        dc.change(dc);
        System.out.println(dc.getK());
    }

    public static int add(int a, int b) {
        return a + b;
    }

    public void change(a t) {
        t = new a(10);
        System.out.println(t.k);
    }

    a(int c) {
        k = c;
    }

    public int getK() {
        return k;
    }
}
