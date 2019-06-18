<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>WELCOME YOU</title>
<link rel="stylesheet" href="bootstrap-3.3.7/css/bootstrap.min.css" />
<script type="text/javascript" src="js/jquery-2.2.3.min.js"></script>
<script type="text/javascript" src="bootstrap-3.3.7/js/bootstrap.min.js"></script>
</head>
<body>
<div class="container" >
	<div class="row clearfix">
		<div class="col-md-12 column">
			<div class="row clearfix">
				<div class="col-md-4 column">
				</div>
				<div class="col-md-4 column"style="position: relative; top: 200px;">
					<form class="form-horizontal" role="form">
					 <div class="form-group">
					    <label for="firstname" class="col-sm-2 control-label">WELCOM</label>
					  </div>
					  <div class="form-group">
					    <label for="firstname" class="col-sm-2 control-label">账号</label>
					    <div class="col-sm-10">
					      <input type="text" class="form-control" id="userid" name="userid" placeholder="请输入账号">
					    </div>
					  </div>
					  <div class="form-group">
					    <label for="lastname" class="col-sm-2 control-label">密码</label>
					    <div class="col-sm-10">
					      <input type="text" class="form-control" id="userpassword" name="userpassword" placeholder="请输入密码">
					    </div>
					  </div>
					  <div class="form-group">
					    <div class="col-sm-offset-2 col-sm-10">
					      <div class="checkbox">
					        <label>
					          <input type="checkbox">请记住我
					        </label>
					      </div>
					    </div>
					  </div>
					  <div class="form-group">
					    <div class="col-sm-offset-2 col-sm-10">
					      <button type="submit" class="btn btn-default">登录</button>
					      <a type="submit" class="btn btn-default" href="register.jsp">注册</a>
					    </div>
					  </div>
					</form>
				</div>
			</div>
		</div>
	</div>
</div>
</body>
</html>