package com.top.cloud.bean;

import org.springframework.stereotype.Component;

@Component
public class User {
	private int id;
	
	private String name;
	
	private String password;
	
	private int age;
	
	private String tel;

	public User() {
	}
	
	public User(String name, String password, int age, String tel) {
		super();
		this.name = name;
		this.password = password;
		this.age = age;
		this.tel = tel;
	}
	
	public User(int id, String name, String password, int age, String tel) {
		super();
		this.id = id;
		this.name = name;
		this.password = password;
		this.age = age;
		this.tel = tel;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	@Override
	public String toString() {
		return "User [age=" + age + ", id=" + id + ", name=" + name
				+ ", password=" + password + ", tel=" + tel + "]";
	}

	@Override
	public int hashCode() {
		return id;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (age != other.age)
			return false;
		if (id != other.id)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		if (tel == null) {
			if (other.tel != null)
				return false;
		} else if (!tel.equals(other.tel))
			return false;
		return true;
	}
	
	
	

}
