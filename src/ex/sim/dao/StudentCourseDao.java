package ex.sim.dao;

import java.util.List;

import ex.sim.entity.StudentCourse;

public interface StudentCourseDao {
	public void insert(StudentCourse cs);
	public boolean select(StudentCourse sc);
	public List selectByStuNum(String stuNum);
	public List selectByCourseNum(String courseNum);
}
