<#import "/uri.ftl" as uri />
<#import "/util.ftl" as util />
<!DOCTYPE HTML>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<meta name="Description" content="" />
	<meta name="Keywords" content="" />
	<title>公募统计</title>
	<@uri.link href = ["/reset.css","/style.css","/change.css"]/>
	<@uri.script src=[
		"/uaa_data_util.js","/uaa_fund.js"
	]/>
</head>
<body>

<!-- main begin-->
<div class="main2">
	<ul class="filter_a clearfix">
		<li>
			<select name="selectOption" id="selectOption" class="bar1" style="font-family: 微软雅黑;">
				<option value="20">近20天</option>
				<option value="0">今天</option>
				<option value="1">昨天</option>
				<option value="3">近三天</option>
				<option value="7">近7天</option>
				<option value="30">近一个月</option>
				<option value="zdy">自定义</option>
			</select>
		</li>
		<li><input type="text" value="请输入日期" class="laydate-icon" id="startTime" ></li>
		<script>
	    	function matchTime(){
				var startTime = $("#startTime").val();
				var endTime = $("#endTime").val();
				var yesterday = laydate.now(-1);
				var now = laydate.now();
				if(startTime==endTime){
					if(endTime == yesterday){
						$("#selectOption").val("1");//昨天
					}else if(endTime == now){
						$("#selectOption").val("0");//今天
					}
				}else if(yesterday==endTime){//结束日期选择昨天
					var start = new Date(startTime).getTime();
					var end = new Date(endTime).getTime();
					var interval = (end-start)/(1000*60*60*24);//转换为天数
					$("#selectOption").find("option[value='"+interval+"']").prop("selected",true);
				}
			}
			var start = {
			elem: '#startTime',
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
			    elem: '#endTime',
			    format: 'YYYY-MM-DD', // 分隔符可以任意定义，该例子表示只显示年月
			    //min: startTime.max,
			    max : laydate.now(-1),//设定最大日期为当前日期
			    istime: true,
    			istoday: false,
			    choose: function(datas){ //选择日期	完毕的回调
			        $("#selectOption").val("zdy");
			        start.max = datas; //结束日选好后，重置开始日的最大日期
			        matchTime();
			        //laydate(start);
			    }
			};
			laydate(start);
			$("#startTime").val(laydate.now(-20));
		</script>
		<li style="margin:0 5px;">至</li>
		<li><input type="text" value="请输入日期" class="laydate-icon" id="endTime"></li>
		<script>
			laydate(end);
			$("#endTime").val(laydate.now(-1));
		</script>
	</ul>
	<!-- 访问渠道 begin -->
	<div class="filter_b" id="fundCondition">
		<dl class="fwqd">
			<dt>分析维度：</dt>
			<dd>
				<ul class="clearfix" id="fenxiweidu">
					<li class="current" para="analysisWd%1"><a href="javascript:void(0)" target="_self">开户渠道</a></li>
					<li para="analysisWd%2"><a href="javascript:void(0)" target="_self">交易渠道</a></li>
				</ul>
			</dd>
		</dl>
		
		<div class="fw">
			<div id="qudaoChoose"></div>
            										
            <dl class="mt10 fwqd2" id="jijinleixing">
                <dt>基金类型：</dt>
                <dd>
                    <ul class="clearfix">
                        <li class="current" para="fundType%-1"><a href="#" target="_self">全部</a><p style="display: none;">全部基金类型</p></li>
                        <li para="fundType%0"><a href="#" target="_self">股票型</a></li>
                        <li para="fundType%1"><a href="#" target="_self">混合型</a></li>
                        <li para="fundType%2"><a href="#" target="_self">债券型</a></li>
                        <li para="fundType%3"><a href="#" target="_self">货币型</a></li>
                        <li para="fundType%4"><a href="#" target="_self">QDII</a></li>
                        <li para="fundType%6"><a href="#" target="_self">结构型</a></li>
                        <li para="fundType%7"><a href="#" target="_self">公募专户</a></li>		
                        <li para="fundType%a"><a href="#" target="_self">储蓄罐</a></li>
                        <li para="fundType%b"><a href="#" target="_self">保险</a></li>
                        <li para="fundType%c"><a href="#" target="_self">创新产品</a></li>
                        <input type="button" value="确   定" class="btn-style-a" id="fundok">
                    </ul>
                </dd>
            </dl>	
		</div>
	</div>
	<!-- 访问渠道 end -->
	
	<!-- 公募开户渠道 begin -->
	<div class="chartbox hb-chart" id="gmGraph" align="center" style="min-width: 1300px">
		<#include "../include/fundGraph.ftl">
	</div>
	<!-- 公募开户渠道 end -->
	<!-- 明细 begin -->
	<div class="detail">
		<div class="tab_list1">
			<ul class="tab_menu clearfix">
				
				<li id="tab_qs" tag="tab_qs">趋势明细</li>
				<li id="tab_qd" tag="tab_qd" class="current">渠道明细</li>
				<li id="tab_fund" tag="tab_fund">基金类型明细</li>
			</ul>
			<div class="tab_box">
			
			    <!-- 趋势明细 begin -->
				<div class="con hide" id="trendDetailAll"  tag="tab_qs">
					<div class="detail_filter" id="OpenTrendNorm">
					 <table class="table1" id="trendDetailOpen">
					     <tr class="trendOpenNorm" id="trendOpenNorm" cst="trendOpenNorm">
					         <td class="w1">开户指标：</td>
					         <td><label><img src="${uri.context_path}/images/03.jpg" id="trendOpenNormImage"  style="display:none"> <input type="checkbox" name="" value="-1" cst="trendOpenNorm" class="detail_b"/>全选</label></td>
					        <!-- <td><label><input type="checkbox" name="" value="-1" cst="trendOpenNorm"/>全选</label></td>-->
					         <td><label><input type="checkbox" name="" value="drkh">开户人数</label></td>
							 <td><label><input type="checkbox" name="" value="drscbk">绑卡人数</label></td>
							 <td><label><input type="checkbox" name="" value="drscjq">鉴权人数</label></td>
							 <td><label><input type="checkbox" name="" value="drkhdrbk">当日开户绑卡人数</label></td>
							 <td><label><input type="checkbox" name="" value="drkhdrjq">当日开户鉴权人数</label></td>
							 <td><label><input type="checkbox" name="" value="drscyk">验卡人数</label></td>
							 <td><label><input type="checkbox" name="" value="drkhdryk">当日开户验卡人数</label></td>
					     </tr>
					     <tr cst="trendOpenNorm"  class="trendOpenNorm">
					         <td></td>
					         <td></td>
					         <td><label><input type="checkbox" name="" value="drkhbkl">当日开户绑卡率</label></td>
							 <td><label><input type="checkbox" name="" value="drkhjql">当日开户鉴权率</label></td>
							 <td><label><input type="checkbox" name="" value="drkhykl">当日开户验卡率</label></td>
					     </tr>
					     <tr class="trendOpenOtherNorm" cst="trendOpenOtherNorm">
					         <td class="w1">交易指标：</td>
					         <td><label><img src="${uri.context_path}/images/03.jpg" id="trendOpenOtherNormImage"  style="display:none"> <input type="checkbox" name="" value="-1" cst="trendOpenOtherNorm" class="detail_b"/>全选</label></td>
					         <!--<td><label><input type="checkbox" name="" value="-1" cst="trendOpenOtherNorm">全选</label></td>-->
					         <td><label><input type="checkbox" name="" value="drxdrs">下单人数</label></td>
							 <td><label><input type="checkbox" name="" value="drxdje">下单金额</label></td>
							 <td><label><input type="checkbox" name="" value="drxdbs">下单笔数</label></td>
							 <td><label><input type="checkbox" name="" value="rjxdje">人均下单金额</label></td>
							 <td><label><input type="checkbox" name="" value="drzfrs">支付人数</label></td>
							 <td><label><input type="checkbox" name="" value="drzfje">支付金额</label></td>
							 <td><label><input type="checkbox" name="" value="drzfbs">支付笔数</label></td>
					     </tr>
					     <tr class="trendOpenOtherNorm" cst="trendOpenOtherNorm">
					     	 <td></td>
					     	 <td></td>
					     	 <td><label><input type="checkbox" name="" value="rjzfje">人均支付金额</label></td>
					     	 <td><label><input type="checkbox" name="" value="drqrjycjrs">成交人数</label></td>
							 <td><label><input type="checkbox" name="" value="drqrjycjje">成交金额</label></td>
							 <td><label><input type="checkbox" name="" value="drqrjycjbs">成交笔数</label></td>
							 <td><label><input type="checkbox" name="" value="rjcjje">人均成交金额</label></td>
					     </tr>
					     <tr class="trendOpenOtherNorm" cst="trendOpenOtherNorm">
					     	 <td></td>
					     	 <td></td>
					     	 <td><label><input type="checkbox" name="" value="drkhdrjyje">当日开户当日交易金额</label></td>
							 <td><label><input type="checkbox" name="" value="drkhdrjyrs">当日开户当日交易人数</label></td>
							 <td><label><input type="checkbox" name="" value="drkhjyl">当日开户交易率</label></td>
 							 <td><label><input type="checkbox" name="" value="drxzjyje">当日新增交易金额</label></td>
							 <td><label><input type="checkbox" name="" value="drxzjyrs">当日新增交易人数</label></td>
					     </tr>
					 </table>
					</div>
					<div class="detail_filter" id="TradeTrendNorm" style="display:none;">
					 <table class="table1" id="trendDetailTrade">
					     <tr id="trendTradeNorm" class="trendTradeNorm" cst="trendTradeNorm">
					         <td class="w1">交易指标：</td>
				             <td><label><img src="${uri.context_path}/images/03.jpg" id="trendTradeNormImage"  style="display:none"> <input type="checkbox" name="" value="-1" cst="trendTradeNorm" class="detail_b"/>全选</label></td>
					         <!--<td><label><input type="checkbox" name="" value="-1" cst="trendTradeNorm"/>全选</label></td>-->
					         <td><label><input type="checkbox" name="" value="drxdrs">下单人数</label></td>
					         <td><label><input type="checkbox" name="" value="drxdje">下单金额</label></td>
					         <td><label><input type="checkbox" name="" value="drxdbs">下单笔数</label></td>
					         <td><label><input type="checkbox" name="" value="rjxdje">人均下单金额</label></td>
					         <td><label><input type="checkbox" name="" value="drzfrs">支付人数</label></td>
					         <td><label><input type="checkbox" name="" value="drzfje">支付金额</label></td>
					         <td><label><input type="checkbox" name="" value="drzfbs">支付笔数</label></td>
					     </tr>
					     <tr id="trendTradeNorm" class="trendTradeNorm" cst="trendTradeNorm">
					         <td class="w1"></td>
					         <td></td>
					         <!--<td><label><input type="checkbox" name="" value="-1" cst="trendTradeOtherNorm">全选</label></td>-->
					          <td><label><input type="checkbox" name="" value="rjzfje">人均支付金额</label></td>
					          <td><label><input type="checkbox" name="" value="drqrjycjrs">成交人数</label></td>
					         <td><label><input type="checkbox" name="" value="drqrjycjje">成交金额</label></td>
					         <td><label><input type="checkbox" name="" value="drqrjycjbs">成交笔数</label></td>
					         <td><label><input type="checkbox" name="" value="rjcjje">人均成交金额</label></td>
					         <td><label><input type="checkbox" name="" value="drxzjyje">当日新增交易金额</label></td>
					         <td><label><input type="checkbox" name="" value="drxzjyrs">当日新增交易人数</label></td>
					     </tr>
					 </table>
					</div>
					<div class="tools">
						<input type="button" value="确定" class="btn-style-a" id="trendDetailOk">
						<input type="button" value="导出明细" class="btn-style-a" id="trendDetailExport">
					</div>
					<div id="showTrend" class="showLoading" style="display:none;margin-left:500px;margin-top:-200px;opacity:0.5;z-index:200;position:absolute;">
						${util.loadImg}
					</div>
					<div class="det-datalist" id="fundTrendDetail" align="center">
						<#include "../include/fundTrendDetail.ftl">
					</div>
					<div id="showPageLoad"class="showLoading" style="display:none;margin-left:500px;margin-top:-100px;opacity:0.5;z-index:200;position:absolute;">
						${util.loadImg}
					</div>
				</div><!-- 趋势明细 end -->
				
				
				<!-- 渠道明细 begin -->
				<div class="con" id="channelDetailAll" tag="tab_qd">
					<div class="detail_filter fund_detail_filter" id="OpenChannelNorm">
					 <table class="table1" id="channelDetailOpen">
					     <tr id="channelOpenNorm" cst="channelOpenNorm" class="channelOpenNorm">
					         <td class="w1">开户指标：</td>
					         <td><label><img src="${uri.context_path}/images/03.jpg" id="channelOpenNormImage"  style="display:none"> <input type="checkbox" name="" value="-1" cst="channelOpenNorm" class="detail_b"/>全选</label></td>
					         <td><label><input type="checkbox" name="" value="drkh">开户人数</label></td>
							 <td><label><input type="checkbox" name="" value="drscbk">绑卡人数</label></td>
							 <td><label><input type="checkbox" name="" value="drscjq">鉴权人数</label></td>
							 <td><label><input type="checkbox" name="" value="drkhdrbk">当日开户绑卡人数</label></td>
							 <td><label><input type="checkbox" name="" value="drkhdrjq">当日开户鉴权人数</label></td>
							 <td><label><input type="checkbox" name="" value="drscyk">验卡人数</label></td>
							 <td><label><input type="checkbox" name="" value="drkhdryk">当日开户验卡人数</label></td>
					     </tr>
					     <tr cst="channelOpenNorm"  class="channelOpenNorm">
					         <td></td>
					         <td></td>
					         <td><label><input type="checkbox" name="" value="drkhbkl">当日开户绑卡率</label></td>
							 <td><label><input type="checkbox" name="" value="drkhjql">当日开户鉴权率</label></td>
							 <td><label><input type="checkbox" name="" value="drkhykl">当日开户验卡率</label></td>
					     </tr>
					     <tr class="channelOpenOtherNorm" cst="channelOpenOtherNorm">
					         <td class="w1">交易指标：</td>
					         <td><label><img src="${uri.context_path}/images/03.jpg" id="channelOpenOtherNormImage"  style="display:none"> <input type="checkbox" name="" value="-1" cst="channelOpenOtherNorm" class="detail_b"/>全选</label></td>
					         <!--<td><label><input type="checkbox" name="" value="-1" cst="channelOpenOtherNorm">全选</label></td>-->
					          <td><label><input type="checkbox" name="" value="drxdrs">下单人数</label></td>
							 <td><label><input type="checkbox" name="" value="drxdje">下单金额</label></td>
							 <td><label><input type="checkbox" name="" value="drxdbs">下单笔数</label></td>
							 <td><label><input type="checkbox" name="" value="rjxdje">人均下单金额</label></td>
							 <td><label><input type="checkbox" name="" value="drzfrs">支付人数</label></td>
							 <td><label><input type="checkbox" name="" value="drzfje">支付金额</label></td>
							 <td><label><input type="checkbox" name="" value="drzfbs">支付笔数</label></td>
					     </tr>
					     <tr class="channelOpenOtherNorm" cst="channelOpenOtherNorm">
					     	 <td></td>
					     	 <td></td>
					     	 <td><label><input type="checkbox" name="" value="rjzfje">人均支付金额</label></td>
					     	 <td><label><input type="checkbox" name="" value="drqrjycjrs">成交人数</label></td>
							 <td><label><input type="checkbox" name="" value="drqrjycjje">成交金额</label></td>
							 <td><label><input type="checkbox" name="" value="drqrjycjbs">成交笔数</label></td>
							 <td><label><input type="checkbox" name="" value="rjcjje">人均成交金额</label></td>
					     </tr>
					     <tr class="channelOpenOtherNorm" cst="channelOpenOtherNorm">
					     	 <td></td>
					     	 <td></td>
					     	  <td><label><input type="checkbox" name="" value="drkhdrjyje">当日开户当日交易金额</label></td>
							 <td><label><input type="checkbox" name="" value="drkhdrjyrs">当日开户当日交易人数</label></td>
							 <td><label><input type="checkbox" name="" value="drkhjyl">当日开户交易率</label></td>
 							 <td><label><input type="checkbox" name="" value="drxzjyje">当日新增交易金额</label></td>
							 <td><label><input type="checkbox" name="" value="drxzjyrs">当日新增交易人数</label></td>
					     </tr>
					 </table>
					</div>
					<div class="detail_filter fund_detail_filter" id="TradeChannelNorm" style="display:none;">
					 <table class="table1" id="channelDetailTrade">
					     <tr id="channelTradeNorm" cst="channelTradeNorm">
					         <td class="w1">交易指标：</td>
							<td><label><img src="${uri.context_path}/images/03.jpg" id="channelTradeNormImage"  style="display:none"> <input type="checkbox" name="" value="-1" cst="channelTradeNorm" class="detail_b"/>全选</label></td>
					         <!--<td><label><input type="checkbox" name="" value="-1" cst="channelTradeNorm"/>全选</label></td>-->
					         <td><label><input type="checkbox" name="" value="drxdrs">下单人数</label></td>
					         <td><label><input type="checkbox" name="" value="drxdje">下单金额</label></td>
					         <td><label><input type="checkbox" name="" value="drxdbs">下单笔数</label></td>
					         <td><label><input type="checkbox" name="" value="rjxdje">人均下单金额</label></td>
					         <td><label><input type="checkbox" name="" value="drzfrs">支付人数</label></td>
					         <td><label><input type="checkbox" name="" value="drzfje">支付金额</label></td>
					         <td><label><input type="checkbox" name="" value="drzfbs">支付笔数</label></td>
					     </tr>
					     <tr id="channelTradeNorm" class="channelTradeOtherNorm" cst ="channelTradeOtherNorm">
					         <td></td>
					         <td></td>
					         <!--<td><label><input type="checkbox" name="" value="-1" cst="channelTradeOtherNorm">全选</label></td>-->
					          <td><label><input type="checkbox" name="" value="rjzfje">人均支付金额</label></td>
					          <td><label><input type="checkbox" name="" value="drqrjycjrs">成交人数</label></td>
					         <td><label><input type="checkbox" name="" value="drqrjycjje">成交金额</label></td>
					         <td><label><input type="checkbox" name="" value="drqrjycjbs">成交笔数</label></td>
					         <td><label><input type="checkbox" name="" value="rjcjje">人均成交金额</label></td>
					         <td><label><input type="checkbox" name="" value="drxzjyje">当日新增交易金额</label></td>
					         <td><label><input type="checkbox" name="" value="drxzjyrs">当日新增交易人数</label></td>
					     </tr>
					 </table>
					</div>
					
					<div class="tools">
						<input type="button" value="确定" class="btn-style-a" id="channelDetailOk">
						<input type="button" value="导出明细" class="btn-style-a" id="channelDetailExport">
					</div>
					<div id="showChannel" class="showLoading" style="display:none;margin-left:500px;margin-top:-200px;opacity:0.5;z-index:200;position:absolute;">
						${util.loadImg}
					</div>
					<div class="det-datalist" id="fundChannelDetail" align="center">
						<#include "../include/fundChannelDetail.ftl">
					</div>
				</div>
				<!-- 渠道明细 end -->
				
				<!-- 基金类型明细 begin -->
				<div class="con hide" id="fundDetailAll" tag="tab_fund">
					<div class="detail_filter" id="OpenFundNorm">
					 <table class="table1" id="fundDetailOpen">
					     <tr id="fundOpenNorm" class="fundOpenNorm" cst="fundOpenNorm">
					         <td class="w1">交易指标：</td>
							<td><label><img src="${uri.context_path}/images/03.jpg" id="fundOpenNormImage"  style="display:none"> <input type="checkbox" name="" value="-1" cst="fundOpenNorm" class="detail_b"/>全选</label></td>
					         <!--<td><label><input type="checkbox" name="" value="-1" cst="fundOpenNorm"/>全选</label></td>-->
					         <td><label><input type="checkbox" name="" value="drxdrs">下单人数</label></td>
					         <td><label><input type="checkbox" name="" value="drxdje">下单金额</label></td>
					         <td><label><input type="checkbox" name="" value="drxdbs">下单笔数</label></td>
					         <td><label><input type="checkbox" name="" value="rjxdje">人均下单金额</label></td>
					         <td><label><input type="checkbox" name="" value="drzfrs">支付人数</label></td>
					         <td><label><input type="checkbox" name="" value="drzfje">支付金额</label></td>
					         <td><label><input type="checkbox" name="" value="drzfbs">支付笔数</label></td>
					         
					     </tr>
					     <tr class="fundOpenNorm" cst="fundOpenNorm">
					         <td></td>
							 <td></td>
							 <td><label><input type="checkbox" name="" value="rjzfje">人均支付金额</label></td>
					         <td><label><input type="checkbox" name="" value="drqrjycjrs">成交人数</label></td>
					         <td><label><input type="checkbox" name="" value="drqrjycjje">成交金额</label></td>
					         <td><label><input type="checkbox" name="" value="drqrjycjbs">成交笔数</label></td>
					         <td><label><input type="checkbox" name="" value="rjcjje">人均成交金额</label></td>
					         <td><label><input type="checkbox" name="" value="drkhdrjyrs">当日开户当日交易人数</label></td>
					         <td><label><input type="checkbox" name="" value="drkhdrjyje">当日开户当日交易金额</label></td>
					     </tr>
					     <tr class="fundOpenNorm" cst="fundOpenNorm">
					         <td></td>
							 <td></td>
					         <td><label><input type="checkbox" name="" value="drxzjyrs">当日新增交易人数</label></td>
					         <td><label><input type="checkbox" name="" value="drxzjyje">当日新增交易金额</label></td>
					     </tr>
					 </table>
					</div>
					<div class="detail_filter" id="TradeFundNorm" style="display:none;">	
					 <table class="table1" id="fundDetailTrade">
					     <tr id="fundTradeNorm" cst="fundTradeNorm" class="fundTradeNorm">
					         <td class="w1">交易指标：</td>
					         <td><label><img src="${uri.context_path}/images/03.jpg" id="fundTradeNormImage"  style="display:none"> <input type="checkbox" name="" value="-1" cst="fundTradeNorm" class="detail_b"/>全选</label></td>
					        <!-- <td><label><input type="checkbox" name="" value="-1" cst="fundTradeNorm"/>全选</label></td>-->
					         <td><label><input type="checkbox" name="" value="drxdrs">下单人数</label></td>
					         <td><label><input type="checkbox" name="" value="drxdje">下单金额</label></td>
					         <td><label><input type="checkbox" name="" value="drxdbs">下单笔数</label></td>
					         <td><label><input type="checkbox" name="" value="rjxdje">人均下单金额</label></td>
					         <td><label><input type="checkbox" name="" value="drzfrs">支付人数</label></td>
					         <td><label><input type="checkbox" name="" value="drzfje">支付金额</label></td>
					         <td><label><input type="checkbox" name="" value="drzfbs">支付笔数</label></td>
					     </tr>
					     <tr class="fundTradeNorm" cst="fundTradeNorm">
					         <td></td>
					         <td><label><img src="${uri.context_path}/images/03.jpg" id="fundTradeOtherNormImage"  style="display:none"> <input type="checkbox" name="" value="-1" cst="fundTradeOtherNorm" class="detail_b"/>全选</label></td>
					         <!--<td><label><input type="checkbox" name="" value="-1" cst="fundTradeOtherNorm">全选</label></td>-->
					         <td><label><input type="checkbox" name="" value="rjzfje">人均支付金额</label></td>
					         <td><label><input type="checkbox" name="" value="drqrjycjrs">成交人数</label></td>
					         <td><label><input type="checkbox" name="" value="drqrjycjje">成交金额</label></td>
					         <td><label><input type="checkbox" name="" value="drqrjycjbs">成交笔数</label></td>
					         <td><label><input type="checkbox" name="" value="rjcjje">人均成交金额</label></td>
					         <td><label><input type="checkbox" name="" value="drxzjyrs">当日新增交易人数</label></td>
					         <td><label><input type="checkbox" name="" value="drxzjyje">当日新增交易金额</label></td>
					     </tr>
					 </table>

					</div>
					<div class="tools">
						<input type="button" value="确定" class="btn-style-a" id="fundDetailOk">
						<input type="button" value="导出明细" class="btn-style-a" id="fundDetailExport">
					</div>
					<div class="det-datalist" id="fundTypeDetail" align="center">
						<#include "../include/fundTypeDetail.ftl">
					</div>
				</div><!-- 基金类型明细 end -->
			</div>
		</div>						
	</div>
	<!-- 明细 end -->
	<#include "../include/quota_explain.ftl">
	
	
