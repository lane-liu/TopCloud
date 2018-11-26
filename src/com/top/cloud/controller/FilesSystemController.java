package com.top.cloud.controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;


import com.top.cloud.bean.TopCloudFile;
import com.top.cloud.service.FileSystemService;
import com.top.cloud.utils.SortUtils;

@Controller
public class FilesSystemController {
	@Autowired
	private FileSystemService fileSystemService;
	private String root="C:\\apache-tomcat-6.0.18\\webapps\\upload";
	
	
	@RequestMapping("getfile")
	@ResponseBody
	public List<TopCloudFile> getFile(int id ,String root,HttpServletResponse response,HttpSession session){
		response.setHeader("Access-Control-Allow-Origin", "*");
	
		System.out.println("-------------------------->获取数据路径"+root);
		List<TopCloudFile> topCloudFile = fileSystemService.getFile(id ,root);
		int filesystemsize=0;
		for (TopCloudFile topCloudFile2 : topCloudFile) {
			String filesize = topCloudFile2.getFilesize();
			if(filesize.contains("M")){
				filesystemsize+=Integer.parseInt(filesize.substring(0,filesize.indexOf("M")))*1024*1024;
			}else if(filesize.contains("K")){
				filesystemsize+=Integer.parseInt(filesize.substring(0,filesize.indexOf("K")))*1024;
			}else{
				filesystemsize+=Integer.parseInt(filesize);
			}
		}
		session.setAttribute("file", topCloudFile);
		session.setAttribute("filesystemsize", filesystemsize);
		return topCloudFile;
	}
	
	
	@RequestMapping("getAllfile")
	@ResponseBody
	public List<TopCloudFile> getAllfile(int id,HttpServletResponse response ,HttpSession session){
		response.setHeader("Access-Control-Allow-Origin", "*");
		System.out.println(id);
		List<TopCloudFile> topCloudFile = fileSystemService.getAllfile(id );
		System.out.println(topCloudFile);
		session.setAttribute("allfile", topCloudFile);
		topCloudFile=SortUtils.desFileName(topCloudFile);
		return topCloudFile;
	}
	//分类查询 ：查询文档
	@RequestMapping("selectFileByDocument")
	@ResponseBody
	public List<TopCloudFile> selectFileByDocument(int id,HttpSession session){
		System.out.println("流程到Controll中的selectFileByDocment里面了");
		List<TopCloudFile> alldocument = fileSystemService.getAllDocument(id);
		alldocument=SortUtils.desFileName(alldocument);
		return alldocument;
	}
	
	//分类查询 ：查询音乐
	@RequestMapping("selectFileByMusic")
	@ResponseBody
	public List<TopCloudFile> selectFileByMusic(int id,HttpSession session){
		System.out.println("流程到Controll中的selectFileByMusic里面了");
		List<TopCloudFile> allmusic = fileSystemService.getAllMusic(id);
		allmusic=SortUtils.desFileName(allmusic);
		return allmusic;
	}
	
	//分类查询 ：查询视频
	@RequestMapping("selectFileByVideo")
	@ResponseBody
	public List<TopCloudFile> selectFileByVideo(int id,HttpSession session){
		System.out.println("流程到Controll中的selectFileByVideo里面了");
		List<TopCloudFile> allvideo = fileSystemService.getAllVideo(id);
		allvideo=SortUtils.desFileName(allvideo);
		return allvideo;

	}
	
