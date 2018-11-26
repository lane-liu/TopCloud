package com.top.cloud.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.top.cloud.bean.TopCloudUser;
import com.top.cloud.service.TopCloudUserService;
import com.top.cloud.utils.MD5Util;

@Controller
public class TopCloudUserCollerL {
	@Autowired
	private TopCloudUserService topCloudUserService;
	@RequestMapping("/login")
	public String selectUserByNP(String username,String password,String ckeckedNum,HttpSession session){
		System.out.println(username);
		System.out.println(password);
		String number=(String) session.getAttribute("number");
		System.out.println(number);
		TopCloudUser user = topCloudUserService.login(username);
		String password2 = user.getPassword();
		System.out.println(password2);
		try {
			password=MD5Util.encode(password);
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("oldPassword"+user.getPassword());
		System.out.println("NewPassword"+password);
		if(password.equals(user.getPassword())){
			if(number.equals(ckeckedNum)){
				return "redirect:index.html?id="+user.getId();
			}else{
				return "redirect:login.html";
			}
				
		}else {
			return "redirect:login.html";
		}
	}
	
	@RequestMapping("/checkedEmail")
	@ResponseBody
	public int  checkedEmail(String email){
		int result = topCloudUserService.checkedEmail(email);
		System.out.println("checkedEmail                   ----------------->result="+result);
		return result;
	}
	@RequestMapping("/checkedName")
	@ResponseBody
	public int  checkedName(String name){
		int result = topCloudUserService.checkedName(name);
		System.out.println("checkedName                   ----------------->result="+result);
		return result;
		
		
	}
	
	@RequestMapping("/regist")
	@ResponseBody
	public int  regist(String username,String email,String password){
		System.out.println("注册：——---------------------------------------------------->"+username);
	int result=	topCloudUserService.regist(username,email,password);
		return result;
		
	}
	@RequestMapping("/checkCode")
	@ResponseBody
	public int  checkCode(String verifyCode ,HttpSession session){
		System.out.println("checkCode                   ----------------->verifyCode="+verifyCode);
		String object =(String) session.getAttribute("number");
		if(verifyCode.equals(object)){
			return 1;
			
		}
		return 0;
	}
	
	//修改密码	@ResponseBody
	@RequestMapping("/ChangePassword")
	public String ChangePassword(int id,String newpassword,String oldpassword){
		System.out.println("流程到达TopCloudUserContriller中的ChangePassword方法中");
		System.out.println("======================>获取到的用户id为"+id);
		System.out.println("======================>获取到的用户newpassword为"+newpassword);
		System.out.println("======================>获取到的用户oldpassword为"+oldpassword);
		 topCloudUserService.ChangePassword(id,newpassword,oldpassword);
		 return "redirect:remima.jsp?id="+id;
	}
	
	//个人系统
		@RequestMapping("getTopCloudUser")
		@ResponseBody
		public TopCloudUser getTopCloudUser(int id ,HttpSession session){
			System.out.println("======================>流程到getTopCloudUser");
			TopCloudUser topCloudUser = topCloudUserService.getTopCloudUser(id);
			System.out.println("======================>topCloudUser"+topCloudUser); 
			Integer attribute =(Integer) session.getAttribute("filesystemsize");
			if(attribute!=0){
				Integer a=1024*1024*1024-attribute;
				topCloudUser.setSurplusdevicespace(a.toString());	
			}
			
			return topCloudUser;
		}
		//修改个人信息
		@RequestMapping("UpdateUserInfor")
		public String updateUserInfor(int id, String sex, String address, String tel) {
			System.out.println("流程到达UserContriller中的updateUserInfor方法中");
			System.out.println("======================>获取到的用户id"+id);
			System.out.println("======================>获取到的用户sex"+sex);
			System.out.println("======================>获取到的用户address"+address);
			System.out.println("======================>获取到的用户tel"+tel);
			int result=topCloudUserService.updateUserInfor(id, sex, address, tel);
			System.out.println("================>"+result);
			return "redirect:mygrxx.jsp?id="+id;
		}
		
		@RequestMapping("CheckedOldPassWord")
		@ResponseBody
		public int CheckedOldPassWord(String oldpassword,int id){
			int result = topCloudUserService.CheckedOldPassWord(oldpassword,id);
			return result;
			
		}
		//修改邮箱验证
		@RequestMapping("Sendmailbox")
		 @ResponseBody
		 public String Sendmailbox(int id ,HttpSession session){
			System.out.println("=================>Sendmailbox中的id为"+id);
			String emailCheckCode = topCloudUserService.findApplicantUser(id);
			System.out.println("=================>Sendmailbox中的emailCheckCode为"+emailCheckCode);
			session.setAttribute("emailCheckCode", emailCheckCode);
			return "1";
		 }
		 @RequestMapping("Checkmailboxcode")
		 @ResponseBody
		 public int Checkmailboxcode(String checkCode ,int id,HttpSession session){
			String emailCheckCode=(String) session.getAttribute("emailCheckCode");
			int update=0;
			if(emailCheckCode.equals(checkCode)){
				update = topCloudUserService.insertMailboxCode(emailCheckCode, id);
			}
			return update;
		}
		
}