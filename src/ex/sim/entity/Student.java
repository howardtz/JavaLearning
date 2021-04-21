package ex.sim.entity;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Student {
	private int id;
	private String num;
	private String name;
	private Date birthday;
	private String department;
	
	public Student(String num, String name, Date birthday, String department) {
		super();
		this.num = num;
		this.name = name;
		this.birthday = birthday;
		this.department = department;
	}
	
	public Student() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Date getBirthday() {
		return birthday;
	}
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getNum() {
		return num;
	}
	public void setNo(String num) {
		this.num = num;
	}
	
	public String toString(){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		StringBuilder sb = new StringBuilder();
		sb.append(num).append(","+name).append(","+sdf.format(birthday)).append(","+department);
		return sb.toString();
	}
	

}
