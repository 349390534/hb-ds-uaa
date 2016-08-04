<div class="detail_fs" style="margin:10px 20px 0;">
	<div class="tab_list">
		<ul class="tab_menu3 clearfix" id="fundAndCompanyTab">
			<li class="current"><h3>基金查询</h3></li>
			<li ><h3>基金公司查询</h3></li>
		</ul>
		<div class="tab_box3" id="fundAndCompanyTabBox">
			<div class="con"><!-- 渠道明细 begin -->
				<div class="f_find">基金代码：<input id="fundInput" type="num" value="" class="f_code" size="12"><#--<input type="submit" value="确定" style="width: 80px;" class="btn-style-b">--> <p id="fundInputName"></p></div>	
				<div class="f-datalist" id="fundChart"></div>
			</div><!-- 渠道明细 end -->
			<div class="con hide"><!-- 渠道明细 begin -->
				<div class="f_find">基金公司：<input id="companyInput" type="num" value="" class="f_code" size="12"><#--<input type="submit" value="确定" style="width: 80px;" class="btn-style-b"> --><p id="companyInputName"></p></div>
				<div class="f-datalist" id="fundCompanyChart"></div>
			</div><!-- 趋势明细 end -->
		</div>
	</div>					
</div>

<script type="text/javascript">
var pth = "${uri.context_path}";
$(function(){
	//注册切换事件
	fundAndCompanyTogEvent();
	//基金搜索事件
	$("#fundInput").autocomplete({
		source:function(request, response ){
         $.ajax({
          	url:pth+"/auth/fundsale/ajaxQueryFundListJsonData.htm",
          	dataType:"json",
          	data:{term:$("#fundInput").val()},
          	success:function(data){
          		
          		var num=10;
          		response($.map(data,function(item){
          			if(num-->=1){
		          		return {lable:item.FUND_CODE,value:item.FUND_NAME};
          			}
          		}));
          	}
          });
		},select:function(event,ui){
			console.log(ui.item.value);
			var fundcode = ui.item.lable;
			$("#fundInputName").html(ui.item.value);
			//画图
			var datex = getdatexArray();
			drawFundDataChart("fundChart",fundcode,datex,pth);
		},delay: 600
	});
	
	//基金公司搜索事件
	$("#companyInput").autocomplete({
		source:function(request, response ){
         $.ajax({
          	url:pth+"/auth/fundsale/ajaxQueryFundCompanyListJsonData.htm",
          	dataType:"json",
          	data:{term:$("#companyInput").val()},
          	success:function(data){
          		
          		var num=10;
          		response($.map(data,function(item){
          			if(num-->=1){
		          		return {lable:item.TA_CODE,value:item.TA_NAME};
          			}
          		}));
          	}
          });
		},select:function(event,ui){
			console.log(ui.item.value);
			var tacode = ui.item.lable;
			$("#companyInputName").html(ui.item.value);
			//画图
			
			var datex = getdatexArray();
			drawFundCompanyDataChart("fundCompanyChart",tacode,datex,pth);
		},delay: 600
	});
});

</script>