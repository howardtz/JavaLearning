package ums2.dao;

import java.sql.Connection;
import java.util.List;


import ums2.entity.Role;

public interface RoleDao {
	public void insert(Role role);
	public void deleteById(int id);
	public void deleteById(Connection con, int id);
	public void update(int id, Role role);	

	public Role selectById(int id);
	public Role selectByName(String rname);
	public List<Role> selectAll();
}