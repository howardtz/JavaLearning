package ex.sim.dao.impl;

import java.sql.Connection;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import ex.sim.dao.StudentDao;
import ex.sim.entity.Student;
import ex.sim.util.ConnectionFactory;
//import chap13.util.ResourceUtil;
//import chap13.util.ViewResultSet;

public class StudentDaoImpl implements StudentDao{
	public void delete(String name) {
	}

	public void insert(Student stu) {
		Connection con = null;
    	PreparedStatement ps = null;
    	
    	try {
			con=ConnectionFactory.getConnection();
			String sql = "insert into student values(NULL,?,?,?,?)";
			ps  = con.prepareStatement(sql);
			ps.setString(1, stu.getNum());
			ps.setString(2, stu.getName());
			
			//将java.util.Date 转换为java.sql.Date
			//本质：毫秒数
			ps.setDate(3, new Date(stu.getBirthday().getTime()));
			
			ps.setString(4,stu.getDepartment());
			
			ps.executeUpdate();			
    	}catch(SQLException e){
    		e.printStackTrace();
		}
	}

	public List select() {
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		List list = new ArrayList();
		try{
			con=ConnectionFactory.getConnection();
			st = con.createStatement();
			String sql = "select * from student";
			rs = st.executeQuery(sql);
			while(rs.next()){
				Student stu = new Student();
				stu.setId(rs.getInt("id"));
				stu.setName(rs.getString("name"));
				stu.setDepartment(rs.getString("department"));
				stu.setNo(rs.getString("num"));
				stu.setBirthday(new java.util.Date(rs.getDate("birthday").getTime()));
				list.add(stu);
			}
		}catch(SQLException e){
				e.printStackTrace();
		}
		return list;
	}

	public Student selectByName(String name) {		
		Connection con = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		Student stu = null;
		try{
			con=ConnectionFactory.getConnection();
			
			String sql = "select * from student where name=?";
			pst = con.prepareStatement(sql);
			pst.setString(1, name);
			rs = pst.executeQuery();
			if(rs.next()){
				stu = new Student();
				stu.setId(rs.getInt("id"));
				stu.setName(rs.getString("name"));
				stu.setDepartment(rs.getString("department"));
				stu.setNo(rs.getString("num"));
				stu.setBirthday(new java.util.Date(rs.getDate("birthday").getTime()));
			}
		}catch(SQLException e){
				e.printStackTrace();
		}
		return stu;
	}

	public void update(int id, Student stu) {
	}

	
	public Student selectByNum(String num) {
		Connection con = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		Student stu = null;
		try{
			con=ConnectionFactory.getConnection();
			
			String sql = "select * from student where num=?";
			pst = con.prepareStatement(sql);
			pst.setString(1, num);
			rs = pst.executeQuery();
			if(rs.next()){
				stu = new Student();
				stu.setId(rs.getInt("id"));
				stu.setName(rs.getString("name"));
				stu.setDepartment(rs.getString("department"));
				stu.setNo(rs.getString("num"));
				stu.setBirthday(new java.util.Date(rs.getDate("birthday").getTime()));
			}
		}catch(SQLException e){
				e.printStackTrace();
		}
		return stu;
	}
}
