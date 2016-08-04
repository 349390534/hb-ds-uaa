/**对应指标的报表标题Map*/
var titleMap = new HashMap();
titleMap.put("ljkhs","总客户数");
titleMap.put("ljbks","总绑卡人数");
titleMap.put("ljyks","总验卡人数");
titleMap.put("ljjqs","总鉴权人数");
titleMap.put("ljscjys","首次交易总人数");
titleMap.put("cys","总持有人数");
titleMap.put("ljykl","总验卡率");
titleMap.put("ljjql","总鉴权率");
titleMap.put("ljjyl","总交易率");
titleMap.put("ljjqjyl","总鉴权交易率");



/**gid 对应的查询条件code*/
/*
0	日期-机构-平台-合作类型-网点-基金类型
1	日期-机构-平台-合作类型-网点
2 	日期-机构-平台-合作类型-基金类型
3	日期-机构-平台-合作类型
6 	日期-机构-平台-基金类型
7	日期-机构-平台
14  日期-机构-基金类型
15	日期-机构
30  日期-基金类型
31	日期*/
var codeTagMap = new HashMap();
codeTagMap.put(31,"discode");
codeTagMap.put(30,"discode");
codeTagMap.put(15,"tradechan");
codeTagMap.put(14,"tradechan");
codeTagMap.put(7,"hzlxcode");
codeTagMap.put(6,"hzlxcode");
codeTagMap.put(3,"outletcode");
codeTagMap.put(2,"outletcode");
codeTagMap.put(1,"fundtype");
codeTagMap.put(0,"fundtype");

var nameTagMap = new HashMap();
nameTagMap.put(31,"disname");
nameTagMap.put(30,"disname");
nameTagMap.put(15,"channame");
nameTagMap.put(14,"channame");
nameTagMap.put(7,"hzlx");
nameTagMap.put(6,"hzlx");
nameTagMap.put(3,"outletname");
nameTagMap.put(2,"outletname");
nameTagMap.put(1,"fundtypename");
nameTagMap.put(0,"fundtypename");

//1-算术加和指标（非交易指标）:开户数，验卡数，鉴权数，持有数，绑卡数
var quota_can_plused_no_trade=["ljkhs","ljyks","ljjqs","cys","ljbks"];

//2-算术加和（交易指标）:首次交易总数
var quota_can_plused_trade=["ljscjys"];

//3-非算术加和指标（非交易类）:总验卡率，总鉴权率，总交易率，总鉴权交易率
var quota_nocan_plused_no_trade=["ljykl","ljjql","ljjyl","ljjqjyl"];
//各种率的指标
var quota_nocan_plused_no_trade_z=["ljykl","ljjql","ljjyl","ljjqjyl"];

//4-非算术加和指标（交易类）
var quota_nocan_plused_trade=[];

// 特殊处理的指标集合(半可算术加和指标)
var half_plused_wd=new HashMap();

/**
 * 判断是否属于半累计指标
 * @param wds 1开户，2交易
 * @param _quota 指标
 * @return true/false
 */
function isSpecial(wd,_quota){
	if(null==wd || ""==wd || null == _quota||_quota==""){
		return false;
	}
	var _quotaArray = half_plused_wd.get(wd);
	for(var i=0;i<_quotaArray.length;i++){
		var qa =_quotaArray[i];
		if(_quota==qa){
			return true;
		}
	}
	return false;
	
}


/**
 * 判断某个对象是否包含在某个数组中
 * @param _quota
 * @param _quotaArray
 * @returns
 */
function isContains(_quota,_quotaArray){
	if(null ==_quotaArray ||typeof (_quotaArray)!="object" || _quotaArray.length==0){
		return false;
	}
	for(var i=0;i<_quotaArray.length;i++){
		var q = _quotaArray[i];
		if(q==_quota){
			return true;
		}
	}
	return false;
}

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
function judgeQuotaType(quota){
	for(var i=0;i<quota_can_plused_no_trade.length;i++){
		var q = quota_can_plused_no_trade[i];
		if(q==quota){
			return 1;
		}
	}
	for(var i=0;i<quota_can_plused_trade.length;i++){
		var q = quota_can_plused_trade[i];
		if(q==quota){
			return 2;
		}
	}
	for(var i=0;i<quota_nocan_plused_no_trade.length;i++){
		var q = quota_nocan_plused_no_trade[i];
		if(q==quota){
			return 3;
		}
	}
	for(var i=0;i<quota_nocan_plused_trade.length;i++){
		var q = quota_nocan_plused_trade[i];
		if(q==quota){
			return 4;
		}
	}
	
	return -1;
}


