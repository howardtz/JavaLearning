package chap5;


public class Foo {
    public static void main(String[] args) {
        Koo k1=new Koo();
        Koo k2=new Koo();
        System.out.println(k1.id+","+k2.id+","+Koo.index);
//        k1.id=10;
    }
}

class  Koo{
    static int index=0;
    final int id;
    public Koo(){
        id=index++;
    }
}