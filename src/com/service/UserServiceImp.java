package com.service;

import com.dao.UserDaoImp;
import com.model.User;

public class UserServiceImp implements IUserService{
	private UserDaoImp udi = new UserDaoImp();
	@Override
	public void register(User user) {
		//业务处理
		//链接数据库
		udi.register(user);
	}
}
