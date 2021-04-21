package chap4;

import java.util.Scanner;

public class PointTest {
    public static void main(String[] args) {
        int x, y;
        Scanner input = new Scanner(System.in);
        System.out.print("请输入一个点的点坐标：");
        x = input.nextInt();
        y = input.nextInt();
        Point a = new Point(x, y);
        System.out.print("请输入另一个点的点坐标：");
        x = input.nextInt();
        y = input.nextInt();
        System.out.println("点(" + a.getX() + "," + a.getY() + ")到原点的距离为：" + a.distance());
        System.out.println("点(" + a.getX() + "," + a.getY() + ")到点(" + x + "," + y + ")的距离为：" + a.distance(x, y));
    }
}
