package ex.sim.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import ex.sim.dao.StudentCourseDao;
import ex.sim.entity.StudentCourse;
import ex.sim.util.ConnectionFactory;
//import chap13.util.ResourceUtil;

public class StudentCourseDaoImpl implements StudentCourseDao{
	
	public void insert(StudentCourse cs){
		Connection con = null;
    	PreparedStatement pst = null;
    	try {
			con=ConnectionFactory.getConnection();
			String sql = "insert into studentCourse values(NULL,?,?)";
			pst  = con.prepareStatement(sql);
			pst.setString(1, cs.getsNum());
			pst.setString(2, cs.getcNum());
			pst.executeUpdate();			
    	}catch(SQLException e){
    		e.printStackTrace();
		}
	}

	public boolean select(StudentCourse sc) {
		Connection con = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		try{
			con=ConnectionFactory.getConnection();
			String sql = "select * from studentCourse where snum=? and cnum=?";
			pst = con.prepareStatement(sql);
			pst.setString(1, sc.getsNum());
			pst.setString(2, sc.getcNum());
			rs = pst.executeQuery();
			if(rs.next()){
				return true;
			}
		}catch(SQLException e){
				e.printStackTrace();
		}
		return false;
	}

	public List selectByCourseNum(String courseNum) {
		Connection con = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		List list = new ArrayList();
		try{
			con=ConnectionFactory.getConnection();
			String sql = "select * from studentCourse where cnum=?";
			pst = con.prepareStatement(sql);
			pst.setString(1, courseNum);
			rs = pst.executeQuery();
			while(rs.next()){
				StudentCourse sc = new StudentCourse();
				sc.setId(rs.getInt("id"));
				sc.setsNum(rs.getString("snum"));
				sc.setcNum(rs.getString("cnum"));
				list.add(sc);
			}
		}catch(SQLException e){
				e.printStackTrace();
		}
		return list;
	}

	public List selectByStuNum(String stuNum) {
		Connection con = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		List list = new ArrayList();
		try{
			con=ConnectionFactory.getConnection();
			String sql = "select * from studentCourse where snum=?";
			pst = con.prepareStatement(sql);
			pst.setString(1, stuNum);
			rs = pst.executeQuery();
			while(rs.next()){
				StudentCourse sc = new StudentCourse();
				sc.setId(rs.getInt("id"));
				sc.setsNum(rs.getString("snum"));
				sc.setcNum(rs.getString("cnum"));
				list.add(sc);
			}
		}catch(SQLException e){
				e.printStackTrace();
		}
		return list;
	}

}
