<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>交易查询 - 好买基金交易网</title>
<#import "/util.ftl" as util />
<#import "/uri.ftl" as uri />
<#import "/common.ftl" as common />
<script type="text/javascript" src="${uri.context_path}/js/jquery/jquery.min.js"></script>
<script type="text/javascript" src="${uri.context_path}/js/jquery/jquery.ui.datepicker.js"></script>
<link rel="stylesheet" type="text/css" href="${uri.context_path}/css/datepicker.css" />
<style type="text/css">
table {
border-collapse: collapse;
text-align: right;
}
table .tit {
background: rgb(238, 238, 238);
}
table .tit td {
padding: 5px;
}
table td {
border-top: 1px solid rgb(216, 221, 226);
border-bottom: 1px solid rgb(216, 221, 226);
height: 36px;
font-size: 12px;
color: rgb(102, 102, 102);
}
body, td, input {
font-size: 12px;
font-family: Arial, "å®‹ä½“";
}
#ui-datepicker-div{
	display: none;
}
form{
	text-align:center;
}
.tdfir{
    border-right: 1px solid rgb(216, 221, 226);
}
</style>
<base target="_blank" />
</head>
<body>
	<form action="" method="post" id="postContent">
	<h3 style="margin-right:20px">howbuy2ehowbuy转 化率</h3>
	<div style="margin: 10px 0px 10px 0px">日期：
	<input type="text" class="startTime datepicker" name="start" id="starttime" <#if start??>value="${start?date('yyyy-MM-dd HH:mm:ss')?string('yyyy-MM-dd')}"</#if>/>&nbsp;&nbsp;<span style="*position:relative;*bottom:3px;">-</span>&nbsp;&nbsp;
	<input type="text" class="endTime datepicker" name="endTime" id="endtime" <#if end??>value="${end?date('yyyy-MM-dd HH:mm:ss')?string('yyyy-MM-dd')}"</#if>/>
	<!--&nbsp;&nbsp;&nbsp;&nbsp;未来天数
	<select name="intervals">
		<option value="">全部</option>
		<option value="10">10</option>
		<option value="30">30</option>
	</select-->
	<select name="channelID">
		<#--<#list mappingChannels?keys as channel>
		<option value="${channel}">${(mappingChannels[channel])!"default"}</option>
		</#list>-->
		<#list common.channelkeys as channel>
		<option value="${channel}">${(common.channels[channel])!"default"}</option>
		</#list>
    </select>
	<button value="" type="button" onclick="TimeJudge()">查询</button>
	</div>
	</form>
	<div id="ajaxShow"></div>
	
	<#include "../include/Howbuy2EhowbuyIntervalsStat.ftl">
	<script type="text/javascript">
	$(".startTime,.endTime").datepicker({
        changeMonth : true,
        changeYear : true,
    });
    function TimeJudge()
	{
		var start = $("#starttime").val();
        var end = $("#endtime").val();
        var now = new Date(); 
        
        var startTime = new Date(Date.parse(start.replace(/-/g, "/"))).getTime(); 
        var endTime = new Date(Date.parse(end.replace(/-/g, "/"))).getTime(); 
        var nowtime = now.getFullYear()+"-"+(now.getMonth()+1)+"-"+now.getDate();
        var nowTime = new Date(Date.parse(nowtime.replace(/-/g, "/"))).getTime();
        
        var dates = Math.abs((endTime - startTime))/(1000*60*60*24);<#--开始日期距结束日期-->
        var dates1 = Math.abs((nowTime - endTime))/(1000*60*60*24);<#--结束日期距今天-->
        
        var period = 10;//天数间隔
        if(start=="" || end == ""||start>end||start>nowtime||end>nowtime){
        	alert("请正确选择日期");
        	return false;
        }
        else if(dates1<period)
            {
                var remind = "结止日期距今应大于"+period;
                alert(remind);
                return false;
            }
        else
			ajaxRequest(
	                    {url:'/howbuy-uaa/statReport/Howbuy2EhowbuyIntervals.htm',
	                    postMethod:'POST',
	                    params:$('#postContent').serialize(),
	                    callback:function(data){
	                    	$('#ajaxShow').html(data);
	                    	}
	                    });
        
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
	                   alert('您已退出系统，请重新登录.');
	                   if(window.loginURL){
	                       window.location.href = window.loginURL;
	                   }
	               }else if(xhr.statusText == 'timeout'){
	                   //这里的处理将影响全局功能
	               }else{
	                   alert('系统异常，请稍后再试.');
	               }
	            }
	            
	         });
	}
	</script>
</body>
</html>