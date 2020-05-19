package com.appsdeveloperblog.photoapp.api.users.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="users")
//Entity class for table creation 
public class UserEntity implements Serializable {
	
	
	private static final long serialVersionUID = -7164543398391255610L;
	
	@Id
	@GeneratedValue
	private long id;
	
	@Column(nullable=false,length=30)
	private String firstname;
	
	@Column(nullable=false,length=30)
	private String lastname;
		
	@Column(nullable=false,length=120,unique=true)
	private String email;
	
	@Column(nullable=false,length=30,unique=true)
	private String encryptedpassword;
	
	@Column(nullable=false,length=30,unique=true)
	private String userid;
	
	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getEncryptedpassword() {
		return encryptedpassword;
	}
	public void setEncryptedpassword(String encryptedpassword) {
		this.encryptedpassword = encryptedpassword;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}


}
