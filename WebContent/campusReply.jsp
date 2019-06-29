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
		    <li><a href="KnowServlet?act=search">冷门知识</a></li>
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
						<a class="btn" href="#" style="font-size:36px; color:black;">${cInfo.cName }</a>
					</p>
					<p>
						${cInfo.cText }
					</p>
					<p>
						<c:forEach items="${pictureList }" var="picture">
							<c:if test="${picture.articleId==cInfo.cId }">
								<img src="${picture.picturePath }" class="img-rounded" style="width: 200px;height: 200px;">
							</c:if>
						</c:forEach>
					</p>
					<p style="display:inline;">
						
						<a class="btn btn-default btn-sm" onclick="share()">
								<input type="hidden" value=""/>
         					 	<span class="glyphicon glyphicon-share-alt"></span>分享
       					 	</a>
						<a id="openReply1" data-toggle="modal" data-target="#myModal"></a>
				 		<a id="openReply" class="btn btn-default btn-sm" data-toggle="modal" data-target="#myModal" >
				 			<span class="glyphicon glyphicon-pencil"></span>评论${cInfo.cReply }
				 		</a>
				 		
				 		<a onclick="prise(this)" 
			 				<c:choose>
			 					<c:when test="${cInfo.cStatus==0 }">
			 						class="btn btn-default btn-sm"
			 					</c:when>
			 					<c:otherwise>
									class="btn btn-info btn-sm"
								</c:otherwise>
			 				</c:choose> >
				 				<input type="hidden" value="${cInfo.cId }"/>
          						<span class="glyphicon glyphicon-thumbs-up"></span>点赞${cInfo.cPrise }
       					</a>
        				
				 		<h5 style="display:inline;position:relative;left:300px;">2019-02-23</h5>
				 		<a href="#" style="position:relative;left:320px;">修改</a>
					</p>
					
					<!-- 回复内容 -->
					<div style="width:600px;">
						<c:forEach items="${cReplyList }" var="info">
							<c:choose>
								<c:when test="${info.userName2==null }">
									<!-- 评论 -->
									<ul class="list-inline">
									  <li><font style="color:black;font-size:18px;">${info.userName1 }</font></li>
									  <li>:</li>
									  <li>${info.repContent }</li>
									</ul>
									<ul class="list-inline">
									  <li>${info.repDate }</li>
									  <li style="position:relative;left:50px;"><a href="campusInform?act=intoText&userId1=${info.userId1 }&cId=${cId}&userName1=${info.userName1 } ">回复</a></li>
									</ul>
									<ul class="list-inline">
										<li>-------------------------------------------------------------------------------------------------------------------------------</li>
									</ul>
								</c:when>
								<c:otherwise>
									<!-- 回复 -->
									<ul class="list-inline">
									  <li><font style="color:black;font-size:18px;">${info.userName1 }</font></li>
									  <li>→</li>
									  <li><font style="color:black;font-size:18px;">${info.userName2 }:</font></li>
									  <li >${info.repContent }</li>
									</ul>
									<ul class="list-inline">
									  	<li>${info.repDate }</li>
									  	<li style="position:relative;left:50px;"><a href="campusInform?act=intoText&userId1=${info.userId1 }&cId=${cId}&userName1=${info.userName1 }">回复</a></li>
									</ul>
									<ul class="list-inline">
										<li>-------------------------------------------------------------------------------------------------------------------------------</li>
									</ul>
								</c:otherwise>
							</c:choose>
						</c:forEach>
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
						<form  accept-charset="utf-8" id="putReply" method="get">
		                <input id="val" type="text"  name="val" class="form-control"/>
		                <div class="modal-footer">
		                <input id="button" type="button" value="确认" style="width:150px;" class="btn btn-default"/>
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
	<script type="text/javascript">
		//如果没有登录  直接返回登录界面
		if(${user.userId==null}){
			window.location.href="LoginServlet?act=useLogin";
		}else{
			//如果点击回复后 设置标题的字为     回复：${userName}
			if(${userId1!=null }){
				var userName = "${userName1}";
				$("#title").val("回复："+userName);
				$("#openReply1").trigger("click");//打开回复框
			}	
			
			//如果点击评论的话   设置标题的字为 评论
			$("#openReply").click(function(){
				$("#title").val("评论");
			})
			
			//如果点击回复框的确定  执行以下操作
			$("#button").click(function(){
				var val = document.getElementById("val").value; 
				var title = document.getElementById("title").value;
				
				//评论没有被回复人id 
				if(title=="评论"){
					window.location.href="campusInform?val="+val+"&act=addReply&userId="+${user.userId}+"&cId="+${cId};
				}else{
					
					//没必要进行判断 但是如果不判断  走评论的话  万一${userId1}==null  下面url会报错  jquery 无法正常运行
					var userId1 = "${userId1}";
					if(${userId1== null}){
						userId1 = -1;
					}
					
					window.location.href="campusInform?val="+val+"&act=addReply&userId="+${user.userId}+"&cId="+${cId}+"&userId1="+userId1;
				}
			})
		}
		//点击点赞按钮后运行
	    function prise(c){
			
	    	var articleId = c.firstElementChild.value;
			var userId = "${user.userId}";
			
			if(${user.userName!=null}){
				$.ajax({
					url:"campusInform?act=clickPrise",//请求地址
					data:{"articleId":articleId,"userId":userId}, //请求的参数  {username: 'name', password: '123456'}
					type:"post",//请求的类型
					dataType:"json",//接受的数据类型 text html xml json
					//回调函数
					success:function(result){
						
						if(result==1){
							//  class="btn btn-default btn-sm"  class="btn btn-info btn-sm"
							$(c).removeClass("btn btn-default btn-sm").addClass('btn btn-info btn-sm');
							//得到点赞数 并且加一
							var priseConetext = c.innerText;
							var priseCount = parseInt(priseConetext.substring(2));
							var val = priseCount+1;
							$(c).html("<input type='hidden' value='"+articleId+"'/><span class='glyphicon glyphicon-thumbs-up'></span>点赞"+val);
						}else{
							$(c).removeClass("btn btn-info btn-sm").addClass('btn btn-default btn-sm');
							//得到点赞数 并且减一
							var priseConetext = c.innerText;
							var priseCount = parseInt(priseConetext.substring(2));
							var val = priseCount-1;
							$(c).html("<input type='hidden' value='"+articleId+"'/><span class='glyphicon glyphicon-thumbs-up'></span>点赞"+val);
						}
					}, 
					error:function(){
						alert("系统繁忙，点赞失败")
					}
				})
			}else{
				window.location.href="LoginServlet?act=useLogin";
			}
	   }
	 	 //分享功能实现
		function share(){
			alert("已经将链接复制到剪切板");
	  		clipboardData.setData("text", this.location.href);
	    }
	</script>
</html>