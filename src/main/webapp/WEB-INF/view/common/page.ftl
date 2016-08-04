<div style="text-align:right; margin-top: 7px; margin-right: 20px;">
<script language="javascript">
var frm;
function goPage(i,frm)
{
	if (!frm)
		frm = document.getElementById("allPage").form;
	frm.page.value = i;
	if(isNaN(frm.page.value))
	{
		alert("page Value Must Be Numbers !");
		return false;
	}
	if (parseInt(frm.page.value)<=0 || parseInt(frm.page.value)>parseInt(frm.allPage.value))
	{
		alert("Page Value is Out of Range !");
		return false;
	}
	frm.submit();
	return false;
}
</script>
	<input type="hidden" name="page" id="page" value="${page}">
	<input type="hidden" name="allPage" id="allPage" value="${allPage}">
	&nbsp;&nbsp;共 <font color=blue>${total}</font> 条记录
	&nbsp;${firstPageStart}第一页${firstPageEnd}
	&nbsp;${prevPageStart}前一页${prevPageEnd}
	&nbsp;${nextPageStart}后一页${nextPageEnd}
	&nbsp;${lastPageStart}最后一页${lastPageEnd}
	&nbsp;&nbsp;&nbsp;页次：${page}/${allPage}&nbsp;&nbsp; 跳转到第&nbsp;${jumpPage}&nbsp;页
</div>