<#import "/uri.ftl" as uri />
<#import "/common.ftl" as common />
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
    <link rel="stylesheet" type="text/css" href="${uri.css_host}/min/b=css&f=jQuery/ui/datepicker.css"/>
</head>

<body>
<p style="color:#C00">每日预约用户行为跟踪情况：</p>

<form action="track.htm">
    <p>按日期查询：
        <input name="actionDate" type="text" id="actionDate" class="date" value="${actionDate!}"/>
        <#--<input name="endDate" type="text" id="endDate" class="date" value="${endDate!}"/>-->
        <input type="submit" name="button" id="button" value="查询"/>
    </p>
</form>
<table width="1280" border="0" cellspacing="0" cellpadding="0">
<tr align="center" bgcolor="#FFFF00">
    <td width="107" rowspan="2"><strong>用户序号</strong></td>
    <td width="156" rowspan="2"><strong>用户标识</strong></td>
    <td width="156" rowspan="2"><strong>身份信息</strong></td>
    <td colspan="3"><strong>浏览页面轨迹</strong></td>
</tr>
<tr>
    <td width="69" align="center" bgcolor="#FFFF00"><strong>顺序</strong></td>
    <td width="254" align="center" bgcolor="#FFFF00"><strong>页面地址</strong></td>
    <td width="254" align="center" bgcolor="#FFFF00"><strong>访问时间</strong></td>
<#--<td width="63" align="center" bgcolor="#FFFF00"><strong>顺序</strong></td>-->
<#--<td width="300" align="center" bgcolor="#FFFF00"><strong>页面地址</strong></td>-->
</tr>
<#assign i = 0>
<#assign j = 0>
<#list bookerTrackForms as form>
<tr>
    <#--<#if form_index=0>-->
        <#--<td rowspan="${bookerTrackForms?size}" align="center">${acitonDate!}</td>-->
    <#--</#if>-->
    <#if i==0>
        <#assign i=form.cookieNum>
        <#assign j=j+1>
        <td rowspan="${form.cookieNum!}" align="center">${j!}</td>
        <td rowspan="${form.cookieNum!}" align="center">${form.cookie!}</td>
    </#if>
    <#assign i=i-1>
<#--<td  align="center">${form.cookie!}</td>-->
    <td align="center">${common.confoundPhone(form.phone)}</td>
    <td align="center">${form.cookieNum-i!}</td>
    <td><#if form.phone?exists><span class="cRed">${form.pvUrl!}</span><#else>${form.pvUrl!}</#if></td>
    <td>${form.visitTime!}</td>
