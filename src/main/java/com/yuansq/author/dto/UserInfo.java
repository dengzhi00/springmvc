package com.yuansq.author.dto;

public class UserInfo {
	private String name;
	private String birthday;
	private String sex;
	private String tall;
	private String weight;
	private String age;
	private String area;
	private String state;
	private String user_id;
	private String pwd;

	public UserInfo(){
		
	}
	public UserInfo(String name, String birthday, String sex, String tall, String weight, String age, String area,
			String state,String pwd) {
		this.name = name;
		this.birthday = birthday;
		this.sex = sex;
		this.tall = tall;
		this.weight = weight;
		this.age = age;
		this.area = area;
		this.state = state;
		this.pwd = pwd;
		
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getBirthday() {
		return birthday;
	}
	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getTall() {
		return tall;
	}
	public void setTall(String tall) {
		this.tall = tall;
	}
	public String getWeight() {
		return weight;
	}
	public void setWeight(String weight) {
		this.weight = weight;
	}
	public String getAge() {
		return age;
	}
	public void setAge(String age) {
		this.age = age;
	}
	public String getArea() {
		return area;
	}
	public void setArea(String area) {
		this.area = area;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	
	
}
