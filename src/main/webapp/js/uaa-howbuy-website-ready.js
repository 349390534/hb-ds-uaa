$(function(){
	//初始化根渠道数据和搜索引擎渠道数据
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
		var input='<li><input type="button" value="确定" style="width: 80px;" class="btn-style-a" onclick="accessChannelSubmit()"></li>';
		zero_select+=zero_select_end;
		//zeroChannel =all+zero_select+input;//取消搜索引擎下拉框
		zeroChannel =all+input;
		$("#ssyq").prepend(zeroChannel);
		$("#other_channel").append(other_channel_op);
	}
	
	//访问渠道
	$(".fwqd ul li:has(a)").each(function(i,obj){
		$(obj).click(function(){
			if($(this).hasClass("ssyq")){
				$(".ssyq-box ul li:has(a)").first().click();
				$(".ssyq-box").show();
			}else{
				$(".ssyq-box").hide();
			}
			if($(this).hasClass("tgqd")){
				$(".tgqd-box").show();
			}else{
				$(".tgqd-box").hide();
			}
			if($(this).hasClass("other-qd")){
				$(".other-qd-box").show();
			}else{
				$(".other-qd-box").hide();
				$("#other_channel").val("");
			}
			var inp = $(obj).find("input");
			if(inp.length==0){
				$(this).addClass("current").siblings("li").removeClass("current");
			}
			switch(i){
				case 0:
				case 1:
				  $(this).parent().find('li').find('.btn-style-a').show();
				  break;
				case 2:
				case 3:
				case 4:				
				$(this).parent().find('li').find('.btn-style-a').hide();
				break;
			}				
			return false;
		});

	});
	
	$(".ssyq-box ul li:has(a)").click(function(){
		$(this).addClass("current").siblings("li").removeClass("current");
		$("#ssyqqt").val("");
	});
	
	$("#ssyqqt").change(function(){
		if($(this).val() != ""){
			$(".ssyq-box ul li:has(a)").removeClass("current");
		}else{
			$("#ssyq li:first").addClass("current");
		}
	});
	
	$(".tab_menu li").click(function(){
		var _this = $(this);
		_this.addClass("current").siblings().removeClass("current");
		$("div.tab_box div.con:visible").addClass("hide");
		var tag=_this.attr("tag");
		$("div.tab_box div.con[tag='"+tag+"']").removeClass("hide");
	});
	
	//注册checkbox事件------ begin
	$("#channelFlowData input[type=checkbox]").click(function(){
		var _this = $(this);
		var val = _this.val();
		var check = _this.prop("checked");
		if(val==""){//全选按钮
			$("#channelFlowData input[tag]").prop("checked",check);
			if(check){
				//显示
				$("#channelFlowData input[tag]").each(function(i,obj){
					var q = $(obj).attr("tag");
					$("#channelDataTab td[head='"+q+"']").show();
				});
			}else{
				//隐藏
				$("#channelFlowData input[tag]").each(function(i,obj){
					var q = $(obj).attr("tag");
					$("#channelDataTab td[head='"+q+"']").hide();
				});
			}
		}else if(""!=val){
			if(check){
				//显示
				$("#channelDataTab td[head='"+val+"']").show();
			}else{
				//隐藏
				$("#channelDataTab td[head='"+val+"']").hide();
			}
		}
	});
	
	$("#channelOpenData input[type=checkbox]").click(function(){
		var _this = $(this);
		var val = _this.val();
		var check = _this.prop("checked");
		if(val==""){//全选按钮
			$("#channelOpenData input[tag]").prop("checked",check);
			if(check){
				//显示
				$("#channelOpenData input[tag]").each(function(i,obj){
					var q = $(obj).attr("tag");
					$("#channelDataTab td[head='"+q+"']").show();
				});
			}else{
				//隐藏
				$("#channelOpenData input[tag]").each(function(i,obj){
					var q = $(obj).attr("tag");
					$("#channelDataTab td[head='"+q+"']").hide();
				});
			}
		}else if(""!=val){
			if(check){
				//显示
				$("#channelDataTab td[head='"+val+"']").show();
			}else{
				//隐藏
				$("#channelDataTab td[head='"+val+"']").hide();
			}
		}
	});
	
	$("#channelTradeData input[type=checkbox]").click(function(){
		var _this = $(this);
		var val = _this.val();
		var check = _this.prop("checked");
		if(val==""){//全选按钮
			$("#channelTradeData input[tag]").prop("checked",check);
			if(check){
				//显示
				$("#channelTradeData input[tag]").each(function(i,obj){
					var q = $(obj).attr("tag");
					$("#channelDataTab td[head='"+q+"']").show();
				});
			}else{
				//隐藏
				$("#channelTradeData input[tag]").each(function(i,obj){
					var q = $(obj).attr("tag");
					$("#channelDataTab td[head='"+q+"']").hide();
				});
			}
		}else if(""!=val){
			if(check){
				//显示
				$("#channelDataTab td[head='"+val+"']").show();
			}else{
				//隐藏
				$("#channelDataTab td[head='"+val+"']").hide();
			}
		}
	});
	
	
	$("#trendFlowData input[type=checkbox]").click(function(){
		var _this = $(this);
		var val = _this.val();
		var check = _this.prop("checked");
		if(val==""){//全选按钮
			$("#trendFlowData input[tag]").prop("checked",check);
			if(check){
				//显示
				$("#trendFlowData input[tag]").each(function(i,obj){
					var q = $(obj).attr("tag");
					$("#trendDataTab td[head='"+q+"']").show();
				});
			}else{
				//隐藏
				$("#trendFlowData input[tag]").each(function(i,obj){
					var q = $(obj).attr("tag");
					$("#trendDataTab td[head='"+q+"']").hide();
				});
			}
		}else if(""!=val){
			if(check){
				//显示
				$("#trendDataTab td[head='"+val+"']").show();
			}else{
				//隐藏
				$("#trendDataTab td[head='"+val+"']").hide();
			}
		}
	});
	
	
	$("#trendOpenData input[type=checkbox]").click(function(){
		var _this = $(this);
		var val = _this.val();
		var check = _this.prop("checked");
		if(val==""){//全选按钮
			$("#trendOpenData input[tag]").prop("checked",check);
			if(check){
				//显示
				$("#trendOpenData input[tag]").each(function(i,obj){
					var q = $(obj).attr("tag");
					$("#trendDataTab td[head='"+q+"']").show();
				});
			}else{
				//隐藏
				$("#trendOpenData input[tag]").each(function(i,obj){
					var q = $(obj).attr("tag");
					$("#trendDataTab td[head='"+q+"']").hide();
				});
			}
		}else if(""!=val){
			if(check){
				//显示
				$("#trendDataTab td[head='"+val+"']").show();
			}else{
				//隐藏
				$("#trendDataTab td[head='"+val+"']").hide();
			}
		}
	});
	
	
	$("#trendTradeData input[type=checkbox]").click(function(){
		var _this = $(this);
		var val = _this.val();
		var check = _this.prop("checked");
		if(val==""){//全选按钮
			$("#trendTradeData input[tag]").prop("checked",check);
			if(check){
				//显示
				$("#trendTradeData input[tag]").each(function(i,obj){
					var q = $(obj).attr("tag");
					$("#trendDataTab td[head='"+q+"']").show();
				});
			}else{
				//隐藏
				$("#trendTradeData input[tag]").each(function(i,obj){
					var q = $(obj).attr("tag");
					$("#trendDataTab td[head='"+q+"']").hide();
				});
			}
		}else if(""!=val){
			if(check){
				//显示
				$("#trendDataTab td[head='"+val+"']").show();
			}else{
				//隐藏
				$("#trendDataTab td[head='"+val+"']").hide();
			}
		}
	
	});
	
	//注册checkbox事件------ end
	
	
	$("#fwqd .tgqd").click(function(){
		var param = "level=1";
		var reqObj = {
			url:context_path + "/" + _path + '/initailQueryQd.htm',
            postMethod:'POST',
            params:param,
            callback:function(data){
               	$("#indexQueryRouteOne").html(data);
               	$("li#indexQueryRouteTwo select#queryRouteTwo").html("");
               	$("li#indexQueryRouteThree select#queryRouteThree").html("");
            }
        };
		ajaxRequest(reqObj);
	});
	
	//时间段选择
	$("#selectOption").change(function(){
		var opt = $(this).val();
		if(opt == "zdy"){
			$("#beginDate").val("");
			$("#endDate").val("");
		}
		else{
			$("#beginDate").val(laydate.now(0-opt));
			$("#endDate").val(laydate.now(-1));
		}
	});

	//触发查询事件
	accessChannelSubmit();
	//全选所有指标复选框
	clickAllCheckbox();
});

