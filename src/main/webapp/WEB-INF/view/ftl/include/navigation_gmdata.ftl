<#import "/uri.ftl" as uri />
<#import "/util.ftl" as util />

<div class="rchart_left" id="gmdata_view"  permission_id="000112">
	<div class="rchart_top clearfix">
		<p class="date fl">日期：${lastDay!}</p>
		<p class="btnbox fr"><input type="radio" name="renshu" value="drkh" checked="checked">昨日开户人数&nbsp;&nbsp;&nbsp;<input type="radio"  name="renshu" value="drxzjyrs">昨日新增交易人数</p>
	</div>
	<div class="tbbox fl">
		<table>
			<tr>
				<td>开户人数</td>
				<td>日环比</td>
			</tr>
			<tr>
				<td><span class="cBold ft22">${util.formatNum(summary.drkh!)}</span></td>
				<td><span class="cBold <#if drkhV?? && drkhV gt 0>cRed<#else>cGreen</#if> ft18">${util.formatNum(drkhV!,',##0.00%')}</span></td>
			</tr>
			<tr>
				<td>新增交易人数</td>
				<td>日环比</td>
			</tr>
			<tr>
				<td><span class="cBold ft22">${util.formatNum(summary.drxzjyrs!)}</span></td>
				<td><span class="cBold <#if xzjyV?? && xzjyV gt 0>cRed<#else>cGreen</#if> ft18">${util.formatNum(xzjyV!,',##0.00%')}</span></td>
			</tr>
		</table>
	</div>
	<div class="chartmx fr" id="gmdata_chart">1</div>
</div>
<a class="btn_detail fl" href="javascript:void(0);" url="${uri.context_path}/auth/fund/index.htm" permission_id ="0001" pageid="000112">查看公募统计明细</a>
<div class="rchart_right" permission_id ="000411">
	<p class="date">日期：${lastDay!}</p>
	<div class="all_num">
		<table>
			<tr>
				<td></td>
				<td>人数</td>
				<td>日环比</td>
			</tr>
			<tr>
				<td>公募总客户数</td>
				<td><span class="cBold">${util.formatNum(summary.ljkhs!)}</span></td>
				<td><span class="cBold <#if ljkhsV?? && ljkhsV gt 0>cRed<#else>cGreen</#if> ft16">${util.formatNum(ljkhsV!,',##0.00%')}</span></td>
			</tr>
			<tr>
				<td>公募总交易数</td>
				<td><span class="cBold">${util.formatNum(summary.ljscjys!)}</span></td>
				<td><span class="cBold <#if ljscjysV?? && ljscjysV gt 0>cRed<#else>cGreen</#if> ft16">${util.formatNum(ljscjysV!,',##0.00%')}</span></td>
			</tr>
			<tr>
				<td>公募总持有数</td>
				<td><span class="cBold">${util.formatNum(summary.cys!)}</span></td>
				<td><span class="cBold <#if cysV?? && cysV gt 0>cRed<#else>cGreen</#if> ft16">${util.formatNum(cysV!,',##0.00%')}</span></td>
			</tr>
		</table>
	</div>
	<a class="btn-style-b gmkh fr" url="${uri.context_path}/auth/customerData/index.htm" href="javascript:void(0);" 
	permission_id ="0004" pageid="000411">查看公募总客户明细</a><#--总客户数-->
</div>
<script type="text/javascript">
var _jsonData = null;
var _datexJson = null;
<#if jsonData??>
	_jsonData = ${jsonData};
</#if> 
<#if datexJson??>
	_datexJson = ${datexJson};
</#if>
var _perListJson = [];
<#if Session.allPerList??>
	_perListJson=${Session.allPerList!};
</#if>
$(function(){
	//公募数据判断权限
 	var hasGm = containsPer(_perListJson,"0001") && containsPer(_perListJson,"000112");
 	if(!hasGm){
 		$("[permission_id='000112']").detach();
 		$("[pageid='000112']").detach();
 		$("[permission_id='000411']").css("float","left");
 		
 	}else{
	 	var datay = loadGmDataY(_jsonData,"drkh");
		drawGmChart('#gmdata_chart',_datexJson,datay);
		radioClick();
 	}
 	
 	var hasGmAll=containsPer(_perListJson,"0004")&&containsPer(_perListJson,"000411");
 	if(!hasGmAll){
 		$("[permission_id='000411']").detach();
 	}
	bindClick("a[pageid='000112']");
	bindClick("a[pageid='000411']");
	
});
</script> 