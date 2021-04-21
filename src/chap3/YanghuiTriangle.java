package chap3;

import java.util.Scanner;

public class YanghuiTriangle {
    public static void main(String[] args) {
        System.out.print("Enter the num of level:");
        Scanner input = new Scanner(System.in);
        int n = input.nextInt();
        int[][] YhT = new int[n][];
        for (int i = 0; i < n; i++) {
            YhT[i] = new int[i + 1];
            YhT[i][0] = YhT[i][i] = 1;
            for (int j = 1; j < i; j++)
                YhT[i][j] = YhT[i - 1][j - 1] + YhT[i - 1][j];
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j <= i; j++)
                System.out.print(YhT[i][j] + " ");
            System.out.println();
        }
        input.close();
    }
}
