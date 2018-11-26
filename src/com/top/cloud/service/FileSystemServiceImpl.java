package com.top.cloud.service;

import java.io.File;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.top.cloud.bean.ChangeDir;
import com.top.cloud.bean.ChangeName;
import com.top.cloud.bean.MoveFile;
import com.top.cloud.bean.SelectFile;
import com.top.cloud.bean.TopCloudFile;
import com.top.cloud.mapper.FileSystemMapper;
import com.top.cloud.mapper.GarbageMapper;
import com.top.cloud.utils.MD5Util;
@Service
public class FileSystemServiceImpl implements FileSystemService {
	@Autowired
	private FileSystemMapper fieFileSystemMapper;
	@Autowired
	private GarbageMapper garbageMapper;
	private String root="C:\\apache-tomcat-6.0.18\\webapps\\upload";
	
	@Override
	public List<TopCloudFile> getFile(int id ,String root) {
		System.out.println(root);
		if(root.equals("root")||root.equals("root+")){
			root="C:\\apache-tomcat-6.0.18\\webapps\\upload";
		}else{
			root=this.root+"\\"+root.substring(root.indexOf("+")+1,root.length());
		}
		root=root.replace("+", "\\");
		if(root.endsWith("\\")){
			root=root.substring(0,root.length()-1);
		}
		System.out.println("-------------------------->获取数据路径"+root);
		SelectFile selectFile = new SelectFile(id,root);
		List<TopCloudFile> file = fieFileSystemMapper.getFile(selectFile);
		return file;
	}

	@Override
	public List<TopCloudFile> getAllfile(int id) {
		List<TopCloudFile> file = fieFileSystemMapper.getAllFile(id);
		return file;
	}
	
	public List<TopCloudFile> getAllDocument(int id) {
		System.out.println("流程到Service中的getAllDocument里面了");
		List<TopCloudFile> alldocument = fieFileSystemMapper.getAllDocument(id);
		return alldocument;
	}
		public List<TopCloudFile> getAllMusic(int id) {
		System.out.println("流程到Service中的getAllMusic里面了");
		List<TopCloudFile> allmusic = fieFileSystemMapper.getAllMusic(id);
		return allmusic;
	}

		public List<TopCloudFile> getAllVideo(int id) {
			System.out.println("流程到Service中的getAllVideo里面了");
			List<TopCloudFile> allvideo = fieFileSystemMapper.getAllVideo(id);
			return allvideo;
		}

		public List<TopCloudFile> getAllPicture(int id) {
			System.out.println("流程到Service中的getAllPicture里面了");
			List<TopCloudFile> allpicture = fieFileSystemMapper.getAllPicture(id);
			return allpicture;
		}


		@Override
		public int UpLoadFile(String root, String filename, long filesize,
				String filetype, int id) {
			TopCloudFile topCloudFile = new TopCloudFile();
			topCloudFile.setFilename(filename);
			topCloudFile.setFilepath(root);
			topCloudFile.setFilesize(filesize+"");
			topCloudFile.setFiletype(filetype);
			topCloudFile.setFilelevel(1+"");
			topCloudFile.setDir(false);
			topCloudFile.setFileuploadtime(new Date());
			topCloudFile.setUser_id(id);
			int result = fieFileSystemMapper.upLoadFile(topCloudFile);
			return result;
		}
		public Integer CrectedFolder(int id, String name, String path) {
			System.out.println(path+"-------------------------------------------------------------->");
			char[] patharr=path.toCharArray();
			int pathlevel=0;
			for (int i = 0; i < patharr.length; i++) {
				if(patharr[i]=='+'){
					pathlevel++;
				}
			}
			if(path.equals("root+")||path.equals("root")){
				path=this.root;
			}else{
				path=this.root+"\\"+path.substring(path.indexOf('+')+1,path.length()).replace("+", "\\");
			}
			if(path.endsWith("\\")){
				path=path.substring(0, path.length()-1);
			}
			System.out.println("新建文件夹——--------------------------------------》"+path);
			Date date =new Date();
			boolean isDir=true;
			String filesize="1KB";
			String filetype="dri";
			TopCloudFile topCloudFile=new TopCloudFile();
			topCloudFile.setDir(isDir);
			topCloudFile.setFilelevel(pathlevel+"");
			topCloudFile.setFilename(name);
			topCloudFile.setFilepath(path);
			topCloudFile.setFilesize(filesize);
			topCloudFile.setFiletype(filetype);
			topCloudFile.setFileuploadtime(date);
			topCloudFile.setUser_id(id);
			Integer result = fieFileSystemMapper.CrectedFolder(topCloudFile);
			String dirName = path+"\\"+name;  
			File dir = new File(dirName);
			boolean mkdirs = dir.mkdirs();
			if(mkdirs){
				System.out.println("本地文件夹创建成功!!!");
			}
			return result;
		}
		
