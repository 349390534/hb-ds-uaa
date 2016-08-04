<#import "/util.ftl" as util />
<div class="title" align="left">
	<div class="time fr" style="font-size: 16px;font-weight: normal;"><#if startTime??>${startTime?date('yyyyMMdd')?string('yyyy-MM-dd')}</#if>　至　<#if endTime??>${endTime?date('yyyyMMdd')?string('yyyy-MM-dd')}</#if></div>
	<h3 id="fundCondition_exp">开户渠道 - 全部机构 - 全部基金类型</h3>
</div>
<#if wd?? && wd == '1'>
<input type="hidden" value="1" name="hide">
<div class="chartbox_tab" id="graph">
	<div class="bar clearfix">
		<ul class="fl">
			<li class="current" quotatype="drkh">    开户人数<#if (summary.zkh)??><p>    ${util.formatNum(summary["zkh"])}</p></#if></li>
			<li quotatype="drscjq">    鉴权人数<#if (summary.sczjq)??><p>    ${util.formatNum(summary["sczjq"])}</p></#if></li>
			<li quotatype="drkhdrjyrs">    当日开户当日交易人数<#if (summary.drkhdrjyzrs)??><p>    ${util.formatNum(summary["drkhdrjyzrs"])}</p></#if></li>
			<li quotatype="drxzjyrs">    当日新增交易人数<#if (summary.xzjyzrs)??><p>    ${util.formatNum(summary["xzjyzrs"])}</p></#if></li>
			<li quotatype="drxdje">    下单金额<#if (summary.xdzje)??><p>    ${util.formatNum(summary["xdzje"],",##0.00")}</p></#if></li>
			<li quotatype="drzfje">    支付金额<#if (summary.zzfje)??><p>${util.formatNum(summary["zzfje"],",##0.00")}</p></#if></li>	
			<#--<li quotatype="drxdrs">    下单人数<#if (summary.xdzrs)??><p>${util.formatNum(summary["xdzrs"])}</p></#if></li>-->	
			<#--<li quotatype="drqrjycjrs" id="drqrjycjrs" >    成交人数<#if (summary.qrjycjzrs)??><p>    ${util.formatNum(summary["qrjycjzrs"])}</p></#if></li>	-->	
			<#--<li quotatype="drqrjycjje" id="drqrjycjje" style="display:none ">    成交金额<#if (summary.qrjycjzje)??><p>    ${util.formatNum(summary["qrjycjzje"],",##0.00")}</p></#if></li>	-->	
			<li class="other fl" quota_tag="quotatype">    其他指标：<p>
				<select name="select_quotatype" id="select_quotatype" class="quota_select">
					<option value="">请选择其他指标</option>
					<option value="drxdrs">下单人数</option>					
					<option value="drzfrs">支付人数</option>					
					<option value="drxdbs">下单笔数</option>
					<option value="drzfbs">支付笔数</option>
					<option value="drqrjycjrs">成交人数</option>					
					<option value="drqrjycjje">成交金额</option>
					<option value="drscyk">验卡人数</option>
					<option value="drscbk" >绑卡人数</option>
					<option value="drqrjycjbs">成交笔数</option>
					<option value="rjxdje">人均下单金额</option>
					<option value="rjzfje">人均支付金额</option>
					<option value="rjcjje">人均成交金额</option>
					<option value="drkhdrbk">当日开户绑卡人数</option>
					<option value="drkhdryk">当日开户验卡人数</option>
					<option value="drkhdrjq">当日开户鉴权人数</option>
					<option value="drkhbkl">当日开户绑卡率</option>
					<option value="drkhykl">当日开户验卡率</option>
					<option value="drkhjql">当日开户鉴权率</option>
					<option value="drkhdrjyje">当日开户当日交易金额</option>
					<option value="drkhjyl">当日开户交易率</option>
					<option value="drxzjyje">当日新增交易金额</option>
				</select>
				</p>
			</li>
		</ul>
	</div>
	<div class="box">
         <div class="con">
             <div class="drkh-radio" style="margin-top:20px;margin-bottom:20px;	margin-left: -200px;display:none;"  id="radio_div">
                 <label><input type="radio" name="fundTypeStat" id="radio_channel" value="1"/>按渠道明细</label>
                 <label><input type="radio" name="fundTypeStat" id="radio_fund" value="0"/>按基金类型</label>
             </div>
             <div id = "container1" style=""></div>
             <div id = "container2" style=""></div>
         </div>
    </div>
