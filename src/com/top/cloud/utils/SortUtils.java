package com.top.cloud.utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import com.top.cloud.bean.TopCloudFile;

public class SortUtils {
	
	/*日期降序*/
	public static void desDateSort(List<TopCloudFile> sspMechant){
		Collections.sort(sspMechant, new Comparator<TopCloudFile>(){
			@Override
			public int compare(TopCloudFile o1, TopCloudFile o2) {
		            Date dt1 = o1.getFileuploadtime();
		            Date dt2 = o2.getFileuploadtime();
		            if (dt1.getTime() < dt2.getTime()) {
		               return 1;
		            } else if (dt1.getTime() > dt2.getTime()) {
		               return -1;
		            } else {
		               return 0;
		            }
		      }			
		});
    }
	
	/*日期升序*/
	public static List<TopCloudFile> ascDateSort(List<TopCloudFile> sspMechant){
		Collections.sort(sspMechant, new Comparator<TopCloudFile>(){
			@Override
			public int compare(TopCloudFile o1, TopCloudFile o2) {
		            Date dt1 = o1.getFileuploadtime();
		            Date dt2 = o2.getFileuploadtime();
		            if (dt1.getTime() > dt2.getTime()) {
		               return 1;
		            } else if (dt1.getTime() < dt2.getTime()) {
		               return -1;
		            } else {
		               return 0;
		            }
		      }			
		});
		return sspMechant;
    }
	
	/*文件大小降序*/
    public static List<TopCloudFile> desSizeSort(List<TopCloudFile> sspMechant){
    	Collections.sort(sspMechant, new Comparator<TopCloudFile>(){
			@Override
			public int compare(TopCloudFile o1, TopCloudFile o2) {
				String size1 = o1.getFilesize();
				String size2 = o2.getFilesize();
				int s1 = 0,s2 = 0;
				if(size1.contains("M")){
					s1=Integer.parseInt( size1.substring(0,size1.indexOf("M")))*1024*1024;
				}else if(size1.contains("K")){
					s1=Integer.parseInt( size1.substring(0,size1.indexOf("K")))*1024;
				}else{
					s1=Integer.parseInt(size1);
				}
				if(size2.contains("M")){
					s2=Integer.parseInt( size2.substring(0,size2.indexOf("M")))*1024*1024;
				}else if(size2.contains("K")){
					s2=Integer.parseInt( size2.substring(0,size2.indexOf("K")))*1024;
				}	else{
					s2=Integer.parseInt(size2);
				}
				if(s1<s2){
					return 1;
				}else if(s1>s2){
					return -1;
				}else{
					return 0;
				}
			}
    	});
		return sspMechant;
    }
    
    /*按文件名排序*/
    public static List<TopCloudFile> desFileName(List<TopCloudFile> file){
    	List<String> fileName=new ArrayList<String>();
		for(TopCloudFile f:file){
			String name=f.getFilename();
			fileName.add(name);
		}
		Collections.sort(fileName);
		List<TopCloudFile>  filelist=new ArrayList<TopCloudFile>();
		for (String files : fileName) {
			for (TopCloudFile topCloudFile : file) {
				if(topCloudFile.getFilename().equals(files)){
					filelist.add(topCloudFile);
				}
			}
		}
		return filelist;
    }

}

    