package com.dao;

import com.model.User;

public interface IUserDao {
	//注册
	public void register(User user);
	//登录
	public User login(String userId);
}