		//删除文件或文件夹
		@Override
		public Integer deleteFile(String filename, String filepath,
				String filetype, int id) {
			System.out.println("删除的filepath:"+filepath);
			System.out.println("删除的filename:"+filename);
			filepath=filepath.replace("++", "+");
				if(filepath.equals("root+")||filepath.equals("root")){
					filepath=this.root;
				}else{
					filepath=this.root+"\\"+filepath.substring(filepath.indexOf('+')+1,filepath.length()-1).replace("+", "\\");
				}
			System.out.println(filepath);

			boolean mkdirs = true;
			
			TopCloudFile topCloudFile = new TopCloudFile();
			topCloudFile.setFilename(filename);
			topCloudFile.setFilepath(filepath);
			topCloudFile.setUser_id(id);
			
			Integer deleteFiel = 0;
			System.out.println(filetype.equals("dri"));
			//判断是否 是文件夹（目录文件）
			if (filetype.equals("dri")) {
				String dirName = filepath + "\\" + filename;
				System.out.println(dirName + "------------>dri");
				File dir = new File(dirName);
				String Cloud = filepath + "\\" + filename + "%";
				Cloud = Cloud.replace("\\", "\\\\");
				List<TopCloudFile> delFileSystemgar = garbageMapper.getDelFileSystemgar(Cloud);
				for (TopCloudFile topCloudFile2 : delFileSystemgar) {
					garbageMapper.saveFileSystem(topCloudFile2);
				}
				deleteFiel = fieFileSystemMapper.deleteCloudFile(Cloud);
				TopCloudFile selectByPathAndNameWithGarbage = fieFileSystemMapper.selectByPathAndNameWithGarbage(topCloudFile);
				 garbageMapper.saveFileSystem(selectByPathAndNameWithGarbage);
				deleteFiel = fieFileSystemMapper.deleteFile(topCloudFile);

				// 删除本地文件夹
				if (dir.exists()) {
					dAllFile(dirName);
					java.io.File myFile = new java.io.File(dirName);
					mkdirs = myFile.delete();
					if (mkdirs) {
						System.out.println("删除本地文件夹成功!!!");
					}
				} else {
					System.out.println("需要删除的本地文件夹不存在!!!");
				}
			} else {
				//删除本地文件
				String dirName = filepath + "\\" + filename;
				System.out.println(dirName);
				File dir = new File(dirName);
				if (dir.exists()) {
					TopCloudFile selectByPathAndNameWithGarbage = fieFileSystemMapper.selectByPathAndNameWithGarbage(topCloudFile);
					System.out.println("获取将要删除的文件："+selectByPathAndNameWithGarbage);
					 garbageMapper.saveFileSystem(selectByPathAndNameWithGarbage);
					deleteFiel = fieFileSystemMapper.deleteFile(topCloudFile);
					System.out.println("刪除数据库文件成功!!!");
					mkdirs = dir.delete();
					if (mkdirs) {
						System.out.println("删除本地文件成功!!!");
					}
				} else {
					System.out.println("需要删除的本地文件不存在!!!");
				}
			}
			return deleteFiel;
		}
		
