/**
 * 穿透分析js
 */
//图表tag对应指标map,key:tag,value:[coltag]
var chart_quota_map = new HashMap();
//20W以上客户占比趋势-[20W以上人数,首次公募交易客户数]
chart_quota_map.put("up20w",["cnt_d","cnt_b","cnt_d/cnt_b"]);
//货转股转化率、
chart_quota_map.put("c2e_rate",["cnt_c_e","cnt_c","cnt_c_e/cnt_c"]);
//公转私转化率趋势 [公转私的人数-20W以下,公转私的人数-20W以上(含),"公转私基数20W以下 ","公转私基数20W以上(含)"]
chart_quota_map.put("f2b_rate", ["cnt_f-cnt_d_f","cnt_d_f","cnt_b-cnt_d","cnt_d","cnt_f","cnt_b",
                               "(cnt_f-cnt_d_f)/(cnt_b-cnt_d)","cnt_d_f/cnt_d","cnt_f/cnt_b"]);
//公转私贡献率 [公转私人数，公转私人数20W以上，公转私的贡献率20W以上(公转私的人数20W以上/公转私人数)]
chart_quota_map.put("f2b_rate_up20w",["cnt_f","cnt_d_f","cnt_d_f/cnt_f"]);
//公转私六象限
chart_quota_map.put("f2b_rate_ctfx6r",["cnt_f","cnt_d_f","cnt_d_f/cnt_f"]);

//默认显示的指标
var gtctMap = new HashMap();
gtctMap.put("f2b_rate",["cnt_f","cnt_b","cnt_f/cnt_b"]);

//标题map
var yAxisTitleArrayMap = new HashMap();
yAxisTitleArrayMap.put("up20w",["占比","人数"]);
yAxisTitleArrayMap.put("c2e_rate",["转化率","人数"]);
yAxisTitleArrayMap.put("f2b_rate",["转化率","人数"]);
yAxisTitleArrayMap.put("f2b_rate_up20w",["贡献率","人数"]);
yAxisTitleArrayMap.put("f2b_rate_ctfx6r",["人数"]);

/**
 * 对应指标的categories数据
 */
var chartCategoriesMap = new HashMap();
//20W以上客户占比
chartCategoriesMap.put("cnt_d_up20w","20W以上人数");
chartCategoriesMap.put("cnt_b_up20w","首次公募交易客户数");

//货转股
chartCategoriesMap.put("cnt_c_e_c2e_rate","货转股人数");
chartCategoriesMap.put("cnt_c_c2e_rate","货转股基数");

////公转私转化率
chartCategoriesMap.put("cnt_b-cnt_d_f2b_rate","公转私基数-20W以下");
chartCategoriesMap.put("cnt_d_f2b_rate","公转私基数-20W以上");
chartCategoriesMap.put("cnt_f_f2b_rate","公转私人数-全部");
chartCategoriesMap.put("cnt_b_f2b_rate","公转私基数-全部");
chartCategoriesMap.put("(cnt_f-cnt_d_f)/(cnt_b-cnt_d)_f2b_rate","公转私转化率-20W以下");

//公转私贡献率
chartCategoriesMap.put("cnt_d_f/cnt_f_f2b_rate_up20w","公转私的贡献率-20W以上（含）");

chartCategoriesMap.put("cnt_d","20W以上人数");
chartCategoriesMap.put("cnt_b","首次公募交易客户数");
chartCategoriesMap.put("cnt_d/cnt_b","20W以上占比");
chartCategoriesMap.put("cnt_c_e","货转股人数");
chartCategoriesMap.put("cnt_c","首次交易为货币基金的客户数");
chartCategoriesMap.put("cnt_c_e/cnt_c","货转股转化率");
chartCategoriesMap.put("cnt_f","公转私人数");
chartCategoriesMap.put("cnt_f/cnt_b","公转私转化率-全部");
chartCategoriesMap.put("cnt_f-cnt_d_f","公转私的人数-20W以下");
chartCategoriesMap.put("cnt_d_f","公转私的人数-20W以上(含)");
chartCategoriesMap.put("cnt_b-cnt_d","公转私基数20W以下");
chartCategoriesMap.put("cnt_d","公转私基数20W以上(含)");
chartCategoriesMap.put("(cnt_f-cnt_d_f)/(cnt_b-cnt_d)","公转私转化率20W以下");
chartCategoriesMap.put("cnt_d_f/cnt_d","公转私转化率-20W以上(含)");
chartCategoriesMap.put("cnt_d_f/cnt_f","公转私的贡献率20W以上");



