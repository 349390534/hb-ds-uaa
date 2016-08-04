<div class="detail_fs" style="margin:10px 20px 0;">
	<div class="tab_list">
		<ul class="tab_menu1 clearfix" id="trendTabUL">
			<li class="current"><h3>趋势明细</h3></li>
			<li><h3>基金明细</h3></li>
		</ul>
		<div class="tab_box1" id="trendAndFundDiv">
			<div class="con"><!-- 渠道明细 begin -->
				<div class="det-datalist" id="fundTypeDetail">
				<table class="det-d-qdmx">
					<thead>
						<tr class="tit" type="1">
							<td>日期</td>
							<td>类型</td>
							<td>存量金额</td>
							<td>申购金额（下单）</td>
							<td>赎回份额（下单）</td>
							<td>申购金额（确认）</td>
							<td>赎回金额（确认）</td>
							<td>净申购金额（确认）</td>
							<td>费率</td>
						</tr>
						<tr class="tit  hide" type="2">
							<td>日期</td>
							<td>类型</td>
							<td>收入</td>
							<td>成本</td>
							<td>认购费收入</td>
							<td>申购费收入</td>
							<td>赎回费收入</td>
							<td>尾随收入</td>
							<td>销售服务费收入</td>
							<td>额外营销收入</td>
						</tr>
					</thead>
					<tbody id="fundTypeDetail_table_tbody"></tbody>
				</table>
				</div>
			</div><!-- 渠道明细 end -->
			<div class="con hide" id="fundDetail"><!-- 渠道明细 begin -->
				<div class="det-datalist">
					<table class="det-d-qdmx" style="width:1800px;">
						<thead>
							<tr class="tit" type="1">
								<td style="width:300px;">基金</td>
								<td style="width:200px;">基金公司</td>
								<td>类型</td>
								<td>存量金额</td>
								<td>申购金额(下单)</td>
								<td>赎回份额(下单)</td>
								<td>申购金额(确认)</td>
								<td>赎回金额(确认)</td>
								<td>净申购金额(确认)</td>
								<td>费率</td>
							</tr>
							<tr class="tit hide" type="2">
								<td style="width:300px;">基金</td>
								<td style="width:200px;">基金公司</td>
								<td>类型</td>
								<td>收入</td>
								<td>成本</td>
								<td>认购费收入</td>
								<td>申购费收入</td>
								<td>赎回费收入</td>
								<td>销售服务费收入</td>
								<td>尾随收入</td>
								<td>额外营销费用</td>
							</tr>
						</thead>
						<tbody id="fundDetail_table_tbody"></tbody>
						</table>
				</div>
			</div><!-- 趋势明细 end -->
		</div>
	</div>
	<div class="page clearfix" id="fundtypepage">
		<p class="fl">共<fundtypepage_count>0</fundtypepage_count>页，每页显示
			<select name="fundtypepagePerPage" id="fundtypepagePerPage">
				<option value="10">10</option>
				<option value="20">20</option>
				<option value="50">50</option>
			</select>
		</p>
		<ul class="fr" tag="pn" type="fundtype">
			<li pc="p"><a href="javascript:void(0);">上一页</a></li>
			<li tag="ps"><span>1</span></li>
			<li pc="n"><a href="javascript:void(0)">下一页</a></li>
		</ul>
	</div>
	
	<div class="page clearfix hide"  id="funddetailpage">
		<p class="fl">共<funddetailpage_count>0</funddetailpage_count>页，每页显示
			<select name="fundDataPerPage" id="fundDataPerPage">
				<option value="10">10</option>
				<option value="20">20</option>
				<option value="50">50</option>
			</select>
		</p>
		<ul class="fr" tag="pn" type="funddetailtype">
		<li pc="p"><a href="javascript:void(0);">上一页</a></li>
		<li tag="ps"><span>1</span></li>
		<li pc="n"><a href="javascript:void(0);">下一页</a></li>
		</ul>
	</div>
	<div class="tools1">
		<input type="submit" value="导出明细" class="btn-style-a" id="fundsaleExport">
	</div>						
</div>

<script type="text/javascript">
$(function(){
	eventTrendTabToggle();
	
	eventPageControl();
	var contentPath = '${uri.context_path!}';
	downloadEvent(contentPath);
});

</script>