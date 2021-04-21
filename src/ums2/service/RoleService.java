package ums2.service;

import java.util.List;

import ums2.util.DaoFactory;
import ums2.dao.RoleDao;
import ums2.dao.impl.RoleDaoImpl;
import ums2.entity.Role;

public class RoleService {
	private RoleDao roleDao;
	
	public RoleService(){
		roleDao = new RoleDaoImpl();
//		roleDao= (RoleDao)DaoFactory.getDao("roleDao");
	}
	
	public void add(Role role) {
		roleDao.insert(role);
	}

	public Role find(int id) {
		return roleDao.selectById(id);
	}
	
	public Role find(String name) {
		return roleDao.selectByName(name);
	}
	
	public List<Role> find() {
		return roleDao.selectAll();
	}

	public void modify(int id, Role role){
		roleDao.update(id, role);
	}

	public void remove(int id){
		roleDao.deleteById(id);
	}
}
