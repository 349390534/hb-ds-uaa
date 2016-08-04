/**
 * 基金销量主js
 */


//注册分析维度事件
var wdclickEvent=function(){
	$(".tab_menu4 li[wd]").click(function(){
		var  _obj = $(this);
		if(_obj.hasClass("current"))return;
		$(".tab_menu4 li").removeClass("current");
		_obj.addClass("current");
	});
};
wdclickEvent();

var formatdata = function(){
	var paramArray=new Array();
	var start_date = $("#start_date").val();
	paramArray.push("start_date="+start_date.replace(/-/g,""));
	var end_date =$("#end_date").val();
	paramArray.push("end_date="+end_date.replace(/-/g,""));
	
	var wd = getwd();
	paramArray.push("wd="+wd);
	return paramArray.join("&");
	
};

function getwd(){
	var wdtype = $(".tab_menu4 li[wd].current").attr("wd");
	return wdtype;
}
//提交查询事件
var submitClickEvent=function(contextPath){
	$("#submit").click(function(){
		var url =contextPath + "/auth/fundsale/graph.htm";
		var param=formatdata();
		var reqObj = {
				url: url,
	            postMethod:'POST',
	            params:param,
	            callback:function(data){
	               	$("#maindata").html(data);
	               	//加载趋势明细
	               	setTimeout("loadFundTypeData('"+contextPath+"')",2000);//延迟加载
	               	setTimeout("loadFundDetailData('"+contextPath+"')",4000);//延迟加载
	               	
	               	setTrendAndFundDivTableHead();
	               	$("#submit").removeAttr("disabled");
	            }
	        };
		ajaxRequest(reqObj);
		$("#maindata").html(loadImg2(contextPath));
		$("#submit").attr("disabled","disabled");
	});
};
/**
 * 总体情况下拉框选择事件
 * **/
var overallSelectEvent=function(fundTypeJson,fundDataDetailJson,datex){
	$("#overall_select").change(function(){
		var  _this = $(this);
		var pro = _this.val();
		drawChartFundSaleDataOfOverall("overall_chart",fundTypeJson,fundDataDetailJson,pro,datex);
	});	
};

/**
 * 销量数据下总体情况、基金类型tab切换事件
 * */
var overallTabEventType = "overall";
var overallTabEvent=function(fundTypeJson,fundDataDetailJson,datex){
	$("#overall_tab li").click(function(){
		//显示tab，
		var _this = $(this);
		if(_this.hasClass("current"))return;
		$("#overall_tab li[class=current]").removeClass("current");
		_this.addClass("current");
		//隐藏tab，和图表数据
		$("#overall_tab_box div.gaikuang_con").each(function(i,obj){
			var  _thisDiv = $(obj);
			var dtag = _thisDiv.attr("tag");
			if(_thisDiv.hasClass("hide")){
				_thisDiv.removeClass("hide");
				if("fundType"==dtag){
					//画基金类型数据
					var fundType=$("#overallFundTypeSelect option:selected").val();
//					var fundType="0";//默认股票型
					var yAxisTitleArray=["比率","金额"];
					var fundTypeData = loadOverallFundTypeDataObj(fundDataDetailJson,fundType,datex,1);
					var columnDataMap=fundTypeData[0];
					var splineDataMap=fundTypeData[1];
					drawCombinationChart("fundType_chart",datex, columnDataMap, splineDataMap, yAxisTitleArray);
				}else if("overall"==dtag){
					//画总体情况数据
					var target=$("#overall_select option:selected").val();
					drawChartFundSaleDataOfOverall("overall_chart",fundTypeJson,fundDataDetailJson,target,datex);
				}
			}else{
				//隐藏图表
				_thisDiv.addClass("hide");
			}
		});
	});
};

/**
 * 加载销量数据基金类型下拉框全部基金类型
 * **/
