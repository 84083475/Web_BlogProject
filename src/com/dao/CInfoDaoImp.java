package com.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.model.CInfo;

public class CInfoDaoImp implements ICInfoDao{

	public List search(int pageNumber ,int pageSize) {
		List<CInfo> cInfoList = new ArrayList();
		try {
			//JDBC数据库连接 1.加载驱动
			Class.forName("oracle.jdbc.driver.OracleDriver");
			//2.创建链接
			Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "scott", "ADMIN");
			//3.获取一个Preparestatement
			String sql = "select * from (select t1.* , rownum num  from ("
					+ "select * from campus_inform order by cid) "
					+ "t1 where rownum <="+pageNumber*pageSize+")"
					+ "where num>"+(pageNumber-1)*pageSize;
			PreparedStatement psmt = conn.prepareStatement(sql);
			
			ResultSet rs = psmt.executeQuery();
			while(rs.next()){
				CInfo cInfo = new CInfo();
				
				String userId=rs.getString(1);
				String cId=rs.getString(2);
				String cName=rs.getString(3);
				String cText=rs.getString(4);
				Date cDate = rs.getDate(5);
				int cReply=rs.getInt(6);
				int cPrise=rs.getInt(7);
				int cTransmit=rs.getInt(8);
				
				cInfo.setUserId(userId);
				cInfo.setcId(cId);
				cInfo.setcName(cName);
				cInfo.setcText(cText);
				cInfo.setcDate(cDate);
				cInfo.setcReply(cReply);
				cInfo.setcPrise(cPrise);
				cInfo.setcTransmit(cTransmit);
				
				cInfoList.add(cInfo);
			}
			return cInfoList;
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
	public int getMaxPage() {
		try {
			//JDBC数据库连接 1.加载驱动
			Class.forName("oracle.jdbc.driver.OracleDriver");
			//2.创建链接
			Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "scott", "ADMIN");
			//3.获取一个Prepeastatement 预编译sql
			String sql = "select count(*) from campus_inform";
			PreparedStatement pstm = conn.prepareStatement(sql);
			//4.执行sql
			ResultSet rs = pstm.executeQuery();
			int data=0;
			if(rs.next()){
				data = rs.getInt(1);
			}
			return data;
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return -1;
	}

}
