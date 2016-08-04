<#import "/util.ftl" as util />
<table class="det-d-qdmx" style="table-layout:fixed ;" id="channelDataTab">
	<thead>
		<tr class="tit">
			<td>渠道名称</td>
			<td head="enter" width="100">进入次数</td>
			<td head="pv" width="100">PV</td>
			<td head="uv" width="100">UV</td>
			<td head="validuv" width="100">有效UV</td>
			<td head="gmuv" width="120">公募基金档案页UV</td>
			<td head="simuuv" width="100">高端详情页UV</td>
			<td head="drkh" width="100">开户人数</td>
			<td head="drbk" width="100">绑卡人数</td>
			<td head="persons" width="100">下单人数</td>
			<td head="bills" width="100">下单笔数</td>
			<td head="amt" width="100">下单金额</td>
			<td head="xdzhl" width="100">下单转化率</td>
			<td head="drxdcjrs" width="100">成交人数</td>
			<td head="drxdcjbs" width="100">成交笔数</td>
			<td head="drxdcjje" width="100">成交金额</td>
			<td head="cjzhl" width="100">成交转化率</td>
		</tr>
	</thead>
	<#if collData??>
	<tr class="n-list">
		<td class="tdl name-2"><a href="#" target="_self" class="n-open" value="n-all">全部</a></td>
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
		</#if>
		</td>
	</tr>
	</#if>
	<#if websiteDataList?? && websiteDataList?size gt 0>
		<#list websiteDataList as wd>
			<tr class="hide n-all" data="data">
				<td class="name-2-1">
				${wd.channelName!}
				</td>
				<td head="enter">${util.formatNum(wd.enter!0)}</td>
				<td head="pv">${util.formatNum(wd.pv!0)}</td>
				<td head="uv">${util.formatNum(wd.uv!0)}</td>
				<td head="validuv">${util.formatNum(wd.validuv!0)}</td>
				<td head="gmuv">${util.formatNum(wd.gmuv!0)}</td>
				<td head="simuuv">${util.formatNum(wd.simuuv!0)}</td>
				<td head="drkh">${util.formatNum(wd.drkh!0)}</td>
				<td head="drbk">${util.formatNum(wd.drbk!0)}</td>
				<td head="persons">${util.formatNum(wd.persons!0)}</td>
				<td head="bills">${util.formatNum(wd.bills!0)}</td>
				<td head="amt">${util.formatNum(wd.amt!0,',##0.00')}</td>
				<td head="xdzhl">
				<#if wd.xdzhl??>
				${util.formatNum(wd.xdzhl*100,',##0.00')}%
				<#else>
				0.00%
				</#if>
				</td>
				<td head="drxdcjrs">${util.formatNum(wd.drxdcjrs!0)}</td>
				<td head="drxdcjbs">${util.formatNum(wd.drxdcjbs!0)}</td>
				<td head="drxdcjje">${util.formatNum(wd.drxdcjje!0,',##0.00')}</td>
				<td head="cjzhl">
				<#if wd.cjzhl??>
				${util.formatNum(wd.cjzhl*100,',##0.00')}%
				<#else>
				0.00%
				</#if></td>
			</tr>
		</#list>
	</#if>
</table>
				
<script>
	$(function(){
		$(".det-datalist table .n-open").click(function(){
		var _n = $(this).attr("value");
		if($(this).hasClass("n-open")){
			$(this).addClass("n-close").removeClass("n-open");
			$(this).parent().parent().siblings("."+_n).show();
		}else{
			$(this).addClass("n-open").removeClass("n-close");
			$(this).parent().parent().siblings("."+_n).hide();
			$(this).parent().parent().nextUntil(".n-list").hide();
			$(this).parent().parent().siblings().find(".name-2 a").addClass("n-open").removeClass("n-close");
		}
		return false;
		});
		
		$("table tr.n-list").not("a").find("td").each(function(i,obj){
			if(!$(obj).hasClass("name-2")){
				$(obj).css({"text-align": "right"});
			}
		});
		$("table tr[data='data']").not("a").find("td[head]").each(function(i,obj){
			if(!$(obj).hasClass("name-2")){
				$(obj).css({"text-align": "right"});
			}
		});
		
		$(".det-datalist table .n-open").click();
	})
</script>