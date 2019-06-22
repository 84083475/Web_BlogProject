package com.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.model.Article;
import com.util.JdbcUtil;

public class KnowDaoImp implements IKnowDao {
	private int pz=5;
	JdbcUtil jdbc = new JdbcUtil();
	
	@Override
	public void search(HttpServletRequest request) {
	
			int cp=1;//设置默认页码为第一页
			//当前需要展示的页码
			String currentPage = request.getParameter("cp");
			if(currentPage!=null){
				cp=Integer.parseInt(currentPage);				
			}
			//根据当前页码得到数据
			List articles = getPage(cp);
			//得到总页数
			int tp = getTotalPage();
		
			request.setAttribute("cp", cp);
			request.setAttribute("tp", tp);
			request.setAttribute("articles", articles);
		
	}

	@Override
	public int getTotalPage() {
		
/*		String sql = "select * from tb_knowledge";		
		List list = jdbc.queryPreparedStatement(sql, null, Article.class);	
		int count = list.size();

		return count%pz==0 ?count/pz :count/pz+1;*/
		
		try {
			//查询数据个数
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521/orcl", "scott", "admin");
			String sql = "select count(*) from tb_knowledge";
			
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery(sql);
			int count = 0;
			if(rs.next()){
				count = rs.getInt(1);
			}
			//返回页码数
			return count%pz==0 ?count/pz :count/pz+1;
			
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return -1;

	}

	@Override
	public List getPage(int cp) {
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521/orcl", "scott", "admin");
			String sql = "select * from ("
					+ "select e.*,rownum num from ("
					+ "select * from tb_knowledge order by postdate desc) e "
					+ "where rownum<="+pz*cp+") where num>"+(cp-1)*pz;
									
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery(sql);
			List articles = new ArrayList();
			while(rs.next()){
				
				String userId = rs.getString(1);//作者ID
				String articleId = rs.getString(2);//文章编号
				String artTitle = rs.getString(3);//文章标题
				String artContent = rs.getString(4);//文章内容
				Date postDate = rs.getDate(5);//发布日期
				int transmit = rs.getInt(6);//转发数量
				int critique = rs.getInt(6);//评论数量
				int likeUp = rs.getInt(6);//点赞数量
				
				Article article = new Article();
				article.setUserId(userId);
				article.setArticleId(articleId);
				article.setArtTitle(artTitle);
				article.setArtContent(artContent);
				article.setPostDate(postDate);
				article.setTransmit(transmit);
				article.setCritique(critique);
				article.setLikeUp(likeUp);
				
				articles.add(article);
				
			}
			return articles;
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
		
	}
}
