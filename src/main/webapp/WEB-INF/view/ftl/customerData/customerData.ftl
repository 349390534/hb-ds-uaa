<#import "/uri.ftl" as uri />
<#import "/util.ftl" as util />
<!DOCTYPE HTML>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<meta name="Description" content="" />
	<meta name="Keywords" content="" />
	<title>总客户数据</title>
	<@uri.link href = ["/reset.css","/style.css","/change.css"]/>
	<@uri.script src=
	[
	"/uaa_customerData.js","/uaa_customerData_util.js"
	]/>
	
</head>
<body>

<!-- main begin-->
<div class="main">
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
	<div class="filter_b" id="custCondition" style="min-width:1100px;">
		<dl class="fwqd">
			<dt>分析维度：</dt>
			<dd>
				<ul class="clearfix" id="fenxiweidu">
					<li class="current" para="analysisWd%1"><a href="javascript:void(0)" target="_self">开户渠道</a></li>
				</ul>
			</dd>
		</dl>
		
		<div class="fw">
			<div id="qdChoose"></div>
            <dl class="mt10 fwqd2" >
                <input type="button" value="确   定" class="btn-style-a" id="dataok" style="float:right;width:80px;margin-right:65px;" onclick="dataDetailSubmit();">
            </dl>											
		</div>
	</div>
	<!-- 访问渠道 end -->
	
	<!-- 开户渠道 begin -->
	<div class="chartbox hb-chart" id="custGraph" align="center">
		<#include "customerGraph.ftl"/>
	</div>
	<!-- 开户渠道 end -->
	<!-- 明细 begin -->
	<div class="detail" style="min-width:1100px;">
		<div class="tab_list">
			<ul class="tab_menu clearfix">
				<li id="custqd"class="current" tag="custqd">渠道明细</li>
				<li id="custqs" tag="custqs">趋势明细</li>
			</ul>
			<div class="tab_box">
				<!--渠道明细-->
				<div class="con" id="custchannelDetail" tag="custqd">
					<div class="detail_filter" id="custOpenChannelNorm">
					 <table class="table1" id="custchannelDetailOpen">
					     <tr class="custchannelOpenNorm" cst="channelOpenNorm">
					         <td class="w1">开户指标：</td>
					         <td><label><img src="${uri.context_path}/images/03.jpg" id="custchannelOpenNormImage"  style="display:none"> <input type="checkbox" name="" value="-1" cst="channelOpenNorm" class="detail_b"/>全选</label></td>
					         <td><label><input type="checkbox" name="" value="ljkhs">总客户数</label></td>
					         <td><label><input type="checkbox" name="" value="ljbks">总绑卡人数</label></td>
					         <td><label><input type="checkbox" name="" value="ljjqs">总鉴权人数</label></td>
					         <td><label><input type="checkbox" name="" value="cys">总持有人数</label></td>
					         <td><label><input type="checkbox" name="" value="ljscjys">首次交易总人数</label></td>
					         <td><label><input type="checkbox" name="" value="ljyks">总验卡人数</label></td>
					     </tr>
					     <tr class="custchannelOpenNorm" cst="channelOpenNorm">
					         <td></td>
					     	 <td></td>
					         <td><label><input type="checkbox" name="" value="ljzjql">总鉴权率</label></td>
					         <td><label><input type="checkbox" name="" value="ljzjyl">总交易率</label></td>
					         <td><label><input type="checkbox" name="" value="ljzjqjyl">总鉴权交易率</label></td>
					         <td><label><input type="checkbox" name="" value="ljzykl">总验卡率</label></td>
					        
					     </tr>
					     
					 </table>

					</div>
					
					<div class="tools">
						<input type="button" value="导出明细" class="btn-style-a" id="custchannelDetailExport">
					</div>
					<div id="showChannel" class="showLoading" style="display:none;margin-left:500px;margin-top:-200px;opacity:0.5;z-index:200;position:absolute;">
						${util.loadImg}
					</div>
					<div class="det-datalist" id="custChannelDetail" align="center">
						<#include "../include/custChannelDetail.ftl"/>
					</div>
				</div>
				<!--渠道明细end-->
				
				 <!-- 趋势明细 begin -->
				<div class="con hide" id="custtrendDetailAll"  tag="custqs">
					<div class="detail_filter" id="custOpenTrendNorm">
					 <table class="table1" id="custtrendDetailOpen">
					     <tr class="custtrendOpenNorm" cst="custtrendOpenNorm">
					         <td class="w1">开户指标：</td>
					         <td><label><img src="${uri.context_path}/images/03.jpg" id="custtrendOpenNormImage"  style="display:none"> <input type="checkbox" name="" value="-1" cst="custtrendOpenNorm" class="detail_b"/>全选</label></td>
					         <td><label><input type="checkbox" name="" value="ljkhs">总客户数</label></td>
					         <td><label><input type="checkbox" name="" value="ljbks">总绑卡人数</label></td>
					         <td><label><input type="checkbox" name="" value="ljjqs">总鉴权人数</label></td>
					         <td><label><input type="checkbox" name="" value="cys">总持有人数</label></td>
					         <td><label><input type="checkbox" name="" value="ljscjys">首次交易总人数</label></td>
					         <td><label><input type="checkbox" name="" value="ljyks">总验卡人数</label></td>
					     </tr>
					      <tr class="custtrendOpenNorm" cst="custtrendOpenNorm">
					         <td></td>
					     	 <td></td>
					         <td><label><input type="checkbox" name="" value="ljzjql">总鉴权率</label></td>
					         <td><label><input type="checkbox" name="" value="ljzjyl">总交易率</label></td>
					         <td><label><input type="checkbox" name="" value="ljzjqjyl">总鉴权交易率</label></td>
					         <td><label><input type="checkbox" name="" value="ljzykl">总验卡率</label></td>
					        
					     </tr>
					 </table>
					</div>
					<div class="tools">
						<input type="button" value="导出明细" class="btn-style-a" id="custtrendDetailExport">
					</div>
					<div class="det-datalist" id="custTrendDetail" align="center">
						<#include "../include/custTrendDetail.ftl">
					</div>
				</div>
				<!-- 趋势明细 end -->
			</div>
		</div>
	</div>
	<!-- 明细 end -->
	<#include "zhibiao_explain.ftl">
	
</div>
<!-- main end-->

<script type="text/javascript">
context_path = "${uri.context_path}";

$(function(){
	//动态加载渠道选择
	var channeljson = ${zkhsjqd};
	var all = loadChannel(channeljson);
	$("#qdChoose").html(all);
	defaultEvent();
	loadImage = "${util.loadImg}";
	$(".filter_b dl dd ul li").css("width","80px");
	dataDetailSubmit();
	toggleTabEvent();
	someEvent();
});
</script>
</body>
</html>