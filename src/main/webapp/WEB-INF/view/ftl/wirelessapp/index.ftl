<#import "/util.ftl" as util />
<#import "/uri.ftl" as uri />
<#import "/host.ftl" as host />
<!DOCTYPE HTML>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<meta name="Description" content="" />
	<meta name="Keywords" content="" />
	<title>好买分析</title>
	<@uri.link href = ["/style_wla.css","/reset.css"]/>
	<!--[if IE 6]><script type="text/javascript" src="/js/global/DD_belatedPNG.js"></script><![endif]-->
	<!--[if IE 6]><script>$(function(){ DD_belatedPNG.fix(".pngFix,.pngFix:hover");})</script><![endif]-->
	<@uri.script src=["/wireless_app.js","/wireless_drawchart.js"]/>
</head>
<body>

<!-- main begin-->
<div class="main">
	<!-- 访问渠道 begin -->
	<div class="filter_b clearfix">
		<dl class="fwqd">
			<dt>平台：</dt>
			<dd class="wd">
				<ul id="ul_outlet">
					<li class="current all" code="-1"><a href="#" target="_self">全部</a></li>
					<li class="cxg" code="HZLX_HOWBUY_CAPP"><a href="javascript:void(0);" target="_self">储-APP</a></li>
					<li class="zj" code="HZLX_HOWBUY_ZAPP"><a href="javascript:void(0);" target="_self">掌-APP</a></li>
				</ul>
			</dd>
			<dd class="wd">
               <select name="outlet" id="outlet" class="bar">
                    <option value="">全部</option>
               </select>     
               <input type="button" id="search_data" value="查询" style="width:80px;" class="btn-style-q">
			</dd>
		</dl>
		<dl class="mt10 cxg-box">
			<dt>无线客户端统计-<pt id ="proid_name">全部</pt><span>数据截止时间：<time id="time"><time></span></dt>
			<dd  class="dbrq"><input type="checkbox" id="dbrq" class="compare_date"><label for="dbrq1">对比其他日期</label>
			<input type="text" name="time" value="请输入日期" class="db_date" id="date" >
			</dd>
		</dl>
	</div>
	<!-- 访问渠道 end -->
	
	<!-- 分布图/趋势图 begin -->
	<div id="html_data">
		<#include 'wireless_data.ftl'/>
	</div>
	<!-- 分布图/趋势图 end -->
	<#include "../include/wirelessapp_quota_explain.ftl">
</div>
<!-- main end-->
<script type="text/javascript">
wirelessOutletJson = ${wirelessOutletJson!};
var time = {
    elem: '#date',
    format: 'YYYY-MM-DD', // 分隔符可以任意定义，该例子表示只显示年月
    max : laydate.now(-1),//设定最大日期为当前日期
    istime: true,
	istoday: false,
    choose: function(datas){ //选择日期	完毕的回调
    }
};
laydate(time);
$("input#date").val(laydate.now(-1));

if (null != wirelessOutletJson) {
	// 初始化无线网点
	loadOutlet(wirelessOutletJson);
}
bindFwqdClick();
bindDateClick();

$("#search_data").click(function(){
	submitData('${uri.context_path!}');
});

//每五秒钟扫描一次
intervalOfWireless = setInterval("autoRefeshData('${uri.context_path!}')",5000);
</script>

</body>
</html>