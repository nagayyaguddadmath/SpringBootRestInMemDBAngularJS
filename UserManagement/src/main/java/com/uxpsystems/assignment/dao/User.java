package com.uxpsystems.assignment.dao;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import org.hibernate.validator.constraints.NotEmpty;


@Entity
@Table(name = "user_table")
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class User {


	@Id
	@Column(name = "user_id")
	@XmlAttribute
	private int userid;

	@Column(name = "user_name")
	@XmlAttribute
//	@NotEmpty(message = "username can't empty!")
	private String username;

	@Column(name = "user_password")
//	@ColumnTransformer(
//			forColumn = "user_password",
//			 read = "decrypt( 'AES', '00', user_password)",
//		        write = "CRYPT_KEY('AES', ?)" )
	@XmlAttribute
	private String password;

	@Column(name = "user_status")
	@Convert(converter = StatusConverter.class)
	@XmlAttribute
//	@Enumerated(EnumType.STRING)
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
