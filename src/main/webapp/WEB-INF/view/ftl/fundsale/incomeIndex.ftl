<#import "/uri.ftl" as uri />
<#import "/util.ftl" as util />
<div class="xl_data">
	<!-- 分布图/趋势图 begin -->
	<div class="chartbox">
		<div class="title">
			<h3>收入：<income id="income">0</income>元 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 成本：<capital id="capital">0</capital>元</h3>
		</div>
		<div class="chu_app clearfix">
			<div class="detail1">
				<div class="yh_select clearfix">
					<p class="fl">基金类型：</p>
				  <ul class="filter_c fl">
						<li>
							<select name="incomeFundTypeSelect" id="incomeFundTypeSelect" class="bar1">
								<option value="-1" selected="selected">全部</option>
							</select>
						</li>
					</ul>
				</div>
		    <div class="charts" id="incomeFundTypeChart"></div>
		</div>
		</div>
		<div class="chartbox_tab_xl" style="margin-top:20px;">
			<div class="bar clearfix">
				<ul class="fl" id="incomeChart2Tab">
					<li tag="INCOME" class="current" style="border-left:none;">收入</li>
					<li tag="CAPITAL" >成本</li>
					<li tag="RAISE_FEE" >认购费收入</li>
					<li tag="SUBS_FEE" >申购费收入</li>
					<li tag="REDE_FEE" >赎回费收入</li>
					<li tag="SVC_AMT" >尾随金额</li>
					<li tag="AGENT_SVC_FEE" >销售服务费收入</li>
					<li tag="SVC_FEE" >尾随收入</li>
					<li tag="OTHER_FEE"  style="width:113px;">额外营销收入</li>				
				</ul>
			</div>
			<div class="box">
				<div class="con clearfix">
					<ul class="filter_e fl">
						<li>
							<select name="incomeFundTypeTopSelect" id="incomeFundTypeTopSelect" class="bar1">
								<option value="">全部 TOP30</option>
								<option ttype="TA_CODE" value="-1">基金公司 TOP30</option>
							</select>
						</li>
					</ul>
					<div class="ll_chart" id="incomeChart2"></div>
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
	
	var fundSumDataJson=[];
	<#if fundSumDataJson??>
		fundSumDataJson=${fundSumDataJson};
	</#if>
	
	var fundTaSumDataJson=[];
	<#if fundTaSumDataJson??>
		fundTaSumDataJson=${fundTaSumDataJson};
	</#if>
	
	//加载基金类型
	loadOverallFundTypeSelect("incomeFundTypeSelect",fundTypeJson);
	
	//画图
	var fundType = $("#incomeFundTypeSelect").val();
	var yAxisTitleArray=["金额","比率"];
	var fundTypeData = loadOverallFundTypeDataObj(fundDataDetailJson,fundType,datex,2);
	var columnDataMap=fundTypeData[0];
	var splineDataMap=fundTypeData[1];
	drawCombinationChart("incomeFundTypeChart", datex, columnDataMap, splineDataMap, yAxisTitleArray);
	//设置收入和成本数据
	setIncomeAndCapital(fundDataDetailJson,"-1");
	//下拉框change事件
	overallFundTypeSelectChangeEvent("incomeFundTypeSelect","incomeFundTypeChart",fundDataDetailJson,datex,2);
	
	//加载图表二基金类型下拉框
	loadSaleDataChart2FundTypeSelect("incomeFundTypeTopSelect",fundTypeJson);
	
	toggleIncomeDataChart2TabEvent(fundSumDataJson,fundTaSumDataJson);
	incomeFundTypeTopSelectChangeEvent(fundSumDataJson,fundTaSumDataJson);
	//画图
	drawIncomeTab2Chart(fundSumDataJson,fundTaSumDataJson);
});
</script>