var loadOverallFundTypeSelect=function(e,fundTypeJson){
	var select = $("#"+e);
	if(fundTypeJson){
		for(var i in fundTypeJson){
			var ft=fundTypeJson[i];
			var type = ft.FUND_TYPE;
			var name = ft.FUND_TYPE_NAME;
			if("b"==type||"c"==type)continue;
			select.append("<option value='"+type+"'>"+name+"</option>");
		}
	}
};

var  overallFundTypeSelectChangeEvent=function(se,chart,fundDataDetailJson,datex,wdtype){
	$("#"+se).change(function(){
		var _this = $(this);
		var fundType = _this.val();
		var yAxisTitleArray=["比率","金额"];
		var fundTypeData = loadOverallFundTypeDataObj(fundDataDetailJson,fundType,datex,wdtype);
		var columnDataMap=fundTypeData[0];
		var splineDataMap=fundTypeData[1];
		drawCombinationChart(chart, datex, columnDataMap, splineDataMap, yAxisTitleArray);
		//设置收入值
		setIncomeAndCapital(fundDataDetailJson, fundType);
	});
};
/**
 * 加载销量数据图表二的下拉框基金类型数据
 * **/
var loadSaleDataChart2FundTypeSelect=function(e,fundTypeJson){
	var select = $("#"+e);
	if(fundTypeJson){
		for(var i in fundTypeJson){
			var ft=fundTypeJson[i];
			var type = ft.FUND_TYPE;
			if("a"==type||"b"==type||"c"==type)continue;//过滤储蓄罐
			var name = ft.FUND_TYPE_NAME+" TOP"+topNum;
			select.append("<option ftype='FUND_TYPE' value='"+type+"'>"+name+"</option>");
		}
	}
};

/**
 *销量数据图表二tab切换事件
 ***/
var toggleSaleDataChart2TabEvent=function(fundDataJson,fundDataSumedJson,fundTaSumDataJson){
	$("#fundSaleQkTab li").click(function(){
		
		//显示tab，
		var _this = $(this);
		if(_this.hasClass("current"))return;
		$("#fundSaleQkTab li[class=current]").removeClass("current");
		_this.addClass("current");
		drawChart2Top(fundDataJson,fundDataSumedJson,fundTaSumDataJson);
	});
};

function getDataByAttr(data,attr,attrValue){
	var findData = data;
	if(data){
		findData = new Array();
		for(var i=0;i<data.length;i++){
			var dataObj = data[i];
			var attrvalueVar = dataObj[attr];
			if(attrValue==attrvalueVar)
				findData.push(dataObj);
		}
	}
	return findData;
}


var FUND_NAME_ATTR = "FUND_NAME";
var TA_NAME_ATTR = "TA_NAME";
var MARKET_AMT_ATTR = "MARKET_AMT";
var TA_CODE_ATTR = "TA_CODE";
var sortedDataJson = null;
var sortedTaDataJson = null;
var currentTag = null;
/**画图表2top图*/
var drawChart2Top=function(fundDataJson,fundDataSumedJson,fundTaSumDataJson){
	var localJsonData = null;
	var _dataList = [];
	if(fundDataJson){
		//按照指标大小排序
		var valueLable =$("#fundSaleQkTab li.current").attr("tag");
		currentTag = valueLable;
		var name_attr = FUND_NAME_ATTR;
		var filterType = $("#saleChart2FundTypeSelect option:selected").attr("ftype");
		var taType = $("#saleChart2FundTypeSelect option:selected").attr("ttype");
		if(taType &&  TA_CODE_ATTR==taType){
			//基金公司汇总数据
			localJsonData = fundTaSumDataJson;
			name_attr =TA_NAME_ATTR;
		}else{
			if(MARKET_AMT_ATTR==valueLable){
				//存量金额取最近一天的数据排序
				var lastDay = $("#end_date").val();
				lastDay = lastDay.replace(/-/g,"");
				localJsonData = getDataByAttr(fundDataJson.slice(), TRADE_DT_ATTR, lastDay);
			}else{
				//从已经汇总的数据中排序
				localJsonData = fundDataSumedJson;
			}
		}
		_dataList=sortTop(localJsonData, "0", valueLable, false);
		/*if(taType &&  TA_CODE_ATTR==taType){
			sortedTaDataJson = _dataList;
		}else{
			sortedDataJson = _dataList;
		}*/
		//加载数据
		var filterTypeValue = $("#saleChart2FundTypeSelect").val();
		var startLog = new Date().getTime();
		
		var _dataMapList = loadOverallFundDataObj(_dataList,filterType,filterTypeValue, valueLable, name_attr);
		var endLog = new Date().getTime();
		console.log("drawChart2Top耗时:"+ (endLog-startLog));
		var colMapData = _dataMapList[0];
		var splineMapData = _dataMapList[1];
		var xMapData = _dataMapList[2].get("X");
		var yAxisTitleArray=["费率","金额"];
		drawCombinationChart("saleChart2FundDataChart", xMapData, colMapData, splineMapData, yAxisTitleArray);
	}
};

