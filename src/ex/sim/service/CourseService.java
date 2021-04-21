package ex.sim.service;

import java.util.List;


//import ums.util.DaoFactory;

import ex.sim.dao.CourseDao;
import ex.sim.dao.impl.CourseDaoImpl;
import ex.sim.entity.Course;

public class CourseService {
    private CourseDao courseDao;

    public CourseService() {
        courseDao = new CourseDaoImpl();
    }

    public void addCourse(Course course) {
        courseDao.insert(course);
    }

    public void removeCourse(String name) {
    }

    public void modifyCourse(int id, Course course) {
    }

    public Course findByName(String name) {
        return courseDao.selectByName(name);
    }

    public List findAll() {
        return courseDao.select();
    }

    public Course findByNum(String num) {
        return courseDao.selectByNum(num);
    }
}
