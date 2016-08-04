<#import "/uri.ftl" as uri />
<div class="pop" id="overdueDiv" style="padding-bottom: 20px;">
	<#--<div class="login_title"><h3>欢迎登录好买数据平台</h3></div>-->
	<div class="login_title"><h2>会话已经超时,请重新登录</h2></div>
	<form name="login" action = "${uri.context_path}/login/login.htm" method="post">
            <div class="login_con">
              <#-- <table>
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
                </table>-->
        	   <#--<a href="javascript:void(0);" class="login_btn" target="">登录</a>-->
        	   <a href="${uri.context_path}/login/login.htm" class="login_btn" target="">去登录</a>
        	 </div>
	</form>
</div>
<input type="hidden" name="session_out" id="session_out" value="0">
<input type="hidden" name="ajax_login" id="ajax_login" value="1">
	
<#--<@uri.script src=["/login.js"]/>-->	
<script type="text/javascript">
uaa_context_path = "${uri.context_path}";
$.blockUI(
{message:$("#overdueDiv"),
	css:{cursor: '',left:'30%',width:'405px',top:'20%',position:'fixed',border:'0px',textAlign:'left'},
	overlayCSS:{cursor: ''}
});
</script>