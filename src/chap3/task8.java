package chap3;

import java.util.Scanner;

public class task8 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.print("输入参与的队伍总数：");
        int n = input.nextInt();
        System.out.printf("%6s", "");
        for (int i = 1; i <= n; i++) {
            System.out.printf("%-6d", i);
        }
        System.out.println();
        for (int i = 1; i <= n; i++) {
            for (int j = 0; j <= n; j++) {
                if (j == 0)
                    System.out.printf("%-6d", i);
                else if (i == j)
                    System.out.printf("%-6s", "~~~~");
                else
                    System.out.printf("%d~%-4d", i, j);
            }
            System.out.println();
        }
    }
}