/**
 * 
 * **/
var saleChart2FundTypeSelectEvent=function(fundDataJson,fundDataSumtedJson,fundTaSumDataJson){
	$("#saleChart2FundTypeSelect").change(function(){
		var _this = $(this);
		var _localDataJson = null; 
		//按照指标大小排序
		var valueLable =currentTag;
		if(!valueLable)
			valueLable = $("#fundSaleQkTab li.current").attr("tag");
		var _dataList=null;
		var filterType = _this.find("option:selected").attr("ftype");
		var taType = _this.find("option:selected").attr("ttype");
		var name_attr = FUND_NAME_ATTR;
		if(taType && TA_CODE_ATTR==taType){
			name_attr =TA_NAME_ATTR;
			_dataList  = sortedTaDataJson;
		}
		//if(!_dataList || _dataList.length==0){}

		if(taType && TA_CODE_ATTR==taType){
			//基金公司汇总数据
			_localDataJson = fundTaSumDataJson;
		}else{
			if(MARKET_AMT_ATTR==valueLable){//存量金额取最近一天的数据排序
				var lastDay = $("#end_date").val();
				lastDay = lastDay.replace(/-/g,"");
				_localDataJson = getDataByAttr(fundDataJson.slice(), TRADE_DT_ATTR, lastDay);
			}else{
				_localDataJson = fundDataSumtedJson;
			}
		}
		_dataList=sortTop(_localDataJson.slice(), "0", valueLable, false);
	
		//加载数据
		var filterTypeValue = _this.val();
		var _dataMapList = loadOverallFundDataObj(_dataList, filterType,filterTypeValue, valueLable, name_attr);
		var colMapData = _dataMapList[0];
		var splineMapData = _dataMapList[1];
		var xMapData = _dataMapList[2].get("X");
		var yAxisTitleArray=["费率","金额"];
		drawCombinationChart("saleChart2FundDataChart", xMapData, colMapData, splineMapData, yAxisTitleArray);
		
	});
};

/**
 *收入数据图表二tab切换事件
 ***/
var toggleIncomeDataChart2TabEvent=function(fundDataJson,fundTaSumDataJson){
	$("#incomeChart2Tab li").click(function(){
		
		//显示tab，
		var _this = $(this);
		if(_this.hasClass("current"))return;
		$("#incomeChart2Tab li[class=current]").removeClass("current");
		_this.addClass("current");
		drawIncomeTab2Chart(fundDataJson,fundTaSumDataJson);
	});
};

var incomeCurrentTag=null;
var incomeSortedDataJson=null;
var incomeSortedTaDataJson=null;
/**
 * 收入数据画图
 * */
