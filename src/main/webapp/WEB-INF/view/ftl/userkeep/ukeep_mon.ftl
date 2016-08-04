<#import "/uri.ftl" as uri />
<#import "/util.ftl" as util />
<!DOCTYPE HTML>
<html>

<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<meta name="Description" content="" />
	<meta name="Keywords" content="" />
	<@uri.link href = ["/reset.css","/style_uk.css"]/>
	<@uri.script src=[
		"/userkeep.js"
	]/>
</head><body>
<!-- main begin-->
<div class="main3">
	<ul class="filter_a clearfix" style="margin-left: 35px;">
		<li>月份：
			<select name="monthSelect" id="monthSelect" class="bar1"></select>
		</li>
	</ul>
	<!-- 访问渠道 begin -->
	<div class="filter_b" style="min-width:1100px;">
		<dl class="fwqd">
			<dt>分析维度：</dt>
			<dd>
				<ul class="clearfix">
					<li class="current" wd="newtrade-m"><a href="javascript:void(0)" target="_self">新增交易用户</a></li>
					<li wd="newuser-m"><a href="javascript:void(0)" target="_self">新开户用户</a></li>
				</ul>
			</dd>
		</dl>
		<div class="fw" id="qudaoChoose"></div>
		<dl class="mt10 clearfix">
			<a href="javascript:void(0);" target="_self" class="btn-style-b btn2" style="float:right;" id="submitM">确定</a>
		</dl>
	</div>
	<!-- 访问渠道 end -->
	<!-- 公募开户渠道 begin -->
	<div class="chartbox hb-chart" id="ukeepGraphDetailM"></div>
</div>
<!-- main end-->
</body>
<script type="text/javascript">
$(function(){
	//加载年份
	loadMonth();
	//动态加载渠道选择
	var all = loadChannel(${gmtjqd!});
	$("#qudaoChoose").html(all);
	
	var fwqd = 0;
	//访问渠道
    $(".fwqd").find('li').click(function(){
     	var _this = $(this);
        var _index = _this.index() ;
		if(_index ==fwqd){
			return;
		}
        fwqd = _this.index();
        _this.parent().find('li').removeClass('current');
        _this.addClass("current");
    });
    
    $("#qudaoChoose dl.Inst").hide();
	$("#qudaoChoose dl.Platform").hide();
	$("#openInstHB000A001").find("li").eq(1).hide();
	$("#qudaoChoose li").click(function(){
		debugger;
		var arr = $(this).attr("para").split("%");
		var id = arr[0];
		for(var i = 1;i<arr.length;i++)
		{
			id = id + arr[i];
		}
		var p = $(this).parent().parent().parent();
		$("#qudaoChoose select").hide();
		$("#qudaoChoose select").each(function(){
				$(this).val("-1");
		});
		$("#qudaoChoose select").hide();
		if(p.hasClass("khjg")){
			$("#qudaoChoose li").removeClass("current");
			$(this).addClass("current");
			$("#qudaoChoose dl.Inst").hide();
			$("#qudaoChoose dl.Platform").hide();
			
			$("#"+id).show();
			$("#"+id).find("li").eq(0).addClass("current");
		}
		if(p.hasClass("Inst")){
			$("#qudaoChoose .Inst li").removeClass("current");
			$("#qudaoChoose .Platform li").removeClass("current");
			$("#qudaoChoose dl.Platform").hide();
			
			$(this).addClass("current");
			$("#"+id).show();
			$("#"+id).find("li").eq(0).addClass("current");
		}
		if(p.hasClass("Platform")){
			$(this).parent().find("li").removeClass("current");
			$("#qudaoChoose .Platform li").removeClass("current");
			$(this).addClass("current");
			$("#"+id).show();
		}
	});

	//提交查询事件
	submitQueryM();
	$("#submitM").click();
	
});


</script>

</html>