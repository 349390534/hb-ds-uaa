/**
 * 无线画图使用js
 */

/**
 * 平台代码map
 */
var wirelessAppProidMap = new HashMap();
wirelessAppProidMap.put("-1","");
/**
 * 储蓄罐proid数组
 */
var cpArray = ["2001","2002"];
wirelessAppProidMap.put("HZLX_HOWBUY_CAPP",cpArray);
/**
 * 掌基proid数组
 */
var zpArray = ["3001","3002"];
wirelessAppProidMap.put("HZLX_HOWBUY_ZAPP",zpArray);
/**
 * 平台代码 自定义key
 */
var proidCode =["2001-2002","3001-3002"];
var proidCodeNameMap =new HashMap(); 
proidCodeNameMap.put("2001-2002","储-APP");
proidCodeNameMap.put("HZLX_HOWBUY_CAPP","储-APP");
proidCodeNameMap.put("3001-3002","掌-APP");
proidCodeNameMap.put("HZLX_HOWBUY_ZAPP","掌-APP");

var yTitleMap = new HashMap();
yTitleMap.put("activateNum","次");
yTitleMap.put("openaccNum","人");
yTitleMap.put("bindcardNum","人");
yTitleMap.put("orderNum","人");



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
		dataJson24 = dataX24;
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

/**
 * 把相同时刻的相同产品不同平台的数据合并
 * @param dataJsonAll
 * @param timelable
 * @param proidpro
 * @param proidArray
 * @param compare 是否是对比数据
 * @return HashMap key:timelable，value:具有相同timelable的对象数组
 */
function combineData(dataJsonAll,timelable,proidpro,proidArray,compare){
	var _hashmap = new HashMap();
	if(null!=dataJsonAll && typeof dataJsonAll == "object"){
		for(var i =0;i<dataJsonAll.length;i++){
			var _data = dataJsonAll[i];
			var time_key = _data[timelable];//取时间
			if(null ==time_key || "" ==time_key){
				continue;
			}
			var _proid = _data[proidpro];
			var isc = contains(proidArray, _proid);
			if(!isc){
				//不属于该平台
				continue;
			}
			var value = _hashmap.get(time_key);
			if (null == value) {
				var arr = [];
				arr[0] = _data;
				_hashmap.put(time_key, arr);
			} else {
				var index = value.length;
				value[index] = _data;
				_hashmap.put(time_key, value);
			}
		}
	}
	if(!compare){//填充今天的数据
		//填充没有数据的时间段
		if(null!=dataX24 && dataX24.length>=1){
			for(var d =0;d<dataX24.length;d++){
				var _dt = dataX24[d];
				if(compareWithNow(_dt))break;
				if(null == _hashmap.get(_dt)){
					_hashmap.put(_dt,[0]);
				}
			}
		}
	}else if(compare){//填充对比日期数据
		if(null!=dataX24Compare && dataX24Compare.length>=1){
			for(var d =0;d<dataX24Compare.length;d++){
				var _dt = dataX24Compare[d];
				if(null == _hashmap.get(_dt)){
					_hashmap.put(_dt,[0]);
				}
			}
		}
		
	}
	
	return _hashmap;
}
/**
 * 从汇总好的数据中取出对应属性的数据组合
 * @param _hashmap 合并后的数据
 * @param valuelable 数据属性
 * @return array 数据数组
 * @return combine 来自合并数据，true、false 是则默认按照日期升序排序hashmap
 */
function getdataYAll(_hashmap,valuelable,reverse,combine){
	var dataObj = [];
	if (!_hashmap.isEmpty()) {
		var keySets = _hashmap.keySets();
		var _keysetVar = keySets.slice();
		if(combine){
			_keysetVar = sort(keySets.slice(), "1", true);
		}
		for (var k = 0; k < _keysetVar.length; k++) {
			var tkey = _keysetVar[k];
			var value = _hashmap.get(tkey);
			if(null ==value || value.length==0){
				continue;
			}
			var _sumValue = 0;
			for(var j=0;j<value.length;j++){
				var _v = value[j];
				var _targetValue = _v[valuelable];
				if(""!=_targetValue && !isNaN(_targetValue)){
					_sumValue+=parseInt(_targetValue);
				}
			}
			dataObj.push(_sumValue);
		}
	}
	if(reverse){
		dataObj = dataObj.reverse();
	}
	return dataObj;
}

