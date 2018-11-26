package com.top.cloud.bean;

import java.io.Serializable;
import java.util.Date;

/*
 *伪文件系统 
 * */
public class TopCloudFile implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id; //文件id
	private String filename; //文件名（1.txt）
	private String filepath; //文件路径
	private boolean isDir;   //是否是文件夹 默认false
	private String filesize;  //文件大小
	private String filetype;  //文件类型
	private Date fileuploadtime; //文件上传时间
	private String filelevel;//文件等级
	private int user_id;     //对应用户id
	private String MD5ID ;
	public TopCloudFile() {
	}


	
	
	public TopCloudFile(int id, String filename, String filepath,
			boolean isDir, String filesize, String filetype,
			Date fileuploadtime, String filelevel, int userId) {
		super();
		this.id = id;
		this.filename = filename;
		this.filepath = filepath;
		this.isDir = isDir;
		this.filesize = filesize;
		this.filetype = filetype;
		this.fileuploadtime = fileuploadtime;
		this.filelevel = filelevel;
		user_id = userId;
	}




	public int getId() {
		return id;
	}

	public String getFilelevel() {
		return filelevel;
	}


	public void setFilelevel(String filelevel) {
		this.filelevel = filelevel;
	}


	public void setId(int id) {
		this.id = id;
	}

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public String getFilepath() {
		return filepath;
	}

	public void setFilepath(String filepath) {
		this.filepath = filepath;
	}

	public boolean isDir() {
		return isDir;
	}

	public void setDir(boolean isDir) {
		this.isDir = isDir;
	}

	public String getFilesize() {
		return filesize;
	}

	public void setFilesize(String filesize) {
		this.filesize = filesize;
	}

	public String getFiletype() {
		return filetype;
	}

	public void setFiletype(String filetype) {
		this.filetype = filetype;
	}

	public Date getFileuploadtime() {
		return fileuploadtime;
	}

	public void setFileuploadtime(Date fileuploadtime) {
		this.fileuploadtime = fileuploadtime;
	}

	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int userId) {
		user_id = userId;
	}

	

	public String getMD5ID() {
		return MD5ID;
	}




	public void setMD5ID(String mD5ID) {
		MD5ID = mD5ID;
	}

	@Override
	public String toString() {
		return "TopCloudFile [id=" + id + ", filename=" + filename
				+ ", filepath=" + filepath + ", isDir=" + isDir + ", filesize="
				+ filesize + ", filetype=" + filetype + ", fileuploadtime="
				+ fileuploadtime + ", filelevel=" + filelevel + ", user_id="
				+ user_id + ", MD5ID=" + MD5ID + "]";
	}




	@Override
	public int hashCode() {
		return super.hashCode();
	}
	
	@Override
	public boolean equals(Object obj) {
		return super.equals(obj);
	}
	
	
	
}
