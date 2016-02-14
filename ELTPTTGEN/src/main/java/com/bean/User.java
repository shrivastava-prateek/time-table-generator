package com.bean;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class User {
	@Id
	private String username;
	private String password;
	private User_type user_type;
	private String name;
	private String email;
	
	public User() {
		super();
	}

	
	public User(String username, String password, User_type user_type,
			String name, String email) {
		super();
		this.username = username;
		this.password = password;
		this.user_type = user_type;
		this.name = name;
		this.email = email;
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

	public User_type getUser_type() {
		return user_type;
	}

	public void setUser_type(User_type user_type) {
		this.user_type = user_type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}


	@Override
	public String toString() {
		return "User [username=" + username + ", password=" + password
				+ ", user_type=" + user_type + ", name=" + name + ", email="
				+ email + "]";
	}
	
	
	

}
