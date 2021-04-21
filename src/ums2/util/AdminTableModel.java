package ums2.util;

import java.util.List;

import javax.swing.table.AbstractTableModel;
import ums2.entity.Administrator;
import ums2.service.RoleService;


public class AdminTableModel extends AbstractTableModel{
	private List<Administrator> admins;
	
	public AdminTableModel(List admins){
		this.admins =admins;	
	}

	public int getColumnCount() {
		return 3;		
	}

	public int getRowCount() {
		return admins.size();
	}

	public Object getValueAt(int row, int col) {  //按指定的行、列取出数据
		RoleService roleService = new RoleService();
		Administrator admin = (Administrator)admins.get(row);
  	    switch(col){
  	    	case 0: return row+1+"";
			case 1: return admin.getUsername();
			case 2: return roleService.find(admin.getRid()).getRname(); 
		 }
		 return null;
	}

	public String getColumnName(int col) {
		switch(col){
		 case 0: return "ID";
		 case 1: return "用户名"; 
		 case 2: return "角色名"; 
		}
		return null;
	}	
}
