<#import "/uri.ftl" as uri />
<#import "/util.ftl" as util />
<div class="chartbox">
        <ul class="data_bar clearfix">
            <li class="current"><p>货转股人数<span class="fr pdr5">${lastDate!}</span></p><h1>${util.formatNum(dataTop.cnt_c_e!0)}</h1><p>环比<span class="cRed">${util.formatNum(CNT_C_E_R!0,',##0.00%')}</span></p></li>
            <li><p>货转股基数<span class="fr pdr5">${lastDate!}</span></p><h1>${util.formatNum(dataTop.cnt_c!0)}</h1><p>环比<span class="cRed">${util.formatNum(CNT_C_R!0,',##0.00%')}</span></p></li>
            <li><p>公转私人数<span class="fr pdr5">${lastDate!}</span></p><h1>${util.formatNum(dataTop.cnt_f!0)}</h1><p>环比<span class="cRed">${util.formatNum(CNT_F_R!0,',##0.00%')}</span></p></li>
            <li><p>公转私基数<span class="fr pdr5">${lastDate!}</span></p><h1>${util.formatNum(dataTop.cnt_b!0)}</h1><p>环比<span class="cRed">${util.formatNum(CNT_B_R!0,',##0.00%')}</span></p></li>
        </ul>
	<div class="chartbox_tab" style="border:none;background-color: #f1f4f7">
		<div class="box" style="height: 425px;">
			<div class="con clearfix" style="">
			    <div class="charts_left">
                    <p class="title">穿透分析金字塔</p>
                    <div class="charts" id="pyramid" style="width:750px;"></div>  
			    </div>
			    <div class="charts_right" >
                    <p class="title">六象限双饼图</p>
                    <div style="width: 290px;padding: 5px 8px 0px 2px;">
				        <input type="text" id="range" value="" name="range" />
					</div>
                    <div class="charts" id="ctfx6r" style="display:none;width:300px;"></div>
			    </div>
			</div>
		</div>
	</div>						
