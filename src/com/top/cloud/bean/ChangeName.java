package com.top.cloud.bean;

import java.io.Serializable;
import java.util.Date;

public class ChangeName implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String root;
	private String oldName;
	private String newName;
	private int user_id;
	private Date data;
	
	public ChangeName() {
	}
	
	public ChangeName(String root, String oldName, String newName, int user_id,
			Date data) {
		super();
		this.root = root;
		this.oldName = oldName;
		this.newName = newName;
		this.user_id = user_id;
		this.data = data;
	}

	public String getRoot() {
		return root;
	}

	public void setRoot(String root) {
		this.root = root;
	}

	public String getOldName() {
		return oldName;
	}

	public void setOldName(String oldName) {
		this.oldName = oldName;
	}

	public String getNewName() {
		return newName;
	}

	public void setNewName(String newName) {
		this.newName = newName;
	}

	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	@Override
	public String toString() {
		return "ChangeName [root=" + root + ", oldName=" + oldName
				+ ", newName=" + newName + ", user_id=" + user_id + ", data="
				+ data + "]";
	}
	
}
