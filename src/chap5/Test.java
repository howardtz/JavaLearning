package chap5;

class Base{
    public Base show(){
        System.out.println("12");
        Base a=new Base();
        return a;
    }
}

class child extends Base{
    public child show(){
        System.out.println("22");
        child a=new child();
        return a;
    }
}

public class Test {
    public static void main(String[] args) {
        new child().toString();
    }
}
