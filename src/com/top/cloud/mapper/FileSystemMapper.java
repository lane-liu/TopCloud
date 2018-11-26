package com.top.cloud.mapper;

import java.util.List;

import com.top.cloud.bean.ChangeDir;
import com.top.cloud.bean.ChangeName;
import com.top.cloud.bean.MoveFile;
import com.top.cloud.bean.SelectFile;
import com.top.cloud.bean.TopCloudFile;

public interface FileSystemMapper {
	
	public List<TopCloudFile> getFile( SelectFile selectFile);
	public List<TopCloudFile> getAllFile( int id);
	public List<TopCloudFile> getAllDocument(int id);
	public List<TopCloudFile> getAllMusic(int id);
	public List<TopCloudFile> getAllVideo(int id);
	public List<TopCloudFile> getAllPicture(int id);
	public int upLoadFile(TopCloudFile topCloudFile);
	public Integer CrectedFolder(TopCloudFile topCloudFile);
	public Integer deleteFile(TopCloudFile topCloudFile);
	public Integer deleteCloudFile(String Cloud);
	public int changeFileName(ChangeName changeName);
	public int changeDirName(ChangeDir changeDir);
	public int moveFile(MoveFile moveFile);
	public List<TopCloudFile> getVagueFile(SelectFile selectFile);
	public List<TopCloudFile> selectedFileName(String fileName);
	
	public TopCloudFile findLinkShare(TopCloudFile topCloudFile);
	public int insertLinkShare(TopCloudFile topCloudFile);
	public TopCloudFile selectByPathAndNameWithGarbage(TopCloudFile topCloudFile);
	public TopCloudFile findLinkShareDownload(String mD5ID);
	public TopCloudFile getXiangQing(TopCloudFile topCloudFile);
}
