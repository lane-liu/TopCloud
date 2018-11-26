package com.top.cloud.bean;

import java.util.Date;

public class MoveFile {
	private String newPath;
	private String oldPath;
	private String fileName;
	private int user_id	;
	private Date date;
	public MoveFile(String newPath, String oldPath, String fileName,
			int user_id, Date date) {
		super();
		this.newPath = newPath;
		this.oldPath = oldPath;
		this.fileName = fileName;
		this.user_id = user_id;
		this.date = date;
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
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	@Override
	public String toString() {
		return "MoveFile [newPath=" + newPath + ", oldPath=" + oldPath
				+ ", fileName=" + fileName + ", user_id=" + user_id + ", date="
				+ date + "]";
	}
	
	
}
