package ums2.util;

import java.util.List;




import javax.swing.table.AbstractTableModel;

import ums2.entity.Role;

public class RoleTableModel extends AbstractTableModel{
	private List roles;
	
	public RoleTableModel(List roles){
		this.roles =roles;	
	}

	public int getColumnCount() {
		return 4;		
	}

	public int getRowCount() {
		return roles.size();
	}

	public Object getValueAt(int row, int col) {//按指定的行、列取出数据
		Role role = (Role)roles.get(row);
  	    switch(col){
			 case 0: return row+1+"";
			 case 1: return role.getRname();
			 case 2: return role.getRdesc();
			 case 3: return role.getRights();
		 }
		 return null;
	}

	public String getColumnName(int col) {
		switch(col){
		 case 0: return "序号";
		 case 1: return "角色名称";
		 case 2: return "描述"; 
		 case 3: return "权限"; 
		}
		return null;
	}	
}
