/**
 * 
 */

var context_path = null;
var _path = null;

var rootChannelJson = null;
var zeroChannelTagJson = null;
var curpage=1;
var pagerows = 10;
var maxpage = null; 
var orderby="dt";
var order="desc";
/**
 * form数据封装
 */
function formdata(){
	var _param_array=[];
	var channelTypeLi = $("ul#fwqd li.current:eq(0)");
	var channelType = channelTypeLi.attr("channel_type");
	if(""!=channelType && null!=channelType){
		_param_array.push("channelType="+channelType);

		var beginDate = $("#beginDate").val();
		var endDate = $("#endDate").val();
		_param_array.push("beginDate="+beginDate);
		_param_array.push("endDate="+endDate);
		
		var code_tag = channelTypeLi.attr("code_tag");
		_param_array.push("channelCode="+code_tag);
		if("-1"==channelType){//全部渠道
			_param_array.push("level=1");
		}else if("1"==channelType){//直接访问
			_param_array.push("level=1");
			_param_array.push("channel="+code_tag);
		}else if("2"==channelType){//其他渠道
			_param_array.push("level=1");
			var channel = $("#other_channel").val();
			_param_array.push("channel="+channel);
		}else if("3"==channelType){//搜索引擎
			var searchEngine = "";
			var ssyqLi = $("ul#ssyq li.current");
			var tag_code = ssyqLi.attr("tag_code");
			if (typeof (tag_code) != "undefined" && tag_code!="") {
				searchEngine = tag_code;
			} /*else {
				var ssv = $("#ssyqqt option:selected").val();
				if(ssv!=null &&  ssv!=""){
					searchEngine =ssv;
				}
			}*/
			if(""!=searchEngine){
				_param_array.push("searchEngine="+searchEngine);
			}
			_param_array.push("level=1");
		}else if("4"==channelType){//推广渠道
			var tgqd_UL = $("ul#tgqd");
			var routeOne = tgqd_UL.find("li#indexQueryRouteOne select#queryRouteOne");
			var routeTwo = tgqd_UL.find("li#indexQueryRouteTwo select#queryRouteTwo");
			var routeThree = tgqd_UL.find("li#indexQueryRouteThree select#queryRouteThree");
			var threeOption = routeThree.find("option:selected");
			var twoOption = routeTwo.find("option:selected");
			var oneOption = routeOne.find("option:selected");
			var threeChannel = threeOption.val();
			var twoChannel = twoOption.val();
			var oneChannel = oneOption.val();
			var level = null;
			var root = null;
			var parent =null;
			var channel =null;
			if (undefined != threeChannel && "" != threeChannel
					&& "-1" != threeChannel) {//选择三级渠道
				level="3";
				channel=threeOption.attr("root");
				parent=twoOption.attr("id");
			} else if ((undefined == threeChannel || "" == threeChannel || "-1" == threeChannel)
					&& (null != twoChannel && "" != twoChannel && "-1" != twoChannel)) {//选择二级渠道
				level="3";
				root = twoOption.attr("root");
				parent=twoOption.attr("id");
			} else if ((undefined == twoChannel || "" == twoChannel || "-1" == twoChannel)
					&& (undefined != oneChannel && oneChannel != "" && oneChannel != "-1")) {//选择一级渠道
				root = oneOption.attr("root");
				parent=oneOption.attr("id");
				level = "2";
			} else if (undefined == oneChannel || oneChannel == ""
					|| oneChannel == "-1") {//所有渠道
				level = "1";
			}
			_param_array.push("level="+level);
			if(null!=root){
				_param_array.push("channelRoot="+root);
			}
			if(null!=parent){
				_param_array.push("parent="+parent);
			}
			if(null!=channel){
				_param_array.push("channel="+channel);
			}
		}
	}
	var params = _param_array.join("&");
	return params;
}


/**
 * 访问渠道确定按钮提交事件
 * @returns {Boolean}
 */
function accessChannelSubmit()
 {
	var start = $("#beginDate").val();
	var end = $("#endDate").val();
	if (start == "" || end == "" || start > end) {
		alert("请正确选择日期");
		return false;
	} else {
		var param = formdata();
		var reqObj = {
			url : context_path + "/" + _path + '/trendGraph.htm',
			postMethod : 'POST',
			params : param,
			callback : function(data) {
				$('#TrendGraph').html(data);
			}
		};
		ajaxRequest(reqObj);
		channelDetailSubmit();
		trendDetailSubmit();
		clickAllCheckbox();
		tab_change();
	}
}



