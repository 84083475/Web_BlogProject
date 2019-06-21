package com.dao;

import com.model.User;

public interface IUserDao {
	//注册
	public void register(User user);
	//登录
	public User login(String userId,String userPassWord);
	//修改
	public void update(User user);
	//修改
	public String search(String userId);
}
