package ex.ex3;

public class Student {
    private String name;
    private int grade;
    private Course[] courses;

    public Student() {
        super();
    }

    public int getGrade() {
        return grade;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public Course[] getCourses() {
        return courses;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    public void setCourses(Course[] courses) {
        this.courses = courses;
    }

    public int getHours() {
        int hourSum = 0;
        for (Course element : courses) {
            hourSum += element.getCourseHour();
        }
        return hourSum;
    }

    public void showMessage() {
        System.out.println(this.name + " " + this.grade + " 年级");
        System.out.println("选修的课程包括：");
        for (int i = 0; i < courses.length; i++) {
            System.out.println("(" + (i + 1) + ")" + courses[i].getCourseName() + "   " + courses[i].getCourseHour() + "分");
        }
        System.out.println("总学分：" + this.getHours());
    }
}

