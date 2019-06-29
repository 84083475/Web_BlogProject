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
import com.model.Picture;
import com.model.Prise;
import com.model.Reply;
import com.model.User;

public class CInfoDaoImp implements ICInfoDao{

	public List search(int pageNumber ,int pageSize) {
		List<CInfo> cInfoList = new ArrayList();
		try {
			//JDBC数据库连接 1.加载驱动
			Class.forName("oracle.jdbc.driver.OracleDriver");
			//2.创建链接
			Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "scott", "admin");
			//3.获取一个Preparestatement
			String sql = "select * from (select t1.* , rownum num  from ("
					+ "select * from tb_article where articleType = 1 order by postDate) "
					+ "t1 where rownum <="+pageNumber*pageSize+")"
					+ "where num>"+(pageNumber-1)*pageSize;
			PreparedStatement psmt = conn.prepareStatement(sql);
			
			ResultSet rs = psmt.executeQuery();
			while(rs.next()){
				CInfo cInfo = new CInfo();
				
				String userId=rs.getString(1);
				int cId=rs.getInt(2);
				String cName=rs.getString(3);
				String cText=rs.getString(4);
				Date cDate = rs.getDate(5);
				int cReply=rs.getInt(6);
				int cPrise=rs.getInt(7);
				
				cInfo.setUserId(userId);
				cInfo.setcId(cId);
				cInfo.setcName(cName);
				cInfo.setcText(cText);
				cInfo.setcDate(cDate);
				cInfo.setcReply(cReply);
				cInfo.setcPrise(cPrise);
				
				cInfoList.add(cInfo);
			}
			
			if(conn!=null){
				conn.close();
			}
			if(psmt!=null){
				psmt.close();
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
			Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "scott", "admin");
			//3.获取一个Prepeastatement 预编译sql
			String sql = "select count(*) from tb_article";
			PreparedStatement pstm = conn.prepareStatement(sql);
			//4.执行sql
			ResultSet rs = pstm.executeQuery();
			int data=0;
			if(rs.next()){
				data = rs.getInt(1);
			}
			
			if(conn!=null){
				conn.close();
			}
			if(pstm!=null){
				pstm.close();
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

	@Override
	public CInfo singleSearch(String cId) {
		CInfo cInfo = new CInfo();
		try {
			//JDBC数据库连接 1.加载驱动
			Class.forName("oracle.jdbc.driver.OracleDriver");
			//2.创建链接
			Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "scott", "admin");
			//3.获取一个Prepeastatement 预编译sql
			String sql = "select * from tb_article where articleId = ?";
			PreparedStatement pstm = conn.prepareStatement(sql);
			pstm.setObject(1, cId);
			//4.执行sql
			ResultSet rs = pstm.executeQuery();
			if(rs.next()){
				String userId=rs.getString(1);
				int cId1=rs.getInt(2);
				String cName=rs.getString(3);
				String cText=rs.getString(4);
				Date cDate = rs.getDate(5);
				int cReply=rs.getInt(6);
				int cPrise=rs.getInt(7);
				int cTransmit=rs.getInt(8);
				
				cInfo.setUserId(userId);
				cInfo.setcId(cId1);
				cInfo.setcName(cName);
				cInfo.setcText(cText);
				cInfo.setcDate(cDate);
				cInfo.setcReply(cReply);
				cInfo.setcPrise(cPrise);
				cInfo.setcTransmit(cTransmit);
			}
			
			if(conn!=null){
				conn.close();
			}
			if(pstm!=null){
				pstm.close();
			}
			return cInfo;
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
	public List searchReply(String cId) {
		List replyList = new ArrayList();
		try {
			//JDBC数据库连接 1.加载驱动
			Class.forName("oracle.jdbc.driver.OracleDriver");
			//2.创建链接
			Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "scott", "admin");
			//3.获取一个Prepeastatement 预编译sql
			String sql = "select * from tb_reply where articleId = ? order by repId";
			PreparedStatement pstm = conn.prepareStatement(sql);
			pstm.setObject(1, cId);
			//4.执行sql
			ResultSet rs = pstm.executeQuery();
			while(rs.next()){
				Reply reply = new Reply();
				
				String repId = rs.getString(1);
				String repContent = rs.getString(2);
				Date repDate = rs.getDate(3);
				String userId1 = rs.getString(4);
				String userId2 = rs.getString(5);
				String articleId = rs.getString(6);
				
				reply.setRepId(Integer.parseInt(repId));
				reply.setRepContent(repContent);
				reply.setRepDate(repDate);
				reply.setUserId1(userId1);
				reply.setUserId2(userId2);
				reply.setArticleId(articleId);
				
				replyList.add(reply);
			}
			
			if(conn!=null){
				conn.close();
			}
			if(pstm!=null){
				pstm.close();
			}
			return replyList;
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
	public User searchUser(String userId) {
		User userMessage = new User();
		try {
			//JDBC数据库连接 1.加载驱动
			Class.forName("oracle.jdbc.driver.OracleDriver");
			//2.创建链接
			Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "scott", "admin");
			//3.获取一个Prepeastatement 预编译sql
			String sql = "select * from tb_user where userId = ?";
			PreparedStatement pstm = conn.prepareStatement(sql);
			pstm.setObject(1, userId);
			//4.执行sql
			ResultSet rs = pstm.executeQuery();
			if(rs.next()){
				userMessage.setUserName(rs.getString(1));
				userMessage.setUserId(rs.getString(2));
				userMessage.setUserPassWord(rs.getString(3));
			}
			
			if(conn!=null){
				conn.close();
			}
			if(pstm!=null){
				pstm.close();
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
	public String getMaxRepId() {
		try {
			//JDBC数据库连接 1.加载驱动
			Class.forName("oracle.jdbc.driver.OracleDriver");
			//2.创建链接
			Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "scott", "admin");
			//3.获取一个Prepeastatement 预编译sql
			String sql = "select max(repId) from tb_reply ";
			PreparedStatement pstm = conn.prepareStatement(sql);
			//4.执行sql
			ResultSet rs = pstm.executeQuery();
			String maxReplyId = null;
			if(rs.next()){
				maxReplyId = rs.getString(1);
			}
			
			if(conn!=null){
				conn.close();
			}
			if(pstm!=null){
				pstm.close();
			}
			return maxReplyId;
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
	public void addData(Reply reply) {
		int repId =reply.getRepId();
	    String repContent = reply.getRepContent();
	    Date repDate = new Date(reply.getRepDate().getTime());
	    String userId1 = reply.getUserId1();
	    String userId2 = reply.getUserId2();
	    String articleId = reply.getArticleId();
	    try {
			//JDBC数据库连接 1.加载驱动
			Class.forName("oracle.jdbc.driver.OracleDriver");
			//2.创建链接
			Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "scott", "admin");
			//3.获取一个Prepeastatement 预编译sql
			String sql = "insert into tb_reply (repid, repcontent, repdate, userid1, userid2, articleid) values (?, ?, ?, ?, ?, ?)";
			PreparedStatement pstm = conn.prepareStatement(sql);
			pstm.setObject(1,repId);
			pstm.setObject(2, repContent);
			pstm.setObject(3, repDate);
			pstm.setObject(4, userId1);
			pstm.setObject(5, userId2);
			pstm.setObject(6, articleId);
			//4.执行sql
			pstm.execute();
			
			if(conn!=null){
				conn.close();
			}
			if(pstm!=null){
				pstm.close();
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void addReplyCount(String cId) {
		try {
			//JDBC数据库连接 1.加载驱动
			Class.forName("oracle.jdbc.driver.OracleDriver");
			//2.创建链接
			Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "scott", "admin");
			//3.获取一个Prepeastatement 预编译sql
			String sql = "select Reply from tb_article where articleId = ?";
			PreparedStatement pstm = conn.prepareStatement(sql);
			pstm.setObject(1, cId);
			//4.执行sql
			ResultSet rs = pstm.executeQuery();
			int cReply = -1;
			if(rs.next()){
				cReply=Integer.parseInt(rs.getString(1));
				cReply++;
			}
			String sql1 = "update tb_article set reply = ? where articleId = ?";
			PreparedStatement pstm1 = conn.prepareStatement(sql1);
			pstm1.setObject(1, cReply);
			pstm1.setObject(2, cId);
			pstm1.execute();
			
			if(conn!=null){
				conn.close();
			}
			if(pstm!=null){
				pstm.close();
			}
			if(pstm1!=null){
				pstm1.close();
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void clickPrise(Prise prise) {
		
		try {
			//JDBC数据库连接 1.加载驱动
			Class.forName("oracle.jdbc.driver.OracleDriver");
			//2.创建链接
			Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "scott", "admin");
			//3.获取一个Prepeastatement 预编译sql
			String sql = "insert into tb_prise ( articleid, userid) values ( ?, ?)";
			PreparedStatement pstm = conn.prepareStatement(sql);
			
			String articleId = prise.getArticleId();
			String userId = prise.getUserId();
			pstm.setObject(1, articleId);
			pstm.setObject(2, userId);
			
			pstm.execute();
			if(conn!=null){
				conn.close();
			}
			if(pstm!=null){
				pstm.close();
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public int checkPrise(Prise prise) {
		Connection conn=null;
		PreparedStatement pstm=null;
		try {
			//JDBC数据库连接 1.加载驱动
			Class.forName("oracle.jdbc.driver.OracleDriver");
			//2.创建链接
			conn= DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "scott", "admin");
			//3.获取一个Prepeastatement 预编译sql
			String sql = "select * from tb_prise where articleId = ? and userId = ?";
			pstm = conn.prepareStatement(sql);
			
			String articleId = prise.getArticleId();
			String userId = prise.getUserId();
			pstm.setObject(1, articleId);
			pstm.setObject(2, userId);
			
			ResultSet rs = pstm.executeQuery();	
			if(rs.next()){
				return -1;
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			try {
				if(conn!=null){
					conn.close();
				}
				if(pstm!=null){
					pstm.close();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return 1;
	}

	@Override
	public void delClickPrise(Prise prise) {
		try {
			//JDBC数据库连接 1.加载驱动
			Class.forName("oracle.jdbc.driver.OracleDriver");
			//2.创建链接
			Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "scott", "admin");
			//3.获取一个Prepeastatement 预编译sql
			String sql = "delete tb_prise where articleId = ? and userId = ?";
			PreparedStatement pstm = conn.prepareStatement(sql);
			
			String articleId = prise.getArticleId();
			String userId = prise.getUserId();
			pstm.setObject(1, articleId);
			pstm.setObject(2, userId);
			
			pstm.execute();
			if(conn!=null){
				conn.close();
			}
			if(pstm!=null){
				pstm.close();
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override 
	public void updatePriseCount(int check,Prise prise) {
		String cId = prise.getArticleId();
		int priseCount = 0;
		try {
			//JDBC数据库连接 1.加载驱动
			Class.forName("oracle.jdbc.driver.OracleDriver");
			//2.创建链接
			Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "scott", "admin");
			//3.获取一个Prepeastatement 预编译sql
			String sql = "select Prise from tb_article where articleId = ?";
			PreparedStatement pstm = conn.prepareStatement(sql);
			pstm.setObject(1, cId);
			ResultSet rs = pstm.executeQuery();
			if(rs.next()){
				priseCount = rs.getInt(1);
			}
			
			if(check==1){
				priseCount++;
				String sql1 = "update tb_article set Prise = ? where articleId = ?";
				PreparedStatement pstm1 = conn.prepareStatement(sql1);
				pstm1.setObject(1, priseCount);
				pstm1.setObject(2, cId);
				pstm1.execute();
				
				if(pstm1!=null){
					pstm1.close();
				}
			}else{
				priseCount--;
				String sql1 = "update tb_article set Prise = ? where articleId = ?";
				PreparedStatement pstm1 = conn.prepareStatement(sql1);
				pstm1.setObject(1, priseCount);
				pstm1.setObject(2, cId);
				pstm1.execute();
				
				if(pstm1!=null){
					pstm1.close();
				}
			}
			if(conn!=null){
				conn.close();
			}
			if(pstm!=null){
				pstm.close();
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public List searchPrise() {
		List priseList = new ArrayList();
		try {
			//JDBC数据库连接 1.加载驱动
			Class.forName("oracle.jdbc.driver.OracleDriver");
			//2.创建链接
			Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "scott", "admin");
			//3.获取一个Prepeastatement 预编译sql
			String sql = "select * from tb_prise";
			PreparedStatement pstm = conn.prepareStatement(sql);
			ResultSet rs = pstm.executeQuery();
			while(rs.next()){
				Prise prise = new Prise();
				prise.setpId(rs.getInt(1));
				prise.setArticleId(rs.getString(2));
				prise.setUserId(rs.getString(3));
				priseList.add(prise);
			}
			
			if(conn!=null){
				conn.close();
			}
			if(pstm!=null){
				pstm.close();
			}
			return priseList;
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
	public List getPicturePath() {
		List pictureList = new ArrayList();
		try {
			//JDBC数据库连接 1.加载驱动
			Class.forName("oracle.jdbc.driver.OracleDriver");
			//2.创建链接
			Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "scott", "admin");
			//3.获取一个Prepeastatement 预编译sql
			String sql = "select * from tb_picture";
			PreparedStatement pstm = conn.prepareStatement(sql);
			ResultSet rs = pstm.executeQuery();
			while(rs.next()){
				Picture picture = new Picture();
				picture.setpId(rs.getInt(1));
				picture.setPicturePath(rs.getString(2));
				picture.setArticleId(Integer.parseInt(rs.getString(3)));
				pictureList.add(picture);
			}
			if(conn!=null){
				conn.close();
			}
			if(pstm!=null){
				pstm.close();
			}
			return pictureList;
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
	public String getMaxCid() {
		try {
			//JDBC数据库连接 1.加载驱动
			Class.forName("oracle.jdbc.driver.OracleDriver");
			//2.创建链接
			Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "scott", "admin");
			//3.获取一个Prepeastatement 预编译sql
			String sql = "select Max(articleId) from tb_article";
			PreparedStatement pstm = conn.prepareStatement(sql);
			ResultSet rs = pstm.executeQuery();
			String cId = null;
			if(rs.next()){
				cId = rs.getInt(1)+"";
			}
			if(conn!=null){
				conn.close();
			}
			if(pstm!=null){
				pstm.close();
			}
			return cId;
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
	public void addPicturePath(Picture picture) {
		try {
			//JDBC数据库连接 1.加载驱动
			Class.forName("oracle.jdbc.driver.OracleDriver");
			//2.创建链接
			Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "scott", "admin");
			//3.获取一个Prepeastatement 预编译sql
			String sql = "insert into tb_picture (picturepath, articleid) values(?, ?)";
			PreparedStatement pstm = conn.prepareStatement(sql);
			pstm.setObject(1, picture.getPicturePath());
			pstm.setObject(2, picture.getArticleId());
			pstm.execute();
			if(conn!=null){
				conn.close();
			}
			if(pstm!=null){
				pstm.close();
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

	@Override
	public void addCInfo(CInfo cInfo) {
		try {
			//JDBC数据库连接 1.加载驱动
			Class.forName("oracle.jdbc.driver.OracleDriver");
			//2.创建链接
			Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "scott", "admin");
			//3.获取一个Prepeastatement 预编译sql
			String sql = "insert into tb_article (userid, articleId, artTitle, artContent, postDate, reply, prise, articleType) values (?, ?, ?, ?, ?, ?, ?, 1)";
			PreparedStatement pstm = conn.prepareStatement(sql);
			pstm.setObject(1, cInfo.getUserId());
			pstm.setObject(2, cInfo.getcId());
			pstm.setObject(3, cInfo.getcName());
			pstm.setObject(4, cInfo.getcText());
			pstm.setObject(5, new java.sql.Date(cInfo.getcDate().getTime()));
			pstm.setObject(6, cInfo.getcReply());
			pstm.setObject(7, cInfo.getcPrise());
			pstm.execute();
			if(conn!=null){
				conn.close();
			}
			if(pstm!=null){
				pstm.close();
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void delCInfo(String cId) {
		try {
			//JDBC数据库连接 1.加载驱动
			Class.forName("oracle.jdbc.driver.OracleDriver");
			//2.创建链接
			Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "scott", "admin");
			//3.获取一个Prepeastatement 预编译sql
			String sql = "delete tb_article where articleId = ?";
			PreparedStatement pstm = conn.prepareStatement(sql);
			pstm.setObject(1, cId);
			pstm.execute();
			if(conn!=null){
				conn.close();
			}
			if(pstm!=null){
				pstm.close();
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void delReply(String cId) {
		try {
			//JDBC数据库连接 1.加载驱动
			Class.forName("oracle.jdbc.driver.OracleDriver");
			//2.创建链接
			Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "scott", "admin");
			//3.获取一个Prepeastatement 预编译sql
			String sql = "delete tb_reply where articleId = ?";
			PreparedStatement pstm = conn.prepareStatement(sql);
			pstm.setObject(1, cId);
			pstm.execute();
			if(conn!=null){
				conn.close();
			}
			if(pstm!=null){
				pstm.close();
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void delPrise(String cId) {
		try {
			//JDBC数据库连接 1.加载驱动
			Class.forName("oracle.jdbc.driver.OracleDriver");
			//2.创建链接
			Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "scott", "admin");
			//3.获取一个Prepeastatement 预编译sql
			String sql = "delete tb_prise where articleId = ?";
			PreparedStatement pstm = conn.prepareStatement(sql);
			pstm.setObject(1, cId);
			pstm.execute();
			if(conn!=null){
				conn.close();
			}
			if(pstm!=null){
				pstm.close();
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void delPicture(String cId) {
		try {
			//JDBC数据库连接 1.加载驱动
			Class.forName("oracle.jdbc.driver.OracleDriver");
			//2.创建链接
			Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "scott", "admin");
			//3.获取一个Prepeastatement 预编译sql
			String sql = "delete tb_picture where articleId = ?";
			PreparedStatement pstm = conn.prepareStatement(sql);
			pstm.setObject(1, cId);
			pstm.execute();
			if(conn!=null){
				conn.close();
			}
			if(pstm!=null){
				pstm.close();
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
		

