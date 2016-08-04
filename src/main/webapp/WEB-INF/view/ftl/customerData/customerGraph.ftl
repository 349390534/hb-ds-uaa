<#import "/util.ftl" as util />
<div class="title" align="left">
	<div class="time fr" style="font-size: 16px;font-weight: normal;"><#if startTime??>${startTime?date('yyyyMMdd')?string('yyyy-MM-dd')}</#if>　至　<#if endTime??>${endTime?date('yyyyMMdd')?string('yyyy-MM-dd')}</#if></div>
	<h3 id="custCondition_exp">开户渠道 - 全部机构 </h3>
</div>
<input type="hidden" value="1" name="hide">
<div class="chartbox_tab" id="graph" style="min-width:1147px;">
	<div class="bar clearfix">
		<ul class="fl" style="width:1130px">
			<li class="current" quotatype="ljkhs">    总客户数<#if (summary.ljzkhs)??><p>    ${util.formatNum(summary["ljzkhs"])}</p></#if></li>
			<li quotatype="ljbks">    总绑卡人数<#if (summary.ljzbks)??><p>    ${util.formatNum(summary["ljzbks"])}</p></#if></li>
			<li quotatype="ljjqs">    总鉴权人数<#if (summary.ljzjqs)??><p>    ${util.formatNum(summary["ljzjqs"])}</p></#if></li>
			<li quotatype="ljscjys">    首次交易总人数<#if (summary.ljscjyzs)??><p>    ${util.formatNum(summary["ljscjyzs"])}</p></#if></li>
			<li quotatype="cys">    总持有人数<#if (summary.zcys)??><p>${util.formatNum(summary["zcys"])}</p></#if></li>	
			<li quotatype="ljykl" style="display:none;">    总验卡率<#if (summary.ljzykl)??><p>    ${util.formatNum(summary["ljzykl"],",##0.00%")}</p></#if></li>
			<li quotatype="ljjql" id="ljjql" style="display:none;">    总鉴权率<#if (summary.ljzjql)??><p>    ${util.formatNum(summary["ljzjql"],",##0.00%")}</p></#if></li>	
			<li quotatype="ljjyl" id="ljjyl" style="display:none;">    总交易率<#if (summary.ljzjyl)??><p>    ${util.formatNum(summary["ljzjyl"],",##0.00%")}</p></#if></li>	
			<li class="other fl" quota_tag="quotatype" >    其他指标：<p>
				<select name="select_quotatype" id="select_quotatype" class="quota_select">
					<option value="">请选择其他指标</option>
					<option value="ljjql" id="ljjql" >总鉴权率</option>	
					<option value="ljjyl" id="ljjyl" >总交易率</option>	
					<option value="ljjqjyl" id="ljjqjyl" >总鉴权交易率</option>					
					<option value="ljyks" id="ljyks" >总验卡人数</option>	
					<option value="ljykl" id="ljykl" >总验卡率</option>	
				</select>
				</p>
			</li>
		</ul>
	</div>
	<div class="box">
         <div class="con">
             <div class="ljzkhs-radio" style="margin-top:20px;margin-bottom:20px;	margin-left: -200px;display:none;"  id="radio_div">
                
             </div>
             <div id = "container1" style=""></div>
             <div id = "container2" style="width:580px;"></div>
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

var _data_all_one = null;
<#if dataJsonOne ??>
	_data_all_one = ${dataJsonOne!};
</#if>

var _data_all_sum = null;
<#if dataJsonSum ??>
	_data_all_sum = ${dataJsonSum!};
</#if>

