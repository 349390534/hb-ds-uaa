<#import "/console.ftl" as con />
<#import "/util.ftl" as util />
<#import "/uri.ftl" as uri />
<#if datalist??>
	<#list datalist as data>
	<tr>
		<td><#if data.statdt??>${data.statdt?date('yyyyMMdd')}</#if></td>
		<#list theadlist as head>
			<#if wd == '1' && head != '-1'>
			<td style="text-align:right;" head="${head!}">
			<#if data[head]??>
			<@util.formatData head data[head]/>
			</#if>
			</td>
			</#if>
			<#if wd == '2' && head != '-1'>
			<td style="text-align:right;" head="${head!}"><#if data[head]??>
			<@util.formatData head data[head]/>
			</#if></td>
			</#if>
		</#list>
	</tr>
	</#list>
<#else>
<tr>
	<td></td>
	<td colspan="${theadlist?size}" align="left">
		暂无数据
	</td>
</tr>

</#if>
<script type="text/javascript">

$(function(){
	
	//前一页
	$("#prepage").click(function(){
		var currentOrderby = "";
		var currentOrder = "";
		if(lastClickTrend !="" && lastTrendOrder !="")
		{
			currentOrderby = lastClickTrend;
			currentOrder = lastTrendOrder;
		}
		else
		{
			currentOrderby = orderby;
			currentOrder = order;
		}
		curpage = curpage - 1;
		var detailParams = detailChecked("#trendDetailAll","#trendDetailOpen input","#trendDetailTrade input");
		if(detailParams == false)
			return false;
		if(curpage>0){
			pageshow(curpage,maxpage,function(data){
				$("#pagecontrol").html(data);
				ajaxRequest(
	                    {url:'${uri.context_path}/auth/fund/trendDetailTbody.htm',
	                    postMethod:'POST',
	                    params:params+"&"+detailParams+"&pageRows="+pagerows+"&curPage="+curpage+"&orderBy="+currentOrderby+"&order="+currentOrder,
	                    callback:function(data){
	                    
	                    	$('#TrendDetailTbody').html(data);
	                    	$("#showPageLoad").hide();
	                    	checkClickQuota(wd,"1");
                    	}
	                    });
	                    
			});
			$("#showPageLoad").show();
		}
		else
			curpage = 1;
	});
	//后一页
	$("#nextpage").click(function(){
		debugger;
		var currentOrderby = "";
		var currentOrder = "";
		if(lastClickTrend !="" && lastTrendOrder !="")
		{
			currentOrderby = lastClickTrend;
			currentOrder = lastTrendOrder;
		}
		else
		{
			currentOrderby = orderby;
			currentOrder = order;
		}
		curpage = curpage + 1;
		var detailParams = detailChecked("#trendDetailAll","#trendDetailOpen input","#trendDetailTrade input");
		if(detailParams == false)
			return false;
		if(curpage<=maxpage){
			pageshow(curpage,maxpage,function(data){
				$("#pagecontrol").html(data);
				ajaxRequest(
	                    {url:'${uri.context_path}/auth/fund/trendDetailTbody.htm',
	                    postMethod:'POST',
	                    params:params+"&"+detailParams+"&pageRows="+pagerows+"&curPage="+curpage+"&orderBy="+currentOrderby+"&order="+currentOrder,
	                    callback:function(data){
	                    	$('#TrendDetailTbody').html(data);
	                    	$("#showPageLoad").hide();
	                    	checkClickQuota(wd,"1");
                    	}
	                    });
			});
			$("#showPageLoad").show();
		}
		else 
			curpage = maxpage;
	});
	//点击某一页
	$("#pagecontrol li a").click(function(){
		debugger;
		var currentOrderby = "";
		var currentOrder = "";
		if(lastClickTrend !="" && lastTrendOrder !="")
		{
			currentOrderby = lastClickTrend;
			currentOrder = lastTrendOrder;
		}
		else
		{
			currentOrderby = orderby;
			currentOrder = order;
		}
		curpage = Number($(this).text());
		var detailParams = detailChecked("#trendDetailAll","#trendDetailOpen input","#trendDetailTrade input");
		if(detailParams == false)
			return false;
		pageshow(curpage,maxpage,function(data){
			$("#pagecontrol").html(data);
			ajaxRequest(
	                {url:'${uri.context_path}/auth/fund/trendDetailTbody.htm',
	                postMethod:'POST',
	                params:params+"&"+detailParams+"&pageRows="+pagerows+"&curPage="+curpage+"&orderBy="+currentOrderby+"&order="+currentOrder,
	                callback:function(data){
	                	$('#TrendDetailTbody').html(data);
	                	$("#showPageLoad").hide();
	                	checkClickQuota(wd,"1");
                	}
	                });
		});
		$("#showPageLoad").show();
	});

});
</script>