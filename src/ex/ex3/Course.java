package ex.ex3;

public class Course {
    private String courseName;
    private int courseHour;

    public Course() {
        super();
    }

    public Course(String courseName, int courseHour) {
        this.courseName = courseName;
        this.courseHour = courseHour;
    }

    public String getCourseName() {
        return courseName;
    }

    public int getCourseHour() {
        return courseHour;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public void setCourseHour(int courseHour) {
        this.courseHour = courseHour;
    }

}
