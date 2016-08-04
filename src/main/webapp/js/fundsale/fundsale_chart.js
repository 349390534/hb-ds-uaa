/**
 * 基金销量画图js
 */


var FUND_TYPE_ATTR="FUND_TYPE";
var FUND_TYPE_NAME_ATTR="FUND_TYPE_NAME";
var TRADE_DT_ATTR="TRADE_DT";
var FEE_RATE_ATTR = "FEE_RATE";
/**
 * 画折线图
 * @param e
 * @param dataObj
 * */
var drawLine=function(e,dataObj){
	var valueSuffix="";
	$('#'+e).highcharts(
			{
				chart : {
					type : 'line'
				},
				title : {
					// text:title,
					text : '',
					useHTML : true,
					style : {
						color : 'black',
						fontSize : '14px',
						fontFamily : '微软雅黑'
					}
				},
				xAxis : {
					// categories: datax,
					type : 'datetime',
					dateTimeLabelFormats : {
						second : '',
						minute : '',
						hour : '',
						day : '%y-%m',
						week : '%y-%m-%d',
						month : '%Y-%m',
						year : '%Y'
					},
					labels : {
						formatter : function() {
							return Highcharts
									.dateFormat('%y-%m-%d', this.value);
						}
					},
					title : {
						text : ''
					}
				},
				yAxis : {
					min : 0,
					plotLines : [ {
						value : 0,
						width : 1,
						color : '#808080'
					} ],
					dateTimeLabelFormats : {
						day : '%y-%m-%d',
						week : '%y-%m-%d',
						month : '%Y-%m',
						year : '%Y'
					},
					title:{
						text:''
					},
					labels: {
		                formatter: function () {
		                    return formatNum(this.value,0) + valueSuffix;
		                }
		            }
				},
				tooltip : {
					formatter : function() {
						return '日期:'
								+ Highcharts.dateFormat('%Y-%m-%e', this.x)
								+ '<b><br/>' + this.series.name + '</b>: '
								+ Highcharts.numberFormat(this.y,2,'.') + valueSuffix;
					},
					dateTimeLabelFormats : {
						day : '%y-%m-%e',
						week : '%y-%m-%e',
						month : '%Y-%m',
						year : '%Y'
					},
					valueDecimals: 2,
					/*valueSuffix: valueSuffix*/
				},
				series : dataObj.series
			});


};

/**
 * 获取基金类型的map，key：type,value：type_name
 * **/
var buildxAxis=function(fundTypeJson){
	var fundTypeMap = new HashMap();
	if(fundTypeJson){
		for(var i in fundTypeJson){
			var ft=fundTypeJson[i];
			var type = ft.FUND_TYPE;
			var name = ft.FUND_TYPE_NAME;
			fundTypeMap.put(type,name);
		}
	}
	return fundTypeMap;
};

 /**
 * 填充缺失数据 销量数据总体情况
 * @param data
 * @param _pro_c
 * @param _pro_nameTag
 * @param dataTag
 * @param fromDate
 * @param datax
 */
var fillDataLineFundSale=function(data, _pro_codeTag, _pro_nameTag, dataTag,fromDate,datax,fillKey){
	var _dataList = data.slice();
	 
	var arr = [];
	for(var d=0;d<datax.length;d++){
		var date = datax[d];
		var findValue =false;
		for (var i = 0; i < _dataList.length; i++) {
			var obj = _dataList[i];
			var dateVar = obj[TRADE_DT_ATTR];
			var dateR = date.replace(/\-/g,"");
			if(dateR==dateVar){
				var val_code_tag = obj[_pro_codeTag];
				var val_name_tag = obj[_pro_nameTag];
				var key = val_name_tag == null ? val_code_tag : val_name_tag;
				if(_pro_nameTag =="全部"){
					key=_pro_nameTag;
				}
				if(null ==key || "" ==key){
					continue;
				}
				if(fillKey!=key){
					continue;
				}
				findValue =true;
				var val_dataTag = obj[dataTag];
				var target_value = 0;
				if(""!=val_dataTag && !isNaN(val_dataTag)){
					target_value = parseFloat(val_dataTag);
				}
				var index = arr.length;
				arr[index] = target_value;
			}
		}
		if(!findValue){
			var index = arr.length;
			arr[index] = 0;
		}
	}
	return arr;
};

