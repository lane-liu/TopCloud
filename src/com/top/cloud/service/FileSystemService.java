package com.top.cloud.service;

import java.util.List;

import com.top.cloud.bean.TopCloudFile;

public interface FileSystemService {
	
	public List<TopCloudFile> getFile(int id,String root);
	public List<TopCloudFile> getAllfile(int id);
	public List<TopCloudFile> getAllDocument(int id);
	public List<TopCloudFile> getAllMusic(int id);
	public List<TopCloudFile> getAllVideo(int id);
	public List<TopCloudFile> getAllPicture(int id);
	public int UpLoadFile(String root, String filename, long filesize,
			String filetype, int id);
	public Integer CrectedFolder(int id,String name,String path);
	//删除文件或文件夹
	public Integer deleteFile(String filename, String filepath,
			String filetype, int id);
	//批量删除文件或文件夹
	public Integer deleteBatchFile(String[] filenames, String[] filepaths,
			String[] filetypes, int[] ids);
	
	public int changeName(String root,String oldName,String newName,int user_id);
	public int changeDirName(String newPath,String oldPath,int user_id);
	public int moveFile(String newPath,String oldPath,String fileName,int user_id);
	public List<TopCloudFile> selectFileByName(String fileName);
	public String linkShare(String filename,String filepath,int user_id);
	public TopCloudFile findLinkShareDownload(String mD5ID);
	public TopCloudFile getXiangQing(int id, String filepath);
	
}
