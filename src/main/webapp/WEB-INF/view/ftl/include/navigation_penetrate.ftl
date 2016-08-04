<#import "/uri.ftl" as uri />
<#import "/util.ftl" as util />
<p class="date">月份：${lastDate!}</p>
<div class="n_detail_bg clearfix">
	<div class="zh_table fl">
		<table>
			<tr>
				<td>公转私人数</td>
				<td>月环比</td>
			</tr>
			<tr>
				<td><span class="cBold ft22">${util.formatNum(dataTop.cnt_f!0)}</td>
				<td><span class="cBold  <#if CNT_F_R?? && CNT_F_R gt 0>cRed<#else>cGreen</#if> ft18">${util.formatNum(CNT_F_R!,',##0.00%')}</td>
			</tr>
			<tr>
				<td>货转股人数</td>
				<td>月环比</td>
			</tr>
			<tr>
				<td><span class="cBold ft22">${util.formatNum(dataTop.cnt_c_e!0)}</span></td>
				<td><span class="cBold  <#if CNT_C_E_R?? && CNT_C_E_R gt 0>cRed<#else>cGreen</#if> ft18">${util.formatNum(CNT_C_E_R!0,',##0.00%')}</span></td>
			</tr>
		</table>
	</div>
	<div class="bt_table1 fr" id="ctfx6r" style="height:200px;width:170px;">3</div>
</div>
<a href="javascript:void(0);" class="btn-style-b btn" url="${uri.context_path}/auth/pta/index.htm" permission_id="0004" pageid="000421">查看穿透数据明细</a>
<script type="text/javascript">
var _ctfx6rJson = null;
<#if ctfx6rJson??>
	_ctfx6rJson = ${ctfx6rJson};
</#if> 
$(function(){
	drawCtfx6r(_ctfx6rJson);
	bindClick("a[pageid='000421']");
});
</script> 