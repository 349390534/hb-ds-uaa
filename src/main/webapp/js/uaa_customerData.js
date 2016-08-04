/**
 * 
 */

var context_path = null;
var _path = null;
window.context_path = "";
var HB = "HB000A001";
var paraname = ["","openInst","openPlatform","cooperateType","openWangDian"];
var loadImage = null;
var params = "";
var lastClickChannel ="";
var lastChannelOrder="";
var curpage=1;
var pagerows = 10;
var maxpage = 5;
var orderby="stat_dt";
var order="desc";
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

function dataDetailSubmit(){
	//加会话判断
	syncSession();
	var out = $("#session_out").val();
	if(eval(out))return;
	var start = $("#startTime").val();
	var end = $("#endTime").val();
	if(start === "" || end ==="" || start > end){
		alert("请正确选择时间！！！");
		return false;
	}
	$("#custchannelOpenNormImage").hide();
	$("#custtrendOpenNormImage").hide();
	params="startDate="+start+"&endDate="+end;
	
	var fxwd = $("#fenxiweidu li.current:eq(0)");
	var farr=fxwd.attr("para").split("%");
	params = params + "&" + farr[0] + "=" + farr[1];
		
	var paraval = new Array(5);
	var openInst = null;//当前选择开户机构
	var paramMap = new HashMap();
	$("#qdChoose li.current").each(function(i,obj){
		var para = $(obj).attr("para"); 
		var arr=para.split("%");
		var len = arr.length;
		if(len>=2){
			var pType = arr[0];
			var val = arr[len-1];
			if(pType=="openInst"){
				openInst = val;
			}
			paramMap.put(pType,val);
		}
	});
	var max=paraname.length;
	//非好买机构的参数设置
	if("-1"!=openInst && openInst!=HB){
		paramMap.put(paraname[2],"A");//非-1的值
		paramMap.put(paraname[3],"A");//非-1的值
	}
	for(var i = 1; i < max; i++)
	{
	 	var pType=paraname[i];
		var paraval = paramMap.get(pType);
		if(null!=paraval && ""!=paraval){
			params = params + "&" + pType+ "=" + paraval;
		}
	}
	
	var khjg = $(".khjg li.current").attr("para").split("%");
	var khjgLength = khjg.length;
	
	$("#qdChoose select").each(function(){
		var it = $(this).val();
		if(it !== "-1"){
			params = params + "&openWangDian=" + it;
		}
	});
	$("#custqd").click();
	ajaxRequest(
	{
		url:context_path+'/auth/customerData/customerGraph.htm',
        postMethod:'POST',
        params:params,
        session_out:false,
        callback:function(data){
        	$('#custGraph').html(data);
        	$("#dataok").removeAttr("disabled");
        }
    });
    $('#custGraph').html(loadImage);
    $("#dataok").attr("disabled","disabled");

    //触发全选明细
    selectQuato();
    //渠道明细
    custChannelDetail();
    //趋势明细
    custTrendDetail();

}
function custChannelDetail(){
	var orderby = "ljkhs";//开户渠道默认按开户人数降序排列
	
	var detailParams = detailChecked("#custchannelDetail","#custchannelDetailOpen input");
	if(detailParams == false){
		return false;
	}
	detailParams = detailParams + "&orderBy="+orderby+"&order=desc";
	
	var reqObj=
	{
		url:context_path+'/auth/customerData/custChannelDetail.htm',
        postMethod:'POST',
        params:params + '&' + detailParams,
        session_out:false,
        callback:function(data){
	       $("div[name='oDivL_custchannelDataTab']").each(function(i,obj){
	        	$(obj).hide();
	        	$(obj).remove();
	        });
        	$('#custChannelDetail').html(data);
	        $("#custChannelDetail").scrollLeft(0);
//	        frozenChannelTable();
    	}
	};
	ajaxRequest(reqObj);
	
	$('#custChannelDetail').html(loadImage);
	
}
function custTrendDetail(){
	orderby="stat_dt";
	var order="desc";
	lastClickTrend = "" ;
	lastTrendOrder = "" ;
	var detailParams = detailChecked("#custtrendDetailAll","#custtrendDetailOpen input");
	if(detailParams == false)
		return false;
	detailParams = detailParams + "&orderBy="+orderby+"&pageRows="+pagerows+"&curPage=1&order="+order;
	if(params == ""){
		alert("请确定分析维度！");
		return false;
	}
	else
	ajaxRequest({
		url:context_path+ '/auth/customerData/custtrendDetail.htm',
        postMethod:'POST',
        params:params+"&"+detailParams,
        session_out:false,
        callback:function(data){
        	hideF("custtrendDetailTable");
        	$('#custTrendDetail').html(data);
        	maxpage = Math.ceil($("#total-rows").text()/pagerows); 
        	if(maxpage > 1){
        		$("#nextpage").addClass("active");
        	}
        	
        	//frozenTrendTable();
    	}
	});
	
	$('#custTrendDetail').html(loadImage);
}
function selectQuato(){
	$("#custchannelDetailOpen input[type=checkbox]").prop("checked",true);
	
	//趋势明细全选事件触发
	$("#custtrendDetailOpen input[type=checkbox]").prop("checked",true);
}
function detailChecked(allNormId,openNormId){
	var cnt = 0;
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
		if($(this).prop("checked")){
			cnt = i + 1;
		}
	});
	if(cnt === 0)
	{
		alert("请选择指标！");
		return false;
	}
	else{
		var openNorm = "";
		var openNormArray = [];
		$(openNormId).each(function(i){
			if($(this).prop("checked")){
				var qval = $(this).val(); 
				if("-1"!=qval){
					openNormArray.push(qval);
				}
			};
		});
		openNorm = openNormArray.join("$");
		
	};
	params = "openNorm=" + openNorm ;
	return params;
}
function timeChangeEvent(){
	//时间选择
	$("#selectOption").change(function(){
		var opt = $(this).val();
		if(opt === "zdy"){
			$("#startTime").val("");
			$("#endTime").val("");
		}
		else if(opt === "0"){
			$("#startTime").val(laydate.now());
			$("#endTime").val(laydate.now());
		}
		else{
			$("#startTime").val(laydate.now(0-opt));
			$("#endTime").val(laydate.now(-1));
		}
	});
}
function qdLiEvent(){
	$("#qdChoose li").click(function(){
		var arr = $(this).attr("para").split("%");
		var id = arr[0];
		for(var i = 1;i<arr.length;i++)
		{
			id = id + arr[i];
		}
		//$(this).addClass("current");
		var p = $(this).parent().parent().parent();
		$("#qdChoose select").hide();
		$("#qdChoose select").each(function(){
				$(this).val("-1");
		});
		$("#qdChoose select").hide();
		if(p.hasClass("khjg")){
			$("#qdChoose li").removeClass("current");
			$(this).addClass("current");
			$("#qdChoose dl.Inst").hide();
			$("#qdChoose dl.Platform").hide();
			
			$("#"+id).show();
			$("#"+id).find("li").eq(0).addClass("current");
		}
		if(p.hasClass("Inst")){
			$("#qdChoose .Inst li").removeClass("current");
			$("#qdChoose .Platform li").removeClass("current");
			$("#qdChoose dl.Platform").hide();
			
			$(this).addClass("current");
			$("#"+id).show();
			$("#"+id).find("li").eq(0).addClass("current");
		}
		if(p.hasClass("Platform")){
			$(this).parent().find("li").removeClass("current");
			$("#qdChoose .Platform li").removeClass("current");
			$(this).addClass("current");
			$("#"+id).show();
		}
	});
}

