


	<#assign totalpv1=0  totaluv1=0 >
    <#assign totalopen=0 totaltrade=0>
    <#assign totalnewhow=0 totalnewehow=0>
	<#if !pageType??>
	<table cellpadding="0" cellspacing="0" width="100%" id="tip">
	<tr><td style="text-align:center;"><strong>亲，请输入查询条件</strong></td></tr>
	</table>
	<#else>
	<script type="text/javascript">
	$("#tip").hide();
	</script>
	<span style="float: right;margin: 0px 0px 10px 0px">记录总数：<#if (retList??)>${retList?size}<#else>0</#if></span>
	<#if pageType == '1'>
	<#--前导页-->
	<table cellpadding="0" cellspacing="0" width="100%">
        <thead>
            <tr class="tit">
            	<td width="3%"></td>
                <td width="7%"><strong>日期</strong></td>
                <td width="40%"  align="center"><strong>url</strong></td>
                <td width="10%"><div><strong>url(PV/UV)</strong></div>
                                <div id="totalshow1">total:</div></td>
                <td width="10%"><strong>所属频道</strong></td>
                <td width="10%"><strong>所属频道(PV/UV)</strong></td>
                <#--td width="10%"><strong>ehowbuy(uv)</strong></td-->
                <td width="10%"><div><strong>开户数</strong></div>
                				<div id="totalopenshow"></div></td>
                <td width="10%"><div><strong>交易笔数</strong></div>
      		        			<div id="totaltradeshow"></div></td>
            </tr>
        </thead>
        <tbody>
        	
            <#if (retList??) && (retList?size gt 0) >
            <#list retList as rs>
            <tr>

            	<td>${rs_index + 1}</td>

            	<td>${rs.reportDateTime?string('yyyy-MM-dd')}</td>

            	<td align="center"><a href="${rs.url}"><#if (rs.url?length gt 60)>${rs.url?substring(0,60)+'...'}<#else>${rs.url}</#if></a></td>
            	<td>${rs.urlpv!}/${rs.urluv!}</td>
                <td class="tdl">${rs.channelID + '$' + rs.subChannelID}</td>
                <td>${rs.howbuypv!}/${rs.howbuyuv!}</td>
                <td class="tdr">${rs.acctopenNum!}</td>
                <td class="tdr">${rs.tradeNum!}</td>
            </tr>
            <#assign totalpv1 = totalpv1+rs.urlpv totaluv1 = totaluv1+rs.urluv>
            <#assign totalopen = totalopen+rs.acctopenNum totaltrade = totaltrade+rs.tradeNum>
            </#list>
            <#else>
            <tr><td colspan="9" style="text-align:center;">无数据</td></tr>
            </#if>
        </tbody>
    </table>
    <#else>
        <#--首次登陆页-->
    <table cellpadding="0" cellspacing="0" width="100%">
        <thead>
            <tr class="tit">
            	<td width="3%"></td>
                <td width="7%"><strong>日期</strong></td>
                <td width="30%" align="center"><strong>url</strong></td>
                <td width="10%">
                	<div><strong>url(PV/UV)</strong></div>
                    <div id="totalshow1">total:</div>
                </td>
                <td width="10%"><strong>所属频道</strong></td>                
                <td width="10%"><strong>所属频道(PV/UV)</strong></td>
                <td width="10%"><div><strong>howbuy(新增uv)</div></strong>
                				<div id="totalnewhowshow"></div></td>
                <td width="10%"><div><strong>ehowbuy(新增uv)</strong></div>
                				<div id="totalnewehowshow"></div></td>
                <td width="5%"><div><strong>开户数</strong><div>
                				<div id="totalopenshow"></div></td>
                <td width="5%"><div><strong>交易笔数</strong></div>
      		        			<div id="totaltradeshow"></div></td>
            </tr>
        </thead>
        <tbody>
        	 <#if (retList??) && (retList?size gt 0) >
            <#list retList as rs>
            <tr>
            	<td>${rs_index+1}</td>
                <td>${rs.reportDateTime?string('yyyy-MM-dd')}</td>
            	<td align="center"><a href="${rs.url}"><#if (rs.url?length gt 60)>${rs.url?substring(0,60)+'...'}<#else>${rs.url}</#if></a></td>
                <td>${rs.urlpv!}/${rs.urluv!}</td>
                <td class="tdl">${rs.channelID + '$' + rs.subChannelID}</td>
                <td>${rs.howbuypv!}/${rs.howbuyuv!}</td>
                <td class="tdr">${rs.howbuyAddedUV!}</td>
                <td class="tdr">${rs.ehowbuyAddeduv!}</td>
                <td class="tdr">${rs.acctopenNum!}</td>
                <td class="tdr">${rs.tradeNum!}</td>
            </tr>
             <#assign totalpv1 = totalpv1+rs.urlpv totaluv1 = totaluv1+rs.urluv>
             <#assign totalnewhow = totalnewhow+rs.howbuyAddedUV totalnewehow = totalnewehow+rs.ehowbuyAddeduv>
             <#assign totalopen = totalopen+rs.acctopenNum totaltrade = totaltrade+rs.tradeNum>
             
            </#list>
            <#else>
            <tr><td colspan="9" style="text-align:center;">无数据</td></tr>
            </#if>
        </tbody>
    </table>
    </#if>
    </#if>
	<script type="text/javascript">
		$(function(){
		
		$("#totalshow1").html("Total:${totalpv1}/${totaluv1}");
		$("#totalopenshow").html("Total:${totalopen}");
		$("#totaltradeshow").html("Total:${totaltrade}");
		$("#totalnewhowshow").html("Total:${totalnewhow}");
		$("#totalnewehowshow").html("Total:${totalnewehow}");
		
	});
	</script>