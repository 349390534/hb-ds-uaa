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
</head>

<body>
<form action="month.htm">
<p>网站预约用户每月流量排名：
    <select name="statMonth" id="statMonth">
        <option <#if statMonth=='2013-01'>selected</#if> value="2013-01">2013年1月</option>
        <option <#if statMonth=='2013-02'>selected</#if> value="2013-02">2013年2月</option>
        <option <#if statMonth=='2013-03'>selected</#if> value="2013-03">2013年3月</option>
        <option <#if statMonth=='2013-04'>selected</#if> value="2013-04">2013年4月</option>
        <option <#if statMonth=='2013-05'>selected</#if> value="2013-05">2013年5月</option>
        <option <#if statMonth=='2013-06'>selected</#if> value="2013-06">2013年6月</option>
        <option <#if statMonth=='2013-07'>selected</#if> value="2013-07">2013年7月</option>
        <option <#if statMonth=='2013-08'>selected</#if> value="2013-08">2013年8月</option>
        <option <#if statMonth=='2013-09'>selected</#if> value="2013-09">2013年9月</option>
        <option <#if statMonth=='2013-10'>selected</#if> value="2013-10">2013年10月</option>
        <option <#if statMonth=='2013-11'>selected</#if> value="2013-11">2013年11月</option>
        <option <#if statMonth=='2013-12'>selected</#if> value="2013-12">2013年12月</option>
    </select>
    <input type="submit" name="button" id="button" value="查询"/>
    （每月计算一次，该月的流量排名）
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
<#list monthBookerPvStatForms as form>
    <tr>
        <#if form_index=0>
        <td rowspan="${monthBookerPvStatForms?size}" align="center">${statMonth!}</td>
        </#if>
        <td align="center">${form.subChannelRowNum!}</td>
        <td>${form.channel!}</td>
        <td align="right"><#if form.channelPv?exists>${form.channelPv?string.number!}</#if></td>
        <td>${form.subChannel!}</td>
        <td align="right"><#if form.subChannelPv?exists>${form.subChannelPv?string.number!}</#if></td>
    </tr>
</#list></table>

<p>&nbsp;</p>

</body>
</html>
