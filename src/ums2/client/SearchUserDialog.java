package ums2.client;

import java.awt.Panel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import ums2.entity.User;
import ums2.service.UserService;


public class SearchUserDialog extends JDialog{
	/**
	 * 
	 */
	private static final long serialVersionUID = 5195978572456687280L;
	private JLabel labelName = new JLabel("email");
	private JTextField userEmail = new JTextField(20);		
	private JButton buttonSearch = new JButton("  查询    ");
	
	private int windowHeight = 120;  //窗口高度	
	private int windowWidth = 400;   //窗口宽度
	
	public SearchUserDialog(JFrame parent, String msg){
		super(parent, msg, true);			
	}	
	
	public void showMe(JFrame parent){		
		Panel pSouth=new Panel();
		pSouth.add(buttonSearch);
		
		Panel pCenter=new Panel();
		pCenter.add(labelName);
		pCenter.add(userEmail);
		pCenter.add(pSouth);		

		addEventHandler(parent);
		
		add(pCenter);

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
	
	public void addEventHandler(JFrame parent){
		buttonSearch.addActionListener(new ButtonSearchEmailHandler(parent));			
	}	
	
	private class ButtonSearchEmailHandler implements ActionListener{  //按email查询用户
		private JFrame parents;
		public ButtonSearchEmailHandler(JFrame parent){
			parents = parent;
		}
		
		public void actionPerformed(ActionEvent e){		
			UserService service = new UserService();
			
			if(userEmail.getText()==null || userEmail.getText().length()==0){
				JOptionPane.showMessageDialog( null, "请输入要查询用户的email", "提示" ,JOptionPane.PLAIN_MESSAGE );
			}else{
				User user = service.find(userEmail.getText());
				if(user!=null){
					//将查询到的用户添加到list,显示在表格中
					List<User> resList = new ArrayList<User>();
					resList.add(user);
					new ShowUserTableDialog(parents,"查询结果", resList);
				}else{
					JOptionPane.showMessageDialog( null, "该用户不存在", "提示" ,JOptionPane.PLAIN_MESSAGE );
				}	
			}
		}
	}
}
