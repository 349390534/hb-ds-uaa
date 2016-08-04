<#import "/uri.ftl" as uri />
<#import "/util.ftl" as util />
<!-- 分布图/趋势图 begin -->
<div class="chartbox">
        <ul class="data_bar2 clearfix">
            <li class="current">
                <p>一手成交客户数<span class="fr pdr5">${lastDate!}</span></p>
                <p style="font-size:20px;">${util.formatNum(dataTop.trade_cnt1!)}
                	<span class="fr pdr20"style="font-size: 14px;">环比<span class="<#if trade_cnt1_r?? && trade_cnt1_r gte 0>cRed<#else>cGreen</#if>">${util.formatNum(trade_cnt1_r,',##0.00%')}</span></span>
                </p>     
                <p class="p">当月分配 <span>${util.formatNum(dataTop.curr_assign_cnt1!)}</span>人</p>
                <p class="p">当月成交 <span>${util.formatNum(dataTop.curr_trade_cnt1!)}</span>人</p>        
            </li>
            <li>
                <p>二手潜在客户数<span class="fr pdr5">${lastDate!}</span></p>
                <p style="font-size:20px;">${util.formatNum(dataTop.trade_cnt2!)}<span class="fr pdr20" style="font-size: 14px;">环比<span class="<#if trade_cnt2_r?? && trade_cnt2_r gte 0>cRed<#else>cGreen</#if>">${util.formatNum(trade_cnt2_r,',##0.00%')}</span></span></p>  
                <p class="p">当月分配 <span>${util.formatNum(dataTop.curr_assign_cnt2!)}</span>人</p>
                <p class="p">当月成交 <span>${util.formatNum(dataTop.curr_trade_cnt2!)}</span>人</p>
            </li>
            <li>
                <p>二手成交客户数<span class="fr pdr5">${lastDate!}</span></p>
                <p style="font-size:20px;">${util.formatNum(dataTop.trade_cnt3!)}<span class="fr pdr20" style="font-size: 14px;">环比<span class="<#if trade_cnt3_r?? && trade_cnt3_r gte 0>cRed<#else>cGreen</#if>">${util.formatNum(trade_cnt3_r,',##0.00%')}</span></span></p>       
                <p class="p">当月分配 <span>${util.formatNum(dataTop.curr_assign_cnt3!)}</span>人</p>
                <p class="p">当月成交 <span>${util.formatNum(dataTop.curr_trade_cnt3!)}</span>人</p>                      
            </li>
        </ul>	
	<div class="chartbox_tab">
		<div class="box" style="height: 446px;">
			<div class="con clearfix" style="height: 446px;background-color: #fff;color: inherit;padding: 15px 20px 20px;border-color: #e7eaec;border-image: none;border-style: solid solid none;border-width: 1px 0;">
			    <div class="charts" id="disabClient_chart"></div>
			</div>
		</div>
	</div>						
