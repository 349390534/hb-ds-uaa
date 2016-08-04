/**
 * 好买活动js
 */

var rootChannelJson = null;
var zeroChannelTagJson = null;

/**
 *初始化根渠道数据和搜索引擎渠道数据
 *PS:目前只展现推广渠道
 */
function initRootChannel(){
	
	var rootChannel = "";
	var all = '<li class="current" channel_type="-1"><a href="#" target="_self" >全部</a></li>';
	rootChannel += all;
	if(null!=rootChannelJson){
		for(var i=0;i<rootChannelJson.length;i++){
			var obj = rootChannelJson[i];
			var tag = obj.channelName;
			var channelType = obj.channelType;
			if("5"==channelType){
				//汇总渠道不显示
				continue;
			}
			var value = obj.channelValue;
			var code_tag = obj.channelCode;
			var li = '<li class="'+value+'" code_tag="'+code_tag+'" channel_type="'+channelType+'"><a href=";" target="_self">'+tag+'</a></li>';
			rootChannel+=li;
		}
		var submitLi ='<li><input id="submit" type="submit" value="查询" style="width: 80px; display: inline-block;" class="btn-style-a"></li>';
		rootChannel+=submitLi;
		$("#fwqd").prepend(rootChannel);
	};
	var zeroChannel = "";
	if(null!=zeroChannelTagJson){
		var zero_select = '<li><select style="margin-top:2px;padding: 0 0px;width: 100%;" id="ssyqqt">'+
		'<option value="">请选择</option>';
		var zero_select_end = '</li></select>';
		var all = '<li class="current" tag_code=""><a href="#" target="_self">全部</a></li>';
		var other_channel_op ="";
		for(var i=0;i<zeroChannelTagJson.length;i++){
			var obj = zeroChannelTagJson[i];
			var tagName = obj.tagName;
			var tagCode = obj.tagCode;
			var type = obj.type;
			var title = obj.title;
			var tag = (title==null||title=='')?tagName:title;
			if(type==1){
				var  li = '<li tag_code="'+tagCode+'"><a href="#" target="_self">'+tag+'</a></li>';
				all +=li;
			}else if(type ==2){
				var op = '<option value="'+tagCode+'">'+tagName+'</option>';
				other_channel_op+=op;
			}
		}
		var input='<li><input id="submit" type="button" value="查询" style="width: 80px;" class="btn-style-a"></li>';
		zero_select+=zero_select_end;
		//zeroChannel =all+zero_select+input;//取消搜索引擎下拉框
		zeroChannel =all+input;
		$("#ssyq").prepend(zeroChannel);
		$("#other_channel").append(other_channel_op);
	}
}


/**
 * form数据封装
 */
