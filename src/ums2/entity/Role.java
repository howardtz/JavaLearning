package ums2.entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Role implements Serializable{
	private int id;
	private String rname;  //角色名
	private String rdesc;  //角色描述
	private String rights;  //角色所对应的权限
	
	public Role() {
	}
	public Role(String rname, String rdesc, String rights) {
		super();
		this.rname = rname;
		this.rdesc = rdesc;
		this.rights = rights;
	}
	public Role(int id, String rname, String rdesc, String rights) {
		this.id = id;
		this.rname = rname;
		this.rdesc = rdesc;
		this.rights = rights;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}

	public String getRdesc() {
		return rdesc;
	}

	public void setRdesc(String rdesc) {
		this.rdesc = rdesc;
	}
	
	public String getRname() {
		return rname;
	}

	public void setRname(String rname) {
		this.rname = rname;
	}

	public Role( String rname, String rdesc) {
		super();
		this.rname = rname;
		this.rdesc = rdesc;
	}
	public String getRights() {
		return rights;
	}
	public void setRights(String rights) {
		this.rights = rights;
	}
}
