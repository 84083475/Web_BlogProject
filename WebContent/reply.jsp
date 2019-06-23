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
		    <li><a href="#">推荐</a></li>
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
			
			<!-- 板块内容显示区域 -->
			<div class="col-md-8 column">
			
				<div>
					<p>
						<a class="btn" href="#" style="font-size:36px; color:black;">Heading</a>
					</p>
					<p>
						Donec id elit non mi porta gravida at eget metus. Fusce dapibus, tellus ac cursus commodo, tortor mauris condimentum nibh, ut fermentum massa justo sit amet risus. Etiam porta sem malesuada magna mollis euismod. Donec sed odio dui.
					</p>
					<p style="display:inline;">
						
						<a type="button" class="btn btn-default btn-sm">
         					 <span class="glyphicon glyphicon-share-alt"></span>分享111
       					 </a>
						
				 		<a class="btn btn-default btn-sm" data-toggle="modal" data-target="#myModal" >
				 			<span class="glyphicon glyphicon-pencil"></span>评论111
				 		</a>
				 		
				 		<a type="button" class="btn btn-default btn-sm">
          					<span class="glyphicon glyphicon-thumbs-up"></span>点赞111
        				</a>
        				
				 		<h5 style="display:inline;position:relative;left:300px;">2019-02-23</h5>
				 		<a href="#" style="position:relative;left:320px;">修改</a>
					</p>
					<!-- 回复内容 -->
					<div style="width:600px;">
						<!-- 评论 -->
						<ul class="list-inline">
						  <li><font style="color:black;font-size:18px;">用户1</font></li>
						  <li>:</li>
						  <li>内容内容内容内容内容内容</li>
						</ul>
						<ul class="list-inline">
						  <li>2019-02-23</li>
						  <li style="position:relative;left:50px;"><a href="" data-toggle="modal" data-target="#myModal">回复</a></li>
						</ul>
						<ul class="list-inline">
							<li>-------------------------------------------------------------------------------------------------------------------------------</li>
						</ul>
						
						<!-- 回复 -->
						<ul class="list-inline">
						  <li><font style="color:black;font-size:18px;">用户1</font></li>
						  <li>→</li>
						  <li><font style="color:black;font-size:18px;">用户2:</font></li>
						  <li >内容内容内容内容内容内容</li>
						</ul>
						<ul class="list-inline">
						  	<li>2019-02-23</li>
						  	<li style="position:relative;left:50px;"><a href="" data-toggle="modal" data-target="#myModal">回复</a></li>
						</ul>
						<ul class="list-inline">
							<li>-------------------------------------------------------------------------------------------------------------------------------</li>
						</ul>
						
						<!-- 评论 -->
						<ul class="list-inline">
						  <li><font style="color:black;font-size:18px;">用户1</font></li>
						  <li>:</li>
						  <li>内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容</li>
						</ul>
						<ul class="list-inline">
						  	<li>2019-02-23</li>
						  	<li style="position:relative;left:50px;"><a href="" data-toggle="modal" data-target="#myModal">回复</a></li>
						</ul>
						<ul class="list-inline">
							<li>-------------------------------------------------------------------------------------------------------------------------------</li>
						</ul>
						
						<!-- 回复 -->
						<ul class="list-inline">
						  <li><font style="color:black;font-size:18px;">用户1</font></li>
						  <li>→</li>
						  <li><font style="color:black;font-size:18px;">用户2:</font></li>
						  <li>内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容</li>
						</ul>
						<ul class="list-inline">
						  	<li>2019-02-23</li>
						  	<li style="position:relative;left:50px;"><a href="" data-toggle="modal" data-target="#myModal">回复</a></li>
						</ul>
						<ul class="list-inline">
							<li>-------------------------------------------------------------------------------------------------------------------------------</li>
						</ul>
						
						<!-- 回复 -->
						<ul class="list-inline">
						  <li><font style="color:black;font-size:18px;">用户1</font></li>
						  <li>→</li>
						  <li><font style="color:black;font-size:18px;">用户2:</font></li>
						  <li>内容内容内容内容内容内容</li>
						</ul>
						<ul class="list-inline">
						  	<li>2019-02-23</li>
						  	<li style="position:relative;left:50px;"><a href="" data-toggle="modal" data-target="#myModal">回复</a></li>
						</ul>
						<ul class="list-inline">
							<li>-------------------------------------------------------------------------------------------------------------------------------</li>
						</ul>
					</div>
					
				</div>
				
			</div>
			
			<!-- 回复框 -->
			<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
				<div class="modal-dialog">
					<div class="modal-content">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal" aria-hidden="true">
								&times;
							</button>
							<input id="title" class="btn btn-default btn-lg btn-block" value="评论"/>
						</div>
						<form action="" accept-charset="utf-8" id="login_form" method="get">
		                <input type="hidden" name="act" value=""/>
		                <input type="text" id="u" name="val" class="form-control"/>
		                <div class="modal-footer">
		                <input type="submit" value="确认" style="width:150px;" class="btn btn-default"/>
		                </div>
		              </form>
					</div>
				</div>
			</div>
			<div class="col-md-2 column">
			</div>
		</div>
	</div>
	</body>
</html>