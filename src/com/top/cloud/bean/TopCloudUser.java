package com.top.cloud.bean;

import java.util.Date;

public class TopCloudUser {
	private int id; //用户id
	private String username;//用户名
	private String email;//用户邮箱
	private String password;//用户密码
	private int age;//用户年龄
	private String sex;//用户性别 男or女or不男不女
	private String address;//用户地址
	private String tel;//用户号码
	private String is_ckeckedEmail;//用户是否通过邮箱验证 false true
	private Date regiesttime;//用户注册时间
	private Date lastlogintime;//用户上一次登陆时间
	private String maxdevicespace;//最大存储空间 默认1GB
	private String surplusdevicespace;//剩余容量
	
	
	public TopCloudUser() {
	}
	
	public TopCloudUser(String username, String email, String password) {
		this.username = username;
		this.email = email;
		this.password = password;
	}
	
	public TopCloudUser(int id, String username, String email, String password,
			int age, String sex, String address, String tel,
			String isCkeckedEmail, Date regiesttime, Date lastlogintime,
			String maxdevicespace, String surplusdevicespace) {
		super();
		this.id = id;
		this.username = username;
		this.email = email;
		this.password = password;
		this.age = age;
		this.sex = sex;
		this.address = address;
		this.tel = tel;
		is_ckeckedEmail = isCkeckedEmail;
		this.regiesttime = regiesttime;
		this.lastlogintime = lastlogintime;
		this.maxdevicespace = maxdevicespace;
		this.surplusdevicespace = surplusdevicespace;
	}


	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
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
	public void setPassword(String password) {
		this.password = password;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getIs_ckeckedEmail() {
		return is_ckeckedEmail;
	}
	public void setIs_ckeckedEmail(String isCkeckedEmail) {
		is_ckeckedEmail = isCkeckedEmail;
	}
	public Date getRegiesttime() {
		return regiesttime;
	}
	public void setRegiesttime(Date regiesttime) {
		this.regiesttime = regiesttime;
	}
	public Date getLastlogintime() {
		return lastlogintime;
	}
	public void setLastlogintime(Date lastlogintime) {
		this.lastlogintime = lastlogintime;
	}
	public String getMaxdevicespace() {
		return maxdevicespace;
	}
	public void setMaxdevicespace(String maxdevicespace) {
		this.maxdevicespace = maxdevicespace;
	}
	public String getSurplusdevicespace() {
		return surplusdevicespace;
	}
	public void setSurplusdevicespace(String surplusdevicespace) {
		this.surplusdevicespace = surplusdevicespace;
	}
	@Override
	public String toString() {
		return "TopCloudUser [address=" + address + ", age=" + age + ", email="
				+ email + ", id=" + id + ", is_ckeckedEmail=" + is_ckeckedEmail
				+ ", lastlogintime=" + lastlogintime + ", maxdevicespace="
				+ maxdevicespace + ", password=" + password + ", regiesttime="
				+ regiesttime + ", sex=" + sex + ", surplusdevicespace="
				+ surplusdevicespace + ", tel=" + tel + ", username="
				+ username + "]";
	}
	
	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		return super.equals(obj);
	}
	
	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return super.hashCode();
	}
	
	

}
