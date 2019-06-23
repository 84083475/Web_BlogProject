package com.servlet;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.base.Base;
import com.model.CInfo;
import com.model.Reply;
import com.service.CInfoServiceImp;
@WebServlet("/campusInform")
public class CampusInformServlet extends Base{
	//查找文章
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
	
	//进入到单个文章页面或者显示回复框
	public void intoText(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String cId = request.getParameter("cId");
		
		CInfoServiceImp csi = new CInfoServiceImp();
		List cReplyList = csi.searchReply(cId);//得到回复的内容
		CInfo singleCInfo = csi.singleSearch(cId);//得到文章
		
		//判断 如果点击回复按钮则把userId和name传送回主界面  主界面通过jquery控制显示回复框  并设置userId和name
		String userId1 = request.getParameter("userId1");
		String userName1 = request.getParameter("userName1");
		if(userId1!=null){
			request.setAttribute("userId1", userId1);
			request.setAttribute("userName1", userName1);
		}
		
		request.setAttribute("cId", cId);
		request.setAttribute("cReplyList", cReplyList);
		request.setAttribute("cInfo", singleCInfo);
		request.getRequestDispatcher("campusReply.jsp").forward(request, response);
		
	}
	
	//添加回复内容
	public void addReply(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userId = request.getParameter("userId"); //回复人
		String userId1 = request.getParameter("userId1"); //被回复人
		String val = request.getParameter("val");  //文章内容
		String cId = request.getParameter("cId"); //文章编号
		//获取系统时间
		Long l = System.currentTimeMillis();
		Date date = new Date(l);
		//得到回复id号
		String replyId = new CInfoServiceImp().getRepId();
		
		Reply reply = new Reply();
		reply.setRepId(Integer.parseInt(replyId));
		reply.setUserId1(userId);
		reply.setUserId2(userId1);
		reply.setRepDate(date);
		reply.setRepContent(val);
		reply.setArticleId(cId);
		
		new CInfoServiceImp().addData(reply);
		
		//跳转
		response.sendRedirect("campusInform?act=intoText&cId="+cId);
	}
}
