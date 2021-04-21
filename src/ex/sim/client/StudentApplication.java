package ex.sim.client;

import java.util.Calendar;


import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

import ex.sim.entity.Course;
import ex.sim.entity.Student;
import ex.sim.entity.StudentCourse;
import ex.sim.service.CourseService;
import ex.sim.service.StudentCourseService;
import ex.sim.service.StudentService;

public class StudentApplication {
    public static void main(String[] args) {
        StudentService ss = new StudentService();

        Student stu1 = new Student("006", "tom", StudentApplication.setDate(1990, 1, 1), "计算机"); //1990-1-1
        Student stu2 = new Student("007", "jerry", StudentApplication.setDate(1991, 11, 1), "计算机");
//		ss.addStudent(stu1);
//		ss.addStudent(stu2);
        List list = ss.findAll();
        Iterator<Student> it1 = list.iterator();
        System.out.println("学生信息：");
        while (it1.hasNext()) {
            Student stu = it1.next();
            System.out.println(stu);
        }
        System.out.println();


        CourseService cs = new CourseService();
        Course course1 = new Course("001", "C程序设计", 3);
        Course course2 = new Course("002", "Java程序设计", 4);
//		cs.addCourse(course1);
//		cs.addCourse(course2);
        List courseList = cs.findAll();
        Iterator<Course> it2 = courseList.iterator();
        System.out.println("课程信息：");
        while (it2.hasNext()) {
            Course course = it2.next();
            System.out.println(course);
        }
        System.out.println();

        Scanner scn = new Scanner(System.in);
        System.out.print("输入选课的学生编号:");
        String stuNum = scn.next();
        while (!stuNum.equals("quit")) {
            if (ss.findByNum(stuNum) == null) {
                System.out.println("没有该学生.");
            } else {
                System.out.print("输入选课的课程编号:");
                String courseNum = scn.next();
                if (cs.findByNum(courseNum) == null) {
                    System.out.println("没有该课程.");
                } else {
                    StudentCourse sc = new StudentCourse(stuNum, courseNum);
                    StudentCourseService scs = new StudentCourseService();
                    if (!scs.find(sc)) {
                        scs.addStudentCourse(sc);
                        System.out.println("选课成功!");
                    } else {
                        System.out.println("该选课信息已存在.");
                    }
                }
            }
            System.out.print("输入选课的学生编号:");
            stuNum = scn.next();
        }
        System.out.println("选课结束.");

        System.out.print("输入要查询哪个学生的选课信息-姓名：");
        String stuName = scn.next();
        stuNum = ss.findByName(stuName).getNum();
        StudentCourseService scs = new StudentCourseService();
        List stuCourses = scs.findByStuNum(stuNum);
        Iterator<StudentCourse> it = stuCourses.iterator();
        while (it.hasNext()) {
            StudentCourse sc = it.next();
            System.out.println(cs.findByNum(sc.getcNum()));
        }

        System.out.print("输入要查询哪门课程的选课信息-课程名：");
        String courseName = scn.next();
        String coursNum = cs.findByName(courseName).getNum();
        scs = new StudentCourseService();
        stuCourses = scs.findByCourseNum(coursNum);
        it = stuCourses.iterator();
        while (it.hasNext()) {
            StudentCourse sc = it.next();
            System.out.println(ss.findByNum(sc.getsNum()));
        }

    }

    private static java.util.Date setDate(int year, int month, int day) {
        Calendar cal = Calendar.getInstance();
        cal.set(year, month - 1, day);
        return new java.util.Date(cal.getTimeInMillis());
    }


}
