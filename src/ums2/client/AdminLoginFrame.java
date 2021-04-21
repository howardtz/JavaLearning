package ums2.client;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import ums2.entity.Administrator;
import ums2.entity.Role;
import ums2.service.AdminService;
import ums2.service.RoleService;

public class AdminLoginFrame extends JFrame{
	private static final long serialVersionUID = 1L;
	private JLabel labelUserName = new JLabel("用户名     ");
	private JTextField txtUserName = new JTextField(15);
	
	private JLabel labelPwd = new JLabel("密码         ");
	private JPasswordField txtPwd = new JPasswordField(15);
	
	private JButton buttonLogin = new JButton("登录");
	private JButton buttonExit = new JButton("退出");
	
	public static void main(String[] args) throws IOException{
		new AdminLoginFrame("登录").showMe();
	}
	
	//设置布局	
	private void init(){	
		JPanel pUser=new JPanel();  
		JPanel pPwd=new JPanel();  
		JPanel pButton = new JPanel(); 
		JPanel panel =new JPanel(); 	
		
		pUser.add(labelUserName);	
		pUser.add(txtUserName);	
		
		//用户名
		pPwd.add(labelPwd);	
		pPwd.add(txtPwd);
		
		//按钮
		pButton.add(buttonLogin);	
		pButton.add(buttonExit);	

		//添加至总面板
		panel.add(pUser);
		panel.add(pPwd);		
		panel.add(pButton);
		
		this.add(panel);
	}
	
	public AdminLoginFrame(String msg) {		
		super(msg);		
	}
	
	public void showMe(){
		this.init();  //设置窗口布局	
		addEventHandler();		
		this.setBounds(500,300,300,160);
		this.validate();
		setVisible(true);
	}	
	
	public void addEventHandler(){
		buttonLogin.addActionListener(new ButtonLoginHandler());	
		buttonExit.addActionListener(new ButtonExitHandler());
	}
	
	private class ButtonLoginHandler implements ActionListener{  //保存按钮的事件监听器
		public void actionPerformed(ActionEvent e){
			AdminService service = new AdminService();
			RoleService roleService = new RoleService();
			
			//1.用户名
			String userName = txtUserName.getText();
			
			//2.密码
			String pwd = txtPwd.getText();
			
			if(userName.length()==0 ){
				JOptionPane.showMessageDialog( null, "请输入用户名", "提示" ,JOptionPane.PLAIN_MESSAGE );
				txtUserName.grabFocus();
			}else if(pwd.length()==0 ){
				JOptionPane.showMessageDialog( null, "请输入密码", "提示" ,JOptionPane.PLAIN_MESSAGE );
				txtUserName.grabFocus();
			}else{
				Administrator admin = service.login(userName, pwd);
				if(admin!=null){//登录成功
					int rid = admin.getRid();
					Role role = roleService.find(rid);
					String[] rights = role.getRights().split(" ");
					Menu menu = new Menu();
					menu.showMe();
					//设置哪些菜单可用，哪些菜单不可用
					menu.setRights(rights);
				}
			}
		}			
	}
	
	private class ButtonExitHandler implements ActionListener{
		public void actionPerformed(ActionEvent arg0) {
			dispose();			
		}		
	}
}
