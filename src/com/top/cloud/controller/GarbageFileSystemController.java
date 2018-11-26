package com.top.cloud.controller;

import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.top.cloud.bean.TopCloudFile;
import com.top.cloud.service.GarbageFileSystemService;
@Controller
public class GarbageFileSystemController {
	private String root="C:\\apache-tomcat-6.0.18\\webapps\\upload";
	
	@Autowired
	private GarbageFileSystemService garbageFileSystemService;
	
	@RequestMapping("getGarbageFileSystem")
	@ResponseBody
	public List<TopCloudFile> getGarbageFileSystem(int id ,String root,HttpServletResponse response,HttpSession session){
		TopCloudFile topCloudFile=new TopCloudFile();
		topCloudFile.setUser_id(id);
		if(root.equals("root")||root.equals("root+")){
			root="C:\\apache-tomcat-6.0.18\\webapps\\upload";
		}else{
			root=this.root+"\\"+root.substring(root.indexOf("+")+1,root.length());
		}
		root=root.replace("+", "\\");
		if(root.endsWith("\\")){
			root=root.substring(0,root.length()-1);
		}
		topCloudFile.setFilepath(root);
		System.out.println("获取回收站所有数据");
		List<TopCloudFile> garbageFileSystem = garbageFileSystemService.getGarbageFileSystem(topCloudFile);
		return garbageFileSystem;
	}

}
