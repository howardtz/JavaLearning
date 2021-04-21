package ex.ums.dao;

import java.util.List;

import ex.ums.entity.User;

public interface UserDao{

    void insert(User user);

    User delete(String name);

    void update(User user);

    List<User> selectAll();

    User selectByEmail(String email);

}