/**
*加载图表 
*@param gid 
*@param quotatype
**/
function loadChart(_json_data,_coll_data,gid,fundType,quotatype,codeType){
	;
	if(null!=_json_data){
		//判断指标分类	
		var qt = judgeQuotaType(quotatype);
		var sum = $.trim($("div.chartbox_tab ul.fl").find("li[quotatype].current:eq(0)").find("p").html());
		if(-1==qt || sum =="0" || sum=="0.00"){
			//不正确的指标、总记录数为0的指标
			$("#container1").hide();
			$("#container2").hide();
			$("#radio_div").hide();
			return;
		}
		var wd ='${wd!}';
		//显示列code
		var _pro_codeTag =codeTagMap.get(gid);
		//显示列名称
		var _pro_nameTag =nameTagMap.get(gid);
		//数据列名称
		//默认显示当日开户人数
		var dataTag=quotatype||"ljkhs";
		var dChart = getDistributionChart(dataTag);
		
		if(null!=dChart){
			var drawD = true;
			var point = 5;//切分点
			if(gid<=1 && fundType=="-1"){//无细分渠道 + 全部基金
				if(1==qt||3==qt){//算术加和指标（非交易）、非算术加和指标（非交易类）不画分布图		
					drawD=false;
				}
			}else if(gid<=1 && fundType!="-1"){//无细分渠道 + 选择基金类型
				if(1==qt || 2==qt ||4==qt){//所有指标无分布图(除了3:非算术加和指标（非交易类）)
					drawD=false;
				}
			}else if(gid>1 && fundType!="-1"){//有细分渠道 + 选择基金类型
				if(1==qt){//所有指标无分布图
					drawD=false;
				}else{
					//var isSpe = isSpecial(wd,dataTag);
					//if(isSpe){
						//if(codeType==1){
							//dChart="pie";
						//}
					//}
				}
			}else{
				//有细分渠道+全部基金类型
				var wd ='${wd!}';
				//var isSpe = isSpecial(wd,dataTag);
				//if(isSpe){
					//if(codeType==1){
						//dChart="pie";
					//}
				//}
			}
			if(drawD){
				$("#container1").show();
				$("#container2").css({"float":"left","margin-left":"100px"});
				//画分布图
				var dataColTag = quota_kmap.get(dataTag)||'ljzkhs';
				if("pie" == dChart){
					var pieData = loadPieChartData(_coll_data,_pro_codeTag,_pro_nameTag,dataColTag);
					if((gid==3 || gid ==2) && pieData.length>point && codeType!=0){
						var sortData = sortPieData(pieData,0);
						pieData = filterPieData(sortData,point);
					}
					
					HbCharts.loadPieChart({
						targetId: $("#container1"),  //元素
						data: pieData  //数据
					});
				
				}else if("col" == dChart){
					var columnCategories = loadColumnCategories(_coll_data,_pro_codeTag,dataColTag);
					var columnData = loadColumnChartData(_coll_data,_pro_codeTag,_pro_nameTag,dataColTag);
					if((gid==3 || gid ==2) && columnData.length>point && codeType!=0){
						var sortData = sortColData(columnData,0);
						columnData = filterColData(sortData,point);
					}
					var valueSuffix = "";
					var decimalFloat = 0;
					if(qt==3){
						valueSuffix="%";
						decimalFloat = 2;
					}
					//if(isContains(dataTag,_float_quota_array)){
						//decimalFloat = 2;
					//}
					HbCharts.loadColumnChart({
						targetId: $("#container1"),  //元素
						dataX: ['所有渠道'],  //x轴数据
						dataY: columnData,  //y轴数据
						oneDataWidth: 10,  //一条数据柱体宽度
						pointWidth: 20,  //柱体宽度
						verticalAlign:"bottom",
						valueSuffix:valueSuffix,
						decimalFloat:decimalFloat
					});
				}
			}else{
				$("#container1").hide();
				$("#container2").css({"margin-left":"25%"});
			}
		}
		
		
		//画折线图begin
		var lineChart = getLineChart(dataTag);
		if(null != lineChart && "line" == lineChart){
			var drawLine = true;
			var noc = false;//是否取下级渠道数据
			//根据细分渠道和基金类型判断是否画图
			if(gid<=1){
				if(fundType!="-1"){//无细分渠道 + 选择基金类型
					if(1==qt){//算术加和指标（非交易）
						drawLine = false;
					}else if(qt==3){//非算术加和指标（交易类）
						noc = true;//显示该渠道折线图
					}
				}else{//无细分渠道 + 全部基金类型
					if(1==qt||qt==3){//算术加和指标（非交易）、非算术加和指标（非交易类）
						noc = true;//显示该渠道折线图
					}
				}
			}else if(gid>1 && fundType!="-1"){//有细分渠道 + 选择基金类型
				if(1==qt){//所有指标无分布图
					drawLine=false;
				}
			}
			
			if(drawLine){
				$("#container2").show();
				var dateFrom = null;
				//趋势图X轴
				<#if datelist??>
				<#assign last = datelist?size - 1>
				dateFrom = '${datelist[0]}';
				var datax = [
					<#list datelist as date>
						<#if date_index != last>
							"${date}",
						<#else>
							"${date}"
						</#if>
					</#list>
				];
				</#if>
			
				var title = titleMap.get(dataTag);
				var dataY = [];
				
				if(noc){
					var code = codeTagMap.get(gid+2);
					var name = nameTagMap.get(gid+2);
					dataY = loadLineChartDataY(_data_all_one,code,name,dataTag,dateFrom,datax);
				}else{
					dataY = loadLineChartDataY(_json_data,_pro_codeTag,_pro_nameTag,dataTag,dateFrom,datax);
				}
				if((gid==3 || gid ==2) && dataY.length>point && codeType!=0){
					var sortData = sortLineData(dataY,0);
					dataY = filterLineData(sortData,point,datax.length,dChart,dateFrom);
				}
				
				if(null!=_data_all_sum){
					//添加一条汇总数据
					var dataYsum = loadLineChartDataY(_data_all_sum,"","全部",dataTag,dateFrom,datax);
					if(dataYsum.length>=1){
						//dataY.push(dataYsum[0]);// 添加到最后
						dataY.unshift(dataYsum[0]);// 添加到第一个位置
					}
				}
				/*
				HbCharts.loadLineChart({
					targetId: $("#container2"),  //元素
					dataX: datax,  //x轴数据
					dataY: dataY,  //y轴数据
					yAxisTitleText: title,
					yAxisTitleAlign: 'high',
					yAxisTitleOffset: -60,
					yAxisTitleRotation: 0,
					yAxisTitleY: -1
				});*/
				var attrMap = new HashMap();
				if(qt==3){
					attrMap.put("valueSuffix","%");
				}
				loadLineChart($("#container2"),dataY,attrMap);
				
				}else{
					$("#container2").hide();
				}
			}
		//画折线end
	}
}

