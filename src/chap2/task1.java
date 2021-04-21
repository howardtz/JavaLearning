package chap2;

import java.util.Scanner;

public class task1 {
    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);
        System.out.print("Enter the Distance:");
        double distance = scn.nextDouble();
        int price = 0;
        if (distance < 0) {
            System.out.println("Wrong Input");
            return;
        } else if (distance <= 6)
            price = 3;
        else if (distance <= 12)
            price = 4;
        else if (distance <= 22)
            price = 5;
        else if (distance <= 32)
            price = 6;
        else
            price = 6 + (int) Math.ceil((distance - 32) / 20);
        System.out.println("Price is ¥" + price);
    }
}
