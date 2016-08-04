<#import "/uri.ftl" as uri />
<#import "/util.ftl" as util />
<!DOCTYPE HTML>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<meta name="Description" content="" />
	<meta name="Keywords" content="" />
	<title>基金销量数据</title>
	<@uri.link href = ["/reset.css","/style_fundsale.css","/jquery/ui/jquery-ui.css"]/>
	<@uri.script src=["/fundsale/fundsale_index.js","/fundsale/fundsale_chart.js"]/>
</head>
<body>
<!-- main begin-->
<div class="main">
	<ul class="filter_a clearfix">
		<li>
			<select name="selectOption" id="selectOption" class="bar1">
				<option value="-1">昨天</option>
				<option value="-3">近3天</option>
				<option value="-7">近7天</option>
				<option value="-20">近20天</option>
				<option value="-30">近30天</option>
				<option value="zdy">自定义</option>
			</select>
		</li>
		<li><input id="start_date" name="start_date" type="text" class="laydate-icon"></li>
		<li style="margin:0 5px;">至</li>
		<li><input id="end_date" name="end_date" type="text" class="laydate-icon"></li>
	</ul>
	
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
	<!-- 分析维度 begin -->
	<div class="filter_d">
		<dl>
			<dt style="line-height:40px;">分析维度：</dt>
			<dd>
				<ul class="tab_menu4">
					<li class="current" wd="1">销量数据</li>
					<li wd="2">收入数据</li>
				</ul>
				<input id="submit" type="button" value="确定" style="width: 80px;" class="btn-style-b fr">
			</dd>
		</dl>
	</div>
	<!-- 分析维度 end -->
	<div class="tab_box4">
		<div id="maindata"></div>
		<!-- 基金查询 begin -->
		<#include "fundAndCompany.ftl" />
		<!-- 基金查询 end -->
		<!--趋势明细begin-->
		<#include "trendAndfundTypeData.ftl" />
		<!-- 趋势明细 end -->
	</div>

<#include "../include/fundsale_quota_explain.ftl" />
</div>
<!-- main end-->

<script type="text/javascript">

$(function(){
	var contentPath = '${uri.context_path!}';
	submitClickEvent(contentPath);
	$("#submit").click();
})
</script>

</body>
</html>