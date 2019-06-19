package com.dao;

import java.util.ArrayList;
import java.util.List;

import com.model.User;
import com.util.JdbcUtil;

public class UserDaoImp implements IUserDao{

	@Override
	public void register(User user) {
		
		List list = new ArrayList();
		list.add(user.getUserName());
		list.add(user.getUserId());
		list.add(user.getUserPassWord());
		
		String sql = "insert into tb_user (user_name, user_id, user_password) values (?, ?, ?)";
		new JdbcUtil().updatePreparedStatement(sql, list);
		
	}
}