function defaultEvent(){
	timeChangeEvent();
	qdLiEvent();
	$("#qdChoose dl.Inst").hide();
	 $("#qdChoose dl.Platform").hide();
	 $("#openInstHB000A001").find("li").eq(1).hide();
	 selectQuato();
}
function someEvent(){
	//渠道明细全选
	$(".custchannelOpenNorm td:eq(1) input").click(function(){
		$(".custchannelOpenNorm input").prop("checked",$(this).prop("checked"));
	});
	//趋势明细全选
	$(".custtrendOpenNorm td:eq(1) input").click(function(){
		$(".custtrendOpenNorm input").prop("checked",$(this).prop("checked"));
	});
	//渠道明细
	$("#custchannelDetail input[type=checkbox]").click(function(){
		var ch = $(this).prop("checked");
		var val = $(this).val();
		if("-1"==val){
			//全选按钮
			var cst = $(this).attr("cst");
			$("#custchannelDetail table tr[cst='"+cst+"'] input[type=checkbox]").each(function(j,inp){
				var inpVal = $(inp).val();
				if(ch){
					$("#custchannelDataTab").find("td[head='"+inpVal+"']").show();
				}else{
					$("#custchannelDataTab").find("td[head='"+inpVal+"']").hide();
				}
			});
		}else {
			if(ch){
				$("#custchannelDataTab").find("td[head='"+val+"']").show();
			}else{
				$("#custchannelDataTab").find("td[head='"+val+"']").hide();
			}
		}
	});
	//趋势明细
	$("#custtrendDetailAll input[type=checkbox]").click(function(){
		var ch = $(this).prop("checked");
		var val = $(this).val();
		if("-1"==val){
			//全选按钮
			var cst = $(this).attr("cst");
			$("#custtrendDetailAll table tr[cst='"+cst+"'] input[type=checkbox]").each(function(j,inp){
				var inpVal = $(inp).val();
				if(ch){
					$("#custtrendDetailTable").find("td[head='"+inpVal+"']").show();
				}else{
					$("#custtrendDetailTable").find("td[head='"+inpVal+"']").hide();
				}
			});
		}else {
			if(ch){
				$("#custtrendDetailTable").find("td[head='"+val+"']").show();
			}else{
				$("#custtrendDetailTable").find("td[head='"+val+"']").hide();
			}
		}
	});
	//渠道明细导出
	$("#custchannelDetailExport").click(function(){
		var detailParams = detailChecked("#custchannelDetail","#custchannelDetailOpen input");
		if(detailParams == false)
			return false;
		
		openBuildUrl('/auth/customerData/custchannelDetailExport.htm',detailParams);
	});
	//趋势明细导出
	$("#custtrendDetailExport").click(function(){
		var currentOrderby = "";
		var currentOrder = "";
		if(lastClickTrend !="" && lastTrendOrder !="")
		{
			currentOrderby = lastClickTrend;
			currentOrder = lastTrendOrder;
		}
		else
		{
			currentOrderby = orderby;
			currentOrder = order;
		}
		var detailParams = detailChecked("#custtrendDetailAll","#custtrendDetailOpen input");
		detailParams = detailParams + "&orderBy="+currentOrderby+"&pageRows="+pagerows+"&curPage=1&order="+currentOrder;
		openBuildUrl('/auth/customerData/custtrendDetailExport.htm',detailParams);
	});
	
	$(".custchannelOpenNorm input").click(function(){
		changeCheckBox(".custchannelOpenNorm","#custchannelOpenNormImage");
	});
	$(".custtrendOpenNorm input").click(function(){
		changeCheckBox(".custtrendOpenNorm","#custtrendOpenNormImage");
	});
}

