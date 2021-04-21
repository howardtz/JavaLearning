package ex.ex1;

import java.util.Scanner;

public class Circle {
    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);
        System.out.print("Enter the radius: ");
        double r = scn.nextDouble();
        System.out.println("Circumference:" + (2 * Math.PI * r));
        System.out.println("Area:" + (Math.PI * r * r));
    }
}
