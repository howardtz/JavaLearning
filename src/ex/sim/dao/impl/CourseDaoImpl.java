package ex.sim.dao.impl;

import java.sql.Connection;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import ex.sim.dao.CourseDao;
import ex.sim.dao.StudentDao;
import ex.sim.entity.Course;
import ex.sim.entity.Student;
import ex.sim.util.ConnectionFactory;
//import chap13.util.ResourceUtil;
//import chap13.util.ViewResultSet;

public class CourseDaoImpl implements CourseDao{
	public void delete(String name) {
	}

	public void insert(Course course) {
		Connection con = null;
    	PreparedStatement pst = null;
    	
    	try {
			con=ConnectionFactory.getConnection();
			String sql = "insert into course values(NULL,?,?,?)";
			pst  = con.prepareStatement(sql);
			pst.setString(1, course.getNum());
			pst.setString(2, course.getCourseName());
			pst.setInt(3, course.getCredit());
			pst.executeUpdate();			
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
			String sql = "select * from course";
			rs = st.executeQuery(sql);
			while(rs.next()){
				Course course = new Course();
				course.setId(rs.getInt("id"));
				course.setNum(rs.getString("num"));
				course.setCourseName(rs.getString("name"));
				course.setCredit(rs.getInt("credit"));
				list.add(course);
			}
		}catch(SQLException e){
				e.printStackTrace();
		}
		return list;
	}

	public Course selectByName(String name) {		
		Connection con = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		Course course = null;
		
		try{
			con=ConnectionFactory.getConnection();
			
			String sql = "select * from course where name like '"+ name +"%'";
			pst = con.prepareStatement(sql);
			rs = pst.executeQuery();
			if(rs.next()){
				course = new Course();
				course.setId(rs.getInt("id"));
				course.setNum(rs.getString("num"));
				course.setCourseName(rs.getString("name"));
				course.setCredit(rs.getInt("credit"));
			}
		}catch(SQLException e){
				e.printStackTrace();
		}
		return course;
	}

	public void update(int id, Course course) {
		
	}

	public Course selectByNum(String num){
		Connection con = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		Course course = null;
		
		try{
			con=ConnectionFactory.getConnection();			
			String sql = "select * from course where num=?";
			pst = con.prepareStatement(sql);
			pst.setString(1, num);
			rs = pst.executeQuery();
			if(rs.next()){
				course = new Course();
				course.setId(rs.getInt("id"));
				course.setNum(rs.getString("num"));
				course.setCourseName(rs.getString("name"));
				course.setCredit(rs.getInt("credit"));
			}
		}catch(SQLException e){
				e.printStackTrace();
		}
		return course;
	}
}