var drawIncomeTab2Chart=function(fundDataJson,fundTaSumDataJson){
	var _dataList = [];
	if(fundDataJson){
		//按照指标大小排序
		var valueLable =$("#incomeChart2Tab li.current").attr("tag");
		currentTag = valueLable;
		_dataList=sortTop(fundDataJson, "0", valueLable, false);
		incomeSortedDataJson = _dataList;
		//加载数据
		var filterTypeValue = $("#incomeFundTypeTopSelect").val();
		var filterType = $("#incomeFundTypeTopSelect option:selected").attr("ftype");
		var taType = $("#incomeFundTypeTopSelect option:selected").attr("ttype");
		var name_attr = FUND_NAME_ATTR;
		if(taType && TA_CODE_ATTR==taType){
			name_attr =TA_NAME_ATTR;
			incomeSortedTaDataJson=_dataList;
		}
		
		var _dataMapList = loadIncomeFundDataObj(_dataList, filterType,filterTypeValue, valueLable, name_attr);
		var colMapData = _dataMapList[0];
		var xMapData = _dataMapList[1].get("X");
		HbCharts.loadColumnChart({
			targetId: $("#incomeChart2"),  //元素
			dataX: xMapData,  //x轴数据
			dataY: colMapData,  //y轴数据
			oneDataWidth: 10,  //一条数据柱体宽度
			pointWidth: 20,  //柱体宽度
			verticalAlign:"bottom",
			valueSuffix:""
		});
	}
};

var incomeFundTypeTopSelectChangeEvent=function(fundDataJson,fundTaSumDataJson){
	$("#incomeFundTypeTopSelect").change(function(){
		var _this = $(this);
		//按照指标大小排序
		var valueLable =incomeCurrentTag;
		var localData = fundDataJson.slice();
		if(!valueLable)
			valueLable = $("#incomeChart2Tab li.current").attr("tag");
		var _dataList=incomeSortedDataJson;
		
		var taType = _this.find("option:selected").attr("ttype");
		var name_attr = FUND_NAME_ATTR;
		if(taType && TA_CODE_ATTR==taType){
			name_attr =TA_NAME_ATTR;
			_dataList  = incomeSortedTaDataJson;
			localData  = fundTaSumDataJson.slice();
		}
		if(!_dataList || _dataList.length == 0)
			_dataList=sortTop(localData, "0", valueLable, false);
		//加载数据
		
		var filterType = _this.find("option:selected").attr("ftype");
		var filterTypeValue = _this.val();
		var _dataMapList = loadIncomeFundDataObj(_dataList, filterType,filterTypeValue, valueLable, name_attr);
		var colMapData = _dataMapList[0];
		var xMapData = _dataMapList[1].get("X");
		HbCharts.loadColumnChart({
			targetId: $("#incomeChart2"),  //元素
			dataX: xMapData,  //x轴数据
			dataY: colMapData,  //y轴数据
			oneDataWidth: 10,  //一条数据柱体宽度
			pointWidth: 20,  //柱体宽度
			verticalAlign:"bottom",
			valueSuffix:""
		});
	});
};


/**
 * 设置收入和成本数据
 */
function setIncomeAndCapital(fundDataDetailJson,fundType){
	if(fundDataDetailJson){
		var  income = 0;
		var  capital = 0;
		for(var i=0;i<fundDataDetailJson.length;i++){
			var dataObj = fundDataDetailJson[i];
			var fundTypeVar = dataObj[FUND_TYPE_ATTR];
			if(!fundType)
				fundType = "-1";
			if(fundTypeVar==fundType){
				income+=dataObj.INCOME;
				capital+=dataObj.CAPITAL;
			}
		}
		$("#income").html(formatNum(new Number(income), fixedNumber));
		$("#capital").html(formatNum(new Number(capital), fixedNumber));
	}
}
/**
 * 基金、基金公司tab切换事件
 * **/
var fundAndCompanyTogEvent=function(){
	$("#fundAndCompanyTab li").click(function(){
		var _this =$(this);
		if(_this.hasClass("current"))return;
		$("#fundAndCompanyTab li.current").removeClass("current");
		_this.addClass("current");
		$("#fundAndCompanyTabBox div.con").each(function(i,obj){
			var divbox = $(obj);
			if(divbox.hasClass("hide")){
				divbox.removeClass("hide");
			}else{
				divbox.addClass("hide");
			}
		});
	});
	
};


/**
 * 获取当前选择日期的连续数据
 * @returns
 */
