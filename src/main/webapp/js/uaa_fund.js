window.context_path = "";
var HB = "HB000A001";

var curpage=1;
var pagerows = 10;
var maxpage = 5;
var orderby="stat_dt";
var order="desc";
var params = "";
var paraname = ["","openInst","openPlatform","cooperateType","openWangDian"];
var lastClickChannel = "";
var lastClickTrend = "";
var lastChannelOrder = "";
var lastTrendOrder = "";

/**
 * 加载平台数据
 * @returns {String}
 */
function loadChannel(json){
	var all = '';
	var jigouHead = '<dl class="mt10 khjg clearfix"><dt class="fl">开户机构：</dt><dd><ul class="clearfix"><li class="current" para="openInst%-1"><a href="javascript:void(0)" target="_self">全部</a><p style="display: none;">全部机构</p></li>';
	var Tail = '</ul></dd></dl>';
	
	var instLen = json.length;
	for (var i =  0; i < instLen; i++) {
		jigouHead = jigouHead + ' <li class="'+json[i].discode+'" para="openInst%'+json[i].discode+'"><a href="#" target="_self">'+json[i].disname+'</a></li>';

		var pingtaiHead;
		var platLen = json[i].chan.length;
		if(json[i].discode == HB){
			pingtaiHead = '<dl class="mt10  Inst" id ="openInst'+json[i].discode+'"><dt id="pingtai">开户平台:</dt><dd><ul class="clearfix"><li para="openPlatform%'+json[i].discode+'%-1"><a href="#" target="_self">全部</a><p style="display: none;">全部平台</p></li>';
			for (var j =  0; j < platLen; j++) {

				pingtaiHead = pingtaiHead + ' <li para="openPlatform%'+json[i].discode+"%"+json[i].chan[j].tradechan+'"><a href="#" target="_self">'+json[i].chan[j].channame+'</a></li>';

				var hzlxHead = '<dl class="mt10  Platform" id ="openPlatform'+json[i].discode+json[i].chan[j].tradechan+'"><dt>合作类型：</dt><dd><ul class="clearfix"><li para="cooperateType%'+json[i].discode+'%'+json[i].chan[j].tradechan+'%-1"><a href="#" target="_self">全部</a><p style="display: none;">全部合作类型</p></li>';

				var selectAll = '';
				var coopLen = json[i].chan[j].hzlxname.length;
				for(var k =  0; k < coopLen; k++)
				{
					hzlxHead = hzlxHead + ' <li para="cooperateType%'+json[i].discode+"%"+json[i].chan[j].tradechan+"%"+json[i].chan[j].hzlxname[k].hzlxcode+'"><a href="#" target="_self">'+json[i].chan[j].hzlxname[k].hzlx+'</a></li>';

					var select = '<select name="cooperateType" id="cooperateType'+json[i].discode+json[i].chan[j].tradechan+json[i].chan[j].hzlxname[k].hzlxcode+'" style="margin-top: 2px;float:left;font-family: 微软雅黑;"><option value="-1">开户网点(渠道)</option>';
					for (var l = json[i].chan[j].hzlxname[k].outlet.length - 1; l >= 0; l--) {
						select = select + '<option value="'+json[i].chan[j].hzlxname[k].outlet[l].outletcode+'" id="'+json[i].chan[j].hzlxname[k].outlet[l].outletcode+'">'+json[i].chan[j].hzlxname[k].outlet[l].outletname+'</option>';
					};
					selectAll = selectAll + select + '</select>';
				}
				all = hzlxHead + selectAll + Tail + all;
			}
		}
		else
		{
			pingtaiHead = '<dl class="mt10  Inst" id ="openInst'+json[i].discode+'"><dt>开户网点：</dt><dd><ul class="clearfix"><li para="openWangDian%'+json[i].discode+'%-1"><a href="#" target="_self">全部</a><p style="display: none;">全部网点</p></li>';
			for (var j =  0; j < platLen; j++) {
				var coopLen = json[i].chan[j].hzlxname.length;
				for(var k =  0; k < coopLen; k++)
				{
					var oulen = json[i].chan[j].hzlxname[k].outlet.length;
					for (var l = 0; l < oulen; l++) {
						pingtaiHead = pingtaiHead + ' <li para="openWangDian%'+json[i].discode+"%"+json[i].chan[j].tradechan+"%"+json[i].chan[j].hzlxname[k].hzlxcode+"%"+json[i].chan[j].hzlxname[k].outlet[l].outletcode+'"><a href="#" target="_self">'+json[i].chan[j].hzlxname[k].outlet[l].outletname+'</a></li>';
					};
				}
			}
		}
		
		all = pingtaiHead + Tail + all;
	}
	all = jigouHead + Tail + all;
	return all;
}