var buildfundTypeDataData=function(fundDataDetailJson,dataTag,_pro_codeTag,_pro_nameTag,datax){
	var dataObj = [];
	if (null != fundDataDetailJson) {
		var _dataList = fundDataDetailJson;
		var _hashmap = new HashMap();
		for (var i = 0; i < _dataList.length; i++) {
			var obj = _dataList[i];
			var val_code_tag = obj[_pro_codeTag];
			var val_name_tag = obj[_pro_nameTag];
			var val_dataTag = obj[dataTag];
			var target_value = 0;
			if(""!=val_dataTag && !isNaN(val_dataTag)){
				target_value = parseFloat(val_dataTag);
			}
			var key = val_name_tag == null ? val_code_tag : val_name_tag;
			if(_pro_nameTag =="全部"){
				key=_pro_nameTag;
			}
			if(null ==key || "" ==key){
				continue;
			}
			var value = _hashmap.get(key);
			if (null == value) {
				var arr = [];
				arr[0] = target_value;
				_hashmap.put(key, arr);
			} else {
				var index = value.length;
				value[index] = target_value;
				_hashmap.put(key, value);
			}
		}
		var fromDate = datax[0];
		var date = new Date();
		switch(typeof fromDate) {   
	        case "string":   
	            date = new Date(fromDate);   
	            break;   
	        case "number":   
	            date = new Date(fromDate);   
	            break;
	    }
		if (!_hashmap.isEmpty()) {
			var keySets = _hashmap.keySets();
			for (var k = 0; k < keySets.length; k++) {
				var hkey = keySets[k];
				var value = _hashmap.get(hkey);
				if(null ==hkey || "" ==hkey){
					continue;
				}
				//判断数据缺少
				var vLen = value.length;
				var dLen = datax.length;
				if(vLen<=dLen-1){//缺少数据
					var fillValue = fillDataLineFundSale(_dataList, _pro_codeTag, _pro_nameTag, dataTag, fromDate, datax, hkey);
					value = fillValue;
				}
				var dObj = {
					name : hkey,
					data : value.slice(),
					pointStart: Date.UTC(date.getFullYear(), date.getMonth(), date.getDate()),
		            pointInterval: 24 * 3600 * 1000
				};
				dataObj.push(dObj);
			}
		}

	}
	return dataObj;

	
}; 
/**
 * 加载销量数据总体情况的图表数据
 * */
var loadFundSaleDataOfOverall=function(fundTypeJson,fundDataDetailJson,target,datax){
	var dataObj={};
	var xAxismap=buildxAxis(fundTypeJson);
	var xAxis=xAxismap.valueSets();
	dataObj.xAxis = xAxis;
	var dataY = buildfundTypeDataData(fundDataDetailJson, target,FUND_TYPE_ATTR,FUND_TYPE_NAME_ATTR,datax);
	dataObj.series=dataY;
	return dataObj;
};
var drawChartFundSaleDataOfOverall = function (e,fundTypeJson,fundDataDetailJson,target,datax){
	var t1 = new Date().getTime();
	var dataObj=loadFundSaleDataOfOverall(fundTypeJson, fundDataDetailJson, target,datax);
	var t2 = new Date().getTime();
	console.log("loadFundSaleDataOfOverall of drawChartFundSaleDataOfOverall 耗时:"+(t2-t1)+"毫秒");
	drawLine(e, dataObj);
};


/**
 * 销量情况总体情况基金类型指标属性和名称map
 * key:列值,value:名称
 */
var overallFundTypeTagNameDataMap=new HashMap();
overallFundTypeTagNameDataMap.put("MARKET_AMT","存量金额");
overallFundTypeTagNameDataMap.put("APP_AMT","申购金额(下单)");
overallFundTypeTagNameDataMap.put("SELL_APP_VOL","赎回份额(下单)");
overallFundTypeTagNameDataMap.put("ACK_AMT","申购金额(确认)");
overallFundTypeTagNameDataMap.put("SELL_ACK_AMT","赎回金额(确认)");
overallFundTypeTagNameDataMap.put("NET_ACK_AMT","净申购金额(确认)");
overallFundTypeTagNameDataMap.put("FEE_RATE","费率");

var overallFundTypeTagNameDataTypeMap=new HashMap();
overallFundTypeTagNameDataTypeMap.put("MARKET_AMT","column");
overallFundTypeTagNameDataTypeMap.put("APP_AMT","column");
overallFundTypeTagNameDataTypeMap.put("SELL_APP_VOL","column");
overallFundTypeTagNameDataTypeMap.put("ACK_AMT","column");
overallFundTypeTagNameDataTypeMap.put("SELL_ACK_AMT","column");
overallFundTypeTagNameDataTypeMap.put("NET_ACK_AMT","column");
overallFundTypeTagNameDataTypeMap.put("FEE_RATE","spline");

