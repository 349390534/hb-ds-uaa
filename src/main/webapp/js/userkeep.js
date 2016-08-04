/**
 * 用户留存js
 */


/**
 *初始化年份 
 */
function loadYear(){
	var yearArray = [];
	var fromYear = 2015;//开始年份
	var now = new Date();
	var nowyear = now.getFullYear();
	var month = now.getMonth()+1;
	if(month<=1)
		nowyear -=1;
	for(var y=nowyear;y>=fromYear;y-- ){
		var op="<option value='"+y+"'>"+y+"年</option>";
		yearArray.push(op);
	}
	var yhtml = yearArray.join("");
	$("#yearSelect").html(yhtml);
}

/**
 * 加载月份
 */
function loadMonth(){
	var yearArray = [];
	var fromYear = 2015;//开始年份
	var now = new Date();
	var nowyear = now.getFullYear();
	var month = now.getMonth()+1;
	if(month<=1)
		nowyear -=1;
	for(var y=nowyear;y>=fromYear;y-- ){
		for(var m=12;m>=1;m--){
			if(y==nowyear){
				if(m>=month)continue;
			}
			var _0 ="0";
			var v = "",mh = "";
			if(m<=9)
				mh =_0+m;
			else
				mh = m;
			v = y+""+mh;
			var op="<option value='"+v+"'>"+y+"年"+mh+"月</option>";
			yearArray.push(op);
		}
	}
	
	var mhtml = yearArray.join("");
	$("#monthSelect").html(mhtml);
}

/**
 * 初始化月份
 * @param mon
 */
function initSelectMonth(mon){
	var ops = $("#monthSelect").find("option");
	if(null!=ops){
		var array = [];
		ops.each(function(i,obj){
			var _this = $(obj);
			var v = _this.val();
			array.push(v);
			if(v==mon){
				return false;
			}
		});
		var newArray = array.reverse().slice(0, 12).reverse();
		var mArray = [];
		for(var i=0;i<newArray.length;i++){
			var v = newArray[i];
			var mh = $("#monthSelect option[value="+v+"]").text();
			var op="<option value='"+v+"'>"+mh+"</option>";
			mArray.push(op);
		}
		var mhtml = mArray.join("");
		$("#chart_month_from").html(mhtml);
		$("#chart_month_from option[value="+mon+"]").attr("selected",true);
	}
}

/**
 * 图表下拉框改变事件
 */
function eventChartMonthSelect(jsonData){
	$("#chart_month_from").change(function(){
		debugger;
		var _this = $(this);
		var mon = _this.val();
		if(!drawMonth(jsonData.list, mon)){
			$("#xzjr_rs").html("0");
			$("#monthChart").html("no data");
		}
	});
}
/**
 * 加载平台数据
 * @returns {String}
 */
