package ums2.client;

import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import ums2.entity.User;
import ums2.service.UserService;

public class ModifyUserDialog extends JDialog{
	private static final long serialVersionUID = 1L;
	private JLabel labelEmail = new JLabel("用户email");
	private JTextField userEmail = new JTextField(20);
	private JButton buttonModify = new JButton("修改注册信息");
	
	private int windowHeight = 150;  //窗口高度	
	private int windowWidth = 400;   //窗口宽度
	
	public ModifyUserDialog(JFrame parent, String msg){
		super(parent, msg, true);
	}		
	
	public void showMe(JFrame parent){
		Panel pSouth=new Panel();
		pSouth.add(buttonModify);

		Panel pCenter=new Panel();
		pCenter.add(labelEmail);
		pCenter.add(userEmail);
		pCenter.add(pSouth);
		
		add(pCenter);
				
		buttonModify.addActionListener(new ButtonModifyHandler(parent));
		
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
	/**
	 * 
	 * 修改按钮的事件监听器
	 *
	 */
	private class ButtonModifyHandler implements ActionListener{
		private JFrame parents;
		public ButtonModifyHandler(JFrame parent){
			parents = parent;
		}
		
		public void actionPerformed(ActionEvent e){
			UserService service = new UserService();
			
			if(userEmail.getText()==null || userEmail.getText().length()==0){
				JOptionPane.showMessageDialog( null, "请输入要修改用户的email", "提示" ,JOptionPane.PLAIN_MESSAGE );
			}else{				
				User user  = service.find(userEmail.getText());
				if(user!=null){ 
					new RegisteUserDialog(parents, "修改注册信息", user);
					setVisible(false);
				}else{
					JOptionPane.showMessageDialog( null, "没有该用户", "提示" ,JOptionPane.PLAIN_MESSAGE );
				}
			}
		}
	}	
}	