package ex.ums.gui;

import ex.ums.dao.UserDao;
import ex.ums.dao.impl.UserDaoImplForList;
import ex.ums.entity.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.*;
import java.util.List;

public class Menu extends JFrame {
    private JMenuItem jmiRegister, jmiSave, jmiRead;
    private JMenuItem jmiModify, jmiRemove;
    private JMenuItem jmiAddRight, jmiRemoveRight, jmiAssignRight;
    private JMenuItem jmiSearchByEmail, jmiSearchAll;


    private JButton btnRegister, btnEdit, btnRemove, btnSearch;

    private UserDao userDao;

    public static void main(String[] args) throws IOException {
        new Menu().showMe();
    }

    public Menu() {
        userDao = new UserDaoImplForList();
    }

    private void createMenuBar() {
        //创建菜单栏
        JMenuBar menuBar = new JMenuBar();
        //创建菜单
        JMenu registerMenu = new JMenu("注册(N)");
        //设置热键
        registerMenu.setMnemonic(KeyEvent.VK_N);
        //创建、并向菜单添加菜单项
        registerMenu.add(jmiRegister = new JMenuItem("注册用户"));
        registerMenu.add(jmiSave = new JMenuItem("保存用户"));
        registerMenu.add(jmiRead = new JMenuItem("导入用户"));
        //设置快捷键
        jmiRegister.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, ActionEvent.CTRL_MASK));
        //将菜单添加到菜单栏
        menuBar.add(registerMenu);

        JMenu editMenu = new JMenu("编辑用户信息(E)");
        editMenu.setMnemonic(KeyEvent.VK_E);
        menuBar.add(editMenu);
        editMenu.add(jmiModify = new JMenuItem("修改用户信息(M)", 'M'));
        editMenu.add(jmiRemove = new JMenuItem("删除用户信息(R)", 'R'));

        JMenu searchMenu = new JMenu("查询用户信息(S)");
        searchMenu.setMnemonic(KeyEvent.VK_S);
        menuBar.add(searchMenu);
        searchMenu.add(jmiSearchByEmail = new JMenuItem("按email查询(N)", 'N'));
        searchMenu.add(jmiSearchAll = new JMenuItem("浏览所有用户(H)", 'H'));

        JMenu rightMenu = new JMenu("权限管理(L)");
        menuBar.add(rightMenu);
        rightMenu.setMnemonic(KeyEvent.VK_L);
        rightMenu.add(jmiAddRight = new JMenuItem("增加权限(N)", 'N'));
        rightMenu.add(jmiRemoveRight = new JMenuItem("删除权限(R)", 'R'));
        rightMenu.add(jmiAssignRight = new JMenuItem("为用户分配权限(A)", 'A'));

        this.setJMenuBar(menuBar);
    }

    private void createToolBar() {
        JToolBar toolBar = new JToolBar();  //创建工具栏

        btnRegister = new JButton("", new ImageIcon(this.getClass().getResource("../ico/add.gif")));
        btnRegister.setToolTipText("注册用户");
        btnEdit = new JButton("", new ImageIcon(this.getClass().getResource("../ico/modify.gif")));
        btnEdit.setToolTipText("修改用户信息");
        btnRemove = new JButton("", new ImageIcon(this.getClass().getResource("../ico/remove.gif")));
        btnRemove.setToolTipText("删除用户信息");
        btnSearch = new JButton("", new ImageIcon(this.getClass().getResource("../ico/search.gif")));
        btnSearch.setToolTipText("浏览所有用户");

        toolBar.add(btnRegister);
        toolBar.add(btnEdit);
        toolBar.add(btnRemove);
        toolBar.add(btnSearch);

        //将工具栏添加至JFrame，this为当前窗口对象
        this.add("North", toolBar);
    }

    private void init() {
        //1.创建菜单栏
        createMenuBar();

        //2.创建工具栏
        createToolBar();

        //3.按钮面板
        JPanel panel = new JPanel();
        panel.setPreferredSize(new Dimension(500, 300));
        this.add("Center", panel);
    }

    public void showMe() {
        //设置窗口对象
        this.init();
        this.addEventHandler();
        this.setTitle("用户管理系统");
        this.setBounds(300, 200, 500, 300);
        this.pack();
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void addEventHandler() {
        jmiRegister.addActionListener(new RegisterHandler());
        jmiSave.addActionListener(new SaveHandler());
        jmiRead.addActionListener(new ReadHandler());
        jmiModify.addActionListener(new ModifyHandler());
        jmiRemove.addActionListener(new RemoveHandler());
        jmiSearchByEmail.addActionListener(new SearchByEmailHandler());
        jmiSearchAll.addActionListener(new SearchAllHandler());

        btnRegister.addActionListener(new RegisterHandler());
        btnEdit.addActionListener(new ModifyHandler());
        btnRemove.addActionListener(new RemoveHandler());
        btnSearch.addActionListener(new SearchAllHandler());
    }

    private class RegisterHandler implements ActionListener {//注册

        public void actionPerformed(ActionEvent events) {
            new RegisterDialog(Menu.this, "注册用户", userDao).showMe(Menu.this);
        }
    }

    private class SaveHandler implements ActionListener {//保存信息

        public void actionPerformed(ActionEvent events) {
            FileOutputStream fos;
            ObjectOutputStream output = null;
            try {
                fos = new FileOutputStream("src/ex/ums/data/userData.dat");
                output = new ObjectOutputStream(fos);
                List<User> allUser = userDao.selectAll();
                for (User element : allUser)
                    output.writeObject(element);
                output.writeObject(new User("EOF", "EOF", "EOF", "EOF"));
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (output != null)
                    try {
                        output.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
            }
            JOptionPane.showMessageDialog(null, "保存用户成功！", "提示", JOptionPane.PLAIN_MESSAGE);
        }
    }

    private class ReadHandler implements ActionListener {//导入

        public void actionPerformed(ActionEvent events) {
            FileInputStream ips;
            ObjectInputStream input = null;
            try {
                ips = new FileInputStream("src/ex/ums/data/userData.dat");
                input = new ObjectInputStream(ips);
                User tmp;
                while (!(tmp = (User) input.readObject()).getEmail().equalsIgnoreCase("EOF")) {
                    userDao.insert(tmp);
                }
            } catch (ClassNotFoundException | IOException e) {
                e.printStackTrace();
            } finally {
                if (input != null)
                    try {
                        input.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
            }
            JOptionPane.showMessageDialog(null, "导入用户成功！", "提示", JOptionPane.PLAIN_MESSAGE);
        }
    }

    private class ModifyHandler implements ActionListener {//修改

        public void actionPerformed(ActionEvent events) {
            new ModifyDialog(Menu.this, "修改用户信息", userDao).showMe(Menu.this);
        }
    }

    private class RemoveHandler implements ActionListener {//删除

        public void actionPerformed(ActionEvent events) {
            new DeleteDialog(Menu.this, "删除用户信息", userDao).showMe(Menu.this);
        }
    }

    private class SearchByEmailHandler implements ActionListener {//按email查询

        public void actionPerformed(ActionEvent events) {
            new SearchDialog(Menu.this, "按email查询", userDao).showMe(Menu.this);
        }
    }

    private class SearchAllHandler implements ActionListener {  //查询所有用户
        public void actionPerformed(ActionEvent e) {
            List<User> users = userDao.selectAll();
            if (users.size() != 0) {
                new ShowDataTableDialog(Menu.this, "查询结果", users).showMe(Menu.this);  //显示所有用户
            } else {
                JOptionPane.showMessageDialog(null, "没有用户的信息", "提示", JOptionPane.PLAIN_MESSAGE);
            }
        }
    }
}