function getdatexArray(){
	var startTime = $("#start_date").val();
	var endTime = $("#end_date").val();
	return getDateX(startTime, endTime);
}

/**
 * 画单个基金数据图表
 */
function drawFundDataChart(e,fundCode,datex,contextPath){
	//查询基金数据
	var startTime = $("#start_date").val();
	if(startTime)startTime = startTime.replace(/-/g,"");
	var endTime = $("#end_date").val();
	if(endTime)endTime = endTime.replace(/-/g,"");
	var params = "fundCode="+fundCode+"&beginDate="+startTime+"&endDate="+endTime;
	var reqObj = {
			url : contextPath+'/auth/fundsale/ajaxGetFundData.htm',
			postMethod : 'POST',
			dataType:"json",
			params :params ,
			callback : function(data) {
				var fundJsonData = data;
				
				if(fundJsonData){
					//构造数据
					var wdtype = getwd();
					var fundDataMap = loadFundDetailJsonData(fundJsonData, datex, wdtype);
					var _hashmapColumn=fundDataMap[0];
					var _hashmapSpline=fundDataMap[1];
					//画图
					var yAxisTitleArray=["费率","金额"];
					drawCombinationChart(e, datex, _hashmapColumn, _hashmapSpline, yAxisTitleArray);
				}
					
			}	
	};
	ajaxRequest(reqObj);
}


/**
 * 画单个基金公司数据图表
 */
function drawFundCompanyDataChart(e,taCode,datex,ctPath){
	//查询基金数据
	var startTime = $("#start_date").val();
	if(startTime)startTime = startTime.replace(/-/g,"");
	var endTime = $("#end_date").val();
	if(endTime)endTime = endTime.replace(/-/g,"");
	var params = "taCode="+taCode+"&beginDate="+startTime+"&endDate="+endTime;
	var reqObj = {
			url : ctPath+'/auth/fundsale/ajaxGetFundCompanyData.htm',
			postMethod : 'POST',
			dataType:"json",
			params :params ,
			callback : function(data) {
				var fundCompanyJsonData = data;
				if(fundCompanyJsonData){
					//构造数据
					var wdtype = getwd();
					var fundCompanyDataMap = loadFundDetailJsonData(fundCompanyJsonData, datex, wdtype);
					var _hashmapColumn=fundCompanyDataMap[0];
					var _hashmapSpline=fundCompanyDataMap[1];
					//画图
					var yAxisTitleArray=["费率","金额"];
					drawCombinationChart(e, datex, _hashmapColumn, _hashmapSpline, yAxisTitleArray);
				}
					
			}	
	};
	ajaxRequest(reqObj);
}

function setTrendAndFundDivTableHead(){
	var wdtype = getwd();
	$("#trendAndFundDiv table thead tr[type="+wdtype+"]").removeClass("hide");
	$("#trendAndFundDiv table thead tr[type!="+wdtype+"]").addClass("hide");
}
/**
 * 趋势tab切换事件
 */
function eventTrendTabToggle(){
	$("#trendTabUL li").click(function(){
		var _thisLi = $(this);
		if(_thisLi.hasClass("current"))return;
		$("#trendTabUL li.current").removeClass("current");
		_thisLi.addClass("current");
		$("#trendAndFundDiv div.con").each(function(i,obj){
			var d = $(obj);
			if(d.hasClass("hide"))d.removeClass("hide");
			else d.addClass("hide");
		});
		//分页控制
		$("div.page").each(function(i,obj){
			var d = $(obj);
			if(d.hasClass("hide"))d.removeClass("hide");
			else d.addClass("hide");
		});
		//根据维度设置table表头
		setTrendAndFundDivTableHead();
		
	});
}
/**
 * 基金类型显示属性字段
 */
