package com.hbase.test.servlet.entities;
import java.io.Serializable;
import java.util.Date;

/*
 * 用户 实体类
 */
public class User implements Serializable {
	private static final long serialVersionUID = 435894070589975762L;
	private String userName;
	private String userSex;
	private String userPassword;
	private String userEmail;
	private Date userBirthday;
	private Integer userFlag;
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserSex() {
		return userSex;
	}
	public void setUserSex(String userSex) {
		this.userSex = userSex;
	}
	public String getUserPassword() {
		return userPassword;
	}
	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}
	public String getUserEmail() {
		return userEmail;
	}
	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}
	public Date getUserBirthday() {
		return userBirthday;
	}
	public void setUserBirthday(Date userBirthday) {
		this.userBirthday = userBirthday;
	}
	public Integer getUserFlag() {
		return userFlag;
	}
	public void setUserFlag(Integer userFlag) {
		this.userFlag = userFlag;
	}
	public User() {
	}
	public User(String userName, String userSex, String userPassword, String userEmail) {
		this.userName = userName;
		this.userSex = userSex;
		this.userPassword = userPassword;
		this.userEmail = userEmail;
	}
}
