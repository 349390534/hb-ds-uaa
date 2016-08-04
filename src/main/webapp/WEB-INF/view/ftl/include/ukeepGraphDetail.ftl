<#import "/util.ftl" as util />
<div class="title">
	<h3 id="exp"></h3>
</div>
<div class="chu_app clearfix">
<div class="chu_app_left fl" id="ukeepYearChart"></div>
<div class="chu_app_right fr">
	<p>截止${util.formatStrDateTime(stat_dt!,'yyyyMM','yyyy年MM月')}</p>
	<#if wd?? && wd=="newtrade-y">
	<table>
		<tr>
			<td class="ht40 pt22">新增交易人数</td>
			<td class="ht40 pt22"><span class="cBold ft22">${util.formatNum(dnxzjy_rs_total!0)}</span>人</td>
		</tr>
		<tr>
			<td class="ht40 pt52">复购人数</td>
			<td class="ht40  pt52"><span class="cBold ft22">${util.formatNum(dnxzjy_fgrs!0)}</span>人</td>
		</tr>
		<tr>
			<td class="ht40">占比</td>
			<td class="ht40"><span class="cBold ft22">${util.formatNum(dnxzjy_fgrszb!0,',##0.00%')}</span></td>
		</tr>
		<tr>
			<td class="ht40 pt52">有存量人数</td>
			<td class="ht40 pt52"><span class="cBold ft22">${util.formatNum(clrs!0)}</span>人</td>
		</tr>			
		<tr>
			<td class="ht40">占比</td>
			<td class="ht40"><span class="cBold ft22">${util.formatNum(clrsRate!0,',##0.00%')}</span></td>
		</tr>	
	</table>
	</#if>
	<#if wd?? && wd=="newuser-y">
	<table>
	<tr>
		<td width="40%" class="pt20">新开户人数</td>
		<td width="60%" class="pt20"><span class="cBold ft22">${util.formatNum(dnkh_zrs!0)}</span>人</td>
	</tr>
	<tr>
		<td class="pt20">新增交易人数</td>
		<td class="pt20"><span class="cBold ft22">${util.formatNum(dnkhdnjy_rs_total!0)}</span>人</td>
	</tr>
	<tr>
		<td>占比</td>
		<td><span class="cBold ft22">${util.formatNum(dnkhdnjy_rs_total_zb!0,',##0.00%')}</span></td>
	</tr>
	<tr>
		<td class="pt20">复购人数</td>
		<td class="pt20"><span class="cBold ft22">${util.formatNum(fgrs!0)}</span>人</td>
	</tr>
	<tr>
		<td>占比</td>
		<td><span class="cBold ft22">${util.formatNum(fgrs_zb!0,',##0.00%')}</span></td>
	</tr>
	<tr>
		<td class="pt20">有存量人数</td>
		<td class="pt20"><span class="cBold ft22">${util.formatNum(clrs!0)}</span>人</td>
	</tr>
	<tr>
		<td>占比</td>
		<td><span class="cBold ft22">${util.formatNum(clrs_zb!0,',##0.00%')}</span></td>
	</tr>
	</table>
	</#if>
	
