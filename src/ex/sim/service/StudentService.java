package ex.sim.service;

import java.util.List;

//import ums.util.DaoFactory;
import ex.sim.dao.StudentDao;
import ex.sim.dao.impl.StudentDaoImpl;
import ex.sim.entity.Student;

public class StudentService {
	private StudentDao stuDao;
	
	public StudentService() {
//		stuDao = (Studentdao)DaoFactory.getDao("studentDao");
		stuDao = new StudentDaoImpl();
	}
	
	public void addStudent(Student stu){
		stuDao.insert(stu);
	}
	
	public void removeStudent(String name){
	}
	
	public void modifyStudent(int id, Student stu){
	}
	
	public Student findByName(String name){
		return stuDao.selectByName(name);
	}
	
	public List findAll(){
		return stuDao.select();
	}
	
	public Student findByNum(String num){
		return stuDao.selectByNum(num);
	}
}
