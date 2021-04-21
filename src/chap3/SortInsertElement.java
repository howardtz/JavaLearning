package chap3;

import java.util.Arrays;
import java.util.Scanner;

public class SortInsertElement {
    public static void main(String[] args) {
        int[] basic = new int[10];
        initArray(basic);
        int[] result = Arrays.copyOf(basic, basic.length + 1);
        System.out.print("输入要插入的数字:");
        Scanner input = new Scanner(System.in);
        int i = 0, n = input.nextInt();
        if (basic[0] < basic[basic.length - 1])
            for (; i < basic.length - 1; i++)
                if (n <= basic[i])
                    break;
                else
                    for (; i < basic.length - 1; i++)
                        if (n >= basic[i])
                            break;
        for (int j = basic.length - 1; j >= i; j--) {
            result[j + 1] = result[j];
        }
        result[i] = n;
        System.out.print("插入前:");
        for (int element:basic)
            System.out.print(element+" ");
        System.out.println();
        System.out.print("插入后:");
        for (int element:result)
            System.out.print(element+" ");
    }

    public static void initArray(int[] basic) {
        for (int i = 0; i < basic.length; i++) {
            basic[i] = (int) (Math.random() * 101);
        }
        Arrays.sort(basic);

    }
}
