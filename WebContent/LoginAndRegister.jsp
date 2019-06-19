<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Login and Register</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<script type="text/javascript" src="js/jquery-2.2.3.min.js"></script>
<link href="css/login2.css" rel="stylesheet" type="text/css" />
</head>
<body>
<h1>校园论坛<sup>v666666</sup></h1>

<div class="login" style="margin-top:50px;">
    
    <div class="header">
        <div class="switch" id="switch">
        	<a class="switch_btn_focus" id="switch_qlogin"  tabindex="7">快速登录</a>
			<a class="switch_btn" id="switch_login"  tabindex="8">快速注册</a>
			<div class="switch_bottom" id="switch_bottom" style="position: absolute; width: 64px; left: 0px;"></div>
        </div>
    </div>    
  
	
    <div class="web_qr_login" id="web_qr_login" style="display: block; height: 235px;">    
            <!--登录-->
            <div class="web_login" id="web_login">
            <div class="login-box">
			<div class="login_form">
			<form action="LoginServlet" name="loginform" accept-charset="utf-8" id="login_form" class="loginForm" method="get">
                <input type="hidden" name="act" value="login"/>
                <input type="hidden" name="userName" value="test"/>
                <div class="uinArea" id="uinArea">
                <label class="input-tips" for="u">帐号：</label>
                <div class="inputOuter" id="uArea">
                    <input type="text" id="u" name="userId" class="inputstyle"/>
                </div>
                </div>
                <div class="pwdArea" id="pwdArea">
               <label class="input-tips" for="p">密码：</label> 
               <div class="inputOuter" id="pArea">
                    <input type="password" id="p" name="userPassWord" class="inputstyle"/>
                </div>
                </div>
               
                <div style="padding-left:50px;margin-top:20px;">
                <input type="submit" value="登 录" style="width:150px;" class="button_blue"/></div>
              </form>
           </div>
           
            	</div>
               
            </div>
            <!--登录end-->
  </div>

  <!--注册-->
    <div class="qlogin" id="qlogin" style="display: none;">
    <div class="web_login">
    <form name="form2" id="regUser" accept-charset="utf-8"  action="LoginServlet" method="post">
        <ul class="reg_form" id="reg-ul">
        		<div id="userCue" class="cue">快速注册请注意格式</div>
        		
        		<li>
                 <label for="qq" class="input-tips2">账号：</label>
                    <div class="inputOuter2">
                       	<input type="hidden" name="act" value="register"/>
                        <input type="text" id="userid" name="userId" maxlength="10" class="inputstyle2"/>
                    </div>
                   
                </li>
        		
                <li>
                	
                    <label for="user"  class="input-tips2">用户名：</label>
                    <div class="inputOuter2">
                        <input type="text" id="user" name="userName" maxlength="16" class="inputstyle2"/>
                    </div>
                    
                </li>
                
                <li>
                <label for="passwd" class="input-tips2">密码：</label>
                    <div class="inputOuter2">
                        <input type="password" id="passwd"  name="userPassWord" maxlength="16" class="inputstyle2" />
                    </div>
                    
                </li>
                <li>
                <label for="passwd2" class="input-tips2">确认密码：</label>
                    <div class="inputOuter2">
                        <input type="password" id="passwd2"  maxlength="16" class="inputstyle2" />
                    </div>
                    
                </li>
 
                <li>
                    <div class="inputArea">
                        <input type="submit" id="reg"  style="margin-top:10px;margin-left:85px;" class="button_blue" value="同意协议并注册"/>
                    </div> 
                </li>
            </ul></form>
           
    
    </div>
   
    
    </div>
    <!--注册end-->
</div>
<div class="jianyi">*推荐使用ie8或以上版本ie浏览器或Chrome内核浏览器访问本站</div>
</body>
<script type="text/javascript">

	$('#switch_qlogin').click(function(){
		$('#switch_login').removeClass("switch_btn_focus").addClass('switch_btn');
		$('#switch_qlogin').removeClass("switch_btn").addClass('switch_btn_focus');
		$('#switch_bottom').animate({left:'0px',width:'70px'});
		$('#qlogin').css('display','none');
		$('#web_qr_login').css('display','block');
		
		});
	
	$('#switch_login').click(function(){
		
		$('#switch_login').removeClass("switch_btn").addClass('switch_btn_focus');
		$('#switch_qlogin').removeClass("switch_btn_focus").addClass('switch_btn');
		$('#switch_bottom').animate({left:'154px',width:'70px'});
		$('#qlogin').css('display','block');
		$('#web_qr_login').css('display','none');
		});

	
	
	
$(document).ready(function() {
	if(${l==2}){
		$("#switch_login").trigger("click");
	}
	
	$('#reg').click(function() {
		if ($('#userid').val() == "") {
			$('#userCue').html("<font color='red'><b>×id不可以为空</b></font>");
			return false;
		}
		
		if ($('#user').val() == "") {
			$('#userCue').html("<font color='red'><b>×用户名不能为空</b></font>");
			return false;
		}



		if ($('#user').val().length < 4 || $('#user').val().length > 16) {
			$('#userCue').html("<font color='red'><b>×用户名位4-16字符</b></font>");
			return false;
		}
		
		if ($('#passwd').val().length < 6) {
			$('#userCue').html("<font color='red'><b>×密码不能小于" + 6 + "位</b></font>");
			return false;
		}
		if ($('#passwd2').val() != $('#passwd').val()) {		
			$('#userCue').html("<font color='red'><b>×两次密码不一致！</b></font>");
			return false;
		}
	});
});
</script>
</html>