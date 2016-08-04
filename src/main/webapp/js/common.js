var HB = "HB000A001";
var fwqdnum = {"全部":-1,"直接访问":0,"搜索引擎":1,"推广渠道":2,"其他渠道":3};
var ssyqnum = {"全部":-1,"Google":0,"百度":1,"搜狗":2,"360搜索":3,"雅虎":4,"其他":5};
Highcharts.setOptions({ 
    colors: ['#058DC7','#ED561B', '#50B432',  '#DDDF00', '#24CBE5', '#F00','#64E572', '#FF9655', 
'#FFF263', '#6AF9C4'] 
});
var _void0="javascript:void(0)";
var loadImg = null;
var loadImg2 = function(cpt){
	return "<img style='margin-left: 40%;margin-top: 10%;margin-bottom: 10%;' src='"+cpt+"/images/load2.gif'></img>";
};
//分页显示
//cp： 当前页    tp： 总页数     callback：function(data) 回调函数
function pageshow(cp,tp,callback){
		var sp = cp - 2;//开始页     -2表示当前页码前有2个页码显示
	var ep = cp + 2;//结束页     +2表示当前页码后有2个页码显示
	var eoff = ep - tp;
	if(eoff>0){
			sp = sp - eoff;
	}
	if(sp<=0){
		ep = ep -sp + 1;
	}
	var str = '';
	if(cp != 1)
		str = str + '<ul><li><span id="prepage" class="active">上一页</span></li>';
	else
		str = str + '<ul><li><span id="prepage">上一页</span></li>';
	for(var i= sp;i<=ep;i++){
		if(i>0&& i<=tp){
			if(i == cp)
				str = str + '<li class="num current"><a>'+i+'</a></li>';
			else
				str = str + '<li class="num"><a>'+i+'</a></li>';
		}
	}
	
	if(cp != tp)
		str = str + '<li><span class="active" id="nextpage">下一页<span></li></ul>';
	else
		str = str + '<li><span id="nextpage">下一页<span></li></ul>';
	if(typeof(callback) == "function")
		callback(str);
}

//Ajax请求
//ajaxRequest({'url':'',postMethod:'', 'params':'', 'callback':function(data){});
function ajaxRequest(obj) {
    var param = obj.params;
    var postmethod = obj.postMethod;
    
    if(/\..*\?/g.test(obj.url)){
        obj.url = obj.url + '&ajax=true';
    }else{
        obj.url = obj.url + '?ajax=true';
    }
    
    if(!obj.dataType){
        obj.dataType = 'html';
    }
    
    if(!obj.timeout){
        obj.timeout = 0;
    }
    var session_out = obj.session_out;
    if(undefined == obj.session_out){
    	session_out = true;
    }
    if(session_out){
    	//默认进入判断
    	syncSession();
    	var out = $("#session_out").val();
		if(eval(out))return;
    }
    $.ajax({
           type: postmethod,
           cache: false,
           url: obj.url,
           data : param,
           dataType: obj.dataType,
           timeout: obj.timeout,
           success: function(data){
               if (typeof(obj.callback) == "function") {
                   obj.callback(data);
               }
           },
           error:function(xhr,err){ 
               if(xhr.responseText == 'SESSION_TIME_OUT'){
                   //alert('您已退出系统，请重新登录.');
                   if(window.loginURL){
                       window.location.href = window.loginURL;
                   }
               }else if(xhr.statusText == 'timeout'){
                   //这里的处理将影响全局功能
               }else{
            	   console.log(err);
                   //alert('系统异常，请稍后再试.');
               }
            }
         });
}
/**
 * 同步判断会话是否超时
 * @param obj
 */
function syncSession() {
	var path = window.uaa_context_path ||""; 
	$.ajax({
		type: "post",
		cache: false,
		url: path+"/login/isSessionOut.htm",
		async: false,		 
		data : "",
		dataType: "html",
		success: function(data){
			if(null!=data && ""!=data){
				var div=$("<div>");
				div.attr("id","time_out_div");
				div.html(data);
				$("body").append(div);
				//会话超时
				$("#session_out").val("true");
			}
		},
		error:function(xhr,err){ 
			if(xhr.responseText == 'SESSION_TIME_OUT'){
				alert('您已退出系统，请重新登录.');
				if(window.loginURL){
					window.location.href = window.loginURL;
				}
			}else if(xhr.statusText == 'timeout'){
				//这里的处理将影响全局功能
			}else{
				alert('系统异常，请稍后再试.');
			}
			$("#session_out").val("true");
		}
	});
}


/**
 * @param num 要格式化的数字
 * @param n 保留小数位    
 * @returns
 */
function formatNum(num,n){
	num = String(num.toFixed(n));    
	var re = /(-?\d+)(\d{3})/;    
	while(re.test(num)) {
		num = num.replace(re,"$1,$2");
	}
	return num;
}

function loadMainData(url,data,callFunction){
	$("#outframe").html("");
	$("#outframe").hide();
	var dataVar={r:Math.random()};
	if(data){
		dataVar = data;
	}
	if(url.indexOf(window.uaa_context_path)==-1){
		url= window.uaa_context_path + url;
	}
	$("#main").load(url,dataVar,function(){
		if (typeof(callFunction) == "function") {
			callFunction();
        }
	});
	$("#main").show();
	$("#main").html(loadImg);
}

function loadOutData(url,data,callFunction){
	$("#main").html("");
	$("#main").hide();
	var dataVar={r:Math.random()};
	if(data){
		dataVar = data;
	}
	for ( var ie in dataVar) {
		var key = ie;
		var value = dataVar[ie];
		if(url.indexOf("?")==-1){
			url+="?"+key+"="+value;
		}else{
			url+="&"+key+"="+value;
		}
	}
	$("#outframe").attr("src",url);	
	/*$("#outframe").load(url,dataVar,function(){
		if (typeof(callFunction) == "function") {
			callFunction();
        }
	});*/
	
	$("#outframe").show();
	$("#outframe").load(function(){
		if (typeof(callFunction) == "function") {
			callFunction();
        }
	});
}


/**
 * tab切换事件
 */
function toggleTabEvent(){
	$(".tab_menu li").click(function(){
		var _this = $(this);
		_this.addClass("current").siblings().removeClass("current");
		var tag = _this.attr("tag");
		$(".tab_box div[tag='"+tag+"']").removeClass("hide").siblings().addClass("hide");
	});
}