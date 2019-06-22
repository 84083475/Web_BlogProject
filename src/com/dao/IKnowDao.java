package com.dao;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

public interface IKnowDao {
	//查询所有文章
	public void search(HttpServletRequest request);
	//得到总页数
	public int getTotalPage();
	//根据页码获取每页数据
	public List getPage(int cp);

	//发表文章
	//删除文章
}
