package com.top.cloud.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.top.cloud.bean.User;
import com.top.cloud.mapper.UserMapper;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	private UserMapper usermapper;
	@Override
	public User selectUserById(int id) {
		User user = usermapper.selectUserById(id);
		return user;
	}

}
