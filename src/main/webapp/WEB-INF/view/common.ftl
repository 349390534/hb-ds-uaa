<#ftl strip_whitespace=true>
<#include "uri.ftl">
<#--
 * common.ftl
 * @author Jeff
 * @since 1.0
 -->
 <#function getPageStartRs page>
	<#if page?has_content && page!="">
		<#return (page.page - 1) * page.perPage + 1>
	<#else>
        <#return 1>	
    </#if>
</#function> 
      
<#function getOperaorName operatorMap operatorId>
    <#if operatorId==-1>
        <#return "系统">
	</#if>
	<#list operatorMap?values as operator>
		<#if operator.userId==operatorId>
			<#return operator.userName?default("")>
		</#if>
	</#list>
	<#return "">	
	
</#function>

<#--
 * 静态区块内容
-->
<#function getPagePiece pagePieceList name>
	<#list pagePieceList as pagePiece>
		<#if pagePiece.pieceName==name>
			<#return pagePiece.content?default(pagePiece.pieceName)>
		</#if>
	</#list>
	<#return "">
</#function>


<#macro navigationContent content type=1 maxLength=200>
	<#switch type>
		<#case 1>
			${content.title}
			<#break>
		<#case 2>
			<#if content.summary??>
				${content.summary}
			<#elseif (content.content?length>maxLength)>	
				${content.content?substring(0,maxLength)}
			<#else>
				${content.content}
			</#if>
			<#break>
		<#case 3>
			<#if (content.title?length>15)>	
				${content.title?substring(0,15)}
			<#else>
				${content.title}
			</#if>
			<#break>
		<#case 4>
			<#if (content.title?length>19)>	
				${content.title?substring(0,19)}
			<#else>
				${content.title}
			</#if>
			<#break>	
	</#switch>			
</#macro>

<#--
  * 格式化数字
  -->
<#function formatNumber numVal format="0.00" defaultVal="--">
	<#if numVal?has_content>
		<#if numVal?is_number>
			<#return numVal?string(format)>
		<#else>
			<#return defaultVal>
		</#if>	
	<#else>
		<#return defaultVal>
	</#if>	
</#function>

<#function percent numVal format="0.00">
	<#if numVal?has_content>
		<#if numVal?is_number>
			<#assign val = formatNumber(numVal*100,format) + "%">
			<#return val>
		<#else>
			<#return "--">
		</#if>	
	<#else>
		<#return "--">
	</#if>
</#function>

<#function percent1 numVal format="0.00">
	<#if numVal?has_content>
		<#if numVal?is_number>
			<#return percent(numVal/100,format)>
		<#else>
			<#return "--">
		</#if>	
	<#else>
		<#return "--">
	</#if>
</#function>

<#function highLightKeyword words key className="red">
	<#return words?replace(key, "<span class=\""+className+"\">"+key+"</span>")>
</#function>

<#--
 * 关键字加链接正则表达式
 * @key 关键字
 -->
<#function keyLinkRegex key>
	<#return "(?<!<[aA] [^>]{0,100}>[^>] {0,1})"+key+"(?![^<]{0,100}</[aA]>)">
</#function>

<#function fundLink funds content>
	<#assign sss = content>
	<#list funds as fund>
		<#assign sss = singleFundLink(fund, sss)>
	</#list>
	<#return sss>
</#function>

<#function formatDate sDate fmt="yyyyMMdd">
  <#if sDate?has_content>
	<#if (sDate=="" || sDate=="19000101" || sDate=="29991231")>
		<#return "">
	</#if>
	<#if (sDate?length!=8)>
		<#if sDate?length==6>
			<#assign sda = sDate?date("yyyyMM")!>
			<#if sda?has_content>
				<#return sda?substring(0,7)>
			</#if>
		</#if>
		<#return sDate>
	</#if>
	<#assign sd = sDate?date(fmt)!>
	<#if sd?has_content>
		<#return sd>
	</#if>
  </#if>		
  <#return "--">
</#function>

<#macro errorDesc error="">
<#if error?has_content>
	<div id="error_info" class="errorinfo">
			${error}
	</div>	
</#if>



</#macro>

<#function confoundPhone phone="">
    <#if phone?length==11>
        <#assign newPhone = phone?substring(0,3)+"****"+phone?substring(7)>
        <#return newPhone>
    </#if>
    <#return "">
</#function>

<#function formatString s="">
    <#if s??&&s=="null">
        <#return "">
    </#if>
    <#return s>
</#function>

<#assign channels = {"fund$index":"fund$index",
					"fund$company_detail":"fund$company_detail",
					"fund$board":"fund$board",
					"fund$new_issue":"fund$new_issue",
					"fund$aip":"fund$aip",
					"fund$company_list":"fund$company_list",
					"fund$manager_list":"fund$manager_list",
					"fund$ranking":"fund$ranking",
					"fund$detail":"fund$detail",
					"fund$manager_detail":"fund$manager_detail",
					"fundtool$filter":"fundtool$filter",
					"fundtool$licai":"fundtool$licai",
					"fundtool$zixuan":"fundtool$zixuan",
					"licai$product":"licai$product",
					"licai$index":"licai$index",
					"licai$list":"licai$list",
					"licai$news":"licai$news",
					"login$login":"login$login",
					"news$detail":"news$detail",
					"opinion$detail":"opinion$detail",
					"register$register":"register$register",
					"research$daily":"research$daily",
					"research$interview":"research$interview",
					"research$simu":"research$simu",
					"research$guquan":"research$guquan",
					"research$trust":"research$trust",
					"research$licai":"research$licai",
					"research$duichong":"research$duichong",
					"simu$manager":"simu$manager",
					"simu$detail":"simu$detail",
					"simu$shop":"simu$shop",
					"simu$mlboardold":"simu$mlboardold",
					"simu$mlboard":"simu$mlboard",
					"simu$company_list":"simu$company_list",
					"simu$manager_detail_sub":"simu$manager_detail_sub",
					"simu$mboard":"simu$mboard",
					"simu$simu_news":"simu$simu_news",
					"simu$index":"simu$index",
					"simu$zcg":"simu$zcg",
					"simu$manager_detail":"simu$manager_detail",
					"simu$board":"simu$board",
					"simu$fund_list":"simu$fund_list",
					"subject$subject":"subject$subject",
					"trust$company":"trust$company",
					"trust$news":"trust$news",
					"trust$list":"trust$list",
					"trust$company_list":"trust$company_list",
					"trust$index":"trust$index",
					"trust$basics":"trust$basics",
					"trust$product":"trust$product",
					"video$channel":"video$channel",
					"video$index":"video$index",
					"video$search":"video$search",
					"website$index":"website$index",
					"zhlc$zhlc":"zhlc$zhlc"}/>
<#assign channelkeys = channels?keys>