<table>
	<tr>
		<td width="18%" class="t tdlt">推广渠道</td>
		<td width="82%" class="tdlt" id="tgqdDetail"></td>
	</tr>
	<tr>
		<td class="t tdlt">HTAG</td>
		<td class="tdlt" id="routehtag"><#if (detail)??>${detail.htag}</#if></td>
	</tr>
	<tr>
		<td class="t tdlt">申请时间</td>
		<td class="tdlt"><#if (detail)??>${detail.createDate?date?string.short}</#if></td>
	</tr>
	<tr>
		<td class="t tdlt">申请人</td>
		<td class="tdlt"><#if (detail.account)??>${detail.account}</#if></td>
	</tr>
	<tr>
		<td class="t tdlt">备注</td>
		<td><#if (detail.remark)??>${detail.remark}</#if></td>
	</tr>
</table>
<script>
	$(function(){
	<#if detail??>
		var qd = $("#selectOne").val().split("$$")[0] +'-'+ $("#selectTwo").val().split("$$")[0]+ '-' + $("#selectThree").val().split("$$")[0];
		
		$("#tgqdDetail").text(qd);
	</#if>
	})
</script>