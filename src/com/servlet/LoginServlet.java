package com.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.base.Base;
import com.model.User;
import com.service.UserServiceImp;
import com.util.Conversion;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends Base {
	//点击登录后跳转
	public void useLogin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("l", 1);
		request.getRequestDispatcher("LoginAndRegister.jsp").forward(request, response);
	}
	//点击注册后跳转
	public void useRegister(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("l", 2);
		request.getRequestDispatcher("LoginAndRegister.jsp").forward(request, response);
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
}
