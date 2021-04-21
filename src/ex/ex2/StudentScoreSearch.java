package ex.ex2;

import java.util.Scanner;

public class StudentScoreSearch {
    final static int STUDENT_NUM = 6;
    final static int COURSE_NUM = 5;
    static String[] students = {"zhang", "wang", "li", "zhao", "liu", "song"};
    static String[] courses = {"C", "Java", "mySQL", "Linux", "HTML"};
    static int[][] score = new int[STUDENT_NUM][COURSE_NUM];

    public static void main(String[] args) {
        for (int i = 0; i < score.length; i++) {
            for (int j = 0; j < score[i].length; j++)
                score[i][j] = (int) (Math.random() * 101);
        }
        run(students, courses, score);
    }

    public static void run(String[] students, String[] courses, int[][] score) {
        Scanner scn = new Scanner(System.in);
        while (true) {
            System.out.print("请输入命令：");
            String command = scn.next();
            if (command.equalsIgnoreCase("avg")) {
                String parameter = scn.next();
                avg(students, courses, score, parameter);
            } else if (command.equalsIgnoreCase("max")) {
                String parameter = scn.next();
                getMax(students, courses, score, parameter);
            } else if (command.equalsIgnoreCase("exit")) {
                System.out.println("退出查询系统！byebye！");
                System.exit(0);
            } else {
                System.out.println("输入指令有误，请重新输入！");
            }
        }
    }

    public static void avg(String[] students, String[] courses, int[][] score, String parameter) {
        if (parameter.equalsIgnoreCase("all")) {
            int[] studentSum = new int[students.length];
            int[] courseSum = new int[courses.length];
            for (int i = 0; i < students.length; i++) {
                for (int j = 0; j < courses.length; j++) {
                    studentSum[i] += score[i][j];
                    courseSum[j] += score[i][j];
                }
            }
            System.out.printf("%-8s", "");
            for (String element : courses)
                System.out.printf("%-8s", element);
            System.out.printf("%s\n", "平均分");
            for (int i = 0; i < students.length; i++) {
                System.out.printf("%-8s", students[i]);
                for (int j = 0; j < courses.length; j++)
                    System.out.printf("%-8d", score[i][j]);
                System.out.printf("%-8.2f\n", (double) studentSum[i] / courses.length);
            }
            System.out.printf("%-5s", "平均分");
            for (int element : courseSum) {
                System.out.printf("%-8.2f", (double) element / students.length);
            }
            System.out.println();
        }
    }

    public static void getMax(String[] students, String[] courses, int[][] score, String parameter) {
        int max = -1, maxNum = 0;
        for (int i = 0; i < students.length; i++) {
            if (students[i].equalsIgnoreCase(parameter)) {
                for (int j = 0; j < courses.length; j++) {
                    if (max < score[i][j]) {
                        max = score[i][j];
                        maxNum = j;
                    }
                }
                System.out.println(students[i] + "的" + courses[maxNum] + "课程分数最高：" + max);
                return;
            }
        }
        for (int i = 0; i < courses.length; i++) {
            if (courses[i].equalsIgnoreCase(parameter)) {
                for (int j = 0; j < students.length; j++) {
                    if (max < score[j][i]) {
                        max = score[j][i];
                        maxNum = j;
                    }
                }
                System.out.println(students[maxNum] + "的" + courses[i] + "课程分数最高：" + max);
                return;
            }
        }
        System.out.println("你输入的既不是课程名，也不是学生名");
    }
}