</div>
<!-- 分布图/趋势图 end -->
<!-- 明细 begin -->
<div class="detail">
	<div class="tab_list">
		<div class="tab_box">
		    <!-- 客户成交分析 begin -->
			<div class="con">
				<div class="detail_filter">
				    <div class="zhl2 clearfix">
				        <p class="title">指标：</p>
				        <div class="zhl_right clearfix" id="quato">
						    <div class="ch_left2">
						        <label><input type="checkbox" class="detail_b" style="margin-right: 5px;  position: relative;top: 2px;" q="0"/>全选</label>
						    </div>
							<ul class="clearfix">
								<li><label><input type="checkbox" q="ASSIGN_CNT1">一手分配客户数</label></li>
								<li><label><input type="checkbox" q="TRADE_CNT1">一手成交客户数</label></li>
								<li><label><input type="checkbox" q="TRADE_PCT1">一手客户成交比</label></li>
								<li><label><input type="checkbox" q="ASSIGN_CNT2">二手潜在分配客户数</label></li>
								<li><label><input type="checkbox" q="TRADE_CNT2">二手潜在成交数</label></li>
								<li><label><input type="checkbox" q="TRADE_PCT2">二手潜在客户成交比</label></li>	
								<li><label><input type="checkbox" q="ASSIGN_CNT3">二手成交分配客户数</label></li>
								<li><label><input type="checkbox" q="TRADE_CNT3">二手成交客户成交数</label></li>
								<li><label><input type="checkbox" q="TRADE_PCT3">二手成交客户成交比</label></li>
							</ul>
				        </div>
                    </div>
				</div>
				<div class="tools">
					<input type="button" value="导出明细" class="btn-style-a" id="export_penetrate">
				</div>
				<table class="det-d-qdmx2head" id="title">
                    <thead>
                        <tr class="tit">
                            <td width="120">投顾姓名</td>
                            <td width="102" tag="1">一手客户</td>
                            <td width="100" tag="1"></td>
                            <td width="100" tag="1"></td>
                            <td width="122" tag="2">二手潜在客户</td>
                            <td width="100" tag="2"></td>
                            <td width="100" tag="2"></td>
                            <td width="122" tag="3">二手成交客户</td>
                            <td width="100" tag="3"></td>
                            <td width="118" tag="3"></td>
                        </tr>
                    </thead>
				</table>
				<div class="det-datalist" style="height: 300px;margin-top:0;overflow-y: scroll;overflow-x: hidden;" id="con_main">
					<table class="det-d-qdmx3" style="width: 1073px;">
						<tr>
							<td width="125"></td>
							<td width="100" q="ASSIGN_CNT1" tag="1">分配客户</td>
							<td width="100" q="TRADE_CNT1" tag="1">成交客户</td>
							<td width="100" q="TRADE_PCT1" tag="1">成交比</td>
							<td width="125" q="ASSIGN_CNT2" tag="2">分配客户</td>
							<td width="100" q="TRADE_CNT2" tag="2">成交客户</td>
							<td width="100" q="TRADE_PCT2" tag="2">成交比</td>
							<td width="125" q="ASSIGN_CNT3" tag="3">分配客户</td>
							<td width="100" q="TRADE_CNT3" tag="3">成交客户</td>
							<td width="100" q="TRADE_PCT3" tag="3">成交比</td>
						</tr>
						<#if dataTgList?? && dataTgList?size gt 0 >
							<#list dataTgList as dt >
							<tr>
								<td>${dt.consname!}</td>
								<td class="tdr" q="ASSIGN_CNT1">${dt.assign_cnt1!}</td>
								<td class="tdr" q="TRADE_CNT1">${dt.trade_cnt1!}</td>
								<td class="tdr" q="TRADE_PCT1">${dt.trade_pct1!0}%</td>
								<td class="tdr" q="ASSIGN_CNT2">${dt.assign_cnt2!}</td>
								<td class="tdr" q="TRADE_CNT2">${dt.trade_cnt2!}</td>
								<td class="tdr" q="TRADE_PCT2">${dt.trade_pct2!0}%</td>
								<td class="tdr" q="ASSIGN_CNT3">${dt.assign_cnt3!}</td>
								<td class="tdr" q="TRADE_CNT3">${dt.trade_cnt3!}</td>
								<td class="tdr" q="TRADE_PCT3">${dt.trade_pct3!0}%</td>
							</tr>							
							</#list>
						</#if>
						
						
					</table>
				</div>
            </div>	<!-- 客户成交分析 end -->																	
		</div>
	</div>						
</div>
<!-- 明细 end -->


<script type="text/javascript">
$("#start").val('${start!}');
$("#end").val('${end!}');
<!--图表数据 -->
var dataListJson=${dataListJson![]};

//var loadImg = "<p style='font-size:15px;align:center;marging-left:200px;'>数据加载中...</p>";

 
$(function() {
	//默认指标全部选中
	checkBox(true);
	
	//默认画图
	drawIndex();
	
	//注册复选框事件
	$("#quato input[type=checkbox]").click(checkBoxClickEvent);
	
	//注册导出事件
	$("#export_penetrate").click(exportPenetrateData);
});


</script>