	//分类查询 ：查询图片
	@RequestMapping("selectFileByPicture")
	@ResponseBody
	public List<TopCloudFile> selectFileByPicture(int id,HttpSession session){
		System.out.println("流程到Controll中的selectFileByPicture里面了");
		List<TopCloudFile> allpicture = fileSystemService.getAllPicture(id);
		allpicture=SortUtils.desFileName(allpicture);
		return allpicture;
	}
	@RequestMapping("UpLoadFile")
	public String  UpLoadFile(String filepath,int id,HttpServletRequest request ,@RequestParam("file") CommonsMultipartFile file){
		System.out.println("上传...filepath"+filepath);
		//获取上传文件的目录
		String root="C:\\apache-tomcat-6.0.18\\webapps\\upload";
		root=root+"\\"+filepath.substring(filepath.indexOf("+")+1,filepath.length());
		root=root.replace("+", "\\");
		if(root.endsWith("\\")){
			root=root.substring(0,root.length()-1);	
		}
		System.out.println("上传...root="+root);
		String path = request.getSession().getServletContext().getRealPath("upload");
		System.out.println(path);
		String filename = file.getOriginalFilename();
		filename=filename.replace(" ", "");
		InputStream is=null;
		OutputStream os = null;
		//2 Io流读写临时文件file
		try {
			 is=file.getInputStream();
			 os=new FileOutputStream(new File(root,filename));
			byte [] buffer =new byte[8*1024];
			while (true) {
				int len = is.read(buffer);
				if(len==-1) break;
				os.write(buffer ,0,len);
				
			}
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			try {
				is.close();
				os.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		long filesize = file.getSize();
		String filetype=filename.substring(filename.lastIndexOf(".")+1,filename.length());
		int result = fileSystemService.UpLoadFile(root,filename,filesize,filetype,id);
		if(result==1){
			System.out.println("上传成功！");
		}
		
		return "redirect:index.html?id="+id;
		
		
	}
	
	@RequestMapping("CrectedFolder")
	@ResponseBody
	public int CrectedFolder(int id,String name,String path){
		System.out.println("新建文件名------------------------------------》"+name);
		System.out.println("新建文件路径------------------------------------》"+path);
		path=path.replace("++", "+");
		int result= fileSystemService.CrectedFolder(id,name,path);
		return result;
	}
	
	//删除文件或文件夹
	@RequestMapping("DeleteFile")
	@ResponseBody
	public int deleteFile(String filepath,int id){
		String filepaths=filepath.replace("$", "");
		String filetype;
		String filename=filepaths.substring(filepaths.lastIndexOf("+")+1,filepaths.length());
		if(filepaths.indexOf(".")==-1){
			filetype="dri";
		}else{
			filetype=filepaths.substring(filepaths.lastIndexOf(".")+1,filepaths.length());	
		}
		filepath=filepath.substring(0 ,filepaths.lastIndexOf("+")+1);
		System.out.println("filename="+filename);
		System.out.println("filepath="+filepath);
		System.out.println("filetype="+filetype);
		int result= fileSystemService.deleteFile(filename,filepath,filetype,id);
		return result;
	}
	
	
	//批量删除文件或文件夹
	@RequestMapping("DeleteBatchFile")
	@ResponseBody
	public int deleteBatchFile(String filepath,int[] id){
		System.out.println(filepath);
		String[] filepaths=filepath.split("\\$");
		System.out.println(filepaths.length);
		String[] filenames= new String[filepaths.length];
		String[] filetypes= new String[filepaths.length];
		String filetype;
		String filename;
		for (String string : filepaths) {
			string=string.replace("$", "");
		}
		for (int i = 0; i < filepaths.length; i++) {
			String file=filepaths[i];
			filepaths[i]=file.substring(0,file.lastIndexOf("+"));
			filename=file.substring(file.lastIndexOf("+")+1,file.length());
			filenames[i]=filename;
			if(file.indexOf(".")==-1){
				filetype="dri";
				filetypes[i]=filetype;
			}else{
				filetype=file.substring(file.lastIndexOf(".")+1,file.length());
				filetypes[i]=filetype;
			}
		}
		int result= fileSystemService.deleteBatchFile(filenames,filepaths,filetypes,id);
		System.out.println("result="+result);
		return result;
	}
	
	 public  void  copyFolder(String  oldPath,  String  newPath)  {  
		 
	       try  {  
	           (new  File(newPath)).mkdirs();  //如果文件夹不存在  则建立新文件夹  
	           File  a=new  File(oldPath);  
	           String[]  file=a.list();  
	           File  temp=null;  
	           for  (int  i  =  0;  i  <  file.length;  i++)  {  
	               if(oldPath.endsWith(File.separator)){  
	                   temp=new  File(oldPath+file[i]);  
	               }  
	               else{  
	                   temp=new  File(oldPath+File.separator+file[i]);  
	               }  
	 
	               if(temp.isFile()){  
	                   FileInputStream  input  =  new  FileInputStream(temp);  
	                   FileOutputStream  output  =  new  FileOutputStream(newPath  +  "/"  + 
	                           (temp.getName()).toString());  
	                   byte[]  b  =  new  byte[1024  *  5];  
	                   int  len;  
	                   while  (  (len  =  input.read(b))  !=  -1)  {  
	                       output.write(b,  0,  len);  
	                   }  
	                   output.flush();  
	                   output.close();  
	                   input.close();  
	               }  
	               if(temp.isDirectory()){//如果是子文件夹  
	                   copyFolder(oldPath+"/"+file[i],newPath+"/"+file[i]);  
	               }  
	           }  
	       }  
	       catch  (Exception  e)  {  
	           System.out.println("复制整个文件夹内容操作出错");  
	           e.printStackTrace();  
	 
	       }  
	 
	   }  
	  public  void  delFolder(String  folderPath)  {  
	       try  {  
	           delAllFile(folderPath);  //删除完里面所有内容  
	           String  filePath  =  folderPath;  
	           filePath  =  filePath.toString();  
	           java.io.File  myFilePath  =  new  java.io.File(filePath);  
	           myFilePath.delete();  //删除空文件夹  
	 
	       }  
	       catch  (Exception  e)  {  
	           System.out.println("删除文件夹操作出错");  
	           e.printStackTrace();  
	 
	       }  
	 
	   }  
	 
	   /**  
	     *  删除文件夹里面的所有文件  
	     *  @param  path  String  文件夹路径  如  c:/fqf  
	     */  
	   public  void  delAllFile(String  path)  {  
	       File  file  =  new  File(path);  
	       if  (!file.exists())  {  
	           return;  
	       }  
	       if  (!file.isDirectory())  {  
	           return;  
	       }  
	       String[]  tempList  =  file.list();  
	       File  temp  =  null;  
	       for  (int  i  =  0;  i  <  tempList.length;  i++)  {  
	           if  (path.endsWith(File.separator))  {  
	               temp  =  new  File(path  +  tempList[i]);  
	           }  
	           else  {  
	               temp  =  new  File(path  +  File.separator  +  tempList[i]);  
	           }  
	           if  (temp.isFile())  {  
	               temp.delete();  
	           }  
	           if  (temp.isDirectory())  {  
	               delAllFile(path+"/"+  tempList[i]);//先删除文件夹里面的文件  
	               delFolder(path+"/"+  tempList[i]);//再删除空文件夹  
	           }  
	       }  
	   }  

	// 要凭借用户名和文件名进行更新 文件名和时间（ 上级目录没有改 ）
	// C:\\\apace-tomcat-6.0.18\\\webapps\\\ upload\\\+
	// 思路判断是否是文件还是文件家，如果是文件 后台数据库需要凭借 用户名和旧文件名进行更新 新文件名、、时间、
	// / 如果是文件夹， 后台数据需要凭借 用户名和旧文件夹名和 root（上级目录） 进行更新 root、、文件夹名、、时间
	// path指的是root+“+”，新文件名或文件夹名 ，用户id
	// 页面请求时
	// http://localhost:8080/TopCloud/changeName.do?path=root&&oldName=333&&newName=222&&user_id=2
	@RequestMapping("changeName")
	@ResponseBody
	public int changeName(String path, String oldName, String newName,
			int id) {
		// 验证一下contoller有没有收完全
		int user_id=id;
		oldName=oldName.substring(oldName.lastIndexOf("+")+1,oldName.length()-1);
		if(path.equals("root")||path.equals("root+")){
			path = this.root;
		}else{
			path = this.root
			+ "\\"
			+ path.substring(path.indexOf('+') + 1,
					path.length());
			path = path.replace("+", "\\");
		}
		if(path.endsWith("\\")){
			path=path.substring(0,path.lastIndexOf("\\"));
		}
		System.out.println("+++++++++++++++++++" + path);
		System.out.println("+++++++++++++++++++" + oldName);
		System.out.println("+++++++++++++++++++" + newName);
		System.out.println("+++++++++++++++++++" + user_id);
		
		if(oldName.indexOf(".")==-1){
			copyFolder( path+"\\"+oldName, path+"\\"+newName);
			delFolder( path+"\\"+oldName);
			fileSystemService.changeName(path, oldName, newName, user_id);
			fileSystemService.changeDirName(path+"\\"+newName, path+"\\"+oldName, user_id);
		}else{
			copyFile(path+"\\"+oldName, path+"\\"+newName);
			delFile(path+"\\"+oldName);
			fileSystemService.changeName(path, oldName, newName, user_id);
		}
		
		return 1;
	}
//		String substring = path.substring(path.length() - 1, path.length());// 得是否是+
//		System.out.println("*******&&&&&&&&&&&&&**********" + path + "*******");
//		System.out.println("*******&&&&&&&substring&&&&&&**********"
//				+ substring + "*******");
//		if (substring.equals("+")) {
//			path = path.substring(0, path.lastIndexOf("+"));
//		}
//		System.out.println("###################>" + path);
//		if (path.equals("root")) {
//			path = this.root;
//		} else {
//			path = path.substring(path.indexOf('+')+1, path.length());
//			System.out.println("===========>" + path);
//			path = this.root + "\\" + path;
//			// path=path.substring(0, path.lastIndexOf("$")-1);
//			System.out.println("?????????????????????????" + path);
//			path = path.replace("+", "\\");
//
//			System.out.println("+++++++++++++++++++" + path + "*******");
//		}
//		System.out.println("*******&&&&&&&&&&&&&**********" + path + "*******");
//
//		if (substring.equals("+")) {
//			System.out.println("**************wen*wen*****************************");
//			System.out.println("*******&&&&&&&&&&&&&**********进入到插入文件夹这一选项");
//			fileSystemService.changeName(path, oldName, newName, user_id);
//			System.out.println("++++++++path+++++++++++" + path + "*******");
//			String newPath = path +"\\"+ newName;
//			System.out.println("++++++++newPath+++++++++++" + newPath
//					+ "*******");
//			String oldPath = path +"\\"+oldName;
//			System.out.println("+++++++++oldPath++++++++++" + oldPath
//					+ "*******");
//			fileSystemService.changeDirName(newPath, oldPath, 1);
//		} else {
//			System.out.println("*******&&&&&&&&&&&&&**********进入到插入文件这一选项");
//			// 已经判断是文件 需要调用sevice 传递参数是 旧文件名，新文件名，root路径，，用户名
//			fileSystemService.changeName(path, oldName, newName, user_id);
//		}
//		return null;
	
	// id指的是文件的标识 //oldPath是文件的上一级目录
	@RequestMapping("MoveFile")
	@ResponseBody
	public int moveFile(String newPath, String oldPath, String fileName,
			int id) {
		fileName=fileName.substring(fileName.lastIndexOf("+")+1,fileName.length()-1);
		// 检查一下受到的数据是什么
		int user_id=id;
		oldPath=oldPath.substring(0,oldPath.lastIndexOf("+"));
		System.out.println("newPath===================>" + newPath);
		System.out.println("oldPath===================>" + oldPath);
		System.out.println("fileName===================>" + fileName);
		System.out.println("user_id===================>" + user_id);
		if(newPath==""||newPath==null){
			return 0;
		}
		
		if(newPath.equals("root")||newPath.equals("root+")){
			newPath = this.root;
		}else{
			newPath = this.root
			+ "\\"
			+ newPath.substring(newPath.indexOf('+') + 1,
					newPath.length());
			newPath = newPath.replace("+", "\\");
		}
		if(oldPath.equals("root")||oldPath.equals("root+")){
			oldPath = this.root;
		}else{
			oldPath = this.root
			+ "\\"
			+ oldPath.substring(oldPath.indexOf('+') + 1,
					oldPath.length());
			oldPath = oldPath.replace("+", "\\");
			System.out.println("+++++++++oldPath++++++++++" + oldPath
					+ "*******");
		}
		if(newPath.endsWith("\\")){
		newPath=newPath.substring(0,newPath.length()-1);
		}
		System.out.println("开始对服务器文件进行移动");
		System.out.println("开始对服务器文件进行移动        "+oldPath+"\\"+fileName);
		System.out.println("开始对服务器文件进行移动        "+newPath+"\\"+fileName);
		copyFile(oldPath+"\\"+fileName,newPath+"\\"+fileName);
		delFile(oldPath+"\\"+fileName);
		 int result = fileSystemService.moveFile(newPath, oldPath, fileName, user_id);
		return result;
	}
	 public  void  delFile(String  filePathAndName)  {  
	       try  {  
	           String  filePath  =  filePathAndName;  
	           filePath  =  filePath.toString();  
	           java.io.File  myDelFile  =  new  java.io.File(filePath);  
	           myDelFile.delete();  
	 
	       }  
	       catch  (Exception  e)  {  
	           System.out.println("删除文件操作出错");  
	           e.printStackTrace();  
	 
	       }  
	 
	   }  

	 public  void  copyFile(String  oldPath,  String  newPath)  {  
	       try  {  
//	           int  bytesum  =  0;  
	           int  byteread  =  0;  
	           File  oldfile  =  new  File(oldPath);  
	           if  (oldfile.exists())  {  //文件存在时  
	               InputStream  inStream  =  new  FileInputStream(oldPath);  //读入原文件 
	               FileOutputStream  fs  =  new  FileOutputStream(newPath);  
	               byte[]  buffer  =  new  byte[1444];  
//	               int  length;  
	               while  (  (byteread  =  inStream.read(buffer))  !=  -1)  {  
//	                   bytesum  +=  byteread;  //字节数  文件大小  
//	                   System.out.println(bytesum);  
	                   fs.write(buffer,  0,  byteread);  
	               }  
	               inStream.close();  
	           }  
	       }  
	       catch  (Exception  e)  {  
	           System.out.println("复制单个文件操作出错");  
	           e.printStackTrace();  
	 
	       }  
	 
	   }  


	 @RequestMapping("download")
     public void download(
             HttpServletRequest request,
             HttpServletResponse response,String filepath) throws IOException {
         //模拟文件，myfile.txt为需要下载的文件  
		 
		 System.out.println("########################进入下载");
		 filepath=filepath.replace(" ", "+");
		 System.out.println("filepath:"+filepath);
         String path =this.root+"\\"+filepath.substring(filepath.indexOf("+")+1,filepath.length()-1);
         path = path.replace("+", "\\");
         String filename=path.substring(path.lastIndexOf("\\")+1,path.length());
         System.out.println("filename:"+filename);
         //获取输入流  
         InputStream bis = new BufferedInputStream(new FileInputStream(new File(path)));
         //转码，免得文件名中文乱码  
         filename = URLEncoder.encode(filename,"UTF-8");  
         //设置文件下载头  
         response.addHeader("Content-Disposition", "attachment;filename=" + filename);    
         //1.设置文件ContentType类型，这样设置，会自动判断下载文件类型    
         response.setContentType("multipart/form-data");   
         BufferedOutputStream out = new BufferedOutputStream(response.getOutputStream());  
         int len = 0;  
         while((len = bis.read()) != -1){  
             out.write(len);  
             out.flush();  
         }  
         out.close();  
     }
	 @RequestMapping("/loaddingdoc")
	 @ResponseBody
	 public int loaddingdoc(String path,HttpSession session){
		 System.out.println("进入文件预览="+path);
		 path=this.root+path.substring(path.indexOf("+"),path.length());
		 path=path.replace("+", "\\");
		 System.out.println("进入文件预览="+path);
		 StringBuffer buffer = new StringBuffer();
	        try {
	            BufferedReader bf= new BufferedReader(new InputStreamReader(new FileInputStream(path)));
		        String s = null;
				while((s = bf.readLine())!=null){
					//使用readLine方法，一次读一行
				    buffer.append(s.trim());
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
	        String xml = buffer.toString();
	        System.out.println("预览文件="+xml);
		 session.setAttribute("doc", xml);
		return 1;
	 }
	 @RequestMapping("/loaddingVideo")
	 @ResponseBody
	 public int loaddingVideo(String path,HttpSession session,String filename){
			InputStream is=null;
			OutputStream os = null;
			 path=this.root+path.substring(path.indexOf("+"),path.length());
			 path=path.replace("+", "\\");
			//2 Io流读写临时文件file
			try {
				 is=new FileInputStream(path);
				 os=new FileOutputStream(new File("C:\\apache-tomcat-6.0.18\\webapps\\TopCloud\\video\\"+filename));
				byte [] buffer =new byte[8*1024];
				while (true) {
					int len = is.read(buffer);
					if(len==-1) break;
					os.write(buffer ,0,len);
					
				}
			} catch (IOException e) {
				e.printStackTrace();
			}finally{
				try {
					is.close();
					os.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			} 
		return 1;
		 
	 }
	 @RequestMapping("/loaddingMusic")
	 @ResponseBody
	 public int loaddingMusic(String path,HttpSession session,String filename){
			InputStream is=null;
			OutputStream os = null;
			 path=this.root+path.substring(path.indexOf("+"),path.length());
			 path=path.replace("+", "\\");
			//2 Io流读写临时文件file
			try {
				 is=new FileInputStream(path);
				 os=new FileOutputStream(new File("C:\\apache-tomcat-6.0.18\\webapps\\TopCloud\\media\\"+filename));
				byte [] buffer =new byte[8*1024];
				while (true) {
					int len = is.read(buffer);
					if(len==-1) break;
					os.write(buffer ,0,len);
					
				}
			} catch (IOException e) {
				e.printStackTrace();
			}finally{
				try {
					is.close();
					os.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			} 
		return 1;
		 
	 }
	 
	 @RequestMapping("/loaddingPicture")
	 @ResponseBody
	 public int loaddingImg(String path,HttpSession session,String filename){
			InputStream is=null;
			OutputStream os = null;
			 path=this.root+path.substring(path.indexOf("+"),path.length());
			 path=path.replace("+", "\\");
			//2 Io流读写临时文件file
			try {
				 is=new FileInputStream(path);
				 os=new FileOutputStream(new File("C:\\apache-tomcat-6.0.18\\webapps\\TopCloud\\img\\"+filename));
				byte [] buffer =new byte[8*1024];
				while (true) {
					int len = is.read(buffer);
					if(len==-1) break;
					os.write(buffer ,0,len);
					
				}
			} catch (IOException e) {
				e.printStackTrace();
			}finally{
				try {
					is.close();
					os.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			} 
		return 1;
	 }
	 
	//按文件名进行文件搜索
		@RequestMapping("selectFileByName")
		@ResponseBody
		public List<TopCloudFile> selectFileByName(String fileName,HttpSession session){
			System.out.println(fileName);
			List<TopCloudFile> selectedFileName= fileSystemService.selectFileByName(fileName);
			System.out.println(selectedFileName);
			selectedFileName=SortUtils.desFileName(selectedFileName);
			return selectedFileName;
		}
		
		//排序
		@SuppressWarnings("unchecked")
		@RequestMapping("sortFileSystem")
		@ResponseBody
		public List<TopCloudFile> sortFileSystem(String type,HttpSession session){
			List<TopCloudFile> file =(List<TopCloudFile>) session.getAttribute("file");
			if(type.equals("time")){
				file=SortUtils.ascDateSort(file);
			}else if(type.equals("size")){
				file=SortUtils.desSizeSort(file);
			}else if(type.equals("name")){
				file=SortUtils.desFileName(file);
				}
			return file;
		}
		//查看详情页获取目标文件数据
		@RequestMapping("getFileInfoByfilename")
		@ResponseBody
		public TopCloudFile getFileInfoByfilename(int id,String filepath){
			TopCloudFile xiangQing = fileSystemService.getXiangQing(id,filepath);
			System.out.println(xiangQing);
			return xiangQing;
			
		}
		//filepath=root+show+5.mp3$
		 @RequestMapping("/linkShare")
		 @ResponseBody
		 public String linkShare(int id,String filepath){
			 System.out.println("==================>进入链接分享");
			 System.out.println("==================>获取filepath为"+filepath+"===============");
			 filepath=filepath.substring(0,filepath.lastIndexOf("$"));
			 System.out.println("==================>filepath去$得"+filepath+"===============");
			 String filename=filepath.substring(filepath.lastIndexOf("+")+1, filepath.length());
			 System.out.println("==================>得filename得"+filename+"===============");
			 //filepath=filepath.substring(0, filepath.lastIndexOf("$"));
			 filepath=this.root+filepath.substring(filepath.indexOf("+"),filepath.lastIndexOf("+"));
			 filepath=filepath.replace("+", "\\");
			 System.out.println("==================>得filepath根目录得"+filepath+"===============");
			 String MD5ID = fileSystemService.linkShare(filename, filepath, id);
			 
			return MD5ID;
			 
		 }
		
		 @RequestMapping("linkSharedownload")
	     public void linkSharedownload(
	             HttpServletRequest request,
	             HttpServletResponse response,String MD5ID) throws IOException {
			 	System.out.println("sfsdfsfsf"+MD5ID);
			 	TopCloudFile topCloudFile = fileSystemService.findLinkShareDownload(MD5ID);
			 	String filename=topCloudFile.getFilename();
			 	String path=topCloudFile.getFilepath()+"\\"+topCloudFile.getFilename();
			 	filename = URLEncoder.encode(filename,"UTF-8"); 
				InputStream is=null;
				OutputStream bos = null;
				//2 Io流读写临时文件file
				try {
					response.addHeader("Content-Disposition", "attachment;filename=" + filename);    
			         //1.设置文件ContentType类型，这样设置，会自动判断下载文件类型    
			         response.setContentType("multipart/form-data");   
						bos=response.getOutputStream();
					 is=new FileInputStream(path);
					 bos=new FileOutputStream(new File("C:\\apache-tomcat-6.0.18\\webapps\\TopCloud\\img\\"+filename));
					byte [] buffer =new byte[8*1024];
					while (true) {
						int len = is.read(buffer);
						if(len==-1) break;
						bos.write(buffer ,0,len);
						
					}
				} catch (IOException e) {
					e.printStackTrace();
				}finally{
					try {
						is.close();
						bos.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				} 
		 
	         
	        
	     }
		 
		 
}