function pageEvent(){
	if(maxpage>1){
		$("#nextpage").addClass("active");
	};
	//当前单页显示行数
	$("#rowsToshow").change(function(){
		var currentOrderby = "";
		var currentOrder = "";
		if(lastClickTrend !="" && lastTrendOrder !="")
		{
			currentOrderby = lastClickTrend;
			currentOrder = lastTrendOrder;
		}
		else
		{
			currentOrderby = orderby;
			currentOrder = order;
		}
		pagerows = $("#rowsToshow").val();
		curpage = 1;
		var detailParams = detailChecked("#custtrendDetailAll","#custtrendDetailOpen input");
		if(detailParams == false)
			return false;
		detailParams = detailParams + "&pageRows="+pagerows+"&curPage="+curpage+"&orderBy="+currentOrderby+"&order="+currentOrder;
		ajaxRequest(
                {url:context_path+'/auth/customerData/custtrendDetail.htm',
                postMethod:'POST',
                params:params+"&"+detailParams,
                callback:function(data){
                	$('#custTrendDetail').html(data);
                	showTrendTableContent();
                	$('#showPageLoad').hide();
                	maxpage = Math.ceil($("#total-rows").text()/pagerows);
            		if(maxpage > 1)
            			$("#nextpage").addClass("active");
            		$("#custtrendDetailThead a").each(function(){
	                		if($(this).attr("tag") === orderby &&  orderby!='stat_dt')
	                		{
	                			if(currentOrder === "desc"){
	                				$(this).addClass("sort-down");
	                			}
	                			if(currentOrder === "asc"){
	                				$(this).addClass("sort-up");
	                			}
	                		}
	                });
                }
                
         });
		$("#showPageLoad").show();
	});
	//前一页
	$("#prepage").click(function(){
		var currentOrderby = "";
		var currentOrder = "";
		if(lastClickTrend !="" && lastTrendOrder !="")
		{
			currentOrderby = lastClickTrend;
			currentOrder = lastTrendOrder;
		}
		else
		{
			currentOrderby = orderby;
			currentOrder = order;
		}
		curpage = curpage - 1;
		var detailParams = detailChecked("#custtrendDetailAll","#custtrendDetailOpen input");
		if(detailParams == false)
			return false;
		if(curpage>0){
			pageshow(curpage,maxpage,function(data){
				$("#pagecontrol").html(data);
				pageEvent();
				ajaxRequest(
	                    {url:context_path+'/auth/customerData/custtrendDetailTbody.htm',
	                    postMethod:'POST',
	                    params:params+"&"+detailParams+"&pageRows="+pagerows+"&curPage="+curpage+"&orderBy="+currentOrderby+"&order="+currentOrder,
	                    callback:function(data){	                    
	                    	$('#custTrendDetailTbody').html(data);
	                    	showtbodyContent();
	                    	$("#showPageLoad").hide();
	                    	}
	                    });
	                    
			});
			$("#showPageLoad").show();
		}
		else
			curpage = 1;
	});
	//后一页
	$("#nextpage").click(function(){
		var currentOrderby = "";
		var currentOrder = "";
		if(lastClickTrend !="" && lastTrendOrder !="")
		{
			currentOrderby = lastClickTrend;
			currentOrder = lastTrendOrder;
		}
		else
		{
			currentOrderby = orderby;
			currentOrder = order;
		}
		curpage = curpage + 1;
		var detailParams = detailChecked("#custtrendDetailAll","#custtrendDetailOpen input");
		if(detailParams == false)
			return false;
		if(curpage<=maxpage){
			pageshow(curpage,maxpage,function(data){
				$("#pagecontrol").html(data);
				pageEvent();
				ajaxRequest(
	                    {url:context_path+'/auth/customerData/custtrendDetailTbody.htm',
	                    postMethod:'POST',
	                    params:params+"&"+detailParams+"&pageRows="+pagerows+"&curPage="+curpage+"&orderBy="+currentOrderby+"&order="+currentOrder,
	                    callback:function(data){
	                    	$('#custTrendDetailTbody').html(data);
	                    	showtbodyContent();
	                    	$("#showPageLoad").hide();
	                    	}
	                    });
			});
			$("#showPageLoad").show();
		}
		else 
			curpage = maxpage;
	});
	//点击某一页
	$("#pagecontrol li a").click(function(){
		var currentOrderby = "";
		var currentOrder = "";
		if(lastClickTrend !="" && lastTrendOrder !="")
		{
			currentOrderby = lastClickTrend;
			currentOrder = lastTrendOrder;
		}
		else
		{
			currentOrderby = orderby;
			currentOrder = order;
		}
		curpage = Number($(this).text());
		var detailParams = detailChecked("#custtrendDetailAll","#custtrendDetailOpen input");
		if(detailParams == false)
			return false;
		pageshow(curpage,maxpage,function(data){
			$("#pagecontrol").html(data);
			pageEvent();
			ajaxRequest(
	                {url:context_path+'/auth/customerData/custtrendDetailTbody.htm',
	                postMethod:'POST',
	                params:params+"&"+detailParams+"&pageRows="+pagerows+"&curPage="+curpage+"&orderBy="+currentOrderby+"&order="+currentOrder,
	                callback:function(data){
	                	$('#custTrendDetailTbody').html(data);
	                	showtbodyContent();
	                	$("#showPageLoad").hide();
	                }
	        });
		});
		$("#showPageLoad").show();
	});

}
function openBuildUrl(uri,detailParams){
	var param = getSearchParam();
	var currentParam = "&currentParam="+param;
	var url=context_path+uri+'?'+params+'&'+detailParams+currentParam;
	window.open(encodeURI(url));
}
function getSearchParam(){
	var channel = $("#custCondition_exp").text().replace(/\s/g,"");
	var time = $("#custCondition_exp").parent().find(" > div").text().replace(/\s/g,"");
	var param = channel +time;
	return param;
}
function showtbodyContent(){
	$("#custtrendDetailThead td").each(function(){
		var head = $(this).attr("head");
		var display = $(this).css("display");
		if("none" == display){
			$("#custTrendDetailTbody").find("td[head='"+head+"']").hide();
		}
	});
}
function showTrendTableContent(){
		
	$("#custtrendDetailAll input[type=checkbox]").each(function(){
		var ch = $(this).prop("checked");
		var val = $(this).val();
		if("-1"!=val){
			if(ch){
				$("#custtrendDetailTable").find("td[head='"+val+"']").show();
			}else{
				$("#custtrendDetailTable").find("td[head='"+val+"']").hide();
			}
		}
	});
}
/**
 * 加载查询条件
 */
