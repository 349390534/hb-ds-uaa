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
	<@uri.script src=["/disabClientGrade_chart.js","/disabClientGrade_data.js"]/>
</head>
<body>

<!-- main begin-->
<div class="main">
	<!-- 分布图/趋势图 begin -->
	<div class="chartbox">
		    <div class="bar3 clearfix">
		        <div class="bar3_left bar3_con">
		            <ul class="clearfix" id="custTypeUl">
		                <li custType="1" class="current">只买货币基金客户</li>
		                <li custType="2">先买货币基金后买股票客户</li>
		                <li custType="3">先买股票客户</li>
		            </ul>
		            <div class="box">
		              <div class="charts" id="cust_chart" style="height:300px;"></div>
		            </div>
		        </div>
		        <div class="bar3_right bar3_con">
		            <ul class="clearfix">
		                <li class="current">客户评分分布</li>
		               <#-- <li>客户评分占比</li>-->
		            </ul>
		            <div class="box">
		              <div class="charts" id="cust_grade_chart" style="height:300px;"></div>
		            </div>		            
		        </div>
		</div>						
	<!-- 分布图/趋势图 end -->
	<!-- 明细 begin -->
	<div class="detail">
		<div class="tab_list">
			<div class="tab_box">
				<div class="con"><!-- 渠道明细 begin -->
					<div class="detail_filter">
					    <div class="zhl3 clearfix">
					        <p class="title">指标：</p>
					        <div class="zhl_right clearfix" id="quato">
							    <div class="ch_left2">
							        <label><input q="0" type="checkbox" class="detail_b" style="margin-right: 5px;  position: relative;top: 2px;" />全选</label>
							    </div>
								<ul class="clearfix">
									<li><label><input q="ttl_cnt" type="checkbox">总数</label></li>
									<li><label><input q="cnt0" type="checkbox">0分客户数</label></li>
									<li><label><input q="cnt1" type="checkbox">1分客户数</label></li>
									<li><label><input q="cnt2" type="checkbox">2分客户数</label></li>
									<li><label><input q="cnt3" type="checkbox">3分客户数</label></li>
									<li><label><input q="cnt4" type="checkbox">4分客户数</label></li>	
									<li><label><input q="cnt5" type="checkbox">5分客户数</label></li>
									<li><label><input q="cnt123" type="checkbox">1-3分客户数</label></li>
									<li><label><input q="pct123" type="checkbox">1-3分客户占比</label></li>
									<li><label><input q="cnt45" type="checkbox">4-5分客户数</label></li>
									<li><label><input q="pct45" type="checkbox">4-5分客户占比</label></li>				
								</ul>
					        </div>
                        </div>
					</div>
		
					<div class="tools">
						<input type="button" value="导出明细" class="btn-style-a" id="export_penetrate">
					</div>
					<p class="data_title">客户成交数据明细</p>
					<table class="g_head">
                        <thead>
                            <tr class="tit">
                                <td width="125">投顾姓名</td>
                                <td width="86" q="ttl_cnt">总数</td>
                                <td width="85" q="cnt0">0分客户数</td>
                                <td width="85" q="cnt1">1分客户数</td>
                                <td width="85" q="cnt2">2分客户数</td>
                                <td width="85" q="cnt3">3分客户数</td>
                                <td width="86" q="cnt4">4分客户数</td>
                                <td width="84" q="cnt5">5分客户数</td>
                                <td width="86" q="cnt123">1-3分客户数</td>
                                <td width="84" q="pct123">1-3分客户占比</td>
                                <td width="86" q="cnt45">4-5分客户数</td>
                                <td width="102" q="pct45">4-5分客户占比</td>
                            </tr>
                        </thead>
					</table>
					<div class="det-datalist" style="height: 400px;margin-top:0;overflow-y: scroll;overflow-x: hidden;">
						<table class="det-d-qdmx2" style="width: 1073px;">
						<#if respListTg ?? && respListTg?size gt 0>
						<#list respListTg as tg>
							<tr>
								<td width="129">${tg.consname!}</td>
								<td width="86" q="ttl_cnt">${tg.ttl_cnt}</td>
								<td width="86" q="cnt0">${tg.cnt0!}</td>
								<td width="86" q="cnt1">${tg.cnt1!}</td>
								<td width="86" q="cnt2">${tg.cnt2!}</td>
								<td width="86" q="cnt3">${tg.cnt3!}</td>
								<td width="86" q="cnt4">${tg.cnt4!}</td>
								<td width="86" q="cnt5">${tg.cnt5!}</td>
								<td width="86" q="cnt123">${tg.cnt123!}</td>
								<td width="86" q="pct123">${tg.pct123!0}%</td>
								<td width="86" q="cnt45">${tg.cnt45!}</td>
								<td width="86" q="pct45">${tg.pct45!0}%</td>
							</tr>
						</#list>
						</#if> 
						</table>
					</div>
				</div><!-- 渠道明细 end -->

			</div>
		</div>						
	</div>
	<!-- 明细 end -->
	
	<#include '../include/disabClientGrade_quota_explain.ftl'>
</div>
<!-- main end-->
 

<script type="text/javascript">
<#--图表数据 -->
var custTypeJsonData = [];
<#if custTypeJsonData??>
	custTypeJsonData=${custTypeJsonData!};
</#if>

var deptJsonData = [];
<#if deptJsonData??>
	deptJsonData=${deptJsonData!};
</#if>
$(function() {
debugger;
	//购买基金客户分类图
	drawCustChart(custTypeJsonData);
	//指标切换事件
	bindToggleTab(custTypeJsonData);
	//客户评分表
	drawCustGradeChart(deptJsonData);
	
	//默认指标全部选中
	checkBox(true);
	
	
	//注册复选框事件
	$("#quato input[type=checkbox]").click(checkBoxClickEvent);
	
	//注册导出事件
	$("#export_penetrate").click(exportPenetrateData);
});
</script>

</body>
</html>