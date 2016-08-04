/**
 * 数据报表 工具 依赖 HashMap.js
 * @author qiankun.li
 * @date 2015-6-24
 */

/*
drkh			当日开户人数
drscbk			当日首次绑卡人数
drscyk			当日首次验卡人数
drscjq			当日首次鉴权人数
drkhdrbk		当日开户当日绑卡人数
drkhdryk		当日开户当日验卡人数
drkhdrjq		当日开户当日鉴权人数
drxzjyrs		当日新增交易人数
drxzjyje		当日新增交易金额
drkhdrjybs		当日开户当日交易笔数
drkhdrjyje		当日开户当日交易金额
drxdbs			当日下单笔数
drxdje			当日下单金额
drqrjycjbs		当日确认交易的成交笔数
drqrjycjje		当日确认交易的成交金额
drkhdrjyrs		当日开户当日交易人数
drxdrs			当日下单人数
drqrjycjrs		当日确认交易的成交人数*/
/**对应指标的报表标题Map*/
var titleMap = new HashMap();
titleMap.put("drkh","当日开户人数");
titleMap.put("drscbk","当日首次绑卡人数");
titleMap.put("drscyk","当日首次验卡人数");
titleMap.put("drscjq","当日首次鉴权人数");
titleMap.put("drkhdrbk","当日开户绑卡人数");
titleMap.put("drkhdryk","当日开户验卡人数");
titleMap.put("drkhdrjq","当日开户鉴权人数");
titleMap.put("drxzjyrs","当日新增交易人数");
titleMap.put("drxzjyje","当日新增交易金额");
titleMap.put("drkhdrjyje","当日开户当日交易金额");
titleMap.put("drxdbs","当日下单笔数");
titleMap.put("drxdje","当日下单金额");
titleMap.put("drqrjycjbs","当日确认交易的成交笔数");
titleMap.put("drqrjycjje","当日确认交易的成交金额");
titleMap.put("drkhbkl","当日开户绑卡率");
titleMap.put("drkhykl","当日开户验卡率");
titleMap.put("drkhjql","当日开户鉴权率");
titleMap.put("drkhdrjyrs","当日开户当日交易人数");
titleMap.put("drxdrs","当日下单人数");
titleMap.put("drqrjycjrs","当日确认交易的成交人数");
titleMap.put("rjxdje","人均下单金额");
titleMap.put("rjcjje","人均成交金额");
titleMap.put("drkhjyl","当日开户交易率");



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

//1-算术加和指标（非交易指标）:当日开户人数,当日首次绑卡人数,当日首次验卡人数,当日首次鉴权人数,当日开户绑卡人数,当日开户验卡人数,当日开户鉴权人数
var quota_can_plused_no_trade=["drkh","drscbk","drscyk","drscjq","drkhdrbk","drkhdryk","drkhdrjq"];

//2-算术加和（交易指标）:当日新增交易人数,当日新增交易金额,当日开户当日交易金额,当日下单笔数,当日下单金额,当日确认交易的成交笔数,当日确认交易的成交金额,当日支付笔数,当日支付金额
var quota_can_plused_trade=["drxzjyrs","drxzjyje","drkhdrjyje","drxdbs","drxdje","drqrjycjbs","drqrjycjje","drzfbs","drzfje"];

//3-非算术加和指标（非交易类）:当日开户绑卡率,当日开户验卡率,当日开户鉴权率 ,当日开户交易率
var quota_nocan_plused_no_trade=["drkhbkl","drkhykl","drkhjql","drkhjyl"];
//各种率的指标
var quota_nocan_plused_no_trade_z=["drkhzbkl","drkhzykl","drkhzjql","drkhzjyl"];

//4-非算术加和指标（交易类）:当日开户当日交易人数,当日下单人数,当日确认交易的成交人数,人均下单金额,人均成交金额,人均支付金额,当日支付人数
var quota_nocan_plused_trade=["drkhdrjyrs","drxdrs","drqrjycjrs","rjxdje","rjcjje","rjzfje","drzfrs"];

