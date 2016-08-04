/**
 * 报表插件
 * @type {HbCharts|*|{}}
 */
var HbCharts = HbCharts || {};
(function ($) {
	var HbChartsData = null;
	Highcharts.setOptions({
		global: {
			useUTC: false
		},
		lang: {
			months: ['一月', '二月', '三月', '四月', '五月', '六月', '七月', '八月', '九月', '十月', '十一月', '十二月'],
			weekdays: ['星期日', '星期一', '星期二', '星期三', '星期四', '星期五', '星期六']
		}
	});
	function showCharts() {
		this.options = null;
		this.pieOptions = {
			targetId: null,  //元素
			labelFormatter: "2",
			data: null,  //数据
			colors: ['#7cb5ec', '#434348', '#90ed7d', '#f7a35c', '#8085e9', '#f15c80', '#e4d354', '#8085e8', '#8d4653', '#91e8e1'],  //图线颜色
			showInLegend: true,  //是否显示图例
			plotBackgroundColor: null,  //图表背景层颜色
			plotBorderWidth: null,  //边框宽度
			plotShadow: false,  //是否显示阴影
			title: null,  //头部文案
			showTooltip: true,  //是否显示Tooltip
			dataLabels: false,  //是否显示提示线
			dataLabelsColor: '#333',  //提示线文本颜色
			dataLabelsConnectorColor: '#333',  //提示线颜色
			allowPointSelect: true,  //点击饼图是否突出显示
			cursor: 'pointer',  //获取焦点样式
			is3dEnabled: false,  //是否3d显示
			pieDepth: 35  //3d饼图高度
		};
		this.initPicChart = function () {
			var chart;
			var $than = this;
			$than.settings = $.extend({}, this.pieOptions, this.options);
			$than.settings.targetId.highcharts({
				credits : {
					enabled : false
				},
				colors: $than.settings.colors,
				chart: {
					type: 'pie',
					plotBackgroundColor: $than.settings.plotBackgroundColor,
					plotBorderWidth: $than.settings.plotBorderWidth,
					plotShadow: $than.settings.plotShadow,
					options3d: {
						enabled: $than.settings.is3dEnabled,
						alpha: 45,
						beta: 0
					}
				},
				title: {
					text: $than.settings.title
				},
				tooltip: {
					followTouchMove: true,
					pointFormat: '<b>{point.percentage:.2f}%</b><p>  </p>{point.y}'
				},
				plotOptions: {
					pie: {
						allowPointSelect: $than.settings.allowPointSelect,
						cursor: 'pointer',
						dataLabels: {
							enabled: $than.settings.dataLabels,
							color: $than.settings.dataLabelsColor,
							connectorColor: $than.settings.dataLabelsConnectorColor,
							formatter: function () {
								return '<b>' + this.name + '</b>: ' + this.percentage + ' %';
							}
						},
						showInLegend: $than.settings.showInLegend,
						depth: $than.settings.pieDepth
					}
				},
				legend: {
					borderColor: "#909090",
					align: 'center',
					verticalAlign: 'bottom'
				},
				series: [{
					type: 'pie',
					data: $than.settings.data
				}],
				exporting: {
					enabled: false
				}
			});
		};

		this.lineOptions = {
			targetId: null,  //元素
			dataX: null,  //x轴数据
			dataY: null,  //y轴数据
			colors: ['#7cb5ec', '#434348', '#90ed7d', '#f7a35c', '#8085e9', '#f15c80', '#e4d354', '#8085e8', '#8d4653', '#91e8e1'],  //图线颜色
			title: null,  //头部文案
			titleOffset: 0, //头部文案左右偏移量
			title2: null,  //头部第二行文案
			title2Offset: 0, //头部第二行文案左右偏移量
			yAxisTitleText: null,  //y轴文案
			yAxisTitleAlign: 'high',
			yAxisTitleOffset: -60,
			yAxisTitleRotation: 0,
			yAxisTitleY: -15,
			plotLinesValue: 0,  //针对y轴某值进行设置
			plotLinesWidth: 1,  //针对y轴某值进行设置宽度
			plotLinesColor: '#808080',  //针对y轴某值进行颜色
			valueSuffix: '',  //单位
			showInLegend: true,  //是否显示图例
			legendLayout: 'vertical',  //图例布局方式
			legendAlign: 'right',  //图例横向布局
			legendVerticalAlign: 'middle',  //图例竖直布局
			legendBorderWidth: 0  //图例边框
		};
		this.initLineChart = function () {
			var chart;
			var $than = this;
			$than.settings = $.extend({}, this.lineOptions, this.options);
			$than.settings.targetId.highcharts({
				credits : {
					enabled : false
				},
				chart: {
					height: 300
				},
				colors: $than.settings.colors,
				title: {
					text: $than.settings.title,
					x: $than.settings.titleOffset
				},
				subtitle: {
					text: $than.settings.title2,
					x: $than.settings.title2Offset
				},
				xAxis: {
					categories: $than.settings.dataX
				},
				yAxis: {
					title: {
						text: $than.settings.yAxisTitleText,
						align: $than.settings.yAxisTitleAlign,
						offset: $than.settings.yAxisTitleOffset,
						rotation: $than.settings.yAxisTitleRotation,
						y: $than.settings.yAxisTitleY
					},
					plotLines: [{
						value: $than.settings.plotLinesValue,
						width: $than.settings.plotLinesWidth,
						color: $than.settings.plotLinesColor
					}]
				},
				tooltip: {
					followTouchMove: true,
					valueSuffix: $than.settings.valueSuffix,
					shared: true
				},
				legend: {
					layout: $than.settings.legendLayout,
					align: $than.settings.legendAlign,
					verticalAlign: $than.settings.legendVerticalAlign,
					borderWidth: $than.settings.legendBorderWidth
				},
				plotOptions: {
					line: {
						cursor: 'pointer',
						showInLegend: $than.settings.showInLegend
					}
				},
				series: $than.settings.dataY,
				exporting: {
					enabled: false
				}
			});
		};

		this.columnOptions = {
			targetId: null,  //元素
			dataX: null,  //x轴数据
			dataY: null,  //y轴数据
			colors: ['#7cb5ec', '#434348', '#90ed7d', '#f7a35c', '#8085e9', '#f15c80', '#e4d354', '#8085e8', '#8d4653', '#91e8e1'],  //图线颜色
			title: null,  //头部文案
			title2: null,  //头部第二行文案
			yAxisTitleText: null,  //y轴文案
			yAxisTitleAlign: 'high',
			yAxisTitleOffset: -60,
			yAxisTitleRotation: 0,
			yAxisTitleY: -15,
			plotLinesValue: 0,  //针对y轴某值进行设置
			plotLinesWidth: 1,  //针对y轴某值进行设置宽度
			plotLinesColor: '#808080',  //针对y轴某值进行颜色
			valueSuffix: '',  //单位
			showInLegend: true,  //是否显示图例
			legendLayout: 'vertical',  //图例布局方式
			legendAlign: 'center',  //图例横向布局
			legendVerticalAlign: 'high',  //图例竖直布局
			legendBorderWidth: 0,  //图例边框
			minValue: 0,  //Y轴最小值
			shared: true,  //是否整块提示
			pointPadding: 0.1,  //柱体间距
			oneDataWidth: null,  //一条数据柱体宽度
			pointWidth: null,  //柱体宽度
			legendReversed:true,
			showCredits:false,
			layout:"horizontal",
			enabled:true,
			borderWidth:0,
			borderRadius:0,
			borderColor:'#909090',
			backgroundColor:"",
			align:'center',
			verticalAlign:"top",
			decimalFloat:0
		};
		this.initColumnChart = function () {
			var chart;
			var $than = this;
			$than.settings = $.extend({}, this.columnOptions, this.options);

			if ($than.settings.dataY.length < 2 && $than.settings.dataY[0].data.length === 1) {
				$than.settings.pointWidth = $than.settings.oneDataWidth;
			}
			$than.settings.targetId.highcharts({
				colors: $than.settings.colors,
				chart: {
					type: 'column'
				},
				title: {
					text: $than.settings.title
				},
				subtitle: {
					text: $than.settings.title2
				},
				xAxis: {
					categories: $than.settings.dataX
				},
				yAxis: {
					min: $than.settings.minValue,
					title: {
						text: $than.settings.yAxisTitleText,
						align: $than.settings.yAxisTitleAlign,
						offset: $than.settings.yAxisTitleOffset,
						rotation: $than.settings.yAxisTitleRotation,
						y: $than.settings.yAxisTitleY
					},
					plotLines: [{
						value: $than.settings.plotLinesValue,
						width: $than.settings.plotLinesWidth,
						color: $than.settings.plotLinesColor
					}],
					labels: {
		                formatter: function () {
		                    return formatNum(this.value,0) + $than.settings.valueSuffix;
		                }
		            }
				},
				tooltip: {
					followTouchMove: true,
					headerFormat: '<span style="font-size:10px">{point.key}</span><table>',
					pointFormat: '<tr><td style="color:{series.color};padding:0">{series.name}: </td>' +
					'<td style="padding:0"><b>{point.y:.'+$than.settings.decimalFloat+'f}' + $than.settings.valueSuffix + '</b></td></tr>',
					footerFormat: '</table>',
					shared: $than.settings.shared,
					useHTML: true
				},
				legend: {
					reversed: $than.settings.legendReversed,
					layout: $than.settings.layout ,
					enabled: $than.settings.enabled,
					borderWidth: $than.settings.borderWidth,
					borderRadius: $than.settings.borderRadius,
					borderColor: $than.settings.borderColor,
					backgroundColor: $than.settings.backgroundColor,
					align: $than.settings.align,
					verticalAlign: $than.settings.verticalAlign
				},
				plotOptions: {
					column: {
						cursor: 'pointer',
						showInLegend: $than.settings.showInLegend,
						pointPadding: $than.settings.pointPadding
					},
					series: {
						oneDataWidth: $than.settings.oneDataWidth,
						pointWidth: $than.settings.pointWidth,
						turboThreshold: 0,
						followTouchMove: true
					}
				},
				credits: {
					enabled:$than.settings.showCredits,
					text: 'HowBuy数据',
					href: 'http://howbuy.com',
					position: {
						align: 'right', //水平居右
						verticalAlign: 'bottom' //垂直底部
					},
					style: {
						cursor: 'pointer', //鼠标样式为手型
						color: 'blue', //字体颜色
						fontSize: '10px' //字体大小
					}
				},
				series: $than.settings.dataY,
				exporting: {
					enabled: false
				}
			});
		};

		this.areasplineOptions = {
			targetId: null,
			cats: null,
			catsIndex: null,
			data: null,
			hideDataIndex: null,
			dataColors: [],
			credits: false,
			chartType: "line",
			title: null,
			titleX: null,
			subtitle: null,
			subTitleX: null,
			isShowLegend: true,
			tooltipValueSuffix: null,
			xAxisLabelFormatter: "%Y-%m-%d",
			yAxisTitle: null,
			yAxisLabelsFormat: null,
			customEvent: null,
			showTooltip: true,
			tooltipFormatter: null,
			markerRadius: 0,
			fillColor: "#d9eafe",//
			gridLineDashStyle: "ShortDot",//虚线
			gridLineColor: "#ddeaf0",//背景线条颜色
			tickPixelIntervalWidth: 342
		};
		this.initAreasplineChart = function () {
			var chart;
			var $than = this;
			var options = $.extend({}, this.areasplineOptions, this.options);
			var targetId = options.targetId;
			var series = [];

			var navArrStr = '';
			var cats = options.cats;
			var catsLength = cats.length;
			for (var i = 0; i < catsLength; i++) {
				series.push({
					name: cats[i],
					color: options.dataColors[i],
					data: []
				});
				var hideCats = options.hideDataIndex;
				if (hideCats) {
					var hideCatsLength = hideCats.length;
					for (var j = 0; j < hideCatsLength; j++) {
						if (hideCats[j] == i) {
							series[i].visible = false;
						}
					}
				}
				var lineImgDate = options.data;
				jQuery.each(lineImgDate, function (ij, line) {
					var items = line;
					var _time = items[0];
					series[i].data.push([_time, parseFloat(items[options.catsIndex[i]])]);
				});
			}

			var tooltip = {};
			if (options.showTooltip) {
				tooltip = {
					crosshairs: [true, true],
					valueSuffix: options.tooltipValueSuffix,
					followTouchMove: true,
					formatter: function () {
						if (options.tooltipFormatter != null) {
							return options.tooltipFormatter(this.point);
						}
					}
				}
			} else {
				tooltip = {
					crosshairs: [true, true],
					enabled: false
				}
			}
			targetId.highcharts({
				chart: {
					type: options.chartType
				},
				title: {
					text: options.title,
					x: options.titleX
				},
				subtitle: {
					text: options.subtitle,
					x: options.subTitleX
				},
				tooltip: tooltip,
				credits: {
					enabled: options.credits
				},
				xAxis: {
					labels: {
						formatter: function () {
							return new Date(this.value).format(options.xAxisLabelFormatter);
						}
					},
					tickPixelInterval: (options.tickPixelIntervalWidth) / 6.8,
					gridLineWidth: 1,
					tickWidth: 0,
					gridLineColor: options.gridLineColor,
					minorGridLineColor: options.gridLineColor,
					gridLineDashStyle: options.gridLineDashStyle
				},
				yAxis: {
					title: {
						text: options.yAxisTitle
					},
					gridLineDashStyle: options.gridLineDashStyle,
					gridLineColor: options.gridLineColor,
					minorGridLineColor: options.gridLineColor,
					labels: {
						formatter: function () {
							if (options.yAxisLabelsFormat != null) {
								return options.yAxisLabelsFormat(this.value);
							} else {
								return this.value;
							}
						}
					}
				},
				legend: {
					enabled: options.isShowLegend
				},
				plotOptions: {
					line: {
						fillOpacity: 0.5,
						threshold: null,
						lineWidth: 1,
						marker: {
							radius: options.markerRadius
						},
						states: {
							hover: {
								lineWidth: 1
							}
						}
					},
					area: {
						fillOpacity: 0.5,
						threshold: null,
						lineWidth: 1,
						marker: {
							radius: options.markerRadius
						},
						states: {
							hover: {
								lineWidth: 1
							}
						},
						fillColor: {
							linearGradient: {x1: 0, y1: 0, x2: 0, y2: 1},
							stops: [
								[0, Highcharts.Color(options.fillColor).setOpacity(0.3).get('rgba')]
							]
						}
					},
					areaspline: {
						fillOpacity: 0.5,
						threshold: null,
						lineWidth: 1,
						marker: {
							radius: options.markerRadius
						},
						states: {
							hover: {
								lineWidth: 1
							}
						},
						fillColor: {
							linearGradient: {x1: 0, y1: 0, x2: 0, y2: 1},
							stops: [
								[0, Highcharts.Color(options.fillColor).setOpacity(0).get('rgba')]
							]
						}
					},
					series: {
						point: {
							events: {
								click: function () {
									options.customEvent(this.point);
								}
							}
						}
					}
				},
				noData: {
					// Custom positioning/aligning options
					position: {
						//align: 'right',
						//verticalAlign: 'bottom'
					},
					// Custom svg attributes
					attr: {
						//'stroke-width': 1,
						//stroke: '#cccccc'
					},
					// Custom css
					style: {
						//fontWeight: 'bold',
						//fontSize: '15px',
						//color: '#202030'
					}
				},
				series: series
			});
		};

		this.radarOptions = {
			targetId: null,  //元素
			dataX: null,  //x轴数据
			dataY: null,  //y轴数据
			colors: ['#7cb5ec', '#434348', '#90ed7d', '#f7a35c', '#8085e9', '#f15c80', '#e4d354', '#8085e8', '#8d4653', '#91e8e1'],  //图线颜色
			title: null,  //头部文案
			titleOffset: 0, //头部文案左右偏移量
			title2: null,  //头部第二行文案
			title2Offset: 0, //头部第二行文案左右偏移量
			size: '80%',  //图标显示比例
			valueSuffix: null,  //单位
			showInLegend: false,  //是否显示图例
			legendLayout: 'vertical',  //图例布局方式
			legendAlign: 'right',  //图例横向布局
			legendVerticalAlign: 'middle',  //图例竖直布局
			legendBorderWidth: 0,  //图例边框
			shared: true,  //是否整块提示
			tooltipEnabled: false
		};
		this.initRadarChart = function () {
			var chart;
			var $than = this;
			$than.settings = $.extend({}, this.radarOptions, this.options);
			$than.settings.targetId.highcharts({
				colors: $than.settings.colors,
				chart: {
					polar: true,
					type: 'line'
				},
				title: {
					text: $than.settings.title,
					x: $than.settings.titleOffset
				},
				subtitle: {
					text: $than.settings.title2,
					x: $than.settings.title2Offset
				},
				pane: {
					size: $than.settings.size
				},
				xAxis: {
					categories: $than.settings.dataX,
					tickmarkPlacement: 'on',
					lineWidth: 0
				},
				yAxis: {
					gridLineInterpolation: 'polygon',
					lineWidth: 0,
					min: 0
				},
				tooltip: {
					enabled: $than.settings.tooltipEnabled,
					followTouchMove: true,
					shared: $than.settings.shared,
					pointFormat: '<span style="color:{series.color}">{series.name}: <b>${point.y:,.0f}</b><br/>'
				},
				legend: {
					enabled: $than.settings.showInLegend,
					layout: $than.settings.legendLayout,
					align: $than.settings.legendAlign,
					verticalAlign: $than.settings.legendVerticalAlign,
					borderWidth: $than.settings.legendBorderWidth
				},
				plotOptions: {
					line: {
						cursor: 'pointer',
						showInLegend: $than.settings.showInLegend
					}
				},
				series: $than.settings.dataY,
				exporting: {
					enabled: false
				}
			});
		};

		this.baseLineOptions = {
			targetId: null,//标签ID
			data: null,//数据
			chartsHeight: "160",//报表高度
			chartsWidth: "240",//报表宽度
			lineColor: "red",//数据线颜色
			dataIndex: 3,//默认现实数据下标
			tooltip: null,//自定义tooltip
			lineWidth: 1
		};
		this.initBaseLineChart = function () {
			var $than = this;
			//默认参数设置
			$than.settings = $.extend({}, this.baseLineOptions, this.options);
			//获取元素
			var targetId = $than.settings.targetId;
			//获取数据
			var data = $than.settings.data.data;
			//获取tooltip设置
			var tooltip = $than.settings.tooltip;
			//显示数据下表位
			var dataIndex = $than.settings.dataIndex;
			HbChartsData = data;
			//报表参数设置
			var options = {
				//时间区间按钮和日期控件
				rangeSelector: {
					enabled: false
				},
				//报表设置
				chart: {
					renderTo: targetId,
					//width: $than.settings.chartsWidth,
					//height: $than.settings.chartsHeight,
					marginTop: 10,
					marginLeft: 0
				},
				navigator: {
					enabled: false
				},
				legend: {
					enabled: false
				},
				scrollbar: {
					enabled: false
				},
				// 禁用版权信息
				credits: {
					enabled: false
				},
				plotOptions: {
					line: {
						cursor: 'pointer',
						//shadow: false,
						lineWidth: $than.settings.lineWidth,
						dataGrouping: {
							enabled: false,
							dateTimeLabelFormats: {
								millisecond: ['%Y-%m-%d', '%Y-%m-%d', ''],
								second: ['%Y-%m-%d', '%Y-%m-%d', ''],
								minute: ['%Y-%m-%d', '%Y-%m-%d', ''],
								hour: ['%Y-%m-%d', '%Y-%m-%d', ''],
								day: ['%Y-%m-%d', '%Y-%m-%d', ''],
								week: ['%Y-%m-%d', '%Y-%m-%d', ''],
								month: ['%Y-%m', '%Y-%m', ''],
								year: ['%Y', '%Y', '-%Y']
							}
						}
					},
					series: {
						compare: 'value'
					}
				},
				xAxis: {
					type: 'datetime',
					tickPixelInterval: ($than.settings.chartsWidth) / 3.2,
					dateTimeLabelFormats: {
						week: '%m-%e',
						month: '%m-%e',
						year: '%Y-%m'
					}
				},
				tooltip: {
					followTouchMove: true,
					formatter: function () {
						if ($than.settings.tooltipFormatter != null) {
							return $than.settings.tooltipFormatter(this.x, this.point);
						} else {
							var s = '<b>' + Highcharts.dateFormat('%Y-%m-%d', this.x) + '</b><br/>';
							var x = this.x;
							var HbChartsDataLength = HbChartsData.length;
							jQuery.each(HbChartsData, function (i, line) {
								var items = line.split(/\,/g);
								if (items.length >= 4) {
									var _time = Date.UTC(parseInt(items[0]), parseInt(items[1] - 1), parseInt(items[2]));
									if (_time == x) {
										s += '<span style="color:brown">收盘</span>: <b>' + items[3] + '</b><br/>';
										s += '<span style="color:brown">成交量</span>: <b>' + items[4] + '(万股)</b><br/>';
										s += '<span style="color:green">成交量</span>: <b>' + items[5] + '(万元)</b><br/>';
										if (items[6] > 0) {
											s += '<span style="color:deepskyblue">涨跌</span>: <b style="color:red">' + items[6] + '</b><br/>';
										} else {
											s += '<span style="color:deepskyblue">涨跌</span>: <b style="color:green">' + items[6] + '</b><br/>';
										}
										if (items[7] > 0) {
											s += '<span style="color:darkkhaki">涨跌幅</span>: <b style="color:red">' + items[7] + '</b><br/>';
										} else {
											s += '<span style="color:darkkhaki">涨跌幅</span>: <b style="color:green">' + items[7] + '</b><br/>';
										}
									}
								}
							});
							return s;
						}
					}
				},
				yAxis: {
					labels: {
						x: -($("#" + $than.settings.targetId).width()) + 45,
						formatter: function () {
							return this.value;
						}
					}
				},
				series: []
			};
			//拼数据
			options.series.push({
				name: '',
				color: $than.settings.lineColor,
				data: []
			});
			var seriesName = $than.settings.data.cats;
			var data0 = [];

			jQuery.each(data, function (i, line) {
				var items = line.split(/\,/g);
				if (items.length >= 4) {
					var _time = Date.UTC(parseInt(items[0]), parseInt(items[1] - 1), parseInt(items[2]));
					data0.push([_time, parseFloat(items[dataIndex])]);
				}
			});
			options.series[0].data = data0;
			var chart = new Highcharts.StockChart(options);
		};

		this.compareOptions = {
			targetId: null,//标签ID
			subTitle: null,//文案
			data: null,//数据
			seriesName: null,
			pmInfo: null,//基金经理信息
			lineColor: [],
			hideLegend: [],
			dataType: "direct",//数据类型    mingle 混合类型  direct  直接取值类型  name和data对应   single  单个图表类型
			showCredits: false,
			credits: {text: "", href: ""},
			isShowLegend: true,//是否显示图例
			legendAlign: "center",//图例位置
			legendBorderWidth: "0",//图例边框宽度
			legendBorderColor: "#ccc",//图例边框颜色
			pmIcon: 'http://static.howbuy.com/images/www/manager/smallpeople.png',
			chartsHeight: "160",//报表高度
			chartsWidth: "240",//报表宽度
			tooltip: null,
			tooltipFormatter: null,
			yAxisLabelsFormat: null
		};
		this.initCompareOptions = function () {
			var $than = this;
			$than.settings = $.extend({}, this.compareOptions, this.options);
			var targetId = $than.settings.targetId;
			var subTitle = $than.settings.subTitle;
			var data = $than.settings.data;
			var seriesName = $than.settings.seriesName;
			var chartsWidth = $than.settings.chartsWidth;
			var chartsHeight = $than.settings.chartsHeight;
			var options = {
				chart: {
					renderTo: targetId,
					type: 'line',
					marginTop: 10,
					marginLeft: 0
				},
				rangeSelector: {
					enabled: false
				},
				navigator: {
					enabled: false
				},
				legend: {
					enabled: $than.settings.isShowLegend,
					borderWidth: $than.settings.legendBorderWidth,
					align: $than.settings.legendAlign,
					borderColor: $than.settings.legendBorderColor,
					itemStyle: {'font-weight': 'normal'}
				},
				exporting: {
					enabled: false
				},
				scrollbar: {
					enabled: false
				},
				tooltip: {
					followTouchMove: true,
					formatter: function () {
						if ($than.settings.tooltipFormatter != null) {
							return $than.settings.tooltipFormatter(this.points ? this.points : this.point);
						} else {
							var s = '<b>' + Highcharts.dateFormat('%Y-%m-%d', this.x) + '</b><br/>';
							var fhbjzrq = Highcharts.dateFormat('%Y%m%d', this.x);
							var jjzz = '';
							if (navArrStr && navArrStr != '') {
								var __idx = navArrStr.indexOf(fhbjzrq + ":");
								if (__idx >= 0) {
									var __tstr = navArrStr.substring(__idx + fhbjzrq.length + 1);
									jjzz = __tstr.substring(0, __tstr.indexOf("|"));
								}
							}
							var __nJz = '';

							$.each(this.points, function (i, point) {
								s += '<span style="color:' + this.series.color + '">' + this.series.name + '</span>: <b>' + Highcharts.numberFormat(point.y, 4) + '</b><br/>';
							});
							return s;
						}
					}
				},
				subtitle: {
					text: $than.settings.subTitle,
					align: 'left',
					x: 50,
					y: 50,
					floating: true,
					style: {
						margin: "20",
						color: '#959595',
						fontWeight: 'bold'
					}
				},
				xAxis: {
					type: 'datetime',
					tickPixelInterval: ($than.settings.chartsWidth) / 3.8,
					isClick: true,
					dateTimeLabelFormats: {
						week: '%m-%e',
						month: '%m-%e',
						year: '%Y-%m'
					},
					ordinal: false,
					lineWidth: 0
					//min: 0
				},
				yAxis: {
					title: {
						text: ''
					},
					labels: {
						x: -($("#" + $than.settings.targetId).width()) + 45,
						formatter: function () {
							if ($than.settings.yAxisLabelsFormat != null) {
								return $than.settings.yAxisLabelsFormat(this.value);
							} else {
								return this.value;
							}
						}
					}
				},
				plotOptions: {
					series: {cursor: ''},
					line: {
						marker: {enabled: false},
						dataGrouping: {
							enabled: true,
							dateTimeLabelFormats: {
								millisecond: ['%Y-%m-%d', '%Y-%m-%d', ''],
								second: ['%Y-%m-%d', '%Y-%m-%d', ''],
								minute: ['%Y-%m-%d', '%Y-%m-%d', ''],
								hour: ['%Y-%m-%d', '%Y-%m-%d', ''],
								day: ['%Y-%m-%d', '%Y-%m-%d', ''],
								week: ['%Y-%m-%d', '%Y-%m-%d', ''],
								month: ['%Y-%m', '%Y-%m', ''],
								year: ['%Y', '%Y', '-%Y']
							}
						},
						cursor: 'pointer',
						shadow: false,
						lineWidth: 1,
						states: {
							/*状态*/
							hover: {
								/*(鼠标)悬浮状态*/
								lineWidth: 2    /*曲线宽*/
							}
						}
					}
				},
				credits: {
					enabled: $than.settings.showCredits
				},
				series: []
			};
			var navArrStr = '';
			var seriesLength = seriesName.length;
			var hideLegend = $than.settings.hideLegend;
			var hideLegendLength = $than.settings.hideLegend.length;
			var dataType = $than.settings.dataType;
			//mingle 混合类型  direct  直接取值类型  name和data对应   single  单个图表类型
			if (dataType === "direct") {
				//设置seriesName
				for (var i = 0; i < seriesLength; i++) {
					if (hideLegendLength > 0 && hideLegend.indexOf(i) > -1) {
						options.series.push({
							name: '',
							visible: false,
							color: $than.settings.lineColor[i],
							data: []
						});
					} else {
						options.series.push({
							name: '',
							color: $than.settings.lineColor[i],
							data: []
						});
					}
					options.series[i].name = seriesName[i];
					jQuery.each(data, function (j, line) {
						options.series[i].data.push([line[0], parseFloat(line[i + 1])]);
					});
				}
				var pmInfo = $than.settings.pmInfo;
				if (pmInfo) {
					var pmInfoLength = pmInfo.length;
					for (var ii = 0; ii < pmInfoLength; ii++) {
						var index = pmInfo[ii][2];
						var series = options.series[index];
						var seriesData = series.data;
						var seriesDataLength = seriesData.length;
						for (var jj = 0; jj < seriesDataLength; jj++) {
							if (pmInfo[ii][0] == seriesData[jj][0]) {
								var dataNeed = {
									x: pmInfo[ii][0],
									y: seriesData[jj][1],
									text: pmInfo[ii][1],
									title: pmInfo[ii][1],
									events: {
										click: function (e) {
											hs.htmlExpand(null, {
												pageOrigin: {
													x: e.pageX,
													y: e.pageY
												},
												headingText: Highcharts.dateFormat('%Y-%m-%d', this.x),
												maincontentText: this.text,
												width: 200
											});
										}
									},
									cursor: 'pointer',
									marker: {
										cursor: 'pointer',
										enabled: true,
										symbol: 'url(http://static.howbuy.com/images/www/manager/smallpeople.png)'
									}
								};
								seriesData[jj] = dataNeed;
							}
						}
					}
				}
			}
			if (dataType === "mingle") {
				var navArrStr = '';

				//设置图例颜色
				options.series.push({
					name: '',
					color: '#4c77aa',
					data: []
				});
				options.series.push({
					name: '',
					color: '#aa604c',
					data: []
				});
				options.series.push({
					name: '',
					color: '#b5b5b5',
					data: []
				});
				//设置图例名称
				options.series[0].name = seriesName[0];
				options.series[1].name = seriesName[1];
				options.series[2].name = seriesName[2];
				//最后一个图例不显示
				if (seriesName[3] != undefined) {
					options.series.push({
						name: '',
						visible: false,
						color: '#799a62',
						data: []
					});
					options.series[3].name = seriesName[3];
				}

				var data0 = [],
					data1 = [],
					data2 = [],
					data3 = [];
				jQuery.each(data, function (i, line) {
					var items = line.split(/\,/g);
					if (items.length >= 4) {
						var _time = Date.UTC(parseInt(items[0]), parseInt(items[1]), parseInt(items[2]));
						data0.push([_time, parseFloat(items[3])]);
						data1.push([_time, parseFloat(items[4])]);
						data2.push([_time, parseFloat(items[5])]);
						//data0.push(items[3]);
						//data1.push(items[4]);
						//data2.push(items[5]);
						if (seriesName[3] != undefined) {
							if (seriesName[3] == "创业板") {
								data3.push([_time, parseFloat(items[9])]);
							} else if (seriesName[3] == "标普500") {
								data3.push([_time, parseFloat(items[10])]);
							}
						}
						if (navArrStr == '') {
							navArrStr = Highcharts.dateFormat('%Y%m%d', _time) + ":" + items[6] + "|";
						} else {
							navArrStr += Highcharts.dateFormat('%Y%m%d', _time) + ":" + items[6] + "|";
						}
					}
				});
				options.series[0].data = data0;
				options.series[1].data = data1;
				options.series[2].data = data2;
				if (seriesName[3] != undefined) {
					options.series[3].data = data3;
				}
			}
			if (dataType === "single") {
				//设置seriesName
				for (var i = 0; i < seriesLength; i++) {
					if (hideLegendLength > 0 && hideLegend.indexOf(i) > -1) {
						options.series.push({
							name: '',
							visible: false,
							color: $than.settings.lineColor[i],
							data: []
						});
					} else {
						options.series.push({
							name: '',
							color: $than.settings.lineColor[i],
							data: []
						});
					}

					options.series[i].name = seriesName[i];
					var dataLength = data[i].length;
					for (var j = 0; j < dataLength; j++) {
						options.series[i].data.push([data[i][j][0], parseFloat(data[i][j][1])]);
					}
				}
				var pmInfo = $than.settings.pmInfo;
				if (pmInfo) {
					var pmInfoLength = pmInfo.length;
					for (var ii = 0; ii < pmInfoLength; ii++) {
						var index = pmInfo[ii].split(",")[2];
						var series = options.series[index];
						var seriesData = series.data;
						var seriesDataLength = seriesData.length;
						for (var jj = 0; jj < seriesDataLength; jj++) {
							if (pmInfo[ii].split(",")[0] == seriesData[jj][0]) {
								var dataNeed = {
									x: pmInfo[ii].split(",")[0],
									y: seriesData[jj][1],
									text: pmInfo[ii].split(",")[1],
									title: pmInfo[ii].split(",")[1],
									events: {
										click: function (e) {
											hs.htmlExpand(null, {
												pageOrigin: {
													x: e.pageX,
													y: e.pageY
												},
												headingText: Highcharts.dateFormat('%Y-%m-%d', this.x),
												maincontentText: this.text,
												width: 200
											});
										}
									},
									cursor: 'pointer',
									marker: {
										cursor: 'pointer',
										enabled: true,
										symbol: 'url(http://static.howbuy.com/images/www/manager/smallpeople.png)'
									}
								};
								seriesData[jj] = dataNeed;
							}
						}
					}
				}
			}
			//设置数据
			var chart = new Highcharts.StockChart(options);
		};
	}

	HbCharts = (function () {
		return {
			loadPieChart: function (options) {
				var charts = new showCharts();
				charts.options = options;
				return charts.initPicChart();
			},
			loadLineChart: function (options) {
				var charts = new showCharts();
				charts.options = options;
				return charts.initLineChart();
			},
			loadColumnChart: function (options) {
				var charts = new showCharts();
				charts.options = options;
				return charts.initColumnChart();
			},
			loadAreasplineChart: function (options) {
				var charts = new showCharts();
				charts.options = options;
				return charts.initAreasplineChart();
			},
			loadRadarChart: function (options) {
				var charts = new showCharts();
				charts.options = options;
				return charts.initRadarChart();
			},
			loadBaseLine: function (options) {
				StopWatch.start();
				var charts = new showCharts();
				charts.options = options;
				var returnChart = charts.initBaseLineChart();
				StopWatch.stop("initBaseLineChart");
				return returnChart;
			},
			loadCompareOptions: function (options) {
				var charts = new showCharts();
				charts.options = options;
				return charts.initCompareOptions();
			}
		};
	})();
})(jQuery);
var StopWatch = (function () {
	var records = [];
	return {
		start: function () {
			records.push(new Date().getTime());
		}, stop: function (flag) {
			if (records.length == 0) {
				return 0;
			}
			flag = flag || "duration";
			var end = new Date().getTime();
			var start = records.pop();
			var duration = end - start;
			console.log("Execution Method  【" + flag + "】  Depletion Period:" + (end - start) + "MS");
			return duration;
		}
	};
})();
Date.prototype.format = function (format) {
	var o = {
		"M+": this.getMonth() + 1, //month
		"d+": this.getDate(),    //day
		"h+": this.getHours(),   //hour
		"m+": this.getMinutes(), //minute
		"s+": this.getSeconds(), //second
		"q+": Math.floor((this.getMonth() + 3) / 3),  //quarter
		"S": this.getMilliseconds() //millisecond
	}
	if (/(y+)/.test(format)) format = format.replace(RegExp.$1,
		(this.getFullYear() + "").substr(4 - RegExp.$1.length));
	for (var k in o)if (new RegExp("(" + k + ")").test(format))
		format = format.replace(RegExp.$1,
			RegExp.$1.length == 1 ? o[k] :
				("00" + o[k]).substr(("" + o[k]).length));
	return format;
};
