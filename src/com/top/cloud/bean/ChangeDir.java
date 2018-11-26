package com.top.cloud.bean;

import java.io.Serializable;
import java.util.Date;

public class ChangeDir implements Serializable{
	private static final long serialVersionUID = 1L;
	private int user_id;
	private String newPath;
	private String oldPath;
	private Date date;
	public ChangeDir() {
	}
	public ChangeDir(int user_id, String newPath, String oldPath, Date date) {
		super();
		this.user_id = user_id;
		this.newPath = newPath;
		this.oldPath = oldPath;
		this.date = date;
	}
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	public String getNewPath() {
		return newPath;
	}
	public void setNewPath(String newPath) {
		this.newPath = newPath;
	}
	public String getOldPath() {
		return oldPath;
	}
	public void setOldPath(String oldPath) {
		this.oldPath = oldPath;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	@Override
	public String toString() {
		return "ChangeDir [user_id=" + user_id + ", newPath=" + newPath
				+ ", oldPath=" + oldPath + ", date=" + date + "]";
	}
	
}
