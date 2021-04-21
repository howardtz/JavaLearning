package ums2.dao.impl;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import ums2.util.ConnectionFactory;

import ums2.dao.RoleDao;
import ums2.entity.Role;

public class RoleDaoImpl implements RoleDao {
	public void insert(Role role) {
		Connection con = null;
		PreparedStatement pst = null;
		try {
			con=ConnectionFactory.getConnection();
			String sql = "insert into role(rname, rdesc, rights) values(?,?,?)";
			pst = con.prepareStatement(sql);
			pst.setString(1, role.getRname());
			pst.setString(2, role.getRdesc());
			pst.setString(3, role.getRights());
			pst.executeUpdate();			
		} catch (SQLException e) {			
			e.printStackTrace();
		}finally{
			if(pst!=null) try{pst.close();}catch (SQLException e) {e.printStackTrace();}
			if(con!=null) try{con.close();}catch (SQLException e) {e.printStackTrace();}
		}	
		
	}

	public Role selectById(int id) {
		Connection con = null;
		Statement st= null;
		ResultSet rs=null;
		Role role = null;
		try{
			con=ConnectionFactory.getConnection();
			st = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
			String sql="select * from role where id="+id;
			rs = st.executeQuery(sql);
			
			if(rs.next()){			
				role = new Role(); 
				role.setId(rs.getInt("id"));
				role.setRname(rs.getString("rname"));
				role.setRdesc(rs.getString("rdesc"));
				role.setRights(rs.getString("rights"));
			}			
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			if(rs!=null)try{rs.close();}catch (SQLException e) {e.printStackTrace();}
			if(st!=null) try{st.close();}catch (SQLException e) {e.printStackTrace();}			
			if(con!=null) try{con.close();}catch (SQLException e) {e.printStackTrace();}
		}
		return role;
	}

	public Role selectByName(String rname) {
		Connection con = null;
		PreparedStatement pst= null;
		ResultSet rs=null;
		Role role = null;
		
		try{
			con=ConnectionFactory.getConnection();
			String sql="select * from role where rname=?";
			pst = con.prepareStatement(sql);
			pst.setString(1,rname);
			rs = pst.executeQuery();			
			if(rs.next()){
				role = new Role(); 
				role.setId(rs.getInt("id"));
				role.setRname(rs.getString("rname"));
				role.setRdesc(rs.getString("rdesc"));
				role.setRights(rs.getString("rights"));
			}
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			if(rs!=null)try{rs.close();}catch (SQLException e) {e.printStackTrace();}
			if(pst!=null) try{pst.close();}catch (SQLException e) {e.printStackTrace();}			
			if(con!=null) try{con.close();}catch (SQLException e) {e.printStackTrace();}
		}
		return role;
	}
	
	public List<Role> selectAll() {
		List list = new ArrayList();
		Connection con = null;
		Statement st=null;
		ResultSet rs=null;
		
		try{
			con=ConnectionFactory.getConnection();
			st = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
			String sql="select * from role";
			rs = st.executeQuery(sql);
			
			while(rs.next()){
				Role role = new Role(); 
				role.setId(rs.getInt("id"));
				role.setRname(rs.getString("rname"));
				role.setRdesc(rs.getString("rdesc"));
				role.setRights(rs.getString("rights"));
				list.add(role);
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

	public void update(int id, Role role) {
		Connection con = null;
		PreparedStatement pst=null;
		
		String sql="update role set rname=?, rdesc=? where id="+ id;
		try{
			con=ConnectionFactory.getConnection();
			pst=con.prepareStatement(sql);
			pst.setString(1, role.getRname());
			pst.setString(2, role.getRdesc());
			pst.executeUpdate();
			
		}catch(SQLException e) {			
			e.printStackTrace();
		}finally{
			if(pst!=null) try{pst.close();}catch (SQLException e) {e.printStackTrace();}
			if(con!=null) try{con.close();}catch (SQLException e) {e.printStackTrace();}
		}	
	}
		
	public void deleteById(int id) {
		Connection con = null;
		Statement st=null;
		
		try{
			con=ConnectionFactory.getConnection();
			st = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
			String sql="delete from role where id="+id;
			st.executeUpdate(sql);			
			
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			if(st!=null) try{st.close();}catch (SQLException e) {e.printStackTrace();}			
			if(con!=null) try{con.close();}catch (SQLException e) {e.printStackTrace();}
		}
	}

	public void deleteById(Connection con, int id) {
		Statement st=null;
		
		try{
			st = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
			String sql="delete from role where id="+id;
			st.executeUpdate(sql);			
			
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			if(st!=null) try{st.close();}catch (SQLException e) {e.printStackTrace();}			
		}
	}
}














