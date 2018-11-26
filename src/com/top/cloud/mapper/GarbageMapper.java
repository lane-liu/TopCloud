package com.top.cloud.mapper;

import java.util.List;

import com.top.cloud.bean.TopCloudFile;

public interface GarbageMapper {
	public int saveFileSystem(TopCloudFile topCloudFile);
	public List<TopCloudFile> getDelFileSystemgar(String Cloud);
	public  List<TopCloudFile> getGarbageFileSystem(TopCloudFile topCloudFile);

}
