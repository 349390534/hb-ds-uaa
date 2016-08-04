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
<p style="color:#C00">每日预约用户首访页面：</p>

<form action="firstVisit.htm">
    <p>按日期查询：
        <input name="actionDate" type="text" id="actionDate" class="date" value="${actionDate!}"/>
        <input type="submit" name="button" id="button" value="查询"/>
    </p>
</form>
<table width="1280" border="0" cellspacing="0" cellpadding="0">
    <tr align="center" bgcolor="#FFFF00">
        <td width="107" align="center" bgcolor="#FFFF00"><strong>用户序号</strong></td>
        <td width="156" align="center" bgcolor="#FFFF00"><strong>用户标识</strong></td>
        <td width="156" align="center" bgcolor="#FFFF00"><strong>身份信息</strong></td>
        <td width="69" align="center" bgcolor="#FFFF00"><strong>预约页面</strong></td>
        <td width="254" align="center" bgcolor="#FFFF00"><strong>预约时间</strong></td>
        <td width="254" align="center" bgcolor="#FFFF00"><strong>首次来源</strong></td>
        <td width="254" align="center" bgcolor="#FFFF00"><strong>首次来源时间</strong></td>
        <td width="254" align="center" bgcolor="#FFFF00"><strong>首次来源关键字</strong></td>
    </tr>

<#list bookerFirstPageForms as form>
    <tr title="${form.srcUrl!}">
        <td>${form_index+1!}</td>
        <td>${form.cookie!}</td>
        <td>${common.confoundPhone(form.phone)}</td>
        <td>${form.userUrl!}</td>
        <td>${form.bookTime!}</td>
        <td>${form.srcDomain!}</td>
        <td>${form.srcTime!}</td>
        <td>${form.srcKey!}</td>
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
