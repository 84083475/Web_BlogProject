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
						<a type="button" class="btn btn-info" href="user.jsp">${user.userName }</a>
						<a type="button" class="btn btn-link" href="LoginServlet?act=useLogin">退出</a>
					</div>
				</c:otherwise>
			</c:choose>
			
		</div>
		  <ul class="nav nav-tabs">
		    <li class="active"><a href="#">推荐</a></li>
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
				 		<a class="btn btn-default" href="#">转发11</a>
				 		<a class="btn btn-default" href="#">评论111</a>
				 		<a class="btn btn-default" href="#">点赞1111</a>
				 		<h5 style="display:inline;position:relative;left:350px;">2019-02-23</h5>
					</p>
				</div>
				
			</div>
			
			
			
			
			<%--页码--%>
			<ul class="pagination">
			
				<%--cp:当前页  tp总页数--%>
				
				<%--首页--%>
				<li >
				<a href="#">首页</a>
				</li>
				<%--上一页--%>
				<li <c:if test="${cp==1}"> class="disabled" </c:if>>
				<a href="#<%-- KnowServlet?act=search&cp=${cp-1<=0 ?1 :cp-1} --%>">上一页</a>
				</li>
				
				<%--中间页--%>
			    <%--显示6页中间页[begin=起始页,end=最大页]--%>
			    <%--总页数没有6页--%>
			    <c:choose>
			        <c:when test="${tp <= 6}">
			            <c:set var="begin" value="1"/>
			            <c:set var="end" value="${tp}"/>
			        </c:when>
			        <%--页数超过了6页--%>
			        <c:otherwise>
			            <c:set var="begin" value="${cp - 1}"/>
			            <c:set var="end" value="${cp + 3}"/>
			            <%--如果begin减1后为0,设置起始页为1,最大页为6--%>
			            <c:if test="${begin -1 <= 0}">
			                <c:set var="begin" value="1"/>
			                <c:set var="end" value="6"/>
			            </c:if>
			            <%--如果end超过最大页,设置起始页=最大页-5--%>
			            <c:if test="${end > tp}">
			                <c:set var="begin" value="${tp - 5}"/>
			                <c:set var="end" value="${tp}"/>
			            </c:if>
			        </c:otherwise>
			    </c:choose>
			    <%--遍历--%>
			    <c:forEach var="i" begin="${begin}" end="${end}">
			        <%--当前页,选中--%>
			        <c:choose>
			            <c:when test="${i == cp}">
			                <li class="active"><a href="#<%-- KnowServlet?act=search&cp=${i} --%>">${i}</a></li>
			            </c:when>
			            <%--不是当前页--%>
			            <c:otherwise>
			                <li><a href="#">${i}</a></li>
			            </c:otherwise>
			        </c:choose>
			    </c:forEach>				
				
				<%--下一页--%>
				<li <c:if test="${cp==tp}"> class="disabled" </c:if>>
				<a href="#<%-- KnowServlet?act=search&cp=${cp+1>tp ?tp :cp+1} --%>">下一页</a>
				</li>
				
				<%--尾页--%>
				<li >
				<a href="#">尾页/${tp}</a>
				</li>
					
			</ul> 
			
			
			
			
			<div class="col-md-2 column">
			</div>
		</div>
	</div>
	</body>
</html>