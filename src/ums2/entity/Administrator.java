package ums2.entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class Administrator implements Serializable {
	private int id;
	private String username;
	private String password;
	private int rid;  //管理员角色

	public Administrator(String username, String password, int rid) {
		this.username = username;
		this.password = password;
		this.rid = rid;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Administrator(String username, String password) {
		this.username = username;
		this.password = password;
	}

	public Administrator() {
		super();
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getRid() {
		return rid;
	}

	public void setRid(int rid) {
		this.rid = rid;
	}
}