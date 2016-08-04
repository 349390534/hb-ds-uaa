/**
 * 工具js
 */
/**
 * @param data 排序数据
 * @param order 排序 1升序，0降序
 * @param valueLable 排序字段
 * @param 是否是日期
 */
function sortTop(data,order,valueLable,isdate){
	if (null != data && typeof(data) =="object") {
		var _dataList = data;
		_dataList.sort(function(p1,p2){
			var v1 = p1[valueLable];
			var v2 = p2[valueLable];
			if(v1 ==undefined){
				if(isdate)v1="1970-01-01";
				else v1= 0;
			}
			if(v2 ==undefined){
				if(isdate)v2="1970-01-01";
				else v2= 0;
			}
			if(v2 ==undefined)v2= 0;
			var time1 = 0;
			var time2= 0;
			if(isdate){
				var d1 = v1.replace(/\-/gi,"/");	
				var d2 = v2.replace(/\-/gi,"/");
				time1= new Date(d1).getTime();
				time2 = new Date(d2).getTime();
			}
			if(0==order || "0"==order ||""==order){
				if(isdate){
					return time2-time1;
				}else{
					return v2-v1;
				}
			}else{
				if(isdate){
					return time2-time1;
				}else{
					return v2-v1;
				}
			}
		});
		return _dataList;
	}
	return data;
}
/**
 * 排序
 * @param data 日期数组
 * @param order 排序 1升序，0降序
 * @param isdate
 * @returns
 */
function sort(data,order,isdate){
	if (null != data && typeof(data) =="object") {
		var _dataList = data;
		_dataList.sort(function(p1,p2){
			var v1 = p1;
			var v2 = p2;
			var time1 = 0;
			var time2= 0;
			if(isdate){
				var d1 = v1.replace(/\-/gi,"/");	
				var d2 = v2.replace(/\-/gi,"/");
				time1= new Date(d1).getTime();
				time2 = new Date(d2).getTime();
			}
			if(1==order || "1"==order ||""==order){
				if(isdate){
					return time1-time2;
				}else{
					return v1-v2;
				}
			}else if(0==order || "0"==order ){
				if(isdate){
					return time2-time1;
				}else{
					return v2-v2;
				}
			}
		});
		return _dataList;
	}
	return data;
}


/**获取数组的最大、最小值
 * @param array
 * @param type 1：最大，2最小
 */
function getM(array,type){
	var max = 0;
	var min = Number.MAX_VALUE;
	if(array){
		for(var i=0;i<array.length;i++){
			if(type==1){
				max = Math.max(max,array[i]);
			}else if(type==2){
				min = Math.min(min,array[i]);
			}
		}
	}
	if(type==1){
		return max ;
	}else if(type==2){
		return min ;
	}
}



function getDateX(startTime,endTime){
	var datexArray = new Array();
	datexArray[0]=startTime;
	var start = new Date(startTime).getTime();
	var end = new Date(endTime).getTime();
	var interval = (end-start)/(1000*60*60*24);//转换为天数
	interval;
	datexArray[interval]=endTime;
	var  tmp = new Date();
	for(var i=1;i<interval;i++){
		tmp.setTime(start+(1000*60*60*24*i));
		datexArray[i] =tmp.format("yyyy-MM-dd");
	}
	return datexArray;
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