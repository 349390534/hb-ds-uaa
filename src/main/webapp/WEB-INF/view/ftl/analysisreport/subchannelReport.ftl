<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>交易查询 - 好买基金交易网</title>
<#import "/util.ftl" as util />
<#import "/uri.ftl" as uri />
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
</style>
</head>
<body>

	<form action="${uri.context_path}/analysisReport/subchannelReport.htm" method="post">
	<div style="margin: 10px 0px 10px 0px">统计日期：
	<input type="text" class="startTime datepicker" name="start" value="${start?date('yyyy-MM-dd HH:mm:ss')?string('yyyy-MM-dd')}"/>&nbsp;&nbsp;<span style="*position:relative;*bottom:3px;">-</span>&nbsp;&nbsp;
	<input type="text" class="endTime datepicker" name="end" value="${end?date('yyyy-MM-dd HH:mm:ss')?string('yyyy-MM-dd')}"/>
	着陆页模糊匹配：<input type="text" name="keyword" style="width: 200px" value="${keyword!}"/>
	<button value="" type="submit">查询</button>
	</div>
	<input type="hidden" name="channelId" value="${channelId!}"/>
	</form>
	
	<span style="float: right;margin: 10px 0px 10px 0px">记录总数：<#if (list??)>${list?size}</#if></span>
	<table cellpadding="0" cellspacing="0" width="100%">
                    <thead>
                        <tr class="tit">
                            <td width="10%"><strong>统计日期</strong></td>
                            <td width="14%"><strong>着陆页</strong></td>
                            <td width="8%"><strong>PV/UV</strong></td>
                            <td width="10%"><strong>用户数</strong></td>
                            <td width="12%" class="tdr"><strong>到达trade人数</strong></td>
                            <td width="12%" class="tdr"><strong>开户人数</strong></td>
                            <td width="12%" class="tdr"><strong>购买人数</strong></td>
                            <td width="14%" class="tdr"><strong>购买金额</strong></td>
                            <td width="8"><strong>预约人数</strong></td>
                        </tr>
                    </thead>
                    <tbody>
                        <#if (list??) && (list?size gt 0) >
                        <#list list as rs>
                        <tr>
                        	<td>${rs.reportDateTime?string('yyyy-MM-dd HH:mm:ss')}</td>
                            <td class="tdl">${rs.pageUrl!}</td>
                            <td>${rs.pv!}/${rs.uv!}</td>
                            <td>${rs.userTotal!}</td>
                            <td class="tdr">${rs.toTradeTotal!}</td>
                            <td class="tdr">${rs.openAccountTotal!}</td>
                            <td>${rs.tradeTotal!}<a href="${uri.context_path}/analysisReport/subchannelTradeDetail.htm?channelId=${rs.channelId}&start=${rs.reportDateTime?string('yyyy-MM-dd')}&end=${rs.reportDateTime?string('yyyy-MM-dd')}&url=${rs.pageUrl!}" target="_blank">[明细]</a></td>
                            <td>${rs.amountTotal!}</td>
                            <td>${rs.subscribeTotal!}</td>
                        </tr>
                        </#list>
                        <#else>
                        <tr><td colspan="9">无数据</td></tr>
                        </#if>
                    </tbody>
                </table>
	
<script type="text/javascript">
$(function(){
	//date选择器
	$(".startTime,.endTime").datepicker({
		changeMonth : true,
		changeYear : true
	});

});
</script>
</body>
</html>