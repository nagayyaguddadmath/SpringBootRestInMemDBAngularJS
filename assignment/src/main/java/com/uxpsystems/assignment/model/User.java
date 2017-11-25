package com.uxpsystems.assignment.model;

import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "user_table")
@Cacheable
public class User {


	@Id
	@Column(name = "user_id")
	private int userid;

	@Column(name = "user_name")
	private String username;

	@Column(name = "user_password")
	@Convert(converter = PasswordEncoder.class)
	private String password;

	@Column(name = "user_status")
	@Convert(converter = StatusConverter.class)
	private UserStatus status;

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getUserid() {
		return userid;
	}

	public void setUserid(int userid) {
		this.userid = userid;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public UserStatus getStatus() {
		return status;
	}

	public void setStatus(UserStatus status) {
		this.status = status;
	}
}
