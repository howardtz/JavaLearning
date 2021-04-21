package ex.sim.dao;

import java.util.List;

import ex.sim.entity.Student;

public interface StudentDao {
	
	public void insert(Student stu);
	public void update(int id, Student stu);
	public void delete(String name);
	public List select();
	public Student selectByName(String name);
	public Student selectByNum(String num);
}
