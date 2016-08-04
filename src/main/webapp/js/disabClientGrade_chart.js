/**
 * 穿透分析已分配客户评分js
 */
var colArray = ["zero_cnt","one_cnt","two_cnt","three_cnt","four_cnt","five_cnt"];
var colNameMap = new HashMap();
colNameMap.put("total_cnt","分数");
colNameMap.put("zero_cnt","0分");
colNameMap.put("one_cnt","1分");
colNameMap.put("two_cnt","2分");
colNameMap.put("three_cnt","3分");
colNameMap.put("four_cnt","4分");
colNameMap.put("five_cnt","5分");

function loadPieData(custTypeJsonData,custType){
	var dataObj = [];
	if(null!=custTypeJsonData && custTypeJsonData.length>=1){
		if(!custType) 
			custType= "1";//默认选项,只购买过货币基金
		for(var i=0;i<custTypeJsonData.length;i++){
			var _data=custTypeJsonData[i];
			var custtypeVar = _data.custtype;
			if(custtypeVar == custType){
				for(var c=0;c<colArray.length;c++){
					var col = colArray[c];
					var cValue = _data[col];
					var hkey =colNameMap.get(col); 
					var dObj = [hkey,cValue];
					dataObj.push(dObj);
				}
				break;
			}
		}
	}
	return dataObj;
}
var gnwjj_Outletcode = "618001999";

function loadDeptPieData(deptJsonData,outletcode){
	debugger;
	var dataObj = [];
	if(null!=deptJsonData && deptJsonData.length>=1){
		if(!outletcode) 
			outletcode = gnwjj_Outletcode;
		for(var i=0;i<deptJsonData.length;i++){
			var _data=deptJsonData[i];
			var outletcodeVar = _data.outletcode;
			if(outletcodeVar == outletcode){
				for(var c=0;c<colArray.length;c++){
					var col = colArray[c];
					var cValue = _data[col];
					var hkey =colNameMap.get(col); 
					var dObj = [hkey,cValue];
					dataObj.push(dObj);
				}
				break;
			}
		}
	}
	return dataObj;
}

/**
 * 画客户类型统计数据表
 */
function drawCustChart(custTypeJsonData,custType) {
	var pieData = loadPieData(custTypeJsonData, custType);
	// 基本设置
	$('#cust_chart').html("");
	HbCharts.loadPieChart({
		targetId: $("#cust_chart"),  //元素
		data: pieData  //数据
	});
}



/**
 * 画客户评分图表
 */
function drawCustGradeChart(deptJsonData){
	var pieData = loadDeptPieData(deptJsonData);
	// 基本设置
	$('#cust_grade_chart').html("");
	HbCharts.loadPieChart({
		targetId: $("#cust_grade_chart"),  //元素
		data: pieData  //数据
	});
}