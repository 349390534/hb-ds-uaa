<#import "/console.ftl" as con />
<#import "/util.ftl" as util />
<#if theadlist??>
<table class="det-d-qsmx" style="table-layout:fixed ;" id="custtrendDetailTable">

	<thead id="custtrendDetailThead">
		<tr class="tit">

			<td class="wdqd"><a href="javascript:void(0)" target="self" class="sort" tag = "stat_dt" style="color:white;">时间</a></td>
			<td head="ljkhs" width="100"><a href="javascript:void(0)" target="self" class="sort" tag = "ljkhs">总客户数</a></td>
			<td head="ljbks" width="100"><a href="javascript:void(0)" target="self" class="sort" tag = "ljbks"> 总绑卡人数</a></td>
			<td head="ljjqs" width="100"><a href="javascript:void(0)" target="self" class="sort" tag = "ljjqs"> 总鉴权人数</a></td>
			<td head="ljscjys" width="120"><a href="javascript:void(0)" target="self" class="sort" tag = "ljscjys"> 首次交易总人数</a></td>
			<td head="ljyks" width="100"><a href="javascript:void(0)" target="self" class="sort" tag = "ljyks"> 总验卡人数</a></td>
			<td head="cys" width="100"><a href="javascript:void(0)" target="self" class="sort" tag = "cys"> 总持有人数</a></td>
			<td head="ljzjql" width="100"><a href="javascript:void(0)" target="self" class="sort" tag = "ljzjql"> 总鉴权率</a></td>
			<td head="ljzjyl" width="100"><a href="javascript:void(0)" target="self" class="sort" tag = "ljzjyl"> 总交易率</a></td>
			<td head="ljzjqjyl" width="100"><a href="javascript:void(0)" target="self" class="sort" tag = "ljzjqjyl"> 总鉴权交易率</a></td>
			<td head="ljzykl" width="100"><a href="javascript:void(0)" target="self" class="sort" tag = "ljzykl"> 总验卡率</a></td>
		</tr>
	    <#--<#if coll?? && coll?size gt 0 >
		<tr>
		    <td>全部</td>
		    <#list coll as co>
		    	<td head="ljkhs" style="text-align:right;">${util.formatNum(co.ljzkhs!0)}</td>
				<td head="ljbks" style="text-align:right;">${util.formatNum(co.ljzbks!0)}</td>
				<td head="ljyks" style="text-align:right;">${util.formatNum(co.ljzyks!0)}</td>
				<td head="ljjqs" style="text-align:right;">${util.formatNum(co.ljzjqs!0)}</td>
				<td head="ljscjys" style="text-align:right;">${util.formatNum(co.ljscjyzs!0)}</td>
				<td head="cys" style="text-align:right;">${util.formatNum(co.zcys!0)}</td>
				<td head="ljykl" style="text-align:right;">
					<#if co.ljzykl??>
						${util.formatNum(co.ljzykl*100,',##0.00')}%
					<#else>
						0.00%
					</#if>
				</td>
				<td head="ljjql"style="text-align:right;">
					<#if co.ljzjql??>
						${util.formatNum(co.ljzjql*100,',##0.00')}%
					<#else>
						0.00%
					</#if>
				</td>
				<td head="ljjyl"style="text-align:right;">
					<#if co.ljzjyl??>
						${util.formatNum(co.ljzjyl*100,',##0.00')}%
					<#else>
						0.00%
					</#if>
				</td>
				<td head="ljjqjyl"style="text-align:right;">
					<#if co.ljzjqjyl??>
						${util.formatNum(co.ljzjqjyl*100,',##0.00')}%
					<#else>
						0.00%
					</#if>
				</td>
		    </#list>
		</tr>
	    </#if>-->
	</thead>
	
	<tbody id="custTrendDetailTbody">
		<#include "custTrendDetailTbody.ftl">
	</tbody>														
</table>
<div class="det-page" style="width:1100px;">
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
	$("#custtrendDetailThead a").each(function(){
		if($(this).attr("tag")!='stat_dt'){
			$(this).addClass("sort-up");
		}
	});
	
	//趋势明细排序 
	$("#custtrendDetailThead a").click(function(){
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
		var detailParams = detailChecked("#custtrendDetailAll","#custtrendDetailOpen input");
		if(detailParams == false)
			return false;
		detailParams = detailParams + "&pageRows="+pagerows+"&curPage=1&orderBy="+orderby+"&order="+currentOrder;
		ajaxRequest(
                {
                url:context_path+'/auth/customerData/custtrendDetail.htm',
                postMethod:'POST',
                params:params+"&"+detailParams,
                session_out:false,
                callback:function(data){
	                	$('#custTrendDetail').html(data);
	                	showTrendTableContent();
	                	$("#showTrend").hide();
	                	maxpage = Math.ceil($("#total-rows").text()/pagerows);
	            		if(maxpage > 1)
	            			$("#nextpage").addClass("active");
	            		$("#custtrendDetailThead a").each(function(){
	                		if($(this).attr("tag") === orderby)
	                		{
	                			if(currentOrder === "desc"){
	                				$(this).addClass("sort-down");
	                			}
	                			if(currentOrder === "asc"){
	                				$(this).addClass("sort-up");
	                			}
	                		}
	                	});
	                	lastClickTrend = currentClick;
						lastTrendOrder = currentOrder;
                	}
                });
               $("#showTrend").show();
	});
	pageEvent();
});
	
</script>