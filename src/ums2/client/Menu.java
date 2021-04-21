package ums2.client;

import java.awt.Dimension;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JToolBar;
import javax.swing.KeyStroke;

import ums2.entity.User;
import ums2.service.UserService;
import ums2.dao.RoleDao;
import ums2.dao.impl.RoleDaoImpl;
import ums2.entity.Administrator;
import ums2.service.AdminService;


public class Menu extends JFrame{
	private static final long serialVersionUID = 2654995894454167911L;
	private JMenuItem jmiRegiste, jmiModify, jmiRemove;
	private JMenuItem jmiAddRole,jmiRemoveRole,jmiAssignRole, jmiRoleList,jmiAdminList;
	private JMenuItem jmiSearchByEmail,jmiSearchAll;
	private JButton btnRegiste, btnEdit, btnRemove, btnSearch;
	
	public static void main(String[] args) throws IOException{
		new Menu().showMe();
	}	  
	
	public Menu(){
	}
	
	private void createMenuBar(){
		//创建菜单栏
		JMenuBar  menuBar = new  JMenuBar();
		//创建菜单
		JMenu registeMenu = new JMenu("注册(N)");
		//设置热键
		registeMenu.setMnemonic(KeyEvent.VK_N);
		//创建、并向菜单添加菜单项
		registeMenu.add(jmiRegiste = new JMenuItem("注册用户"));
		//设置快捷键
		jmiRegiste.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N,ActionEvent.CTRL_MASK));
		//将菜单添加到菜单栏
		menuBar.add(registeMenu);
		
		JMenu editmenu = new JMenu("编辑用户信息(E)");
		editmenu.setMnemonic(KeyEvent.VK_E);
		menuBar.add(editmenu);
		editmenu.add(jmiModify = new JMenuItem("修改用户信息(M)",'M'));
		editmenu.add(jmiRemove = new JMenuItem("删除用户信息(R)",'R'));

		JMenu searchmenu = new JMenu("查询用户信息(S)");
		searchmenu.setMnemonic(KeyEvent.VK_S);
		menuBar.add(searchmenu);
		searchmenu.add(jmiSearchByEmail = new JMenuItem("按email查询(N)",'N'));
		searchmenu.add(jmiSearchAll = new JMenuItem("浏览所有用户(H)",'H'));
		
		JMenu rightmenu = new JMenu("权限管理(L)");
		menuBar.add(rightmenu);
		rightmenu.setMnemonic(KeyEvent.VK_L);
		rightmenu.add(jmiAddRole = new JMenuItem("增加角色(N)",'N'));
		rightmenu.add(jmiRemoveRole = new JMenuItem("删除角色(R)",'R'));
		rightmenu.add(jmiRoleList = new JMenuItem("浏览角色(S)",'S'));
		rightmenu.add(jmiAssignRole = new JMenuItem("为管理员分配角色(A)",'A'));
		rightmenu.add(jmiAdminList = new JMenuItem("浏览管理员(L)",'L'));		
		
		this.setJMenuBar(menuBar);
	}
	
	private void createToolBar(){
		JToolBar toolBar = new JToolBar();  //创建工具栏		
		
		btnRegiste = new JButton("", new ImageIcon(this.getClass().getResource("../ico/add.gif")));
		btnRegiste.setToolTipText("注册用户");
		btnEdit = new JButton("", new ImageIcon(this.getClass().getResource("../ico/modify.gif")));
		btnEdit.setToolTipText("修改用户信息");
		btnRemove = new JButton("", new ImageIcon(this.getClass().getResource("../ico/remove.gif")));
		btnRemove.setToolTipText("删除用户信息");
		btnSearch = new JButton("", new ImageIcon(this.getClass().getResource("../ico/search.gif")));
		btnSearch.setToolTipText("浏览所有用户");
		
		toolBar.add(btnRegiste);  
		toolBar.add(btnEdit); 
		toolBar.add(btnRemove);
		toolBar.add(btnSearch);
		
		//将工具栏添加至JFrame，this为当前窗口对象
		this.add("North",toolBar);
	}
	private void init(){
		//1.创建菜单栏
		createMenuBar();  
		
		//2.创建工具栏
		createToolBar();  			
		
		//3.按钮面板
		JPanel panel = new JPanel();
	    panel.setPreferredSize(new Dimension(800,400));
		this.add("Center",panel);
	}
	
	public void showMe(){
		//设置窗口对象
		this.init();
		this.addEventHandler();
		this.setTitle("用户管理系统");
		this.setBounds(300,200,800,400);
		this.pack();
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public void addEventHandler(){
		jmiRegiste.addActionListener(new RegisteHandler());
		jmiModify.addActionListener(new ModifyHandler());
		jmiRemove.addActionListener(new RemoveHandler());
		jmiSearchByEmail.addActionListener(new SearchByEmailHandler());
		jmiSearchAll.addActionListener(new SearchAllHandler());
		
		btnRegiste.addActionListener(new RegisteHandler());
		btnEdit.addActionListener(new ModifyHandler());
		btnRemove.addActionListener(new RemoveHandler());
		btnSearch.addActionListener(new SearchAllHandler());
		
		jmiAddRole.addActionListener(new RoleHandler());
		jmiRemoveRole.addActionListener(new RemoveRoleHandler());
		jmiRoleList.addActionListener(new RoleListHandler());
		jmiAssignRole.addActionListener(new RoleAssignHandler());
		jmiAdminList.addActionListener(new AdminListHandler());
	}
	private class AdminListHandler implements ActionListener{//注册
		public void actionPerformed(ActionEvent events) {
			AdminService adminService = new AdminService();
			List<Administrator> admins = adminService.find();
			new ShowAdminTableDialog(Menu.this, "浏览管理员", admins).showMe(Menu.this);
		}  		
	}
	
	private class RemoveRoleHandler implements ActionListener{//注册
		public void actionPerformed(ActionEvent events) {
			new DeleteRoleDialog(Menu.this, "删除角色").showMe(Menu.this);
		}  		
	}
	
	private class RegisteHandler implements ActionListener{//注册
		public void actionPerformed(ActionEvent events) {
			new RegisteUserDialog(Menu.this, "注册用户").showMe(Menu.this);
		}  		
	}

	private class ModifyHandler implements ActionListener{//修改
		public void actionPerformed(ActionEvent events) {
			new ModifyUserDialog(Menu.this, "修改用户信息").showMe(Menu.this);
		}  		
	}
	private class RemoveHandler implements ActionListener{//删除
		public void actionPerformed(ActionEvent events) {
			new DeleteUserDialog(Menu.this,"删除用户信息").showMe(Menu.this);
		}  		
	}
	
	private class SearchByEmailHandler implements ActionListener{//按email查询
		public void actionPerformed(ActionEvent events) {
			new SearchUserDialog(Menu.this,"按email查询").showMe(Menu.this);
		}  		
	}

	private class SearchAllHandler implements ActionListener{  //查询所有用户
		public void actionPerformed(ActionEvent e){
			UserService service = new UserService();
			List<User> users = service.find();
			if(users.size()!=0){
				new ShowUserTableDialog(Menu.this, "查询结果", users).showMe(Menu.this);  //显示所有用户				
			}else{
				JOptionPane.showMessageDialog( null, "没有用户的信息", "提示" ,JOptionPane.PLAIN_MESSAGE );
			}			
		}
	}
	
	private class RoleHandler implements ActionListener{//注册
		public void actionPerformed(ActionEvent events) {
			new AddRoleDialog(Menu.this, "角色设置").showMe(Menu.this);
		}  		
	}
	
	private class RoleAssignHandler implements ActionListener{//注册
		public void actionPerformed(ActionEvent events) {
			new RegisteAdminDialog(Menu.this, "注册管理员用户").showMe(Menu.this);
		}  		
	}
	
	private class RoleListHandler implements ActionListener{//注册
		public void actionPerformed(ActionEvent events) {
			RoleDao roleDao = new RoleDaoImpl();
			List roles = roleDao.selectAll();
			
			new ShowRoleTableDialog(Menu.this, "角色列表",roles).showMe(Menu.this);
		}  		
	}
	
	public void setRights(String[] rights){		
		//查找权限中没有哪个
		String r = "增删改查";
		for(int i=0; i<rights.length; i++){
			if (r.indexOf(rights[i])!=-1){
				r=r.replace(rights[i], "");
			}				
		}
//		System.out.println(r);
		for(int i=0; i<r.length(); i++){//将没有的权限的菜单项置为不可用
			switch(r.charAt(i)){
				case '增':
					btnRegiste.setEnabled(false);
					jmiRegiste.setEnabled(false);	
					jmiAssignRole.setEnabled(false);
					jmiAddRole.setEnabled(false);
					break;
				case '删':
					jmiRemove.setEnabled(false);
					btnRemove.setEnabled(false);
					jmiRemoveRole.setEnabled(false);
					break;
				case '改':
//					jmiModify.setEnabled(false);	
//					btnEdit.setEnabled(false);
					break;
				case '查':
					jmiRoleList.setEnabled(false);
					jmiSearchByEmail.setEnabled(false);
					jmiSearchAll.setEnabled(false);
					btnSearch.setEnabled(false);
					jmiAdminList.setEnabled(false);
					break;
			}
		}
	}
}