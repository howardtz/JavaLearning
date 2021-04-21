package ums2.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import ums2.util.ConnectionFactory;

import ums2.dao.AdminDao;
import ums2.entity.Administrator;


public class AdminDaoImpl implements AdminDao {

	public void insert(Administrator admin) {
		Connection con = null;
		PreparedStatement pst = null;
		
		try {
			con=ConnectionFactory.getConnection();
			String sql = "insert into admin(username,password, rid) values(?,?,?)";
			pst = con.prepareStatement(sql);
			pst.setString(1, admin.getUsername());
			pst.setString(2, admin.getPassword());
			pst.setInt(3,admin.getRid());   //默认角色为1，初级管理员
			pst.executeUpdate();
		} catch (SQLException e) {			
			e.printStackTrace();
		}finally{
			if(pst!=null) try{pst.close();}catch (SQLException e) {e.printStackTrace();}
			if(con!=null) try{con.close();}catch (SQLException e) {e.printStackTrace();}
		}	
	}

	public void deleteById(int id) {
		Connection con = null;
		Statement st = null;
		
		try {
			con=ConnectionFactory.getConnection();
			st= con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE ,ResultSet.CONCUR_UPDATABLE );
			String sql = "delete from admin where id=" + id;
			st.executeUpdate(sql);
		} catch (SQLException e) {			
			e.printStackTrace();
		}finally{
			if(st!=null) try{st.close();}catch (SQLException e) {e.printStackTrace();}
			if(con!=null) try{con.close();}catch (SQLException e) {e.printStackTrace();}
		}	
	}
	
	public void deleteByRid(int rid) {
		Connection con = null;
		Statement st = null;
		
		try {
			con=ConnectionFactory.getConnection();
			st= con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE ,ResultSet.CONCUR_UPDATABLE );
			String sql = "delete from admin where rid=" + rid;
			st.executeUpdate(sql);
		} catch (SQLException e) {			
			e.printStackTrace();
		}finally{
			if(st!=null) try{st.close();}catch (SQLException e) {e.printStackTrace();}
			if(con!=null) try{con.close();}catch (SQLException e) {e.printStackTrace();}
		}	
	}
	
	public void update(int id, Administrator admin) {
		Connection con = null;
		PreparedStatement pst=null;
		
		try{
			con=ConnectionFactory.getConnection();
			String sql="update admin set username=?, password=? where id="+ id;
			pst=con.prepareStatement(sql);
			pst.setString(1, admin.getUsername());
			pst.setString(2, admin.getPassword());
			pst.executeUpdate();
		}catch(SQLException e) {			
			e.printStackTrace();
		}finally{
			if(pst!=null) try{pst.close();}catch (SQLException e) {e.printStackTrace();}
			if(con!=null) try{con.close();}catch (SQLException e) {e.printStackTrace();}
		}	
	}

	public Administrator select(int id) {
		Connection con = null;
		Statement st= null;
		ResultSet rs=null;
		Administrator admin = null;
		
		try{
			con=ConnectionFactory.getConnection();
			String sql="select * from admin where id="+id;
			st = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
			rs = st.executeQuery(sql);
			
			if(rs.next()){			
				admin = new Administrator();
				admin.setId(id);
				admin.setUsername(rs.getString("username"));
				admin.setPassword(rs.getString("password"));
				admin.setRid(rs.getInt("rid"));
			}			
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			if(rs!=null)try{rs.close();}catch (SQLException e) {e.printStackTrace();}
			if(st!=null) try{st.close();}catch (SQLException e) {e.printStackTrace();}			
			if(con!=null) try{con.close();}catch (SQLException e) {e.printStackTrace();}
		}
		return admin;
	}

	public boolean selectByName(String name) {
		Connection con = null;
		PreparedStatement pst= null;
		ResultSet rs=null;

		try{
			con=ConnectionFactory.getConnection();
			String sql="select * from admin where username=?";
			pst = con.prepareStatement(sql);
			pst.setString(1,name);
			rs = pst.executeQuery();
			if(rs.next()){
				return true;
			}			
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			if(rs!=null)try{rs.close();}catch (SQLException e) {e.printStackTrace();}
			if(pst!=null) try{pst.close();}catch (SQLException e) {e.printStackTrace();}			
			if(con!=null) try{con.close();}catch (SQLException e) {e.printStackTrace();}
		}
		return false;
	}


	public Administrator select(String username, String password) {
		Connection con = null;
		PreparedStatement pst= null;
		ResultSet rs=null;
		Administrator  admin = null;
		
		try{
			con=ConnectionFactory.getConnection();
			String sql="select * from admin where username=? and password=?";
			pst = con.prepareStatement(sql);
			pst.setString(1,username);
			pst.setString(2,password);
			rs = pst.executeQuery();
			if(rs.next()){
				admin = new Administrator();
				admin.setId(rs.getInt("id"));
				admin.setUsername(rs.getString("username"));
				admin.setPassword(password);
				admin.setRid(rs.getInt("rid"));
			}		
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			if(rs!=null)try{rs.close();}catch (SQLException e) {e.printStackTrace();}
			if(pst!=null) try{pst.close();}catch (SQLException e) {e.printStackTrace();}			
			if(con!=null) try{con.close();}catch (SQLException e) {e.printStackTrace();}
		}
		return admin;
	}

	public List<Administrator> select() {			
		ArrayList list =  new ArrayList();
		Connection con = null;
		Statement st=null;
		ResultSet rs=null;
		
		try{
			con=ConnectionFactory.getConnection();
			st = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
			String sql="select * from admin";
			rs = st.executeQuery(sql);
			while(rs.next()){
				Administrator admin = new Administrator();  
				admin.setId(rs.getInt("id"));
				admin.setUsername(rs.getString("username"));
				admin.setPassword(rs.getString("password"));
				admin.setRid(rs.getInt("rid"));
				list.add(admin);
			}
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			if(rs!=null)try{rs.close();}catch (SQLException e) {e.printStackTrace();}
			if(st!=null) try{st.close();}catch (SQLException e) {e.printStackTrace();}			
			if(con!=null) try{con.close();}catch (SQLException e) {e.printStackTrace();}
		}
		return list;
	}

	public boolean select(Administrator admin) {
		Connection con = null;
		PreparedStatement pst= null;
		ResultSet rs=null;
		
		try{
			con=ConnectionFactory.getConnection();
			String sql="select * from admin where username= ? and password=?";
			pst = con.prepareStatement(sql);
			pst.setString(1, admin.getUsername());
			pst.setString(2,admin.getPassword());
			rs = pst.executeQuery();
			if(rs.next()){
				return true;
			}		
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			if(rs!=null)try{rs.close();}catch (SQLException e) {e.printStackTrace();}
			if(pst!=null) try{pst.close();}catch (SQLException e) {e.printStackTrace();}			
			if(con!=null) try{con.close();}catch (SQLException e) {e.printStackTrace();}
		}
		return false;
	}

	public void deleteByRid(Connection con, int rid) {
		Statement st = null;
		
		try {
			st= con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE ,ResultSet.CONCUR_UPDATABLE );
			String sql = "delete from admin where rid=" + rid;
			st.executeUpdate(sql);
		} catch (SQLException e) {			
			e.printStackTrace();
		}finally{
			if(st!=null) try{st.close();}catch (SQLException e) {e.printStackTrace();}
		}			
	}
}