/**
 * 收入数据基金类型指标
 * */
var incomeFundTypeTagNameDataMap=new HashMap();
incomeFundTypeTagNameDataMap.put("INCOME","收入");
incomeFundTypeTagNameDataMap.put("CAPITAL","成本");
incomeFundTypeTagNameDataMap.put("RAISE_FEE","认购费收入");
incomeFundTypeTagNameDataMap.put("SUBS_FEE","申购费收入");
incomeFundTypeTagNameDataMap.put("REDE_FEE","赎回费收入");
incomeFundTypeTagNameDataMap.put("SVC_FEE","尾随收入");
//incomeFundTypeTagNameDataMap.put("svc_amt","尾随金额");
incomeFundTypeTagNameDataMap.put("AGENT_SVC_FEE","销售服务费收入");
incomeFundTypeTagNameDataMap.put("OTHER_FEE","额外营销费用");

var incomeFundTypeTagNameDataTypeMap=new HashMap();
incomeFundTypeTagNameDataTypeMap.put("SVC_AMT","column");
incomeFundTypeTagNameDataTypeMap.put("RAISE_FEE","column");
incomeFundTypeTagNameDataTypeMap.put("SUBS_FEE","column");
incomeFundTypeTagNameDataTypeMap.put("REDE_FEE","column");
incomeFundTypeTagNameDataTypeMap.put("SVC_FEE","column");
incomeFundTypeTagNameDataTypeMap.put("AGENT_SVC_FEE","column");
incomeFundTypeTagNameDataTypeMap.put("OTHER_FEE","column");
incomeFundTypeTagNameDataTypeMap.put("INCOME","column");
incomeFundTypeTagNameDataTypeMap.put("CAPITAL","column");
/**
 * 销量数据填充
 * **/
var fillDataOverallFundType=function(fundDataDetailJson,datex,fundTypeValue,hkey){
	var _dataList = [];
	if(fundDataDetailJson)
		_dataList=fundDataDetailJson;
	var arr=new Array();
	for(var d=0;d<datex.length;d++){
		var date = datex[d];
		var findValue =false;
		for (var i = 0; i < _dataList.length; i++) {
			var obj = _dataList[i];
			if(fundTypeValue && ""!=fundTypeValue){
				var fundType = obj[FUND_TYPE_ATTR];
				if(fundType!=fundTypeValue){
					continue;
				}
			}
			var dt = obj[TRADE_DT_ATTR];
			var dtVar = date.replace(new RegExp(/-/g),'');
			if(dtVar==dt){
				//查找对应属性
				var val_dataTag = obj[hkey];
				var target_value = 0;
				if(val_dataTag && !isNaN(val_dataTag)){
					target_value = parseFloat(val_dataTag);
				}
				var key = hkey;
				if(null ==key || "" ==key){
					continue;
				}
				findValue=true;
				var index = arr.length;
				arr[index] = target_value;
			}
			
		}
		if(!findValue){
			var index = arr.length;
			arr[index] = 0;
		}
	}
	return arr;
};


/**
 * 加载总体情况销量数据基金类型数据
 * **/
