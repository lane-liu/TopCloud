package com.top.cloud.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.top.cloud.bean.TopCloudUser;
import com.top.cloud.mapper.TopCloudUserMapper;
import com.top.cloud.utils.EmaiCheckCode;
import com.top.cloud.utils.MD5Util;
import com.top.cloud.utils.SendEmailUtil;

@Service
public class TopCloudUserServiceImpl implements TopCloudUserService{
	@Autowired
	private TopCloudUserMapper topCloudUserMapper;
	@Override
	public TopCloudUser login(String username) {
		TopCloudUser user = topCloudUserMapper.login(username);
		return user;
	}
	
	@Override
	public int checkedEmail(String email) {
		TopCloudUser user=topCloudUserMapper.checkedEmail(email);
		if(user==null){
			return 1;
		}
		return 0;
	}

	@Override
	public int checkedName(String username) {
		TopCloudUser user = topCloudUserMapper.login(username);
		if(user==null){
			return 1;
		}
		return 0;
	}

	@Override
	public int regist(String username, String email, String password) {
		try {
			password=MD5Util.encode(password);
		} catch (Exception e) {
			e.printStackTrace();
		}
		TopCloudUser user=new TopCloudUser();
		user.setUsername(username);
		user.setEmail(email);
		user.setPassword(password);
		int result=topCloudUserMapper.regist(user);
		return result;
	}

	@Override
	public int selectId(String username) {
		TopCloudUser topCloudUser = topCloudUserMapper.selectId(username);
		return topCloudUser.getId();
	}
	
	// 修改密码
	public Integer ChangePassword(int id, String newpassword, String oldpassword) {
	
		/*String passwordString = null;
		String password = null;*/
		System.out.println("流程到达TopCloudUserService中的ChangePassword方法中");
		System.out.println("======================>获取到的用户id为"+id);
		System.out.println("======================>获取到的用户newpassword为"+newpassword);
		System.out.println("======================>获取到的用户oldpassword为"+oldpassword);
		try {
			//用户原来的密码
			oldpassword = MD5Util.encode(oldpassword);
		    System.out.println("===============>MD5加密传递过来的用户的老密码"+oldpassword+"***********");
			newpassword = MD5Util.encode(newpassword);
		    System.out.println("===============>MD5加密传递过来的用户的新密码"+newpassword+"***********");

		} catch (Exception e) {
			e.printStackTrace();
		}
		TopCloudUser user = new TopCloudUser();
		user.setId(id);
		//user.setPassword(passwordString);
	    TopCloudUser CloudUser = topCloudUserMapper.SelectByPassword(user);
	    String trueoldpassword=CloudUser.getPassword();
	    System.out.println("===============>调用select获得的用户真实的老密码"+trueoldpassword+"***********");
		//String selectByPassword = selectByPassword2.getPassword();
		
		//System.out.println("selectByPassword-------------------->"+selectByPassword);
		//int changePassword = 0;
		if (oldpassword.equals(trueoldpassword)) {
			try {
				//newpassword = MD5Util.encode(newpassword);
			    System.out.println("===============>MD5加密传递过来的用户的新密码"+newpassword+"***********");

			} catch (Exception e) {
				e.printStackTrace();
			}
			System.out.println(newpassword+"-------------------->MD5Util.encode(newpassword)");
			TopCloudUser changeUserPassWord = new TopCloudUser();
			changeUserPassWord.setId(id);
			changeUserPassWord.setPassword(newpassword);
			int isok = topCloudUserMapper.ChangeById(changeUserPassWord);
			System.out.println("Well done");
			return isok;
		} else {
			System.out.println("rubbish");
			return 0;
		}
	}
	//http://localhost:8080/TopCloud/ChangePassword.do?id=2&newpassword=888888q&oldpassword=123123q
	//根据id获取TopCloudUser对象
	public TopCloudUser getTopCloudUser(int id) {
		TopCloudUser topCloudUser = topCloudUserMapper.getTopCloudUser(id);
		return topCloudUser;
	}
	public int updateUserInfor(int id, String sex, String address, String tel) {
		TopCloudUser infor=new TopCloudUser();
		infor.setId(id);
		infor.setSex(sex);
		infor.setAddress(address);
		infor.setTel(tel);
		int result=topCloudUserMapper.updateUserInfor(infor);
		System.out.println("================>Service中的result"+result);
		return result;
	}

	@Override
	public int CheckedOldPassWord(String oldpassword, int id) {
		TopCloudUser user=new TopCloudUser();
		user.setId(id);
		TopCloudUser selectByOldPassword = topCloudUserMapper.SelectByPassword(user);
		try {
		 oldpassword = MD5Util.encode(oldpassword);
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println(selectByOldPassword);
		boolean equals = selectByOldPassword.getPassword().equals(oldpassword);
		if(equals){
			return 1;
		}
		return 0;
		
	}
	//邮箱验证
	public String findApplicantUser(int id) {
		System.out.println("====================>findApplicantUser的id为"+id);
		TopCloudUser topCloudUser = topCloudUserMapper.getTopCloudUser(id);
		System.out.println("====================>findApplicantUser的获取用户为"+topCloudUser);
		String email = topCloudUser.getEmail();
		System.out.println("====================>findApplicantUser的获取用户getEmail为"+email);
		String emaiCheckCode = EmaiCheckCode.getEmaiCheckCode();
		System.out.println("====================>findApplicantUser的获取emaiCheckCode为"+emaiCheckCode);
		SendEmailUtil.send(email, emaiCheckCode);
		System.out.println("===================>findApplicantUser结束");
		return emaiCheckCode;
	}

	public int insertMailboxCode(String checkCode, int id) {
		
		TopCloudUser topCloudUser = topCloudUserMapper.getTopCloudUser(id);
		topCloudUser.setIs_ckeckedEmail("ture");
		int i = topCloudUserMapper.insertMailboxCode(topCloudUser);
		return i;
	}

}
