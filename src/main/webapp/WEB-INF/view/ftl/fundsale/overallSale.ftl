<#import "/uri.ftl" as uri />
<#import "/util.ftl" as util />
<div class="gaikuang_con" tag="overall">
	<div class="yh_select clearfix">
	  <p class="fl">指标：</p>
	  <ul class="filter_c fl">
			<li>
				<select name="overall_select" id="overall_select" class="bar1">
					<option value="MARKET_AMT" selected="selected">存量金额</option>
					<option value="APP_AMT">申购金额(下单)</option>
					<option value="SELL_APP_VOL">赎回份额(下单)</option>
					<option value="ACK_AMT">申购金额(确认)</option>
					<option value="SELL_ACK_AMT">赎回金额(确认)</option>
					<option value="NET_ACK_AMT">净申购金额(确认)</option>
				</select>
			</li>
		</ul>
	</div>
	<div class="charts" id="overall_chart"></div>
</div>

<script type="text/javascript">

$(function(){
	
	var fundTypeJson = [];
	<#if fundTypeAll??>
		fundTypeJson=${fundTypeAll};
	</#if>
	var fundDataDetailJson=[];
	<#if fundDataDetailJson??>
		fundDataDetailJson=${fundDataDetailJson};
	</#if>
	var datex = [];
	<#if datex??>
		datex=${datex!};
	</#if>
	var target="MARKET_AMT";//默认
	
	drawChartFundSaleDataOfOverall("overall_chart",fundTypeJson,fundDataDetailJson,target,datex);
	//下拉框事件注册
	overallSelectEvent(fundTypeJson,fundDataDetailJson,datex);
});

</script>