var fundTypeShowAttrSale=new Array();
var fundTypeShowAttrIncome=new Array();
fundTypeShowAttrSale.push("TRADE_DT");//日期
fundTypeShowAttrSale.push("FUND_TYPE_NAME");//类型
fundTypeShowAttrSale.push("MARKET_AMT");//存量金额
fundTypeShowAttrSale.push("APP_AMT");//申购金额（下单）
fundTypeShowAttrSale.push("SELL_APP_VOL");//赎回份额（下单）
fundTypeShowAttrSale.push("ACK_AMT");//申购金额（确认）
fundTypeShowAttrSale.push("SELL_ACK_AMT");//赎回金额（确认）
fundTypeShowAttrSale.push("NET_ACK_AMT");//净申购金额（确认）
fundTypeShowAttrSale.push("FEE_RATE");//费率

fundTypeShowAttrIncome.push("TRADE_DT");//日期
fundTypeShowAttrIncome.push("FUND_TYPE_NAME");//类型
fundTypeShowAttrIncome.push("INCOME");//收入
fundTypeShowAttrIncome.push("CAPITAL");//成本
fundTypeShowAttrIncome.push("RAISE_FEE");//认购费收入
fundTypeShowAttrIncome.push("SUBS_FEE");//申购费收入
fundTypeShowAttrIncome.push("REDE_FEE");//赎回费收入
fundTypeShowAttrIncome.push("SVC_FEE");//尾随收入
fundTypeShowAttrIncome.push("AGENT_SVC_FEE");//销售服务费收入
fundTypeShowAttrIncome.push("OTHER_FEE");//额外营销费用
/**
 * 基金数据显示属性字段
 */
var fundDataShowAttrSale=new Array();
var fundDataShowAttrIncome=new Array();
fundDataShowAttrSale.push("FUND_NAME");//基金
fundDataShowAttrSale.push("TA_NAME");//公司名称
fundDataShowAttrSale.push("FUND_TYPE_NAME");//类型
fundDataShowAttrSale.push("MARKET_AMT");//存量金额
fundDataShowAttrSale.push("APP_AMT");//申购金额（下单）
fundDataShowAttrSale.push("SELL_APP_VOL");//赎回份额（下单）
fundDataShowAttrSale.push("ACK_AMT");//申购金额（确认）
fundDataShowAttrSale.push("SELL_ACK_AMT");//赎回金额（确认）
fundDataShowAttrSale.push("NET_ACK_AMT");//净申购金额(确认)
fundDataShowAttrSale.push("FEE_RATE");//费率

fundDataShowAttrIncome.push("FUND_NAME");//基金
fundDataShowAttrIncome.push("TA_NAME");//公司
fundDataShowAttrIncome.push("FUND_TYPE_NAME");//类型
fundDataShowAttrIncome.push("INCOME");//收入
fundDataShowAttrIncome.push("CAPITAL");//成本
fundDataShowAttrIncome.push("RAISE_FEE");//认购费收入
fundDataShowAttrIncome.push("SUBS_FEE");//申购费收入
fundDataShowAttrIncome.push("REDE_FEE");//赎回费收入
fundDataShowAttrIncome.push("AGENT_SVC_FEE");//销售服务费收入
fundDataShowAttrIncome.push("SVC_FEE");//尾随收入
fundDataShowAttrIncome.push("OTHER_FEE");//额外营销费用

function buildDataObj(attrArray,dataObj){
	var tdArray = new Array();
	for(var i=0;i<attrArray.length;i++){
		var attr = attrArray[i];
		var dataValue = dataObj[attr];
		if(null==dataValue || !dataValue)
			dataValue = "-";
		if(TRADE_DT_ATTR!=attr && !isNaN(dataValue)){
			//判断是否是费率
			if("-"!=dataValue && FEE_RATE_ATTR==attr)
				dataValue = new Number(dataValue*100);
			//保留两位小数
			if("-"!=dataValue)
				dataValue=formatNum(new Number(dataValue), fixedNumber);
			
			if("-"!=dataValue && FEE_RATE_ATTR==attr)
				dataValue=dataValue+"%";
		}
		var tdValue = "<td>"+dataValue+"</td>";
		tdArray.push(tdValue);
	}
	return tdArray.join("");
	
}
/**
 * 加载基金类型数据
 * @param pth
 */