var loadOverallFundTypeDataObj=function(fundDataDetailJson,fundTypeValue,datex,wdtype){
	var _dataMapList = [];
	var _dataList = [];
	if(fundDataDetailJson)
		_dataList=fundDataDetailJson;
	var _hashmapColumn = new HashMap();
	var _hashmapSpline = new HashMap();
	var _hashmap = new HashMap();
	var dataTagSet=null;
	
	if(wdtype==1){//销量数据
		dataTagSet=overallFundTypeTagNameDataMap.keySets();
	}else if(wdtype==2){//收入数据
		dataTagSet=incomeFundTypeTagNameDataMap.keySets();
	}
	for (var i = 0; i < _dataList.length; i++) {
		var obj = _dataList[i];
		var fundType = obj[FUND_TYPE_ATTR];
		if(fundType!=fundTypeValue){
			continue;
		}
		//所有需要展示的数据属性
		for(var d=0;d<dataTagSet.length;d++){
			//查找每一个属性
			var dattr = dataTagSet[d];
			var val_dataTag = obj[dattr];
			var target_value = 0;
			if(val_dataTag && !isNaN(val_dataTag)){
				target_value = parseFloat(val_dataTag);
			}
			var key = dattr;
			if(null ==key || "" ==key){
				continue;
			}
			var value = _hashmap.get(key);
			if (null == value) {
				var arr = [];
				arr[0] = target_value;
				_hashmap.put(key, arr);
			} else {
				var index = value.length;
				value[index] = target_value;
				_hashmap.put(key, value);
			}
		}
	}
	if (!_hashmap.isEmpty()) {
		var keySets = _hashmap.keySets();
		for (var k = 0; k < keySets.length; k++) {
			var hkey = keySets[k];
			var value = _hashmap.get(hkey);
			if(null ==hkey || "" ==hkey){
				continue;
			}
			//判断数据缺少
			var vLen = value.length;
			var dLen = datex.length;
			if(vLen<=dLen-1){//缺少数据
				var fillValue = fillDataOverallFundType(fundDataDetailJson, datex, fundTypeValue, hkey);
				value = fillValue;
			}
			var type = null;
			
			var name=null;
			if(wdtype==1){//销量数据
				name=overallFundTypeTagNameDataMap.get(hkey);
				type=overallFundTypeTagNameDataTypeMap.get(hkey);
			}else if(wdtype==2){//收入数据
				name=incomeFundTypeTagNameDataMap.get(hkey);
				type=incomeFundTypeTagNameDataTypeMap.get(hkey);
			}
			var data=value.slice();
			if(type=="column")
				_hashmapColumn.put(name,data);
			else if(type=="spline")
				_hashmapSpline.put(name,data);
		}
	}
	_dataMapList[0]=_hashmapColumn;
	_dataMapList[1]=_hashmapSpline;
	return _dataMapList;

	
};

/**乘法
 * @param value
 * @param mNum
 */
function setValueMulti(valueArray,mNum){
	var returnArray = new Array();
	if(valueArray){
		for(var i=0;i<valueArray.length;i++){
			var v = valueArray[i];
			returnArray[i]=v*100;
		}
	}
	return returnArray;
}
/**
 * 画混合图表
 */
function drawCombinationChart(e,categoriesArray,columnDataMap,splineDataMap,yAxisTitleArray){
	var seriesDataArray = [];
	var maxNumber = 0;
	var minNumber = Number.MAX_VALUE;
	if(!columnDataMap.isEmpty()){
		var keySets = columnDataMap.keySets();
		var dataLabelsOption = {enabled: false,rotation: -90,color: '#FFFFFF',y: 30};
		var len = keySets.length;
		for (var k = 0; k < len; k++) {
			var hkey = keySets[k];
			var cvalue = columnDataMap.get(hkey);
			if(null ==hkey || "" ==hkey){
				continue;
			}
			var v =true;
			var name =hkey;
			var _dataObj={
	            name: name,
	            type: 'column',
	            yAxis: 1,
	            data:cvalue,
	            dataLabels: dataLabelsOption,
	            tooltip: {
	                valueSuffix: ' ',
	            	valueDecimals: 2
	            },visible:v 
	        };
			seriesDataArray.push(_dataObj);
			var max = getM(cvalue, 1);
			maxNumber = Math.max(maxNumber,max);
			var min = getM(cvalue, 2);
			minNumber = Math.min(minNumber,min);
		}
	}
	if(!splineDataMap.isEmpty()){
		var keySets_s = splineDataMap.keySets();
		for (var j = 0; j < keySets_s.length; j++) {
			var hkey = keySets_s[j];
			var svalue = splineDataMap.get(hkey);
			svalue = setValueMulti(svalue.slice(), 100);
			if(null ==hkey || "" ==hkey){
				continue;
			}
			var v =true;
			var name = hkey;
			var dObj = {
					name: name,
		            type: 'spline',
		            data: svalue,
		            tooltip: {
		            	valueSuffix: " %",
		            	valueDecimals: 2
		            },
		            color: "#FF8247",
		            visible:v
		        };
			seriesDataArray.push(dObj);
		}
	}
	
	var  yAxis = [{ // Primary yAxis
        labels: {
            format: '{value}%',
            style: {
                color: Highcharts.getOptions().colors[1]
            }
        },
        title: {
            text: yAxisTitleArray[0]||" ",
            style: {
                color: Highcharts.getOptions().colors[1]
            }
        }
    }, { // Secondary yAxis
        title: {
            text: yAxisTitleArray[1]||"",
            style: {
                color: Highcharts.getOptions().colors[0]
            }
        },
        labels: {
            format: '{value}',
            style: {
                color: Highcharts.getOptions().colors[0]
            }
        },
        lineWidth : 1,
		opposite : true/*,
		//type : "logarithmic",
		min:minNumber,
		max:maxNumber*/
    }];
    $('#'+e).highcharts({
    	credits: { 
    	    enabled: false 
    	},
        chart: {
        	//zoomType: 'xy'
        },
        title: {
            text: ' '
        },
        xAxis: [{
            categories: categoriesArray.slice(),
            crosshair: true
        }],
        yAxis: yAxis,
        tooltip: {/*
			followTouchMove: true,
			headerFormat: '<span style="font-size:10px">{point.key}</span><table>',
			pointFormat: '<tr><td style="color:{series.color};padding:0">{series.name}: </td>' +
			'<td style="padding:0"><b>{point.y:.2f}' + valueSuffix + '</b></td></tr>',
			footerFormat: '</table>',*/
			shared: true,
			useHTML: true
		},
        series: seriesDataArray.slice()
    });
}
var fixedNumber = 2;
var topNum = 30;

