//--------------------画图js-------------------------

var codeTagMap  = new HashMap();
codeTagMap.put("-1","channelName");//全部
codeTagMap.put("1","channelName");//直接访问
codeTagMap.put("3","channelName");//搜索引擎
codeTagMap.put("4","channelName");//推广渠道
codeTagMap.put("2","channelName");//其他渠道


//1-算术加和指标（非交易指标）:进入次数,PV,当日开户人数,当日绑卡人数
var quota_can_plused_no_trade_hb=["enter","pv","drkh","drbk"];

//2-算术加和（交易指标）:当日下单笔数,当日下单金额,成交笔数,成交金额
var quota_can_plused_trade_hb=["bills","amt","drxdcjbs","drxdcjje"];

//3-非算术加和指标（非交易类）:UV,有效UV,下单转化率,成交转化率,公募基金档案页UV,高端详情页UV
var quota_nocan_plused_no_trade_hb=["uv","validuv","xdzhl","cjzhl","gmuv","simuuv"];

//4-非算术加和指标（交易类）:当日下单人数,成交人数
var quota_nocan_plused_trade_hb=["persons","drxdcjrs"];

//需要展示百分比的指标
var quota_need_pluse_fwqd=["xdzhl","cjzhl"];
/**
* 判断指标类型,返回对应的指标类型
* @param quota
* @returns
* 1：算术加和指标（非交易）
* 2：算术加和指标（交易指标）
* 3：非算术加和指标（非交易类）
* 4：非算术加和指标（交易类）
* -1:不正确的指标
*/
function judgeQuotaTypeHb(quota){
	for(var i=0;i<quota_can_plused_no_trade_hb.length;i++){
		var q = quota_can_plused_no_trade_hb[i];
		if(q==quota){
			return 1;
		}
	}
	for(var i=0;i<quota_can_plused_trade_hb.length;i++){
		var q = quota_can_plused_trade_hb[i];
		if(q==quota){
			return 2;
		}
	}
	for(var i=0;i<quota_nocan_plused_no_trade_hb.length;i++){
		var q = quota_nocan_plused_no_trade_hb[i];
		if(q==quota){
			return 3;
		}
	}
	for(var i=0;i<quota_nocan_plused_trade_hb.length;i++){
		var q = quota_nocan_plused_trade_hb[i];
		if(q==quota){
			return 4;
		}
	}
	return -1;
}


var chart_map_hb =new HashMap();
//-------分布图----------
//饼图指标集合
var _quota_pie_Array_hb = [];
_quota_pie_Array_hb.push(quota_can_plused_no_trade_hb);
_quota_pie_Array_hb.push(quota_can_plused_trade_hb);
chart_map_hb.put("pie",_quota_pie_Array_hb);

//柱状图指标集合
var _quota_col_Array_hb = [];
_quota_col_Array_hb.push(quota_nocan_plused_no_trade_hb);
_quota_col_Array_hb.push(quota_nocan_plused_trade_hb);
chart_map_hb.put("col",_quota_col_Array_hb);

//-------趋势图----------
var _quota_line_Array_hb = [];
//走势图指标集合
_quota_line_Array_hb.push(quota_can_plused_no_trade_hb);
_quota_line_Array_hb.push(quota_can_plused_trade_hb);
_quota_line_Array_hb.push(quota_nocan_plused_no_trade_hb);
_quota_line_Array_hb.push(quota_nocan_plused_trade_hb);
chart_map_hb.put("line",_quota_line_Array_hb);

var distributionChart_key_hb = new HashMap();
distributionChart_key_hb.put("pie","pie");
distributionChart_key_hb.put("col","col");

var lineChart_key_hb = new HashMap();
lineChart_key_hb.put("line","line");


/**
 * 获取分布图的类型,不包含的指标，返回null
 * @param _quota 指标名称
 * @returns pie-饼图,col-柱状图
 */

function getDistributionChartHB(_quota){
	return getchartHB(distributionChart_key_hb,_quota);
}

/**
 * 获取走势图的类型,不包含的指标，返回null
 * @param _quota 指标名称
 * @returns line
 */
function getLineChartHB(_quota){
	return getchartHB(lineChart_key_hb,_quota);
}


function getchartHB(chart_key,_quota){
	var sets=this.chart_map_hb.keySets();
	for(var i=0;i<sets.length;i++){
		var key = sets[i];
		if(null!=chart_key.get(key)){
			var qa =chart_map_hb.get(key);
			if(null!=qa && qa.length>=1){
				for(var j=0;j<qa.length;j++){
					var qar = qa[j];
					if(null!=qar && qar.length>=1){
						for(var m=0;m<qar.length;m++){
							var qq = qar[m];
							if(_quota == qq){
								return key;
							}
						}
					}
				}
			}
		}
	}
	return null;
}


function loadChartHB(_json_data,_coll_data,quotatype,channelType,dateFrom,xlen){

	if(null!=_json_data){
		//判断指标分类	
		var qt = judgeQuotaTypeHb(quotatype);
		if(-1==qt){
			//不正确的指标
			return;
		}
		
		//显示列code
		var _pro_codeTag =codeTagMap.get(channelType);
		
		//数据列名称
		//默认显示当日开户人数
		var dataTag=quotatype||"enter";
		var point = 5;
		var dChart = null;
		if(isShowLeft()){
			$("#container1").show();
			dChart = getDistributionChartHB(dataTag);
			if(null!=dChart){
				$("#container1").show();
				//画分布图
				if("pie" == dChart){
					var pieData = loadPieChartData(_coll_data,_pro_codeTag,_pro_codeTag,quotatype);
					if(pieData.length>point){
						var sortData = sortPieData(pieData,0);
						pieData = filterPieData(sortData,point);
					}
					HbCharts.loadPieChart({
						targetId: $("#container1"),  //元素
						data: pieData  //数据
					});
					
				}else if("col" == dChart){
					//var columnCategories = loadColumnCategories(_coll_data,_pro_codeTag,quotatype);
					var columnData = loadColumnChartData(_coll_data,_pro_codeTag,_pro_codeTag,quotatype);
					var valueSuffix = "";
					var decimalFloat = 0;
					if(isContains(dataTag,quota_need_pluse_fwqd)){
						decimalFloat = 2;
						valueSuffix="%";
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
				
			}
		}else{
			$("#container1").hide();
			$("#container2").width("80%");
		}
		
		
		//画折线图begin
		var lineChart = getLineChartHB(dataTag);
		if(null != lineChart && "line" == lineChart){
			$("#container2").width("525px;");
			$("container2").show();
			var dataY = [];
			dataY = loadLineChartDataY(_json_data,_pro_codeTag,_pro_codeTag,dataTag,dateFrom,[]);
			if(dataY.length>point){
				var sortData = sortLineData(dataY,0);
				dataY = filterLineData(sortData,point,xlen,dChart,dateFrom);
			}
			if(null!=_data_all_sum && "-1"!=channelType){
				if(dataY.length>=2){//超过2条加汇总
					//添加一条汇总数据
					var dataYsum = loadLineChartDataY(_data_all_sum,"","全部",dataTag,dateFrom,[]);
					if(dataYsum.length>=1){
						//dataY.push(dataYsum[0]);// 添加到最后
						dataY.unshift(dataYsum[0]);// 添加到第一个位置
					}
				}
			}
			
			var attrMap = new HashMap();
			if(isContains(quotatype, quota_need_pluse_fwqd)){
				attrMap.put("valueSuffix","%");
			}
			loadLineChart($("#container2"),dataY,attrMap);
			
		
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