function loadChannel(json){
	var all = '';
	var jigouHead = '<dl class="mt10 khjg clearfix"><dt class="fl">开户机构：</dt><dd><ul class="clearfix"><li class="current" para="openInst%-1"><a href="javascript:void(0)" target="_self">全部</a></li>';
	var Tail = '</ul></dd></dl>';
	
	var instLen = json.length;
	for (var i =  0; i < instLen; i++) {
		jigouHead = jigouHead + ' <li class="'+json[i].discode+'" para="openInst%'+json[i].discode+'"><a href="#" target="_self">'+json[i].disname+'</a></li>';

		var pingtaiHead;
		var platLen = json[i].chan.length;
		if(json[i].discode == HB){
			pingtaiHead = '<dl class="mt10  Inst" id ="openInst'+json[i].discode+'"><dt id="pingtai">开户平台:</dt><dd><ul class="clearfix"><li para="openPlatform%'+json[i].discode+'%-1"><a href="#" target="_self">全部</a></li>';
			for (var j =  0; j < platLen; j++) {

				pingtaiHead = pingtaiHead + ' <li para="openPlatform%'+json[i].discode+"%"+json[i].chan[j].tradechan+'"><a href="#" target="_self">'+json[i].chan[j].channame+'</a></li>';

				var hzlxHead = '<dl class="mt10  Platform" id ="openPlatform'+json[i].discode+json[i].chan[j].tradechan+'"><dt>合作类型：</dt><dd><ul class="clearfix"><li para="cooperateType%'+json[i].discode+'%'+json[i].chan[j].tradechan+'%-1"><a href="#" target="_self">全部</a></li>';

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
				//all = hzlxHead + selectAll + Tail + all;暂不计算好买网点数据
				all = hzlxHead + Tail + all;
			}
		}
		else
		{
			pingtaiHead = '<dl class="mt10  Inst" id ="openInst'+json[i].discode+'"><dt>开户网点：</dt><dd><ul class="clearfix"><li para="openWangDian%'+json[i].discode+'%-1"><a href="#" target="_self">全部</a></li>';
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
 * 整体留存tab切换
 */
function tabToggleEvent(){
	$("#tab li").click(function(){
		var _this = $(this);
		_this.addClass("current").siblings("li").removeClass("current");
		var index = _this.index();
		$(".user-mx-con").eq(index).show().siblings(".user-mx-con").hide();
	});
}
var mTgIndex=-1;
/**
 * 按月留存tab切换
 */
function tabToggleEventM(jsonData){
	$("#tabm li").click(function(){
		var _this = $(this);
		var index = _this.index();
		if(mTgIndex==index)return;
		mTgIndex=index;
		_this.addClass("current").siblings("li").removeClass("current");
		$("div[tag='chart']").eq(index).show().siblings().hide();
		//画图
		var month=$("#monthSelect").val();
		if(index==0){
			drawMonth(jsonData, month);
		}else if(index==1){
			var quota = $("#_chart_select option:selected").val();
			var quotaname = $("#_chart_select option:selected").text();
			drawMutilMonth(jsonData, month, quota, quotaname);
		}
	});
}

var contextPath = window.uaa_context_path ||""; 
var paraname = new Array("analysisWd","openInst","openPlatform","cooperateType","openWangDian");
/**
 * 提交查询-年
 */
function submitQuery(){
	$("#submit").click(function(){
		var _wdli = $(".fwqd ul li.current");
		var wd = _wdli.attr("wd");
		var paramMap = new HashMap();
		paramMap.put("analysisWd",wd);
		$("#qudaoChoose li.current").each(function(i,obj){
			var para = $(obj).attr("para"); 
			var arr=para.split("%");
			var len = arr.length;
			if(len>=2){
				var pType = arr[0];
				var val = arr[len-1];
				if(val!="-1"){
					paramMap.put(pType,val);
				}
			}
		});
		var year = $("#yearSelect").val();
		var params="year="+year;
		var max=paraname.length;
		var gid = 15;
		for(var i = 0; i < max; i++)
		{
		 	var pType=paraname[i];
			var paraval = paramMap.get(pType);
			if(null!=paraval && ""!=paraval && "-1"!=paraval){
				if(!params)
					params = "?" + pType+ "=" + paraval;
				else
					params = params + "&" + pType+ "=" + paraval;
				
				if(pType=="openInst"){//开户机构
					gid = 7;
				}else if(pType=="openPlatform"){//开户平台
					gid = 3;
				}else if(pType=="cooperateType"){//合作类型
					gid = 1;
				}else if(pType=="openWangDian"){//开户网点
					gid = 0;
				}
			}
		}
		params+="&gid="+gid;
		var reqObj={
				url:contextPath+"/auth/ukeep/loadData.htm",
		        postMethod:'POST',
		        params:params,
		        callback:function(data){
		        	$('#ukeepGraphDetail').html(data);
		        }
	        };
		ajaxRequest(reqObj);
        $('#ukeepGraphDetail').html(loadImg);
	});
}
/**
 * 查询事件-月
 */
function submitQueryM(){
	$("#submitM").click(function(){
		var _wdli = $(".fwqd ul li.current");
		var wd = _wdli.attr("wd");
		var paramMap = new HashMap();
		paramMap.put("analysisWd",wd);
		$("#qudaoChoose li.current").each(function(i,obj){
			var para = $(obj).attr("para"); 
			var arr=para.split("%");
			var len = arr.length;
			if(len>=2){
				var pType = arr[0];
				var val = arr[len-1];
				if(val!="-1"){
					paramMap.put(pType,val);
				}
			}
		});
		var mon = $("#monthSelect").val();
		var params="month="+mon;
		var max=paraname.length;
		var gid = 15;
		for(var i = 0; i < max; i++)
		{
			var pType=paraname[i];
			var paraval = paramMap.get(pType);
			if(null!=paraval && ""!=paraval && "-1"!=paraval){
				if(!params)
					params = "?" + pType+ "=" + paraval;
				else
					params = params + "&" + pType+ "=" + paraval;
				
				if(pType=="openInst"){//开户机构
					gid = 7;
				}else if(pType=="openPlatform"){//开户平台
					gid = 3;
				}else if(pType=="cooperateType"){//合作类型
					gid = 1;
				}else if(pType=="openWangDian"){//开户网点
					gid = 0;
				}
			}
		}
		params+="&gid="+gid;
		var reqObj={
				url:contextPath+"/auth/ukeep/loadDataM.htm",
				postMethod:'POST',
				params:params,
				callback:function(data){
					$('#ukeepGraphDetailM').html(data);
				}
		};
		ajaxRequest(reqObj);
		$('#ukeepGraphDetailM').html(loadImg);
		mTgIndex=-1;
	});
}


/**画图
 * @param jsonData json数据
 * @param wd 维度
 */
function drawPieYear(jsonData,wd){
	try {
		var _ctfx6rJson =[];
		if(jsonData){
			_ctfx6rJson = jsonData;
		}
		//按照日期降序
		//获取最近一月的数据
		var _ctfx6rJsonVar = sortTop(_ctfx6rJson.slice(), 0, "stat_dt", true);
		var _dataObj = _ctfx6rJsonVar[0];
		var TTL1 = _dataObj.cl_dnxzjy_rs_total;
		var TTL2 = _dataObj.wcl_dnxzjy_rs_total;
		var data1 = [ _dataObj.cl_dnxzjy_rs_1, _dataObj.cl_dnxzjy_rs_2, _dataObj.cl_dnxzjy_rs_3,
		              _dataObj.cl_dnxzjy_rs_4,_dataObj.cl_dnxzjy_rs_5,_dataObj.cl_dnxzjy_rs_gt5];

		var data2 = [ _dataObj.wcl_dnxzjy_rs_1,  _dataObj.wcl_dnxzjy_rs_2,  _dataObj.wcl_dnxzjy_rs_3,
		              _dataObj.wcl_dnxzjy_rs_4, _dataObj.wcl_dnxzjy_rs_5,_dataObj.wcl_dnxzjy_rs_gt5];

		var categories1=[ '有存量-交易1次客户','有存量-交易2次客户','有存量-交易3次客户',
			               '有存量-交易4次客户','有存量-交易5次客户','有存量-交易5次以上客户'];
		var categories2 = [ '无存量-交易1次客户','无存量-交易2次客户','无存量-交易3次客户',
				               '无存量-交易4次客户','无存量-交易5次客户','无存量-交易5次以上客户'];
		if(wd=="newuser-y"){
			TTL1 = _dataObj.cl_dnkh_zrs;
			TTL2 = _dataObj.wcl_dnkh_zrs;
			data1 = [ _dataObj.cl_dnkhdnjy_rs_1, _dataObj.cl_dnkhdnjy_rs_2, _dataObj.cl_dnkhdnjy_rs_3,
		              _dataObj.cl_dnkhdnjy_rs_4,_dataObj.cl_dnkhdnjy_rs_5,_dataObj.cl_dnkhdnjy_rs_gt5];
			//设置计算wcl_dnkhdnjy_rs_0 
			var dnkhAll = _dataObj.wcl_dnkh_zrs;
			if(!isNaN(_dataObj.wcl_dnkhdnjy_rs_1))
				dnkhAll-=_dataObj.wcl_dnkhdnjy_rs_1;
			if(!isNaN(_dataObj.wcl_dnkhdnjy_rs_2))
				dnkhAll-=_dataObj.wcl_dnkhdnjy_rs_2;
			if(!isNaN(_dataObj.wcl_dnkhdnjy_rs_3))
				dnkhAll-=_dataObj.wcl_dnkhdnjy_rs_3;
			if(!isNaN(_dataObj.wcl_dnkhdnjy_rs_4))
				dnkhAll-=_dataObj.wcl_dnkhdnjy_rs_4;
			if(!isNaN(_dataObj.wcl_dnkhdnjy_rs_5))
				dnkhAll-=_dataObj.wcl_dnkhdnjy_rs_5;
			if(!isNaN(_dataObj.wcl_dnkhdnjy_rs_gt5))
				dnkhAll-=_dataObj.wcl_dnkhdnjy_rs_gt5;
			_dataObj.wcl_dnkhdnjy_rs_0 =dnkhAll;
			data2 = [ _dataObj.wcl_dnkhdnjy_rs_0,_dataObj.wcl_dnkhdnjy_rs_1, _dataObj.wcl_dnkhdnjy_rs_2, _dataObj.wcl_dnkhdnjy_rs_3,
		              _dataObj.wcl_dnkhdnjy_rs_4,_dataObj.wcl_dnkhdnjy_rs_5,_dataObj.wcl_dnkhdnjy_rs_gt5];
			
			categories2.unshift('无存量-交易0次客户');
		}
			
		var colors = Highcharts.getOptions().colors, categories = [ '有存量用户','无存量用户' ],
		data = [
				{
					y : TTL1,
					color : colors[0],
					drilldown : {
						categories : categories1,
						data : data1,
						color : colors[0]
					}
				},
				{
					y : TTL2,
					color : colors[1],
					drilldown : {
						categories : categories2,
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
					color : Highcharts.Color(data[i].color).brighten(brightness).get()
				});

			}
		}

		// Create the chart
		$('#ukeepYearChart').highcharts(
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


/**
 * 加载查询条件
 */
function loadQueryExp(){
	var expArray = [];
	var wd = $(".fwqd li.current a").text();
	expArray.push(wd);
	$("#qudaoChoose li.current a").each(function(i,obj){
		var t = $(obj).text();
		expArray.push(t);
	});
	var exp = expArray.join(" - ");
	$("#exp").html(exp);
}

/**
 * 复选框默认选中
 */
function checkBoxDefalut(){
	$("#detail_all input[type=checkbox]").prop("checked",true);
	$("#detail_cl input[type=checkbox]").prop("checked",true);
	$("#detail_wcl input[type=checkbox]").prop("checked",true);
}

function checkBoxDefalutM(){
	$("#mutil_month_quota input[type=checkbox]").prop("checked",true);
	$("#mutil_month_wd input[type=checkbox]").prop("checked",true);
}


function eventCheckboxPri(div,table){
	//指标事件
	$("#"+div+" div[ultag='q'] input[type=checkbox]").click(function(){
		var _this=$(this);
		var value = _this.val();
		var ch = _this.is(":checked");
		if("-1"==value){
			$("#"+div+" div[ultag='q'] input[type=checkbox][value!='-1']").prop("checked",ch);
			if(ch){//全选
				$("#"+table+" td[tag_q]").show();
				$("#"+table+" td[wd]").show();
				$("#"+div+" input[type=checkbox][tag='qwd']").prop("checked",true);
				
				//一级标题显示
				$("#"+table+" tr[tag='title'] td[colspan]").attr("colspan","3").show();
			}else{//全不选
				$("#"+table+" td[tag_q]").hide();
				$("#"+table+" td[wd]").hide();
				$("#"+div+" input[type=checkbox][tag='qwd']").prop("checked",false);
			}
		}else{
			var allbox = $("#"+div+" div[ultag='q'] input[type=checkbox][value!='-1']");
			var allboxch = $("#"+div+" div[ultag='q'] input[type=checkbox][value!='-1']:checked");
			if(allboxch.length==allbox.length){
				 $("#"+div+" div[ultag='q'] input[type=checkbox][value='-1']").prop("checked",true);
			}else{
				$("#"+div+" div[ultag='q'] input[type=checkbox][value='-1']").prop("checked",false);
			}
			if(ch){//选中对应指标
				//一级标题
				$("#"+table+" tr[tag='title'] td[tag_q='"+value+"']").show();
				//二级标题
				$("#"+table+" tr[tag='title_c'] td[wd='"+value+"']").show();
				//数据
				$("#"+table+" tr[tag='title_c'] td[tag_q='"+value+"']").show();
				var chNotFindNum =0;
				//处理二级标题数据,查找未选中的维度-隐藏掉未选择的维度
				$("#"+div+" input[type=checkbox][tag='qwd']:not(:checked)").each(function(){
					chNotFindNum++;
					var _thisWd =$(this);
					var wdV = _thisWd.val();
					var vArr=[];
					if(wdV)
						vArr = wdV.split("_");
					var start="",end="";
					if(vArr && vArr.length>=2){
						start = vArr[0];
						end = vArr[1];
					}
					//展示该指标对应的选中维度
					$("#"+table+" tr[tag='title_c'] td[tag_q^='"+start+"'][tag_q$='"+end+"']").hide();
				});
				
				// 如果有未选择维度，则设置一级标题colspan
				if(chNotFindNum>=1){
					var wdInpCh = $("#"+div+" input[type=checkbox][tag='qwd']:checked");
					var wdInpChLen = wdInpCh.length;
					$("#"+table+" tr[tag='title'] td[colspan]").attr("colspan",wdInpChLen);
				}
			}else{
				//标题
				$("#"+table+" tr[tag='title'] td[tag_q='"+value+"']").hide();
				//二级标题
				$("#"+table+" tr[tag='title_c'] td[wd='"+value+"']").hide();
				//数据
				$("#"+table+" tr[tag='title_c'] td[tag_q='"+value+"']").hide();
				
				if(allboxch.length==0){
					$("#"+div+" input[type=checkbox][tag='qwd']").prop("checked",false);
				}
			}
		}
	});
	
	//维度事件
	$("#"+div+" input[type=checkbox][tag='qwd']").click(function(){
		var _this=$(this);
		var value = _this.val();
		var ch = _this.is(":checked");
		var vArr= [];
		if(value)
			vArr = value.split("_");
		var start="",end="";
		if(vArr && vArr.length==2){
			start = vArr[0];
			end = vArr[1];
		}
		if(ch){//显示
			//一级标题显示
			var tit1= $("#"+table+" tr[tag='title'] td[colspan]:visible");
			if(tit1.length==0){
				//一级标题显示
				$("#"+table+" tr[tag='title'] td[colspan]").show();
				//对应指标全部选中
				$("#"+div+" div[ultag='q'] input[type=checkbox]").prop("checked",true);
			}
			//一级选中指标的标题显示
			$("#"+div+" div[ultag='q'] input[type=checkbox]:checked").each(function(){
				var _thisQ = $(this);
				var _q= _thisQ.val();
				//对应选中指标显示
				var tds = $("#"+table+" tr[tag='title_c'] td[wd='"+_q+"'][tag_q^='"+start+"'][tag_q$='"+end+"']:hidden");
				tds.show();
			});
		}else{//隐藏
			var tds = $("#"+table+" tr[tag='title_c'] td[wd][tag_q^='"+start+"'][tag_q$='"+end+"']:visible");
			tds.hide();
		}
		
		//设置colspan
		var wdInpCh = $("#"+div+" input[type=checkbox][tag='qwd']:checked");
		var wdInpChLen = wdInpCh.length;
		$("#"+table+" tr[tag='title'] td[colspan]").attr("colspan",wdInpChLen);
		if(wdInpChLen==0){//都未选中
			//一级指标取消选中
			$("#"+div+" div[ultag='q'] input[type=checkbox][pc]").prop("checked",false);
			//全选取消选中
			$("#"+div+" div[ultag='q'] input[type=checkbox][value='-1']").prop("checked",false);
			//一级标题隐藏
			$("#"+table+" tr[tag='title'] td[colspan][tag_q]").hide();
		}
			
	});
}
function eventCheckbox(){
	var divArray = ["detail_all","detail_cl","detail_wcl"];
	for(var i=0;i<divArray.length;i++){
		var div = divArray[i];
		var table = "table_"+div;
		eventCheckboxPri(div, table);
	}
}

/**
 * 
 */
function eventCheckboxM(){
	//指标
	$("#mutil_month_quota input[type=checkbox]").click(function(){
		var _this=$(this);
		var value = _this.val();
		var ch = _this.is(":checked");
		var pc =_this.attr("pc");
		if("-1"==value){
			$("#mutil_month_quota ul.clearfix input[type=checkbox]").prop("checked",ch);
			var pcbox = $("#mutil_month_quota input[type=checkbox][pc]:checked");
			var pLen = pcbox.length||0;
			var wdnum = $("#mutil_month_wd input[type=checkbox]:checked");
			var wdLen = wdnum.length;
			$("#zb_table tr[tag='title'] td[tag][colspan]").attr("colspan",pLen*wdLen);
			if(ch){
				//所有一二级标题显示
				$("#zb_table td[tag]").show();
				//所有三级标题显示
				$("#zb_table td[qtag]").show();
				//所有二级指标colspan设置为默认值
				$("#zb_table tr[tag='title2'] td[tag][colspan]").attr("colspan",pLen*wdLen);
				
				$("#mutil_month_wd input[type=checkbox]").prop("checked",true);
				//如果所有维度未选中，则选中所有维度
				var wdChBox = $("#mutil_month_wd input[type=checkbox]:checked");
				var wdLen = 0;
				if(wdChBox){
					wdLen = wdChBox.length;
					if(wdLen==0){
						$("#mutil_month_wd input[type=checkbox]").prop("checked",true);
					}
				}
			}else{
				$("#zb_table td[tag]").hide();
				$("#zb_table td[qtag]").hide();
				$("#mutil_month_wd input[type=checkbox]").prop("checked",false);
			}
		}else{
			var allbox = $("#mutil_month_quota ul input[type=checkbox]");
			var allboxCh = $("#mutil_month_quota ul input[type=checkbox]:checked");
			if(allbox.length!=allboxCh.length){
				$("#mutil_month_quota input[type=checkbox][value='-1']").prop("checked",false);
				if(allboxCh.length==0){
					$("#mutil_month_wd input[type=checkbox]").prop("checked",false);
				}else{
					//$("#mutil_month_wd input[type=checkbox]").prop("checked",true);
				}
			}else{
				$("#mutil_month_quota input[type=checkbox][value='-1']").prop("checked",true);
				$("#mutil_month_wd input[type=checkbox]").prop("checked",true);
			}
			var pcbox = $("#mutil_month_quota input[type=checkbox][pc]:checked");
			var pLen = pcbox.length||0;
			var wdnum = $("#mutil_month_wd input[type=checkbox]:checked");
			var wdLen = wdnum.length;
			$("#zb_table tr[tag='title'] td[tag][colspan]").attr("colspan",pLen*wdLen);
			if(ch){
				if(null!=pc){
					$("#zb_table tr[tag='title'] td[tag][colspan]").show();
				}
				$("#zb_table tr[tag='title'] td[tag='"+value+"']").show();
				$("#zb_table tr[tag='title2'] td[tag='"+value+"']").show();
				$("#zb_table tr[tag='titled'] td[qtag='"+value+"']").show();
				$("#zb_table tr[tag='titled'] td[ptag='"+value+"']").show();
				var notCh = $("#mutil_month_wd input[type=checkbox]:not(:checked)");
				if(notCh.length==3){
					if(null!=pc){
						$("#mutil_month_wd input[type=checkbox]").prop("checked",true);
						$("#zb_table tr[tag='title'] td[tag][colspan]").attr("colspan",pLen*3);
						$("#zb_table tr[tag='title2'] td[tag][colspan]").attr("colspan",pLen*3);
					}
				}else{
					//回调判断未选择维度，隐藏对应指标的维度数据
					notCh.each(function(i,obj){
						var _thisWdCh = $(obj);
						var _wd =_thisWdCh.val();
						$("#zb_table tr[tag='titled'] td[qtag$='"+_wd+"']").hide();
					});
				}
			}else{
				if(pLen==0){
					$("#zb_table tr[tag='title'] td[tag][colspan]").hide();
				}
				$("#zb_table tr[tag='title2'] td[colspan][tag='"+value+"']").hide();
				$("#zb_table tr[tag='title'] td[tag='"+value+"']").hide();
				$("#zb_table tr[tag='titled'] td[qtag='"+value+"']").hide();
				$("#zb_table tr[tag='titled'] td[ptag='"+value+"']").hide();
				
				if(pLen==0){
					$("#mutil_month_wd input[type=checkbox]").prop("checked",false);
				}
				
			}
		}
	});
	
	//维度
	$("#mutil_month_wd input[type=checkbox]").click(function(){
		var wdnum = $("#mutil_month_wd input[type=checkbox]:checked");
		var wdLen = wdnum.length;
		var _this=$(this);
		var value = _this.val();
		var ch = _this.is(":checked");
		debugger;
		if(ch){
			//1：获取pc选中指标
			var pcWd = $("#mutil_month_quota input[type=checkbox][pc]:checked");
			var pcWdLen = pcWd.length;
			if(pcWdLen==0){
				//设置指标全选
				$("#mutil_month_quota input[type=checkbox][pc]").prop("checked",true);
				$("#mutil_month_quota input[type=checkbox][pc]").each(function(i,obj){
					var _thisPcWd = $(obj);
					var pcWdq= _thisPcWd.val();
					//2：显示对应指标的标题、数据
					$("#zb_table tr[tag='titled'] td[ptag='"+pcWdq+"'][qtag$='"+value+"']").show();
				});
				//3：设置colspan
				var t2=$("#zb_table tr[tag='title2'] td[colspan]").attr("colspan",wdLen);
				var  pcLen = $("#mutil_month_quota input[type=checkbox][pc]").length;
				var t=$("#zb_table tr[tag='title'] td[tag][colspan]").attr("colspan",wdLen*pcLen);
				t2.show();
				t.show();
			}else{
				pcWd.each(function(i,obj){
					var _thisPcWd = $(obj);
					var pcWdq= _thisPcWd.val();
					//2：显示对应指标的标题、数据
					$("#zb_table tr[tag='titled'] td[ptag='"+pcWdq+"'][qtag$='"+value+"']").show();
					var t2=$("#zb_table tr[tag='title2'] td[colspan][tag='"+pcWdq+"']").attr("colspan",wdLen);
					t2.show();
				});
				//3：设置colspan
				var  pcLen = $("#mutil_month_quota input[type=checkbox][pc]:checked").length;
				var t=$("#zb_table tr[tag='title'] td[tag][colspan]").attr("colspan",wdLen*pcLen);
				t.show();
			}
			
		}else{
			//隐藏选中的指标对应下的维度
			//1：获取选中的指标
			$("#mutil_month_quota input[type=checkbox]:checked").each(function(){
				var _thisQ = $(this);
				var _q = _thisQ.val();
				//2：隐藏选中的指标的维度数据
				$("#zb_table tr[tag='titled'] td[ptag='"+_q+"'][qtag^='"+_q+"'][qtag$='"+value+"']").hide();
				//3：设置指标的colspan
				//设置所有二级维度指标colspan
				$("#zb_table tr[tag='title2'] td[colspan][tag]").attr("colspan",wdLen);
			});
			//设置一级标题colspan 等于指标选中个数*维度选中个数
			var qchBox = $("#mutil_month_quota input[type=checkbox][pc]:checked");
			$("#zb_table tr[tag='title'] td[tag][colspan]").attr("colspan",wdLen*qchBox.length);
			if(wdLen==0){
				$("#zb_table tr[tag='title2'] td[colspan]").hide();
				$("#zb_table tr[tag='title'] td[tag][colspan]").hide();
				$("#mutil_month_quota input[type=checkbox][pc]").prop("checked",false);
			}
			
		}
	});
}




/**
 * 画图
 * @param containerid
 * @param obj
 */
function highchart_drawLine(containerid,obj){
	$('#'+containerid).highcharts({
        title: {
            text: obj.text||" ",
            x: -20 //center
        },
        credits: {
            enabled: false
        }, 
        xAxis: {
            categories: obj.categories||[]
        },
        yAxis: {
            title: {
                text: obj.ytitle||""
            },
            min:0, // 定义最小值  
            plotLines: [{
                value: 0,
                width: 1,
                color: '#808080'
            }]
        },
        tooltip: {
            valueSuffix:  obj.valueSuffix||""
        },
        legend: {
            layout: 'vertical',
            align: 'right',
            verticalAlign: 'middle',
            borderWidth: 0
        },
        series: obj.series
    });
}
var currentWd="newtrade-m";

var categories=['当月', '第二月', '第三月', '第四月', '第五月', '第六月',
                 '第七月', '第八月', '第九月', '第十月', '第十一月', '第十二月'];
/**
 * 单月数据
 * @param jsonData
 * @param month
 */
function drawMonth(jsonData,month){
	//要显示的指标-新增交易人数、复购人数、存量人数、新增复购人数（回流人数）
	var dyxzjy_rs="dyxzjy_rs";
	var dyfg_rs="dyfg_rs";
	var dycl_rs="dycl_rs";
	var dyhl_rs="dyhl_rs";
	
	var _jsonDataArray = null;
	if(jsonData)
		_jsonDataArray = jsonData;
	var _monData=null;
	for(var i=0;i<_jsonDataArray.length;i++){
		var _obj = _jsonDataArray[i];
		var _stat_dt = _obj["stat_dt"];
		if(month==_stat_dt){
			_monData = _obj;
			break;
		}
	}
	var dyxzjy_rs_array=[];//当月新增交易人数数组
	var dyfg_rs_array=[];//复购人数数组
	var dycl_rs_array=[];//存量人数数组
	var dyhl_rs_array=[];//新增复购人数数组
	if(null!=_monData){
		for(var i=1;i<=12;i++){
			var pNum = 0;
			for(var p in _monData){
				if(i==1){
					if(pNum==4){
						break;
					}
					//新增交易人数
					if(p==dyxzjy_rs){
						dyxzjy_rs_array.push(_monData[p]||0);
						pNum++;
					}
					//复购人数
					if(p==dyfg_rs){
						dyfg_rs_array.push(_monData[p]||0);
						pNum++;
					}
					//存量人数
					if(p==dycl_rs){
						dycl_rs_array.push(_monData[p]||0);
						pNum++;
					} 
					//回流人数
					if(p==dyhl_rs){
						dyhl_rs_array.push(_monData[p]||0);
						pNum++;
					}
 				}else{
 					if(pNum==4){
						break;
					}
 					if(p.indexOf(dyxzjy_rs+"_"+i)==0){
 						dyxzjy_rs_array.push(_monData[p]||0);
						pNum++;
					}
 					if(p.indexOf(dyfg_rs+"_"+i)==0){
 						dyfg_rs_array.push(_monData[p]||0);
 						pNum++;
 					}
 					if(p.indexOf(dycl_rs+"_"+i)==0){
						dycl_rs_array.push(_monData[p]||0);
						pNum++;
					}
 					 if(p.indexOf(dyhl_rs+"_"+i)==0){
 						dyhl_rs_array.push(_monData[p]||0);
 						pNum++;
 					}
 				}
			}
		}
		
		var series = [
	            {name: '复购人数',data: dyfg_rs_array}, 
	            {name: '存量人数',data: dycl_rs_array},
	            {name: '新增复购人数',data: dyhl_rs_array}
	            ];
		if(currentWd=="newtrade-m"){
			$("#xzjr_rs").html("新增交易人数："+_monData["dyxzjy_rs"]);
		}else if(currentWd=="newuser-m"){
			$("#xzjr_rs").html("新开户人数："+_monData["dykh_zrs"]);
			series.unshift({name: '新增交易人数',data: dyxzjy_rs_array});
		}
		var _dataObj={};
		_dataObj.categories=categories;
		_dataObj.ytitle="人";
		_dataObj.valueSuffix=" 人";
		_dataObj.series=series;
		highchart_drawLine("monthChart", _dataObj);
		
		return true;
	}
	return false;
	
}

/**
 * 多月数据
 * @param jsonData
 * @param fromMonth
 * @param quota 指标
 * @param quotaname 指标名称
 */
function drawMutilMonth(jsonData,fromMonth,quota,quotaname){
	var _jsonDataArray = null;
	if(jsonData)
		_jsonDataArray = jsonData;
	else
		return false;
	
	var _dataArray=[];
	for(var i=0;i<_jsonDataArray.length;i++){
		var _obj = _jsonDataArray[i];
		var _stat_dt = _obj["stat_dt"];
		if(fromMonth<=_stat_dt){
			_dataArray.push(_obj);
		}
	}
	if(_dataArray.length>=1){
		var series = [];
		for(var m=0;m<_dataArray.length;m++){
			var _monData = _dataArray[m];
			var  _mon_data=[];
			if(null!=_monData){
				for(var i=1;i<=12;i++){
					for(var p in _monData){
						if(i==1){
							if(p==quota){
								_mon_data.push(_monData[p]||0);
								break;
							}
		 				}else{
		 					if(p.indexOf(quota+"_"+i)==0){
		 						_mon_data.push(_monData[p]||0);
		 						break;
							}
		 				}
					}
				}
				var dt = _monData["stat_dt"];
				var name = $("#monthSelect option[value="+dt+"]").text();
				var _dataObj = {name: name,data: _mon_data};
				series.push(_dataObj);
			}
			
		}
		var _dataObjAll={};
		_dataObjAll.categories=categories;
		_dataObjAll.ytitle="人";
		_dataObjAll.valueSuffix=" 人";
		_dataObjAll.series=series;
		highchart_drawLine("mutilpartMonthChart", _dataObjAll);
		return true;
	}
	return false;
}

/**
 * 指标下拉框事件
 */
function eventChartSelect(jsonData){
	$("#_chart_select").change(function(){
		var _this = $(this);
		var quota = _this.val();
		var quotaname = _this.text();
		var fromMonth = $("#monthSelect option:selected").val();
		drawMutilMonth(jsonData, fromMonth, quota, quotaname);
		
	});
}