/**
 * 加载查询条件
 */
function loadFundCondition(){
	var con_li = $("#fundCondition li.current");
	if(con_li.length>=1){
		var con_exp_array =new Array();
		
		con_li.each(function(index){
			var  p = $(this).find("p");
			var  para = $(this).attr("para");//para="analysisWd%1"
			var params = para.split("%");
			var con = "";
			if(params[0]=="analysisWd"){
				con = $(this).find("a:eq(0)").html();
			}else{
				
				if(p.length>=1){
					con = p.html();
				}else{
					con = $(this).find("a:eq(0)").html();
				}
			}
			con_exp_array[index] = con;
		});
		var wd = $("#qudaoChoose select:visible").eq(0).find("option:selected");
		if(null!=wd && wd.length>=1){
			var wdVal = wd.val();
			if(null!=wdVal && "-1"!=wdVal){
				var text = wd.text();
				con_exp_array.push("");
				con_exp_array[con_exp_array.length-1]=con_exp_array[con_exp_array.length-2];
				con_exp_array[con_exp_array.length-2]=text;
			}
		}
		var con_exp = con_exp_array.join(" - ");
		$("#fundCondition_exp").html(con_exp);
	}
}


/**
 * 加载折线图
 * @param ele
 * @param data
 * @param attrMap 属性对象Map
 */