function formdata(){
	var _param_array=[];
	var channelTypeLi = $("ul#fwqd li.current:eq(0)");
	var channelType = channelTypeLi.attr("channel_type");
	if(""!=channelType && null!=channelType){
		_param_array.push("channelType="+channelType);

		var beginDate = $("#start_date").val();
		var endDate = $("#end_date").val();
		_param_array.push("beginDate="+beginDate);
		_param_array.push("endDate="+endDate);
		
		var code_tag = channelTypeLi.attr("code_tag");
		if(code_tag)_param_array.push("channelCode="+code_tag);
		if("-1"==channelType){//全部渠道
			_param_array.push("channelLevel=1");
		}else if("1"==channelType){//直接访问
			_param_array.push("channelLevel=1");
			_param_array.push("channel="+code_tag);
		}else if("2"==channelType){//其他渠道
			_param_array.push("channelLevel=1");
			var channel = $("#other_channel").val();
			_param_array.push("channel="+channel);
		}else if("3"==channelType){//搜索引擎
			var searchEngine = "";
			var ssyqLi = $("ul#ssyq li.current");
			var tag_code = ssyqLi.attr("tag_code");
			if (tag_code) {
				searchEngine = tag_code;
			}
			if(""!=searchEngine){
				_param_array.push("searchEngine="+searchEngine);
			}
			_param_array.push("channelLevel=1");
		}else if("4"==channelType){//推广渠道
			var tgqd_UL = $("dl#tgqd");
			var routeOne = tgqd_UL.find("select#queryRouteOne");
			var routeTwo = tgqd_UL.find("select#queryRouteTwo");
			var routeThree = tgqd_UL.find("select#queryRouteThree");
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
				channel=threeOption.attr("tagcode");
				parent=twoOption.attr("id");
			} else if ((undefined == threeChannel || "" == threeChannel || "-1" == threeChannel)
					&& (null != twoChannel && "" != twoChannel && "-1" != twoChannel)) {//选择二级渠道
				level="3";
				root = twoOption.attr("tagcode");
				parent=twoOption.attr("id");
			} else if ((undefined == twoChannel || "" == twoChannel || "-1" == twoChannel)
					&& (undefined != oneChannel && oneChannel != "" && oneChannel != "-1")) {//选择一级渠道
				root = oneOption.attr("tagcode");
				parent=oneOption.attr("id");
				level = "2";
			} else if (undefined == oneChannel || oneChannel == ""
					|| oneChannel == "-1") {//所有渠道
				level = "1";
			}
			_param_array.push("channelLevel="+level);
			if(null!=root){
				_param_array.push("channelParent="+root);
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

var currentQd = -1;
/**
 * 渠道选择事件
 */
function eventDdClick(){
	//访问渠道
	$(".fwqd ul li:has(a)").each(function(i,obj){
		$(obj).click(function(){
			if(i==currentQd)return false;
			var hasSub=false;
			currentQd = i;
			var channelType = $(this).attr("channel_type");
			$(this).addClass("current").siblings("li").removeClass("current");
			if($(this).hasClass("zjfw")){
				$(".other-qd-box").hide();
				$(".ssyq-box").hide();
				$(".tgqd-box").hide();
			}
			
			if($(this).hasClass("ssyq")){
				$(".ssyq-box").show();
				$(".ssyq-box ul li:has(a)").click(function(){
					$(this).addClass("current").siblings("li").removeClass("current");
				});
				$(".ssyq-box ul li:has(a)").first().click();
				hasSub=true;
			}else{
				$(".ssyq-box").hide();
			}
			if($(this).hasClass("tgqd")){
				$(".tgqd-box").show();
				hasSub=true;
				initTgqd();
			}else{
				$(".tgqd-box").hide();
			}
			if($(this).hasClass("other-qd")){
				$(".other-qd-box").show();
				hasSub=true;
			}else{
				$(".other-qd-box").hide();
				$("#other_channel").val("");
			}
			
			if("-1"==channelType){
				$(".other-qd-box").hide();
				$(".ssyq-box").hide();
				$(".tgqd-box").hide();
				$(this).parent().find('li').find('.btn-style-a').show();
			}
			if(hasSub){
				$(this).parent().find('li').find('.btn-style-a').hide();
			}else{
				$(this).parent().find('li').find('.btn-style-a').show();
			}
			return false;
		});

	});
}

/**
 * 提交查询事件
 * @param ele
 */
function eventSubmit(ele) {
	$("input#" + ele).click(function() {
		var params = formdata();
		var isc = isCompareChecked();
		if(isc){
			var compareDate = $("#date").val();
			params+="&compare=1&compare_date="+compareDate;
		}
		var reqObj = {
			url : window.uaa_context_path + "/auth/at/graph.htm",
			postMethod : 'POST',
			params : params,
			callback : function(data) {
				$("#chart_activity").html(data);
			}
		};
		$("input#submit:visible").attr("disabled",true);
		$("#chart_activity").html("<img src='"+window.uaa_context_path+"/images/load2.gif' style='margin-top:15%;margin-left: 45%;margin-bottom: 40%;'>");
		ajaxRequest(reqObj);
		
		//查询数据明细
		var reqObjData = {
			url : window.uaa_context_path + "/auth/at/queryDetailData.htm",
			postMethod : 'POST',
			params : params,
			callback : function(data) {
				$("#detail_activity").html(data);
			}
		};
		ajaxRequest(reqObjData);
		var startTime = $("#start_date").val();
		var endTime = $("#end_date").val();
		$("#begin").html(startTime);
		$("#end").html(endTime);
		$("input#submit:visible").removeAttr("disabled");
	});
}

/**
 * 推广渠道按钮事件
 */
function initTgqd(){
	var param = "channelType=2&level=1";
	var reqObj = {
		url:window.uaa_context_path + "/auth/at/initailQueryQd.htm",
        postMethod:'POST',
        params:param,
        callback:function(data){
           	$("#queryRouteOne").html(data);
           	$("#queryRouteTwo").html("");
           	$("#queryRouteThree").html("");
        }
    };
	ajaxRequest(reqObj);
}

/**
 * 指标切换事件
 */
function eventTogTag(_dataJson_coll,dataJson,dataJsonCompare){
	$("#quota_ul li").click(function(){
		var _thisLi = $(this);
		var quota = _thisLi.attr("quota");
		_thisLi.addClass("current").siblings("li").removeClass("current");
		//画饼图
		/*var channelType = $("#fwqd li.current").attr("channel_type");
		 if("-1"!=channelType){
		 	$("#pie_chart").css('display','inline');
			drawPie(_dataJson_coll,quota);
		 }else{
		 	$("#pie_chart").css('display','none');
		 	$("#line_chart").css({"float":"left","margin-left":"12%"});
		 }*/
		 
		 var channelType = $("#fwqd li.current").attr("channel_type");
		 var queryRouteThree = $("#queryRouteThree option:selected");
		 var drawP = true;
		 if(queryRouteThree.length>=1){
		 	var tv = queryRouteThree.val();
		 	if(tv && tv!="-1"){
		 		drawP= false;
		 	}
		 }
		 if("1"==channelType){
		 	drawP= false;
		 }
		 if(drawP){//直接访问不显示饼图,第三层渠道不展示
		 	$("#pie_chart").css('display','inline');
			drawPie(_dataJson_coll,quota);
		 }else{
		 	$("#pie_chart").css('display','none');
		 	$("#line_chart").css({"float":"left","margin-left":"12%"});
		 }
		 
		// 画折线图
		drawLineChartActivity(dataJson,dataJsonCompare, quota);
	});
}
/**
 * 指标切换事件
 */
function eventTogTagDay(_dataJson,quota,fromdate,datax){
	$("#quota_ul li").click(function(){
		var _thisLi = $(this);
		var quota = _thisLi.attr("quota");
		_thisLi.addClass("current").siblings("li").removeClass("current");
		//画饼图
		/*var channelType = $("#fwqd li.current").attr("channel_type");
		 if("-1"!=channelType){
		 	$("#pie_chart").css('display','inline');
			drawPie(_dataJson_coll,quota);
		 }else{
		 	$("#pie_chart").css('display','none');
		 	$("#line_chart").css({"float":"left","margin-left":"12%"});
		 }*/
		
		var channelType = $("#fwqd li.current").attr("channel_type");
		var queryRouteThree = $("#queryRouteThree option:selected");
		var drawP = true;
		if(queryRouteThree.length>=1){
			var tv = queryRouteThree.val();
			if(tv && tv!="-1"){
				drawP= false;
			}
		}
		if("1"==channelType){
			drawP= false;
		}
		if(drawP){//直接访问不显示饼图,第三层渠道不展示
			$("#pie_chart").css('display','inline');
			drawPie(_dataJson_coll,quota);
		}else{
			$("#pie_chart").css('display','none');
			$("#line_chart").css({"float":"left","margin-left":"12%"});
		}
		
		// 画折线图
		drawLineChartActivityByDay(_dataJson,quota,fromdate,datax);
	});
}


/**
 * 菜单切换事件
 */
function  eventTabMenu(){
	$("#tab_menu li").click(function(){
		var _thisLi = $(this);
		_thisLi.addClass("current").siblings("li").removeClass("current");
		var index = _thisLi.index();
		$("#detail_activity .tab_box").find(".con").eq(index).show().siblings(".con").hide();
	});
}



/**
 * 自动刷新页面
 */
var auto_queryAc=false;
var _current_li_tag=null;
function autoRefeshDataAt(){
	var date = new Date();
	var m = date.getMinutes();
	if(m%5==0){
		var sec= date.getSeconds(); //秒
		if(!auto_queryAc && sec>=40 ){
			var params = formdata();
			var isc = isCompareChecked();
			if(isc){
				var compareDate = $("#date").val();
				params+="&compare=1&compare_date="+compareDate;
			}
			var reqObj = {
				url : window.uaa_context_path + "/auth/at/graph.htm",
				postMethod : 'POST',
				params : params,
				callback : function(data) {
					$("#chart_activity").html(data);
					 $("#quota_ul li[quota='"+_current_li_tag+"']").click();
				}
			};
			ajaxRequest(reqObj);
			_current_li_tag= $("#quota_ul li.current").attr("quota");
			auto_queryAc = true;
		}
	}else{
		auto_queryAc = false;
	}
}

function defaultEventCheckBox(){
	$("#_channel_detail").find("div.detail_filter_at ul li input[type=checkbox][tag]").prop("checked",true);
	$("#_trend_detail").find("div.detail_filter_at ul li input[type=checkbox][tag]").prop("checked",true);
}

function eventCheckBoxAcPri(divId){
	divId.find("div.detail_filter_at ul li input[type=checkbox][tag]").click(function(){
		var _obj = $(this);
		var val = _obj.val();
		var check = _obj.is(":checked");
		if(val=="-1"){
			divId.find("div.detail_filter_at ul li input[type=checkbox][tag][value!='-1']").prop("checked",check);
			if(check)
				divId.find("div.det-datalist table td[tag]").show();
			else
				divId.find("div.det-datalist table td[tag]").hide();
		}else{
			var tag =_obj.attr("tag");
			divId.find("div.detail_filter_at ul li input[type=checkbox][tag][value='"+tag+"']").prop("checked",check);
			if(check)
				divId.find("div.det-datalist table td[tag='"+tag+"']").show();
			else
				divId.find("div.det-datalist table td[tag='"+tag+"']").hide();
		}
	});
}
/**
 * 复选框事件
 */
function eventCheckBox(){
	var _divIdArray = new Array("#_channel_detail","#_trend_detail");
	$(_divIdArray).each(function(i,obj){
		var _div=$(obj);
		eventCheckBoxAcPri(_div);
	});
}

function bindDateClick(){
	/* 对比日期 */
	$(".db_date").hide();
	$(".compare_date").on("click", function() {
		$(".db_date").toggle();
	});
}
function loadTime(){
	var time = {
		    elem: '#date',
		    format: 'YYYY-MM-DD', // 分隔符可以任意定义，该例子表示只显示年月
		    max : laydate.now(-1),//设定最大日期为当前日期
		    istime: true,
			istoday: false,
		    choose: function(datas){ //选择日期	完毕的回调
		    }
		};
	laydate(time);
	$("input#date").val(laydate.now(-1));
}

/**
 * 是否选中对比日期
 */
function isCompareChecked(){
	return $("#dbrq").is(":checked");
}

/**
 * 明细数据事件绑定
 */
function bindMxEvent(){
	// 查看明细
	$('.ckmx').on('click', function() {
		$(".sjmx").show();
	});
	$(".close_btn").click(function() {
		$(".sjmx").hide();
	});
}

/**
 * 获取当前显示参数
 */
function getCurrentParam(){
	var paramArray = new Array();
	var channelTypeLi = $("ul#fwqd li.current:eq(0)");
	var type = channelTypeLi.attr("channel_type");
	paramArray.push(channelTypeLi.find("a").text());
	if("-1"!=type){
		if("4"==type){
			var tgqd_UL = $("dl#tgqd");
			var routeOne = tgqd_UL.find("select#queryRouteOne");
			var routeTwo = tgqd_UL.find("select#queryRouteTwo");
			var routeThree = tgqd_UL.find("select#queryRouteThree");
			var threeOption = routeThree.find("option:selected");
			var twoOption = routeTwo.find("option:selected");
			var oneOption = routeOne.find("option:selected");
			var threeChannel = threeOption.text();
			var twoChannel = twoOption.text();
			var oneChannel = oneOption.text();
			if(oneChannel){
				paramArray.push(oneChannel);
			}
			if(twoChannel){
				paramArray.push(twoChannel);
			}
			if(threeChannel){
				paramArray.push(threeChannel);
			}
			
		}
	}
	var param = paramArray.join("-");
	return param;
}
/**
 *下载明细 
 */
function downloadReqAt(){
	var params = formdata();
	var isc = isCompareChecked();
	if(isc){
		var compareDate = $("#date").val();
		params+="&compare=1&compare_date="+compareDate;
	}
	var currentParam = getCurrentParam();
	params+="&currentParam="+currentParam;
	var url =window.uaa_context_path + '/auth/at/download.htm?';
	url += params;
	window.open(url);
	
}
function bindDownloadAt(){
	$("#export_data").click(function(){
		downloadReqAt();
	});
}