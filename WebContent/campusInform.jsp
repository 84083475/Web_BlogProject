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
		    <li class="active"><a href="campusInform?act=search">校内通知</a></li>
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
				<c:if test="${user.userName!=null }">
					<input type="button" class="btn btn-info btn-lg btn-block" value="校内通知发表"
						style="position:relative;top:20px;" data-toggle="modal" data-target="#myModal"/>
				</c:if>
			</div>
			
			<!-- 板块内容显示区域 -->
			<div class="col-md-8 column">
				<c:forEach items="${list }" var="cInfo">
					<div>
						<p>
							<a class="btn" 
							<c:choose>
								<c:when test="${user.userName!=null }">
									href="campusInform?act=intoText&cId=${cInfo.cId }"
								</c:when>
								<c:otherwise>
									href="LoginServlet?act=useLogin"
								</c:otherwise>
							</c:choose> 
							style="font-size:36px; color:black;">
							${cInfo.cName }
							</a>
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
						
				 			<a class="btn btn-default btn-sm" 
			 				<c:choose>
								<c:when test="${user.userName!=null }">
									href="campusInform?act=intoText&cId=${cInfo.cId }"
								</c:when>
								<c:otherwise>
									href="LoginServlet?act=useLogin"
								</c:otherwise>
							</c:choose> >
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
        					
					 		<h5 style="display:inline;position:relative;left:300px;">${cInfo.cDate }</h5>
					 		<c:if test="${user.userId==cInfo.userId }">
					 			<a onclick="del(${cInfo.cId })" style="position:relative;left:320px;">删除</a>
					 		</c:if>
						</p>
						<ul class="list-inline">
							<li>---------------------------------------------------------------------------------------------------------------------------------------------------------------</li>
						</ul>
					</div>
				</c:forEach>
				
				<ul class="pagination">
					<li <c:if test="${pagenumber==1}"> class="disabled" </c:if>>
					<a href="campusInform?act=search">首页</a>
					</li>
					<li <c:if test="${pagenumber==1}"> class="disabled" </c:if>>
					<a href="campusInform?act=search&pagenumber=${pagenumber-1<=0 ?1 :pagenumber-1}">上一页</a>
					</li>
					<%--中间页--%>
		   			<%--显示6页中间页[begin=起始页,end=最大页]--%>
		   			<%--总页数没有6页--%>
				    <c:choose>
				        <c:when test="${maxPage <= 6}">
				            <c:set var="begin" value="1"/>
				            <c:set var="end" value="${maxPage}"/>
				        </c:when>
				        <%--页数超过了6页--%>
				        <c:otherwise>
				            <c:set var="begin" value="${pagenumber - 1}"/>
				            <c:set var="end" value="${pagenumber + 3}"/>
				            <%--如果begin减1后为0,设置起始页为1,最大页为6--%>
				            <c:if test="${begin -1 <= 0}">
				                <c:set var="begin" value="1"/>
				                <c:set var="end" value="6"/>
				            </c:if>
				            <%--如果end超过最大页,设置起始页=最大页-5--%>
				            <c:if test="${end > maxPage}">
				                <c:set var="begin" value="${maxPage - 5}"/>
				                <c:set var="end" value="${maxPage}"/>
				            </c:if>
				        </c:otherwise>
				    </c:choose>
				    <%--遍历--%>
				    <c:forEach var="i" begin="${begin}" end="${end}">
				        <%--当前页,选中--%>
				        <c:choose>
				            <c:when test="${i == pagenumber}">
				                <li class="active"><a href="campusInform?act=search&pagenumber=${i}">${i}</a></li>
				            </c:when>
				            <%--不是当前页--%>
				            <c:otherwise>
				                <li><a href="campusInform?act=search&pagenumber=${i}">${i}</a></li>
				            </c:otherwise>
				        </c:choose>
				    </c:forEach>
				
					<li <c:if test="${pagenumber==maxPage}"> class="disabled" </c:if>>
					<a href="campusInform?act=search&pagenumber=${pagenumber+1>maxPage ?maxPage :pagenumber+1}">下一页</a>
					</li>
					
					<li >
					<a href="campusInform?act=search&pagenumber=${maxPage}">尾页/${maxPage}</a>
					</li>
				</ul> 
			<div class="col-md-2 column">
			</div>
		</div>
	</div>
	

	<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
	    <div class="modal-dialog">
	        <div class="modal-content">
	            <div class="modal-header">
	                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
	                <h4 class="modal-title" id="myModalLabel">Publish</h4>
	            </div>
	            <div class="modal-body">
	            	<!-- 该类型的表单数据不是在头文件里面传送的   但是可以吧一些小信息拼接到action里面  其他文件需要在后台解析拿到 -->
	            	<form action="campusInform?act=publish" method="post" enctype="multipart/form-data">
						<div class="form-group">
					 		<label for="exampleInputEmail1">文章名称</label>
					 		<input type="text" class="form-control" name="articleName" />
						</div>
						<div class="form-group">
							 <label for="exampleInputPassword1">内容</label>
							 <textarea type="textarea" class="form-control" style="height:100px;" name="articleContent" ></textarea>
						</div>
						<div class="form-group">
					 		<label for="exampleInputFile">请选择图片</label>
					 		<input type="file" name="file" multiple="multiple"/>
						<p class="help-block">
							Ctrl+鼠标选择 可选择最多不超过九个图片进行发表哦~
						</p>
						</div>
						<button type="submit" class="btn btn-default">Submit</button>
					</form>
	            </div>
	        </div>
	    </div>
	</div>
</body>
<script type="text/javascript">
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
  	
  	//删除
  	function del(c){
  		var flag = window.confirm("是否确定删除?");
  	   	if(flag){
  	   		window.location.href="campusInform?act=del&cId="+c;
  	   	}
  	}
</script>
</html>