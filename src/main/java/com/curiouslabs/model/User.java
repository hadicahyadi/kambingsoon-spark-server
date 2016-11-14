package com.curiouslabs.model;

public class User extends BaseModel implements BaseModelInterface{
	
	private String username;
	private String password;
	private String role;
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
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	@Override
	public Object[] toArray() {
		return new Object[]{username,password,role};
	}

}
