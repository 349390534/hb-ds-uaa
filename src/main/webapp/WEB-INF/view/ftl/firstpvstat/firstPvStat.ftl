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
<p style="color:#C00">用户首次访问页面统计：</p>

<form action="first.htm">
    <p>按日期查询：
        <input name="statDate" type="text" id="statDate" class="date" value="${statDate!}"/>
        <select title="首次访问页为" name="subChannel">
        <#list subChannels as channel>
            <option title="${channel!}" value="${channel!}"
                    <#if channel==subChannel>selected</#if> >${channel!}</option>
        </#list>
        </select>
        <input type="submit" name="button" id="button" value="查询"/>
    </p>
</form>
<table width="1280" border="0" cellspacing="0" cellpadding="0">
    <tr align="center" bgcolor="#FFFF00">
        <td width="107" align="center" bgcolor="#FFFF00"><strong>序号</strong></td>
        <td width="107" align="center" bgcolor="#FFFF00"><strong>总客户数</strong></td>
        <td width="156" align="center" bgcolor="#FFFF00"><strong>来源页面</strong></td>
        <td width="156" align="center" bgcolor="#FFFF00"><strong>来源数</strong></td>
        <td width="69" align="center" bgcolor="#FFFF00"><strong>去向页面</strong></td>
        <td width="69" align="center" bgcolor="#FFFF00"><strong>去向数</strong></td>
        <td width="254" align="center" bgcolor="#FFFF00"><strong>预约页面</strong></td>
        <td width="254" align="center" bgcolor="#FFFF00"><strong>预约数</strong></td>
    </tr>

<#list firstPvStatForms as form>
    <tr>
        <td>${form_index+1!}</td>
        <td>${subChannelPv!}</td>
        <td>${form.srcUrl!}</td>
        <td>${form.srcPv!}</td>
        <td>${form.toUrl!}</td>
        <td>${form.toPv!}</td>
        <td>${form.bookUrl!}</td>
        <td>${form.bookPv!}</td>
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
