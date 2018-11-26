package com.top.cloud.service;

import java.util.List;

import com.top.cloud.bean.TopCloudFile;

public interface GarbageFileSystemService {
	public List<TopCloudFile> getGarbageFileSystem(TopCloudFile topCloudFile);

}
