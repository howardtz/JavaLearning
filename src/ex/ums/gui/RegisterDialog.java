package ex.ums.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import ex.ums.dao.UserDao;
import ex.ums.entity.User;

public class RegisterDialog extends JDialog {
    private UserDao userDao;

    private JLabel labelEmail = new JLabel("email");
    private JTextField userEmail = new JTextField(20);

    private JLabel labelName = new JLabel("用户名");
    private JTextField userName = new JTextField(20);

    private JLabel labelSex = new JLabel("性别：  ");
    private JLabel labelMale = new JLabel("男");
    private JLabel labelFemale = new JLabel("女");
    private JRadioButton male = new JRadioButton();  //单选按钮--男
    private JRadioButton female = new JRadioButton();  //单选按钮--女

    private JLabel labelHobby = new JLabel("爱好：  ");
    private String[] strHobbies = {"体育运动", "上网", "看书", "打游戏"};
    private JCheckBox[] hobbies = new JCheckBox[4];
    private JLabel[] labelHobbies = new JLabel[4];

    private JButton buttonSave = new JButton("保存");
    private JButton buttonModify = new JButton("修改");
    private JButton buttonExit = new JButton("退出");

    private int windowHeight = 250;  //窗口高度
    private int windowWidth = 350;   //窗口宽度

    JPanel pEmail = new JPanel();  //email，与修改功能共享
    JPanel pName = new JPanel();  //用户名，与修改功能共享
    JPanel pSex = new JPanel();  //性别，与修改功能共享
    JPanel pHobby = new JPanel();  //爱好，与修改功能共享
    private JPanel pButton = new JPanel();  //按钮区面板，与修改功能共享
    private JPanel panel = new JPanel();  //总面板，与修改功能共享

    //设置布局
    private void init() {
        //email
        pEmail.add(labelEmail);
        pEmail.add(userEmail);

        //用户名
        pName.add(labelName);
        pName.add(userName);

        //性别
        ButtonGroup sexRadioGroup = new ButtonGroup();
        sexRadioGroup.add(male);
        sexRadioGroup.add(female);
        pSex.add(labelSex);
        pSex.add(labelMale);
        pSex.add(male);
        pSex.add(labelFemale);
        pSex.add(female);
        male.setSelected(true);

        //爱好
        pHobby.add(labelHobby);
        for (int i = 0; i < hobbies.length; i++) {
            hobbies[i] = new JCheckBox();
            labelHobbies[i] = new JLabel();
            labelHobbies[i].setText(strHobbies[i]);
            pHobby.add(hobbies[i]);
            pHobby.add(labelHobbies[i]);
        }

        //按钮
        pButton.add(buttonSave);
        pButton.add(buttonExit);

        //添加至总面板
        panel.add(pEmail);
        panel.add(pName);
        panel.add(pSex);
        panel.add(pHobby);
        panel.add(pButton);

        this.add(panel);
    }

    public RegisterDialog(JFrame parent, String msg, UserDao userDao) {//注册对话框
        super(parent, msg, true);
        this.userDao = userDao;
    }

    public void showMe(JFrame parent) {
        this.init();  //设置窗口布局
        addEventHandler();
        setPosition(parent);
        this.validate();
        setVisible(true);
    }

    private void setPosition(JFrame parent) {
        //计算对话框的显示位置
        int parentX = parent.getX();
        int parentY = parent.getY();
        int parentWidth = parent.getWidth();
        int parentHeight = parent.getHeight();
        int dialogX = parentX + (parentWidth - windowWidth) / 2;
        int dialogY = parentY + (parentHeight - windowHeight) / 2 + 40;
        this.setBounds(dialogX, dialogY, windowWidth, windowHeight);
    }

    public void addEventHandler() {
        buttonSave.addActionListener(new ButtonSaveHandler());
        buttonExit.addActionListener(new ButtonExitHandler());
    }