var timelable = "createTime";
var proidpro = "proid";
/**
 * 加载根平台数据
 * @param dataJsonAll 根数据
 * @param valueLable
 */
function loadDataAllLine(dataJsonAll,valueLable,compare){
	//储APP
	var cxgData = [];
	var _hashmap_cxg = combineData(dataJsonAll, timelable, proidpro, cpArray,compare);
	cxgData =  getdataYAll(_hashmap_cxg, valueLable,false,true);
	var cxgProidName = proidCodeNameMap.get(proidCode[0]);
	//掌APP
	var zjData = [];
	var _hashmap_zj = combineData(dataJsonAll, timelable, proidpro, zpArray,compare);
	zjData =  getdataYAll(_hashmap_zj, valueLable,false,true);
	var zjProidName = proidCodeNameMap.get(proidCode[1]);
	if(compare){
		var compareDt = $("#date").val();
		cxgProidName+=":"+compareDt;
		zjProidName+=":"+compareDt;
	}
	/*
	//对没有数据的时间点填充0 
	if(compare){
		//填充全天日期数据
		var len = dataX24Compare.length;
		if(cxgData.length<len){
			//储蓄罐缺少数据
			cxgData = fillDataAll(dataX24Compare, _hashmap_cxg, valueLable, false, compare);
		}
		if(zjData.length<len){
			//掌基缺少数据
			zjData = fillDataAll(dataX24Compare, _hashmap_zj, valueLable, false, compare);
		}
	}else{
		//填充当前时间的数据
		var len = dataX24.length;
		if(cxgData.length<len){
			//储蓄罐缺少数据
			cxgData = fillDataAll(dataX24, _hashmap_cxg, valueLable, false, compare);
		}
		if(zjData.length<len){
			//掌基缺少数据
			zjData = fillDataAll(dataX24, _hashmap_zj, valueLable, false, compare);
		}
	}*/
	var cxgObj = {
			name:cxgProidName,
			data:cxgData
			};
	var zjObj = {
			name:zjProidName,
			data:zjData
		};
	var seriesData = [cxgObj,zjObj];
	return seriesData;
}

function fillDataAll(dataX24,_hashmap,valuelable,reverse,compare){
	var _resArray = [];
	if(null!=dataX24 && dataX24.length>=1){
		for(var d =0;d<dataX24.length;d++){
			var _dt = dataX24[d];
			if(!compare){
				if(compareWithNow(_dt))break;
			}
			var find = false;
			if (!_hashmap.isEmpty()) {
				var keySets = _hashmap.keySets();
				var _keysetVar = keySets.slice();
				for (var k = 0; k < _keysetVar.length; k++) {
					var tkey = _keysetVar[k];
					if(_dt==tkey){//找到对应日期的数据
						find = true;
						var value = _hashmap.get(tkey);
						if(null ==value || value.length==0){
							continue;
						}
						var _sumValue = 0;
						for(var j=0;j<value.length;j++){
							var _v = value[j];
							var _targetValue = _v[valuelable];
							if(""!=_targetValue && !isNaN(_targetValue)){
								_sumValue+=parseInt(_targetValue);
							}
						}
						_resArray.push(_sumValue);
					}
				}
			}
			if(!find){
				_resArray[_resArray.length]=0;
			}
		}
	}
	if(reverse){
		_resArray = _resArray.reverse();
	}
	return _resArray;
}

/**
 *@param dataJson 明细数据 
 *@param nameLable
 *@param valueLable
 *@param isfillter 是否过滤出对应的数据 true、false ture 根据fillterArray过滤
 *@param fillterArray
 *@param compare 是否是对比日期数据
 */
