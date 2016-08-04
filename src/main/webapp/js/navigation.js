/**
 * 导航页js
 */

function bindClick(ele){
	var e = $(ele);
	if(e.length==0){
		return;
	}
	e.click(function(){
		var _this = $(this);
		var url = _this.attr("url");
		var navId = _this.attr("permission_id");
		var pageId = _this.attr("pageid");
		loadMainData(url, null, function(){
			$(".header .menu li[menuid='"+navId+"']").addClass("current").siblings().removeClass("current");
			if("0001"!=navId){
				var html = navMap.get(navId);
				if (html) {
					$(".sidenav").html(html).show();
					//添加左侧菜单点击事件
					eventMenuLeftLi();
					
					$(".sidenav").find("ul li").each(function(index, obj) {
						var _thisli = $(obj);
						var urlVar = _thisli.find("a").attr("url");
						var refid = _thisli.attr("refid");
						if(refid==pageId){
							if (urlVar && urlVar.indexOf(_void0) == -1) {
								// 寻找到菜单菜单
								_thisli.addClass("current");
								return false;
							}
						}
					});
				}
			}
		});
		
	});
}

/**
 * 请求公募数据
 * @param context_path
 */
function reqGmData(context_path){
	var reqObj={
			url:context_path + "/navigation/ajaxGetFundData.htm",
            postMethod:'POST',
            params:Math.random(),
            callback:function(data){
               	$("#gmdata").html(data);
            }
        };
	ajaxRequest(reqObj);
}
/**
 * 请求公募数据
 * @param context_path
 */
function reqWebSiteData(context_path){
	var reqObj={
			url:context_path + "/navigation/ajaxWebSiteGraph.htm",
			postMethod:'POST',
			params:Math.random(),
			callback:function(data){
				$("#websitedata").html(data);
			}
	};
	ajaxRequest(reqObj);
}
/**
 * 请求穿透数据
 * @param context_path
 */
function reqCtData(context_path){
	var reqObj={
			url:context_path + "/navigation/ajaxPenetrateData.htm",
			postMethod:'POST',
			params:Math.random(),
			callback:function(data){
				$("#ctdata").html(data);
			}
	};
	ajaxRequest(reqObj);
}

var codeNameMap = new HashMap();
codeNameMap.put("drkh","当日开户");
codeNameMap.put("drxzjyrs","当日新增交易人数");

function loadGmDataY(jsonData,quota){
	try {
		if (jsonData) {
			var name = codeNameMap.get(quota);
			var dataArray = [];
			for (var i = 0; i < jsonData.length; i++) {
				var _data = jsonData[i];
				var _dataValue = _data[quota];
				dataArray.push(_dataValue);
			}
			var dataObj = {
				name : name,
				data : dataArray
			};
			return [ dataObj ];
		}
	} catch (e) {
		console.log(e.message);
	}
	return null;
}

function drawGmChart(ele,xData,yData){
    $(ele).highcharts({
        title: {
            text: ' '
        },
        credits:{
        	enabled: false
        },
        xAxis: {
            categories: xData,
           /* type : 'datetime',
			dateTimeLabelFormats : {
				second : '',
				minute : '',
				hour : '',
				day : '%y-%m',
				week : '%y-%m-%d',
				month : '%Y-%m',
				year : '%Y'
			},*/
            labels : {
				formatter : function() {
					if(typeof this.value =="string"){
						return Highcharts.dateFormat('%m-%d', new Date(this.value));
					}else{
						return this.value;
					}
				}
			} 
        },
        yAxis: {
            title: {
                text: ''
            },
            plotLines: [{
                value: 0,
                width: 1,
                color: '#808080'
            }]
        },
        tooltip: {
            valueSuffix: '人'
        },
        legend: {
        	enabled:false
        },
        series: yData
    });
}

var current = "drkh";
function radioClick(){
	$("input[type=radio][name='renshu']").click(function(){
		var _this = $(this);
		var _v = _this.val();
		if(_v==current){
			return;
		}
		current = _v;
		var dataY = loadGmDataY(_jsonData, _v);
		drawGmChart('#gmdata_chart',_datexJson, dataY);
	});
}


/**是否包含对应权限id
 * @param allPerData
 * @param perid
 */
function containsPer(allPerData,perid){
	if(allPerData && allPerData.length>=1){
		for(var i=0;i<allPerData.length;i++){
			var _data = allPerData[i];
			var perVar = _data.permissionId;
			if(perid == perVar){
				return true;
			}
		}
	}
	return false;
}
 
