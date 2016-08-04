<div class="gaikuang_con hide" tag="fundType">
	<div class="yh_select clearfix">
	  <p class="fl">指标：</p>
	  <ul class="filter_c fl">
			<li>
				<select name="overallFundTypeSelect" id="overallFundTypeSelect" class="bar1">
					<option value="-1" selected="selected">全部</option>
				</select>
			</li>
		</ul>
	</div>
	<div class="charts" id="fundType_chart"></div>
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
	//加载下拉框基金类型
	loadOverallFundTypeSelect("overallFundTypeSelect",fundTypeJson);
	
	overallFundTypeSelectChangeEvent("overallFundTypeSelect","fundType_chart",fundDataDetailJson,datex,1);
});

</script>