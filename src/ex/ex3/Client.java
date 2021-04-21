package ex.ex3;

import java.util.Scanner;

public class Client {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.print("输入学生的人数：");
        int n = input.nextInt();
        Student[] students = new Student[n];
        for (int i = 0; i < students.length; i++) {
            students[i] = new Student();
            System.out.print("输入第" + (i + 1) + "个学生的姓名：");
            students[i].setName(input.next());
            System.out.print("输入第" + (i + 1) + "个学生的年级：");
            students[i].setGrade(input.nextInt());
            System.out.print("输入第" + (i + 1) + "个学生选几门课程：");
            int courseNum = input.nextInt();
            Course[] courseInput = new Course[courseNum];
            for (int j = 0; j < courseInput.length; j++) {
                courseInput[j] = new Course();
                System.out.print("输入第" + (j + 1) + "门课程的名称：");
                courseInput[j].setCourseName(input.next());
                System.out.print("输入第" + (j + 1) + "门课程的学分：");
                courseInput[j].setCourseHour(input.nextInt());
            }
            students[i].setCourses(courseInput);
        }
        for (int i = 0; i < students.length; i++) {
            System.out.println("第" + (i + 1) + "个学生信息如下：");
            students[i].showMessage();
        }
    }
}
