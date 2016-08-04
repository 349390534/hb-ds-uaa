<#import "/console.ftl" as con />
<#import "/uri.ftl" as uri />
<#import "/util.ftl" as util />
<#if theadlist??>
<table class="det-d-qsmx" style="table-layout:fixed ;" id="trendDetailTable">

	<thead id="trendDetailThead">
		<tr class="tit">

			<td class="wdqd"><a href="javascript:void(0)" target="self" class="sort" tag = "stat_dt" style="color:white;">时间</a></td>
			<#list theadlist as head>
				<#if wd == '1' && head != '-1'>
				<td head="${head!}"><a href="javascript:void(0)" target="self" class="sort" tag = "${head}">${con.opennorms[head!]!}</a></td>
				</#if>
				<#if wd == '2' && head != '-1'>
				<td head="${head!}"><a href="javascript:void(0)" target="self" class="sort" tag = "${head}">${con.tradenorms[head!]!}</a></td>
				</#if>
			</#list>
		</tr>
	    <#if coll?? && coll?size gt 0 >
		<tr>
		    <td>全部</td>
		    <#list coll as co>
		    <#list theadlist as head>
		    	<#if head != '-1'>
		    		<td style="text-align:right;" head="${head!}">
		    		<#if (co[con.normsum[head]])??>
	    				<@util.formatData head co[con.normsum[head]]/>
		    		</#if>
		    		</td>
		    	</#if>
		    </#list>
		    </#list>
		</tr>
	    </#if>
	</thead>
	
	<tbody id="TrendDetailTbody">
		<#include "fundTrendDetailTbody.ftl">
	</tbody>														
</table>
<div class="det-page">
	<div class="bar1 fl">共<span id="total-rows"><#if maxrows??>${maxrows!0}</#if></span>项，每页显示
		<select name="" id="rowsToshow">
			<option value="10">10</option>
			<option value="20">20</option>
			<option value="50">50</option>
		</select>
	</div>
	<div class="bar2 fr" id="pagecontrol">
		<ul>
			<#if pages??>
				<li><span id="prepage">上一页</span></li>
				<#if (pages >= 5)>
				<li class="num current"><a>1</a></li>
				<li class="num"><a>2</a></li>
				<li class="num"><a>3</a></li>
				<li class="num"><a>4</a></li>
				<li class="num"><a>5</a></li>
				<li><span id="nextpage" class="active">下一页<span></li>
				<#else>
					<li class="num current"><a>1</a></li>
					<#if (pages >= 2)>
					<#list 2..pages as p>
						<li class="num"><a>${p!}</a></li>
					</#list>
					</#if>
					<li><span id="nextpage">下一页<span></li>
				</#if>
			</#if>
		</ul>
	</div>
</div>
</#if>
<script>
	
$(function(){
	<#if rows??>
		$("#rowsToshow").val(${rows!});
	</#if>
	$("#trendDetailThead a").each(function(){
		if($(this).attr("tag")!='stat_dt'){
			$(this).addClass("sort-up");
		}
	});
	//趋势明细排序 
	$("#trendDetailThead a").click(function(){
		var currentClick = $(this).attr("tag");
		var currentOrder = "desc";
		if(currentClick == lastClickTrend){
			if(lastTrendOrder === "asc")
			{
				currentOrder = "desc";
			}
			else if(lastTrendOrder === "desc"){
				currentOrder = "asc";
			}
		}
		orderby = $(this).attr("tag");
		var detailParams = detailChecked("#trendDetailAll","#trendDetailOpen input","#trendDetailTrade input");
		if(detailParams == false)
			return false;
		detailParams = detailParams + "&pageRows="+pagerows+"&curPage=1&orderBy="+orderby+"&order="+currentOrder;
		ajaxRequest(
                {url:'${uri.context_path}/auth/fund/trendDetail.htm',
                postMethod:'POST',
                params:params+"&"+detailParams,
                session_out:false,
                callback:function(data){
	                	$('#fundTrendDetail').html(data);
	                	$("#showTrend").hide();
	                	maxpage = Math.ceil($("#total-rows").text()/pagerows);
	            		if(maxpage > 1)
	            			$("#nextpage").addClass("active");
	            		$("#trendDetailThead a").each(function(){
	                		if($(this).attr("tag") === orderby)
	                		{
	                			if(currentOrder === "desc"){
	                				$(this).addClass("sort-down");
	                			}
	                			if(currentOrder === "asc"){
	                				$(this).addClass("sort-up")
	                			}
	                		}
	                	});
	                	lastClickTrend = currentClick;
						lastTrendOrder = currentOrder;
                	}
                });
               $("#showTrend").show();
	});
	if(maxpage>1){
		$("#nextpage").addClass("active");
	};
	//当前单页显示行数
	$("#rowsToshow").change(function(){
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
		pagerows = $("#rowsToshow").val();
		curpage = 1;
		var detailParams = detailChecked("#trendDetailAll","#trendDetailOpen input","#trendDetailTrade input");
		if(detailParams == false)
			return false;
		detailParams = detailParams + "&pageRows="+pagerows+"&curPage="+curpage+"&orderBy="+currentOrderby+"&order="+currentOrder;
		ajaxRequest(
                {url:'${uri.context_path}/auth/fund/trendDetail.htm',
                postMethod:'POST',
                params:params+"&"+detailParams,
                callback:function(data){
                	$('#fundTrendDetail').html(data);
                	$('#showPageLoad').hide();
                	maxpage = Math.ceil($("#total-rows").text()/pagerows);
            		if(maxpage > 1)
            			$("#nextpage").addClass("active");
            		$("#trendDetailThead a").each(function(){
	                		if($(this).attr("tag") === orderby &&  orderby!='stat_dt')
	                		{
	                			if(currentOrder === "desc"){
	                				$(this).addClass("sort-down");
	                			}
	                			if(currentOrder === "asc"){
	                				$(this).addClass("sort-up");
	                			}
	                		}
	                	});
                	}
                
                });
		$("#showPageLoad").show();
	});
	
	var wd =$("#fenxiweidu li.current").index()+1;
	checkClickQuota(wd,"1");
});
	
</script>