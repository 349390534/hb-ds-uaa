<#import "/uri.ftl" as uri />
<#import "/common.ftl" as common />
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
        "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>预约用户历史访问记录分析报表</title>
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
<p style="color:#C00">预约用户历史访问记录：</p>

<form action="index.htm">
    <p>按日期查询：
        <input name="actionDate" type="text" id="actionDate" class="date" value="${actionDate!}"/>
        <input type="submit" name="button" id="button" value="查询"/>
    </p>
</form>
<table width="1280" border="0" cellspacing="0" cellpadding="0">
    <tr align="center" bgcolor="#FFFF00">
        <td width="107" align="center" bgcolor="#FFFF00"><strong>用户序号</strong></td>
        <td width="156" align="center" bgcolor="#FFFF00"><strong>用户标识</strong></td>
        <td width="154" align="center" bgcolor="#FFFF00"><strong>本次访问时间</strong></td>
        <td width="156" align="center" bgcolor="#FFFF00"><strong>上次访问时间</strong></td>
        <td width="156" align="center" bgcolor="#FFFF00"><strong>间隔时间(天)</strong></td>
        <td width="69" align="center" bgcolor="#FFFF00"><strong>本次访问轨迹</strong></td>
    </tr>

<#list bookerPvHistorys as form>
    <tr>
        <td>${form_index+1!}</td>
        <td><a target="_blank" href="bookerInfo.htm?cookie=${form.cookie!}">${form.cookie!}</a></td>
        <td>${actionDate!}</td>
        <td>${form.lastVisitDate!}</td>
        <td>${form.interval!}</td>
        <td><a target="_blank" href="statDateVisit.htm?cookie=${form.cookie!}&actionDate=${actionDate!}">详细</a></td>
    </tr>
</#list>

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
