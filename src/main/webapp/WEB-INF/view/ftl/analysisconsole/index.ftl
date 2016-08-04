<#import "/uri.ftl" as uri />
<#import "/console.ftl" as console />
<#import "/util.ftl" as util />
<!DOCTYPE HTML>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<meta name="Description" content="" />
	<meta name="Keywords" content="" />
	<title>访问渠道</title>
	<@uri.link href = ["/change.css","/reset.css","/style_hb.css"]/>
	<@uri.script src=
	[
		"/uaa_fund.js","/uaa_data_util.js",
		"/uaa-howbuy-website.js","/uaa-howbuy-website-chart.js"
	]/>
</head>
<body>

<!-- main begin-->
<div class="main">
	<form action="" method="post" id="form-content">
	<ul class="filter_a clearfix">
		<li>
			<select name="selectOption" id="selectOption" class="bar1">
				<option  value="1">昨天</option>
				<option  value="7">最近7天</option>
				<option  value="20" selected="selected">最近20天</option>
				<option  value="30">最近30天</option>
				<option  value="zdy">自定义</option>
			</select>
		</li>
		<li>
			<input type="text" value="请输入日期" class="laydate-icon" name="beginDate" id="beginDate">
			<script>
				function matchTime(){
					var startTime = $("#beginDate").val();
					var endTime = $("#endDate").val();
					var yesterday = laydate.now(-1);
					var now = laydate.now();
					;
					if(startTime==endTime){
						if(endTime == yesterday){
							$("#selectOption").val("1");//昨天
						}
					}else if(yesterday==endTime){//结束日期选择昨天
						var start = new Date(startTime).getTime();
						var end = new Date(endTime).getTime();
						var interval = (end-start)/(1000*60*60*24);//转换为天数
						interval+=1;
						$("#selectOption").find("option[value='"+interval+"']").prop("selected",true);
					}
				}
			
				$("#beginDate").val(laydate.now(-1));
				var start = {
				    elem: '#beginDate',
				    format: 'YYYY-MM-DD', // 分隔符可以任意定义，该例子表示只显示年月
				    max : laydate.now(-1),//设定最大日期为当前日期
				    istime: true,
	    			istoday: false,
				    choose: function(datas){ //选择日期	完毕的回调
				        $("#selectOption").val("zdy");
				          end.min = datas; //开始日选好后，重置结束日的最小日期
		         		  end.start = datas //将结束日的初始值设定为开始日
		         		  matchTime();
		         		  laydate(end);
				    }
				};
				
				var end = {
			    	elem: '#endDate',
				    format: 'YYYY-MM-DD', // 分隔符可以任意定义，该例子表示只显示年月
				    max : laydate.now(-1),//设定最大日期为当前日期
				    istime: true,
	    			istoday: false,
				    choose: function(datas){ //选择日期	完毕的回调
				        $("#selectOption").val("zdy");
				        start.max = datas; //结束日选好后，重置开始日的最大日期
				        matchTime();
				    }
				};
				laydate(start);
				$("#beginDate").val(laydate.now(-20));
			</script>
		</li>
		<li style="margin:0 5px;">至</li>
		<li><input type="text" value="请输入日期" class="laydate-icon" name="endDate" id="endDate"></li>
		<script>
			$("#endDate").val(laydate.now(-1));
			laydate(end);
		</script>
		
	</ul>
	
	<!-- 访问渠道 begin -->
	<div class="filter_b">
		<dl class="fwqd">
			<dt>访问渠道：</dt>
			<dd>
				<ul id="fwqd">
				<li><input type="button" value="确定" style="width: 90px;" class="btn-style-a" onclick="accessChannelSubmit()"></li>
				</ul>
			</dd>
		</dl>
		<dl class="mt10 hide other-qd-box">
			<dt>其他渠道：</dt>
			<dd>
				<ul>
					<li>
						<select name="other_channel" id="other_channel" style="margin-top:2px;padding: 0 0px;width: 100%;">
							<option value="">请选择</option>
						</select>
					</li>
					<li><input type="button" value="确定" style="width: 90px;" class="btn-style-a" onclick="accessChannelSubmit()"></li>
				</ul>
			</dd>
			
		</dl>
		<dl class="mt10 hide ssyq-box">
			<dt>搜索引擎：</dt>
			<dd>
				<ul id="ssyq"></ul>
			</dd>
		</dl>
		<dl class="mt10 hide tgqd-box">
			<dt>推广渠道</dt>
			<dd>
				<ul id="tgqd">
					<li style="width:140px" id="indexQueryRouteOne">
						<#include "../include/indexQueryRouteOne.ftl">
					</li>
					<li style="width:140px;" id="indexQueryRouteTwo">
						<#include "../include/indexQueryRouteTwo.ftl">
					</li>
					<li style="width:140px;" id="indexQueryRouteThree">
						<#include "../include/indexQueryRouteThree.ftl">
					</li>
					<li><input type="button" value="确定" style="width: 90px;" class="btn-style-a" onclick="accessChannelSubmit()"></li>
				</ul>
			</dd>
		</dl>
		</div>
	</form>
	<!-- 访问渠道 end -->
	
	
	<!-- 分布图/趋势图 begin -->
	<div class="chartbox" id="TrendGraph">
	<#include "../include/indexTrendGraph.ftl">
	</div>
	<!-- 分布图/趋势图 end -->
	
	
	<!-- 明细 begin -->
	<div class="detail">
		<div class="tab_list">
			<ul class="tab_menu clearfix">
				<li class="current" tag="channelDetailForm">渠道明细</li>
				<li tag="trendDetailFormFilter">趋势明细</li>
			</ul>
			
			
			<div class="tab_box">
				<div class="con" tag="channelDetailForm"><!-- 渠道明细 begin -->
					<form action="" method="post" id="channelDetailForm">
					<div class="detail_filter" id="channelDetailFormFilter">
						<dl>
							<dt>流量数据：</dt>
							<dd>
								<ul id="channelFlowData">
									<li style="width:72px;"><label><input type="checkbox" value=""  name="flowData" >全选</label></li>
									<li><label><input type="checkbox" value="enter" name="flowData"  tag="enter">进入次数</label></li>
									<li><label><input type="checkbox" value="pv" name="flowData"  tag="pv">PV</label></li>
									<li><label><input type="checkbox" value="uv" name="flowData"  tag="uv">UV</label></li>
									<li style="width:108px;"><label><input type="checkbox" value="validuv" name="flowData"  tag="validuv">有效UV</label></li>
									<li style="width:150px;"><label><input type="checkbox" value="gmuv" name="flowData"  tag="gmuv">公募基金档案页UV</label></li>
									<li style="width:128px;"><label><input type="checkbox" value="simuuv" name="flowData"  tag="simuuv">高端详情页UV</label></li>
								</ul>
							</dd>
						</dl>
						<dl>
							<dt>开户数据：</dt>
							<dd>
								<ul id="channelOpenData">
									<li style="width:72px;"><label><input type="checkbox" value="" name="tradeData" >全选</label></li>
									<li><label><input type="checkbox" value="drkh" name="tradeData"  tag="drkh">开户人数</label></li>
									<li><label><input type="checkbox" value="drbk" name="tradeData"  tag="drbk">绑卡人数</label></li>
								</ul>
							</dd>
						</dl>
						<dl>
							<dt>交易数据：</dt>
							<dd>
								<ul id="channelTradeData">
									<li style="width:72px;"><label><input type="checkbox" value="" name="amountData" >全选</label></li>
									<li><label><input type="checkbox" value="persons" name="amountData"  tag="persons">下单人数</label></li>
									<li><label><input type="checkbox" value="bills" name="amountData"  tag="bills">下单笔数</label></li>
									<li><label><input type="checkbox" value="amt" name="amountData"  tag="amt">下单金额</label></li>
									<li style="width:108px;"><label><input type="checkbox" value="xdzhl" name="amountData"   tag="xdzhl">下单转化率</label></li>
									<li style="width:100px;"><label><input type="checkbox" value="drxdcjrs" name="amountData"  tag="drxdcjrs">成交人数</label></li>
									<li style="width:50px;"><label></label></li>
									<li style="width:120px;"><label><input type="checkbox" value="drxdcjbs" name="amountData"  tag="drxdcjbs">成交笔数</label></li>
									<li style="width:100px;"><label><input type="checkbox" value="drxdcjje" name="amountData"  tag="drxdcjje">成交金额</label></li>
									<li style="width:108px;"><label><input type="checkbox" value="cjzhl" name="amountData"  tag="cjzhl">成交转化率</label></li>
								</ul>
							</dd>
						</dl>
					</div>
					<div class="tools">
						<input type="button" value="导出明细" class="btn-style-a" onclick="channelDetailOut()">
					</div>
					</form>
					<div class="det-datalist" id="ChannelDetail">
					<#include "../include/indexChannelDetail.ftl">
					</div>
				</div><!-- 渠道明细 end -->
				
				
				<div class="con hide" tag="trendDetailFormFilter"><!-- 趋势明细 begin -->
					<form action="" method="post">
					<div class="detail_filter" id="trendDetailFormFilter">
						<dl>
							<dt>流量数据：</dt>
							<dd>
								<ul id="trendFlowData">
									<li style="width:72px;"><label><input type="checkbox" value=""  name="flowData" >全选</label></li>
									<li><label><input type="checkbox" value="enter" name="flowData"  tag="enter">进入次数</label></li>
									<li><label><input type="checkbox" value="pv" name="flowData"  tag="pv">PV</label></li>
									<li><label><input type="checkbox" value="uv" name="flowData"  tag="uv">UV</label></li>
									<li style="width:108px;"><label><input type="checkbox" value="validuv" name="flowData"  tag="validuv">有效UV</label></li>
									<li style="width:150px;"><label><input type="checkbox" value="gmuv" name="flowData"  tag="gmuv">公募基金档案页UV</label></li>
									<li style="width:128px;"><label><input type="checkbox" value="simuuv" name="flowData"  tag="simuuv">高端详情页UV</label></li>
								</ul>
							</dd>
						</dl>
						<dl>
							<dt>开户数据：</dt>
							<dd>
								<ul id="trendOpenData">
									<li style="width:72px;"><label><input type="checkbox" value="" name="tradeData" >全选</label></li>
									<li><label><input type="checkbox" value="drkh" name="tradeData"  tag="drkh">开户人数</label></li>
									<li><label><input type="checkbox" value="drbk" name="tradeData"  tag="drbk">绑卡人数</label></li>
								</ul>
							</dd>
						</dl>
						<dl>
							<dt>交易数据：</dt>
							<dd>
								<ul id="trendTradeData"><li style="width:72px;"><label><input type="checkbox" value="" name="amountData" >全选</label></li>
									<li><label><input type="checkbox" value="persons" name="amountData"  tag="persons">下单人数</label></li>
									<li><label><input type="checkbox" value="bills" name="amountData"  tag="bills">下单笔数</label></li>
									<li><label><input type="checkbox" value="amt" name="amountData"  tag="amt">下单金额</label></li>
									<li style="width:108px;"><label><input type="checkbox" value="xdzhl" name="amountData"   tag="xdzhl">下单转化率</label></li>
									<li style="width:100px;"><label><input type="checkbox" value="drxdcjrs" name="amountData"  tag="drxdcjrs">成交人数</label></li>
									<li style="width:50px;"><label></label></li>
									<li style="width:120px;"><label><input type="checkbox" value="drxdcjbs" name="amountData"  tag="drxdcjbs">成交笔数</label></li>
									<li style="width:100px;"><label><input type="checkbox" value="drxdcjje" name="amountData"  tag="drxdcjje">成交金额</label></li>
									<li style="width:108px;"><label><input type="checkbox" value="cjzhl" name="amountData"  tag="cjzhl">成交转化率</label></li>
								</ul>
							</dd>
						</dl>
					</div>
					<div class="tools">
						<#--<input type="button" value="确定" class="btn-style-a"  onclick="TrendDetailSubmit()">-->
						<input type="button" value="导出明细" class="btn-style-a" onclick="trendDetailOut()">
					</div>
					</form>
					
					<div class="det-datalist" id = "TrendDetail">
						<#include "../include/indexTrendDetail.ftl">
					</div>
				</div><!-- 趋势明细 end -->
			</div>
		</div>						
	</div>
	<!-- 明细 end -->
	<#include "../include/hb_website_quota_explain.ftl">
</div>
<!-- main end-->
<script type="text/javascript">
maxpage = Math.ceil($("#total-rows").text()/pagerows); 
rootChannelJson = ${rootChannelJson!};
zeroChannelTagJson = ${zeroChannelTagJson!};
context_path = '${uri.context_path}';
_path = '${console.howbuywebsite}';
</script>

<@uri.script src=
[
	"/uaa-howbuy-website-ready.js"
]/>
</body>
</html>