</div>
<#elseif wd?? && wd=='2'>
<input type="hidden" value="2" name="hide">
<div class="chartbox_tab" id="graph">
	<div class="bar clearfix">
		<ul class="fl">
			<li class="current" quotatype="drxdje">    下单金额<#if summary?? && summary.xdzje??><p>    ${util.formatNum(summary["xdzje"],",##0.00")}</p></#if></li>
			<li quotatype="drzfje">    支付金额<#if summary?? && summary.zzfje??><p>    ${util.formatNum(summary["zzfje"],",##0.00")}</p></#if></li>
			<li quotatype="drxdrs">    下单人数<#if summary?? && summary.xdzrs??><p>    ${util.formatNum(summary["xdzrs"])}</p></#if></li>
			<li quotatype="drzfrs">    支付人数<#if summary?? && summary.zzfrs??><p>    ${util.formatNum(summary["zzfrs"])}</p></#if></li>
			<li quotatype="drqrjycjrs">    成交人数<#if summary?? && summary.qrjycjzrs??><p>${util.formatNum(summary["qrjycjzrs"])}</p></#if></li>
			<li quotatype="drqrjycjje">   成交金额<#if summary?? && summary.qrjycjzje??><p>    ${util.formatNum(summary["qrjycjzje"],',##0.00')}</p></#if></li>
			
			<#--<li quotatype="drxdbs">    下单笔数<#if summary?? && summary.xdzbs??><p>    ${util.formatNum(summary["xdzbs"])}</p></#if></li>-->
			<#--<li quotatype="rjxdje">    人均下单金额<#if summary?? && summary.rjxdzje??><p>    ${util.formatNum(summary["rjxdzje"],',##0.00')}</p></#if></li>-->
			<#--<li quotatype="xdzhl">    下单转化率<#if summary?? && summary.xdzzhl??><p>    ${util.formatNum(summary["xdzzhl"],',##0.00')}</p></#if></li>	-->
			<#--<li quotatype="drqrjycjbs" id="drjycjbs">    成交笔数<#if summary?? && summary.qrjycjzbs??><p>${util.formatNum(summary["qrjycjzbs"])}</p></#if></li>-->
			<li class="other fl" quota_tag="quotatype">其他指标：<p>
				<select name="select_quotatype" id="select_quotatype" class="quota_select">
					<option value="">请选择其他指标</option>
					<option value="drxdbs">下单笔数</option>
					<option value="drzfbs">支付笔数</option>
					<option value="rjxdje">人均下单金额</option>
					<option value="rjzfje">人均支付金额</option>
					<option value="rjcjje">人均成交金额</option>
					<option value="drqrjycjbs">成交笔数</option>
					<option value="drxzjyrs">当日新增交易人数</option>
					<option value="drxzjyje">当日新增交易金额</option>
				</select>
				</p>
			</li>
		</ul>
	</div>
	<div class="box">
         <div class="con">
             <div class="drkh-radio" style="margin-top:20px;margin-bottom:20px;	margin-left: -200px;display:none;" id="radio_div">
                 <label><input type="radio" name="fundTypeStat" id="radio_channel" value="1"/>按渠道明细</label>
                 <label><input type="radio" name="fundTypeStat" id="radio_fund" value="0"/>按基金类型</label>
             </div>
             <div id = "container1" ></div>
             <div id = "container2"></div>
         </div>
    </div>
</div>
</#if>
<script type="text/javascript">

var _data_all = null;
<#if dataJson ??>
	_data_all = ${dataJson!};
</#if>
var collJson = null;
<#if collJson ??>
	collJson = ${collJson!};
</#if>

var _data_all_fundType = null;
<#if dataJsonFundType ??>
	_data_all_fundType = ${dataJsonFundType!};