function loadFundTypeData(pth){
	var fundUrl = pth+"/auth/fundsale/ajaxQueryFundTypeList.htm";
	var param=formatdata();
	$.post(fundUrl,param,function(data){
		if(data){
			var dataList = data.body.list;
			var page = data.body.pagination;
			var wdtype = getwd();
			var attrArray = null;
			if(wdtype=="1"){
				//销量
				attrArray=fundTypeShowAttrSale.slice();
			}else if(wdtype=="2"){//收入
				attrArray=fundTypeShowAttrIncome.slice();
			}
			if(dataList){
				$("#fundTypeDetail_table_tbody").html("");
				for(var i=0;i<dataList.length;i++){
					var dataObj = dataList[i];
					var tr="<tr>"+buildDataObj(attrArray, dataObj)+"</tr>";
					$("#fundTypeDetail_table_tbody").append(tr);
				}
			}
			if(page){
				//设置分页信息
				var currentPage = page.pageIndex;
				$("#fundtypepage fundtypepage_count").html(page.pageCount);
				$("#fundtypepage fundtypepagePerPage").val(page.pageSize);
				var pn=$("#fundtypepage ul[tag='pn']");
				pn.find("li[tag='ps'] span").html(currentPage);
			}
		}
	});
}

/**
 * @param ep 1 上一页，2下一页
 */
function changePageFundType(ep){
	var fundUrl = pth+"/auth/fundsale/ajaxQueryFundTypeList.htm";
	var param=formatdata();
	if(ep){
		var pageindex = $("#fundtypepage ul[tag='pn'] li[tag='ps'] span").html();
		var page = parseInt(pageindex);
		var count = $("#fundtypepage fundtypepage_count").html();
		if(ep==1)
			page-=1;
		else if(ep==2)
			page+=1;
		if(page<=0)page=1;
		else if(page>parseInt(count))page=count;
		var pagesize = $("#fundtypepagePerPage").val();
		param+="&pageindex="+page+"&pagesize="+pagesize;
	}else{
		var pagesize = $("#fundtypepagePerPage").val();
		param+="&pageindex=1&pagesize="+pagesize;
	}
	$.post(fundUrl,param,function(data){
		if(data){
			var dataList = data.body.list;
			var page = data.body.pagination;
			var wdtype = getwd();
			var attrArray = null;
			if(wdtype=="1"){
				//销量
				attrArray=fundTypeShowAttrSale.slice();
			}else if(wdtype=="2"){//收入
				attrArray=fundTypeShowAttrIncome.slice();
			}
			if(dataList){
				$("#fundTypeDetail_table_tbody").html("");
				for(var i=0;i<dataList.length;i++){
					var dataObj = dataList[i];
					var tr="<tr>"+buildDataObj(attrArray, dataObj)+"</tr>";
					$("#fundTypeDetail_table_tbody").append(tr);
				}
			}
			if(page){
				//设置分页信息
				$("#fundtypepage fundtypepage_count").html(page.pageCount);
				var currentPage = page.pageIndex;
				var pn=$("#fundtypepage ul[tag='pn']");
				pn.find("li[tag='ps'] span").html(currentPage);
			}
		}
	});

}

/**
 * 分页点击事件
 */
function eventPageControl(){
	//上下页事件
	$("div.page ul[tag='pn'] li:not([tag])").click(function(){
		var _this = $(this);
		var type = _this.parent("ul[type]").attr("type");
		if(type=="fundtype"){
			var pc = _this.attr("pc");
			if(pc=="p")
				changePageFundType(1);
			else if(pc=="n")
				changePageFundType(2);
			
		}else if(type=="funddetailtype"){
			var pc = _this.attr("pc");
			if(pc=="p")
				changePageFundData(1);
			else if(pc=="n")
				changePageFundData(2);
		}
		
	});
	
	//改变页数事件
	pageSelectChangeEvent();
	
}


/**
 * @param ep 1 上一页，2下一页
 */
