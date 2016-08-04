<#import "/uri.ftl" as uri />
<#import "/util.ftl" as util />
<div class="xl_data">
	<!-- 分布图/趋势图 begin -->
	<div class="chartbox">
		<div class="chu_app clearfix">
			<div class="detail1">
				<ul class="tab_menu5 clearfix" id="overall_tab">
					<li class="current"><h3>总体情况</h3></li>
					<li><h3>基金类型</h3></li>
				</ul>
				<div class="tab_box5" id="overall_tab_box">
					<!--总体情况-->
					<#include "overallSale.ftl"/>
					<!--基金类型-->
					<#include "fundTypeSale.ftl"/>
				</div>
		</div>
		</div>
		<div class="chartbox_tab_qk" style="margin-top:20px;">
			<div class="bar clearfix">
				<ul class="fl" id="fundSaleQkTab">
					<li tag="MARKET_AMT" class="current" style="border-left:none;">存量金额</li>
					<li tag="APP_AMT">申购金额(下单)</li>
					<li tag="SELL_APP_VOL">赎回份额(下单)</li>
					<li tag="ACK_AMT">申购金额(确认)</li>
					<li tag="SELL_ACK_AMT">赎回金额(确认)</li>
					<li tag="NET_ACK_AMT" style="width:147px">净申购金额(确认)</li>
				</ul>
			</div>
			<div class="box">
				<div class="con clearfix">
					<ul class="filter_e fl">
						<li>
							<select name="saleChart2FundTypeSelect" id="saleChart2FundTypeSelect" class="bar1">
								<option value="-1">全部 TOP30</option>
								<option ttype="TA_CODE" value="-1">基金公司 TOP30</option>
							</select>
						</li>
					</ul>
					<div class="ll_chart" id="saleChart2FundDataChart"></div>
				</div>
			</div>
		</div>						
</div>
<!-- 分布图/趋势图 end -->
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
	var fundDataJson=[];
	<#if fundDataJson??>
		fundDataJson=${fundDataJson};
	</#if>
	var fundSumDataJson=[];
	<#if fundSumDataJson??>
		fundSumDataJson=${fundSumDataJson};
	</#if>
	
	var fundTaSumDataJson=[];
	<#if fundTaSumDataJson??>
		fundTaSumDataJson=${fundTaSumDataJson};
	</#if>
	
	overallTabEvent(fundTypeJson,fundDataDetailJson,datex);
	//加载图表二基金类型下拉框
	loadSaleDataChart2FundTypeSelect("saleChart2FundTypeSelect",fundTypeJson);
	
	toggleSaleDataChart2TabEvent(fundDataJson,fundSumDataJson,fundTaSumDataJson);

	//画图	
	drawChart2Top(fundDataJson,fundSumDataJson,fundTaSumDataJson);
	
	saleChart2FundTypeSelectEvent(fundDataJson,fundSumDataJson,fundTaSumDataJson);
});

</script>
