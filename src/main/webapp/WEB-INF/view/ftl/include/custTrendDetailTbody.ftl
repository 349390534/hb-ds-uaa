<#import "/console.ftl" as con />
<#import "/util.ftl" as util />
<#import "/uri.ftl" as uri />
<#if datalist??>
	<#list datalist as data>
	<tr>
		<td><#if data.statdt??>${data.statdt?date('yyyyMMdd')}</#if></td>
			<td head="ljkhs" style="text-align:right;">${util.formatNum(data.ljkhs!0)}</td>
				<td head="ljbks"style="text-align:right;">${util.formatNum(data.ljbks!0)}</td>
				<td head="ljjqs"style="text-align:right;">${util.formatNum(data.ljjqs!0)}</td>
				<td head="ljscjys"style="text-align:right;">${util.formatNum(data.ljscjys!0)}</td>
				<td head="ljyks"style="text-align:right;">${util.formatNum(data.ljyks!0)}</td>
				<td head="cys"style="text-align:right;">${util.formatNum(data.cys!0)}</td>
				<td head="ljzjql"style="text-align:right;">
					<#if data.ljjql??>
						${util.formatNum(data.ljjql*100,',##0.00')}%
					<#else>
						0.00%
					</#if>
				</td>
				<td head="ljzjyl"style="text-align:right;">
					<#if data.ljjyl??>
						${util.formatNum(data.ljjyl*100,',##0.00')}%
					<#else>
						0.00%
					</#if>
				</td>
				<td head="ljzjqjyl" style="text-align:right;">
					<#if data.ljjqjyl??>
						${util.formatNum(data.ljjqjyl*100,',##0.00')}%
					<#else>
						0.00%
					</#if>
				</td>
				<td head="ljzykl"style="text-align:right;">
					<#if data.ljykl??>
						${util.formatNum(data.ljykl*100,',##0.00')}%
					<#else>
						0.00%
					</#if>
				</td>
	</tr>
	</#list>
<#else>
<tr>
	<td></td>
	<td colspan="${theadlist?size}" align="left">
		暂无数据
	</td>
</tr>
<script>	
$(function(){
	$("#custtrendDetailThead td").each(function(){
		
		var head = $(this).attr("head");
		var display = $(this).attr("display");
		if("none" == display){
			$("#custTrendDetailTbody").find("td[head='"+head+"']").hide();
		}
	});
});
	
</script>
</#if>
