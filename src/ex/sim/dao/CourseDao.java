package ex.sim.dao;

import java.util.List;

import ex.sim.entity.Course;

public interface CourseDao {
	public void insert(Course course);
	public void delete(String courseName);
	public void update(int id, Course course);	
	public List select();
	public Course selectByName(String courseName);
	public Course selectByNum(String num);
}
