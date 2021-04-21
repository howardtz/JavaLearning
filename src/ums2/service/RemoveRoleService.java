package ums2.service;

import java.sql.Connection;
import java.sql.SQLException;

import ums2.util.DaoFactory;
import ums2.dao.AdminDao;
import ums2.dao.RoleDao;
import ums2.dao.impl.AdminDaoImpl;
import ums2.dao.impl.RoleDaoImpl;

public class RemoveRoleService {
	private RoleDao roleDao;
	private AdminDao adminDao;
	
	public RemoveRoleService() {
		roleDao = new RoleDaoImpl();
		adminDao = new AdminDaoImpl();
//		roleDao= (RoleDao)DaoFactory.getDao("roleDao");
//		adminDao= (AdminDao) DaoFactory.getDao("adminDao");
	}
	
	public void removeRoleAndAdmin(Connection con, int id){
		
		try{
			con.setAutoCommit(false);
			roleDao.deleteById(con, id);
			adminDao.deleteByRid(con, id); //删除管理员表中的关联数据
			con.commit();
		}catch(Exception e){
			try{
				System.out.println("删除未成功");
				con.rollback();
			}catch(Exception e1){
				e1.printStackTrace();
			}
		} finally{
			if(con!=null) try {con.close();} catch (SQLException e) {}
		}		
	}

}
