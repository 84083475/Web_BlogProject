package com.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.base.Base;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends Base {
	//�����¼����ת
	public void useLogin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("l", 1);
		request.getRequestDispatcher("LoginAndRegister.jsp").forward(request, response);
	}
	//���ע�����ת
	public void useRegister(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("l", 2);
		request.getRequestDispatcher("LoginAndRegister.jsp").forward(request, response);
	}
}
