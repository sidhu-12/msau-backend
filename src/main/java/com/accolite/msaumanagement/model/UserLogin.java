package com.accolite.msaumanagement.model;

public class UserLogin {
	private final long   serialUUID = 1L;
	private String email;
	private String password;
	private String name;
	private String salt;
	
	private boolean isOAuth;
	
	
	public UserLogin()
	{
		
	}
	public String getSalt() {
		return salt;
	}
	public void setSalt(String salt) {
		this.salt = salt;
	}
	
	public UserLogin(String name, String email, String password, String salt, boolean isOAuth) {
		super();
		this.email = email;
		this.password = password;
		this.name = name;
		this.salt = salt;
		this.isOAuth = isOAuth;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String pwdHash) {
		this.password = pwdHash;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public boolean isOAuth() {
		return isOAuth;
	}
	public void setOAuth(boolean isOAuth) {
		this.isOAuth = isOAuth;
	}
	
	
}