</div>
<!-- 明细 begin -->
<div class="detail_p">
	<div class="" style="width:1130px;">
		<ul class="tab_menu2 clearfix">
			<li class="current" tag="up20w">20W以上客户占比</li>
			<li tag="c2e_rate">货转股转化率</li>
			<li tag="f2b_rate">公转私转化率</li>
			<li tag="f2b_rate_up20w">公转私贡献率</li>
			<li tag="f2b_rate_ctfx6r">公转私六象限</li>															
		</ul>
		<div class="tab_box">
			<div class="con_p" style="height: 300px;">
				<div class="detail_filter" style="border-bottom: none;" align="center">
				    <div class="charts" id="combinationChart" style="height: 290px;width:980px;"></div>
				</div>
            </div>
        </div>
    </div>
 </div>
                			
	<div class="detail">
	<div class="tab_list"> 
		<div class="tab_box">
            <div class="zhl clearfix" style="border-bottom: 1px solid #dbe3eb;">
                <p class="title">指标：</p>
                <div class="zhl_right clearfix" id="quato">
                    <div class="ch_left">
                        <label><input type="checkbox" class="detail_b" style="margin-right: 5px;  position: relative;top: 2px;" q="0"/>全选</label>
                    </div>
                    <ul class="clearfix" >
                        <li><label><input type="checkbox" class="t14" q="1"><span>首次交易为普通公募<br />基金总客户数</span></label></li>
                        <li><label><input type="checkbox" class="t14" q="2"><span>首次交易为货币基金<br />的客户总数</span></label></li>
                        <li><label><input type="checkbox" class="t14" q="3"><span>普通公募基金(保有量)<br />存量到过20万以上的客户总数</span></label></li>
                        <li><label><input type="checkbox" q="4">货转股客户数</label></li>
                        <li><label><input type="checkbox" q="5">货转股的转化率</label></li>
                        <li><label><input type="checkbox" class="t14" q="6"><span>交易股票类基金的<br />总客户数</span></label></li>									
                        <li><label><input type="checkbox" class="t14" q="7"><span>首次交易为公募基金<br />高净值产品交易总客户数</span></label></li>
                        <li><label><input type="checkbox" q="8">公转私的转化率</label></li>
                        <li><label><input type="checkbox" q="9">公转私路径B-E-F客户数</label></li>
                        <li><label><input type="checkbox" q="10">公转私路径B-E-F转化率</label></li>
                        <li><label><input type="checkbox" q="11">公转私路径B-D-F客户数</label></li>
                        <li><label><input type="checkbox" q="12">公转私路径B-D-F转化率</label></li>
                    </ul>
                </div>
            </div>	
			<div class="con" id="con_main">                               									
				<div class="tools">
					<input type="button" value="导出明细" class="btn-style-a" id="export_penetrate">
				</div>
				<table class="det-d-qdmx2head" id="wd">
                    <thead>
                        <tr class="tit">
                            <td width="60" class="tdc">主维度</td>
                            <td width="120" class="tdl"></td>
                            <td width="78" class="tdl" q="1">首次交易为<br />普通公募基金总客户数</td>
                            <td width="78" class="tdl" q="2">首次交易为<br />货币基金的客户总数</td>
                            <td width="74" class="tdl" q="3">普通公募基<br />金存量(保有量市值)到过20万以上的客户总数</td>
                            <td width="75" class="tdl" q="4">交易股票类<br />基金的总客户数</td>
                            <td width="78" class="tdl" q="5">首次交易为<br />公募基金的高净值产品交易总客户数</td>
                            <td width="74" class="tdl" q="6">货转股<br />客户数</td>
                            <td width="76" class="tdl" q="7">货转股<br />东转股数</td>
                            <td width="78" class="tdl" q="8">公转私的<br />转化率</td>
                            <td width="76" class="tdl" q="9">公转私路径<br />B-E-F客户数</td>
                            <td width="76" class="tdl" q="10">公转私路径<br />B-E-F转化率</td>
                            <td width="76" class="tdl" q="11">公转私路径<br />B-D-F客户数</td>
                            <td width="96" class="tdl" q="12">公转私路径<br />B-D-F转化率</td>
                        </tr>
                    </thead>
				</table>
				<div class="det-datalist" style="height: 400px;margin-top:0;overflow-y: scroll;overflow-x: hidden;">
					<table class="det-d-qdmx2" style="width: 1073px;" id="data">
						<tr>
							<td width="60">截止日期</td>
							<td width="128">分配部门</td>
							<td width="78" q="1">B</td>
							<td width="78" q="2">C</td>
							<td width="78" q="3">D</td>
							<td width="78" q="4">E</td>
							<td width="78" q="5">F</td>
							<td width="78" q="6">C∩E</td>
							<td width="78" q="7">(C∩E)/C</td>
							<td width="78" q="8">F/B</td>
							<td width="78" q="9">E∩F</td>
							<td width="78" q="10">(E∩F)/B</td>
							<td width="78" q="11">D∩F</td>
							<td width="78" q="12">(D∩F)/B</td>
						</tr>
						<#if dataList?? && dataList?size gt 0>
							<#list dataList as d>
							<tr>
						    <td>${d.statdt!}</td>
						    <td class="tdl">${d.departcode!}</td>
						    <td q="1">${util.formatNum(d.cnt_b!)}</td>
						    <td q="2">${util.formatNum(d.cnt_c!)}</td>
						    <td q="3">${util.formatNum(d.cnt_d!)}</td>
						    <td q="4">${util.formatNum(d.cnt_e!)}</td>
						    <td q="5">${util.formatNum(d.cnt_f!)}</td>
						    <td q="6">${util.formatNum(d.cnt_c_e!)}</td>
						    <td q="7">${util.formatNum(d.cnt_c_e/d.cnt_c,',##0.0000%')}</td>
						    <td q="8">${util.formatNum(d.cnt_f/d.cnt_b,',##0.0000%')}</td>
						    <td q="9">${d.cnt_e_f!}</td>
						    <td q="10">${d.e_f2b!}</td>
						    <td q="11">${d.cnt_d_f!}</td>
						    <td q="12">${d.d_f2b!}</td>
							</tr>
							</#list>
						</#if>							
					</table>
				</div>
			</div>
		</div>
	</div>						
</div>
<!-- 明细 end -->


<script type="text/javascript">
$("#start").val('${start!}');
$("#end").val('${end!}');
<!--金字塔数据 -->
var dataTopJson=${dataTopJson![]};
<!--六象限图当前月数据 -->
var ctfx6rJson=${ctfx6rJson![]};
<!--图表数据 -->
var dataListJson=${dataListJson![]};
<!--六象限图集合数据  -->
var ctfx6rListJson=${ctfx6rListJson![]};
<!--日期数据-->
var rangeDate = ${rangeDate![]}; 
var rangeDateStr = ${rangeDate![]}; 

//var loadImg = "<p style='font-size:15px;align:center;marging-left:200px;'>数据加载中...</p>";


var ionRangeSliderObj = {
	grid : false,
	from : rangeDate.length - 1,
	postfix : "月",
	values : rangeDate,
	onFinish : function(obj) {
		console.log(obj);
		var from = obj.from;
		var m = rangeDateStr[from];
		var dataObj = [];
		for (var i = 0; i < ctfx6rListJson.length; i++) {
			var ctx = ctfx6rListJson[i];
			var statdt = ctx.statdt;
			var mc = statdt.substr(4, 2);
			if (mc == m) {
				dataObj.push(ctx);
			}
		}
		drawCtfx6r(dataObj);
	},
	onStart : function(obj) {
		console.log(obj);
	}
};
$(function() {
	//默认指标全部选中
	checkBox(true);
	//默认画图
	drawIndex();
	$("#ctfx6r").show();
	//注册切换图表事件
	$("ul.tab_menu2 li").click(tabMenu);
	
	//注册复选框事件
	$("#quato input[type=checkbox]").click(checkBoxClickEvent);
	
	//注册导出事件
	$("#export_penetrate").click(exportPenetrateData);

	//象限图拖动插件
	$("#range").ionRangeSlider(ionRangeSliderObj);
});


</script>


