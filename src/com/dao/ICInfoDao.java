package com.dao;

import java.util.List;

import com.model.CInfo;
import com.model.Picture;
import com.model.Prise;
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
	//给点赞数据库里面增加数据
	public void clickPrise(Prise prise);
	//给点赞数据库里面减数据
	public void delClickPrise(Prise prise);
	//从数据库里面查找是否该文章被该用户点过赞
	public int checkPrise(Prise prise);
	//修改校内通知板块表里面的点赞数量
	public void updatePriseCount(int check,Prise prise);
	//查询点赞数据库  得到所有的数据
	public List searchPrise();
	//从数据拿到所有的图片地址
	public List getPicturePath();
	//拿到当前表里面最大的cid号
	public String getMaxCid();
	//增加图片存储路径信息
	public void addPicturePath(Picture picture);
	//增加校内通知文章信息
	public void addCInfo(CInfo cInfo);
	//根据文章Id删除文章表数据
	public void delCInfo(String cId);
	//根据文章id删除回复表
	public void delReply(String cId);
	//根据文章id删除点赞表
	public void delPrise(String cId);
	//根据文章id删除图片路径表
	public void delPicture(String cId);
}
