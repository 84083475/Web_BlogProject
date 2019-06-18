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
				<div class="col-md-4 column"style="position: relative; top: 170px;">
					<form role="form" action="#">
						<div class="form-group">
							 <label >PLEASE REGISTER！</label>
						</div>
						<div class="form-group">
							 <label for="exampleInputEmail1">请输入用户名</label>
							 <input type="email" class="form-control" id="username" name="username"/>
						</div>
						<div class="form-group">
							 <label for="exampleInputEmail1">请输入账号</label>
							 <input type="email" class="form-control" id="userid" name="userid"/>
						</div>
						<div class="form-group">
							 <label for="exampleInputPassword1">请输入密码</label>
							 <input type="password" class="form-control" id="userpassword" name="userpassword"/>
						</div>
						<div class="form-group">
							 <label for="exampleInputPassword1">请再次输入密码</label>
							 <input type="password" class="form-control" id="userpassword2" name="userpassword2"/>
						</div>
						<button type="submit" class="btn btn-default">确定</button>
					</form>
				</div>
			</div>
		</div>
	</div>
</div>
</body>
</html>