</div>
</div>
<div class="w1130 mt30 table-info">
	<ul class="user-mx-tab tab clearfix" id="tab">
		<li class="current">总体明细</li>
		<li>有存量明细</li>
		<li>无存量明细</li>
	</ul>
    <!-- 总体明细 begin -->
    <div class="user-mx-con" id="detail_all">
		<div class="detail_filter1">
			<div class="zhl4 clearfix">
				<p class="title">指标：</p>
				<div class="zhl_right clearfix" ultag="q">
					<div class="ch_left2">
						<label>
							<input type="checkbox" class="detail_b" style="margin-right: 5px;  position: relative;top: 2px;" value="-1"/>全部</label>
					</div>
					<ul class="clearfix" >
						<#if wd?? && wd=="newuser-y">
						<li>
							<label><input type="checkbox" name="dnkh" value="dnkh">开户人数</label>
						</li>
						</#if>
						<li class="w110">
							<label>
								<input type="checkbox" name="dnxzjy_rs" value="dnxzjy_rs">新增交易人数</label>
						</li>
						<li>
							<label>
								<input type="checkbox" name="dnxzjy_rs_fg" value="dnxzjy_rs_fg" pc />复购</label>
						</li>
						<li>
							<label>
								<input type="checkbox" name="dnxzjy_cl" value="dnxzjy_cl" pc />存量</label>
						</li>
						<li>
							<label>
								<input type="checkbox" name="dnxzjy_rs_1" value="dnxzjy_rs_1" pc />购买1次</label>
						</li>
						<li>
							<label>
								<input type="checkbox" name="dnxzjy_rs_2" value="dnxzjy_rs_2" pc />购买2次</label>
						</li>
						<li>
							<label>
								<input type="checkbox" name="dnxzjy_rs_3" value="dnxzjy_rs_3" pc />购买3次</label>
						</li>
						<li>
							<label>
								<input type="checkbox" name="dnxzjy_rs_4" value="dnxzjy_rs_4" pc />购买4次</label>
						</li>
						<li>
							<label>
								<input type="checkbox" name="dnxzjy_rs_5" value="dnxzjy_rs_5" pc />购买5次</label>
						</li>
						<li>
							<label>
								<input type="checkbox" name="dnxzjy_rs_gt5" value="dnxzjy_rs_gt5" pc />购买5次以上</label>
						</li>
					</ul>
				</div>
			</div>
			<div class="zhl4 clearfix" style="margin-top: 0;">
				<p class="title">维度：</p>
				<div class="zhl_right clearfix">
					<div class="ch_left2">
						<label>
							<input tag="qwd" type="checkbox" class="detail_b" style="margin-right: 5px;  position: relative;top: 2px;" name="dnxzjy_rs" value="dnxzjy_rs"/>人数</label>
					</div>
					<ul class="clearfix">
						<li>
							<label>
								<input  tag="qwd" type="checkbox"  name="dnxzjy_zb" value="dnxzjy_zb">占比</label>
						</li>
						<li>
							<label>
								<input  tag="qwd" type="checkbox" name="dnxzjy_amt" value="dnxzjy_amt">金额</label>
						</li>
					</ul>
				</div>
			</div>
		</div>
		<div class="zb_table" >
			<table id="table_detail_all">
				<tr tag="title">
					<td rowspan="2"></td>
					<#if wd?? && wd=="newuser-y">
					<td rowspan="2" tag_q="dnkh">开户人数</td>
					</#if>
					<td rowspan="2" tag_q="dnxzjy_rs">新增交易人数</td>
					<td colspan="3" tag_q="dnxzjy_rs_fg">复购</td>
					<td colspan="3" tag_q="dnxzjy_cl">存量</td>
					<td colspan="3" tag_q="dnxzjy_rs_1">购买1次</td>
					<td colspan="3" tag_q="dnxzjy_rs_2">购买2次</td>
					<td colspan="3" tag_q="dnxzjy_rs_3">购买3次</td>
					<td colspan="3" tag_q="dnxzjy_rs_4">购买4次</td>
					<td colspan="3" tag_q="dnxzjy_rs_5">购买5次</td>
					<td colspan="3" tag_q="dnxzjy_rs_gt5">购买5次以上</td>
				</tr>
				<tr tag="title_c">
					<td wd="dnxzjy_rs_fg" tag_q="dnxzjy_rs_fg_rs">人数</td>
					<td wd="dnxzjy_rs_fg" tag_q="dnxzjy_rs_fg_zb">占比</td>
					<td wd="dnxzjy_rs_fg" tag_q="dnxzjy_rs_fg_amt">金额</td>
					<td wd="dnxzjy_cl" tag_q="dnxzjy_cl_rs">人数</td>
					<td wd="dnxzjy_cl" tag_q="dnxzjy_cl_zb">占比</td>
					<td wd="dnxzjy_cl" tag_q="dnxzjy_cl_amt">金额</td>
					<td wd="dnxzjy_rs_1" tag_q="dnxzjy_rs_1_rs">人数</td>
					<td wd="dnxzjy_rs_1" tag_q="dnxzjy_rs_1_zb">占比</td>
					<td wd="dnxzjy_rs_1" tag_q="dnxzjy_rs_1_amt">金额</td>
					<td wd="dnxzjy_rs_2" tag_q="dnxzjy_rs_2_rs">人数</td>
					<td wd="dnxzjy_rs_2" tag_q="dnxzjy_rs_2_zb">占比</td>
					<td wd="dnxzjy_rs_2" tag_q="dnxzjy_rs_2_amt">金额</td>
					<td wd="dnxzjy_rs_3" tag_q="dnxzjy_rs_3_rs">人数</td>
					<td wd="dnxzjy_rs_3" tag_q="dnxzjy_rs_3_zb">占比</td>
					<td wd="dnxzjy_rs_3" tag_q="dnxzjy_rs_3_amt">金额</td>
					<td wd="dnxzjy_rs_4" tag_q="dnxzjy_rs_4_rs">人数</td>
					<td wd="dnxzjy_rs_4" tag_q="dnxzjy_rs_4_zb">占比</td>
					<td wd="dnxzjy_rs_4" tag_q="dnxzjy_rs_4_amt">金额</td>
					<td wd="dnxzjy_rs_5" tag_q="dnxzjy_rs_5_rs">人数</td>
					<td wd="dnxzjy_rs_5" tag_q="dnxzjy_rs_5_zb">占比</td>
					<td wd="dnxzjy_rs_5" tag_q="dnxzjy_rs_5_amt">金额</td>
					<td wd="dnxzjy_rs_gt5" tag_q="dnxzjy_rs_gt5_rs">人数</td>
					<td wd="dnxzjy_rs_gt5" tag_q="dnxzjy_rs_gt5_zb">占比</td>
					<td wd="dnxzjy_rs_gt5" tag_q="dnxzjy_rs_gt5_amt">金额</td>
				</tr>
				<#if dataList?? && dataList?size gt 0>
					<#list dataList as dl>
					<#if wd?? && wd=="newtrade-y">
					<tr tag="title_c">
						<td>截止${util.formatStrDateTime(dl.stat_dt!,'yyyyMM','yyyy年MM月')}</td>
						<td tag_q="dnxzjy_rs">${util.formatNum(dl.dnxzjy_rs_total!0)}</td>
						<td wd="dnxzjy_rs_fg" tag_q="dnxzjy_rs_fg_rs">${util.formatNum(dl.fgrs!0)}</td>
						<td wd="dnxzjy_rs_fg" tag_q="dnxzjy_rs_fg_zb">
						<#if dl.dnxzjy_rs_total?? && dl.dnxzjy_rs_total gt 0>
							${util.formatNum((dl.fgrs!0)/(dl.dnxzjy_rs_total),',##0.00%')}
						</#if>
						</td>
						<td wd="dnxzjy_rs_fg" tag_q="dnxzjy_rs_fg_amt">${util.formatNum(dl.fgje!0)}</td>
						
						<td wd="dnxzjy_cl" tag_q="dnxzjy_cl_rs">${util.formatNum(dl.clrs!0)}</td>
						<td wd="dnxzjy_cl" tag_q="dnxzjy_cl_zb">${util.formatNum(dl.clrs_rate!0,',##0.00%')}</td>
						<td wd="dnxzjy_cl" tag_q="dnxzjy_cl_amt">${util.formatNum(dl.clje!0)}</td>
						
						<td wd="dnxzjy_rs_1" tag_q="dnxzjy_rs_1_rs">${util.formatNum(dl.dnxzjy_rs_1!0)}</td>
						<td wd="dnxzjy_rs_1" tag_q="dnxzjy_rs_1_zb">${util.formatNum(dl.dnxzjy_rate_1!0,',##0.00%')}</td>
						<td wd="dnxzjy_rs_1" tag_q="dnxzjy_rs_1_amt">${util.formatNum(dl.dnxzjy_amt_1!)}</td>
						
						<td  wd="dnxzjy_rs_2" tag_q="dnxzjy_rs_2_rs">${util.formatNum(dl.dnxzjy_rs_2!0)}</td>
						<td wd="dnxzjy_rs_2" tag_q="dnxzjy_rs_2_zb">${util.formatNum(dl.dnxzjy_rate_2!0,',##0.00%')}</td>
						<td wd="dnxzjy_rs_2" tag_q="dnxzjy_rs_2_amt">${util.formatNum(dl.dnxzjy_amt_2!)}</td>
						
						<td wd="dnxzjy_rs_3" tag_q="dnxzjy_rs_3_rs">${util.formatNum(dl.dnxzjy_rs_3!0)}</td>
						<td wd="dnxzjy_rs_3" tag_q="dnxzjy_rs_3_zb">${util.formatNum(dl.dnxzjy_rate_3!0,',##0.00%')}</td>
						<td wd="dnxzjy_rs_3" tag_q="dnxzjy_rs_3_amt">${util.formatNum(dl.dnxzjy_amt_3!)}</td>
						
						<td wd="dnxzjy_rs_4" tag_q="dnxzjy_rs_4_rs">${util.formatNum(dl.dnxzjy_rs_4!0)}</td>
						<td wd="dnxzjy_rs_4" tag_q="dnxzjy_rs_4_zb">${util.formatNum(dl.dnxzjy_rate_4!0,',##0.00%')}</td>
						<td wd="dnxzjy_rs_4" tag_q="dnxzjy_rs_4_amt">${util.formatNum(dl.dnxzjy_amt_4!)}</td>
						
						<td wd="dnxzjy_rs_5" tag_q="dnxzjy_rs_5_rs">${util.formatNum(dl.dnxzjy_rs_5!0)}</td>
						<td wd="dnxzjy_rs_5" tag_q="dnxzjy_rs_5_zb">${util.formatNum(dl.dnxzjy_rate_5!0,',##0.00%')}</td>
						<td wd="dnxzjy_rs_5" tag_q="dnxzjy_rs_5_amt">${util.formatNum(dl.dnxzjy_amt_5!)}</td>
						
						<td wd="dnxzjy_rs_gt5" tag_q="dnxzjy_rs_gt5_rs">${util.formatNum(dl.dnxzjy_rs_gt5!0)}</td>
						<td wd="dnxzjy_rs_gt5" tag_q="dnxzjy_rs_gt5_zb">${util.formatNum(dl.dnxzjy_rate_gt5!0,',##0.00%')}</td>
						<td wd="dnxzjy_rs_gt5" tag_q="dnxzjy_rs_gt5_amt">${util.formatNum(dl.dnxzjy_amt_gt5!)}</td>
					</tr>
					</#if>
					<#if wd?? && wd=="newuser-y">
					<tr tag="title_c">
						<td>截止${util.formatStrDateTime(dl.stat_dt!,'yyyyMM','yyyy年MM月')}</td>
						<td tag_q="dnkh">${util.formatNum(dl.dnkh_zrs!0)}</td>
						<td tag_q="dnxzjy_rs">${util.formatNum(dl.dnkhdnjy_rs_total!0)}</td>
						
						<td wd="dnxzjy_rs_fg" tag_q="dnxzjy_rs_fg_rs">${util.formatNum(dl.fgrs!0)}</td>
						<td wd="dnxzjy_rs_fg" tag_q="dnxzjy_rs_fg_zb">
						<#if dl.dnkh_zrs?? && dl.dnkh_zrs gt 0>
							${util.formatNum((dl.fgrs!0)/(dl.dnkh_zrs),',##0.00%')}
						</#if>
						</td>
						<td wd="dnxzjy_rs_fg" tag_q="dnxzjy_rs_fg_amt">${util.formatNum(dl.dnkhdnjy_amt_total!0)}</td>
						
						<td wd="dnxzjy_cl" tag_q="dnxzjy_cl_rs">${util.formatNum(dl.clrs!0)}</td>
						<td wd="dnxzjy_cl" tag_q="dnxzjy_cl_zb">${util.formatNum(dl.clrs_rate!0,',##0.00%')}</td>
						<td wd="dnxzjy_cl" tag_q="dnxzjy_cl_amt">${util.formatNum(dl.clje!0)}</td>
						
						<td wd="dnxzjy_rs_1" tag_q="dnxzjy_rs_1_rs">${util.formatNum(dl.dnkhdnjy_rs_1!0)}</td>
						<td wd="dnxzjy_rs_1" tag_q="dnxzjy_rs_1_zb">${util.formatNum(dl.dnkhdnjy_rate_1!0,',##0.00%')}</td>
						<td wd="dnxzjy_rs_1" tag_q="dnxzjy_rs_1_amt">${util.formatNum(dl.dnkhdnjy_amt_1!0)}</td>
						
						<td wd="dnxzjy_rs_2" tag_q="dnxzjy_rs_2_rs">${util.formatNum(dl.dnkhdnjy_rs_2!0)}</td>
						<td wd="dnxzjy_rs_2" tag_q="dnxzjy_rs_2_zb">${util.formatNum(dl.dnkhdnjy_rate_2!0,',##0.00%')}</td>
						<td wd="dnxzjy_rs_2" tag_q="dnxzjy_rs_2_amt">${util.formatNum(dl.dnkhdnjy_amt_2!0)}</td>
						
						<td wd="dnxzjy_rs_3" tag_q="dnxzjy_rs_3_rs">${util.formatNum(dl.dnkhdnjy_rs_3!0)}</td>
						<td wd="dnxzjy_rs_3" tag_q="dnxzjy_rs_3_zb">${util.formatNum(dl.dnkhdnjy_rate_3!0,',##0.00%')}</td>
						<td wd="dnxzjy_rs_3" tag_q="dnxzjy_rs_3_amt">${util.formatNum(dl.dnkhdnjy_amt_3!0)}</td>
						
						<td  wd="dnxzjy_rs_4" tag_q="dnxzjy_rs_4_rs">${util.formatNum(dl.dnkhdnjy_rs_4!0)}</td>
						<td wd="dnxzjy_rs_4" tag_q="dnxzjy_rs_4_zb">${util.formatNum(dl.dnkhdnjy_rate_4!0,',##0.00%')}</td>
						<td wd="dnxzjy_rs_4" tag_q="dnxzjy_rs_4_amt">${util.formatNum(dl.dnkhdnjy_amt_4!0)}</td>
						
						<td wd="dnxzjy_rs_5" tag_q="dnxzjy_rs_5_rs">${util.formatNum(dl.dnkhdnjy_rs_5!0)}</td>
						<td wd="dnxzjy_rs_5" tag_q="dnxzjy_rs_5_zb">${util.formatNum(dl.dnkhdnjy_rate_5!0,',##0.00%')}</td>
						<td wd="dnxzjy_rs_5" tag_q="dnxzjy_rs_5_amt">${util.formatNum(dl.dnkhdnjy_amt_5!0)}</td>
						
						<td wd="dnxzjy_rs_gt5" tag_q="dnxzjy_rs_gt5_rs">${util.formatNum(dl.dnkhdnjy_rs_gt5!0)}</td>
						<td wd="dnxzjy_rs_gt5" tag_q="dnxzjy_rs_gt5_zb">${util.formatNum(dl.dnkhdnjy_rate_gt5!0,',##0.00%')}</td>
						<td wd="dnxzjy_rs_gt5" tag_q="dnxzjy_rs_gt5_amt">${util.formatNum(dl.dnkhdnjy_amt_gt5!0)}</td>
					</tr>
					</#if>
					</#list>
				</#if>
			</table>
		</div>
		<div class="tools fr mt10">
			<input type="submit" value="导出明细" class="btn-style-a">
		</div>
		</div>
		<!-- 总体明细 end -->
		
		<!-- 存量明细 begin -->
		<div class="user-mx-con hide" id="detail_cl">
		<div class="detail_filter1">
			<div class="zhl4 clearfix">
				<p class="title">指标：</p>
				<div class="zhl_right clearfix" ultag="q">
					<div class="ch_left2">
						<label>
							<input type="checkbox" class="detail_b" style="margin-right: 5px;  position: relative;top: 2px;" value="-1"/>全部</label>
					</div>
					<ul class="clearfix">
						<#if wd?? && wd=="newuser-y">
						<li>
							<label><input type="checkbox" name="dnkh" value="dnkh">开户人数</label>
						</li>
						</#if>
						<li class="w110">
							<label>
								<input type="checkbox" name="dnxzjy_rs" value="dnxzjy_rs">新增交易人数</label>
						</li>
						<li>
							<label>
								<input type="checkbox" name="dnxzjy_rs_fg" value="dnxzjy_rs_fg">复购</label>
						</li>
						<li>
							<label>
								<input type="checkbox" name="dnxzjy_rs_1" value="dnxzjy_rs_1">购买1次</label>
						</li>
						<li>
							<label>
								<input type="checkbox" name="dnxzjy_rs_2" value="dnxzjy_rs_2">购买2次</label>
						</li>
						<li>
							<label>
								<input type="checkbox" name="dnxzjy_rs_3" value="dnxzjy_rs_3">购买3次</label>
						</li>
						<li>
							<label>
								<input type="checkbox" name="dnxzjy_rs_4" value="dnxzjy_rs_4">购买4次</label>
						</li>
						<li>
							<label>
								<input type="checkbox" name="dnxzjy_rs_5" value="dnxzjy_rs_5">购买5次</label>
						</li>
						<li>
							<label>
								<input type="checkbox" name="dnxzjy_rs_gt5" value="dnxzjy_rs_gt5">购买5次以上</label>
						</li>
					</ul>
				</div>
			</div>
			<div class="zhl4 clearfix" style="margin-top: 0;">
				<p class="title">维度：</p>
				<div class="zhl_right clearfix">
					<div class="ch_left2">
						<label>
							<input tag="qwd" type="checkbox" class="detail_b" style="margin-right: 5px;  position: relative;top: 2px;" name="dnxzjy_rs" value="dnxzjy_rs"/>人数</label>
					</div>
					<ul class="clearfix">
						<li>
							<label>
								<input  tag="qwd" type="checkbox"  name="dnxzjy_zb" value="dnxzjy_zb">占比</label>
						</li>
						<li>
							<label>
								<input  tag="qwd" type="checkbox" name="dnxzjy_amt" value="dnxzjy_amt">金额</label>
						</li>
					</ul>
				</div>
			</div>
		</div>
		<div class="zb_table">
			<table id="table_detail_cl">
				<tr tag="title">
					<td rowspan="2"></td>
					<#if wd?? && wd=="newuser-y">
					<td rowspan="2" tag_q="dnkh">开户人数</td>
					</#if>
					<td rowspan="2" tag_q="dnxzjy_rs">新增交易人数</td>
					<td colspan="3" tag_q="dnxzjy_rs_fg">复购</td>
					<td colspan="3" tag_q="dnxzjy_rs_1">购买1次</td>
					<td colspan="3" tag_q="dnxzjy_rs_2">购买2次</td>
					<td colspan="3" tag_q="dnxzjy_rs_3">购买3次</td>
					<td colspan="3" tag_q="dnxzjy_rs_4">购买4次</td>
					<td colspan="3" tag_q="dnxzjy_rs_5">购买5次</td>
					<td colspan="3" tag_q="dnxzjy_rs_gt5">购买5次以上</td>
				</tr>
				<tr tag="title_c">
					<td wd="dnxzjy_rs_fg" tag_q="dnxzjy_rs_fg_rs">人数</td>
					<td wd="dnxzjy_rs_fg" tag_q="dnxzjy_rs_fg_zb">占比</td>
					<td wd="dnxzjy_rs_fg" tag_q="dnxzjy_rs_fg_amt">金额</td>
					<td wd="dnxzjy_rs_1" tag_q="dnxzjy_rs_1_rs">人数</td>
					<td wd="dnxzjy_rs_1" tag_q="dnxzjy_rs_1_zb">占比</td>
					<td wd="dnxzjy_rs_1" tag_q="dnxzjy_rs_1_amt">金额</td>
					<td wd="dnxzjy_rs_2" tag_q="dnxzjy_rs_2_rs">人数</td>
					<td wd="dnxzjy_rs_2" tag_q="dnxzjy_rs_2_zb">占比</td>
					<td wd="dnxzjy_rs_2" tag_q="dnxzjy_rs_2_amt">金额</td>
					<td wd="dnxzjy_rs_3" tag_q="dnxzjy_rs_3_rs">人数</td>
					<td wd="dnxzjy_rs_3" tag_q="dnxzjy_rs_3_zb">占比</td>
					<td wd="dnxzjy_rs_3" tag_q="dnxzjy_rs_3_amt">金额</td>
					<td wd="dnxzjy_rs_4" tag_q="dnxzjy_rs_4_rs">人数</td>
					<td wd="dnxzjy_rs_4" tag_q="dnxzjy_rs_4_zb">占比</td>
					<td wd="dnxzjy_rs_4" tag_q="dnxzjy_rs_4_amt">金额</td>
					<td wd="dnxzjy_rs_5" tag_q="dnxzjy_rs_5_rs">人数</td>
					<td wd="dnxzjy_rs_5" tag_q="dnxzjy_rs_5_zb">占比</td>
					<td wd="dnxzjy_rs_5" tag_q="dnxzjy_rs_5_amt">金额</td>
					<td wd="dnxzjy_rs_gt5" tag_q="dnxzjy_rs_gt5_rs">人数</td>
					<td wd="dnxzjy_rs_gt5" tag_q="dnxzjy_rs_gt5_zb">占比</td>
					<td wd="dnxzjy_rs_gt5" tag_q="dnxzjy_rs_gt5_amt">金额</td>
				</tr>
			 <#if dataList?? && dataList?size gt 0>
				<#list dataList as dl>
				<#if wd?? && wd=="newtrade-y">
				<tr tag="title_c">
					<td>截止${util.formatStrDateTime(dl.stat_dt!,'yyyyMM','yyyy年MM月')}</td>
					<td tag_q="dnxzjy_rs">${util.formatNum(dl.cl_dnxzjy_rs_total!0)}</td>
					<td wd="dnxzjy_rs_fg" tag_q="dnxzjy_rs_fg_rs">${util.formatNum(dl.fgrs_cl!0)}</td>
					<td wd="dnxzjy_rs_fg" tag_q="dnxzjy_rs_fg_zb">
					<#if dl.cl_dnxzjy_rs_total?? && dl.cl_dnxzjy_rs_total gt 0>
						${util.formatNum((dl.fgrs_cl!0)/(dl.cl_dnxzjy_rs_total),',##0.00%')}
					</#if>
					</td>
					<td wd="dnxzjy_rs_fg" tag_q="dnxzjy_rs_fg_amt">${util.formatNum(dl.fgje_cl!0)}</td>
					
					<td wd="dnxzjy_rs_1" tag_q="dnxzjy_rs_1_rs">${util.formatNum(dl.cl_dnxzjy_rs_1!0)}</td>
					<td wd="dnxzjy_rs_1" tag_q="dnxzjy_rs_1_zb">${util.formatNum(dl.cl_dnxzjy_rate_1!0,',##0.00%')}</td>
					<td wd="dnxzjy_rs_1" tag_q="dnxzjy_rs_1_amt">${util.formatNum(dl.cl_dnxzjy_amt_1!)}</td>
					
					<td wd="dnxzjy_rs_2" tag_q="dnxzjy_rs_2_rs">${util.formatNum(dl.cl_dnxzjy_rs_2!0)}</td>
					<td wd="dnxzjy_rs_2" tag_q="dnxzjy_rs_2_zb">${util.formatNum(dl.cl_dnxzjy_rate_2!0,',##0.00%')}</td>
					<td wd="dnxzjy_rs_2" tag_q="dnxzjy_rs_2_amt">${util.formatNum(dl.cl_dnxzjy_amt_2!)}</td>
					
					<td  wd="dnxzjy_rs_3" tag_q="dnxzjy_rs_3_rs">${util.formatNum(dl.cl_dnxzjy_rs_3!0)}</td>
					<td  wd="dnxzjy_rs_3" tag_q="dnxzjy_rs_3_zb">${util.formatNum(dl.cl_dnxzjy_rate_3!0,',##0.00%')}</td>
					<td  wd="dnxzjy_rs_3" tag_q="dnxzjy_rs_3_amt">${util.formatNum(dl.cl_dnxzjy_amt_3!)}</td>
					
					<td wd="dnxzjy_rs_4" tag_q="dnxzjy_rs_4_rs">${util.formatNum(dl.cl_dnxzjy_rs_4!0)}</td>
					<td wd="dnxzjy_rs_4" tag_q="dnxzjy_rs_4_zb">${util.formatNum(dl.cl_dnxzjy_rate_4!0,',##0.00%')}</td>
					<td  wd="dnxzjy_rs_4" tag_q="dnxzjy_rs_4_amt">${util.formatNum(dl.cl_dnxzjy_amt_4!)}</td>
					
					<td wd="dnxzjy_rs_5" tag_q="dnxzjy_rs_5_rs">${util.formatNum(dl.cl_dnxzjy_rs_5!0)}</td>
					<td  wd="dnxzjy_rs_5" tag_q="dnxzjy_rs_5_zb">${util.formatNum(dl.cl_dnxzjy_rate_5!0,',##0.00%')}</td>
					<td wd="dnxzjy_rs_5" tag_q="dnxzjy_rs_5_amt">${util.formatNum(dl.cl_dnxzjy_amt_5!)}</td>
					
					<td wd="dnxzjy_rs_gt5" tag_q="dnxzjy_rs_gt5_rs">${util.formatNum(dl.cl_dnxzjy_rs_gt5!0)}</td>
					<td wd="dnxzjy_rs_gt5" tag_q="dnxzjy_rs_gt5_zb">${util.formatNum(dl.cl_dnxzjy_rate_gt5!0,',##0.00%')}</td>
					<td wd="dnxzjy_rs_gt5" tag_q="dnxzjy_rs_gt5_amt">${util.formatNum(dl.cl_dnxzjy_amt_gt5!)}</td>
				</tr>
				</#if>
				<#if wd?? && wd=="newuser-y">
				<tr tag="title_c">
					<td>截止${util.formatStrDateTime(dl.stat_dt!,'yyyyMM','yyyy年MM月')}</td>
					<td tag_q="dnkh">${util.formatNum(dl.cl_dnkhdnjy_rs_total!0)}</td>
					<td tag_q="dnxzjy_rs">${util.formatNum(dl.cl_dnkh_zrs!0)}</td>
					
					<td wd="dnxzjy_rs_fg" tag_q="dnxzjy_rs_fg_rs">${util.formatNum(dl.fgrs_cl!0)}</td>
					<td wd="dnxzjy_rs_fg" tag_q="dnxzjy_rs_fg_zb">
					<#if dl.cl_dnkh_zrs?? && dl.cl_dnkh_zrs gt 0>
						${util.formatNum((dl.fgrs_cl!0)/(dl.cl_dnkh_zrs),',##0.00%')}
					</#if>
					</td>
					<td wd="dnxzjy_rs_fg" tag_q="dnxzjy_rs_fg_amt">${util.formatNum(dl.cl_dnkhdnjy_amt_total!0)}</td>
					
					<td wd="dnxzjy_rs_1" tag_q="dnxzjy_rs_1_rs">${util.formatNum(dl.cl_dnkhdnjy_rs_1!0)}</td>
					<td wd="dnxzjy_rs_1" tag_q="dnxzjy_rs_1_zb">${util.formatNum(dl.cl_dnkhdnjy_rate_1!0,',##0.00%')}</td>
					<td wd="dnxzjy_rs_1" tag_q="dnxzjy_rs_1_amt">${util.formatNum(dl.cl_dnkhdnjy_amt_1!0)}</td>
					
					<td  wd="dnxzjy_rs_2" tag_q="dnxzjy_rs_2_rs">${util.formatNum(dl.cl_dnkhdnjy_rs_2!0)}</td>
					<td wd="dnxzjy_rs_2" tag_q="dnxzjy_rs_2_zb">${util.formatNum(dl.cl_dnkhdnjy_rate_2!0,',##0.00%')}</td>
					<td wd="dnxzjy_rs_2" tag_q="dnxzjy_rs_2_amt">${util.formatNum(dl.cl_dnkhdnjy_amt_2!0)}</td>
					
					<td wd="dnxzjy_rs_3" tag_q="dnxzjy_rs_3_rs">${util.formatNum(dl.cl_dnkhdnjy_rs_3!0)}</td>
					<td wd="dnxzjy_rs_3" tag_q="dnxzjy_rs_3_zb">${util.formatNum(dl.cl_dnkhdnjy_rate_3!0,',##0.00%')}</td>
					<td wd="dnxzjy_rs_3" tag_q="dnxzjy_rs_3_amt">${util.formatNum(dl.cl_dnkhdnjy_amt_3!0)}</td>
					
					<td wd="dnxzjy_rs_4" tag_q="dnxzjy_rs_4_rs">${util.formatNum(dl.cl_dnkhdnjy_rs_4!0)}</td>
					<td wd="dnxzjy_rs_4" tag_q="dnxzjy_rs_4_zb">${util.formatNum(dl.cl_dnkhdnjy_rate_4!0,',##0.00%')}</td>
					<td wd="dnxzjy_rs_4" tag_q="dnxzjy_rs_4_amt">${util.formatNum(dl.cl_dnkhdnjy_amt_4!0)}</td>
					
					<td wd="dnxzjy_rs_5" tag_q="dnxzjy_rs_5_rs">${util.formatNum(dl.cl_dnkhdnjy_rs_5!0)}</td>
					<td wd="dnxzjy_rs_5" tag_q="dnxzjy_rs_5_zb">${util.formatNum(dl.cl_dnkhdnjy_rate_5!0,',##0.00%')}</td>
					<td wd="dnxzjy_rs_5" tag_q="dnxzjy_rs_5_amt">${util.formatNum(dl.cl_dnkhdnjy_amt_5!0)}</td>
					
					<td wd="dnxzjy_rs_gt5" tag_q="dnxzjy_rs_gt5_rs">${util.formatNum(dl.cl_dnkhdnjy_rs_gt5!0)}</td>
					<td wd="dnxzjy_rs_gt5" tag_q="dnxzjy_rs_gt5_zb">${util.formatNum(dl.cl_dnkhdnjy_rate_gt5!0,',##0.00%')}</td>
					<td wd="dnxzjy_rs_gt5" tag_q="dnxzjy_rs_gt5_amt">${util.formatNum(dl.cl_dnkhdnjy_amt_gt5!0)}</td>
				</tr>
				</#if>
				</#list>
			</#if>
			</table>
		</div>
		<div class="tools fr mt10">
			<input type="submit" value="导出明细2" class="btn-style-a">
		</div>
		</div>
		<!-- 存量明细 end -->
		
		<!-- 无存量明细 begin -->
		<div class="user-mx-con hide" id="detail_wcl">
		<div class="detail_filter1">
			<div class="zhl4 clearfix">
				<p class="title">指标：</p>
				<div class="zhl_right clearfix" ultag="q">
					<div class="ch_left2">
						<label>
							<input type="checkbox" class="detail_b" style="margin-right: 5px;  position: relative;top: 2px;" value="-1"/>全部</label>
					</div>
					<ul class="clearfix">
						<#if wd?? && wd=="newuser-y">
						<li>
							<label><input type="checkbox" name="dnkh" value="dnkh">开户人数</label>
						</li>
						</#if>
						<li class="w110">
							<label>
								<input type="checkbox" name="dnxzjy_rs" value="dnxzjy_rs">新增交易人数</label>
						</li>
						<li>
							<label>
								<input type="checkbox" name="dnxzjy_rs_fg" value="dnxzjy_rs_fg">复购</label>
						</li>
						<li>
							<label>
								<input type="checkbox" name="dnxzjy_rs_1" value="dnxzjy_rs_1">购买1次</label>
						</li>
						<li>
							<label>
								<input type="checkbox" name="dnxzjy_rs_2" value="dnxzjy_rs_2">购买2次</label>
						</li>
						<li>
							<label>
								<input type="checkbox" name="dnxzjy_rs_3" value="dnxzjy_rs_3">购买3次</label>
						</li>
						<li>
							<label>
								<input type="checkbox" name="dnxzjy_rs_4" value="dnxzjy_rs_4">购买4次</label>
						</li>
						<li>
							<label>
								<input type="checkbox" name="dnxzjy_rs_5" value="dnxzjy_rs_5">购买5次</label>
						</li>
						<li>
							<label>
								<input type="checkbox" name="dnxzjy_rs_gt5" value="dnxzjy_rs_gt5">购买5次以上</label>
						</li>
					</ul>
				</div>
			</div>
			<div class="zhl4 clearfix" style="margin-top: 0;">
				<p class="title">维度：</p>
				<div class="zhl_right clearfix">
					<div class="ch_left2">
						<label>
							<input tag="qwd" type="checkbox" class="detail_b" style="margin-right: 5px;  position: relative;top: 2px;" name="dnxzjy_rs" value="dnxzjy_rs"/>人数</label>
					</div>
					<ul class="clearfix">
						<li>
							<label>
								<input  tag="qwd" type="checkbox"  name="dnxzjy_zb" value="dnxzjy_zb">占比</label>
						</li>
						<li>
							<label>
								<input  tag="qwd" type="checkbox" name="dnxzjy_amt" value="dnxzjy_amt">金额</label>
						</li>
					</ul>
				</div>
			</div>
		</div>
		<div class="zb_table">
			<table id="table_detail_wcl">
				<tr tag="title">
					<td rowspan="2"></td>
					<#if wd?? && wd=="newuser-y">
					<td rowspan="2" tag_q="dnkh">开户人数</td>
					</#if>
					<td rowspan="2" tag_q="dnxzjy_rs">新增交易人数</td>
					<td colspan="3" tag_q="dnxzjy_rs_fg">复购</td>
					<td colspan="3" tag_q="dnxzjy_rs_1">购买1次</td>
					<td colspan="3" tag_q="dnxzjy_rs_2">购买2次</td>
					<td colspan="3" tag_q="dnxzjy_rs_3">购买3次</td>
					<td colspan="3" tag_q="dnxzjy_rs_4">购买4次</td>
					<td colspan="3" tag_q="dnxzjy_rs_5">购买5次</td>
					<td colspan="3" tag_q="dnxzjy_rs_gt5">购买5次以上</td>
				</tr>
				<tr tag="title_c">
					<td wd="dnxzjy_rs_fg" tag_q="dnxzjy_rs_fg_rs">人数</td>
					<td wd="dnxzjy_rs_fg" tag_q="dnxzjy_rs_fg_zb">占比</td>
					<td wd="dnxzjy_rs_fg" tag_q="dnxzjy_rs_fg_amt">金额</td>
					<td wd="dnxzjy_rs_1" tag_q="dnxzjy_rs_1_rs">人数</td>
					<td wd="dnxzjy_rs_1" tag_q="dnxzjy_rs_1_zb">占比</td>
					<td wd="dnxzjy_rs_1" tag_q="dnxzjy_rs_1_amt">金额</td>
					<td wd="dnxzjy_rs_2" tag_q="dnxzjy_rs_2_rs">人数</td>
					<td wd="dnxzjy_rs_2" tag_q="dnxzjy_rs_2_zb">占比</td>
					<td wd="dnxzjy_rs_2" tag_q="dnxzjy_rs_2_amt">金额</td>
					<td wd="dnxzjy_rs_3" tag_q="dnxzjy_rs_3_rs">人数</td>
					<td wd="dnxzjy_rs_3" tag_q="dnxzjy_rs_3_zb">占比</td>
					<td wd="dnxzjy_rs_3" tag_q="dnxzjy_rs_3_amt">金额</td>
					<td wd="dnxzjy_rs_4" tag_q="dnxzjy_rs_4_rs">人数</td>
					<td wd="dnxzjy_rs_4" tag_q="dnxzjy_rs_4_zb">占比</td>
					<td wd="dnxzjy_rs_4" tag_q="dnxzjy_rs_4_amt">金额</td>
					<td wd="dnxzjy_rs_5" tag_q="dnxzjy_rs_5_rs">人数</td>
					<td wd="dnxzjy_rs_5" tag_q="dnxzjy_rs_5_zb">占比</td>
					<td wd="dnxzjy_rs_5" tag_q="dnxzjy_rs_5_amt">金额</td>
					<td wd="dnxzjy_rs_gt5" tag_q="dnxzjy_rs_gt5_rs">人数</td>
					<td wd="dnxzjy_rs_gt5" tag_q="dnxzjy_rs_gt5_zb">占比</td>
					<td wd="dnxzjy_rs_gt5" tag_q="dnxzjy_rs_gt5_amt">金额</td>
				</tr>
				<#if dataList?? && dataList?size gt 0>
				<#list dataList as dl>
				<#if wd?? && wd=="newtrade-y">
				<tr tag="title_c">
					<td>截止${util.formatStrDateTime(dl.stat_dt!,'yyyyMM','yyyy年MM月')}</td>
					<td tag_q="dnxzjy_rs">${util.formatNum(dl.wcl_dnxzjy_rs_total!0)}</td>
					<td wd="dnxzjy_rs_fg" tag_q="dnxzjy_rs_fg_rs">${util.formatNum(dl.fgrs_wcl!0)}</td>
					<td wd="dnxzjy_rs_fg" tag_q="dnxzjy_rs_fg_zb">
					<#if dl.wcl_dnxzjy_rs_total?? && dl.wcl_dnxzjy_rs_total gt 0>
						${util.formatNum((dl.fgrs_wcl!0)/(dl.wcl_dnxzjy_rs_total),',##0.00%')}
					</#if>
					</td>
					<td wd="dnxzjy_rs_fg" tag_q="dnxzjy_rs_fg_amt">${util.formatNum(dl.fgje_wcl!0)}</td>
					
					<td wd="dnxzjy_rs_1" tag_q="dnxzjy_rs_1_rs">${util.formatNum(dl.wcl_dnxzjy_rs_1!0)}</td>
					<td wd="dnxzjy_rs_1" tag_q="dnxzjy_rs_1_zb">${util.formatNum(dl.wcl_dnxzjy_rate_1!0,',##0.00%')}</td>
					<td wd="dnxzjy_rs_1" tag_q="dnxzjy_rs_1_amt">${util.formatNum(dl.wcl_dnxzjy_amt_1!)}</td>
					
					<td wd="dnxzjy_rs_2" tag_q="dnxzjy_rs_2_rs">${util.formatNum(dl.wcl_dnxzjy_rs_2!0)}</td>
					<td wd="dnxzjy_rs_2" tag_q="dnxzjy_rs_2_zb">${util.formatNum(dl.wcl_dnxzjy_rate_2!0,',##0.00%')}</td>
					<td wd="dnxzjy_rs_2" tag_q="dnxzjy_rs_2_amt">${util.formatNum(dl.wcl_dnxzjy_amt_2!)}</td>
					
					<td wd="dnxzjy_rs_3" tag_q="dnxzjy_rs_3_rs">${util.formatNum(dl.wcl_dnxzjy_rs_3!0)}</td>
					<td wd="dnxzjy_rs_3" tag_q="dnxzjy_rs_3_zb">${util.formatNum(dl.wcl_dnxzjy_rate_3!0,',##0.00%')}</td>
					<td wd="dnxzjy_rs_3" tag_q="dnxzjy_rs_3_amt">${util.formatNum(dl.wcl_dnxzjy_amt_3!)}</td>
					
					<td wd="dnxzjy_rs_4" tag_q="dnxzjy_rs_4_rs">${util.formatNum(dl.wcl_dnxzjy_rs_4!0)}</td>
					<td wd="dnxzjy_rs_4" tag_q="dnxzjy_rs_4_zb">${util.formatNum(dl.wcl_dnxzjy_rate_4!0,',##0.00%')}</td>
					<td wd="dnxzjy_rs_4" tag_q="dnxzjy_rs_4_amt">${util.formatNum(dl.wcl_dnxzjy_amt_4!)}</td>
					
					<td wd="dnxzjy_rs_5" tag_q="dnxzjy_rs_5_rs">${util.formatNum(dl.wcl_dnxzjy_rs_5!0)}</td>
					<td wd="dnxzjy_rs_5" tag_q="dnxzjy_rs_5_zb">${util.formatNum(dl.wcl_dnxzjy_rate_5!0,',##0.00%')}</td>
					<td wd="dnxzjy_rs_5" tag_q="dnxzjy_rs_5_amt">${util.formatNum(dl.wcl_dnxzjy_amt_5!)}</td>
					
					<td wd="dnxzjy_rs_gt5" tag_q="dnxzjy_rs_gt5_rs">${util.formatNum(dl.wcl_dnxzjy_rs_gt5!0)}</td>
					<td wd="dnxzjy_rs_gt5" tag_q="dnxzjy_rs_gt5_zb">${util.formatNum(dl.wcl_dnxzjy_rate_gt5!0,',##0.00%')}</td>
					<td wd="dnxzjy_rs_gt5" tag_q="dnxzjy_rs_gt5_amt">${util.formatNum(dl.wcl_dnxzjy_amt_gt5!)}</td>
				</tr>
				</#if>
				<#if wd?? && wd=="newuser-y">
				<tr tag="title_c">
					<td>截止${util.formatStrDateTime(dl.stat_dt!,'yyyyMM','yyyy年MM月')}</td>
					<td tag_q="dnkh">${util.formatNum(dl.wcl_dnkh_zrs!0)}</td>
					<td tag_q="dnxzjy_rs">${util.formatNum(dl.wcl_dnkhdnjy_rs_total!0)}</td>
					
					<td wd="dnxzjy_rs_fg" tag_q="dnxzjy_rs_fg_rs">${util.formatNum(dl.fgrs_wcl!0)}</td>
					<td wd="dnxzjy_rs_fg" tag_q="dnxzjy_rs_fg_zb">
					<#if dl.wcl_dnkh_zrs?? && dl.wcl_dnkh_zrs gt 0>
						${util.formatNum((dl.fgrs_wcl!0)/(dl.wcl_dnkh_zrs),',##0.00%')}
					</#if>
					</td>
					<td wd="dnxzjy_rs_fg" tag_q="dnxzjy_rs_fg_amt">${util.formatNum(dl.wcl_dnkhdnjy_amt_total!0)}</td>
					
					<td wd="dnxzjy_rs_1" tag_q="dnxzjy_rs_1_rs">${util.formatNum(dl.wcl_dnkhdnjy_rs_1!0)}</td>
					<td wd="dnxzjy_rs_1" tag_q="dnxzjy_rs_1_zb">${util.formatNum(dl.wcl_dnkhdnjy_rate_1!0,',##0.00%')}</td>
					<td wd="dnxzjy_rs_1" tag_q="dnxzjy_rs_1_amt">${util.formatNum(dl.wcl_dnkhdnjy_amt_1!0)}</td>
					
					<td wd="dnxzjy_rs_2" tag_q="dnxzjy_rs_2_rs">${util.formatNum(dl.wcl_dnkhdnjy_rs_2!0)}</td>
					<td wd="dnxzjy_rs_2" tag_q="dnxzjy_rs_2_zb">${util.formatNum(dl.wcl_dnkhdnjy_rate_2!0,',##0.00%')}</td>
					<td wd="dnxzjy_rs_2" tag_q="dnxzjy_rs_2_amt">${util.formatNum(dl.wcl_dnkhdnjy_amt_2!0)}</td>
					
					<td wd="dnxzjy_rs_3" tag_q="dnxzjy_rs_3_rs">${util.formatNum(dl.wcl_dnkhdnjy_rs_3!0)}</td>
					<td wd="dnxzjy_rs_3" tag_q="dnxzjy_rs_3_zb">${util.formatNum(dl.wcl_dnkhdnjy_rate_3!0,',##0.00%')}</td>
					<td wd="dnxzjy_rs_3" tag_q="dnxzjy_rs_3_amt">${util.formatNum(dl.wcl_dnkhdnjy_amt_3!0)}</td>
					
					<td wd="dnxzjy_rs_4" tag_q="dnxzjy_rs_4_rs">${util.formatNum(dl.wcl_dnkhdnjy_rs_4!0)}</td>
					<td wd="dnxzjy_rs_4" tag_q="dnxzjy_rs_4_zb">${util.formatNum(dl.wcl_dnkhdnjy_rate_4!0,',##0.00%')}</td>
					<td wd="dnxzjy_rs_4" tag_q="dnxzjy_rs_4_amt">${util.formatNum(dl.wcl_dnkhdnjy_amt_4!0)}</td>
					
					<td  wd="dnxzjy_rs_5" tag_q="dnxzjy_rs_5_rs">${util.formatNum(dl.wcl_dnkhdnjy_rs_5!0)}</td>
					<td wd="dnxzjy_rs_5" tag_q="dnxzjy_rs_5_zb">${util.formatNum(dl.wcl_dnkhdnjy_rate_5!0,',##0.00%')}</td>
					<td wd="dnxzjy_rs_5" tag_q="dnxzjy_rs_5_amt">${util.formatNum(dl.wcl_dnkhdnjy_amt_5!0)}</td>
					
					<td wd="dnxzjy_rs_gt5" tag_q="dnxzjy_rs_gt5_rs">${util.formatNum(dl.wcl_dnkhdnjy_rs_gt5!0)}</td>
					<td wd="dnxzjy_rs_gt5" tag_q="dnxzjy_rs_gt5_zb">${util.formatNum(dl.wcl_dnkhdnjy_rate_gt5!0,',##0.00%')}</td>
					<td wd="dnxzjy_rs_gt5" tag_q="dnxzjy_rs_gt5_amt">${util.formatNum(dl.wcl_dnkhdnjy_amt_gt5!0)}</td>
				</tr>
				</#if>
				</#list>
				
			</#if>
			</table>
		</div>
		<div class="tools fr mt10">
			<input type="submit" value="导出明细3" class="btn-style-a">
		</div>
    
    </div>
</div>
<!-- 公募开户渠道 end -->
<#include "../include/ukeep_quota_explain.ftl">
			
<script type="text/javascript">
$(function(){
	var jsonData =[];
	<#if jsonData??>
		jsonData = ${jsonData!};
	</#if>
	var wd =$(".fwqd li.current").attr("wd");
	drawPieYear(jsonData.list,wd);
	//加载查询条件
	loadQueryExp();
	//绑定tab切换事件
	tabToggleEvent();
	//复选框选中
	checkBoxDefalut();
	//复选框事件
	eventCheckbox();
});
	
</script>