function loadFundCondition(){
	var con_li = $("#custCondition li.current");
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
		var wd = $("#qdChoose select:visible").eq(0).find("option:selected");
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
		$("#custCondition_exp").html(con_exp);
	}
}
function changeCheckBox(objectSelect,objectImage){
	var cnt = 0;
		var flag = false;
		
		var inpEleSe = "input[type=checkbox]";
		var objectInput = objectSelect + " td:eq(1) input";
		var size = $(objectSelect).children().find(inpEleSe).size() - 1;
		$(objectSelect).find(inpEleSe).each(function(){
			if($(this).prop("checked")){
				cnt = cnt + 1;
			}
		});
		
		if($(objectInput).prop("checked")){	
			flag = true;
		}
		if(flag){
			cnt = cnt - 1;
		}
		if(cnt != size && cnt != 0 ){
			$(objectImage).show();
			$(objectInput).prop("checked",false);
		}else if(cnt == 0){
			$(objectInput).prop("checked",false);
			$(objectImage).hide();
		}
		else{
			$(objectInput).prop("checked",true);
			$(objectImage).hide();
		}
}
var tab =["custchannelDataTab","custtrendDetailTable"];

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
	var exp = $('#custGraph #container2:has(div)');
	if(exp.length>=1){
		var col = $("#custchannelDataTab #custChannelDetailThead tr td.wdqd").length;
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
function hideF(tag){
	$("div[name='oDivL_"+tab+"']").each(function(i,obj){
		$(obj).hide();
    	$(obj).remove();
    });
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

