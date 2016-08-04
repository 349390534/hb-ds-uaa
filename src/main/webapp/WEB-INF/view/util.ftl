<#import "/uri.ftl" as uri />
<#-- 将string日期格式-->
<#function formatStrDate dateVal='' sformat="yyyyMMdd" tformat="yyyy-MM-dd">
	<#if dateVal?has_content>

			<#return dateVal?date(sformat)?string(tformat)>

	<#else>
		<#return ''>
	</#if>
</#function>
<#-- 将string14位日期格式-->
<#function formatStrDateTime dateVal='' sformat="yyyyMMddHHmmss" tformat="yyyy-MM-dd HH:mm:ss">
	<#if dateVal?has_content>
			<#return dateVal?date(sformat)?string(tformat)>
	<#else>
		<#return ''>
	</#if>
</#function>

<#--字符串转日期时间-->
<#macro stringToDatetime str fomart1 fomart2="">
	<#if str?has_content>
		<#if fomart2?has_content>
			${str?datetime(fomart1)?string(fomart2)}
		<#else>
			${str?datetime(fomart1)}
		</#if>
	</#if>
</#macro>
<#--字符串转日期-->
<#macro stringToDate str fomart1 fomart2="">
	<#if str?has_content>
		<#if fomart2?has_content>
			${str?date(fomart1)?string(fomart2)}
		<#else>
			${str?date(fomart1)}
		</#if>
	</#if>
</#macro>

<#macro dicMapStatuts dicMap statuts>
	<#list dicMap?keys as value>
		<#if value==statuts?string>
			${dicMap[value]?html}
		</#if>
	</#list>
</#macro>

<#macro formSingleSelect inputName selectValue options attributes="">
<#assign stringStatusValue=selectValue?string>
    <select id=${inputName} name=${inputName} ${attributes}>
        <#list options?keys as value>
        <option value="${value?html}"<@checkSelected value/>>${options[value]?html}</option>
        </#list>
    </select>
</#macro>

<#macro formSingleSelectWithBlank inputName options selectValue="" attributes="" first="">
<#assign stringStatusValue=selectValue?string>
    <select id=${inputName} name=${inputName} ${attributes}>
        <option value="">${first}</option>
        <#list options?keys as value>
        <option value="${value?html}"<@checkSelected value/>>${options[value]?html}</option>
        </#list>
    </select>
</#macro>

<#macro checkSelected value>
    <#if stringStatusValue?is_number && stringStatusValue == value?number>selected="selected"</#if>
    <#if stringStatusValue?is_string && stringStatusValue == value>selected="selected"</#if>
</#macro>


<#macro formCheckboxes path options separator="" attributes="">
    <@spring.bind path/>
    <#list options?keys as value>
    <#assign id="${spring.status.expression}${value_index}">
    <#assign isSelected = contains(spring.status.value?default(""), value_index)>
    <input type="checkbox" id="${id}" name="${spring.status.expression}" value="${value?html}"
        <#if isSelected>checked="checked"</#if> ${attributes}
    <@spring.closeTag/>
    <label for="${id}">${options[value]?html}</label>${separator}
    <#if value_index%2 ==1 >
    <br<@spring.closeTag/>
    </#if>
    </#list>
</#macro>

<#macro formCheckboxesNoclose path options separator="" attributes="">
    <@spring.bind path/>
    <#list options?keys as value>
    <#assign id="${spring.status.expression}${value_index}">
    <#assign isSelected = contains(spring.status.value?default(""), value_index)>
    <input type="checkbox" id="${id}" name="${spring.status.expression}" value="${value?html}"
        <#if isSelected>checked="checked"</#if> ${attributes}
    <@spring.closeTag/>
    <label for="${id}">${options[value]?html}</label>${separator}
    </#list>
</#macro>

<#macro parsesCheckboxes path options separator="&nbsp;">
    <@spring.bind path/>
    <#list options?keys as value>
    <#assign id="${spring.status.expression}${value_index}">
    <#assign isSelected = contains(spring.status.value?default(""), value_index)>
        <#if isSelected>${value?html}${separator}</#if>
    </#list>
</#macro>

<#macro parsesCheckboxesNobind str options separator="&nbsp;">
    <#list options?keys as value>
    <#assign isSelected = contains(str, value_index)>
        <#if isSelected>${options[value]?html}${separator}</#if>
    </#list>
</#macro>


<#macro formatData head data>
	<#if con.opennorms_num_format[head]?? && con.opennorms_num_format[head]==1>
		${util.formatNum(data)}
	<#elseif con.opennorms_num_format[head]?? && con.opennorms_num_format[head]==2>
		${util.formatNum(data,',##0.00')}
	<#elseif con.opennorms_num_format[head]?? && con.opennorms_num_format[head]==3>
		${util.formatNum(data,',##0.00%')}
	</#if>
</#macro>

<#function contains str pos>
    <#if str?length &lt; pos+1><#return false></#if>
    <#if str?substring(pos,pos+1) == "0"><#return false></#if>
    <#return true>
</#function>

<#function floatFormat str format=",##0">
	<#if str?has_content>
		<#return str?string(format)>
	<#else>
		<#return "">	
	</#if>
</#function>

<#function leftStrHide str len=0>
	<#if str?has_content>
		<#if str?length &gt; len>
			<#assign bb = str?length-len>	
			<#assign reStr = "">
			<#list 1..bb as i>
				<#assign reStr = reStr + "*"/>
			</#list>
			<#assign reStr = reStr + str?substring(bb)/>
			<#return reStr>
		<#else>
			<#return str>
		</#if>
	<#else>
		<#return "">
	</#if>
</#function>

<#--格式化数字 ,##0.00 默认无小数点--> 
<#function formatNum numVal format=",###" defaultVal="-">
	<#if numVal?has_content>
		<#if numVal?is_number>
			<#return (numVal)?string(format)>
		<#elseif numVal?is_string>
			<#return ((numVal)?number)?string(format)>
		<#else>
			<#return numVal>
		</#if>
	<#else>
		<#return defaultVal>
	</#if>
</#function>

<#assign loadImg="<img src='${uri.context_path}/images/load2.gif' style='margin-top: 15%;margin-left: 5%;margin-bottom: 40%;'>" />