package com.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
		
		String sql = "insert into tb_user (userName, userId, userPassWord) values (?, ?, ?)";
		new JdbcUtil().updatePreparedStatement(sql, list);
		
	}

	@Override
	public User login(String userId) {
		User userMessage = new User();
		try {
			//JDBC数据库连接 1.加载驱动
			Class.forName("oracle.jdbc.driver.OracleDriver");
			//2.创建链接
			Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "scott", "ADMIN");
			//3.获取一个Preparestatement
			
			String sql ="select * from tb_user where userId= ?";
			PreparedStatement psmt = conn.prepareStatement(sql);
			psmt.setObject(1, userId);
			
			ResultSet rs = psmt.executeQuery();
			if(rs.next()){
				userMessage.setUserName(rs.getString(1));
				userMessage.setUserId(rs.getString(2));
				userMessage.setUserPassWord(rs.getString(3));
			}
			return userMessage;
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void update(User user) {
		List list = new ArrayList();
		list.add(user.getUserName());
		list.add(user.getUserPassWord());
		list.add(user.getUserId());
		
		String sql = "update tb_user set username = ?,userpassword = ? where userId=?";
		new JdbcUtil().updatePreparedStatement(sql, list);
	}
}