var quota_kmap =new HashMap();
quota_kmap.put("ljkhs","ljzkhs");
quota_kmap.put("ljyks","ljzyks");
quota_kmap.put("ljjqs","ljzjqs");
quota_kmap.put("ljbks","ljzbks");
quota_kmap.put("ljscjys","ljscjyzs");
quota_kmap.put("cys","zcys");
quota_kmap.put("ljykl","ljzykl");
quota_kmap.put("ljjyl","ljzjyl");
quota_kmap.put("ljjql","ljzjql");
quota_kmap.put("ljjqjyl","ljzjqjyl");


var chart_map =new HashMap();
//-------分布图----------
//饼图指标集合
var _quota_pie_Array = [];
_quota_pie_Array.push(quota_can_plused_no_trade);
_quota_pie_Array.push(quota_can_plused_trade);
chart_map.put("pie",_quota_pie_Array);

//柱状图指标集合
var _quota_col_Array = [];
_quota_col_Array.push(quota_nocan_plused_no_trade);
_quota_col_Array.push(quota_nocan_plused_trade);
chart_map.put("col",_quota_col_Array);

//-------趋势图----------
var _quota_line_Array = [];
//走势图指标集合
_quota_line_Array.push(quota_can_plused_no_trade);
_quota_line_Array.push(quota_can_plused_trade);
_quota_line_Array.push(quota_nocan_plused_no_trade);
_quota_line_Array.push(quota_nocan_plused_trade);
chart_map.put("line",_quota_line_Array);

var distributionChart_key = new HashMap();
distributionChart_key.put("pie","pie");
distributionChart_key.put("col","col");

var lineChart_key = new HashMap();
lineChart_key.put("line","line");

/**
 * 获取分布图的类型,不包含的指标，返回null
 * @param _quota 指标名称
 * @returns pie-饼图,col-柱状图
 */

function getDistributionChart(_quota){
	return getchart(distributionChart_key,_quota);
}

/**
 * 获取走势图的类型,不包含的指标，返回null
 * @param _quota 指标名称
 * @returns line
 */
function getLineChart(_quota){
	return getchart(lineChart_key,_quota);
}