/**
CNT_B		首次交易为普通公募基金总客户数
CNT_C	首次交易为货币基金的客户总数
CNT_E	交易股票类基金的总客户数， 股票类基金是指不含货币和专户的其他公募基金
CNT_F	首次交易为公募基金的高净值产品交易总客户数， 高净值产品是指公募专户和私募产品
*/
var pyramid_quato = ["cnt_f","cnt_e","cnt_c","cnt_b"];
var pyramid_quato_name_map = new HashMap();
pyramid_quato_name_map.put("cnt_f","F-高净值客户");
pyramid_quato_name_map.put("cnt_e","E-股票类客户");
pyramid_quato_name_map.put("cnt_c","C-首次交易为货币基金的客户");
pyramid_quato_name_map.put("cnt_b","B-首次交易为普通公募基金客户");


/**加载金字塔图的数据
 * @param dataTopJson
 * @returns
 */
function loadPyramidData(dataTopJson){
	var dataTopJsonArray = [];
	for(var i=0;i<pyramid_quato.length;i++){
		var pcode = pyramid_quato[i];
		var val_dataTag = dataTopJson[pcode];
		var target_value = 0;
		if(""!=val_dataTag && !isNaN(val_dataTag)){
			target_value = parseInt(val_dataTag);
		}
		var name_code = pyramid_quato_name_map.get(pcode);
		var dataObj=[name_code,target_value];
		dataTopJsonArray.push(dataObj);
	}
	return dataTopJsonArray.reverse();
}

/**
 * 根据类型获取不同象限图数据
 * @param asset
 */
function loadCtfx6rData(asset,_ctfx6rJson){
	var ctfxDataObj = null;
	for(var i=0;i<_ctfx6rJson.length;i++){
		var _ctfxObj = _ctfx6rJson[i];
		var _asset20w=_ctfxObj["asset20w"];
		if(_asset20w==asset){
			ctfxDataObj = _ctfxObj;
			break;
		}
	}
	return ctfxDataObj;
}


/**
 * 加载对应指标下面图的catergories
 */
function loadChartCategoriesArray(tag){
	var categories=[];
	if(tag){
		var _dataListJson=dataListJson||[];
		for(var i=0;i<_dataListJson.length;i++){
			var _data = _dataListJson[i];
			var category = _data[tag];
			if(category){
				categories.push(category);
			}
		}
	}
	return categories;
}
/**
 * 加载 混合图表数据
 * @param tag 当前加载的数据表tag
 */
