<#import "/uri.ftl" as uri />
<#import "/util.ftl" as util />
<div class="tab_list">
<ul class="tab_menu clearfix" id="tab_menu">
	<li class="current">渠道明细</li>
	<li>趋势明细</li>
</ul>
<div class="tab_box">
	<div class="con" id="_channel_detail"><!-- 渠道明细 begin -->
		<div class="detail_filter_at">
			<dl>
				<dt style="text-align:left;width:40px;">指标：</dt>
				<dd>
					<ul>
						<li><label class="detail_a"><input type="checkbox" class="detail_b" name="" tag="" value="-1"/>全选</label></li>
						<li><label><input type="checkbox" name="_ch_q" tag="enter">进入次数</label></li>
						<li><label><input type="checkbox" name="_ch_q" tag="pv">总PV</label></li>
						<li><label><input type="checkbox" name="_ch_q" tag="uv">总UV</label></li>
						<li><label><input type="checkbox" name="_ch_q" tag="hongbaoIndexUv">活动首页UV</label></li>
						<li><label><input type="checkbox" name="_ch_q" tag="h5OpenAccIndexPageUv">H5开户首页UV</label></li>
						<li><label><input type="checkbox" name="_ch_q" tag="authPageUv">身份验证页UV</label></li>
						<li><label><input type="checkbox" name="_ch_q" tag="h5OpenAccResultPageUv">H5开户结果页UV</label></li>
						<li><label><input type="checkbox" name="_ch_q" tag="openaccNum">开户人数</label></li>
					</ul>
				</dd>
			</dl>
		</div>
		<div class="tools">
			<input type="submit" value="导出明细" class="btn-style-a">
		</div>
		<div class="det-datalist">
			<table class="det-d-qdmx" style="width:1090px!important;">
				<thead>
					<tr class="tit">
						<td width="120">渠道名称</td>
						<td width="100" tag="enter">进入次数</td>
						<td width="100" tag="pv">PV</td>
						<td width="100" tag="uv">UV</td>
						<td width="100" tag="hongbaoIndexUv">活动首页UV</td>
						<td width="100" tag="h5OpenAccIndexPageUv">H5开户首页UV</td>
						<td width="100" tag="authPageUv">身份验证页UV</td>
						<td width="100" tag="h5OpenAccResultPageUv">H5开户结果页UV</td>
						<td width="100" tag="openaccNum">开户人数</td>
					</tr>
				</thead>
				<#if collData??>
				<tr>
					<td>全站</td>
					<td tag="enter">${util.formatNum(collData.enter!0)}</td>
					<td tag="pv">${util.formatNum(collData.pv!0)}</td>
					<td tag="uv">${util.formatNum(collData.uv!0)}</td>
					<td tag="hongbaoIndexUv">${util.formatNum(collData.hongbaoIndexUv!0)}</td>
					<td tag="h5OpenAccIndexPageUv">${util.formatNum(collData.h5OpenAccIndexPageUv!0)}</td>
					<td tag="authPageUv">${util.formatNum(collData.authPageUv!0)}</td>
					<td tag="h5OpenAccResultPageUv">${util.formatNum(collData.h5OpenAccResultPageUv!0)}</td>
					<td tag="openaccNum">${util.formatNum(collData.openaccNum!0)}</td>
				</tr>
				</#if>
				<#if channelList?? && channelList?size gt 0>
				<#list channelList as ch>
				<tr>
					<td>${ch.channelName!}</td>
					<td tag="enter">${util.formatNum(ch.enter!0)}</td>
					<td tag="pv">${util.formatNum(ch.pv!0)}</td>
					<td tag="uv">${util.formatNum(ch.uv!0)}</td>
					<td tag="hongbaoIndexUv">${util.formatNum(ch.hongbaoIndexUv!0)}</td>
					<td tag="h5OpenAccIndexPageUv">${util.formatNum(ch.h5OpenAccIndexPageUv!0)}</td>
					<td tag="authPageUv">${util.formatNum(ch.authPageUv!0)}</td>
					<td tag="h5OpenAccResultPageUv">${util.formatNum(ch.h5OpenAccResultPageUv!0)}</td>
					<td tag="openaccNum">${util.formatNum(ch.openaccNum!0)}</td>
				</tr>
				</#list>
				</#if>
			</table>
		</div>
	</div><!-- 渠道明细 end -->
	
	<!-- 趋势明细 begin -->
	<div class="con hide" id="_trend_detail">
		<div class="detail_filter_at">
			<dl>
				<dt style="text-align:left;width:40px;">指标：</dt>
				<dd>
					<ul>
						<li><label class="detail_a"><input type="checkbox" class="detail_b" name="" tag="" value="-1"/>全选</label></li>
						<li><label><input type="checkbox" name="_ch_q" tag="enter">进入次数</label></li>
						<li><label><input type="checkbox" name="_ch_q" tag="pv">总PV</label></li>
						<li><label><input type="checkbox" name="_ch_q" tag="uv">总UV</label></li>
						<li><label><input type="checkbox" name="_ch_q" tag="hongbaoIndexUv">活动首页UV</label></li>
						<li><label><input type="checkbox" name="_ch_q" tag="h5OpenAccIndexPageUv">H5开户首页UV</label></li>
						<li><label><input type="checkbox" name="_ch_q" tag="authPageUv">身份验证页UV</label></li>
						<li><label><input type="checkbox" name="_ch_q" tag="h5OpenAccResultPageUv">H5开户结果页UV</label></li>
						<li><label><input type="checkbox" name="_ch_q" tag="openaccNum">开户人数</label></li>
					</ul>
				</dd>
			</dl>
		</div>
		<div class="tools">
			<input type="submit" value="导出明细" class="btn-style-a">
		</div>
		<div class="det-datalist">
			<table class="det-d-qdmx" style="width:1090px!important;">
				<thead>
					<tr class="tit">
						<td width="120">日期</td>
						<td width="100" tag="enter">进入次数</td>
						<td width="100" tag="pv">PV</td>
						<td width="100" tag="uv">UV</td>
						<td width="100" tag="hongbaoIndexUv">活动首页UV</td>
						<td width="100" tag="h5OpenAccIndexPageUv">H5开户首页UV</td>
						<td width="100" tag="authPageUv">身份验证页UV</td>
						<td width="100" tag="h5OpenAccResultPageUv">H5开户结果页UV</td>
						<td width="100" tag="openaccNum">开户人数</td>
					</tr>
				</thead>
				<#if collData??>
				<tr>
					<td>全站</td>
					<td tag="enter">${util.formatNum(collData.enter!0)}</td>
					<td tag="pv">${util.formatNum(collData.pv!0)}</td>
					<td tag="uv">${util.formatNum(collData.uv!0)}</td>
					<td tag="hongbaoIndexUv">${util.formatNum(collData.hongbaoIndexUv!0)}</td>
					<td tag="h5OpenAccIndexPageUv">${util.formatNum(collData.h5OpenAccIndexPageUv!0)}</td>
					<td tag="authPageUv">${util.formatNum(collData.authPageUv!0)}</td>
					<td tag="h5OpenAccResultPageUv">${util.formatNum(collData.h5OpenAccResultPageUv!0)}</td>
					<td tag="openaccNum">${util.formatNum(collData.openaccNum!0)}</td>
				</tr>
				</#if>
				<#if trendList?? && trendList?size gt 0>
				<#list trendList as td>
				<tr>
					<td <#if td.statdt??>style="width: 138px;"</#if> ><#if td.dt??>${td.dt}<#else><@util.stringToDatetime td.statdt!,'','' /> </#if></td>
					<td tag="enter">${util.formatNum(td.enter!0)}</td>
					<td tag="pv">${util.formatNum(td.pv!0)}</td>
					<td tag="uv">${util.formatNum(td.uv!0)}</td>
					<td tag="hongbaoIndexUv">${util.formatNum(td.hongbaoIndexUv!0)}</td>
					<td tag="h5OpenAccIndexPageUv">${util.formatNum(td.h5OpenAccIndexPageUv!0)}</td>
					<td tag="authPageUv">${util.formatNum(td.authPageUv!0)}</td>
					<td tag="h5OpenAccResultPageUv">${util.formatNum(td.h5OpenAccResultPageUv!0)}</td>
					<td tag="openaccNum">${util.formatNum(td.openaccNum!0)}</td>
				</tr>
				</#list>
				</#if>																										
			</table>
		</div>
	</div><!-- 趋势明细 end -->
</div>
</div>
<#-- TODO
<div class="page clearfix">
<p class="fl">共30页，每页显示
	<select name="" id=""><option value="">50</option></select>
</p>
<ul class="fr">
<li><a href="">上一页</a></li>
<li><span><a href="">1</span></a></li>
<li><a href="">下一页</a></li>
</ul>
</div>-->


<script type="text/javascript">

$(function(){
	//菜单切换
	eventTabMenu();
	//复选框事件
	defaultEventCheckBox();
	eventCheckBox();
})
</script>