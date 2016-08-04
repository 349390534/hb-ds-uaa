<#import "/uri.ftl" as uri />
<#import "/util.ftl" as util />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<meta name="Description" content="" />
	<meta name="Keywords" content="" />
	<title>好买数据平台登录</title>
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
	<div class="tool fr">
		
	</div>
</div>
<!-- main begin-->
<div class="main" style="border-left:none;">
	<div class="chartbox">
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
                        <td><input type="text"  class="login_yzm" name="yzm" placeholder="请输入右侧数字" /><img src="${uri.context_path}/images/yzm.png" /></td>
                    </tr>
                </table>
                
                <div class="jz_password"><input type="checkbox" name = "remember" />记住我</div>
                <!--<input type="button" class="login_btn" name="login" value="登录" style="border:0" disabled="disabled">-->
            	  <a href="javascript:void(0);" class="login_btn" target="">登录</a>
            	<div class="tips"></div>
				</form>
            </div>
        </div>						
	</div>
	<!-- 分布图/趋势图 end -->
</div>
<!-- main end-->
<@uri.script src=[
	"common.js"]/>
<script type="text/javascript">
  $(function(){
		var isClick = false;
		var backcolor = $(".login_con .login_btn").css("background-color");
		
		var username = $.cookie('username');
		var password = $.cookie('password');
		//将获取的值填充入输入框中
		$(".login_con input[name='userName']").val(username);
		$(".login_con input[name='pwd']").val(password); 
		if(username != null && username != '' && password != null && password != ''){//选中保存秘密的复选框
			$(".jz_password input[name='remember']").prop("checked",true);
		}
		<#if name?? && pwd??  && yzm??>		
  			var loginName = "${name}";
			var loginpwd = "${pwd}";
			var loginyzm = "${yzm}";
			var message = "${message!}";
			$(".login_con input[name='userName']").val(loginName);
			$(".login_con input[name='pwd']").val(loginpwd); 
			$(".login_con input[name='yzm']").val(loginyzm)	;
			$(".tips").html(message);
			isClick = true;
			$(".login_con .login_btn").css("background-color",backcolor);
		<#else>
      		$(".login_con .login_btn").css("background-color","#C0C0C0");	
		</#if>
		
      	
	

	$(".login_con input[name='userName']").on('input',function(e){  
   			var name = $(".login_con input[name='userName']").val();
			var pwd = $(".login_con input[name='pwd']").val();
			var yzm = $(".login_con input[name='yzm']").val();
			if(name !=null && pwd!=null && yzm!=null 
				&& name!="" && pwd !="" &&yzm!=""){
				$(".login_con .login_btn").css("background-color",backcolor);
				isClick = true;
			}else{
				$(".login_con .login_btn").css("background-color","#C0C0C0");
				isClick = false;
			}
		}); 
		
		$(".login_con input[name='pwd']").on('input',function(e){  
   			var name = $(".login_con input[name='userName']").val();
			var pwd = $(".login_con input[name='pwd']").val();
			var yzm = $(".login_con input[name='yzm']").val();
			if(name !=null && pwd!=null && yzm!=null 
				&& name!="" && pwd !="" &&yzm!=""){
				$(".login_con .login_btn").css("background-color",backcolor);
				isClick = true;
			}else{
				$(".login_con .login_btn").css("background-color","#C0C0C0");
				isClick = false;
			}
		}); 
		
		$(".login_con input[name='yzm']").on('input',function(e){  
   			var name = $(".login_con input[name='userName']").val();
			var pwd = $(".login_con input[name='pwd']").val();
			var yzm = $(".login_con input[name='yzm']").val();
			if(name !=null && pwd!=null && yzm!=null 
				&& name!="" && pwd !="" &&yzm!=""){
				$(".login_con .login_btn").css("background-color",backcolor);
				isClick = true;
			}else{
				$(".login_con .login_btn").css("background-color","#C0C0C0");
				isClick = false;
			}
		});
      	
      	
	  $(".login_btn").click(function(){
		if(isClick){
			var name = $(".login_con input[name='userName']").val();
		 	var pwd = $(".login_con input[name='pwd']").val();
		  	var yzm = $(".login_con input[name='yzm']").val();
		 	 var remember ;
		  	var html = "";
		  	if(name==null || ""==name){
		   	 	html = "用户名或密码为空!"
		  		$(".tips").html(html);
		  		return false;
		  	}
		  if(pwd == null || ""==pwd){
		  	html = "用户名或密码为空!"
		  	$(".tips").html(html);
		  	return false;
		  }
		  if(yzm == null || ""==yzm){
		  	html = "验证码为空!"
		  	$(".tips").html(html);
		  	return false;
		  }
		  if(!checkUserName(name)){
				html = "输入的用户名格式错误!"
		  		$(".tips").html(html);
				return false;
			}
			
		  	var uName =$(".login_con input[name='userName']").val();
			var psw = $(".login_con input[name='pwd']").val();
			if($(".jz_password input[name='remember']").prop("checked")){//保存密码
				$.cookie('username',uName, {expires:7,path:'/'});
				$.cookie('password',psw, {expires:7,path:'/'});
			}else{//删除cookie
				$.cookie('username', '', { expires: -1, path: '/' });
				$.cookie('password', '', { expires: -1, path: '/' });
			}
		  $("form[name='login']").submit();
			
		}
		  
		 
	  });
  });
 
 function checkUserName(name){
 	var pattern = /^\w+((-\w+)|(\.\w+))*\@[A-Za-z0-9]+((\.|-)[A-Za-z0-9]+)*\.[A-Za-z0-9]+$/;  
　　　var flag = pattern.test(name); 
	var pattern1 =  /^[a-zA-Z0-9]+\.[a-zA-Z0-9]+$/;
	var flag1 = pattern1.test(name);
	return flag || flag1;
 }
 </script> 
</body>
</html>