function loadCombinationChart(tag){
	var combinationChartObj = [];
	var columnDataMap = new HashMap();
	var splineDataMap = new HashMap();
	if(tag){
		var _dataListJson = dataListJson||[];
		var colTagArray = chart_quota_map.get(tag);
		for(var i =0;i<colTagArray.length;i++){
			var col = colTagArray[i];
			var dataArray = [];
			if(col && col.indexOf("/")==-1 && col.indexOf("-")==-1 && col.indexOf("+")==-1){
				//柱状图数据
				for(var j =0;j<_dataListJson.length;j++){
					var _dataObj = _dataListJson[j];
					var data = _dataObj[col];
					dataArray.push(parseInt(data));
				}
				var category = col;
				columnDataMap.put(category,dataArray);
			}else if(col &&  col.indexOf("/")==-1 && (col.indexOf("-")>-1||col.indexOf("+")>-1)){
				if(col.indexOf("-")>-1){
					var col_0_vs = col.split("-");
					for(var j =0;j<_dataListJson.length;j++){
						var sum_col_0=0;
						var _dataObj = _dataListJson[j];
						for(var jj=0;jj<col_0_vs.length;jj++){
							var col_0_j = col_0_vs[jj];
							if(jj==0){
								sum_col_0+=_dataObj[col_0_j];
							}else{
								sum_col_0+=_dataObj[col_0_j]*-1;
							}
						}
						dataArray.push(parseInt(sum_col_0));
					}
				}else if(col.indexOf("+")>-1){
					var col_0_vs = col.split("+");
					for(var j =0;j<_dataListJson.length;j++){
						var sum_col_0=0;
						var _dataObj = _dataListJson[j];
						for(var jj=0;jj<col_0_vs.length;jj++){
							var col_0_j = col_0_vs[jj];
							sum_col_0+=_dataObj[col_0_j];
						}
						dataArray.push(parseInt(sum_col_0));
					}
				}
				var category = col;
				columnDataMap.put(category,dataArray);
			}else{
				//计算spline线图数据
				var colDivArray = col.split("/");
				if(colDivArray && colDivArray.length==2){
					for(var j =0;j<_dataListJson.length;j++){
						var _dataObj = _dataListJson[j];
						var col_0 = colDivArray[0];
						var col_1 = colDivArray[1];
						if(col_0.indexOf("(")==-1 && col_1.indexOf("(")==-1){
							var da= 100*_dataObj[col_0]/_dataObj[col_1];
							da=da||0;
							//保留4位小数
							dataArray.push(parseFloat(da.toFixed(4)));
						}else{
							col_0=col_0.replace("(","").replace(")","");
							var sum_col_0=0;
							var sum_col_1=0;
							if(col_0.indexOf("+")>-1){
								var col_0_vs = col_0.split("+");
								for(var jj=0;jj<col_0_vs.length;jj++){
									var col_0_j = col_0_vs[jj];
									sum_col_0+=_dataObj[col_0_j];
								}
							}else if(col_0.indexOf("-")>-1){
								var col_0_vs = col_0.split("-");
								for(var jj=0;jj<col_0_vs.length;jj++){
									var col_0_j = col_0_vs[jj];
									if(jj==0){
										sum_col_0+=_dataObj[col_0_j];
									}else{
										sum_col_0+=_dataObj[col_0_j]*-1;
									}
								}
							}
							col_1=col_1.replace("(","").replace(")","");
							if(col_1.indexOf("+")>-1){
								var col_1_vs = col_1.split("+");
								for(var jj=0;jj<col_1_vs.length;jj++){
									var col_1_j = col_1_vs[jj];
									sum_col_1+=_dataObj[col_1_j];
								}
							}else if(col_1.indexOf("-")>-1){
								var col_1_vs = col_1.split("-");
								for(var jj=0;jj<col_1_vs.length;jj++){
									var col_1_j = col_1_vs[jj];
									if(jj==0){
										sum_col_1+=_dataObj[col_1_j];
									}else{
										sum_col_1+=_dataObj[col_1_j]*-1;
									}
								}
							}
							var da= 100*sum_col_0/sum_col_1;
							da=da||0;
							//保留4位小数
							dataArray.push(parseFloat(da.toFixed(4)));
						}
					}
					var category = col;
					//线图数据
					splineDataMap.put(category,dataArray);
				}
			}
		}
		combinationChartObj[0] =  columnDataMap;
		combinationChartObj[1] =  splineDataMap;
	}
	return combinationChartObj;
}



/**加载公转私六象限数据
 * @param tag
 */
function loadStatckColumn(){
	var seriesDataArrayM=[];  
	var seriesDataArrayL=[];  
	var _ctfx6rListJson = ctfx6rListJson||[];
	/*A2C_CNT	Y	公转私的人数  首次货币（仅货币）
	AB2C_CNT	Y	公转私的人数  首次货币（后买股）
	B2C_CNT	Y	公转私的人数  */
	var statckColumnCnTagArray = ["a2c_cnt","ab2c_cnt","b2c_cnt"];
	var statckColumnCnTagArrayName = new HashMap();
	statckColumnCnTagArrayName.put("a2c_cnt","首次货币(仅货币)");
	statckColumnCnTagArrayName.put("ab2c_cnt","首次货币(后买股)");
	statckColumnCnTagArrayName.put("b2c_cnt","首次非货币");
	
	for(var j = 0;j<statckColumnCnTagArray.length;j++){
		var cn = statckColumnCnTagArray[j];
		var dataArrayM20W = [];
		var dataArrayL20W = [];
		var nameM20W = ">=20w"+statckColumnCnTagArrayName.get(cn);
		var nameL20W = "<20w"+statckColumnCnTagArrayName.get(cn);
			for(var i=0;i<_ctfx6rListJson.length;i++){
				var _ctfxObj = _ctfx6rListJson[i];
				var _asset20w=_ctfxObj["asset20w"];
				var _data = _ctfxObj[cn];
				if(_asset20w==">=20w"){
					dataArrayM20W.push(_data);
				}else if(_asset20w=="<20w"){
					dataArrayL20W.push(_data);
				}
			}
			var _assetM20wObj = 
			{
					name: nameM20W,
					data: dataArrayM20W,
					stack: ">=20w"
			};
			var _assetL20wObj = 
			{
					name: nameL20W,
					data: dataArrayL20W,
					stack: "<20w"
			};
			seriesDataArrayM.push(_assetM20wObj);
			seriesDataArrayL.push(_assetL20wObj);
	}
	return seriesDataArrayM.concat(seriesDataArrayL);
}