<#--<td align="center">1</td>-->
<#--<td>http://www.howbuy.com/trust/TG0000133.htm</td>-->
</tr>
</#list>
<#--<tr>-->
<#--<td align="center">2</td>-->
<#--<td>http://www.howbuy.com/</td>-->
<#--<td align="center">&nbsp;</td>-->
<#--<td>&nbsp;</td>-->
<#--</tr>-->
<#--<tr>-->
<#--<td align="center">3</td>-->
<#--<td>http://www.howbuy.com/trust/list.htm</td>-->
<#--<td align="center">&nbsp;</td>-->
<#--<td>&nbsp;</td>-->
<#--</tr>-->
<#--<tr>-->
<#--<td align="center">4</td>-->
<#--<td>http://www.howbuy.com/trust/company.htm</td>-->
<#--<td align="center">&nbsp;</td>-->
<#--<td>&nbsp;</td>-->
<#--</tr>-->
<#--<tr>-->
<#--<td align="center">5</td>-->
<#--<td>http://simu.howbuy.com/</td>-->
<#--<td align="center">&nbsp;</td>-->
<#--<td>&nbsp;</td>-->
<#--</tr>-->
<#--<tr>-->
<#--<td align="center">6</td>-->
<#--<td><span class="cRed">http://www.howbuy.com/trust/TG0000133.htm</span></td>-->
<#--<td align="center">&nbsp;</td>-->
<#--<td>&nbsp;</td>-->
<#--</tr>-->
<#--<tr>-->
<#--<td align="center">7</td>-->
<#--<td>&nbsp;</td>-->
<#--<td align="center">&nbsp;</td>-->
<#--<td>&nbsp;</td>-->
<#--</tr>-->
<#--<tr>-->
<#--<td rowspan="2" align="center" bgcolor="#eeeeee">13300138000</td>-->
<#--<td height="41" align="center" bgcolor="#eeeeee">1</td>-->
<#--<td bgcolor="#eeeeee">http://www.howbuy.com/trust/company.htm</td>-->
<#--<td align="center" bgcolor="#eeeeee">1</td>-->
<#--<td bgcolor="#eeeeee">http://www.howbuy.com/trust/TG0000132.htm</td>-->
<#--</tr>-->
<#--<tr>-->
<#--<td align="center" bgcolor="#eeeeee">2</td>-->
<#--<td bgcolor="#eeeeee"><span class="cRed">http://www.howbuy.com/trust/TG0000132.htm</span></td>-->
<#--<td align="center" bgcolor="#eeeeee">&nbsp;</td>-->
<#--<td bgcolor="#eeeeee">&nbsp;</td>-->
<#--</tr>-->
<#--<tr>-->
<#--<td rowspan="3" align="center">13800138011</td>-->
<#--<td align="center">1</td>-->
<#--<td>http://www.howbuy.com/trust/list.htm</td>-->
<#--<td align="center">1</td>-->
<#--<td>http://www.howbuy.com/trust/company.htm</td>-->
<#--</tr>-->
<#--<tr>-->
<#--<td align="center">2</td>-->
<#--<td><span class="cRed">http://www.howbuy.com/trust/company.htm</span></td>-->
<#--<td align="center">&nbsp;</td>-->
<#--<td>&nbsp;</td>-->
<#--</tr>-->
<#--<tr>-->
<#--<td align="center">3</td>-->
<#--<td>http://simu.howbuy.com/</td>-->
<#--<td align="center">&nbsp;</td>-->
<#--<td>&nbsp;</td>-->
<#--</tr>-->
<#--<tr>-->
<#--<td rowspan="2" align="center" bgcolor="#eeeeee">13300138000</td>-->
<#--<td height="41" align="center" bgcolor="#eeeeee">1</td>-->
<#--<td bgcolor="#eeeeee">http://www.howbuy.com/trust/company.htm</td>-->
<#--<td align="center" bgcolor="#eeeeee">1</td>-->
<#--<td bgcolor="#eeeeee">http://www.howbuy.com/trust/TG0000132.htm</td>-->
<#--</tr>-->
<#--<tr>-->
<#--<td align="center" bgcolor="#eeeeee">2</td>-->
<#--<td bgcolor="#eeeeee"><span class="cRed">http://www.howbuy.com/trust/TG0000132.htm</span></td>-->
<#--<td align="center" bgcolor="#eeeeee">&nbsp;</td>-->
<#--<td bgcolor="#eeeeee">&nbsp;</td>-->
<#--</tr>-->
<#--<tr>-->
<#--<td rowspan="14" align="center">2013-02-21</td>-->
<#--<td rowspan="7" align="center">13800138000</td>-->
<#--<td align="center">1</td>-->
<#--<td>http://www.howbuy.com/trust/TG0000132.htm</td>-->
<#--<td align="center">1</td>-->
<#--<td>http://www.howbuy.com/trust/TG0000133.htm</td>-->
<#--</tr>-->
<#--<tr>-->
<#--<td align="center">2</td>-->
<#--<td>http://www.howbuy.com/</td>-->
<#--<td align="center">&nbsp;</td>-->
<#--<td>&nbsp;</td>-->
<#--</tr>-->
<#--<tr>-->
<#--<td align="center">3</td>-->
<#--<td>http://www.howbuy.com/trust/list.htm</td>-->
<#--<td align="center">&nbsp;</td>-->
<#--<td>&nbsp;</td>-->
<#--</tr>-->
<#--<tr>-->
<#--<td align="center">4</td>-->
<#--<td>http://www.howbuy.com/trust/company.htm</td>-->
<#--<td align="center">&nbsp;</td>-->
<#--<td>&nbsp;</td>-->
<#--</tr>-->
<#--<tr>-->
<#--<td align="center">5</td>-->
<#--<td>http://simu.howbuy.com/</td>-->
<#--<td align="center">&nbsp;</td>-->
<#--<td>&nbsp;</td>-->
<#--</tr>-->
<#--<tr>-->
<#--<td align="center">6</td>-->
<#--<td><span class="cRed">http://www.howbuy.com/trust/TG0000133.htm</span></td>-->
<#--<td align="center">&nbsp;</td>-->
<#--<td>&nbsp;</td>-->
<#--</tr>-->
<#--<tr>-->
<#--<td align="center">7</td>-->
<#--<td>&nbsp;</td>-->
<#--<td align="center">&nbsp;</td>-->
<#--<td>&nbsp;</td>-->
<#--</tr>-->
<#--<tr>-->
<#--<td rowspan="2" align="center" bgcolor="#eeeeee">13300138000</td>-->
<#--<td height="41" align="center" bgcolor="#eeeeee">1</td>-->
<#--<td bgcolor="#eeeeee">http://www.howbuy.com/trust/company.htm</td>-->
<#--<td align="center" bgcolor="#eeeeee">1</td>-->
<#--<td bgcolor="#eeeeee">http://www.howbuy.com/trust/TG0000132.htm</td>-->
<#--</tr>-->
<#--<tr>-->
<#--<td align="center" bgcolor="#eeeeee">2</td>-->
<#--<td bgcolor="#eeeeee"><span class="cRed">http://www.howbuy.com/trust/TG0000132.htm</span></td>-->
<#--<td align="center" bgcolor="#eeeeee">&nbsp;</td>-->
<#--<td bgcolor="#eeeeee">&nbsp;</td>-->
<#--</tr>-->
<#--<tr>-->
<#--<td rowspan="3" align="center">13800138011</td>-->
<#--<td align="center">1</td>-->
<#--<td>http://www.howbuy.com/trust/list.htm</td>-->
<#--<td align="center">1</td>-->
<#--<td>http://www.howbuy.com/trust/company.htm</td>-->
<#--</tr>-->
<#--<tr>-->
<#--<td align="center">2</td>-->
<#--<td><span class="cRed">http://www.howbuy.com/trust/company.htm</span></td>-->
<#--<td align="center">&nbsp;</td>-->
<#--<td>&nbsp;</td>-->
<#--</tr>-->
<#--<tr>-->
<#--<td align="center">3</td>-->
<#--<td>http://simu.howbuy.com/</td>-->
<#--<td align="center">&nbsp;</td>-->
<#--<td>&nbsp;</td>-->
<#--</tr>-->
<#--<tr>-->
<#--<td rowspan="2" align="center" bgcolor="#eeeeee">13300138000</td>-->
<#--<td height="41" align="center" bgcolor="#eeeeee">1</td>-->
<#--<td bgcolor="#eeeeee">http://www.howbuy.com/trust/company.htm</td>-->
<#--<td align="center" bgcolor="#eeeeee">1</td>-->
<#--<td bgcolor="#eeeeee">http://www.howbuy.com/trust/TG0000132.htm</td>-->
<#--</tr>-->
<#--<tr>-->
<#--<td align="center" bgcolor="#eeeeee">2</td>-->
<#--<td bgcolor="#eeeeee"><span class="cRed">http://www.howbuy.com/trust/TG0000132.htm</span></td>-->
<#--<td align="center" bgcolor="#eeeeee">&nbsp;</td>-->
<#--<td bgcolor="#eeeeee">&nbsp;</td>-->
<#--</tr>-->
<#--<tr>-->
<#--<td align="center">&nbsp;</td>-->
<#--<td align="center">&nbsp;</td>-->
<#--<td align="center">&nbsp;</td>-->
<#--<td>&nbsp;</td>-->
<#--<td align="center">&nbsp;</td>-->
<#--<td>&nbsp;</td>-->
<#--</tr>-->
</table>
<p>&nbsp;</p>

</body>
<script type="text/javascript"
        src="${uri.js_host}/min/b=js/jquery&f=jquery1.3.2.js,ui/ui.core.js,ui/ui.datepicker.js,ui/ui.dialog.js,ui/ui.draggable.js"></script>
<script type="text/javascript">
    $(".date").datepicker({
        changeMonth: true,
        changeYear: true
    });
    $(document).ready(function () {
    });
</script>
</html>
