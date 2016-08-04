
/**
 * 获取饼图数据
 */
function loadPieChartData(data,dataTag) {
	var dataObj = [];
	if (null != data) {
		var _pro_codeTag="channel";
		var _pro_nameTag="channelName";
		var _dataList = data;
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
			if(null ==key || "" ==key){
				continue;
			}
			var v = _hashmap.get(key);
			if(v!=null){
				target_value +=v;
			}
			_hashmap.put(key,target_value);
		}
		
		if (!_hashmap.isEmpty()) {
			var keySets = _hashmap.keySets();
			for (var k = 0; k < keySets.length; k++) {
				var hkey = keySets[k];
				var value = _hashmap.get(hkey);
				if(null ==hkey || "" ==hkey){
					continue;
				}
				var dObj = [hkey,value];
				dataObj.push(dObj);
			}
		}
	}
	return dataObj;
}


/**
 * 画饼图
 * @param jsonData
 */
function drawPie(jsonData,dataTag){
	var pieData = loadPieChartData(jsonData,dataTag);
	HbCharts.loadPieChart({
		targetId: $("#pie_chart"),  //元素
		data: pieData  //数据
	});
}

function loadHighChart(xAxis,seriesData,title,yTitle){
	if(!title){
		title=" ";
	}
	$("#line_chart").highcharts({
		credits : {
			enabled : false
		},
		title : {
			text : title,
			x : -20
		//center
		},
		xAxis : {
			//type:"datetime",
			min: 0,
			max: 288,
			useHTML: true,
			tickInterval:24 ,
			labels: {
				formatter: function () {
					var arr = [];
					for(var i=0;i<xAxis.length;i++){
						arr[i] = '<a style="font-size: 8px;">'+xAxis[i]+'</a>';
					}
					return arr[this.value];
				}
			}/*,
			categories : xAxis*/
		},
		yAxis : {
			title : {
				text : yTitle
			},
			labels: {
				formatter: function () {
					return this.value;
				}
			},
			min: 0,
            startOnTick: false,
			plotLines : [ {
				value : 0,
				width : 1,
				color : '#808080'
			} ]
		},
		tooltip: {
			formatter:function(){
				return xAxis[this.x] +"<br>"+this.series.name+" "+this.y;
			},
			valueSuffix : '人',
			followTouchMove: true
		},
		/* legend: {
		     layout: 'vertical',
		     align: 'right',
		     verticalAlign: 'middle',
		     borderWidth: 0
		 },*/
		series : seriesData
	});
}

/**
 * 加载折线图
 * @param ele
 * @param data
 * @param attrMap 属性对象Map
 */
function loadLineChart(data) {
	var valueSuffix = "";
	
	$("#line_chart").highcharts(
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
								+ this.y + valueSuffix;
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
				series : data
			});

}


/**
 * 获取X轴数据
 * @param dataJson
 */
function getdataX(dataJson,lable,reverse){
	var dataX = [];
	var _hashMap =new HashMap();
	if(null!=dataJson && typeof dataJson == "object"){
		for(var i =0;i<dataJson.length;i++){
			var _data = dataJson[i];
			var cx = _data[lable];
			var d =_hashMap.get(cx);
			if(d==null){
				_hashMap.put(cx,cx);
				var t = cx.substr(10).trim().substr(0,5);
				dataX.push(t);
			}
		}
	}
	if(reverse){
		dataX = dataX.reverse();
	}
	return dataX;
}
function getdataX24(dataJson24,lable,reverse){
	if(!dataJson24){
		dataJson24 = _dataX24Json;
	}
	var dataX = [];
	if(null!=dataJson24 && typeof dataJson == "object"){
		for(var i =0;i<dataJson24.length;i++){
			var _data = dataJson24[i];
			if(_data!=null){
				var t = _data.substr(10).trim().substr(0,5);
				dataX.push(t);
			}
		}
	}
	if(reverse){
		dataX = dataX.reverse();
	}
	return dataX;
}

function contains(array,value){
	if(null!=array){
		for(var i=0;i<array.length;i++){
			var _v = array[i];
			if(_v==value){
				return true;
			}
		}
	}
	
	return false;
}

function getTagDataMap(_dataList,dataTag){
	var _hashmap = new HashMap();
	var _pro_codeTag="channel";
	var _pro_nameTag="channelName";
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
	return _hashmap;
	
}
/**
 * 画图按分钟
 * @param dataJsonAll
 * @param dataJson
 * @param valuelable
 */
function drawLineChartActivity(dataJson,dataJsonCompare,dataTag){
	var dataX = getdataX24(null, timelable,false);
	var dataY = [];
	var _dataList = dataJson.slice();
	var _hashmap =getTagDataMap(_dataList, dataTag);
	if (!_hashmap.isEmpty()) {
		var keySets = _hashmap.keySets();
		for (var k = 0; k < keySets.length; k++) {
			var hkey = keySets[k];
			var value = _hashmap.get(hkey);
			if(null ==hkey || "" ==hkey){
				continue;
			}
			// 判断数据缺少
			var dLen = getTimeLenToNow();
			if(value.length<=dLen-1){
				//填充数据
				value  = fillDataLine(dataJson,dataTag, hkey);
			}
			var dObj = {
				name : hkey,
				data : value.slice()
			};
			dataY.push(dObj);
		}
	}
	if(isCompareChecked()){
		var dataYComp = getCompareData(dataJsonCompare, dataTag);
		dataY=dataY.concat(dataYComp);
	}
	loadHighChart(dataX, dataY,"","");
}
/**
 * 画图按天
 * @param dataJson
 * @param dataTag
 * @param fromDate
 * @param datax
 */
