package com.service;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dao.KnowDaoImp;
import com.model.Article;
import com.util.JdbcUtil;

public class KnowServiceImp implements IKnowService {
	private KnowDaoImp knowdao = new KnowDaoImp();
	
	//查询所有文章
	public void search(HttpServletRequest request) {
		knowdao.search(request);
	}

}
