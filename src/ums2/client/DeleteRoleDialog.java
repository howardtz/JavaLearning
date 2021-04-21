package ums2.client;

import
java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Panel;



import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.util.Iterator;
import java.util.List;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import ums2.service.UserService;
import ums2.entity.Role;
import ums2.service.AdminService;
import ums2.service.RemoveRoleService;
import ums2.service.RoleService;
import ums2.util.ConnectionFactory;

public class DeleteRoleDialog extends JDialog{
	private static final long serialVersionUID = 1L;
	
	private JLabel labelRoleAssign = new JLabel("角色名称");
	private JLabel[] labelRoles;
	private JRadioButton[] roleButtons;
	private List<Role> roles;
	
	private JButton buttonRemove = new JButton("删除角色");

	private int windowHeight = 140;  //窗口高度		
	private int windowWidth = 260;   //窗口宽度
	
	public DeleteRoleDialog(JFrame parent, String msg){
		super(parent,msg,true);
	}
	
	private void init(){				
		//角色列表
		JPanel pRole = new JPanel();
		pRole.add(labelRoleAssign);
		ButtonGroup rolesRadioGroup = new  ButtonGroup();
		RoleService service  = new RoleService();		
		roles = service.find();  //获取所有角色
		labelRoles = new JLabel[roles.size()];
		roleButtons = new JRadioButton[roles.size()];
		Iterator<Role> it  = roles.iterator();
		int i=0;
		JPanel p = new JPanel(new GridLayout(roles.size(),2));
		while(it.hasNext()){
			Role role  = it.next();
			labelRoles[i] = new JLabel(role.getRname());
			roleButtons[i] = new JRadioButton();
			rolesRadioGroup.add(roleButtons[i]);
			p.add(roleButtons[i]);
			p.add(labelRoles[i]);			
			i++;			
		}
		pRole.add(p);
		
		JPanel pCenter=new JPanel();
		Panel pSouth=new Panel();
		pSouth.add(buttonRemove);
		pCenter.add(pRole);
		pCenter.add(pSouth);	
		add(pCenter);
	}
	
	public void showMe(JFrame parent){
		init();		
		buttonRemove.addActionListener(new ButtonRemoveHandler());		
		//计算对话框的显示位置
		setPosition(parent);		
		setVisible(true);
		validate();		
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
	}
			
	private void setPosition(JFrame parent){
		//计算对话框的显示位置
		int parentX = parent.getX();
		int parentY = parent.getY();	
		int parentWidth = parent.getWidth();
		int parentHeight = parent.getHeight();
		int dialogX = parentX + (parentWidth-windowWidth)/2;
		int dialogY = parentY + (parentHeight-windowHeight)/2+40;
		this.setBounds(dialogX,dialogY,windowWidth,windowHeight);
	}
	
	private class ButtonRemoveHandler implements ActionListener{  //删除按钮的事件监听器
		public void actionPerformed(ActionEvent e){
			RoleService roleService = new RoleService();
//			AdminService adminService = new AdminService();
			Connection con = ConnectionFactory.getConnection();
			RemoveRoleService rrs = new RemoveRoleService();
			//角色
			int i;
			for(i=0; i<roleButtons.length; i++){
				if(roleButtons[i].isSelected())
					break;
			}
			int rid = roleService.find(labelRoles[i].getText()).getId();
//			roleService.remove(rid);	
//			adminService.removeByRid(rid); 
			rrs.removeRoleAndAdmin(con, rid);
			
			JOptionPane.showMessageDialog( null, "角色及其对应用户已删除", "提示" ,JOptionPane.PLAIN_MESSAGE );
			dispose();	
		}
	}
}