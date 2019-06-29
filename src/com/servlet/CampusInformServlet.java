package com.servlet;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.base.Base;
import com.model.CInfo;
import com.model.Picture;
import com.model.Prise;
import com.model.Reply;
import com.model.User;
import com.service.CInfoServiceImp;
@WebServlet("/campusInform")
public class CampusInformServlet extends Base{
	//查找文章
	public void search(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		int pagenumber = 1;//设置初始页码为1
		
		String userId = "0";
		User user = (User) session.getAttribute("user");
		if(user!=null){
			userId = user.getUserId();
		}
		
		if(request.getParameter("pagenumber")!=null){
			pagenumber =Integer.parseInt(request.getParameter("pagenumber"));
		}
		
		CInfoServiceImp csi = new CInfoServiceImp();
		List cInfoList =csi.search(pagenumber,userId);//得到文章
		List pictureList = csi.getPicturePath();//得到所有的图片地址
		int maxPage = csi.getMaxPage(); //得到最大页码数
		
		
		request.setAttribute("pictureList", pictureList);
		request.setAttribute("pagenumber", pagenumber);
		request.setAttribute("maxPage", maxPage);
		request.setAttribute("list", cInfoList);
		request.getRequestDispatcher("campusInform.jsp").forward(request, response);
	}
	
	//进入到单个文章页面或者显示回复框
	public void intoText(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String cId = request.getParameter("cId");
		String userId = "0";
		User user = (User) session.getAttribute("user");
		if(user!=null){
			userId = user.getUserId();
		}
		
		
		CInfoServiceImp csi = new CInfoServiceImp();
		List cReplyList = csi.searchReply(cId);//得到回复的内容
		List pictureList = csi.getPicturePath();//得到所有的图片地址
		CInfo singleCInfo = csi.singleSearch(cId,userId);//得到文章
		
		//判断 如果点击回复按钮则把userId和name传送回主界面  主界面通过jquery控制显示回复框  并设置userId和name
		String userId1 = request.getParameter("userId1");
		String userName1 = request.getParameter("userName1");
		if(userId1!=null){
			request.setAttribute("userId1", userId1);
			request.setAttribute("userName1", userName1);
		}
		
		request.setAttribute("pictureList", pictureList);
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
	
	//点赞
	public void clickPrise(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		String userId = request.getParameter("userId");
		String articleId = request.getParameter("articleId");
		
		Prise prise = new Prise();
		prise.setUserId(userId);
		prise.setArticleId(articleId);
		
		CInfoServiceImp csi = new CInfoServiceImp();
		int check = csi.clickPrise(prise);
		if(check==1){
			response.getWriter().print("1");
			
		}else{
			response.getWriter().print("0");
		}

	}
	//发表
	public void publish(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		request.setCharacterEncoding("utf-8");
		
		//校内通知模块文章表model
		CInfo cInfo = new CInfo();
		//点赞数量  评论数量  等等均设置为0
		cInfo.setcPrise(0);cInfo.setcReply(0);cInfo.setcTransmit(0);
	
		//得到当前登录的用户id
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		String userId = user.getUserId();
		cInfo.setUserId(userId);
		//得到当前日期
		Long l = System.currentTimeMillis();
		Date date = new Date(l);
		cInfo.setcDate(date);
		
		List pictureList = new ArrayList();
		try {
			//1.文件解析工厂
			DiskFileItemFactory factory = new DiskFileItemFactory();
			//2.创建文件解析器
			ServletFileUpload upload = new ServletFileUpload(factory);
			upload.setHeaderEncoding("utf-8");
			//3.解析request请求
			List<FileItem> list = upload.parseRequest(request);
			
			//4.遍历集合
			for(FileItem item:list){ 
				//判断是否是普通表单属性
				if(item.isFormField()){
					//通过判断对应的表单名字  来确定是哪一个表单属性
					if(item.getFieldName().equals("articleName")){
						//中文处理
						String value = new String(item.getString().getBytes("iso-8859-1"),"utf-8");
						
						cInfo.setcName(value);
					}else if(item.getFieldName().equals("articleContent")){
						//中文处理
						String value = new String(item.getString().getBytes("iso-8859-1"),"utf-8");
						
						cInfo.setcText(value);
					}
				}else{
					//拿到文件名字
					String fileName = item.getName(); 
					if(fileName!=""){
						//截取出文件的类型
						String fileClass = fileName.substring(fileName.indexOf("."));
						//创建文件别名  
						String nFileName = new Date().getTime()+fileClass;
						
						Picture picture = new Picture();//图片model
						//将model添加到集合里面
						picture.setPicturePath("/FileandIo/"+nFileName);
						pictureList.add(picture);
						//读取上传的文件  将文件存储到服务器端
						InputStream is = item.getInputStream();
						File file = new File("G:\\FileandIo\\"+nFileName);
						if(!file.exists()){
							file.createNewFile();
						}
						FileOutputStream fos = new FileOutputStream(file);
						
						//转存
						byte[] b = new byte[512];
						int p=-1;
						while((p=is.read(b))!=-1){
							fos.write(b,0,p);
						}
						is.close();
						fos.close();
					}
				}	
			}
			//将图片存储地址信息 和 文章信息 处理后 存到数据库
			CInfoServiceImp csi = new CInfoServiceImp();
			csi.publish(cInfo, pictureList);
			
			//跳转
			response.sendRedirect("campusInform?act=search");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	} 
	public void del(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String cId = request.getParameter("cId");
		
		CInfoServiceImp csi = new CInfoServiceImp();
		csi.del(cId);
		
		//跳转
		response.sendRedirect("campusInform?act=search");
	}
}