/**
 * 渠道明细加载数据方法
 */
function channelDetailSubmit()
{
	//var formParam = $("#form-content").serialize();
	var formParam = formdata();
	var reqObj=
	{
	url:context_path+"/" +  _path+'/channelDetail.htm',
    postMethod:'POST',
    params:formParam,
    callback:function(data){
    	$('#ChannelDetail').html(data);
    }
    };
	ajaxRequest(reqObj);
}


function channelDetailOut()
 {
	var param = formdata();
	if (param == "") {
		return false;
	} else {
		var searchParam = getSearchParam();
		var  url = context_path + "/" + _path + '/channelDetailOut.htm?'+ param+"&currentParam="+searchParam;
		window.open(url);
	}
}


function channelDetail(){
	var cnt = 0;
	var param = "";
	$("#channelDetailFormFilter input").each(function(){
		if($(this).prop("checked"))
			cnt = cnt + 1;
	});
	if(cnt==0)
	{
		alert("请选择指标！");
		return param;
	}
	else{
		
		var flowdata = "flowData=";
		var opendata = "openData=";
		var tradedata = "tradeData=";
		
		$("#channelFlowData input").each(function(){
			if($(this).prop("checked"))
				flowdata =flowdata+$(this).val()+"$";
		});
		
		$("#channelOpenData input").each(function(){
			if($(this).prop("checked"))
				opendata = opendata+$(this).val()+"$";
		});
		
		$("#channelTradeData input").each(function(){
			if($(this).prop("checked"))
				tradedata = tradedata+$(this).val()+"$";
		});
		
		param = flowdata + "&" + opendata + "&" + tradedata;
		return param;
	}
}

/**
 * 趋势明细数据加载
 */
function trendDetailSubmit()
{
	
	var curPage=1;
	var pageRows = 10;
	var order="desc";
	var orderBy = "dt";
	 
	
	//var formParam = $("#form-content").serialize();
	var formParam = formdata();
	formParam+="&curPage="+curPage+"&pageRows="+pageRows+"&order="+order+"&orderBy="+orderBy;
	var reqObj = 
	{
		url:context_path+"/" +  _path+'/trendDetail.htm',
        postMethod:'POST',
        params:formParam,
        callback:function(data){
        	$('#TrendDetail').html(data);
        	maxpage = Math.ceil($("#total-rows").text()/pagerows); 
        	if(maxpage > 1){
        		$("#nextpage").addClass("active");
        	}
         }
     };
	ajaxRequest(reqObj);
}

function trendDetailOut() {
	var formParam = formdata();
	formParam+="&order="+order+"&orderBy="+orderby;
	if (formParam == "") {
		return false;
	} else {
		var searchParam = getSearchParam();
		var url =context_path + "/" + _path + '/trendDetailOut.htm?'+ formParam+"&currentParam="+searchParam;
		window.open(url);
	}
}

function TrendDatail()
{
	var cnt = 0;
	$("#trendDetailFormFilter input").each(function(){
		if($(this).prop("checked"))
			cnt = cnt + 1;
	});
	if(cnt==0)
	{
		alert("请选择指标！");
		return false;
	}
	else{
		var param = "";
		var flowdata = "flowData=";
		var opendata = "openData=";
		var tradedata = "tradeData=";
		
		$("#trendFlowData input").each(function(){
			if($(this).prop("checked"))
				flowdata =flowdata+$(this).val()+"$";
		});
		
		$("#trendOpenData input").each(function(){
			if($(this).prop("checked"))
				opendata = opendata+$(this).val()+"$";
		});
		
		$("#trendTradeData input").each(function(){
			if($(this).prop("checked"))
				tradedata = tradedata+$(this).val()+"$";
		});
		
		param = flowdata + "&" + opendata + "&" + tradedata + "&orderBy=qid&pageRows=10&curPage=1&order=desc";
		return param;
	}
}

/**
 * 选中所有复选框
 */
