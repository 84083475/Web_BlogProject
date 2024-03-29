<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>leader</title>
<link rel="stylesheet" href="bootstrap-3.3.7/css/bootstrap.min.css" />
<script type="text/javascript" src="js/jquery-2.2.3.min.js"></script>
<script type="text/javascript" src="bootstrap-3.3.7/js/bootstrap.min.js"></script>
</head>
<body>
<div class="container">
		<div class="row clearfix">
			<div class="col-md-3 column">
				<h3 style="font-family: 华文彩云; color: red;">WELCOM校园论坛</h3>
			</div>
			<div class="col-md-7 column" style="position: relative; top: 10px;">
				<form class="navbar-form navbar-left" role="search">
					<div class="form-group">
						<input type="text" class="form-control" placeholder="文章标题/作者"/>
					</div> 
					<button type="submit" class="btn btn-default">Submit</button>
				</form>
			</div>
			<c:choose>
				<c:when test="${user.userName==null }">
					<div class="col-md-2 column" style="position: relative; top: 20px;">
						<a href="LoginServlet?act=useLogin" style="font-size: 20px;">登录</a>
						<a href="LoginServlet?act=useRegister" style="font-size: 20px;">注册</a>
					</div>
				</c:when>
				<c:otherwise>
					<div class="col-md-2 column" style="position: relative; top: 20px;">
						<a type="button" class="btn btn-info" href="user.jsp"><span class="glyphicon glyphicon-user"></span>${user.userName }</a>
						<a type="button" class="btn btn-link" href="LoginServlet?act=useLogin">退出</a>
					</div>
				</c:otherwise>
			</c:choose>
			
		</div>
		  <ul class="nav nav-tabs">
		    <li><a href="leader.jsp">推荐</a></li>
		    <li><a href="campusInform?act=search">校内通知</a></li>
		    <li><a href="#">板块2</a></li>
		    <li><a href="#">板块3</a></li>
		    <li class="dropdown">
		      <a class="dropdown-toggle" data-toggle="dropdown" href="#">
		        下拉板块 <span class="caret"></span>
		      </a>
		      <ul class="dropdown-menu">
		        <li><a href="#">下拉选项1</a></li>
		        <li><a href="#">下拉选项2</a></li>
		        <li><a href="#">下拉选项3</a></li>
		      </ul>
		    </li>
		    <li><a href="#">板块4</a></li>
		  </ul>
		  
		<div class="row clearfix">
			<div class="col-md-2 column">
			</div>
			
			<!-- 内容显示区域 -->
			<div class="col-md-8 column">
				<div>
					<p>
						<a class="btn" href="#" style="font-size:36px; color:black;">Heading</a>
					</p>
					<p>
						ssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssss
					</p>
					<p style="display:inline;">
				 		<a type="button" class="btn btn-default btn-sm">
         					 	<span class="glyphicon glyphicon-share-alt"></span>分享${cInfo.cTransmit }
       					 	</a>
						
				 			<a class="btn btn-default btn-sm" href="#">
				 				<span class="glyphicon glyphicon-pencil"></span>评论${cInfo.cReply }
				 			</a>
				 		
				 			<a type="button" class="btn btn-default btn-sm">
          						<span class="glyphicon glyphicon-thumbs-up"></span>点赞${cInfo.cPrise }
        					</a>
        					
					 		<h5 style="display:inline;position:relative;left:300px;">${cInfo.cDate }</h5>
					 		<c:if test="${user.userId==cInfo.userId }">
					 			<a href="#" style="position:relative;left:320px;">修改</a>
					 		</c:if>
					</p>
					<ul class="list-inline">
						<li>---------------------------------------------------------------------------------------------------------------------------------------------------------------</li>
					</ul>
				</div>
			</div>
			
			<!-- 用户信息查询更改 -->
			<div class="col-md-2 column">
				<form action="LoginServlet" class="form-horizontal" role="form" style="position:relative;top:80px;">
					<div class="form-group">
						 <label class="col-sm-4 control-label">账号</label>
						<div class="col-sm-8">
							<input type="hidden" name="act" value="update"/>
							<input type="text" name="userId" class="form-control" readonly="true" value="${user.userId}"/>
						</div>
					</div>
					<div class="form-group">
						 <label  class="col-sm-4 control-label">用户名</label>
						<div class="col-sm-8">
							<input type="text" name="userName" class="form-control" value="${user.userName}"/>
						</div>
					</div>
					<div class="form-group">
						 <label class="col-sm-4 control-label">密码</label>
						<div class="col-sm-8">
							<input type="text" name="userPassWord" class="form-control"  value="${user.userPassWord}"/>
						</div>
					</div>
					<div class="form-group">
						<div class="col-sm-offset-2 col-sm-10">
							 <button type="submit" class="btn btn-default">修改</button>
						</div>
					</div>
				</form>
			</div>
		</div>
	</div>
</body>
</html>