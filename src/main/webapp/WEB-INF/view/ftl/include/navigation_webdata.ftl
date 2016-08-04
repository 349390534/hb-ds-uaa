<#import "/uri.ftl" as uri />
<#import "/util.ftl" as util />
<p class="date">日期：${lastDay!}</p>
<div class="n_detail_bg">
	<table>
		<tr>
			<td>UV数</td>
			<td><span class="cBold ft22">${util.formatNum(dataColl.uv!)}</span></td>
		</tr>
		<tr>
			<td>日环比</td>
			<td><span class="cBold <#if uvV?? && uvV gt 0>cRed<#else>cGreen</#if> ft18">${util.formatNum(uvV!,',##0.00%')}</span></td>
		</tr>
	</table>
	<div class="chart_mx" id="website_chart" >2</div>
</div>
<a href="javascript:void(0);" class="btn-style-b btn" permission_id="0003" pageid="000311" url="${uri.context_path}/auth/howbuywebsite/index.htm">查看网站访问明细</a>
<script type="text/javascript">
var _jsonData = null;
var _datexJson = null;
<#if jsonData??>
	_jsonData = ${jsonData};
</#if> 
<#if datexJson??>
	_datexJson = ${datexJson};
</#if>
$(function(){
	var datay = loadGmDataY(_jsonData,"uv");
	drawGmChart('#website_chart',_datexJson,datay);
	bindClick("a[pageid='000311']");
});
</script> 