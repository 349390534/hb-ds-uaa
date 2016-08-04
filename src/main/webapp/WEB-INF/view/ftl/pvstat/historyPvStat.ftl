<#import "/uri.ftl" as uri />
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
        "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>用户行为分析报表</title>
    <style>
        body, td {
            font-size: 12px;
        }

        td {
            height: 24px;
            line-height: 24px;
            border-right: 1px solid #ccc;
            border-bottom: 1px solid #ccc;
            padding: 0 10px;
        }

        table {
            border-left: 1px solid #ccc;
            border-top: 1px solid #ccc;
        }

        p {
            font-size: 14px;
            font-weight: bold;
        }

        .cRed {
            color: #c00;
        }
    </style>
    <link rel="stylesheet" type="text/css" href="${uri.css_host}/min/b=css&f=jQuery/ui/datepicker.css" />
</head>

<body>
<p style="color:#C00">网站预约用户浏览情况跟踪表：</p>

<form action="history.htm">
<p>网站预约用户每日流量排名：
    <input name="statDate" type="text" id="statDate" class="date" value="${statDate!}"/>
    <input type="submit" name="button" id="button" value="查询"/>
    （每日计算一次，该日之前的累计流量排名）
</p>
</form>

<table width="750" border="0" cellspacing="0" cellpadding="0">
    <tr align="center" bgcolor="#FFFF00">
        <td rowspan="2"><strong>日期</strong></td>
        <td rowspan="2"><strong>排名</strong></td>
        <td colspan="2"><strong>网站频道</strong></td>
        <td colspan="2"><strong>全站网页项目</strong></td>
    </tr>
    <tr>
        <td align="center" bgcolor="#FFFF00"><strong>频道名称</strong></td>
        <td align="center" bgcolor="#FFFF00"><strong>PV</strong></td>
        <td align="center" bgcolor="#FFFF00"><strong>网页项目名称</strong></td>
        <td align="center" bgcolor="#FFFF00"><strong>PV</strong></td>
    </tr>
<#list historyBookerPvStatForms as form>
    <tr>
        <#if form_index=0>
            <td rowspan="${historyBookerPvStatForms?size}" align="center">${statDate!}</td>
        </#if>
        <td align="center">${form.subChannelRowNum!}</td>
        <td>${form.channel!}</td>
        <td align="right"><#if form.channelPv?exists>${form.channelPv?string.number!}</#if></td>
        <td>${form.subChannel!}</td>
        <td align="right"><#if form.subChannelPv?exists>${form.subChannelPv?string.number!}</#if></td>
    </tr>
</#list>

</table>


</body>
<script type="text/javascript" src="${uri.js_host}/min/b=js/jquery&f=jquery1.3.2.js,ui/ui.core.js,ui/ui.datepicker.js,ui/ui.dialog.js,ui/ui.draggable.js"></script>
<script type="text/javascript">
    $(".date").datepicker({
        changeMonth: true,
        changeYear: true
    });
    $(document).ready(function(){});
</script>
</html>
