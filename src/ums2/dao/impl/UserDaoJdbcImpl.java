package ums2.dao.impl;

import java.sql.Connection;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import ums2.dao.UserDao;
import ums2.entity.User;

import ex.sim.util.ConnectionFactory;


public class UserDaoJdbcImpl implements UserDao{
	private Connection con;

	public UserDaoJdbcImpl() {
	}

	public int delete(String email) {
		PreparedStatement pst = null;
		try {
			con = ConnectionFactory.getConnection();
			String sql="delete from users where email= ?";
			pst = con.prepareStatement(sql);
			pst.setString(1, email);
			return pst.executeUpdate();
		 }catch (Exception e) {
			e.printStackTrace();
		 } finally {
			if(pst!=null) 	try {pst.close();} catch (SQLException e) {e.printStackTrace();}
			if(con!=null) 	try {con.close();} catch (SQLException e) {e.printStackTrace();}
		 }
		 return 0;
	}

	public void insert(User user) {
		PreparedStatement pst = null;
		try {		
			con = ConnectionFactory.getConnection();
			
			String sql = "insert into users values(?,?,?,?,?)";	
			pst = con.prepareStatement(sql);
			pst.setString(1, user.getId());
			pst.setString(2, user.getEmail());
			pst.setString(3, user.getUsername());
			pst.setString(4, user.getSex());
			pst.setString(5, user.getHobbies());	
			pst.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(pst!=null) 	try {pst.close();} catch (SQLException e) {e.printStackTrace();}
			if(con!=null) 	try {con.close();} catch (SQLException e) {e.printStackTrace();}
		}	
		
	}

	public List<User> selectAll() {
		PreparedStatement pst=null;		
		ResultSet rs = null;
		List<User> list= new ArrayList<User>();
		try {
			con = ConnectionFactory.getConnection();
			
			//获取查询条件
			String sql="select * from users";
			pst = con.prepareStatement(sql);
			rs=pst.executeQuery();			

			while(rs.next()){
				User user = new User();
				user.setId(rs.getString("id"));
				user.setEmail(rs.getString("email"));
				user.setUsername(rs.getString("username"));
				user.setSex(rs.getString("sex"));
				user.setHobbies(rs.getString("hobbies"));
				list.add(user);
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}finally{
			if(rs!=null) 	try {rs.close();} catch (SQLException e) {e.printStackTrace();}
			if(pst!=null) 	try {pst.close();} catch (SQLException e) {e.printStackTrace();}
			if(con!=null) 	try {con.close();} catch (SQLException e) {e.printStackTrace();}
		}
		return list;		
	}

	public User selectByEmail(String email) {
		PreparedStatement pst=null;		
		ResultSet rs = null;
		User user = null; 

		try{
			con = ConnectionFactory.getConnection();
			
			String sql = "select * from users where email =?";			
			pst = con.prepareStatement(sql);
			pst.setString(1, email);
			rs=pst.executeQuery();
			
			if(rs.next()){
				user = new User();
				user.setId(rs.getString("id"));
				user.setEmail(rs.getString("email"));
				user.setUsername(rs.getString("username"));
				user.setSex(rs.getString("sex"));
				user.setHobbies(rs.getString("hobbies"));
			}		
		}catch (SQLException e) {
			e.printStackTrace();
		}finally{
			if(rs!=null) 	try {rs.close();} catch (SQLException e) {e.printStackTrace();}
			if(pst!=null) 	try {pst.close();} catch (SQLException e) {e.printStackTrace();}
			if(con!=null) 	try {con.close();} catch (SQLException e) {e.printStackTrace();}
		}
		return user;	
	}

	public void update(User user) {
		PreparedStatement pst = null;
		
		try {		
			con = ConnectionFactory.getConnection();
			
			String sql="update users set email=?, sex=? , hobbies=?  where username= ?";
			pst = con.prepareStatement(sql);
			pst.setString(1, user.getEmail());
			pst.setString(2, user.getSex());
			pst.setString(3, user.getHobbies());
			pst.setString(4, user.getUsername());
			pst.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(pst!=null) 	try {pst.close();} catch (SQLException e) {e.printStackTrace();}
			if(con!=null) 	try {con.close();} catch (SQLException e) {e.printStackTrace();}
		}
	}
	
}
