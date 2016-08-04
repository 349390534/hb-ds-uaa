

<#assign totalaccount=0>
<#assign totaltrade=0>
<#if !pageType??>
	<table cellpadding="0" cellspacing="0" width="100%" id="tip">
	<tr><td style="text-align:center;"><strong>亲，请输入查询条件</strong></td></tr>
	</table>
	<#else>
	<script type="text/javascript">
	$("#tip").hide();
	</script>
	<span style="float: right;margin: 0px 0px 10px 0px">记录总数：<#if (retList??)>${retList?size}<#else>0</#if></span>
	<table cellpadding="0" cellspacing="0" width="100%">
        <thead>
            <tr class="tit">
                <td width="10%"><strong>日期</strong></td>
                <td width="10%"><strong>频道pv/uv</strong></td>
                <td width="10%"><strong>当日开户数</strong></td>
                <td width="10%"><strong>当日交易笔数</strong></td>
                <td width="10%"><strong>howbuy新增uv</strong></td>
                <td width="10%"><strong>ehowbuy新增uv</strong></td>
                <td width="10%"><strong>十日开户数</strong></td>
                <td width="10%"><strong>十日交易笔数</strong></td>
                <td width="10%"><strong>三十日开户数</strong></td>
                <td width="10%"><strong>三十日交易笔数</strong></td>
            </tr>
        </thead>
        <tbody>
        	<#if (retList??) && (retList?size gt 0) >
            <#list retList as rs>
            <tr>
                <td>${rs.reportDateTime?string('yyyy-MM-dd')}</td>
                <td>${rs.howbuypv}/${rs.howbuyuv}</td>
                <td>${rs.acctopenNum}</td>
                <td>${rs.tradeNum}</td>
                <td>${rs.howbuyAddedUV}</td>
                <td>${rs.ehowbuyAddeduv}</td>
                <td>${rs.openAcct10days!0}</td>
                <td>${rs.trade10days!0}</td>
                <td>${rs.openAcct30days!0}</td>
                <td>${rs.trade30days!0}</td>
            </tr>
            <#assign totalaccount=totalaccount+rs.acctopenNum >
            <#assign totaltrade=totaltrade+rs.tradeNum >
            </#list>
            <#else>
            <tr><td colspan="9" style="text-align:center;">无数据</td></tr>
            </#if>
        </tbody>
    </table>
</#if>
<script type="text/javascript">
$(function(){
		<#--$("#totalleadin").html("Total:${totalpv1}/${totaluv1}");
		$("#totalopenshow").html("Total:${totalopen}");
		$("#totaltradeshow").html("Total:${totaltrade}");-->
		<#if Intervals??>
		$("#totalopenshow").html("Total:${totalaccount}");
		$("#totaltradeshow").html("Total:${totaltrade}");
		</#if>
	});
</script>