
<#assign domain="a.howbuy.com">

<#assign context_path ="" >
<#if springMacroRequestContext?exists >
	<#assign context_path =springMacroRequestContext.getContextPath()>
</#if>


<#assign image_host=context_path + "/images">
<#assign js_host=context_path + "/js">
<#assign css_host=context_path + "/css">