function getdataY(dataJson,namelable,valuelable,isfillter,fillterArray,compare){
	var dataObj = [];
	if(null!=dataJson && typeof dataJson == "object"){
		var _hashmap = new HashMap();
		for(var i =0;i<dataJson.length;i++){
			var _data = dataJson[i];
			var name_key = _data[namelable];
			if(null ==name_key || "" ==name_key){
				continue;
			}
			if(isfillter){
				var isf = contains(fillterArray, name_key);
				if(!isf){
					continue;//不需要的数据
				}
			}
			var proidVar = _data["proid"];
			if(cxg_AppStore_outlet==name_key){
				if(cpArray[1] ==proidVar ){
					continue;//对储蓄罐AppStore的错误网点数据过滤
				}
			}
			
			var value = _hashmap.get(name_key);
			var target_value =_data[valuelable];
			if (null == value) {
				var arr = [];
				arr[0] = target_value;
				_hashmap.put(name_key, arr);
			} else {
				var index = value.length;
				value[index] = target_value;
				_hashmap.put(name_key, value);
			}
		}
		if (!_hashmap.isEmpty()) {
			var dataLen = 0;
			if(dataX24)
				dataLen=dataX24.length;
			if(compare){
				dataLen=dataX24Compare.length;
			}
			var keySets = _hashmap.keySets();
			for (var k = 0; k < keySets.length; k++) {
				var hkey = keySets[k];
				var value = _hashmap.get(hkey);
				if(null ==hkey || "" ==hkey){
					continue;
				}
				if(dataLen > 0 && dataLen > value.length){
					//网点数据缺少
					if(!compare){
						value = fillData(dataX24, hkey, dataJson, namelable, valuelable,compare);
					}else{
						value = fillData(dataX24Compare, hkey, dataJson, namelable, valuelable,compare);
					}
				}
				var keyName= outletCodeNameMap.get(hkey);
				
				if(compare){
					var compareDt = $("#date").val();
					keyName+=":"+compareDt;
				}
				
				var dObj = {
					name : keyName,
					data : value.slice()
				};
				dataObj.push(dObj);
			}
		}
	}
	return dataObj;
}

function compareWithNow(time){
	var d1 = time.replace(/\-/gi,"/");	
	var timeVar= new Date(d1).getTime();
	var date = new Date().getTime();
	return (timeVar-date)>5000?true:false;//5秒时间缓冲
}

function fillData(dataX24,outlet,dataJson,namelable,valuelable,compare){
	var _resArray = [];
	if(null!=dataX24 && dataX24.length>=1){
		for(var d =0;d<dataX24.length;d++){
			var _dt = dataX24[d];
			if(!compare){
				if(compareWithNow(_dt))break;
			}
			var find = false;
			for(var i =0;i<dataJson.length;i++){
				var _data = dataJson[i];
				var name_key = _data[namelable];
				if(null ==name_key || "" ==name_key){
					continue;
				}
				
				if(name_key==outlet){
					var proidVar = _data["proid"];
					if(cxg_AppStore_outlet == outlet){
						if(cpArray[1] ==proidVar ){
							continue;//对储蓄罐AppStore的错误网点数据过滤
						}
					}
					
					var timeVar = _data[timelable];
					var target_value =_data[valuelable];
					if(timeVar == _dt){
						find =true;
						_resArray[_resArray.length]=target_value;
					}
				}
			}
			
			if(!find){
				_resArray[_resArray.length]=0;
			}
		}
	}
	return _resArray;
}



