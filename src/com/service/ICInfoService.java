package com.service;

import java.util.List;

import com.model.CInfo;
import com.model.Reply;

public interface ICInfoService {
	//分页查询
	public List search(int pageNumber);
	//得到当前表格的最大页数
	public int getMaxPage();
	//通过cId查询单个文章
	public CInfo singleSearch(String cId);
	//通过cId查询该文章下面的所以回复的内容
	public List searchReply(String cId);
	//计算此时应该是第几个repId
	public String getRepId();
	//往回复表里面添加数据
	public void addData(Reply reply);
}
