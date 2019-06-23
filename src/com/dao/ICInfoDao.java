package com.dao;

import java.util.List;

import com.model.CInfo;
import com.model.Reply;
import com.model.User;

public interface ICInfoDao {
	//分页查询
	public List search(int pageNumber ,int pageSize);
	//得到当前表格的最大页数
	public int getMaxPage();
	//通过cId查询单个文章
	public CInfo singleSearch(String cId);
	//通过cId查询该文章下面的所以回复的内容
	public List searchReply(String cId);
	//通过用户id查用户
	public User searchUser(String userId);
	//查询回复表中此时最大的repId（回复id）并返回出来
	public String getMaxRepId();
	//往回复表里面添加数据
	public void addData(Reply reply);
	//给校内通知板块表里面的评论数量加1
	public void addReplyCount(String cId);
}
