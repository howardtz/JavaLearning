package ums2.client;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Iterator;
import java.util.List;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import ums2.entity.User;
import ums2.service.UserService;
import ums2.entity.Administrator;
import ums2.entity.Role;
import ums2.service.AdminService;
import ums2.service.RoleService;

public class RegisteAdminDialog extends JDialog{
	private JLabel labelUserName = new JLabel("用户名     ");
	private JTextField txtUserName = new JTextField(15);
	
	private JLabel labelPwd = new JLabel("密码         ");
	private JPasswordField txtPwd = new JPasswordField(15);
	
	private JLabel labelRoleAssign = new JLabel("角色分配");
	private JLabel[] labelRoles;
	private JRadioButton[] roleButtons;
	private List<Role> roles;
	
	private JButton buttonRegiste = new JButton("注册");
	private JButton buttonExit = new JButton("退出");
	
	private int windowHeight = 230;  //窗口高度		
	private int windowWidth = 300;   //窗口宽度
	
	
	//设置布局	
	private void init(){	
		JPanel pUser=new JPanel();  
		JPanel pPwd=new JPanel();  
		JPanel pRole=new JPanel();  
		JPanel pButton = new JPanel(); 
		JPanel panel =new JPanel(); 	
		
		//用户名
		pUser.add(labelUserName);	
		pUser.add(txtUserName);	
		
		//密码
		pPwd.add(labelPwd);	
		pPwd.add(txtPwd);
		
		//角色分配
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
		
		//按钮
		pButton.add(buttonRegiste);	
		pButton.add(buttonExit);	

		//添加至总面板
		panel.add(pUser);
		panel.add(pPwd);
		panel.add(pRole);
		panel.add(pButton);
		
		this.add(panel);
	}
	
	public RegisteAdminDialog(JFrame parent, String msg) {		
		super(parent,msg, true);		
	}
	
	public void showMe(JFrame parent){
		this.init();  //设置窗口布局	
		addEventHandler();		
		setPosition(parent);
		this.validate();
		setVisible(true);
	}	
	
	public void addEventHandler(){
		buttonRegiste.addActionListener(new ButtonAddHandler());	
		buttonExit.addActionListener(new ButtonExitHandler());
	}
	
	private class ButtonAddHandler implements ActionListener{  //保存按钮的事件监听器
		public void actionPerformed(ActionEvent e){
			AdminService service = new AdminService();
			
			//1.用户名
			String userName = txtUserName.getText();
			
			//2.密码
			String pwd = txtPwd.getText();
			
			//3.角色
			int i;
			for(i=0; i<roleButtons.length; i++){
				if(roleButtons[i].isSelected())
					break;
			}
			
			if(userName.length()==0 ){
				JOptionPane.showMessageDialog( null, "请输入用户名", "提示" ,JOptionPane.PLAIN_MESSAGE );
				txtUserName.grabFocus();
			}else if(pwd.length()==0) { 
				JOptionPane.showMessageDialog( null, "请填写密码", "提示" ,JOptionPane.PLAIN_MESSAGE );
				txtUserName.grabFocus();
			}else{
				if(service.find(userName)){  //邮箱已注册
					JOptionPane.showMessageDialog( null, "该用户已存在", "提示" ,JOptionPane.PLAIN_MESSAGE );
					txtUserName.setText("");	
					txtUserName.grabFocus();
				}else{
					Administrator admin = new Administrator(userName, pwd, roles.get(i).getId());
					service.add(admin);					
					clear();										
				}
			}
		}
	}
	private class ButtonExitHandler implements ActionListener{
		public void actionPerformed(ActionEvent arg0) {
			dispose();			
		}		
	}
	
	/**
	 * 清空组件
	 */
	private void clear(){
		txtUserName.setText("");	
		txtPwd.setText("");
		
		for(int i=0; i<roleButtons.length; i++)	{
			roleButtons[i].setSelected(false);	
		}
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
}
