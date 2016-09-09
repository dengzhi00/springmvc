package com.yuansq.author.dto;

public class UserAccount {

	private int userId;
	private String userName;
	private String nickName;
	private String pwd;
	private String state;
	public UserAccount(){
		
	}
	public UserAccount(String userName, String nickName, String pwd, String state,int userId) {
		this.userName = userName;
		this.nickName = nickName;
		this.pwd = pwd;
		this.state = state;
		this.userId=userId;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

}
