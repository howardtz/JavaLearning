package ums2.client;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import ums2.entity.Role;
import ums2.service.RoleService;

public class AddRoleDialog extends JDialog{
	private JLabel labelRole = new JLabel("角色名     ");
	private JTextField txtRoleName = new JTextField(15);
	
	private JLabel labelDesc = new JLabel("角色描述");
	private JTextField txtDesc = new JTextField(15);
	
	private JLabel labelRight = new JLabel("权限     ");
	private String[] strRights={"增","删","改","查"};
	private JCheckBox rights[] = new JCheckBox[4];
	private JLabel labelRights[] = new JLabel[4];
	
	private JButton buttonAdd = new JButton("添加");
	private JButton buttonExit = new JButton("退出");
	
	private int windowHeight = 200;  //窗口高度		
	private int windowWidth = 300;   //窗口宽度
	
	//设置布局	
	private void init(){	
		JPanel pRole=new JPanel();  
		JPanel pDesc=new JPanel();  
		JPanel pRights=new JPanel();  
		JPanel pButton = new JPanel(); 
		JPanel panel =new JPanel(); 	
		
		//角色名
		pRole.add(labelRole);	
		pRole.add(txtRoleName);	
		
		//角色描述
		pDesc.add(labelDesc);	
		pDesc.add(txtDesc);
		
		//权限
		pRights.add(labelRight);  
		for (int i=0; i<rights.length; i++){
			rights[i] = new JCheckBox();	
			labelRights[i]=new JLabel();
			labelRights[i].setText(strRights[i]);			
			pRights.add(rights[i]);
			pRights.add(labelRights[i]);
		}
		
		//按钮
		pButton.add(buttonAdd);	
		pButton.add(buttonExit);	

		//添加至总面板
		panel.add(pRole);
		panel.add(pDesc);
		panel.add(pRights);
		panel.add(pButton);
		
		this.add(panel);
	}
	
	public AddRoleDialog(JFrame parent, String msg) {		
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
		buttonAdd.addActionListener(new ButtonAddHandler());	
		buttonExit.addActionListener(new ButtonExitHandler());
	}
	
	private class ButtonAddHandler implements ActionListener{  //保存按钮的事件监听器
		public void actionPerformed(ActionEvent e){
			RoleService service = new RoleService();
			
			//1.角色名
			String roleName = txtRoleName.getText();
			
			//2.描述
			String desc = txtDesc.getText();
			
			//3.权限
			String rights = getRightsInfo();			
			
			if(roleName.length()==0 ){
				JOptionPane.showMessageDialog( null, "请输入角色名", "提示" ,JOptionPane.PLAIN_MESSAGE );
				txtRoleName.grabFocus();
			}else{
				if(service.find(roleName)!=null){  //角色名已存在
					JOptionPane.showMessageDialog( null, "该角色已存在", "提示" ,JOptionPane.PLAIN_MESSAGE );
					txtRoleName.setText("");	
					txtRoleName.grabFocus();
				}else{
					Role role = new Role(roleName, desc, rights);
					service.add(role);					
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
		txtRoleName.setText("");	
		txtDesc.setText("");
		
		for(int i=0; i<rights.length; i++)	{
			rights[i].setSelected(false);	
		}
	}
	
	/**
	 * getInfo():获取权限信息
	 * */	
	private String getRightsInfo(){  
		StringBuilder strRights = new StringBuilder();
		for(int i=0; i<rights.length; i++){
			if(rights[i].isSelected()){  //选择了该权限
				String rightText = labelRights[i].getText(); //提取其对应的标签文本
				strRights.append("  "+rightText+"  ");  
			}
		}		
		return strRights.toString();
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
