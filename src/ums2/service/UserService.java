package ums2.service;

import java.util.List;


import ums2.dao.UserDao;
import ums2.dao.impl.UserDaoJdbcImpl;
import ums2.entity.User;
import ums2.util.DaoFactory;

public class UserService {
	private UserDao userDao;
	
	public UserService(){
//		userDao = new UserDaoJdbcImpl();
		userDao= (UserDao) DaoFactory.getDao("userDao");
	}
	
	public void add(User user) {
		userDao.insert(user);
	}

	public User find(String email) {
		return userDao.selectByEmail(email);
	}
	
	public List<User> find() {
		return userDao.selectAll();
	}

	public void modify(User user) {
		userDao.update(user);
	}

	public int remove(String email) {
		return userDao.delete(email);
	}

}
