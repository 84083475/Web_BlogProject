package com.servlet;

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
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.base.Base;
import com.model.Article;
import com.service.KnowServiceImp;
import com.util.JdbcUtil;

/**
 * Servlet implementation class KnowServlet
 */
@SuppressWarnings("all")
@WebServlet("/KnowServlet")
public class KnowServlet extends Base {
	
	//查询所有文章
	public void search(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		KnowServiceImp service = new KnowServiceImp();
		service.search(request);
		request.getRequestDispatcher("knowledge.jsp").forward(request, response);
	
	}
	

}
