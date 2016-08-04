<#import "/uri.ftl" as uri />
<#import "/util.ftl" as util />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<meta name="Description" content="" />
	<meta name="Keywords" content="" />
	<title>好买数据平台</title>
	<@uri.link href = ["/reset.css","/style.css","/change.css"]/>
	<@uri.script src=[
	"/jquery/jquery-1.11.2.min.js","/laydate/laydate.js",
	"/jquery.tabs.js","/jquery/jquery-migrate-1.2.1.js",
	"/DD_belatedPNG.js","/jquery/jquery.cookie.js","/jquery/jquery.zclip.min.js"
	]/>
	<!--[if IE 6]><script type="text/javascript" src="/js/global/DD_belatedPNG.js"></script><![endif]-->
	<!--[if IE 6]><script>$(function(){ DD_belatedPNG.fix(".pngFix,.pngFix:hover");})</script><![endif]-->
</head>
<body>
<div class="header">
	<div class="logobox fl"><a href="" class="pngFix" alt=""></a></div>
</div>
<!-- main begin-->
<div class="main" style="border-left:none;">
	<div class="login">
        	<div class="login_pic"><img src="${uri.context_path}/images/login_pic.png" alt="" ></div>
			<div class="login_title"><h3>欢迎登录好买数据平台</h3></div>
			<form name="login" action = "${uri.context_path}/login/login.htm" method="post">
            <div class="login_con">
            
               <table>
                    <tr>
                        <td width="20%">用户名</td> 
                        <td width="80%"><input type="text"  class="login_name" name="userName" placeholder="请输入用户名" /></td>
                    </tr>
                    <tr>
                        <td>密码</td>
                        <td><input type="password"  class="login_password" name="pwd" placeholder="请输入密码" /></td>
                    </tr>
                    <tr>
                        <td>验证码</td>
                        <td><input type="text"  class="login_yzm" name="yzm" placeholder="请输入验证码" />
                        <img src="${uri.context_path}/verifycode" style="cursor: pointer;" id="imageValue" title="点击刷新" alt="点击刷新"/></td>
                    </tr>
                </table>
                
                <div class="jz_password"><input type="checkbox" name = "remember" />记住我</div>
                <!--<input type="button" class="login_btn" name="login" value="登录" style="border:0" disabled="disabled">-->
            	  <a href="javascript:void(0);" class="login_btn" target="">登录</a>
            	<div class="tips"></div>
            	</div>
				</form>
            </div>
        </div>		

<!-- main end-->
<@uri.script src=["/login.js"]/>
<script type="text/javascript">
  $(function(){
  		var location = window.location.href;
		if(location.indexOf("login")==-1){
			window.location.href=location;
		}
		
		var username = $.cookie('username');
		var password = $.cookie('password');
		$("#imageValue").click(function(){
			var src= $(this).attr("src")+"?r="+Math.random();
			$(this).attr("src", src);
		});
		//将获取的值填充入输入框中
		$(".login_con input[name='userName']").val(username);
		$(".login_con input[name='pwd']").val(password); 
		if(username != null && username != '' && password != null && password != ''){//选中保存秘密的复选框
			$(".jz_password input[name='remember']").prop("checked",true);
		}
		<#if message??>
			$(".tips").html("${message!}");
		</#if>
		<#if name?? && pwd??  && yzm??>		
  			var loginName = "${name}";
			var loginpwd = "${pwd}";
			var loginyzm = "${yzm}";
			var message = "${message!}";
			$(".login_con input[name='userName']").val(loginName);
			$(".login_con input[name='pwd']").val(loginpwd); 
			$(".login_con input[name='yzm']").val(loginyzm)	;
			if("userError" == message){
				
				$(".tips").html("输入的用户名或密码错误！");
			}else if("yzmError" == message){
				$(".tips").html("输入的验证码错误！");
			}
			isClick = true;
			$(".login_con .login_btn").css("background-color",backcolor);
		<#else>
      		$(".login_con .login_btn").css("background-color","#C0C0C0");	
		</#if>
		
  });
 
 </script> 
</body>
</html>