function drawLineChartActivityByDay(dataJson,dataTag,fromDate,datax){
	var date = new Date();
	switch(typeof fromDate) {   
        case "string":   
            date = new Date(fromDate);   
            break;   
        case "number":   
            date = new Date(fromDate);   
            break;
    }
	var dataY = [];
	var _dataList = dataJson.slice();
	var _hashmap =getTagDataMap(_dataList, dataTag);
	if (!_hashmap.isEmpty()) {
		var keySets = _hashmap.keySets();
		for (var k = 0; k < keySets.length; k++) {
			var hkey = keySets[k];
			var value = _hashmap.get(hkey);
			if(null ==hkey || "" ==hkey){
				continue;
			}
			// 判断数据缺少
			var dLen = datax;
			if(value.length<=dLen-1){
				//填充数据
				value  = [];//fillDataLine(dataJson,dataTag, hkey); TODO 填充缺失数据
			}
			var dObj = {
					name : hkey,
					data : value.slice(),
					pointStart: Date.UTC(date.getFullYear(), date.getMonth(), date.getDate()),
		            pointInterval: 24 * 3600 * 1000
			};
			dataY.push(dObj);
		}
	}
	loadLineChart(dataY);
}


function getCompareData(dataJsonCompare,dataTag){
	var dataY = [];
	if(!dataJsonCompare) return dataY;
	var _dataList = dataJsonCompare.slice();
	var _hashmap  = getTagDataMap(_dataList, dataTag);
	if (!_hashmap.isEmpty()) {
		var keySets = _hashmap.keySets();
		for (var k = 0; k < keySets.length; k++) {
			var hkey = keySets[k];
			var value = _hashmap.get(hkey);
			if(null ==hkey || "" ==hkey){
				continue;
			}
			var compareDate = $("#date").val(); 
			// 判断数据缺少
			var dLen = 288;
			if(value.length<=dLen-1){
				//填充数据
				value  = fillDataLineComp(_dataList,dataTag, hkey);
			}
			var dObj = {
				name : hkey+":"+compareDate,
				data : value.slice()
			};
			dataY.push(dObj);
		}
	}
	return dataY;
}

/**
 * 获取截止到当前时间点的五分钟个数
 * @returns {Number}
 */
function getTimeLenToNow(){
	var _date24 = _dataX24Json;
	var now = new Date();
	var time = $("#time").html();
	var tt = time.split(":");
	now.setHours(parseInt(tt[0]));
	now.setMinutes(parseInt(tt[1]));
	now.setSeconds(0);
	var h = now.getHours();
	var min = now.getMinutes();
	var dlen = 0;
	for(var i =0;i<_date24.length;i++){
		var _date = _date24[i];
		if(_date){
			var t = new Date(_date);
			var ht = t.getHours();
			var mint = t.getMinutes();
			if(h==ht && min==mint){
				dlen++;
				break;
			}else{
				dlen++;
			}
		}
	}
	return dlen;
}

function fillDataLine(dataJson,dataTag,hkey){
	var _date24 = _dataX24Json;
	var now = new Date();
	var time = $("#time").html();
	var tt = time.split(":");
	now.setHours(parseInt(tt[0]));
	now.setMinutes(parseInt(tt[1]));
	now.setSeconds(0);
	var arr = new Array();
	for(var i =0;i<_date24.length;i++){
		var _date = _date24[i];
		var _pro_codeTag="channel";
		var _pro_nameTag="channelName";
		var findV = false;
		if(new Date(_date)>now){
			break;
		}
		for (var j = 0; j < dataJson.length; j++) {
			var obj = dataJson[j];
			var dateVar = new Date(obj["statdt"]);
			var dateR = new Date(_date);
			if(dateVar.getTime()!=dateR.getTime()){
				continue;
			}
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
			if(hkey!=key)continue;
			findV =true;
			var index = arr.length;
			arr[index] = target_value;
		}
		if(!findV)arr[arr.length] = 0;
	}
	return arr;
}

/**
 * 对比数据填充
 * @param dataJson
 * @param dataTag
 * @param hkey
 * @returns {Array}
 */
function fillDataLineComp(dataJson,dataTag,hkey){
	var _date24 = _dataX24JsonComp;
	var arr = new Array();
	for(var i =0;i<_date24.length;i++){
		var _date = _date24[i];
		var _pro_codeTag="channel";
		var _pro_nameTag="channelName";
		var findV = false;
		for (var j = 0; j < dataJson.length; j++) {
			var obj = dataJson[j];
			var dateVar = new Date(obj["statdt"]);
			var dateR = new Date(_date);
			if(dateVar.getTime()!=dateR.getTime()){
				continue;
			}
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
			if(hkey!=key)continue;
			findV =true;
			var index = arr.length;
			arr[index] = target_value;
		}
		if(!findV)arr[arr.length] = 0;
	}
	return arr;
}

