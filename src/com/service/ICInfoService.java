package com.service;

import java.util.List;

import com.model.CInfo;

public interface ICInfoService {
	//分页查询
	public List search(int pageNumber);
	//得到当前表格的最大页数
	public int getMaxPage();
}
