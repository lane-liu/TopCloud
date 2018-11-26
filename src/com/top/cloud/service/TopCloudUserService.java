package com.top.cloud.service;

import com.top.cloud.bean.TopCloudUser;


public interface TopCloudUserService {
	public TopCloudUser login(String username);
	
	public int checkedEmail(String email);

	public int checkedName(String username);

	public int regist(String username, String email, String password);

	public int selectId(String username);
	
	public Integer ChangePassword(int id, String newpassword, String oldpassword);
	
	public TopCloudUser getTopCloudUser(int id);
	
	public int updateUserInfor(int id, String sex, String address, String tel);

	public int CheckedOldPassWord(String oldpassword, int id);
	// 修改密码
	//邮箱验证
	String findApplicantUser(int id);
	int insertMailboxCode(String checkCode ,int id);
}
