<#import "/uri.ftl" as uri />
<#import "/util.ftl" as util />
<!DOCTYPE HTML>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<meta name="Description" content="" />
	<meta name="Keywords" content="" />
	<title>好买数据分析平台</title>
	<@uri.link href = ["/reset.css","/style.css","/change.css"]/>
	<@uri.script src=[
	"/jquery/jquery-1.11.2.min.js","/laydate/laydate.js",
	"/jquery.tabs.js","/DD_belatedPNG.js","/jquery/jquery-ui.js"
	,"/jquery/jquery.blockUI.js"
	]/>
	
	<style type="text/css">
	.mainFrame{
		font-family: "微软雅黑";
		border-left: 160px solid #fff;
		min-width: 1200px;
		min-height: 1200px;
		padding-top: 50px;
		padding-left: 16px;
	}
	</style>
</head>
<body>
<div class="header">
	<div class="logobox fl"><a href="" class="pngFix" alt=""></a></div>
	<ul class="menu fl"></ul>
	<div class="tool fr">
		<a href="javascript:void(0);" title="设置"><img src="${uri.context_path}/images/gear.png" alt="" class="pngFix"></a>
		<a href="javascript:void(0);" title="用户" ><img src="${uri.context_path}/images/user.png" alt="" class="pngFix"></a>
		<a href="javascript:void(0);" title="退出" id="loginout"><img class="exit_sys" alt="" src="${uri.context_path}/images/exit.png"></a>
	</div>
</div>
<!-- 侧栏 begin -->
<div class="sidenav" id="sidenav" style="display:none;"></div>
<!-- 侧栏 end -->
<!-- main begin-->
<div id="main"></div>
<iframe id="outframe" name="outframe" class="mainFrame" style="display:none;" scrolling="auto"  ></iframe>
<!-- main end-->
<#include "../include/footer.ftl">
</body>

<@uri.script src=
["/highcharts.js","/highcharts-more.js","/modules/funnel.js",
	"/howbuyCharts.js","/no-data-to-display.js"
	"/gotop.js","/common.js","/uaa_util.js","/HashMap.js",
	"/ion.rangeSlider-2.1.1/ion-rangeSlider/ion.rangeSlider.js"
	,"/jquery/jquery.cookie.js","/jquery/jquery.zclip.min.js"
]/>

<@uri.script src=["/uaa_menu.js"]/>

<script type="text/javascript" >
window.uaa_context_path = "${uri.context_path}";
loadImg ="<img src='${uri.context_path}/images/load2.gif' style='margin-top: 15%;margin-left: 40%;margin-bottom: 20%;'>";
$(function(){
	var allPermission =[];
	var allPerList =[];
	<#if Session.headPermission??>
		allPermission = ${Session.headPermission};
	</#if>
	<#if Session.headPermission??>
		allPerList = ${Session.allPerList};
	</#if>
	//加载头部菜单
	loadHead(allPermission,allPerList);
	//初始化左侧菜单
	initSideNavMap(allPermission,allPerList);
	
	var hasPer = hasPermission(allPerList);
	if(hasPer){
		var headHtml = $(".header .menu");
		var _a = $(headHtml).first().find("a");
		var url =  _a.attr("url");
		var navid = _a.attr("navId");
		if(""!=navid)
			var nav = $(".header .menu").find("li[menuid='"+navid+"']").addClass("current");
		else
			$(".header .menu").find("li:eq(0)").addClass("current");
			
		var data={r:Math.random()};
		loadMainData(url,data,function(){
			console.log("load success");
			//添加头部点击事件
			eventHead();
		});
	}else{
		var tips = "您暂无访问权限，申请权限请邮件给部门总监和中心负责人审批，抄送yu.zhong@howbuy.com，谢谢配合！"; 
		$(".main").html('<div style="font-family: 微软雅黑;font-size:14px;">'+tips+'</div>');
	}
	
	loginOutEvent();
	
});

</script> 
</html>