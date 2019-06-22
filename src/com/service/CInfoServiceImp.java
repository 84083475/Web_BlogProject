package com.service;

import java.util.List;

import com.dao.CInfoDaoImp;

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

}
