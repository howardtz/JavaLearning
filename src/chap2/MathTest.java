package chap2;

import java.util.Scanner;

public class MathTest {

    public static void main(String[] args) {
        System.out.println("***请按编号选择使用哪个功能***");
        System.out.println("1.判断某数是否为素数");
        System.out.println("2.获取亲密数");
        System.out.println("3.算术练习器");
        System.out.println("0.退出");
        run();
    }

    public static void run() {
        Scanner scn = new Scanner(System.in);
        System.out.print("输入菜单编号:");
        int option = scn.nextInt();
        int x;
        while (option != 0) {
            switch (option) {
                case 1 -> {
                    System.out.print("请输入一个数字：");
                    x = scn.nextInt();
                    if (isPrime(x))
                        System.out.println(x + "是素数");
                    else
                        System.out.println(x + "不是素数");
                }
                case 2 -> {
                    System.out.print("你想求几以内的亲密数：");
                    x = scn.nextInt();
                    int count = getIntimacy(x);
                    if (count == 0)
                        System.out.println("该范围内没有亲密数");
                    else
                        System.out.println("共有亲密数" + count + "对");
                }
                case 3 -> {
                    System.out.print("输入要练习题目的个数：");
                    x = scn.nextInt();
                    exercise(x);
                }
            }
            System.out.print("输入菜单编号:");
            option = scn.nextInt();
        }
        System.out.println("再见!");
    }

    public static boolean isPrime(int x) {
        for (int i = 2; i * i <= x; i++)
            if (x % i == 0)
                return false;
        return true;
    }

    public static int getIntimacy(int n) {
        int a, b, count = 0, sum = 0;
        for (a = 1; a < n; a++) {
            b = 1;
            for (int i = 2; i * i <= a; i++)
                if (a % i == 0)
                    b += i + a / i;
            if (a < b) {
                sum = 1;
                for (int i = 2; i * i <= a; i++)
                    if (b % i == 0)
                        sum += i + b / i;
            }
            if (sum == a) {
                System.out.println(a + "和" + b + "是一对亲密数");
                count++;
            }
        }
        return count;
    }

    public static void exercise(int x) {//算术练习
        int m, n, op, resInput, resCalculate = 0;
        int countR = 0, countW = 0;
        do {
            do {
                m = (int) (Math.random() * 100);
                n = (int) (Math.random() * 100);
            } while (m < 10 || n < 10);
            op = (int) (Math.random() * 4);
            switch (op) {
                case 0 -> {
                    System.out.print(m + "+" + n + "=");
                    resCalculate = m + n;
                }
                case 1 -> {
                    System.out.print(m + "-" + n + "=");
                    resCalculate = m - n;
                }
                case 2 -> {
                    System.out.print(m + "*" + n + "=");
                    resCalculate = m * n;
                }
                case 3 -> {
                    System.out.print(m + "/" + n + "=");
                    resCalculate = m / n;
                }
            }
            Scanner sc = new Scanner(System.in);
            resInput = sc.nextInt();  //用户输入的答案
            if (resInput == resCalculate) {
                System.out.println("答案正确!");
                countR++;
            } else {
                System.out.println("答案错误!");
                countW++;
            }
        } while ((countR + countW) < x);

        System.out.print("你做对" + countR + "道题！");
        System.out.println("做错" + countW + "道题！");
    }
}