function clickAllCheckbox(){
	$("#channelFlowData li input[type=checkbox]").prop("checked",true);
	$("#channelOpenData li input[type=checkbox]").prop("checked",true);
	$("#channelTradeData li input[type=checkbox]").prop("checked",true);
	
	$("#trendFlowData li input[type=checkbox]").prop("checked",true);
	$("#trendOpenData li input[type=checkbox]").prop("checked",true);
	$("#trendTradeData li input[type=checkbox]").prop("checked",true);
}


/**
 * 切换趋势/渠道明细数据
 */
function tab_change(){
	var liall= $("ul#fwqd li.current[channel_type=-1]");
	if(liall.length==0){
		//显示趋势明细
		$("div.tab_list ul.tab_menu li:eq(1)").click();
	}
}
/**
 * 加载所有的当前的搜索条件
 */
function loadFwqdExp(){
	var res_exp="";
	var exp_array = [];
	var dls = $("div.filter_b dl:visible");
	if(dls.length>=1){
		dls.each(function(i,obj){
			var dl = $(obj);
			if(dl.hasClass("fwqd")){//访问渠道
				var _fwqd_li=dl.find("ul#fwqd li.current[channel_type!=-1]:has(a)");
				if(_fwqd_li.length>=1){
					exp_array.push(_fwqd_li.text());
				}
			}else if(dl.hasClass("ssyq-box")){//搜索引擎
				var main_ssyq = dl.find("ul#ssyq li.current[tag_code!='']:has(a)");
				if(main_ssyq.length>=1){
					exp_array.push(main_ssyq.text());
				}else{
					var other_ssyq = dl.find("ul#ssyq li select option[value!='']:selected");
					if(other_ssyq.length>=1){
						exp_array.push(other_ssyq.text());
					}
				}
			}else if(dl.hasClass("tgqd-box")){//推广渠道
				var _selects = dl.find("ul#tgqd li:has(select) select");
				_selects.each(function(i,obj){
					var select = $(obj);
					var sv = select.find("option[value!='-1']:selected");
					if(sv.length>=1){
						exp_array.push(sv.text());
					}
				});
			}else if(dl.hasClass("other-qd-box")){//其他渠道
				var _selects = dl.find("ul li:has(select) select");
				_selects.each(function(i,obj){
					var select = $(obj);
					var sv = select.find("option[value!='']:selected");
					if(sv.length>=1){
						exp_array.push(sv.text());
					}
				});
			}
		});
		
		if(exp_array.length>=1){
			res_exp = exp_array.join(" - ");
		}
	}
	if(res_exp!=""){
		$("#fwqd_exp").html(res_exp);
	}
}


/**
 * 是否显示分布图
 */
function isShowLeft(){
	var result = true;
	var dls = $("div.filter_b dl:visible");
	if(dls.length>=2){
		dls.each(function(i,obj){
			var dl = $(obj);
			if(dl.hasClass("ssyq-box")){//搜索引擎
				var main_ssyq = dl.find("ul#ssyq li.current[tag_code!='']:has(a)");
				if(main_ssyq.length>=1){
					result = false;
				}else{
					var other_ssyq = dl.find("ul#ssyq li select option[value!='']:selected");
					if(other_ssyq.length>=1){
						result = false;
					}
				}
			}else if(dl.hasClass("tgqd-box")){//推广渠道
				var _select = dl.find("ul#tgqd li:has(select) select#queryRouteThree");
				var sv = _select.find("option[value!='-1']:selected");
				if(sv.length>=1){
					result = false;
				}
			
			}else if(dl.hasClass("other-qd-box")){//其他渠道
				var _selects = dl.find("ul li:has(select) select");
				_selects.each(function(i,obj){
					var select = $(obj);
					var sv = select.find("option[value!='']:selected");
					if(sv.length>=1){
						result = false;
					}
				});
			}
		});
		
	}else if(dls.length==1){
		if(dls.first().hasClass("fwqd")){//访问渠道
			var _fwqd_li=dls.find("ul#fwqd li.current[channel_type!=-1]:has(a)");
			if(_fwqd_li.length>=1){
				result = false;
			}
		}
	}
	return result;

}

function getSearchParam(){
	var channel = $("#fwqd_exp").text().replace(/\s/g,"");
	var time = $("#fwqd_exp").parent().find(" > div").text().replace(/\s/g,"");
	var param = channel +time;
	return param;
}