function changePageFundData(ep){
	var fundUrl = pth+"/auth/fundsale/ajaxQueryFundDetailList.htm";
	var param=formatdata();
	if(ep){
		var pageindex = $("#funddetailpage ul[tag='pn'] li[tag='ps'] span").html();
		var page = parseInt(pageindex);
		var count = $("#funddetailpage funddetailpage_count").html();
		if(ep==1)
			page-=1;
		else if(ep==2)
			page+=1;
		if(page<=0)page=1;
		else if(page>parseInt(count))page=count;
		var pagesize = $("#fundDataPerPage").val();
		param+="&pageindex="+page+"&pagesize="+pagesize;
	}else{
		var pagesize = $("#fundDataPerPage").val();
		param+="&pageindex=1&pagesize="+pagesize;
	}
	$.post(fundUrl,param,function(data){
		if(data){
			var dataList = data.body.list;
			var page = data.body.pagination;
			var wdtype = getwd();
			var attrArray = null;
			if(wdtype=="1"){
				//销量
				attrArray=fundDataShowAttrSale.slice();
			}else if(wdtype=="2"){//收入
				attrArray=fundDataShowAttrIncome.slice();
			}
			if(dataList){
				$("#fundDetail_table_tbody").html("");
				for(var i=0;i<dataList.length;i++){
					var dataObj = dataList[i];
					var tr="<tr>"+buildDataObj(attrArray, dataObj)+"</tr>";
					$("#fundDetail_table_tbody").append(tr);
				}
			}
			if(page){
				//设置分页信息
				var currentPage = page.pageIndex;
				var pn=$("#funddetailpage ul[tag='pn']");
				pn.find("li[tag='ps'] span").html(currentPage);
				$("#funddetailpage funddetailpage_count").html(page.pageCount);
			}
		}
	});
	
}

/**
 * 加载基金数据
 * @param pth
 */
function loadFundDetailData(pth){
	var fundDetailUrl = pth+ "/auth/fundsale/ajaxQueryFundDetailList.htm"; 
	//加载基金明细数据
	var param=formatdata();
	$.post(fundDetailUrl,param,function(data){
		if(data){
			var dataList = data.body.list;
			var page = data.body.pagination;
			var wdtype = getwd();
			var attrArray = null;
			if(wdtype=="1"){
				//销量
				attrArray=fundDataShowAttrSale.slice();
			}else if(wdtype=="2"){//收入
				attrArray=fundDataShowAttrIncome.slice();
			}
			if(dataList){
				$("#fundDetail_table_tbody").html("");
				for(var i=0;i<dataList.length;i++){
					var dataObj = dataList[i];
					var tr="<tr>"+buildDataObj(attrArray, dataObj)+"</tr>";
					$("#fundDetail_table_tbody").append(tr);
				}
			}
			if(page){
				//设置分页信息
				var currentPage = page.pageIndex;
				$("#funddetailpage funddetailpage_count").html(page.pageCount);
				$("#funddetailpage fundDataPerPage").val(page.pageSize);
				var pn=$("#funddetailpage ul[tag='pn']");
				pn.find("li[tag='ps'] span").html(currentPage);
			}
		}
	
	});
}

/**
 * 页数下拉框选项改变事件
 */
function pageSelectChangeEvent(){
	$("div.page select").change(function(){
		var  _this=(this);
		var  ul = $(_this.parentNode).siblings("ul");
		var type = ul.attr("type");
		if(type=="fundtype"){
			//基金类型明细
			changePageFundType();
		}else if("funddetailtype"){
			//基金数据明细
			changePageFundData();
		}
	});
}


/**
 * 下载事件
 */
function downloadEvent(pth){
	$("#fundsaleExport").click(function(){
		var type = $("#trendTabUL li.current").index()+1;
		var params = formatdata();
		params+="&dtype="+type+"&pagesize=10000&pageindex=1";
		var url = pth+"/auth/fundsale/downLoadData.htm?"+params;
		open(url);
	});
}


