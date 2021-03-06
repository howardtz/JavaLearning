package ex.ums.entity;

import java.io.Serial;
import java.io.Serializable;

public class User implements Serializable{
    @Serial
    private final static long serialVersionUID= 818L;

    private String email;
    private String userName;
    private String sex;
    private String hobbies;

    public User() {
        super();
    }

    public User(String email, String userName, String sex, String hobbies) {
        super();
        this.email = email;
        this.userName = userName;
        this.sex = sex;
        this.hobbies = hobbies;
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getHobbies() {
        return hobbies;
    }

    public void setHobbies(String hobbies) {
        this.hobbies = hobbies;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

}