/**
 * Chart2Top 数据加载
 * **/
var loadOverallFundDataObj=function(fundDataDetailJson,filterType,filterTypeValue,dataTag,namecode){
	var _dataMapList = [];
	var _dataList = [];
	if(fundDataDetailJson)
		_dataList=fundDataDetailJson;
	var _hashmapColumn = new HashMap();
	var _hashmapSpline = new HashMap();
	var _hashmapCatogary = new HashMap();
	var arrData=new Array();
	var arrFeeData=new Array();
	var arrXData=new Array();
	var len = _dataList.length;
	
	var num = 0;
	for (var i = 0; i <len ; i++) {
		var obj = _dataList[i];
		if(filterType){
			var fundType = obj[filterType];
			if(fundType!=filterTypeValue){
				continue;
			}
		}
		//查找每一个属性
		var val_dataTag = obj[dataTag];
		var target_value = 0;
		if(val_dataTag && !isNaN(val_dataTag)){
			target_value = parseFloat(val_dataTag);
		}
		var index = arrData.length;
		arrData[index] = target_value;
		
		var namex = obj[namecode];
		var xindex = arrXData.length;
		arrXData[xindex]=namex;
		
		//费率FEE_RATE
		var fee_data = obj[FEE_RATE_ATTR];
		var findex = arrFeeData.length;
		if(fee_data && !isNaN(fee_data)){
			fee_data = parseFloat(fee_data);
		}else{
			fee_data=0;
		}
		arrFeeData[findex] = fee_data;
		num++;
		if(topNum && num == topNum)
			break;
		
	}
	var ckey=$("#saleChart2FundTypeSelect option:selected").text();
	_hashmapColumn.put(ckey,arrData);
	_hashmapSpline.put("费率",arrFeeData);
	_hashmapCatogary.put("X",arrXData);
	 
	_dataMapList[0]=_hashmapColumn;
	_dataMapList[1]=_hashmapSpline;
	_dataMapList[2]=_hashmapCatogary;
	return _dataMapList;
	
};

/**
 * 加载收入数据
 * */
var loadIncomeFundDataObj=function(fundDataDetailJson,filterType,filterTypeValue,dataTag,namecode){
	var _dataMapList = [];
	var _dataList = [];
	if(fundDataDetailJson)
		_dataList=fundDataDetailJson;
	var _hashmapCatogary = new HashMap();
	var arrData=new Array();
	var arrFeeData=new Array();
	var arrXData=new Array();
	var len =  _dataList.length;
	
	var num=0;
	for (var i = 0; i < len ; i++) {
		var obj = _dataList[i];
		if(filterType){
			var fundType = obj[filterType];
			if(fundType!=filterTypeValue){
				continue;
			}
		}
		//查找每一个属性
		var val_dataTag = obj[dataTag];
		var target_value = 0;
		if(val_dataTag && !isNaN(val_dataTag)){
			target_value = parseFloat(val_dataTag);
		}
		var index = arrData.length;
		arrData[index] = target_value;
		
		var namex = obj[namecode];
		var xindex = arrXData.length;
		arrXData[xindex]=namex;
		
		//费率FEE_RATE
		var fee_data = obj[FEE_RATE_ATTR];
		var findex = arrFeeData.length;
		if(fee_data && !isNaN(fee_data)){
			fee_data = parseFloat(fee_data);
		}else{
			fee_data=0;
		}
		arrFeeData[findex] = fee_data;
		num++;
		if(topNum && num == topNum)
			break;
	}
	var ckey=$("#incomeFundTypeTopSelect option:selected").text();
	_hashmapCatogary.put("X",arrXData);
	var columnData = [{name:ckey,data:arrData}];
	_dataMapList[0]=columnData;
	_dataMapList[1]=_hashmapCatogary;
	return _dataMapList;
	
};



