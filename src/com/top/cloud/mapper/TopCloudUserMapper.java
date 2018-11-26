package com.top.cloud.mapper;

import com.top.cloud.bean.TopCloudUser;


public interface TopCloudUserMapper {
	//登陆
	TopCloudUser login(String username);

	TopCloudUser checkedEmail(String email);

	int regist(TopCloudUser user);

	TopCloudUser selectId(String username);
	

	//通过用户ID修改用户数据数据
	int ChangeById(TopCloudUser topCloudUser);
	
	
	//通过id查数据
	public TopCloudUser getTopCloudUser(int id);

	TopCloudUser SelectByPassword(TopCloudUser user);

	int updateUserInfor(TopCloudUser infor);
	int insertMailboxCode(TopCloudUser topCloudUser);
	
}