function loadLineChart(ele, data, attrMap) {
	var valueSuffix = attrMap.get("valueSuffix")==null?'':attrMap.get("valueSuffix");
	
	$(ele).highcharts(
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
 * 向所有的checkbox注册click事件
 */
function bindCheckboxEvent(){
	//var allEle=["#channelDetailAll","#trendDetailAll","#fundDetailAll"];
	//var allTabEle=["#fundChannelDetailTab","#trendDetailTable","#fundTypeDetailTab"];
	
	$("#channelDetailAll input[type=checkbox]").click(function(){
		var ch = $(this).prop("checked");
		var val = $(this).val();
		if("-1"==val){
			//全选按钮
			var cst = $(this).attr("cst");
			$("#channelDetailAll table tr[cst='"+cst+"'] input[type=checkbox]").each(function(j,inp){
				var inpVal = $(inp).val();
				if(ch){
					$("#fundChannelDetailTab").find("td[head='"+inpVal+"']").show();
				}else{
					$("#fundChannelDetailTab").find("td[head='"+inpVal+"']").hide();
				}
			});
		}else {
			if(ch){
				$("#fundChannelDetailTab").find("td[head='"+val+"']").show();
			}else{
				$("#fundChannelDetailTab").find("td[head='"+val+"']").hide();
			}
		}
	});
	
	$("#trendDetailAll input[type=checkbox]").click(function(){
		var ch = $(this).prop("checked");
		var val = $(this).val();
		if("-1"==val){
			//全选按钮
			var cst = $(this).attr("cst");
			$("#trendDetailAll table tr[cst='"+cst+"'] input[type=checkbox]").each(function(j,inp){
				var inpVal = $(inp).val();
				if(ch){
					$("#trendDetailTable").find("td[head='"+inpVal+"']").show();
				}else{
					$("#trendDetailTable").find("td[head='"+inpVal+"']").hide();
				}
			});
		}else {
			if(ch){
				$("#trendDetailTable").find("td[head='"+val+"']").show();
			}else{
				$("#trendDetailTable").find("td[head='"+val+"']").hide();
			}
		}
	});
	
	$("#fundDetailAll input[type=checkbox]").click(function(){
		var ch = $(this).prop("checked");
		var val = $(this).val();
		if("-1"==val){
			//全选按钮
			var cst = $(this).attr("cst");
			$("#fundDetailAll table tr[cst='"+cst+"'] input[type=checkbox]").each(function(j,inp){
				var inpVal = $(inp).val();
				if(ch){
					$("#fundTypeDetailTab").find("td[head='"+inpVal+"']").show();
				}else{
					$("#fundTypeDetailTab").find("td[head='"+inpVal+"']").hide();
				}
			});
		}else {
			if(ch){
				$("#fundTypeDetailTab").find("td[head='"+val+"']").show();
			}else{
				$("#fundTypeDetailTab").find("td[head='"+val+"']").hide();
			}
		}
	});
}



var openacc_quoat_map = new HashMap();
/**
 * 判断指标是否显示开户维度下的开户指标
 * @param wd
 * @param _quota
 * @param _quota_array
 */
function showNotOpenAccQuoat(wd,fundType,_quota_array){
	
	if(wd=="1"){
		var status = true;
		if(fundType!=""&&fundType!="-1"){
			status = false;
		}else{
			return;
		}
		var select = $("#graph ul li.other select#select_quotatype");
		for(var i=0;i<_open_acc_quoat_array.length;i++){
			var quotatype = _open_acc_quoat_array[i];
			var li = $("#graph ul li[quotatype='"+quotatype+"']");
			if(li.length==0){
				if(status){/*
					var opSet = openacc_quoat_map.keySets();
					for(var j=0;j<opSet.length;j++){
						var index = opSet[j];
						var opt = openacc_quoat_map.get(index);
						document.getElementById("select_quotatype").add(opt,index);
					}
					//document.getElementById("select_quotatype").add(new Option("drscyk","验卡人数"),3);
					openacc_quoat_map=new HashMap(); 
				*/}else{
					var op = select.find("option[value='"+quotatype+"']");
					if(op.length>=1){
						var index = op.index();
						var opt = new Option(op.text(),op.val());
						openacc_quoat_map.put(index,opt);
						op.detach();
					}
				}
			}else{
				li.hide();
			}
			
			//处理checkbox 
			var ch = $("div.tab_box input[type=checkbox][value='"+quotatype+"']");
			ch.parent().parent().detach();
			$("div.tab_box td[head='"+quotatype+"']").detach();
		}
		var livis = $("div.chartbox_tab ul.fl").find("li[quotatype]:visible");
		
		var len = 6 - livis.length;
		//这里先这么写着吧，后续灵活添加li对象，绑定事件
		var ops = select.find("option");
		$(ops).each(function(i,obj){
			if(i>len){
				return;
			}
			var _this = $(obj);
			var val = _this.val();
			if(""!=val){
				var vli = $("#graph ul li[quotatype='"+val+"']");
				vli.show();
				_this.detach();
			}
		});
	}
}

var tab =["fundChannelDetailTab","trendDetailTable","fundTypeDetailTab"];

var frozenTableTi = null;
var count = 0;
function frozenChannelTable(){
	if(count>=15){
		clearInterval(frozenTableTi);
		count=0;
		return;
	}
	var div = $("#"+tab[0]).parent();
	$("div[name='oDivL_"+tab[0]+"']").each(function(i,obj){
		var top = $(obj).css("top").replace("px","");
		top = parseFloat(top);
		var div_top = div.offset().top;
		
		var left = $(obj).offset().left;
		if(left==0){
			$(obj).hide();
			$(obj).remove();
		}else if(top>0 && div_top>1000 && Math.abs(top-div_top)>=5){
			clearInterval(frozenTableTi);
			count=0;
			return;
		}else {
			$(obj).hide();
			$(obj).remove();
		}
    });
	var exp = $('#gmGraph #container2:has(div)');
	if(exp.length>=1){
		var col = $("#fundChannelDetailTab #fundChannelDetailThead tr td.wdqd").length;
		$("#"+tab[0]).FrozenTable(0,0,col);
		var odiv = $("#oDivL_"+tab[0]);
		if(odiv.length>=1){
			var topd = odiv.css("top").replace("px","");
			topd = parseFloat(topd);
			var div_top = div.offset().top;
			if(topd>0 && div_top>1000 && Math.abs(topd-div_top)<=5){
				clearInterval(frozenTableTi);
				count=0;
				return;
			}else{
				count++;
			}
		}
	}else{
		frozenTableTi = setInterval(frozenChannelTable, 1500);
		count++;
	}
}


var frozenTableTrend = null;
var count_trend = 0;
function frozenTrendTable(){
	if(count_trend>=15){
		clearInterval(frozenTableTrend);
		count_trend=0;
		return;
	}
	var div = $("#"+tab[1]).parent();
	$("div[name='oDivL_"+tab[1]+"']").each(function(i,obj){
		var top = $(obj).css("top").replace("px","");
		top = parseFloat(top);
		var div_top = div.offset().top;
		
		var left = $(obj).offset().left;
		if(left==0){
			$(obj).hide();
			$(obj).remove();
		}else if(top>0 && div_top>1000 && Math.abs(top-div_top)>=5){
			clearInterval(frozenTableTrend);
			count_trend=0;
			return;
		}else {
			$(obj).hide();
			$(obj).remove();
		}
    });
	var exp = $('#gmGraph #container2:has(div)');
	if(exp.length>=1){
		$("#"+tab[1]).FrozenTable(0,0,1);
		var odiv = $("#oDivL_"+tab[1]);
		if(odiv.length>=1){
			var topd = odiv.css("top").replace("px","");
			topd = parseFloat(topd);
			var div_top = div.offset().top;
			if(topd>0 && div_top>1000 && Math.abs(topd-div_top)<=5){
				clearInterval(frozenTableTrend);
				count_trend=0;
				return;
			}else{
				count_trend++;
			}
		}
	}else{
		frozenTableTrend = setInterval(frozenTrendTable, 1600);
		count_trend++;
	}
}


var frozenTableFund = null;
var count_fund = 0;
function frozenFundTable(){
	if(count_fund>=15){
		clearInterval(frozenTableFund);
		count_fund=0;
		return;
	}
	var div = $("#"+tab[2]).parent();
	$("div[name='oDivL_"+tab[2]+"']").each(function(i,obj){
		var top = $(obj).css("top").replace("px","");
		top = parseFloat(top);
		var div_top = div.offset().top;
		
		var left = $(obj).offset().left;
		if(left==0){
			$(obj).hide();
			$(obj).remove();
		}else if(top>0 && div_top>1000 && Math.abs(top-div_top)>=5){
			clearInterval(frozenTableFund);
			count_fund=0;
			return;
		}else {
			$(obj).hide();
			$(obj).remove();
		}
    });
	var exp = $('#gmGraph #container2:has(div)');
	if(exp.length>=1){
		$("#"+tab[2]).FrozenTable(0,0,1);
		var odiv = $("#oDivL_"+tab[2]);
		if(odiv.length>=1){
			var topd = odiv.css("top").replace("px","");
			topd = parseFloat(topd);
			var div_top = div.offset().top;
			if(topd>0 && div_top>1000 && Math.abs(topd-div_top)<=5){
				clearInterval(frozenTableFund);
				count_fund=0;
				return;
			}else{
				count_fund++;
			}
		}
	}else{
		frozenTableFund = setInterval(frozenFundTable, 1700);
		count_fund++;
	}
}


function hideF(tag){
	$("div[name='oDivL_"+tab+"']").each(function(i,obj){
		$(obj).hide();
    	$(obj).remove();
    });
}

/**趋势明细、渠道明细开户维度(1)默认显示指标*/
var  mr_qarray_kh = ["drkh","drscjq","drxdrs","drxdje"
 ,"drzfrs","drzfje","drqrjycjrs","drqrjycjje","drkhdrjyrs","drxzjyrs"];
/**趋势明细、渠道明细交易维度(2)默认显示指标*/
var  mr_qarray_jy = ["drxdrs","drxdje","drzfrs","drzfje","drqrjycjrs","drqrjycjje","drxzjyrs"];

/**基金明细开户维度默认显示指标*/
var fund_qarray_kh=["drxdrs","drxdje","drzfrs","drzfje","drqrjycjrs","drqrjycjje","drxzjyrs","drxzjyje"];
/**基金明细交易维度默认显示指标*/
var fund_qarray_jy=["drxdrs","drxdje","drzfrs","drzfje","drqrjycjrs","drqrjycjje","drxzjyrs","drxzjyje"];;
/**
 * 默认指标
 * @param wd 维度
 * @param tab 显示tab的位置
 */
function checkClickQuota(wd,tab){
	if(wd=="1"){
		if(tab=="1"){//趋势
			//趋势明细-开户维度指标显示隐藏
			$("#trendDetailAll #OpenTrendNorm input[type=checkbox]").each(function(i,obj){
				var _this =$(obj);
				var q = _this.val();
				if("-1"!=q){
					var isc = isContains(q,mr_qarray_kh);
					if(!isc){
						_this.click();
					}
				}
			});
		}else if(tab=="2"){//渠道
			//渠道明细-开户维度指标显示隐藏
			$("#channelDetailAll #OpenChannelNorm input[type=checkbox]").each(function(i,obj){
				var _this =$(obj);
				var q = _this.val();
				if("-1"!=q){
					var isc = isContains(q,mr_qarray_kh);
					if(!isc){
						_this.click();
					}
				}
			});
		}else if(tab=="3"){//基金
			//基金明细-开户维度指标显示隐藏
			$("#OpenFundNorm #fundDetailOpen input[type=checkbox]").each(function(i,obj){
				var _this =$(obj);
				var q = _this.val();
				if("-1"!=q){
					var isc = isContains(q,fund_qarray_kh);
					if(!isc){
						_this.click();
					}
				}
			});
		}
		
		
	}else if(wd=="2"){
		if(tab=="1"){//趋势
			//趋势明细-开户维度指标显示隐藏
			$("#trendDetailAll #TradeTrendNorm input[type=checkbox]").each(function(i,obj){
				var _this =$(obj);
				var q = _this.val();
				if("-1"!=q){
					var isc = isContains(q,mr_qarray_jy);
					if(!isc){
						_this.click();
					}
				}
			});
		}else if(tab=="2"){//渠道
			//渠道明细-开户维度指标显示隐藏
			$("#channelDetailAll #TradeChannelNorm input[type=checkbox]").each(function(i,obj){
				var _this =$(obj);
				var q = _this.val();
				if("-1"!=q){
					var isc = isContains(q,mr_qarray_jy);
					if(!isc){
						_this.click();
					}
				}
			});
		}else if(tab=="3"){//基金
			//基金明细-交易维度指标显示隐藏 
			$("#TradeFundNorm #fundDetailTrade input[type=checkbox]").each(function(i,obj){
				var _this =$(obj);
				var q = _this.val();
				if("-1"!=q){
					var isc = isContains(q,fund_qarray_jy);
					if(!isc){
						_this.click();
					}
				}
			});
		}
	}
}



//allNormId:所有checkbox选择器,openNormId：开户指标选择器,tradeNormId交易指标选择器
function detailChecked(allNormId,openNormId,tradeNormId){
	var params = "";
	var jsall = allNormId + " div.detail_filter";
	var inpEle = null;
	$(jsall).each(function(i,obj){
		var cs = $(obj).css("display");
		if(cs=="block"){
			inpEle = obj;
		}
	});
	if(null ==inpEle){
		return;
	}
	var inpEleSe = "input[type=checkbox]";
	$(inpEle).find(inpEleSe).each(function(i){
		if(!$(this).prop("checked")){
			$(this).prop("checked","checked");
		}
	});
	 

	var openNorm = "";
	var tradeNorm = "";
	var openNormArray = [];
	var tradeNormArray = [];
	$(openNormId).each(function(i){
		if($(this).prop("checked")){
			var qval = $(this).val(); 
			if("-1"!=qval){
				openNormArray.push(qval);
			}
		};
	});
	openNorm = openNormArray.join("$");
	$(tradeNormId).each(function(i){
		if ($(this).prop("checked")) {
			var tval =$(this).val();
			if("-1"!=tval){
				tradeNormArray.push(tval);
			}
		};
	});
	tradeNorm = tradeNormArray.join("$");

	params = "openNorm=" + openNorm + "&tradeNorm=" + tradeNorm;
	return params;
}

function getSearchParam(){
	var channel = $("#fundCondition_exp").text().replace(/\s/g,"");
	var time = $("#fundCondition_exp").parent().find(" > div").text().replace(/\s/g,"");
	var param = channel +time;
	return param;
}

function openBuildUrl(uri,detailParams){
	var param = getSearchParam();
	var currentParam = "&currentParam="+param;
	var url=uaa_context_path+uri+'?'+params+'&'+detailParams+currentParam;
	window.open(encodeURI(url));
}


function selectQuato(){
	var wd=$("#fenxiweidu li.current").attr("para").split("%")[1];
	if('1'==wd){//开户维度
		$("#channelDetailOpen input[type=checkbox]").prop("checked",true);
		
		//趋势明细全选事件触发
		$("#trendDetailOpen input[type=checkbox]").prop("checked",true);
		
		//基金类型明细全选事件触发
		$("#fundDetailOpen input[type=checkbox]").prop("checked",true);
		
	}else if('2'==wd){//交易维度
		$("#channelDetailTrade input[type=checkbox]").prop("checked",true);
		
		$("#trendDetailTrade input[type=checkbox]").prop("checked",true);
		
		$("#fundDetailTrade input[type=checkbox]").prop("checked",true);
	}
}
