package ums2.dao;

import java.util.List;

import ums2.entity.User;

public interface UserDao {
	public void insert(User user); 
	public int delete(String email);
	public void update(User user);
	public User selectByEmail(String email);
	public List<User> selectAll();
	
}