		//根据文件夹路径 删除文件夹路径下的所有文件
		public static String dAllFile(String path) {
			File file = new File(path);
			String[] tempList = file.list();
			File temp = null;
			for (int i = 0; i < tempList.length; i++) {
				if (path.endsWith(File.separator)) {
					temp = new File(path + tempList[i]);
				} else {
					temp = new File(path + File.separator + tempList[i]);
				}
				if (temp.isFile()) {
					temp.delete();
				}
			}
			return "删除本地文件夹下的所有文件成功!!!";
		}
		

		//批量删除文件或文件夹
		@Override
		public Integer deleteBatchFile(String[] filenames, String[] filepaths,
				String[] filetypes, int[] ids) {
			String filename;
			String filepath;
			String filetype;
			int id;
			Integer deleteBatchFile = 0;
			for (int i = 0; i < filenames.length; i++) {
				filename = filenames[i];
				filepath = filepaths[i];
				filetype = filetypes[i];
				id = ids[0];
				 deleteBatchFile = deleteFile(filename, filepath, filetype, id);
			}
			return deleteBatchFile;
		}
		//vague模糊查询所有的user_id root（原来的root  即是传递过来的path+改变原文档名）包含指定字段的
		public List<TopCloudFile> getVagueFile(int user_id ,String root){
			SelectFile selectFile=new SelectFile(user_id, root);
			List<TopCloudFile> vagueFileList = fieFileSystemMapper.getVagueFile(selectFile);
			return vagueFileList;
		}
		//root="C:\\apache-tomcat-6.0.18\\webapps\\upload"
		//oldPath：原来的root  即是传递过来的path+改变原文档名 C:\apache-tomcat-6.0.18\webapps\8888
		//newPath：原来的root  即是传递过来的path+改变原文档名 C:\apache-tomcat-6.0.18\webapps\aaa
		public int changeDirName(String newPath,String oldPath,int user_id) {
			System.out.println("到changeDirName方法中了");
			//获取时间
			Date data=new Date();
			System.out.println("format:"+data);
			//调用mapper
			//2.获取所有的根目录是root+"\\\"+newName 的list集合
			System.out.println("++++++++++++changeDirName的地址应该是++++newPath++++"+newPath);
			System.out.println("++++++++++++changeDirName的地址应该是++++oldPath++++"+oldPath);

			oldPath=oldPath.replace("\\", "\\\\");
			System.out.println("++++++++++++模糊查询中的地址应该是++++++++"+oldPath);
			List<TopCloudFile> vagueFile = getVagueFile(user_id,oldPath);
			System.out.println("vagueFile###############"+vagueFile);
			oldPath=oldPath.replace( "\\\\", "\\");
			int result = 0;
			for (TopCloudFile topCloudFile : vagueFile) {
				System.out.println("进行修改所有模糊的文件路径");
				System.out.println("topCloudFile###############"+topCloudFile);
				String filepath = topCloudFile.getFilepath();
				System.out.println("##############原来条目中地址"+filepath);
				System.out.println("##############替换后的部分地址"+newPath);
				System.out.println("##############想替换的地址"+oldPath);

				filepath = filepath.replace(oldPath, newPath);
				System.out.println("####################改后filepath为"+filepath);
				//.replace("\\", "\\\\")
				ChangeDir changeDir=new ChangeDir(user_id, filepath, topCloudFile.getFilepath(),new Date());
				 result = fieFileSystemMapper.changeDirName(changeDir);
			}
			return result;
//			//ChangeName changeDirName=new ChangeName(newPath, oldPath, data);
			//fieFileSystemMapper.changeDirName(changeDirName);
		}
		public int moveFile(String newPath, String oldPath, String fileName,int user_id) {
			System.out.println("到moveFile方法中了");
			//获取时间
			Date data=new Date();
			System.out.println("format:"+data);
			System.out.println("++++++++++++moveFile的地址应该是++++newPath++++"+newPath);
			System.out.println("++++++++++++moveFile的地址应该是++++oldPath++++"+oldPath);
			MoveFile moveFile =new MoveFile(newPath, oldPath, fileName, user_id,new Date());
			int result = fieFileSystemMapper.moveFile(moveFile);
			return result;
		}
		/**以确定是文件的情况下
		 * service业务中需要获取controller传过来的参数，还需要设置时间传递过去
		 * */
		public int changeName(String root, String oldName, String newName,
				int user_id) {
			//获取时间
			System.out.println("&&^&&&&&&&&&&&到插文件夹里的文件修改");
			Date data=new Date();
			System.out.println("format:"+data);
			//调用mapper
			ChangeName changeName=new ChangeName(root, oldName, newName, user_id, data);
			int result = fieFileSystemMapper.changeFileName(changeName);
			return result;
		}

