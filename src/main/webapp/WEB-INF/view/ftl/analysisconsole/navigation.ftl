<#import "/uri.ftl" as uri />
<#import "/util.ftl" as util />
<!DOCTYPE HTML>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<meta name="Description" content="" />
	<meta name="Keywords" content="" />
	<title>公募统计</title>
	<@uri.link href = ["/reset.css","/style_navigation.css"]/>
</head>
<body>
 <!-- main begin-->
<div class="main" style="border-left:none;">
  <div class="register_chart">
    <div class="num_chart clearfix" id="gmdata" permission_id="0001,0004"><#--PS：permission_id对应数据库数据--></div>
    <div class="num_detail clearfix">
    	<div class="n_detail_left fl" id="websitedata" permission_id="000311"><#--来源分析--></div>
    	<div class="n_detail_center fl" id="ctdata" permission_id="000421"><#--穿透分析--></div>
    	
    	<div class="n_detail_right fr" style="display:none;"><#--备用 TODO  -->
    		<p class="date">日期：2015-11-17</p>
    		<div class="n_detail_bg">
					<div class="wz_data">
						<table>
							<tr>
								<td>XXXX数</td>
								<td><span class="cBold ft22">1,000,000</span></td>
							</tr>
							<tr>
								<td>日环比</td>
								<td><span class="cBold cGreen ft18">-10.00%</span></td>
							</tr>
						</table>
					</div>
					<div class="bt_table2">4</div>
				</div>
				<a href="" class="btn-style-b btn">查看网站访问明细</a>
    	</div>
    </div>
  </div>
</div>
<!-- main end-->

<@uri.script src=[
"/navigation.js","/penetrate_analysis_chart.js"
]/>
<script type="text/javascript">
var _perListJson = [];
<#if Session.allPerList??>
	_perListJson=${Session.allPerList!};
</#if>
if(_perListJson.length==0){
	location.href=uaa_context_path+"/login/index.htm";
}
 //1:判断权限
 var hasGm = containsPer(_perListJson,"0001")||containsPer(_perListJson,"0004");
 if(hasGm){
 	reqGmData(uaa_context_path);
 }
 var hasWb = containsPer(_perListJson,"0003") && containsPer(_perListJson,"000311");
 if(hasWb){
	 reqWebSiteData(uaa_context_path);
 }else{
 	$("[permission_id='000311']").detach();
 }

 var hasCt = containsPer(_perListJson,"0004") && containsPer(_perListJson,"000421");
 if(hasCt){
	 reqCtData(uaa_context_path);
 }else{
 	$("[permission_id='000421']").detach();
 
 }
</script>
</body>
</html>