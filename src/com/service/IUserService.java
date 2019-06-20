package com.service;

import com.model.User;

public interface IUserService {
	//注册
	public void register(User user);
	//登录
	public User login(User user);
	//修改
	public void update(User user);
}