/**
 * 单个基金数据填充
 * **/
var fillFundData=function(fundDataDetailJson,datex,hkey){
	var _dataList = [];
	if(fundDataDetailJson)
		_dataList=fundDataDetailJson;
	var arr=new Array();
	for(var d=0;d<datex.length;d++){
		var date = datex[d];
		var findValue =false;
		for (var i = 0; i < _dataList.length; i++) {
			var obj = _dataList[i];
			var dt = obj[TRADE_DT_ATTR];
			var dtVar = date.replace(new RegExp(/-/g),'');
			if(dtVar==dt){
				//查找对应属性
				var val_dataTag = obj[hkey];
				var target_value = 0;
				if(val_dataTag && !isNaN(val_dataTag)){
					target_value = parseFloat(val_dataTag);
				}
				var key = hkey;
				if(null ==key || "" ==key){
					continue;
				}
				findValue=true;
				var index = arr.length;
				arr[index] = target_value;
			}
			
		}
		if(!findValue){
			var index = arr.length;
			arr[index] = 0;
		}
	}
	return arr;
};


/**
 * 加载单个基金数据
 * **/
var loadFundDetailJsonData=function(fundJson,datex,wdtype){
	var _dataMapList = [];
	var _dataList = [];
	if(fundJson)
		_dataList=fundJson;
	var _hashmapColumn = new HashMap();
	var _hashmapSpline = new HashMap();
	var _hashmap = new HashMap();
	var dataTagSet=null;
	
	if(wdtype==1){//销量数据
		dataTagSet=overallFundTypeTagNameDataMap.keySets();
	}else if(wdtype==2){//收入数据
		dataTagSet=incomeFundTypeTagNameDataMap.keySets();
	}
	for (var i = 0; i < _dataList.length; i++) {
		var obj = _dataList[i];
		//所有需要展示的数据属性
		for(var d=0;d<dataTagSet.length;d++){
			//查找每一个属性
			var dattr = dataTagSet[d];
			var val_dataTag = obj[dattr];
			var target_value = 0;
			if(val_dataTag && !isNaN(val_dataTag)){
				target_value = parseFloat(val_dataTag);
			}
			var key = dattr;
			if(null ==key || "" ==key){
				continue;
			}
			var value = _hashmap.get(key);
			if (null == value) {
				var arr = [];
				arr[0] = target_value;
				_hashmap.put(key, arr);
			} else {
				var index = value.length;
				value[index] = target_value;
				_hashmap.put(key, value);
			}
		}
	}
	if (!_hashmap.isEmpty()) {
		var keySets = _hashmap.keySets();
		for (var k = 0; k < keySets.length; k++) {
			var hkey = keySets[k];
			var value = _hashmap.get(hkey);
			if(null ==hkey || "" ==hkey){
				continue;
			}
			//判断数据缺少
			var vLen = value.length;
			var dLen = datex.length;
			if(vLen<=dLen-1){//缺少数据
				var fillValue = fillFundData(fundJson, datex, hkey);
				value = fillValue;
			}
			var type = null;
			
			var name=null;
			if(wdtype==1){//销量数据
				name=overallFundTypeTagNameDataMap.get(hkey);
				type=overallFundTypeTagNameDataTypeMap.get(hkey);
			}else if(wdtype==2){//收入数据
				name=incomeFundTypeTagNameDataMap.get(hkey);
				type=incomeFundTypeTagNameDataTypeMap.get(hkey);
			}
			var data=value.slice();
			if(type=="column")
				_hashmapColumn.put(name,data);
			else if(type=="spline")
				_hashmapSpline.put(name,data);
		}
	}
	_dataMapList[0]=_hashmapColumn;
	_dataMapList[1]=_hashmapSpline;
	return _dataMapList;
	
};

/**
 * 加载基金公司数据
 * */
var loadFundCompanyDetailJsonData = function(fundCompanyJson, datex, wdtype){
	return loadFundDetailJsonData(fundCompanyJson, datex, wdtype);
};

(function(){
	
})();