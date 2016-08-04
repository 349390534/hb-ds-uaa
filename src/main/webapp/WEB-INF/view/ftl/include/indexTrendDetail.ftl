<#import "/uri.ftl" as uri />
<#import "/util.ftl" as util />
<#import "/console.ftl" as console />
		<table class="det-d-qdmx" style="table-layout:fixed ;" id="trendDataTab">
			<thead>
			<tr class="tit">
				<td style="width: 50px;"></td>
				<td head="dt" style="width: 100px;"><a href="javascript:void(0);" target="_self" class="sort-down"></a>日期</td>
				<td head="enter"><a href="javascript:void(0);" target="_self" class="sort-down">进入次数</a></td>
				<td head="pv"><a href="javascript:void(0);" target="_self" class="sort-down">PV</a></td>
				<td head="uv"><a href="javascript:void(0);" target="_self" class="sort-down">UV</a></td>
				<td head="validuv"><a href="javascript:void(0);" target="_self" class="sort-down">有效UV</a></td>
				<td head="gmuv"><a href="javascript:void(0);" target="_self" class="sort-down">公募基金档案页UV</a></td>
				<td head="simuuv"><a href="javascript:void(0);" target="_self" class="sort-down">高端详情页UV</a></td>
				<td head="drkh"><a href="javascript:void(0);" target="_self" class="sort-down">开户人数</a></td>
				<td head="drbk"><a href="javascript:void(0);" target="_self" class="sort-down">绑卡人数</a></td>
				<td head="persons"><a href="javascript:void(0);" target="_self" class="sort-down">下单人数</a></td>
				<td head="bills"><a href="javascript:void(0);" target="_self" class="sort-down">下单笔数</a></td>
				<td head="amt"><a href="javascript:void(0);" target="_self" class="sort-down">下单金额</a></td>
				<td head="xdzhl"><a href="javascript:void(0);" target="_self" class="sort-down">下单转化率</a></td>
				<td head="drxdcjrs"><a href="javascript:void(0);" target="_self" class="sort-down">成交人数</a></td>
				<td head="drxdcjbs"><a href="javascript:void(0);" target="_self" class="sort-down">成交笔数</a></td>
				<td head="drxdcjje"><a href="javascript:void(0);" target="_self" class="sort-down">成交金额</a></td>
				<td head="cjzhl"><a href="javascript:void(0);" target="_self" class="sort-down">成交转化率</a></td>
			</tr>
			</thead>
			<#if collData??>
			<tr class="bottom">
				<td>全部</td>
				<td></td>
				<td head="enter">${util.formatNum(collData.enter!0)}</td>
				<td head="pv">${util.formatNum(collData.pv!0)}</td>
				<td head="uv">${util.formatNum(collData.uv!0)}</td>
				<td head="validuv">${util.formatNum(collData.validuv!0)}</td>
				<td head="gmuv">${util.formatNum(collData.gmuv!0)}</td>
				<td head="simuuv">${util.formatNum(collData.simuuv!0)}</td>
				<td head="drkh">${util.formatNum(collData.drkh!0)}</td>
				<td head="drbk">${util.formatNum(collData.drbk!0)}</td>
				<td head="persons">${util.formatNum(collData.persons!0)}</td>
				<td head="bills">${util.formatNum(collData.bills!0)}</td>
				<td head="amt">${util.formatNum(collData.amt!0,',##0.00')}</td>
				<td head="xdzhl">
				<#if collData.xdzhl??>
				${util.formatNum(collData.xdzhl*100,',##0.00')}%
				<#else>
				0.00%
				</#if>
				</td>
				<td head="drxdcjrs">${util.formatNum(collData.drxdcjrs!0)}</td>
				<td head="drxdcjbs">${util.formatNum(collData.drxdcjbs!0)}</td>
				<td head="drxdcjje">${util.formatNum(collData.drxdcjje!0,',##0.00')}</td>
				<td head="cjzhl">
				<#if collData.cjzhl??>
				${util.formatNum(collData.cjzhl*100,',##0.00')}%
				<#else>
				0.00%
				</#if></td>
			</tr>
			</#if>
			
			<tbody id="tableshow">
				<#include "indexTrendDetailTable.ftl">
			</tbody>
			
		</table>
		<div class="det-page">
			<div class="bar1 fl">共<span id="total-rows"><#if total??>${total}</#if></span>项，每页显示
			<select name="" id="rowsToshow">
				<option value="10">10</option>
				<option value="20">20</option>
				<option value="50">50</option>
			</select></div>
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
					
<script>
	
$(function(){
		<#if rows??>
			$("#rowsToshow").val(${rows});
		</#if>
		
	//排序操作
	$("#trendDataTab thead a").on("click", function(){
		if($(this).hasClass("sort-down")){
			$(this).removeClass("sort-down").addClass("sort-up");
			order = "asc";
		}
		else{
			if($(this).hasClass("sort-up")){
				$(this).removeClass("sort-up").addClass("sort-down");
				order = "desc";
			}
			else{
				$(this).addClass("sort-down");
				order = "desc";
			}
		}
		curpage = 1;
		orderby = $(this).parent().attr("head");
		pageshow(curpage,maxpage,function(data){
			$("#pagecontrol").html(data);
		});
		
		var formParam = formdata();
		formParam+="&pageRows=" + pagerows + "&curPage="
		+ curpage + "&orderBy=" + orderby
		+ "&order=" + order;
		var reqObj = {
			url : context_path + "/" + _path + '/trendDetailTbody.htm',
			postMethod : 'POST',
			params : formParam,
			callback : function(data) {
				$('#tableshow').html(data);
			}
		};
		ajaxRequest(reqObj);
	});
	if(maxpage > 1){
		$("#nextpage").addClass("active");
	}
	
	
	//当前显示行数
	$("#rowsToshow").change(function(){
		pagerows = $("#rowsToshow").val();
		var formParam = formdata();
		formParam+="&pageRows=" + pagerows + "&curPage="
		+ curpage + "&orderBy=" + orderby
		+ "&order=" + order;
		curpage = 1;
		var reqObj = {
			url : context_path + "/" + _path + '/trendDetail.htm',
			postMethod : 'POST',
			params : formParam,
			callback : function(data) {
				$('#TrendDetail').html(data);
				maxpage = Math.ceil($("#total-rows").text()
						/ pagerows);
				if (maxpage > 1)
					$("#nextpage").addClass("active");
			}
		};
		ajaxRequest(reqObj);
		
	});
	
		
});
	
	
</script>