function getchart(chart_key,_quota){
	var sets=this.chart_map.keySets();
	for(var i=0;i<sets.length;i++){
		var key = sets[i];
		if(null!=chart_key.get(key)){
			var qa =chart_map.get(key);
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


/**
 * 获取线性走势图数据
 * demo:
[
{name: '基金1', data: [7.0, 6.9]}, 
{name: '基金2', data: [0.2, 0.8]}
]
 */
function loadLineChartDataY(data, _pro_codeTag, _pro_nameTag, dataTag,fromDate,datax) {
	var dataObj = [];
	if (null != data) {
		var _dataList = data;
		var _hashmap = new HashMap();
		var isC = isContains(dataTag, quota_nocan_plused_no_trade);
		//访问渠道数据使用
		var isC_fwqd = false;
		try {
			isC_fwqd = isContains(dataTag, quota_need_pluse_fwqd);
		} catch (e) {}
		for (var i = 0; i < _dataList.length; i++) {
			var obj = _dataList[i];
			var val_code_tag = obj[_pro_codeTag];
			var val_name_tag = obj[_pro_nameTag];
			var val_dataTag = obj[dataTag];
			var target_value = 0;
			if(""!=val_dataTag && !isNaN(val_dataTag)){
				target_value = parseFloat(val_dataTag);
			}
			if(isC||isC_fwqd){
				target_value*=100;
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
}


/**
 * 柱状图X轴数据
 * demo:['2012-12-31', '2013-06-30', '2013-12-31', '2014-06-30']
 */
function loadColumnCategories(data, _pro_codeTag, _pro_nameTag){
	var categories = [];
	if(null!=data){
		var _dataList = data;
		var _hashmap = new HashMap();
		for (var i = 0; i < _dataList.length; i++) {
			var obj = _dataList[i];
			var val_code_tag = obj[_pro_codeTag];
			var val_name_tag = obj[_pro_nameTag];
			var category = val_name_tag == null ? val_code_tag : val_name_tag;
			if(null ==category || "" ==category){
				continue;
			}
			_hashmap.put(category, category);
		}
		if (!_hashmap.isEmpty()) {
			var keySets = _hashmap.keySets();
			for (var k = 0; k < keySets.length; k++) {
				var hkey = keySets[k];
				var value = _hashmap.get(hkey);
				if(null ==hkey || "" ==hkey){
					continue;
				}
				categories.push(value);
			}
		}
	}
	return categories;
}
/**
 * 获取柱状图数据
 */
function loadColumnChartData(data, _pro_codeTag, _pro_nameTag, dataTag) {
	var dataObj = [];
	if (null != data) {
		var _dataList = data;
		var _hashmap = new HashMap();
		var isC = isContains(dataTag, quota_nocan_plused_no_trade_z);
		var isC_fwqd = false;
		try {
			isC_fwqd = isContains(dataTag, quota_need_pluse_fwqd);
		} catch (e) {}
		for (var i = 0; i < _dataList.length; i++) {
			var obj = _dataList[i];
			var val_code_tag = obj[_pro_codeTag];
			var val_name_tag = obj[_pro_nameTag];
			var val_dataTag = obj[dataTag];
			var target_value = 0;
			if(""!=val_dataTag && !isNaN(val_dataTag)){
				target_value = parseFloat(val_dataTag);
			}
			if(isC||isC_fwqd){
				target_value*=100;
			}
			var key = val_name_tag == null ? val_code_tag : val_name_tag;
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
				/*value[index] = target_value;*/
				value[index-1] = value[index-1]+target_value;
				_hashmap.put(key, value);
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
				var dObj = {
					name : hkey,
					//color:'',
					data : value.slice()
				};
				dataObj.push(dObj);
			}
		}
	}
	return dataObj;
}
/**
 * 获取饼图数据
 */
function loadPieChartData(data, _pro_codeTag, _pro_nameTag, dataTag) {
	var dataObj = [];
	if (null != data) {
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



var other_key="其他";

/**
 * 饼图数据对象排序 默认降序
 * @param data 数据对象
 * @param order 排序 1升序，0降序
 */
function sortPieData(data, order){
	if (null != data && typeof(data) =="object") {
		var _dataList = data;
		_dataList.sort(function(a,b){
			if(0==order || "0"==order ||""==order){
				return b[1]-a[1];
			}else{
				return a[1]-b[1];
			}
		});
		return _dataList;
	}
	return data;
}

/**
 * 过滤饼状图数据
 * @param data
 * @param num
 */
function filterPieData(data,num){
	var dataObj = [];
	if (null != data ){
		var other_value_array=[];
		var _dataList = data;
		for (var i = 0; i < _dataList.length; i++) {
			var obj = _dataList[i];
			if(i<num){
				dataObj.push(obj);
			}else{
				var datao = obj[1];
				if(!isNaN(datao)){
					other_value_array.push(datao);
				}
			}
		}
		var sum_other_value=0;
		//计算其他之和
		for(var j=0;j<other_value_array.length;j++){
			sum_other_value +=other_value_array[j];
		}
		var otherObj = [other_key,sum_other_value];
		dataObj.push(otherObj);
	}
	return dataObj;
}


/**
 * 累加数组
 * @param dataArray
 * @returns {Number}
 */
function sumDataArray(dataArray){
	var sum_other_value=0;
	//计算其他之和
	for(var j=0;j<dataArray.length;j++){
		var data = dataArray[j];
		if(!isNaN(data)){
			sum_other_value += data;
		}
	}
	return sum_other_value;
}


/**
 * 柱状图数据对象排序
 * @param data 数据对象
 * @param order 排序 1升序，0降序
 */
function sortColData(data, order){
	var sortDataArray = [];
	if (null != data && typeof(data) =="object") {
		var dataArray = [];
		var _dataList = data;
		var hashmap =new HashMap();
		for(var i=0;i<_dataList.length;i++){
			var dataObj = _dataList[i];
			var name=dataObj.name;
			var data = dataObj.data;
			var sumData = sumDataArray(data);
			hashmap.put(name,dataObj);
			var obj = [name,sumData];
			dataArray.push(obj);
		}
		var sortData = sortPieData(dataArray, order);
		if(null!=sortData && sortData.length>=1){
			for(var j=0;j<sortData.length;j++){
				var sd = sortData[j];
				var name = sd[0];
				var locData = hashmap.get(name);
				sortDataArray.push(locData);
			}
		}
	}
	return sortDataArray;
}

/**
 * 过滤柱状图数据
 * @param data 
 * @param num 
 */
function filterColData(data,num){
	var dataObj = [];
	if (null != data ){
		var other_value_array=[];
		var _dataList = data;
		for (var i = 0; i < _dataList.length; i++) {
			var obj = _dataList[i];
			if(i<num){
				dataObj.push(obj);
			}else{
				var datao = obj.data[0];
				if(!isNaN(datao)){
					other_value_array.push(datao);
				}
			}
		}
		/*
		var sum_other_value=0;
		//计算其他之和
		for(var j=0;j<other_value_array.length;j++){
			sum_other_value +=other_value_array[j];
		}
		var sum_other_value_array=[];
		sum_other_value_array.push(sum_other_value);
		var otherObj = {name:other_key,data:sum_other_value_array};
		dataObj.push(otherObj);//取消计算其他指标，因为是非算术加和指标*/
	}
	return dataObj.reverse();
}

/**
 * 饼图数据对象排序 默认降序
 * @param data 数据对象
 * @param order 排序 1升序，0降序
 */
function sortLineData(data, order){
	return sortColData(data, order);
}

/**
 * 过滤折线图数据
 * @param data
 * @param num
 * @param xLen
 * @param dChart
 * @param dateFrom
 */
function filterLineData(data,num,xLen,dChart,dateFrom){

	var dataObj = [];
	if (null != data ){
		var other_value_array=[];
		var _dataList = data;
		for (var i = 0; i < _dataList.length; i++) {
			var obj = _dataList[i];
			if(i<num){
				dataObj.push(obj);
			}else{
				var datao = obj.data;
				if(null!=datao && datao.length>=1){
					other_value_array.push(datao);
				}
			}
		}
		//饼图计算其他之和
		if("pie"==dChart){
			var sum_other_value_array=[];
			for(var n=0;n<xLen;n++){
				sum_other_value_array[n]=0;
			}
			//计算其他之和
			for(var j=0;j<other_value_array.length;j++){
				var aobj =other_value_array[j];
				for(var m=0;m<xLen;m++){
					var d = aobj[m];
					if(undefined != d && null!=d && !isNaN(d)){
						sum_other_value_array[m] = sum_other_value_array[m]+d;
					}
				}
			}
			
			var date = new Date();
			switch(typeof dateFrom) {   
		        case "string":   
		            date = new Date(dateFrom); 
		            break;   
		        case "number":   
		            date = new Date(dateFrom);   
		            break;
		    }
			
			var otherObj = {
					name:other_key,
					data:sum_other_value_array,
					pointStart: Date.UTC(date.getFullYear(), date.getMonth(), date.getDate()),
		            pointInterval: 24 * 3600 * 1000};
			dataObj.push(otherObj);
		}
	}
	return dataObj;

}


function formatNum(num,n){
	//参数说明：num 要格式化的数字 n 保留小数位    
	num = String(num.toFixed(n));    
	var re = /(-?\d+)(\d{3})/;    
	while(re.test(num)) {
		num = num.replace(re,"$1,$2");
	}
	return num;
}