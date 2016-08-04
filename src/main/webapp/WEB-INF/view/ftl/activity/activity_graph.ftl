<#import "/uri.ftl" as uri />
<#import "/util.ftl" as util />
<#import "/host.ftl" as host />
<div class="title">
	<h5>活动流量统计-推广渠道    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;    数据截止时间：<time id="time">${time!}</time></h5>
</div>
<div class="chartbox_tab_ll">
	<div class="bar clearfix">
		<ul class="fl" id="quota_ul">
			<#if collData??>
			<li class="current" quota="enter">进入次数<p>${util.formatNum(collData.enter!0)}</p></li>
			<li quota="pv">总PV<p>${util.formatNum(collData.pv!0)}</p></li>
			<li quota="uv">总UV<p>${util.formatNum(collData.uv!0)}</p></li>
			<li quota="hongbaoIndexUv">活动首页UV<p>${util.formatNum(collData.hongbaoIndexUv!0)}</p></li>
			<li quota="h5OpenAccIndexPageUv">H5开户首页<p>${util.formatNum(collData.h5OpenAccIndexPageUv!0)}</p></li>
			<li quota="authPageUv">身份验证页UV<p>${util.formatNum(collData.authPageUv!0)}</p></li>
			<li quota="h5OpenAccResultPageUv">H5开户结果页UV<p>${util.formatNum(collData.h5OpenAccResultPageUv!0)}</p></li>					
			<li quota="openaccNum">开户人数<p>${util.formatNum(collData.openaccNum!0)}</p></li>
			</#if>
		</ul>
	</div>
	<div class="box">
		<div class="con clearfix">
			<div class="ll_chart1 fl" id="pie_chart"></div>
			<div class="ll_chart2 fr" id="line_chart"></div>
		</div>
		<#-- 
		<a id="show_datadeatail" href="javascript:void(0)" class="ckmx">查看数据明细</a>
		<div class="sjmx hide">
            <p>数据明细 <img src="${host.image_host}/close-btn.png" alt="" class="close_btn"></p>
            <table class="sjmx_tab">
	            <tr>
	                <td width="17%">时间</td>
	                <td width="8%">进入次数</td>
	                <td width="8%">总PV</td>
	                <td width="8%">总UV</td>
	                <td width="12%">活动首页UV</td>
	                <td width="12%">H5开户首页UV</td>
	                <td width="12%">身份验证页UV</td>
	                <td width="15%">H5开户结果页UV</td>
	                <td width="8%">开户人数</td>
	            </tr>
             </table>
            <div class="sjmx_tbl">
                <table style="width: 735px;">
                <#if dataDetailList?? && dataDetailList?size gt 0>
                	<#list dataDetailList as d>
		            <tr>
		                <td width="17%">${d.statdt?datetime('yyyy-MM-dd HH:mm:ss')}</td>
		                <td width="8%">${util.formatNum(d.enter!0)}</td>
		                <td width="8%">${util.formatNum(d.pv!0)}</td>
		                <td width="8%">${util.formatNum(d.uv!0)}</td>
		                <td width="12%">${util.formatNum(d.hongbaoIndexUv!0)}</td>
		                <td width="12%">${util.formatNum(d.h5OpenAccIndexPageUv!0)}</td>
		                <td width="12%">${util.formatNum(d.authPageUv!0)}</td>
		                <td width="15%">${util.formatNum(d.h5OpenAccResultPageUv!0)}</td>
		                <td width="8%">${util.formatNum(d.openaccNum!0)}</td>
		            </tr>
                	</#list>
                </#if>
	        	</table>
            </div>
            <input id="export_data" value="导出明细" class="btn-style-a dcmx" type="button">
	    </div>
		-->
		
		
	</div>
</div>

<script type="text/javascript">
var _dataJson_coll=[];
<#if jsonDataChannel??>
_dataJson_coll = ${jsonDataChannel!};
</#if>
var _dataJson=[];
<#if jsonData??>
_dataJson = ${jsonData!};
</#if>
var _dataX24Json=null;
<#if dataX24??>
	_dataX24Json=${dataX24!};
</#if>

var _dataX24JsonComp=null;
<#if dataX24Compare??>
	_dataX24JsonComp=${dataX24Compare!};
</#if>

var jsonDataCompare = null;
<#if jsonDataCompare??>
	jsonDataCompare =${jsonDataCompare!};
</#if>
var isOneDay  = true;
<#if isOneDay??>
	isOneDay = ${isOneDay!};
</#if>
var fromdate = '';
<#if fromdate??>
	fromdate = "${fromdate!}";
</#if>
var datex = [];
<#if datax??>
	datax = ${datax!};
</#if>
$(function(){
	 //指标切换事件
	 if(isOneDay==1){
	 	eventTogTag(_dataJson_coll,_dataJson,jsonDataCompare);
	 }else{
	 	eventTogTagDay(_dataJson,quota,fromdate,datax);
	 }
	 var quota = "enter";
	 var channelType = $("#fwqd li.current").attr("channel_type");
	 var queryRouteThree = $("#queryRouteThree:visible option:selected");
	 var drawP = true;
	 if(queryRouteThree.length>=1){
	 	var tv = queryRouteThree.val();
	 	if(tv && tv!="-1"){
	 		drawP= false;
	 	}
	 }else if("1"==channelType){
	 	drawP= false;
	 }else if($(".ssyq-box ul#ssyq li.current[tag_code!='']").is(":visible")){
	 	drawP= false;
	 }else if($(".other-qd-box select#other_channel").val()!=""){
	 	drawP= false;
	 }else{
	 	drawP= true;
	 }
	 if(drawP){//直接访问不显示饼图,最子层渠道不展示
	 	$("#pie_chart").css('display','inline');
		drawPie(_dataJson_coll,quota);
	 }else{
	 	$("#pie_chart").css('display','none');
	 	$("#line_chart").css({"float":"left","margin-left":"12%"});
	 }
	
	 if(isOneDay==1){
		 drawLineChartActivity(_dataJson,jsonDataCompare,quota);
	 }else{
	 	drawLineChartActivityByDay(_dataJson,quota,fromdate,datax);
	 }
	 bindMxEvent();
	 bindDownloadAt();
})
</script>