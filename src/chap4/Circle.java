package chap4;

import java.util.Scanner;

public class Circle {
    private Point centre;
    private double radius;

    Circle() {
    }

    Circle(int x, int y, double r) {
        centre = new Point(x, y);
        radius = r;
    }

    public double getArea() {
        return Math.PI * radius * radius;
    }

    public boolean contains(Point p) {
        return centre.distance(p) <= radius;
    }

    public boolean contains(int x, int y) {
        Point p = new Point(x, y);
        return centre.distance(p) <= radius;
    }

    public static void main(String[] args) {
        Circle c1;
        int x, y, r;
        Scanner input = new Scanner(System.in);
        System.out.print("圆心坐标：");
        x = input.nextInt();
        y = input.nextInt();
        System.out.print("半径：");
        r = input.nextInt();
        c1 = new Circle(x, y, r);
        System.out.println("圆面积为" + c1.getArea());
        System.out.print("输入点坐标：");
        x = input.nextInt();
        y = input.nextInt();
        if (c1.contains(x, y)) {
            System.out.println("在圆内");
        } else {
            System.out.println("在圆外");
        }

    }
}

