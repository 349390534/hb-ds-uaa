<#import "/uri.ftl" as uri />
<#import "/util.ftl" as util />
<!DOCTYPE HTML>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<meta name="Description" content="" />
	<meta name="Keywords" content="" />
	<title>流量监控</title>
	<@uri.link href = ["/reset.css","/style_at.css"]/>
	<@uri.script src=[
		"/uaa-activity.js","/uaa-activity-chart.js"
	]/>
</head>
<body>
<!-- main begin-->
<div class="main">
	<ul class="filter_a clearfix">
		<li>
			<select name="selectOption" id="selectOption" class="bar1">
				<option value="0" selected="selected">当日</option>
				<option value="-1">昨天</option>
				<option value="-3">近3天</option>
				<option value="-7">近7天</option>
				<option value="-20">近20天</option>
				<option value="-30">近30天</option>
				<option value="zdy">自定义</option>
			</select>
		</li>
		<li>
		<input type="text" name="start_date" id="start_date" class="laydate-icon" >
		</li>
		<li style="margin:0 5px;">至</li>
		<li>
		<input type="text" name="end_date" id="end_date" class="laydate-icon" >
		</li>
	</ul>
	<!-- 访问渠道 begin -->
	<div class="filter_b_at">
		<dl class="fwqd" style="border-bottom:0;">
			<dt>访问渠道：</dt>
			<dd>
				<ul id="fwqd"></ul>
			</dd>
		</dl>
		<dl class="mt10 hide other-qd-box">
			<dt>其他渠道：</dt>
			<dd>
				<select name="other_channel" id="other_channel">
					<option value="">全部</option>
				</select>
				<input id="submit" type="submit" value="查询" style="width: 80px;" class="btn-style-a">
			</dd>
		</dl>
		<dl class="mt10 hide tgqd-box" id="tgqd">
			<dt>推广渠道：</dt>
			<dd>
				<select id="queryRouteOne" name="" style="min-width: 90px;"><#include "../include/indexQueryRouteOne_wl.ftl"></select>
				<select id="queryRouteTwo" name="" style="min-width: 90px;"><#include "../include/indexQueryRouteTwo_wl.ftl"></select>
				<select id="queryRouteThree" name= "" style="min-width: 90px;"><#include "../include/indexQueryRouteThree_wl.ftl"></select>
				<input id="submit"  type="submit" value="查询" style="width: 80px;" class="btn-style-a">			
			</dd>
		</dl>
		<dl class="mt10 hide ssyq-box">
			<dt>搜索引擎：</dt>
			<dd>
				<ul id="ssyq"></ul>
			</dd>
		</dl>
		<dl>
			<dd class="dbrq"><input type="checkbox" id="dbrq" class="compare_date"><label for="dbrq1">对比其他日期</label>
				<input type="text" name="time" value="请输入日期" class="db_date" id="date" style="display: none;">
			</dd>
		</dl>
	</div>
	<!-- 访问渠道 end -->
	<!-- 分布图/趋势图 begin -->
	<div class="chartbox" id="chart_activity"></div>
	<!-- 分布图/趋势图 end -->
	<!-- 明细 begin -->
	<p class="endtime">数据截止时间：<begin id="begin"></begin>　至　<end id="end"></end></p>
	<div class="detail_data" style="margin:10px 20px 0;" id="detail_activity">
	<#include "activity_detail.ftl">
	</div>
	<!-- 明细 end -->
	<!-- 指标解释 begin -->
	<#include "../include/quota_explain_activity.ftl" />
	<!-- 指标解释 end -->
</div>
<!-- main end-->

<script type="text/javascript">
	function matchTime(){
		var startTime = $("#start_date").val();
		var endTime = $("#end_date").val();
		var yesterday = laydate.now(-1);
		var now = laydate.now();
		if(startTime==endTime){
			if(endTime == yesterday){
				$("#selectOption").val("-1");//昨天
			}
		}else if(yesterday==endTime){//结束日期选择昨天
			var start = new Date(startTime).getTime();
			var end = new Date(endTime).getTime();
			var interval = (end-start)/(1000*60*60*24);//转换为天数
			interval+=1;
			$("#selectOption").find("option[value='"+interval+"']").prop("selected",true);
		}
	}

	var start = {
	    elem: '#start_date',
	    format: 'YYYY-MM-DD', // 分隔符可以任意定义，该例子表示只显示年月
	    max : laydate.now(),//设定最大日期为当前日期
	    istime: true,
		istoday: true,
	    choose: function(date){ //选择日期	完毕的回调
	        $("#selectOption").val("zdy");
	          end.min = date; //开始日选好后，重置结束日的最小日期
     		  end.start = date; //将结束日的初始值设定为开始日
     		  matchTime();
     		  laydate(end);
	    }
	};
	var end = {
    	elem: '#end_date',
	    format: 'YYYY-MM-DD', // 分隔符可以任意定义，该例子表示只显示年月
	    max : laydate.now(),//设定最大日期为当前日期
	    istime: true,
		istoday: true,
	    choose: function(date){ //选择日期	完毕的回调
	        $("#selectOption").val("zdy");
	        start.max = date; //结束日选好后，重置开始日的最大日期
	        matchTime();
	    }
	};
	$("#start_date").val(laydate.now());
	$("#end_date").val(laydate.now());
	laydate(start);
	laydate(end);
</script>


<script type="text/javascript">
<#if rootChannelJson??>
rootChannelJson = ${rootChannelJson!};
</#if>
<#if zeroChannelTagJson??>
zeroChannelTagJson = ${zeroChannelTagJson!};
</#if>
var _dataJson_coll=[];
<#if jsonDataChannel??>
_dataJson_coll = ${jsonDataChannel!};
</#if>
var _dataJson=[];
<#if jsonData??>
_dataJson = ${jsonData!};
</#if>

$(function(){

	//初始化渠道
	initRootChannel();
	//渠道选中事件
	eventDdClick();
	//注册查询 事件
	eventSubmit("submit");
	$("input#submit:eq(0)").click();
	bindDateClick();
	loadTime();
	//每五秒钟扫描一次
	intervalOfActivity = setInterval("autoRefeshDataAt()",5000);
})
</script>

</body>
</html>