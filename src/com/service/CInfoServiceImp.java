package com.service;

import java.util.ArrayList;
import java.util.List;

import com.dao.CInfoDaoImp;
import com.model.CInfo;
import com.model.CReply;
import com.model.Picture;
import com.model.Prise;
import com.model.Reply;
import com.model.User;

public class CInfoServiceImp implements ICInfoService{
	int pageSize = 5;//设置每一页显示的条数为5
	@Override
	public List search(int pageNumber,String userId) {
		
		CInfoDaoImp cid = new CInfoDaoImp();
		List<CInfo> cInfoList=cid.search(pageNumber,pageSize);  //得到所有的文章的内容
		List<Prise> priseList = cid.searchPrise();  //得到所有的点赞信息
		//判断当前用户是否给该篇文章点过赞   如果点过赞 就把点赞状态设置为1  否则设置为0
		List<String> priseArticleId = new ArrayList<String>();
		for(int i=0;i<priseList.size();i++){
			if(priseList.get(i).getUserId().equals(userId)){
				priseArticleId.add(priseList.get(i).getArticleId());
			}
		}
		for(int i=0;i<priseArticleId.size();i++){
			for(int j=0;j<cInfoList.size();j++){
				if(priseArticleId.get(i).equals(String.valueOf(cInfoList.get(j).getcId()))){
					cInfoList.get(j).setcStatus(1);
				}else{
					if(cInfoList.get(j).getcStatus()!=1){
						cInfoList.get(j).setcStatus(0);
					}
				}
			}
		}
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
	public CInfo singleSearch(String cId,String userId) {
		
		CInfoDaoImp cid = new CInfoDaoImp();
		CInfo cInfo = cid.singleSearch(cId);
		//判断当前用户是否给该篇文章点过赞   如果点过赞 就把点赞状态设置为1  否则设置为0
		List<Prise> priseList = cid.searchPrise();   //得到所有的点赞信息
		List<String> priseArticleId = new ArrayList<String>();
		for(int i=0;i<priseList.size();i++){
			if(priseList.get(i).getUserId().equals(userId)){
				priseArticleId.add(priseList.get(i).getArticleId());
			}
		}
		for(int i=0;i<priseArticleId.size();i++){
			if(priseArticleId.get(i).equals(String.valueOf(cInfo.getcId()))){
				cInfo.setcStatus(1);
			}else{
				if(cInfo.getcStatus()!=1){
					cInfo.setcStatus(0);
				}
			}
		}
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
	@Override
	public int clickPrise(Prise prise) {
		CInfoDaoImp cdi = new CInfoDaoImp();
		//如果数据库里面没有该用户给该文章点赞的记录  那么就添加 否则 就不添加
		int check = cdi.checkPrise(prise);
		if(check==1){
			//添加
			cdi.clickPrise(prise); 
			//在板块表里面点赞数据添加		
			cdi.updatePriseCount(check, prise);
			return 1; 
		}else{
			//删除
			cdi.delClickPrise(prise);
			//在板块表里面点赞数据减一
			cdi.updatePriseCount(check, prise);
			return-1;  
		}
	}
	@Override
	public List getPicturePath() {
		CInfoDaoImp cdi = new CInfoDaoImp();
		List pictureList = cdi.getPicturePath();
		return pictureList;
	}
	@Override
	public void publish(CInfo cInfo, List<Picture> pictureList) {
		CInfoDaoImp cdi = new CInfoDaoImp();
		//处理cId  拿到下一个cId
		String preCId = cdi.getMaxCid();
		if(preCId.equals("0")){
			preCId="0";
		}
		Integer id = Integer.parseInt(preCId);
		id++;
		
		cInfo.setcId(id);
		
		//如果有发表图片的话 就发表图片
		if(!pictureList.isEmpty()){
			for(int i=0;i<pictureList.size();i++){
				pictureList.get(i).setArticleId(id);
				//把图片地址信息存储到数据库
				cdi.addPicturePath(pictureList.get(i));
			}
		}
		//文章信息  存储到数据库
		cdi.addCInfo(cInfo);
		
	}
	@Override
	public void del(String cId) {
		CInfoDaoImp cdi = new CInfoDaoImp();
		cdi.delCInfo(cId);
		cdi.delPicture(cId);
		cdi.delPrise(cId);
		cdi.delReply(cId);
	}
}
