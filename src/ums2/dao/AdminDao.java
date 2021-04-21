
package ums2.dao;

import java.sql.Connection;
import java.util.List;

import ums2.entity.Administrator;

public interface AdminDao {	
	public void insert(Administrator admin);
	public void deleteById(int id);
	public void deleteByRid(int rid);
	public void deleteByRid(Connection con, int rid);
	public void update(int id, Administrator admin);

	public Administrator select(int id);
	public boolean selectByName(String name);
	public Administrator select(String username, String password);  //π‹¿Ì‘±µ«¬º
	public boolean select(Administrator admin);
	public List<Administrator> select();	
	

}