// 特殊处理的指标集合(半可算术加和指标)
var half_plused_wd=new HashMap();
//开户维度:当日开户当日交易人数,当日下单人数,当日确认交易的成交人数
half_plused_wd.put("1",["drkhdrjyrs","drxdrs","drqrjycjrs"]);
//交易维度
half_plused_wd.put("2",[]);
//需要 保留两位小数的指标：人均下单金额、人均成交金额,人均支付金额
var _float_quota_array=["rjxdje","rjcjje","rjzfje"];

//开户指标
var _open_acc_quoat_array = ["drkh","drscbk","drscyk","drscjq","drkhdrbk","drkhdryk","drkhdrjq","drkhbkl","drkhykl","drkhjql","drkhjyl"];
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
quota_kmap.put("drkh","zkh");
quota_kmap.put("drscbk","sczbk");
quota_kmap.put("drscyk","sczyk");
quota_kmap.put("drscjq","sczjq");
quota_kmap.put("drkhdrbk","drkhdrzbk");
quota_kmap.put("drkhdryk","drkhdrzyk");
quota_kmap.put("drkhdrjq","drkhdrzjq");
quota_kmap.put("drxzjyrs","xzjyzrs");
quota_kmap.put("drxzjyje","xzjyzje");
quota_kmap.put("drkhdrjybs","drkhdrjyzbs");
quota_kmap.put("drkhdrjyje","drkhdrjyzje");
quota_kmap.put("drxdbs","xdzbs");
quota_kmap.put("drxdje","xdzje");
quota_kmap.put("drqrjycjbs","qrjycjzbs");
quota_kmap.put("drqrjycjje","qrjycjzje");
quota_kmap.put("drkhdrjyrs","drkhdrjyzrs");
quota_kmap.put("drxdrs","xdzrs");
quota_kmap.put("drqrjycjrs","qrjycjzrs");
quota_kmap.put("drzfrs","zzfrs");
quota_kmap.put("drzfbs","zzfbs");
quota_kmap.put("drzfje","zzfje");
//人均下单金额-人均总金额
quota_kmap.put("rjxdje","rjxdzje");
//人均成交金额-人均成交总金额
quota_kmap.put("rjcjje","rjcjzje");
//当日开户绑卡率-选择的时间范围内的开户总绑卡率
quota_kmap.put("drkhbkl","drkhzbkl");
//当日开户验卡率-选择的时间范围内的开户验卡率
quota_kmap.put("drkhykl","drkhzykl");
//当日开户鉴权率-选择的时间范围内的开户鉴权率
quota_kmap.put("drkhjql","drkhzjql");
//当日开户交易率-选择的时间范围内的开户交易率
quota_kmap.put("drkhjyl","drkhzjyl");

//下单转化率-下单总转化率 TODO 未统计
quota_kmap.put("xdzhl","xdzzhl");
//成交转化率-成交总转化率 TODO 未统计
quota_kmap.put("cjzhl","cjzzhl");


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
				//判断数据缺少
				var vLen = value.length;
				var dLen = datax.length;
				if(vLen<=dLen-1){//缺少数据
					debugger;
					var fillValue = fillDataLine(data, _pro_codeTag, _pro_nameTag, dataTag, fromDate, datax, hkey);
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
}


/**
 * 填充缺失数据
 * @param data
 * @param _pro_c
 * @param _pro_nameTag
 * @param dataTag
 * @param fromDate
 * @param datax
 */
function fillDataLine(data, _pro_codeTag, _pro_nameTag, dataTag,fromDate,datax,fillKey){
	var _dataList = data.slice();
	var isC = isContains(dataTag, quota_nocan_plused_no_trade);
	//访问渠道数据使用
	var isC_fwqd = false;
	try {
		isC_fwqd = isContains(dataTag, quota_need_pluse_fwqd);
	} catch (e) {}
	
	var arr = [];
	for(var d=0;d<datax.length;d++){
		var date = datax[d];
		var findValue =false;
		for (var i = 0; i < _dataList.length; i++) {
			var obj = _dataList[i];
			var dateVar = obj["statdt"];
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
				if(isC||isC_fwqd){
					target_value*=100;
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