		//按文件名进行文件搜索
		@Override
		public List<TopCloudFile> selectFileByName(String fileName) {
			System.out.println("流程至Service中的selectFileByName");
			List<TopCloudFile> selectedFileName=fieFileSystemMapper.selectedFileName(fileName);
			return selectedFileName;
		}
		
		//分享链接1.1根据文件名，文件更目录，用户名找到file对象 1.2
		public String linkShare(String filename, String filepath, int user_id) {
			 System.out.println("==================>获取linkShare为");
			 System.out.println("==================>获取filename为"+filename+"===============");
			 System.out.println("==================>获取filepath为"+filepath+"===============");
			 System.out.println("==================>获取user_id为"+user_id+"===============");

			TopCloudFile findFile=new TopCloudFile();
			findFile.setFilename(filename);
			findFile.setFilepath(filepath);
			findFile.setUser_id(user_id);
			//1.1根据文件名，文件更目录，用户名找到file对象
			TopCloudFile findLinkShare = fieFileSystemMapper.findLinkShare(findFile);
			 System.out.println("==================>获取findLinkShare文件为"+findLinkShare);
			//TopCloudFile findLinkShare = findLinkShare(findFile);
			if(findLinkShare==null){
				return null;
			}
			String fileID=String.valueOf(findLinkShare.getId());
			String md5id =null;
			try {
				md5id = MD5Util.encode(fileID);
				System.out.println("==================>md5id为"+md5id);
			} catch (Exception e) {
				e.printStackTrace();
			}
			md5id=md5id.replace("+", "1");
			md5id=md5id.replace("", "1");
			md5id=md5id.replace("/", "1");
			md5id=md5id.replace("?", "1");
			md5id=md5id.replace("%", "1");
			md5id=md5id.replace("#", "1");
			md5id=md5id.replace("&", "1");
			md5id=md5id.replace("=", "1");
			System.out.println("==================>md5id为"+md5id+"========");
			findLinkShare.setMD5ID(md5id);
			//将md5id插入到数据库
			int linkShare = fieFileSystemMapper.insertLinkShare(findLinkShare);
			if(linkShare==1){
				System.out.println("===========>插入成功");
			}
			return md5id;
		}

		@Override
		public TopCloudFile findLinkShareDownload(String mD5ID) {
			TopCloudFile file = fieFileSystemMapper.findLinkShareDownload(mD5ID);
			return file;
		}

		@Override
		public TopCloudFile getXiangQing(int id, String filepath) {
			TopCloudFile topCloudFile=new TopCloudFile();
			 filepath=filepath.substring(0,filepath.lastIndexOf("$"));
			 String filename=filepath.substring(filepath.lastIndexOf("+")+1, filepath.length());
			 filepath=this.root+filepath.substring(filepath.indexOf("+"),filepath.lastIndexOf("+"));
			 filepath=filepath.replace("+", "\\");
			 topCloudFile.setUser_id(id);
			 topCloudFile.setFilename(filename);
			 topCloudFile.setFilepath(filepath);
			TopCloudFile xiangQing = fieFileSystemMapper.getXiangQing(topCloudFile);
			return xiangQing;
		}
}

