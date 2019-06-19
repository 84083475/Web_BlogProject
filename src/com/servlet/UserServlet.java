package com.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.base.Base;
import com.model.User;
import com.service.UserServiceImp;
import com.sun.net.httpserver.HttpsServer;
import com.util.Conversion;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class UserServlet extends Base {
	private int count=0;
	//点击登录后跳转
	public void useLogin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("LoginAndRegister.jsp").forward(request, response);
	}
	//点击注册后跳转
	public void useRegister(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("l", 2);
		request.getRequestDispatcher("LoginAndRegister.jsp").forward(request, response);
	}
	//登录
	public void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		User user = new User();
		//通过工具类 给每一个 属性赋值给model类
		Conversion.conver(user, request);
		
		User userMessage =new UserServiceImp().login(user);
		HttpSession session= request.getSession();
		session.setAttribute("user", userMessage);
		response.sendRedirect("leader.jsp");
	}
	//注册
	public void register(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		User user = new User();
		//通过工具类 给每一个 属性赋值给model类
		Conversion.conver(user, request);
		//业务处理
		new UserServiceImp().register(user);
		//跳转 重定向
		response.sendRedirect("LoginServlet?act=useLogin");
	}
	
	//用户信息显示
	public void userMess(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{
		count++;
		request.setAttribute("count", count);
		request.getRequestDispatcher("leader.jsp").forward(request, response);
	}
}