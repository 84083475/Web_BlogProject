package com.service;

import com.dao.UserDaoImp;
import com.model.User;

public class UserServiceImp implements IUserService{

	@Override
	public void register(User user) {
		//业务处理
		//链接数据库
		UserDaoImp udi = new UserDaoImp();
		udi.register(user);
	}
	@Override
	public User login(User user) {
		//业务处理
		String userId = user.getUserId();
		String userPassWord = user.getUserPassWord();
		//链接数据库
		UserDaoImp udi = new UserDaoImp();
		User userMessage = udi.login(userId,userPassWord);
		return userMessage;
	}
	@Override
	public void update(User user) {
		//业务处理
		//链接数据库
		UserDaoImp udi = new UserDaoImp();
		udi.update(user);
	}
	@Override
	public String search(String userId) {
		//业务处理
		//链接数据库
		UserDaoImp udi = new UserDaoImp();
		String userid=udi.search(userId);
		return userid;
	}
}
