package com.top.cloud.bean;

public class SelectFile {
	private int id;
	private String filepath;
	public SelectFile() {
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getFilepath() {
		return filepath;
	}
	public void setFilepath(String filepath) {
		this.filepath = filepath;
	}
	public SelectFile(int id, String filepath) {
		super();
		this.id = id;
		this.filepath = filepath;
	}
	@Override
	public String toString() {
		return "SelectFile [filepath=" + filepath + ", id=" + id + "]";
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