</div>
<!-- main end-->
	
<script type="text/javascript">

$(function(){
	//动态加载渠道选择
	json = ${gmtjqd};
	var all = loadChannel(json);
	$("#qudaoChoose").html(all);

	var fwqd = 0;
	//访问渠道
    $(".fwqd").find('li').click(function(){
     	var _this = $(this);
        var _index = _this.index() ;
		if(_index ==fwqd){
			return;
		}        
        fwqd = _this.index();
        _this.parent().find('li').removeClass('current').data('status',0);
        _this.addClass("current").data('status',1);
        if(_index === 0){
        	$(".TYXC0F001").show();
        	$(".QJW00C001").show();
        }
        else{
        	$(".TYXC0F001").hide();
        	$(".QJW00C001").hide();
        }
        $("#qudaoChoose li").removeClass("current");
        $("#qudaoChoose .khjg").find("li").eq(0).addClass("current");
        ;
        if(_index==0){
         	$("#qudaoChoose .khjg").find("dt").eq(0).html("开户机构:");
         	
         	$("#qudaoChoose dl.Inst dt:not([id=pingtai])").html("开户网点:");
         	$("#pingtai").html("开户平台:");
         	$("#qudaoChoose select[name='cooperateType']").find("option:eq(0)").text("开户网点(渠道)");
        }else if(_index==1){
        	$("#qudaoChoose .khjg").find("dt").eq(0).html("分销机构:");
        	$("#pingtai").html("交易平台:");
        	$("#qudaoChoose dl.Inst dt:not([id=pingtai])").html("交易网点：");
        	$("#qudaoChoose select[name='cooperateType']").find("option:eq(0)").text("交易网点");
        }
        
        $("#qudaoChoose dl.Inst").hide();
		$("#qudaoChoose dl.Platform").hide();
        $("#jijinleixing ul.clearfix").find("li").removeClass("current");
        $("#jijinleixing ul.clearfix").find("li").eq(0).addClass("current");
    });
    
    //基金类型选择
    $(".fwqd2").find('li').click(function(){
        var _this = $(this);
        _this.parent().find('li').removeClass('current');
        _this.addClass("current");
        return false;
    }); 
    //
    $("#qudaoChoose dl.Inst").hide();
	$("#qudaoChoose dl.Platform").hide();
	$("#openInstHB000A001").find("li").eq(1).hide();

	$("#qudaoChoose li").click(function(){
		var arr = $(this).attr("para").split("%");
		var id = arr[0];
		for(var i = 1;i<arr.length;i++)
		{
			id = id + arr[i];
		}
		//$(this).addClass("current");
		var p = $(this).parent().parent().parent();
		$("#qudaoChoose select").hide();
		$("#qudaoChoose select").each(function(){
				$(this).val("-1");
			})
		$("#qudaoChoose select").hide();
		if(p.hasClass("khjg")){
			$("#qudaoChoose li").removeClass("current");
			$(this).addClass("current");
			$("#qudaoChoose dl.Inst").hide();
			$("#qudaoChoose dl.Platform").hide();
			
			$("#"+id).show();
			$("#"+id).find("li").eq(0).addClass("current");
		}
		if(p.hasClass("Inst")){
			$("#qudaoChoose .Inst li").removeClass("current");
			$("#qudaoChoose .Platform li").removeClass("current");
			$("#qudaoChoose dl.Platform").hide();
			
			$(this).addClass("current");
			$("#"+id).show();
			$("#"+id).find("li").eq(0).addClass("current");
		}
		if(p.hasClass("Platform")){
			$(this).parent().find("li").removeClass("current");
			$("#qudaoChoose .Platform li").removeClass("current");
			$(this).addClass("current");
			$("#"+id).show();
		}
	});


	//时间选择
	var start = $("#startTime");
	var end = $("#endTime");
	$("#selectOption").change(function(){
		var opt = $(this).val();
		if(opt === "zdy"){
			$("#startTime").val("");
			$("#endTime").val("");
		}
		else if(opt === "0"){
			$("#startTime").val(laydate.now());
			$("#endTime").val(laydate.now());
		}
		else{
			$("#startTime").val(laydate.now(0-opt));
			$("#endTime").val(laydate.now(-1));
		}
	});
	
	
	//渠道明细全选
	$(".channelOpenNorm td:eq(1) input").click(function(){
		$(".channelOpenNorm input").prop("checked",$(this).prop("checked"));
	});
	$(".channelOpenOtherNorm td:eq(1) input").click(function(){
		$(".channelOpenOtherNorm input").prop("checked",$(this).prop("checked"));
	});
	$(".channelTradeNorm td:eq(1) input").click(function(){
		$(".channelTradeNorm input").prop("checked",$(this).prop("checked"));
	});
	$(".channelTradeOtherNorm td:eq(1) input").click(function(){
		$(".channelTradeOtherNorm input").prop("checked",$(this).prop("checked"));
	});
	//趋势明细全选
	$(".trendOpenNorm td:eq(1) input").click(function(){
		$(".trendOpenNorm input").prop("checked",$(this).prop("checked"));
	});
	$(".trendOpenOtherNorm td:eq(1) input").click(function(){
		$(".trendOpenOtherNorm input").prop("checked",$(this).prop("checked"));
	});
	$(".trendTradeNorm td:eq(1) input").click(function(){
		$(".trendTradeNorm input").prop("checked",$(this).prop("checked"));
	});
	$(".trendTradeOtherNorm td:eq(1) input").click(function(){
		$(".trendTradeOtherNorm input").prop("checked",$(this).prop("checked"));
	});
	//基金类型明细全选
	$(".fundOpenNorm td:eq(1) input").click(function(){
		$(".fundOpenNorm input").prop("checked",$(this).prop("checked"));
	});
	$(".fundOpenOtherNorm td:eq(1) input").click(function(){
		$(".fundOpenOtherNorm input").prop("checked",$(this).prop("checked"));
	});
	$(".fundTradeNorm td:eq(1) input").click(function(){
		$(".fundTradeNorm input").prop("checked",$(this).prop("checked"));
	});
	$(".fundTradeOtherNorm td:eq(1) input").click(function(){
		$(".fundTradeOtherNorm input").prop("checked",$(this).prop("checked"));
	});


	$(".det-datalist table tbody tr").hover(function(){
		$(this).css({background:"#eff4f9"})
	},function(){
		$(this).css({background:""})
	});
	
	$("#channelOpenNorm input").click(function(){
		changeCheckBox("#channelOpenNorm","#channelOpenNormImage");
	});
	
	$(".channelOpenOtherNorm input").click(function(){
		changeCheckBox(".channelOpenOtherNorm","#channelOpenOtherNormImage");
	});

	
	$(".channelTradeNorm input").click(function(){
		changeCheckBox(".channelTradeNorm","#channelTradeNormImage");
	});
	
	$(".channelTradeOtherNorm input").click(function(){
		changeCheckBox(".channelTradeOtherNorm","#channelTradeOtherNormImage");
	});
	
	$(".trendOpenNorm input").click(function(){
		changeCheckBox(".trendOpenNorm","#trendOpenNormImage");
	});

	$(".trendOpenOtherNorm input").click(function(){
		changeCheckBox(".trendOpenOtherNorm","#trendOpenOtherNormImage");	
	});
	
	$(".trendTradeNorm input").click(function(){
		changeCheckBox(".trendTradeNorm","#trendTradeNormImage");
	});
	
	$(".trendTradeOtherNorm input").click(function(){
		changeCheckBox(".trendTradeOtherNorm","#trendTradeOtherNormImage");
	});
	
	$("#fundOpenNorm input").click(function(){
		changeCheckBox("#fundOpenNorm","#fundOpenNormImage");
	});
	
	$(".fundOpenOtherNorm input").click(function(){
		changeCheckBox(".fundOpenOtherNorm","#fundOpenOtherNormImage");
	});
	
	$(".fundTradeNorm input").click(function(){
		changeCheckBox("fundTradeNorm","#fundTradeNormImage");
	});

	$(".fundTradeOtherNorm input").click(function(){
		changeCheckBox(".fundTradeOtherNorm","#fundTradeOtherNormImage");
	});
	//条件选择确认
	$("#fundok").click(function(){
		//加会话判断
		syncSession();
		var out = $("#session_out").val();
		if(eval(out))return;
		var start = $("#startTime").val();
		var end = $("#endTime").val();
		if(start === "" || end ==="" || start > end){
			alert("请正确选择时间！！！");
			return false;
		}
		params="startDate="+start+"&endDate="+end;
		
		var fxwd = $("#fenxiweidu li.current:eq(0)");
		var farr=fxwd.attr("para").split("%");
		params = params + "&" + farr[0] + "=" + farr[1];
			
		var paraval = new Array(5);
		var openInst = null;//当前选择开户机构
		var paramMap = new HashMap();
		$("#qudaoChoose li.current").each(function(i,obj){
			var para = $(obj).attr("para"); 
			var arr=para.split("%");
			var len = arr.length;
			if(len>=2){
				var pType = arr[0];
				var val = arr[len-1];
				if(pType=="openInst"){
					openInst = val;
				}
				paramMap.put(pType,val);
			}
		});
		var max=paraname.length;
		//非好买机构的参数设置
		if("-1"!=openInst && openInst!=HB){
			paramMap.put(paraname[2],"A");//非-1的值
			paramMap.put(paraname[3],"A");//非-1的值
		}
		for(var i = 1; i < max; i++)
		{
		 	var pType=paraname[i];
			var paraval = paramMap.get(pType);
			if(null!=paraval && ""!=paraval){
				params = params + "&" + pType+ "=" + paraval;
			}
		}
		
		var jjlx = $("#jijinleixing li.current").attr("para").split("%");
		var khjg = $(".khjg li.current").attr("para").split("%");
		var jjlxLength = jjlx.length;
		var khjgLength = khjg.length;
		
		if(jjlx[0] == "fundType" && khjg[0] == "openInst" ){
			if(jjlx[jjlxLength-1] == "-1" && khjg[khjgLength-1] == -1){
				$("#tab_qd").click();	
			}else{
				$("#tab_qs").click();
			}
		}
		 

		//控制tab页 基金类型显示与否，当选择到具体的基金类型时，不显示tab的基金类型明细
		$("#jijinleixing li.current").each(function () {
			var arr=$(this).attr("para").split("%");
			var len = arr.length;
			params = params + "&" + arr[0] + "=" + arr[1];
			
			if(arr[0] == "fundType")
			{
				if(arr[len-1] == "-1" ){
					$("#tab_fund").removeClass("hide");
					if($("#tab_fund").hasClass("current")){
						$("#fundDetailAll").removeClass("hide");
					}
				}
				else{
					$("#tab_fund").addClass("hide");
					$("#fundDetailAll").addClass("hide");
				}
			}
		});
		var weidu = farr[1];
		if(weidu === "1"){
			$("#OpenChannelNorm").show();
			$("#TradeChannelNorm").hide();
			$("#TradeChannelNorm input").prop("checked",false);
			
			$("#OpenTrendNorm").show();
			$("#TradeTrendNorm").hide();
			$("#TradeTrendNorm input").prop("checked",false);
			
			$("#OpenFundNorm").show();
			$("#TradeFundNorm").hide();
			$("#TradeFundNorm input").prop("checked",false);
		}
		else if(weidu === "2"){
			$("#TradeChannelNorm").show();
			$("#OpenChannelNorm").hide();
			$("#OpenChannelNorm input").prop("checked",false);
			
			$("#TradeTrendNorm").show();
			$("#OpenTrendNorm").hide();
			$("#OpenTrendNorm input").prop("checked",false);
			
			$("#TradeFundNorm").show();
			$("#OpenFundNorm").hide();
			$("#OpenFundNorm input").prop("checked",false);
		}
		$("#qudaoChoose select").each(function(){
			var it = $(this).val();
			if(it !== "-1"){
				params = params + "&openWangDian=" + it;
			}
		});
		
		ajaxRequest(
		{
			url:'${uri.context_path}/auth/fund/graph.htm',
	        postMethod:'POST',
	        params:params,
	        session_out:false,
	        callback:function(data){
	        	$('#gmGraph').html(data);
	        	$("#fundok").removeAttr("disabled");
	        }
        });
        $('#gmGraph').html("${util.loadImg}");
        $("#fundok").attr("disabled","disabled");
		
		$("#channelOpenNormImage").hide();
		$("#channelOpenOtherNormImage").hide();
		$("#channelTradeNormImage").hide();
		$("#channelTradeOtherNormImage").hide();
		$("#trendOpenNormImage").hide();
		$("#trendOpenOtherNormImage").hide();
		$("#trendTradeNormImage").hide();
		$("#trendTradeOtherNormImage").hide();
		$("#fundOpenNormImage").hide();
		$("#fundOpenOtherNormImage").hide();
		$("#fundTradeNormImage").hide();
		$("#fundTradeOtherNormImage").hide();
        //触发全选明细
        selectQuato();
        $("#channelDetailOk").click();
		$("#trendDetailOk").click();
		$("#fundDetailOk").click();
		
	});
	
	
	
	//渠道明细
	$("#channelDetailOk").click(function(){
		lastClickChannel = "";
		lastChannelOrder = "";

		var detailParams = detailChecked("#channelDetailAll","#channelDetailOpen input","#channelDetailTrade input");
		if(detailParams == false){
			return false;
		}
		var wd = $("#fenxiweidu li.current").index()+1;
		var orderby = "drkh";//开户渠道默认按开户人数降序排列，交易渠道按下单人数降序排列
		if(wd=="2"){
			orderby = "drxdrs";
		}
		
		var detailParams = detailChecked("#channelDetailAll","#channelDetailOpen input","#channelDetailTrade input");
		if(detailParams == false){
			return false;
		}
		detailParams = detailParams + "&orderBy="+orderby+"&order=desc";
		
		var reqObj=
		{
			url:'${uri.context_path}/auth/fund/channelDetail.htm',
            postMethod:'POST',
            params:params + '&' + detailParams,
            session_out:false,
            callback:function(data){
		       /*$("div[name='oDivL_fundChannelDetailTab']").each(function(i,obj){
		        	$(obj).hide();
		        	$(obj).remove();
		        });*/
		        
            	$('#fundChannelDetail').html(data);
		        $("#channelDetailOk").removeAttr("disabled");
		        $("#fundChannelDetail").scrollLeft(0);
		        //frozenChannelTable();
        	}
		};
		ajaxRequest(reqObj);
		$('#fundChannelDetail').html("${util.loadImg}");
        $("#channelDetailOk").attr("disabled","disabled");
	});
	//渠道明细导出
	$("#channelDetailExport").click(function(){
		var detailParams = detailChecked("#channelDetailAll","#channelDetailOpen input","#channelDetailTrade input");
		if(detailParams == "")
			return false;
		var wdname = $("#qudaoChoose dl.Inst:visible li.current a").text();
		detailParams+="&wdname="+wdname;
		openBuildUrl('/auth/fund/channelDetailExport.htm',detailParams);
	})
	
	
	

	//趋势明细
	$("#trendDetailOk").click(function () {
		orderby="stat_dt";
		var order="desc";
		lastClickTrend = "" ;
		lastTrendOrder = "" ;
		var detailParams = detailChecked("#trendDetailAll","#trendDetailOpen input","#trendDetailTrade input");
		if(detailParams == false)
			return false;
		detailParams = detailParams + "&orderBy="+orderby+"&pageRows="+pagerows+"&curPage=1&order="+order;
		if(params == ""){
			alert("请确定分析维度！");
			return false;
		}
		else
		ajaxRequest({
			url:'${uri.context_path}/auth/fund/trendDetail.htm',
            postMethod:'POST',
            params:params+"&"+detailParams,
            session_out:false,
            callback:function(data){
            	//hideF("trendDetailTable");
            	$('#fundTrendDetail').html(data);
		        $("#trendDetailOk").removeAttr("disabled");
            	maxpage = Math.ceil($("#total-rows").text()/pagerows); 
            	if(maxpage > 1){
            		$("#nextpage").addClass("active");
            	}
            	//frozenTrendTable();
        	}
		});
		
		$('#fundTrendDetail').html("${util.loadImg}");
        $("#trendDetailOk").attr("disabled","disabled");
	})
	
	
	//趋势明细导出
	$("#trendDetailExport").click(function(){
		var currentOrderby = "";
		var currentOrder = "";
		if(lastClickTrend !="" && lastTrendOrder !="")
		{
			currentOrderby = lastClickTrend;
			currentOrder = lastTrendOrder;
		}
		else
		{
			currentOrderby = orderby;
			currentOrder = order;
		}
		var detailParams = detailChecked("#trendDetailAll","#trendDetailOpen input","#trendDetailTrade input");
		detailParams = detailParams + "&orderBy="+currentOrderby+"&pageRows="+pagerows+"&curPage=1&order="+currentOrder;
		openBuildUrl('/auth/fund/trendDetailExport.htm',detailParams);
	})
	
	
	//基金类型明细
	$("#fundDetailOk").click(function(){
		var detailParams = detailChecked("#fundDetailAll","#fundDetailOpen input","#fundDetailTrade input");
		if(detailParams == false)
			return false;

		if(params == ""){
			alert("请确定分析维度！");
			return false;
		}
		else
		ajaxRequest({
			url:'${uri.context_path}/auth/fund/fundDetail.htm',
            postMethod:'POST',
            params:params+"&"+detailParams,
            session_out:false,
            callback:function(data){
            	//hideF("fundTypeDetailTab");
            	$('#fundTypeDetail').html(data);
            	$("#fundDetailOk").removeAttr("disabled");
            	//frozenFundTable();
        	}
		});
		
		$('#fundTypeDetail').html("${util.loadImg}");
        $("#fundDetailOk").attr("disabled","disabled");
	});

	//基金类型明细导出
	$("#fundDetailExport").click(function(){
		var detailParams = detailChecked("#fundDetailAll","#fundDetailOpen input","#fundDetailTrade input");
		openBuildUrl('/auth/fund/fundDetailExport.htm',detailParams);
	});
	
	
});

function changeCheckBox(objectSelect,objectImage){
	var cnt = 0;
		var flag = false;
		
		var inpEleSe = "input[type=checkbox]";
		var objectInput = objectSelect + " td:eq(1) input";
		var size = $(objectSelect).children().find(inpEleSe).size() - 1;
		$(objectSelect).find(inpEleSe).each(function(){
			if($(this).prop("checked")){
				cnt = cnt + 1;
			}
		});
		
		if($(objectInput).prop("checked")){	
			flag = true;
		}
		if(flag){
			cnt = cnt - 1;
		}
		if(cnt != size && cnt != 0 ){
			$(objectImage).show();
			$(objectInput).prop("checked",false);
		}else if(cnt == 0){
			$(objectInput).prop("checked",false);
			$(objectImage).hide();
		}
		else{
			$(objectInput).prop("checked",true);
			$(objectImage).hide();
		}
}

$(function(){
	$("#fundok").click();
	//隐藏所有指标查询的确定按钮
	$(".tools input[value='确定']").hide();
	
	//注册指标check事件
	bindCheckboxEvent();
	
	//注册tab切换事件
	toggleTabEvent();
});
</script>
</body>
</html>