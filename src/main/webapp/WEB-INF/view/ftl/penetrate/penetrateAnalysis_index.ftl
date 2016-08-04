<#import "/uri.ftl" as uri />
<#import "/util.ftl" as util />
<!DOCTYPE HTML>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<meta name="Description" content="" />
	<meta name="Keywords" content="" />
	<title>好买分析</title>
	<@uri.link href = ["/reset.css","/style_penetrate.css"]/>
	<@uri.link href = [
	"/ion.rangeSlider-2.1.1/normalize.css",
	"/ion.rangeSlider-2.1.1/ion.rangeSlider.css"
	"/ion.rangeSlider-2.1.1/ion.rangeSlider.skinFlat.css"
	]/>
	<@uri.script src=
	["/penetrate_analysis_chart.js","/penetrate_analysis_data.js"
	]/>
</head>
<body>

<!-- main begin-->
<div class="main">
	<ul class="filter_a clearfix">
		<li><input name="start" id="start" type="text" value="请输入日期" class="laydate-icon"></li>
		<li style="margin:0 5px;">至</li>
		<li><input id="end" name="end" type="text" value="请输入日期" class="laydate-icon" maxEnd="${maxEnd!}"></li>
		<li><input type="button" value="查询" style="width: 80px;" class="btn-style-a ml10" id="query_button"></li>
	</ul>
	<div id="html_data">
		<#include 'penetrateAnalysis_data.ftl'/>
	</div>
	
	<#include '../include/penetrate_quota_explain.ftl'>
</div>
<!-- main end-->
<script type="text/javascript">
$(function(){
	//初始化日期
	var start = {
		elem : '#start',
		format : 'YYYY-MM-DD', // 分隔符可以任意定义，该例子表示只显示年月
		max : $("#end").attr("maxEnd"),//设定最大日期为当前日期
		istime : true,
		istoday : false,
		choose : function(datas) { //选择日期	完毕的回调
			end.min = datas; //开始日选好后，重置结束日的最小日期
			end.start = datas; //将结束日的初始值设定为开始日
			laydate(end);
		}
	};
	var end = {
		elem : '#end',
		format : 'YYYY-MM-DD', // 分隔符可以任意定义，该例子表示只显示年月
		max : $("#end").attr("maxEnd"),//设定最大日期为当前日期
		istime : true,
		istoday : false,
		choose : function(datas) { //选择日期	完毕的回调
			start.max = datas; //结束日选好后，重置开始日的最大日期
		}
	};
	laydate(start);
	laydate(end);
	//注册查询事件
	$("#query_button").click(function (){
		var start = $("#start").val();
		var end = $("#end").val();
		if(!start || !end){
			alert("请选择日期");
		}
		var params ="start="+start+"&end="+end;
		var path = window.uaa_context_path ||""; 
		ajaxRequest(
				{
					url:path+'/auth/pta/loadApiData.htm',
			        postMethod:'POST',
			        params:params,
			        session_out:false,
			        callback:function(data){
			        	$('#html_data').html(data);
			        	$("#query_button").removeAttr("disabled");
			        }
		        });
		        debugger;
	    $('#html_data').html(loadImg);
	    $("#query_button").attr("disabled","disabled");
	});
});
</script>


</body>
</html>