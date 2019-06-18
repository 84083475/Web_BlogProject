<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
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
			<div class="col-md-2 column" style="position: relative; top: 20px;">
				<a href="LoginServlet?act=useLogin" style="font-size: 20px;">登录</a>
				<a href="LoginServlet?act=useRegister" style="font-size: 20px;">注册</a>
			</div>
		</div>
		  <ul class="nav nav-tabs">
		    <li class="active"><a href="#">首页</a></li>
		    <li><a href="#">板块1</a></li>
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
			<div class="col-md-8 column">
			</div>
			<div class="col-md-2 column">
			</div>
		</div>
	</div>
	</body>
</body>
</html>