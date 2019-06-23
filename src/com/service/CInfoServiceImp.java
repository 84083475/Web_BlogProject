package com.service;

import java.util.ArrayList;
import java.util.List;

import com.dao.CInfoDaoImp;
import com.model.CInfo;
import com.model.CReply;
import com.model.Reply;
import com.model.User;

public class CInfoServiceImp implements ICInfoService{
	int pageSize = 5;//设置每一页显示的条数为5
	@Override
	public List search(int pageNumber) {
		
		CInfoDaoImp cid = new CInfoDaoImp();
		List cInfoList=cid.search(pageNumber,pageSize);
		return cInfoList;
	}
	@Override
	public int getMaxPage() {
		CInfoDaoImp cid = new CInfoDaoImp();
		int maxCount = cid.getMaxPage();
		int maxPage = (maxCount/pageSize)==0?maxCount/pageSize:maxCount/pageSize+1;
		return maxPage;
	}
	
	@Override
	public CInfo singleSearch(String cId) {
		
		CInfoDaoImp cid = new CInfoDaoImp();
		CInfo cInfo = cid.singleSearch(cId);
		return cInfo;
	}
	@Override
	public List searchReply(String cId) {
		List<CReply> cReplyList = new ArrayList<CReply>();
		
		//链接数据库 业务处理
		CInfoDaoImp cdi = new CInfoDaoImp();
		List<Reply> replyList =cdi.searchReply(cId);
		for(int i=0; i<replyList.size();i++){
			CReply cReply = new CReply();
			//将姓名id换成名字  顺序不变和其他数据组装到另一个list集合里面
			String userId1 = replyList.get(i).getUserId1();
			String userId2 = replyList.get(i).getUserId2();
			User user1 = cdi.searchUser(userId1);
			User user2 = cdi.searchUser(userId2);
			String userName1 = user1.getUserName();
			String userName2 = user2.getUserName();
			
			cReply.setUserId1(userId1);
			cReply.setUserId2(userId2);
			cReply.setUserName1(userName1);
			cReply.setUserName2(userName2);
			cReply.setRepContent(replyList.get(i).getRepContent());
			cReply.setRepDate(replyList.get(i).getRepDate());
			
			cReplyList.add(cReply);
		}
		return cReplyList;
	}
	@Override
	public String getRepId() {
		String replyId =  new CInfoDaoImp().getMaxRepId();
		int replyId1=0;
		if(replyId!=null){
			replyId1 = Integer.parseInt(replyId);
		}
		replyId1++;
		String id = String.valueOf(replyId1);
		return id;
	}
	@Override
	public void addData(Reply reply) {
		CInfoDaoImp cdi = new CInfoDaoImp();
		cdi.addData(reply);//在回复表里面增加一条记录
		//给文章表的回复数量加一
		String cId = reply.getArticleId();
		cdi.addReplyCount(cId);
	}

}
