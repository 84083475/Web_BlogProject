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
		if(userMessage.getUserName()==null){
			request.getRequestDispatcher("LoginAndRegister.jsp").forward(request, response);
		}else{
			HttpSession session= request.getSession();
			session.setAttribute("user", userMessage);
			
			response.sendRedirect("leader.jsp");
		}
		
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
	//用户信息修改
	public void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 User user = new User();
		//通过工具类 给每一个 属性赋值给model类
		Conversion.conver(user, request);
		//业务处理
		new UserServiceImp().update(user);
		//跳转回登陆页面
		response.sendRedirect("LoginServlet?act=useLogin");
	}
	//ajax判断账号是否重复  回调函数
	public void search(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//设置请求和响应的编码格式
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		
		String val = request.getParameter("val");
		String userId = new UserServiceImp().search(val);
		if(val.equals(userId)){
			response.getWriter().print("该id不可用！！");
		}else{
			response.getWriter().print("id可用！！");
		}
	}
	
}
