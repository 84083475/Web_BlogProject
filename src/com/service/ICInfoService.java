package com.service;

import java.util.List;

import com.model.CInfo;
import com.model.Picture;
import com.model.Prise;
import com.model.Reply;

public interface ICInfoService {
	//分页查询
	public List search(int pageNumber,String userId);
	//得到当前表格的最大页数
	public int getMaxPage();
	//通过cId查询单个文章
	public CInfo singleSearch(String cId,String userId);
	//通过cId查询该文章下面的所以回复的内容
	public List searchReply(String cId);
	//计算此时应该是第几个repId
	public String getRepId();
	//往回复表里面添加数据
	public void addData(Reply reply);
	//给点赞数据库里面增加数据
	public int clickPrise(Prise prise);
	//从数据拿到所有的图片地址
	public List getPicturePath();
	//校内通知板块  发表
	public void publish(CInfo cInfo,List<Picture> pictureList);
	//删除
	public void del(String cId);
}