    private class ButtonSaveHandler implements ActionListener {  //保存按钮的事件监听器
        public void actionPerformed(ActionEvent e) {
            //1.用户email
            String email = userEmail.getText();

            //2.姓名
            String name = userName.getText();

            //3.性别
            String sex = male.isSelected() ? "男" : "女";

            //4.爱好
            String hobby = getHobbiesInfo();

            if (email.length() == 0) {
                JOptionPane.showMessageDialog(null, "请输入email", "提示", JOptionPane.PLAIN_MESSAGE);
                userEmail.grabFocus();
            } else if (!email.matches("[a-zA-Z0-9_]+@([a-zA-Z0-9-]+\\.)+[a-zA-Z0-9]{2,4}")) {
                JOptionPane.showMessageDialog(null, "email格式有误", "提示", JOptionPane.PLAIN_MESSAGE);
                userEmail.setText("");
                userEmail.grabFocus();
            } else if (name.length() == 0) {
                JOptionPane.showMessageDialog(null, "请填写用户名", "提示", JOptionPane.PLAIN_MESSAGE);
                userName.grabFocus();
            } else {
                if (userDao.selectByEmail(email) != null) {  //邮箱已注册
                    JOptionPane.showMessageDialog(null, "该邮箱已存在", "提示", JOptionPane.PLAIN_MESSAGE);
                    userEmail.setText("");
                    userEmail.grabFocus();
                } else {
                    User user = new User(email, name, sex, hobby);
                    userDao.insert(user);
                    clear();
                }
            }
        }
    }

    private class ButtonExitHandler implements ActionListener {
        public void actionPerformed(ActionEvent arg0) {
            dispose();
        }
    }

    public RegisterDialog(JFrame parent, String msg, UserDao userDao, User user) { //修改对话框
        super(parent, msg, true);
        this.userDao = userDao;

        init();  //设置窗口
        userEmail.setEditable(false);

        pButton.remove(buttonSave);    //删除注册窗口中的"保存"按钮
        pButton.add(buttonModify);
        pButton.add(buttonExit);//调整删除按钮的位置
        panel.add(pButton);
        this.add(panel);

        userEmail.setText(user.getEmail());
        userName.setText(user.getUserName());

        if (user.getSex().equals("男")) {
            male.setSelected(true);
        } else {
            female.setSelected(true);
        }

        if (user.getHobbies().contains("体育运动")) {
            hobbies[0].setSelected(true);
        }
        if (user.getHobbies().contains("上网")) {
            hobbies[1].setSelected(true);
        }
        if (user.getHobbies().contains("看书")) {
            hobbies[2].setSelected(true);
        }
        if (user.getHobbies().contains("打游戏")) {
            hobbies[3].setSelected(true);
        }

        buttonModify.addActionListener(new ButtonModifyHandler());
        buttonExit.addActionListener(new ButtonExitHandler());

        //计算对话框的显示位置
        setPosition(parent);

        validate();
        setVisible(true);
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
    }

    private class ButtonModifyHandler implements ActionListener {  //修改按钮的事件监听器
        public void actionPerformed(ActionEvent e) {
            //收集界面提交的信息
            //1.email
            String email = userEmail.getText();

            //2.用户名
            String name = userName.getText();

            //3.性别
            String sex = male.isSelected() ? "男" : "女";

            //4.爱好
            String hobby = getHobbiesInfo();

            if (name.length() == 0) {
                JOptionPane.showMessageDialog(null, "请填写用户名", "提示", JOptionPane.PLAIN_MESSAGE);
                userName.grabFocus();
            } else {
                User user = new User(email, name, sex, hobby);
                //更新数据
                userDao.update(user);
                clear();
            }
        }
    }

    /**
     * 清空组件
     */
    private void clear() {
        userEmail.setText("");
        userName.setText("");
        male.setSelected(true);

        for (JCheckBox hobby : hobbies) {
            hobby.setSelected(false);
        }
    }

    /**
     * getInfo():获取爱好信息
     */
    private String getHobbiesInfo() {
        StringBuilder strHobbies = new StringBuilder();
        for (int i = 0; i < hobbies.length; i++) {
            if (hobbies[i].isSelected()) {  //选择了该爱好
                String hobbyText = labelHobbies[i].getText(); //提取其对应的标签文本
                strHobbies/*.append("  ")*/.append(hobbyText).append("  ");//删除了hobbies字符串前面的空格
            }
        }
        return strHobbies.toString();
    }
}
