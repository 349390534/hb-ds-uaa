<#import "/util.ftl" as util />
<div class="title">
		<div class="time fr">${beginDate!}　至    ${endDate!}</div>
		<h3 id="fwqd_exp">全部访问渠道 </h3>
	</div>
	<div class="chartbox_tab">
		<div class="bar clearfix">
			<ul class="fl">
				<li class="current" tag="enter">进入次数<p><#if collData?? && collData.enter??>${util.formatNum(collData.enter!0)}</#if></p></li>
				<li tag="pv">PV<p><#if collData?? && collData.uv??>${util.formatNum(collData.pv!0)}</#if></p></li>
				<li tag="uv">UV<p><#if collData?? && collData.uv??>${util.formatNum(collData.uv!0)}</#if></p></li>
				<li tag="validuv">有效UV<p><#if collData?? && collData.validuv??>${util.formatNum(collData.validuv!0)}</#if></p></li>
				<li tag="gmuv">公募基金档案页UV<p><#if collData?? && collData.gmuv??>${util.formatNum(collData.gmuv!0)}</#if></p></li>
				<#--<li tag="simuuv">高端详情页UV<p><#if collData?? && collData.simuuv??>${util.formatNum(collData.simuuv!0)}</#if></p></li>
				<li tag="drkh">开户人数<p><#if collData?? && collData.drkh??>${util.formatNum(collData.drkh!0)}</#if></p></li>-->
				<li class="other fl">
					其他指标：<br />
					<select name="other" id="other">
						<option value="">请选择其他指标</option>
						<option value="simuuv">高端详情页UV</option>
						<option value="drkh">开户人数</option>
						<option value="drxdcjje">成交金额</option>
						<option value="drxdcjrs">成交人数</option>
						<option value="drbk">绑卡人数</option>
						<option value="persons">下单人数</option>
						<option value="bills">下单笔数</option>
						<option value="amt">下单金额</option>
						<option value="xdzhl">下单转化率</option>
						<option value="drxdcjbs">成交笔数</option>
						<option value="cjzhl">成交转化率</option>
					</select>
				</li>
			</ul>
		</div>
		<div class="box">
			<div class="con">
				<div id="container1"></div>
				<div id="container2"></div>
			</div>
		</div>
	</div>			
<script type="text/javascript">

var _data_all = null;
<#if dataJson ??>
	_data_all = ${dataJson!};
</#if>
var collJson = null;
<#if collJson ??>
	collJson = ${collJson!};
</#if>

var _data_all_sum = null;
<#if sumListJson ??>
	_data_all_sum = ${sumListJson!};
</#if>


$(function(){
	var xlen = 0;
	<#if dateList??>
		xlen=${dateList?size};
	</#if>

	//分布图/趋势图 tab切换
	$(".chartbox_tab ul li:first-child").addClass("current");


	$(".chartbox_tab ul").find("li[tag]").click(function(){
		$(".chartbox_tab ul").find("li.current").removeClass("current");
		$(this).addClass("current");
		var quotatype=$(this).attr("tag");
		var channelTypeLi = $("ul#fwqd li.current:eq(0)");
		var channelType = channelTypeLi.attr("channel_type");
		var dateFrom = $("#beginDate").val();
		loadChartHB(_data_all,collJson,quotatype,channelType,dateFrom,xlen); 
		$(".chartbox_tab select#other").val("");
	});
	
	$(".chartbox_tab select#other").change(function(){
		$(".chartbox_tab ul").find("li.current").removeClass("current");
		$(this).parent().addClass("current");
		
		var quotatype = $(this).val();
		if(""==quotatype){
			$(".chartbox_tab ul").find("li[tag]:eq(0)").click();
		}else{
			var channelTypeLi = $("ul#fwqd li.current:eq(0)");
			var channelType = channelTypeLi.attr("channel_type");
			var dateFrom = $("#beginDate").val();
			loadChartHB(_data_all,collJson,quotatype,channelType,dateFrom,xlen); 
		}
	});
	
	var quotatype="enter";
	var channelTypeLi = $("ul#fwqd li.current:eq(0)");
	var channelType = channelTypeLi.attr("channel_type");
	var dateFrom = $("#beginDate").val();
	loadChartHB(_data_all,collJson,quotatype,channelType,dateFrom,xlen);
	//加载查询条件
	loadFwqdExp();
	
});
</script>
