package ums2.entity;

public class User {
	private String id;
	private String email;
	private String username;
	private String sex;
    private String hobbies;
    
    
    public User() {
	}

	public User(String id, String email, String username, String sex,
			String hobbies) {
		super();
		this.id = id;
		this.email = email;
		this.username = username;
		this.sex = sex;
		this.hobbies = hobbies;
	}
    
	public User(String email, String username, String sex, String hobbies) {
		super();
		this.email = email;
		this.username = username;
		this.sex = sex;
		this.hobbies = hobbies;
	}

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getHobbies() {
		return hobbies;
	}
	public void setHobbies(String hobbies) {
		this.hobbies = hobbies;
	}
    
    
    
}