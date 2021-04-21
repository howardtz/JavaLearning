package ums2.client;

import java.awt.BorderLayout;


import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;

import javax.swing.JScrollPane;
import javax.swing.JTable;

import ums2.util.PaginationUtil;
import ums2.util.AdminTableModel;

public class ShowAdminTableDialog extends JDialog{	
	private static final long serialVersionUID = 1L;
	private int windowHeight = 180;  //窗口高度		
	private int windowWidth = 300;   //窗口宽度	
	private JTable table = new JTable();	
	private PaginationUtil pagesUtil;  //计算分页的工具类对象
	private List list;	//用户列表
	
	private JLabel pageMessage = new JLabel();
	private JButton previous = new JButton("上一页");
	private JButton next = new JButton("下一页");

	public ShowAdminTableDialog(JFrame parent, String msg, List list){
		super(parent, "管理员列表",true);
		this.list = list;
		pagesUtil = new PaginationUtil(list, 2);
	}	
	
	//设置布局	
	private void init(){
		//总面板
		Panel panel = new Panel(new BorderLayout());
		
		//1.添加表格部分
		//(1)显示第一页
		showTableData(1);	
		//(2)将JTable加入滚动条面板
		JScrollPane pane = new JScrollPane(table);
		//(3)将滚动条面板加入总面板
		panel.add(pane,BorderLayout.CENTER);
		
		//2.添加按钮部分
		Panel southPanel = new Panel();
		southPanel.add(pageMessage);
		southPanel.add(previous);
		southPanel.add(next);
		panel.add(southPanel, BorderLayout.SOUTH);
		
		this.add(panel);
	}
	
	//显示指定页，控制按钮是否可用
	private void showTableData(int pageNo){
		//1.获取pageNo页对应的记录集合
		List currentPageUsers = pagesUtil.getCurrentPageRecords(pageNo);
		//2.利用currentPageUsers创建TableModel
		AdminTableModel model = new AdminTableModel(currentPageUsers);
		//3.为JTable设置TableModel：指定行、列信息
		table.setModel(model);		
		
		//4.设置JTable的列宽
		table.getColumn("ID").setPreferredWidth(30);
		table.getColumn("用户名").setPreferredWidth(100);
		table.getColumn("角色名").setPreferredWidth(100);
		
		//5.关闭JTable的自动调整功能
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		
		//6.根据当前页的情况设置"上一页"、"下一页"按钮是否可用
		int currentPage = pagesUtil.getCurrentPage();
		int totalPage =pagesUtil.getTotalPage();
		if(currentPage==1){
			previous.setEnabled(false);
		}
		if(currentPage==totalPage){
			next.setEnabled(false);
		}
		pageMessage.setText("第"+currentPage+"页 共"+totalPage+"页  ");
	}
	
	public void showMe(JFrame parent){		
		this.init();  //设置窗口布局	
		addEventHandler();		
		setPosition(parent);
		setVisible(true);
		validate();
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
	}
	
	public void addEventHandler(){
		next.addActionListener(new ButtonNextHandler());
		previous.addActionListener(new ButtonPreviousHandler());
	}
	
	private class ButtonNextHandler implements ActionListener{  //下一页
		public void actionPerformed(ActionEvent e){			
			pagesUtil.setCurrentPage(pagesUtil.getNextPage());
			previous.setEnabled(true);
			showTableData(pagesUtil.getCurrentPage());			
		}
	}
	
	private class ButtonPreviousHandler implements ActionListener{  //上一页
		public void actionPerformed(ActionEvent e){
			pagesUtil.setCurrentPage(pagesUtil.getPrePage());
			next.setEnabled(true);
			showTableData(pagesUtil.getCurrentPage());
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
