<#if (page?has_content && page.total>0 && page.page<=10)>
<#assign purl = page.url?replace("page=[0-9]+", "page=PAGE_NUMBER","r")>
<#if (purl?index_of("page=PAGE_NUMBER")<0)>
<#assign purl = page.url?replace(".{1}page=", "","r")>
<#if purl?index_of("?") == -1>
<#assign purl = purl+"?page=PAGE_NUMBER">
<#else>
<#assign purl = purl+"&page=PAGE_NUMBER">
</#if>
</#if>

<#function getPageUrl(pageNumber)>
	<#return purl?replace("PAGE_NUMBER",pageNumber?string)>
</#function>

<#assign a = ((page.page-1)/page.viewPage)?int*page.viewPage+1>
<#assign a2 = (a+page.viewPage-1)?int>
<#assign a3 = ((page.total+page.perPage-1)/page.perPage)?int>
<#if (a3>10)>
<#assign a3=10>
</#if>
<#if (a2>a3)>
<#assign a2=a3>
</#if>

<div class="pagelist"><#if page.page != 1><a href="${getPageUrl(1)}">第一页</a><a href="${getPageUrl(page.page-1)}">前一页</a><#else></#if><#list a..a2 as i><#if i !=page.page><a href="${getPageUrl(i)}"><b>${i}</b></a><#else><a href="javascript:void(0);" class="red"><b>${i}</b></a></#if></#list><#if page.page != a3 && page.page != 10><a href="${getPageUrl(page.page+1)}">后一页</a><a href="${getPageUrl(a3)}">最后一页</a></#if>页次：${page.page}
</div>
</#if>