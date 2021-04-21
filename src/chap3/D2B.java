package chap3;

import java.util.Arrays;
import java.util.Scanner;

public class D2B {
    public static void main(String[] args) {
        int n, i = 0;
        Scanner input = new Scanner(System.in);
        System.out.print("输入一个十进制数：");
        n = input.nextInt();
        int[] tmp = new int[64];
        while (n != 0) {
            tmp[i] = n & 1;
            n >>= 1;
            i++;
        }
        int[] d2B = Arrays.copyOf(tmp, i);
        System.out.print("二进制编码为：");
        for (int j = d2B.length - 1; j >= 0; j--)
            System.out.print(d2B[j]);
        System.out.println();
    }
}
