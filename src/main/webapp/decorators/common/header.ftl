	<div class="headtop">
	<div class="headlogin">
        <div class="left_login" id="lgstatus">
       <#if Session.LOGIN_USER_INFO?exists>
        您好！<b>${Session.LOGIN_USER_INFO.userName!}</b>，<a onclick="javascript:delCookie('USER_INFO_COOKIE');commonloginout('${uri.user_loginout}?action=logoutjs&f=aflogout');" href="javascript:void(0);" target="_self">退出</a>| <a href="${uri.user_index}">我的好买</a>| <a href="${uri.myfund_index}">我的账簿</a>| <a href="${uri.myblog_index}">我的博客</a>
       <#else>
        您好！欢迎登录好买基金网，<a href="${uri.host}/login/login.htm">我要登录</a> <a class="linkRed1" href="${uri.host}/register/register.htm">免费注册</a>/<a href="${uri.host}/findpasswd/index.htm">忘记密码</a>
        </#if>
        </div>
        <div class="right_nav"><a class="mobileicon" href="${uri.host}/mobile">手机站</a> | <a onclick="SetHome(this,top.location.href);" target="_self" href="javascript:void(0);">设为首页</a> | <a onclick="AddFavorite(top.location.href,document.title);" target="_self" href="javascript:void(0);">加入收藏</a></div>
    </div>
    <div class="headmenu">
   	  <a href="${uri.host}">首页</a><span class="line"><a href="${uri.host}/fund/">公募基金</a> <a href="${uri.smhost}">私募基金</a> <a href="${uri.host}/trust/" class="linkRed2">信托产品</a> <a href="${uri.host}/vcpe/">私募股权</a> <a href="${uri.host}/zhlc/">一对多</a> <a href="${uri.smhost}/overseas/">海外基金</a></span><span class="line"><a href="${uri.host}/research/">研报</a> <a href="${uri.host}/news/">资讯</a> <a href="${uri.host}/subjects/list.htm" target="_blank">专题</a> <a href="${uri.host}/fundcollege/">学院</a> <a href="${uri.host}/fundtool/">工具</a></span><span class="line"><a href="${uri.bloghost}/">博客</a> <a href="${uri.host}/forum/">论坛</a> <a href="${uri.host}/video/">视频</a></span><span class="line"><a href="http://www.ehowbuy.com/" class="linkRed2">基金交易</a> <a href="${uri.smhost}/shop/">私募商城</a> <a href="${uri.host}/about/smyuyue.html">产品预约</a> </span>
    </div>
</div>