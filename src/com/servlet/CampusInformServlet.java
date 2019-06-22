package com.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.base.Base;
import com.service.CInfoServiceImp;
@WebServlet("/campusInform")
public class CampusInformServlet extends Base{
	
	public void search(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int pagenumber = 1;//设置初始页码为1
		if(request.getParameter("pagenumber")!=null){
			pagenumber =Integer.parseInt(request.getParameter("pagenumber"));
		}
		
		CInfoServiceImp csi = new CInfoServiceImp();
		List cInfoList =csi.search(pagenumber);
		int maxPage = csi.getMaxPage(); //得到最大页码数
		
		
		request.setAttribute("pagenumber", pagenumber);
		request.setAttribute("maxPage", maxPage);
		request.setAttribute("list", cInfoList);
		request.getRequestDispatcher("campusInform.jsp").forward(request, response);
	}
}