</#if>
var collJson_fundType = null;
<#if collJsonFundType ??>
	collJson_fundType = ${collJsonFundType!};
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
		//按照基金类型显示
		if(codeType==0){
			_pro_codeTag="fundtype";
			_pro_nameTag="fundtypename";
		}
		//数据列名称
		//默认显示当日开户人数
		var dataTag=quotatype||"drkh";
		
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
					var isSpe = isSpecial(wd,dataTag);
					if(isSpe){
						if(codeType==1){
							dChart="pie";
						}
					}
				}
			}else{
				//有细分渠道+全部基金类型
				var wd ='${wd!}';
				var isSpe = isSpecial(wd,dataTag);
				if(isSpe){
					if(codeType==1){
						dChart="pie";
					}
				}
			}
			if(drawD){
				$("#container1").show();
				$("#container2").css({"float":"left","margin-left":"100px"});
				//画分布图
				var dataColTag = quota_kmap.get(dataTag)||'zkh';
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
					if(isContains(dataTag,_float_quota_array)){
						decimalFloat = 2;
					}
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

/**
*显示渠道明显和基金类型单选钮
**/
function showFundTypeStat(gid,fundType){
	if(!typeof (gid) ==="number"){
		gid = parseInt(gid);
	}
	var quotatype = getQuotatype();
	if(null==quotatype || ""==quotatype){
		return;
	}
	//判断指标的算术类型
	var qt = judgeQuotaType(quotatype);
	if(-1==qt){
		//不正确的指标
		return;
	}
	/*
	1：算术加和指标（非交易）
 	2：算术加和指标（交易指标）
 	3：非算术加和指标（非交易类）
 	4：非算术加和指标（交易类）*/
	if(gid>1){//有细分渠道
		if("-1"==fundType){//全部基金
			if(qt==1 || qt==3){
				$("#radio_div").hide();
			}else if(qt==2 || qt==4){
				$("#radio_div").show();
			}
		}else{
			//选择基金类型
			if(qt==1||qt==2 || qt==3 || qt==4){
				$("#radio_div").hide();
			}
		}
	}else if(gid<=1){//无细分渠道
		if("-1"==fundType){//全部基金
			if(qt==1||qt==2 || qt==3|| qt==4){
				$("#radio_div").hide();
			}
		}else {
			//选择基金类型
			if(qt==1||qt==2 || qt==3|| qt==4){
				$("#radio_div").hide();
			}
		}
	}
}


$(function(){
	var gid =${gid!31};
	var fundTypeStat ='${fundTypeStat!1}';
	$("input[type=radio][name='fundTypeStat'][value="+fundTypeStat+"]:eq(0)").prop("checked",true);
	var fundType ='${fundType!-1}';
	//默认加载当日开户
	var firstQuota="drkh";
	<#if wd?? && wd == '2'>
	//交易维度
	firstQuota="drxdrs";
	</#if>
	if(gid==0 && _data_all ==null){
		_data_all=_data_all_one;
	}
	loadChart(_data_all,collJson,gid,fundType,firstQuota,1);
	showFundTypeStat(gid,fundType);
	
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
			//判断是否显示渠道明细和基金明细单选钮 
			showFundTypeStat(gid,fundType);
			$("input[type=radio][name='fundTypeStat'][value="+fundTypeStat+"]:eq(0)").prop("checked",true);
			checkedFundTypeStat="1";
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
		//判断是否显示渠道明显和基金明显单选钮
		showFundTypeStat(gid,fundType);
		$("input[type=radio][name='fundTypeStat'][value="+fundTypeStat+"]:eq(0)").prop("checked",true);
		checkedFundTypeStat="1";
	});
	
	$("input[type=radio][name='fundTypeStat']").click(function(){
		
		//var chV = $("input[type=radio][name='fundTypeStat']:checked:eq(0)").val();
		var v =  $(this).val();
		if(checkedFundTypeStat == v){
			return;
		}
		checkedFundTypeStat = v;
		var quotatype = getQuotatype();
		if(null==quotatype || ""==quotatype){
			return;
		}
		var fundTypeStatVal = this.value;
		//“0”：汇总  “1”：不汇总
		if(fundTypeStatVal=='0'){//
			//基金类型汇总
			loadChart(_data_all_fundType,collJson_fundType,gid,fundType,quotatype,0);
		}else {
			loadChart(_data_all,collJson,gid,fundType,quotatype,1);
		}
	});
	
	loadFundCondition();
	loadTab();
	
	//选择基金类型时，开户指标不显示
	showNotOpenAccQuoat('${wd}',fundType,_open_acc_quoat_array);
	//默认显示第一个指标
	$("div.chartbox_tab ul.fl").find("li[quotatype]:visible:first").click();
	
	
});
function loadTab(){
	var weidu = $("input[name='hide']").val();
	var totalWidth = $("#gmGraph .fl").outerWidth();
	var selectWidth = $("#gmGraph .fl li[quota_tag]").outerWidth();
	var countWidth = 0;
	var cnwidth = 0;
	var inpEleSe = "li[quotatype]";
	$("#gmGraph .fl").find(inpEleSe).each(function(){
		countWidth = countWidth + $(this).outerWidth();
	});
	cnwidth = countWidth;
	countWidth = countWidth + selectWidth;
	if(countWidth>totalWidth){
		//开户渠道
		if(weidu == 1){
			$("#drqrjycjrs").hide();
			$("#optioncjrs").show();
		}else if(weidu== 2){
			$("#drqrjycjje").hide();
			$("#optioncjje").show();
			
			var drjycjbsWidth = $("#drjycjbs").outerWidth();
			var drqrjycjjeWidth = $("#drqrjycjje").outerWidth();
			if(((cnwidth-drqrjycjjeWidth)+selectWidth)>totalWidth){
				$("#drjycjbs").hide();
				$("#optioncjbs").show();
			}
		}
	}
}
</script>