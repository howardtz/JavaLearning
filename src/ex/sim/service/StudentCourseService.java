package ex.sim.service;

import java.util.List;


import ex.sim.dao.StudentCourseDao;
import ex.sim.dao.impl.StudentCourseDaoImpl;
import ex.sim.entity.StudentCourse;

public class StudentCourseService {
	private StudentCourseDao stuCourseDao;
	
	public StudentCourseService() {
		stuCourseDao = new StudentCourseDaoImpl();
	}
	
	public void addStudentCourse(StudentCourse cs){
		stuCourseDao.insert(cs);
	}
	
	public boolean find(StudentCourse sc){
		return stuCourseDao.select(sc);
	}
	
	public List findByStuNum(String stuNum){
		return stuCourseDao.selectByStuNum(stuNum);
	}

	public List findByCourseNum(String courseNum){
		return stuCourseDao.selectByCourseNum(courseNum);
	}
}
