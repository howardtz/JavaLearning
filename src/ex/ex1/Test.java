package ex.ex1;

import java.util.Scanner;

public class Test {
    public static class Rectangle {
        public static void main(String[] args) {
            Scanner scn = new Scanner(System.in);
            System.out.print("Enter the length: ");
            double l = scn.nextDouble();
            System.out.print("Enter the width: ");
            double w = scn.nextDouble();
            System.out.println("Circumference:" + (l + l + w + w));
            System.out.println("Area:" + (l * w));
        }
    }
}
