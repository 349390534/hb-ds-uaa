/**
 * 登录js
 */
var backcolor = $(".login_con .login_btn").css("background-color");
var isClick = false;
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
      	
      	

$(".login_btn").click(function() {
	if (isClick) {
		var name = $(".login_con input[name='userName']").val();
		var pwd = $(".login_con input[name='pwd']").val();
		var yzm = $(".login_con input[name='yzm']").val();
		var html = "";
		if (name == null || "" == name) {
			html = "用户名或密码为空!";
			$(".tips").html(html);
			return false;
		}
		if (pwd == null || "" == pwd) {
			html = "用户名或密码为空!";
			$(".tips").html(html);
			return false;
		}
		if (yzm == null || "" == yzm) {
			html = "验证码为空!";
			$(".tips").html(html);
			return false;
		}
		if (!checkUserName(name)) {
			html = "输入的用户名格式错误!";
			$(".tips").html(html);
			return false;
		}

		var uName = $(".login_con input[name='userName']").val();
		var psw = $(".login_con input[name='pwd']").val();
		if ($(".jz_password input[name='remember']").prop("checked")) {// 保存密码
			$.cookie('username', uName, {
				expires : 7,
				path : '/'
			});
			$.cookie('password', psw, {
				expires : 7,
				path : '/'
			});
		} else {// 删除cookie
			$.cookie('username', '', {
				expires : -1,
				path : '/'
			});
			$.cookie('password', '', {
				expires : -1,
				path : '/'
			});
		}
		var ajax_login = $("#ajax_login");
		if(ajax_login.length==0){
			$("form[name='login']").submit();
		}else{
			//Ajax提交登录
			var url =$("form[name='login']").attr("action");
			var param = $("form[name='login']").serialize();
			$.ajax({
		           type: "post",
		           cache: false,
		           url: url,
		           data : param,
		           success: function(data){
		              if(data!=null){
		            	  $.unblockUI();
		            	  $("div#time_out_div").remove();
		            	  $("#overdueDiv").remove();
		              }
		           },
		           error:function(xhr,err){ }
		         });
		}

	}

});
	 

$(".login_con input[name='yzm']").bind('keypress', function(event) {

	if (event.keyCode == "13") {

		$('.login_btn').click();
	}
});
       
       
       


function checkUserName(name) {
	var pattern = /^\w+((-\w+)|(\.\w+))*\@[A-Za-z0-9]+((\.|-)[A-Za-z0-9]+)*\.[A-Za-z0-9]+$/;
	var flag = pattern.test(name);
	var pattern1 = /^[a-zA-Z0-9]+\.[a-zA-Z0-9]+$/;
	var flag1 = pattern1.test(name);
	return flag || flag1;
}


function inputPrompt() {
	$(".login_con input[name='userName']").focus(function() {
		if ($(this).attr("inputTip") == $(this).val()) {
			$(this).val("").css("color", "");
		}

	}).blur(function() {
		if ($.trim($(this).val()).length == 0) {
			$(this).val($(this).attr("inputTip")).css("color", "#c0c0c0");
		}
	});
	$(".login_con input[name='pwd']").focus(function() {
		if ($(this).attr("inputTip") == $(this).val()) {
			$(this).val("").css("color", "").attr("type", "password");
		}

	}).blur(
			function() {
				if ($.trim($(this).val()).length == 0) {
					$(this).val($(this).attr("inputTip")).css("color",
							"#c0c0c0").attr("type", "text");
				}
			});
	$(".login_con input[name='yzm']").focus(function() {
		if ($(this).attr("inputTip") == $(this).val()) {
			$(this).val("").css("color", "");
		}

	}).blur(function() {
		if ($.trim($(this).val()).length == 0) {
			$(this).val($(this).attr("inputTip")).css("color", "#c0c0c0");
		}
	}).blur();
}