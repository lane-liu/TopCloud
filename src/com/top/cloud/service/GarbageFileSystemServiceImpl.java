package com.top.cloud.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.top.cloud.bean.TopCloudFile;
import com.top.cloud.mapper.GarbageMapper;
@Service
public class GarbageFileSystemServiceImpl implements GarbageFileSystemService {
	@Autowired
	private GarbageMapper garbageMapper;
	@Override
	public List<TopCloudFile> getGarbageFileSystem(TopCloudFile topCloudFile) {
		List<TopCloudFile> garbageFileSystem = garbageMapper.getGarbageFileSystem( topCloudFile);
		return garbageFileSystem;
	}

}
