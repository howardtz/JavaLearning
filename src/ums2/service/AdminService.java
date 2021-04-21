package ums2.service;

import java.util.List;

import ums2.util.DaoFactory;

import ums2.dao.AdminDao;
import ums2.dao.impl.AdminDaoImpl;
import ums2.entity.Administrator;

public class AdminService {
	private AdminDao adminDao;
	
	public AdminService(){
		adminDao = new AdminDaoImpl();
//		adminDao= (AdminDao) DaoFactory.getDao("adminDao");
	}
	
	public void add(Administrator admin) {
		adminDao.insert(admin);
	}

	public Administrator find(int id) {
		return adminDao.select(id);
	}
	
	public boolean find(String name) {
		return adminDao.selectByName(name);
	}
	
	public List<Administrator> find() {
		return adminDao.select();
	}


	public Administrator login(String userName, String pwd){
		return adminDao.select(userName, pwd);
	}
	
	public void removeByRid(int rid){
		adminDao.deleteByRid(rid);
	}
}
