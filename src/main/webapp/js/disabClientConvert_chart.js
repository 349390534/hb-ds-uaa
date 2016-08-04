/**
 * 穿透分析已分配客户转化js
 */

/**
 * @param prop
 * @param isfloat 0/1
 * @returns {Array}
 */
function loadDisabClientChartData(prop,isfloat) {
	var _dataListJson = dataListJson || [];
	var resData = [];
	if (prop) {
		for (var i = 0; i < _dataListJson.length; i++) {
			var _dataObj = _dataListJson[i];
			var data = _dataObj[prop];
			if(isfloat == 0){
				resData.push(parseFloat(data.toFixed(2)));
			}else{
				resData.push(parseInt(data));
			}
		}
	}
	return resData;

}

function drawChart() {
	// 基本设置
	$('#disabClient_chart').html("");
	$('#disabClient_chart').highcharts({
		chart : {
			zoomType : 'xy'
		},
		credits : {
			enabled : false
		},
		title : {
			text : ''
		},
		subtitle : {
			text : ''
		},
		plotOptions : {
			column : {
				stacking : 'normal',
				depth : 40
			}
		},
		xAxis : [ {
			categories : loadDisabClientChartData("statdt"),
		} ],
		yAxis : [ {
			tickInterval : 150,
			max : 3000,
			min : 0,
			gridLineWidth : 0,
			title : {
				text : '客户数',
				style : {
					color : '#4572A7'
				}
			},
			labels : {
				formatter : function() {
					return this.value + ' 人';
				},
				style : {
					color : '#4572A7'
				}
			}
		}, {
			gridLineWidth : 0,
			min : 0,
			title : {
				text : '成交比',
				style : {
					color : '#AA4643'
				}
			},
			labels : {
				formatter : function() {
					return this.value + ' %';
				},
				style : {
					color : '#AA4643'
				}
			},
			opposite : true
		} ],
		tooltip : {
			shared : true
		},
		series : [ {
			name : '零售业务部成交数',
			color : '#00FA9A',
			type : 'column',
			yAxis : 0,
			data : loadDisabClientChartData("trade_cnt_ls",1),
			stack : 'sale',
			tooltip : {
				valueSuffix : ' 人'
			}

		}, {
			name : '零售业务部分配未成交数',
			color : '#8A8A8A',
			type : 'column',
			yAxis : 0,
			data : loadDisabClientChartData("no_trade_cnt_ls",1),
			stack : 'sale',
			tooltip : {
				valueSuffix : ' 人'
			}

		}, {
			name : '公募挖掘团队成交数',
			color : '#00868B',
			type : 'column',
			yAxis : 0,
			data : loadDisabClientChartData("trade_cnt_wj",1),
			stack : 'dig',
			tooltip : {
				valueSuffix : ' 人'
			}

		}, {
			name : '公募挖掘团队分配未成交数',
			color : '#778899',
			type : 'column',
			yAxis : 0,
			data : loadDisabClientChartData("no_trade_cnt_wj",1),
			stack : 'dig',
			tooltip : {
				valueSuffix : ' 人'
			}

		}, {
			name : '公募挖掘团队-储蓄罐未分配人数',
			color : '#776699',
			type : 'column',
			yAxis : 0,
			data : loadDisabClientChartData("cxgwfp_cnt_wj",1),
			stack : 'dig',
			tooltip : {
				valueSuffix : ' 人'
			}

		}, {
			name : '零售业务部成交比',
			type : 'spline',
			color : '#AA4643',
			yAxis : 1,
			data : loadDisabClientChartData("trade_pct_ls",0),
			marker : {
				enabled : false
			},
			dashStyle : 'shortdot',
			tooltip : {
				valueSuffix : ' %'
			}

		}, {
			name : '公募挖掘团队成交比',
			color : '#89A54E',
			type : 'spline',
			yAxis : 1,
			data : loadDisabClientChartData("trade_pct_wj",0),
			tooltip : {
				valueSuffix : ' %'
			}
		}, {
			name : '公募挖掘团队成交比-含储蓄罐未分配',
			color : '#80699B',
			type : 'spline',
			yAxis : 1,
			data : loadDisabClientChartData("no_trade_cnt_wj_ls",0),
			tooltip : {
				valueSuffix : ' %'
			}
		} ]
	});
}