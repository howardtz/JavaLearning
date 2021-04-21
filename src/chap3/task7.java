package chap3;

import java.util.Scanner;

public class task7 {
    public static void main(String[] args) {
        Scanner input=new Scanner(System.in);
        int n;
        System.out.print("输入矩阵的阶数：");
        while((n=input.nextInt())>0){
            int[][] array = new int[n][n];
            for (int i = 0; i < n; i++)
                for (int j = 0; j < n; j++)
                    array[i][j] = input.nextInt();
            if (isSymmetricMatrix(array))
                System.out.println("是对称矩阵");
            else
                System.out.println("不是对称矩阵");
            System.out.print("输入矩阵的阶数：");
        }
        input.close();
    }

    public static boolean isSymmetricMatrix(int[][] array) {
        for (int i = 0; i < array.length; i++)
            for (int j = 0; j < array[i].length; j++)
                if (array[i][j] != array[j][i])
                    return false;
        return true;
    }
}