function getQuotatype(){
	var quotatype = null;
	//获取当前选择指标
	var licur = $("div.chartbox_tab ul.fl").find("li[quotatype].current:eq(0)");
	var length = licur.length;
	if(length==0){
		var sq = $("select[name='select_quotatype']");
		var sqv = sq.val();
		if(sqv!="" && null!=sqv){
			quotatype = sqv;
		}
	}else{
	 	quotatype = licur.attr("quotatype");
	}
	return quotatype;
}


$(function(){
	var gid =${gid!31};
	var fundTypeStat ='${fundTypeStat!1}';
	var fundType ='${fundType!-1}';
	//默认加载
	var firstQuota="ljkhs";
	if(gid==0 && _data_all ==null){
		_data_all=_data_all_one;
	}
	loadChart(_data_all,collJson,gid,fundType,firstQuota,1);
	//showFundTypeStat(gid,fundType);
	
	var checkedFundTypeStat = "1";
	//图表tab切换
	$("div.chartbox_tab ul.fl").find("li[quotatype]").click(function(){
		var licur = $("div.chartbox_tab ul.fl").find("li[quotatype].current:eq(0)");
		var quotatype = licur.attr("quotatype");
		var quotatypeThis = $(this).attr("quotatype");
		if(quotatypeThis != quotatype){//点击当前选中的不处理
			$(this).addClass("current").siblings("li").removeClass("current");
			var select_quota =$("div.chartbox_tab ul.fl").find("li[quota_tag]:eq(0)");
			if(select_quota.hasClass("current")){
				select_quota.removeClass("current");
			}
			select_quota.find("select[name='select_quotatype']:eq(0)").val("");
			//加载数据表
			loadChart(_data_all,collJson,gid,fundType,quotatypeThis,1);
		}
	});
	
	//其他指标的切换
	$("select[name='select_quotatype']").change(function(){
	
		var quotatype = $(this).val();
		if(""==quotatype){
			//默认显示第一个指标
			$("div.chartbox_tab ul.fl").find("li[quotatype]:visible:first").click();
			return;
		}
		var quota_tag_div = $("div.chartbox_tab ul.fl").find("li[quota_tag]:eq(0)");
		quota_tag_div.addClass("current");
		var licur = $("div.chartbox_tab ul.fl").find("li[quotatype].current:eq(0)");
		licur.removeClass("current");
		//加载数据表
		loadChart(_data_all,collJson,gid,fundType,quotatype,1);
	});
	loadFundCondition();
	//默认显示第一个指标
	$("div.chartbox_tab ul.fl").find("li[quotatype]:visible:first").click();
	
	
});
</script>