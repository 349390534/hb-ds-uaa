<#ftl strip_whitespace=true>
<#setting url_escaping_charset='UTF-8'>
<#include "host.ftl" />

<#assign css_version ="2016021801" >
<#assign js_version ="2016042701" >

<#macro link href>
<#list href as item>
<#if (item?has_content && item?ends_with(".css"))>
	<link rel="stylesheet" type="text/css" href="${css_host + item}?version=${css_version}" />
</#if>
</#list>
</#macro>

<#macro script src>
<#list src as item>
<#if (item?has_content && item?ends_with(".js"))>
<#if item?starts_with("http") || item?starts_with("https")>
<script type="text/javascript" src="${item}?version=${js_version}"></script>
<#else>
<script type="text/javascript" src="${js_host + item}?version=${js_version}"></script>
</#if>
</#if>
</#list>
</#macro>


<#assign REST_FIELD = "REST">
<#assign navigate_sarr = "<img src=\"/images/sarr.gif\" />">

<#if springMacroRequestContext?exists>
	<#assign context_path = springMacroRequestContext.getContextPath()>
<#else>
	<#assign context_path = "">
</#if>

<#function getRequestUri>
	<#if springMacroRequestContext?exists >
		<#return springMacroRequestContext.requestUri>
	<#elseif requestURI?exists>
		<#return requestURI>
	<#else>
		<#return "">
	</#if>
</#function>

<#function getRequestUri2>
	<#if springMacroRequestContext?exists >
		<#return springMacroRequestContext.requestUri>
	<#elseif requestURI?exists>
		<#return requestURI>
	<#else>
		<#return "">
	</#if>
</#function>

<#function getRequestUrl>
	<#return smhost + getRequestUri()>
<#--
	<#return springMacroRequestContext.requestURL>
-->	
</#function>


<#function getRequestUrl1>
	<#return springMacroRequestContext.requestURL>
</#function>

<#function getRequestUri1>
	<#assign uri = getRequestUri()>
	<#assign idx = uri?last_index_of("/")>
	<#if (idx > 0) >
		<#assign uri = uri?substring(idx+1)>
	</#if>
	<#assign idx = uri?index_of("?")>
	<#if (idx > 0) >
		<#assign uri = uri?substring(0,idx)>
	</#if>
	<#return uri>
</#function>

<#function isCurrentUri uri>
	<#assign currentUri = getRequestUri()>
	<#return (currentUri=uri)>
</#function>

<#function restUri uri restId>
	<#return uri?replace(REST_FIELD,restId)>
</#function>


<#function getSubPath contextPath>
	<#assign currentUri = getRequestUri()?substring(context_path?length+1)>
	<#assign idx = currentUri?index_of("/")>
	<#if (idx < 0) >
		<#return "">
	<#else>
		<#return currentUri?substring(0,idx)>	
	</#if>
</#function>

<#function getCookie cookieName>
	<#assign cookies = request.getCookies()>
	<#list cookies as cookie>
		<#if cookie.name = cookieName>
			<#return cookie.value>
		</#if>
	</#list>
	<#return "">
</#function>

<#function getSubPathIndex contextPath currentUri>
	<#assign subpathIndex=6>
	<#return subpathIndex>
</#function>
