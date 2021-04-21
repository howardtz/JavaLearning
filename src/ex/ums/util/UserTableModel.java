package ex.ums.util;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import ex.ums.entity.User;

public class UserTableModel extends AbstractTableModel {
    private List<User> users;

    public UserTableModel(List<User> users) {
        this.users = users;
    }

    public int getColumnCount() {
        return 5;
    }

    public int getRowCount() {
        return users.size();
    }

    public Object getValueAt(int row, int col) {  //按指定的行、列取出数据
        User user = users.get(row);
        return switch (col) {
            case 0 -> row + 1 + "";
            case 1 -> user.getEmail();//email
            case 2 -> user.getUserName();//姓名
            case 3 -> user.getSex();//性别
            case 4 -> user.getHobbies();//爱好
            default -> null;
        };
    }

    public String getColumnName(int col) {
        return switch (col) {
            case 0 -> "序号";
            case 1 -> "email";//email
            case 2 -> "用户名";//姓名
            case 3 -> "性别";//性别
            case 4 -> "爱好";//爱好
            default -> null;
        };
    }
}
