<#if (page?has_content && page.total>0)>
<#assign allPage = ((page.total+page.perPage-1)/page.perPage)?int>
<script type="text/javascript">
var frm;
function goPage(i,frm)
{
	if (!frm)
		frm = document.getElementById("allPage").form;
	frm.page.value = i;
	if(isNaN(frm.page.value))
	{
		//alert("page Value Must Be Numbers !");
		alert("跳转页面数必须是数字 !");
		return false;
	}
	if (parseInt(frm.page.value)<=0 || parseInt(frm.page.value)>parseInt(frm.allPage.value))
	{
		//alert("Page Value is Out of Range !");
		alert("跳转页面数超出范围!");
		return false;
	}
	if (frm.onsubmit){
		frm.onsubmit();
	}
	frm.submit();
	return false;
}

function changePerPage(perPage){
	var frm = document.getElementById("allPage").form;
	frm.perPage.value = perPage;
	goPage(1,frm);
}

function gotoTargetPage(frm){
	if(isNaN(frm.targetPage.value))
	{
		//alert("page Value Must Be Numbers !");
		return false;
	}
	goPage(frm.targetPage.value,frm);
}
</script>

<#assign perPages=[20,50,100]>
<div class="pages">
<input type="hidden" name="page" id="page" value="1" />
<input type="hidden" name="perPage" id="perPage" value="${page.perPage}" />
<input type="hidden" name="allPage" id="allPage" value="${allPage}" />
    	每页显示&nbsp;<span class="ts">
    		<#list perPages as perpage><a href="javascript:changePerPage('${perpage}');" <#if perpage==page.perPage>class="on"</#if> target="_self">${perpage}</a>&nbsp;</#list>条</span>
    		第${page.page}页/共${allPage}页&nbsp;<a target="_self" <#if page.page != 1> href="javascript:goPage(1);"</#if>>首页</a>&nbsp;<a target="_self" <#if (page.page>1)>  href="javascript:goPage(${page.page-1})"</#if>>上一页</a>&nbsp;<a target="_self" <#if page.page != allPage>  href="javascript:goPage(${page.page+1})"</#if>>下一页</a>&nbsp;<a  target="_self"  <#if page.page != allPage>  href="javascript:goPage(${allPage})"</#if>>末页</a>&nbsp;转到<input class="it01" type="text" name="targetPage" />页&nbsp;<input type="button" value="查询" class="it02" onclick="gotoTargetPage(this.form);" />
</div>
</#if>