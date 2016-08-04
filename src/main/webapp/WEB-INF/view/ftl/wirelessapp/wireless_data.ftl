<#import "/util.ftl" as util />
<#import "/uri.ftl" as uri />
<#import "/host.ftl" as host />
	
	<!-- 分布图/趋势图 begin -->
	<div class="chartbox">
		<!--<div class="title">
			<div class="time fr">2015-01-28　至　2015-03-01</div>
			<h3>搜索引擎</h3>
		</div>-->
		<div class="chartbox_tab">
			<div class="bar_1 clearfix">
				<ul class="fl" id="data_tab">
					<li class="current" tag="activateNum">访问次数：${all.activateNum!0}次<p>环比：${util.formatNum(actR!0,',##0.00%')}</p></li>
					<li tag="openaccNum">开户人数：${all.openaccNum!0}人<p>环比：${util.formatNum(openR!0,',##0.00%')}</p></li>
					<li tag="bindcardNum">绑卡人数：${all.bindcardNum!0}人<p>环比：${util.formatNum(bindR!0,',##0.00%')}</p></li>
					<li tag="orderNum">下单人数：${all.orderNum!0}人<p>环比：${util.formatNum(orderR!0,',##0.00%')}</p></li>
				</ul>
			</div>
			<div class="box bp">
				<div class="con" id="chart"></div>
				<a id="show_datadeatail" href="javascript:void(0)" class="ckmx">查看数据明细</a>
				<div class="sjmx hide">
                        <p>数据明细 <img src="${host.image_host}/close-btn.png" alt="" class="close_btn"></p>
                        <table class="sjmx_tab">
				            <tr>
				                <td width="32%">时间</td>
				                <td width="13%">访问次数</td>
				                <td width="13%">开户人数</td>
				                <td width="13%">绑卡人数</td>
				                <td width="13%">下单人数</td>
				                <td width="13%">下单金额</td>
				            </tr>
                        </table>
                        <div class="sjmx_tbl">
                            <table >
                            <#if dataDetailList?? && dataDetailList?size gt 0>
                            	<#list dataDetailList as d>
					            <tr>
					                <td width="32%">${d.createTime!}</td>
					                <td width="13%">${d.activateNum!0}</td>
					                <td width="13%">${d.openaccNum!0}</td>
					                <td width="13%">${d.bindcardNum!0}</td>
					                <td width="13%">${d.orderNum!0}</td>
					                <td width="13%">${d.orderAmount!0}</td>
					            </tr>
                            	</#list>
                            </#if>
				        </table>
                        </div>
                        <input id="export_data" value="导出明细" class="btn-style-a dcmx" type="button">
				    </div>
			</div>	
		</div>
	</div>
	 
<script type="text/javascript">

var jsonDataAll =[];
var dataJson =[];
var jsonDataAllCompare =[];
var dataJsonCompare =[];
var dataX24 = [];
var dataX24Compare = [];
$(function(){
	<#if jsonData??>
		dataJson = ${jsonData!};
	</#if>
	<#if jsonDataAll??>
		jsonDataAll = ${jsonDataAll!};
	</#if>
	
	<#if jsonDataAllCompare??>
		jsonDataAllCompare = ${jsonDataAllCompare!};
	</#if>
	<#if jsonDataCompare??>
		dataJsonCompare = ${jsonDataCompare!};
	</#if>
	
	<#if dataX24??>
		dataX24=${dataX24!};
	</#if>
	<#if dataX24Compare??>
		dataX24Compare=${dataX24Compare!};
	</#if>
	//默认展示访问次数
	var tag="activateNum";
	graphReq(_current_li_tag||tag,jsonDataAll,dataJson);
	//drawDataAll(jsonDataAll," ","activateNum");
	bindTabToggle(jsonDataAll,dataJson,'${uri.context_path!}');
	bindMxEvent();
	showDatadeatail();
	$("#time").html("${time!}");
	bindDownload('${uri.context_path!}');
	if(_current_li_tag){//刷新之后默认选中前一次tab
		$(".chartbox_tab ul#data_tab").find("li.current").removeClass("current");
		$(".chartbox_tab ul#data_tab").find("li[tag='"+_current_li_tag+"']").addClass("current");
	}
});
</script>