function loadHighChart(xAxis,seriesData,title,yTitle){
	if(!title){
		title=" ";
	}
	$("#chart").highcharts({
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
					//arr[288] = '<a style="font-size: 8px;">00:05</a>';
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
 * 画图(根平台)
 * @param dataJsonAll
 * @param title
 * @param valueLable
 */
function drawDataAll(dataJsonAll,title,valueLable){
	var seriesData =loadDataAllLine(dataJsonAll, valueLable,false);
	var compare = isCompare();
	if(compare){
		var seriesDataCompare = loadDataAllLine(jsonDataAllCompare, valueLable,true);
		seriesData = seriesData.concat(seriesDataCompare);
	}
	//var xAxis = getdataX(dataJsonAll, timelable, false);
	var xAxis = getdataX24(null, timelable, false);
	var yTitle = yTitleMap.get(valueLable);
	loadHighChart(xAxis, seriesData,"",yTitle);
}

/**
 * 按照某个值，匹配数据中相同值的数据
 * @param dataJson
 * @param valuelable
 * @param value
 */
function filterData(data,valuelable,value){
	var dataObj = [];
	if (null != data && typeof(data) =="object") {
		for(var i =0;i<data.length;i++){
			var _data =data[i];
			var v = _data[valuelable];
			if(v == value){
				dataObj.push(_data);
			}
		}
	}
	return dataObj;
}

/**
 * 过滤出前N个网点号
 * @param dataY
 * @param top
 */
function filterOutlet(data,top){
	var outlet = [];
	if (null != data && typeof(data) =="object") {
		for(var i =0;i<data.length;i++){
			if(i==top){
				break;
			}
			var _data =data[i];
			var _outletcode = _data.outletcode;
			outlet.push(_outletcode);
		}	
	}
	return outlet;
}

/**
 * 画图
 * @param dataJsonAll
 * @param dataJson
 * @param valuelable
 */
function drawLineChart(dataJsonAll,dataJson,valuelable){
	var maxTime = null;
	var _dataJsonVar = dataJson.slice();
	var dataT_s = sortTop(dataJson, "0", "createTime",true);
	if(null!=dataT_s && dataT_s.length>=1){
		maxTime = dataT_s[0].createTime;
	}
	//var dataX = getdataX(dataJson, timelable,false);
	var dataX = getdataX24(null, timelable,false);
	var dataY = [];
	//是否对比
	var compare = isCompare();
	if(!compare){
		//1:过滤出最新时间的所有数据，按照当前数据的值来排序;
		var dataYLast = filterData(dataJson, "createTime", maxTime);
		//2:根据当前时间数据大小的排序,过滤出前五条 
		var dataY_s =sortTop(dataYLast, "0", valuelable,false);
		var top = 5;
		//3:得出前N个网点
		var outletArray = filterOutlet(dataY_s, top);
		dataY = getdataY(_dataJsonVar, "outletcode", valuelable,true,outletArray,false);
	}
	var pArray = [];
	var li = $(".fwqd ul li.current:eq(0)");
	var code = li.attr("code");
	pArray  = wirelessAppProidMap.get(code);
	var _hashmap = combineData(dataJsonAll, timelable, proidpro, pArray,false);
	var dataYAll = getdataYAll(_hashmap, valuelable, false,true);
	var proidName = proidCodeNameMap.get(code);
	var dataAllObj ={
		name:proidName,
		data:dataYAll
	};
	var data = dataY.concat([dataAllObj]);
	if(compare){
		var _hashmap_compare = combineData(jsonDataAllCompare, timelable, proidpro, pArray,true);
		var dataYAll_compare = getdataYAll(_hashmap_compare, valuelable, false,true);
		var dataAllObj_compare ={
			name:proidName +":"+$("#date").val(),
			data:dataYAll_compare
		};
		data = data.concat([dataAllObj_compare]);
	}
	
	var yTitle = yTitleMap.get(valuelable);
	loadHighChart(dataX, data,"",yTitle);
}

/**
 * 画图 （网点）
 * @param dataJson
 * @param valuelable
 */
function drawOutletChart(dataJson,valuelable){
	//var dataX = getdataX(dataJson, timelable,false);
	var dataX = getdataX24(null, timelable,false);
	var dataY = getdataY(dataJson, "outletcode", valuelable,false,null,false);
	var compare = isCompare();
	if(compare){
		var dataYCompare = getdataY(dataJsonCompare, "outletcode", valuelable,false,null,true);
		dataY = dataY.concat(dataYCompare);
	}
	var yTitle = yTitleMap.get(valuelable);
	loadHighChart(dataX, dataY,"",yTitle);
}

/**
 * tab切换事件
 */
function bindTabToggle(jsonDataAll,jsonData,contextPath){
	// 分布图/趋势图 tab切换
	$(".chartbox_tab ul#data_tab li:first-child").addClass("current");
	$(".chartbox_tab ul#data_tab").find("li").click(function() {
		var _this = $(this);
		_this.addClass("current").siblings("li").removeClass("current");
		var  tag  = _this.attr("tag");
		graphReq(tag,jsonDataAll, jsonData, contextPath);
	});
}

/**
 * 是否显示查看数据明细按钮
 */
function showDatadeatail(){
	var li = $(".fwqd ul li.current:eq(0)");
	var code = li.attr("code");
	if(code!="" && code!="-1"){
		$("#show_datadeatail").show();
	}else{
		$("#show_datadeatail").hide();
	}
}

/**
 * 明细数据事件绑定
 */
function bindMxEvent(){
	// 查看明细
	$('.ckmx').on('click', function() {
		$(".sjmx").show();
	});
	$(".close_btn").click(function() {
		$(".sjmx").hide();
	});
}

/**
 * 是否对比
 * @returns
 */
function isCompare(){
	return $("#dbrq").is(":checked");
}

//储蓄罐AppStore网点
var cxg_AppStore_outlet = "A20140301";
/**
 * 提交查询
 */
function submitData(contextPath){
	var li = $(".fwqd ul li.current:eq(0)");
	var code = li.attr("code");
	//平台
	var pc  = wirelessAppProidMap.get(code);
	var outletOp = $("select#outlet option:selected");
	//网点
	var outlet = outletOp.val();
	var params = "";//proid、outletcode
	if(pc=="" && outlet==""){
		params="outletcode=-1";
	}else if(null!=pc && pc!="" && outlet==""){
		//查询单个平台
		var ps = pc.join(",");
		params="proid="+ps+"&outletcode=-1";
	}else if(null!=pc && pc!="" && outlet!=""){
		//查询平台下的网点数据
		var ps = pc.join(",");
		if(cxg_AppStore_outlet==outlet){
			//特殊处理
			ps = ["2001"];
		}
		params="proid="+ps+"&outletcode="+outlet;
	}
	
	var compare = isCompare();
	var compareDate = null;
	if(compare){
		compareDate = $("#date").val();
		params+="&compare=1&compare_date="+compareDate;
	}
	var reqObj = {
			url:contextPath + '/auth/wa/graph.htm',
	        postMethod:'POST',
	        params:params,
	        session_out:false,
	        callback:function(data){
	        	$('#html_data').html(data);
	        	$("#search_data").removeAttr("disabled");
	        }
			
	};
	ajaxRequest(reqObj);
	$("#search_data").attr("disabled","disabled");
	//修改显示条件
	var a = li.find("a");
	var html = a.html();
	if(outlet!=""){
		html+=" - "+outletOp.text();
	}
	$("pt#proid_name").html(html);
}

/**
 * 画图
 * @param jsonDataAll
 * @param jsonData
 * @param contextPath
 */
function graphReq(valueLable,jsonDataAll,dataJson){
	var li = $(".fwqd ul li.current:eq(0)");
	var code = li.attr("code");
	//平台
	var pc  = wirelessAppProidMap.get(code);
	var outletOp = $("select#outlet option:selected");
	//网点
	var outlet = outletOp.val();
	var _valuelableVar = "activateNum";
	if(valueLable){
		_valuelableVar = valueLable;
	}
	if(pc=="" && outlet==""){//选全部平台
		drawDataAll(jsonDataAll," ",_valuelableVar);
	}else if(pc!="" && outlet==""){//选择单个平台
		drawLineChart(jsonDataAll, dataJson, _valuelableVar);
	}else if(pc!="" && outlet!=""){//选中平台下的某个网点
		drawOutletChart(dataJson, _valuelableVar);
	}
}

/**
 *下载明细 
 */
function downloadReq(contextPath){
	var li = $(".fwqd ul li.current:eq(0)");
	var code = li.attr("code");
	//平台
	var pc  = wirelessAppProidMap.get(code);
	var outletOp = $("select#outlet option:selected");
	//网点
	var outlet = outletOp.val();
	var params = "";//proid、outletcode
	var currentParam = "";
	var proidName = li.find("a").text();
	if(pc!="" && outlet==""){
		//查询单个平台
		var ps = pc.join(",");
		params="proid="+ps+"&outletcode=-1";
		currentParam = "&currentParam="+proidName;
	}else if(pc!="" && outlet!=""){
		//查询平台下的网点数据
		var ps = pc.join(",");
		params="proid="+ps+"&outletcode="+outlet;
		currentParam = "&currentParam="+proidName+"-"+outletOp.text();
	}
	params+=currentParam;
	var compare = isCompare();
	var compareDate = null;
	if(compare){
		compareDate = $("#date").val();
		params+="&compare=1&compare_date="+compareDate;
	}
	var url = contextPath + '/auth/wa/download.htm?';
	url += params;
	window.open(url);
	
}
function bindDownload(contextPath){
	$("#export_data").click(function(){
		downloadReq(contextPath);
	});
}

/**
 * 自动刷新页面
 */
var auto_query=false;
var _current_li_tag=null;
function autoRefeshData(contextPath){
	var date = new Date();
	var m = date.getMinutes();
	if(m%5==0){
		var sec= date.getSeconds(); //秒
		if(!auto_query && sec>=10 ){
			var _current_li = $(".chartbox_tab ul#data_tab").find("li.current");
			_current_li_tag= _current_li.attr("tag");
			submitData(contextPath);
			auto_query = true;
		}
	}else{
		auto_query = false;
	}
}