/**加载金字塔图
 * @param data json类型的数据
 */
function drawPyramid(data) {
	$('#pyramid').highcharts(
		{
			credits: { 
	    	    enabled: false 
	    	},
	    	chart : {
				type : 'pyramid',
				margin : [10,220,50,0] 
			},
			title : {
				text : '',
				x : -50
			},
			plotOptions : {
				series : {
					dataLabels : {
						enabled : true,
						format : '<b>{point.name}</b> ({point.y:,.0f})',
						color : (Highcharts.theme && Highcharts.theme.contrastTextColor)
								|| 'black',
						softConnector : true
					}
				}
			},
			legend : {
				enabled : false
			},
			series : [ {
				name : ' ',
				data : data
			} ]
		});
}


/**
 * 画六象限图
 */
function drawCtfx6r(ctfx6rJsonData) {
	try {
		var _ctfx6rJson =[];
		if(ctfx6rJsonData){
			_ctfx6rJson = ctfx6rJsonData;
		}else{
			_ctfx6rJson = ctfx6rJson;
		}
		var dblpie1 = loadCtfx6rData(">=20w",_ctfx6rJson);
		var dblpie2 = loadCtfx6rData("<20w",_ctfx6rJson);
		var TTL1 = dblpie1.sumcnt;
		var TTL2 = dblpie2.sumcnt;
		var data1 = [ dblpie1.a2c_cnt, dblpie1.ab2c_cnt, dblpie1.b2c_cnt ];

		var data2 = [ dblpie2.a2c_cnt, dblpie2.ab2c_cnt, dblpie2.b2c_cnt ];

		var colors = Highcharts.getOptions().colors, categories = [ '20W以上',
				'20W以下' ], data = [
				{
					y : TTL1,
					color : colors[0],
					drilldown : {
						categories : [ '20W以上<br>首次货币（仅货币）', '20W以上<br>首次货币（后买股）',
								'20W以上<br>首次非货币' ],
						data : data1,
						color : colors[0]
					}
				},
				{
					y : TTL2,
					color : colors[1],
					drilldown : {
						categories : [ '20W以下<br>首次货币（仅货币）', '20W以下<br>首次货币（后买股）',
								'20W以下<br>首次非货币' ],
						data : data2,
						color : colors[1]
					}
				} ];
		var browserData = [], versionsData = [], i, j, dataLen = data.length, drillDataLen;

		// Build the data arrays
		for (i = 0; i < dataLen; i += 1) {
			// add browser data
			browserData.push({
				name : categories[i],
				//sliced: categories[i]==pname,
				y : data[i].y,
				color : data[i].color
			});
			// add version data
			drillDataLen = data[i].drilldown.data.length;
			for (j = 0; j < drillDataLen; j += 1) {
				var brightness = 0.2 - (j / data[i].drilldown.data.length) / 5;
				versionsData.push({
					name : data[i].drilldown.categories[j],
					//sliced: data[i].drilldown.categories[j]==pname,
					y : data[i].drilldown.data[j],
					color : Highcharts.Color(data[i].color).brighten(brightness)
							.get()
				});

			}
		}

		// Create the chart
		$('#ctfx6r').highcharts(
				{
					credits : {
						enabled : false
					},
					chart : {
						type : 'pie'
					},
					title : {
						text : ''
					},
					yAxis : {
						title : {
							text : 'Total percent market share'
						}
					},
					plotOptions : {
						pie : {
							shadow : false,
							center : [ '50%', '50%' ],
							events : {
								click : function(e) {
									console.log(e.point.name);
								}
							}
						}
					},
					tooltip : {
						//valueSuffix: ''
						formatter : function() {
							// display only if larger than 1
							return this.y > 1 ? '<b>' + this.point.name
									+ ':</b><br>人数' + this.y + '<br>占比'
									+ Math.round(this.y / (TTL1 + TTL2) * 10000)
									/ 100 + '%' : null;
						}
					},
					series : [ {
						name : '占比',
						data : browserData,
						size : '30%',
						dataLabels : {
							formatter : function() {
								return this.y > 5 ? this.point.name : null;
							},
							color : 'black',
							distance : -15
						}
					}, {
						name : '占比',
						data : versionsData,
						size : '80%',
						innerSize : '60%',
						dataLabels : {
							formatter : function() {
								// display only if larger than 1
								return null;
							}
						}
					} ]
				});

	} catch (e) {
		console.log(e);
	}
	
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
/**
 * 画混合图表
 */
function drawCombinationChart(tag,categoriesArray,columnDataMap,splineDataMap,yAxisTitleArray){
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
			var gt = gtctMap.get(tag);
			if(gt){
				v =false;
				for(var g=0;g<gt.length;g++){
					var _gq = gt[g];
					if(_gq==hkey){
						v = true;
						break;
					}
				}
			}
			var tk = hkey+"_"+tag;
			var name = chartCategoriesMap.get(tk)||chartCategoriesMap.get(hkey);
			var _dataObj={
	            name: name,
	            type: 'column',
	            yAxis: 1,
	            data:cvalue,
	            dataLabels: dataLabelsOption,
	            tooltip: {
	                valueSuffix: ' 人'
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
			if(null ==hkey || "" ==hkey){
				continue;
			}
			var v =true;
			var gt = gtctMap.get(tag);
			if(gt){
				v =false;
				for(var g=0;g<gt.length;g++){
					var _gq = gt[g];
					if(_gq==hkey){
						v = true;
						break;
					}
				}
			}
			var tk = hkey+"_"+tag;
			var name = chartCategoriesMap.get(tk)||chartCategoriesMap.get(hkey);
			var dObj = {
					name: name,
		            type: 'spline',
		            data: svalue,
		            tooltip: {
		            	valueSuffix: " %",
		            	valueDecimals: 4
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
            format: '{value} 人',
            style: {
                color: Highcharts.getOptions().colors[0]
            }
        },
        lineWidth : 1,
		opposite : true,
		type : "logarithmic",
		min:minNumber,
		max:maxNumber
    }];
	
    $('#combinationChart').highcharts({
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
        tooltip: {
            shared: true
        },
        series: seriesDataArray.slice()
    });
    
    
     
    
}

/**
 * 公转私六象限分析画图
 * 
 * @param categoriesArray
 * @param seriesDataArray
 * @param yAxisTitleArray
 */
function drawcolumn(categoriesArray,seriesDataArray,yAxisTitleArray){
	var lenAll = seriesDataArray.length;
	if(lenAll>3){
		for(var h=0;h<lenAll;h++){
			var dh =seriesDataArray[h];
			if((h+1)%3!=0){
				dh.visible=false;
			}
		}
	}
	
	$('#combinationChart').highcharts({
		credits: { 
    	    enabled: false 
    	},
    	chart: {
            type: 'column'
        },
        title: {
            text: ' '
        },
        xAxis: {
            categories: categoriesArray
        },
        yAxis: {
            allowDecimals: false,
            min: 0,
            title: {
                text: yAxisTitleArray[0]
            },stackLabels: {
                enabled: true,
                style: {
                    fontWeight: 'bold',
                    color: (Highcharts.theme && Highcharts.theme.textColor) || 'gray'
                }
            }
        },
        tooltip: {
            formatter: function () {
                return '<b>' + this.x + '</b><br/>' +
                    this.series.name + ': ' + this.y + '<br/>' +
                    'Total: ' + this.point.stackTotal;
            }
        },
        plotOptions: {
            column: {
                stacking: 'normal',
                dataLabels: {
                    enabled: true,
                    color: (Highcharts.theme && Highcharts.theme.dataLabelsColor) || 'white',
                    style: {
                        textShadow: '0 0 3px black'
                    }
                }
            }
        },
        series: seriesDataArray
    });
}
