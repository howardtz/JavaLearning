package ex.ums.dao.impl;

import java.util.ArrayList;
import java.util.Iterator;

import java.util.List;

import ex.ums.dao.UserDao;
import ex.ums.entity.User;

public class UserDaoImplForList implements UserDao {
    private List<User> users;
    public UserDaoImplForList() {
        users = new ArrayList<>();
    }

    public User delete(String email) {
        Iterator<User> it = users.iterator();
        while (it.hasNext()) {
            User user = it.next();
            if (user.getEmail().equalsIgnoreCase(email)) {
                it.remove();
                return user;
            }
        }
        return null;
    }

    public void insert(User user) {
        users.add(user);
    }

    public List<User> selectAll() {
        return users;
    }

    public User selectByEmail(String email) {
        for (User user : users) {
            if (user.getEmail().equals(email)) {
                return user;
            }
        }
        return null;
    }

    public void update(User user) {
        User u = selectByEmail(user.getEmail());
        if (u != null) {
            users.remove(u);
            users.add(user);
        }
    }

}
