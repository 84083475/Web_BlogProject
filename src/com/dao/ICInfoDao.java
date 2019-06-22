package com.dao;

import java.util.List;

import com.model.CInfo;

public interface ICInfoDao {
	//分页查询
	public List search(int pageNumber ,int pageSize);
	//得到当前表格的最大页数
	public int getMaxPage();
}
