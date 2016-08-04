<#import "/util.ftl" as util />
<#import "/uri.ftl" as uri />
<#if cnt??>
<table class="det-d-qdmx" style="table-layout:fixed ;" id="custchannelDataTab">
	<thead id="custChannelDetailThead">
		<tr class="tit">
				<#if (cnt == 3||cnt == 4) && discode != "HB000A001">
					<td class="wdqd">开户机构</td>
					<td class="wdqd">开户网点</td>
				<#elseif cnt != 0>
					<#list 0..cnt-1 as n>
						<td class="wdqd">${pathname[pathcode[n]+wd]}</td>
					</#list>
				</#if>
				<#if childname != "none" && (discode == "HB000A001" || discode=="-1")>
					<td class="wdqd">${pathname[childname+wd]}</td>
				</#if>
			<td head="ljkhs" width="100"><a href="javascript:void(0)" target="self" class="sort" tag = "ljkhs">总客户数</a></td>
			<td head="ljbks" width="100"><a href="javascript:void(0)" target="self" class="sort" tag = "ljbks"> 总绑卡人数</a></td>
			<td head="ljjqs" width="100"><a href="javascript:void(0)" target="self" class="sort" tag = "ljjqs"> 总鉴权人数</a></td>
			<td head="ljscjys" width="120"><a href="javascript:void(0)" target="self" class="sort" tag = "ljscjys"> 首次交易总人数</a></td>
			<td head="ljyks" width="100"><a href="javascript:void(0)" target="self" class="sort" tag = "ljyks"> 总验卡人数</a></td>
			<td head="cys" width="100"><a href="javascript:void(0)" target="self" class="sort" tag = "cys"> 总持有人数</a></td>
			<td head="ljzjql" width="100"><a href="javascript:void(0)" target="self" class="sort" tag = "ljzjql"> 总鉴权率</a></td>
			<td head="ljzjyl" width="100"><a href="javascript:void(0)" target="self" class="sort" tag = "ljzjyl"> 总交易率</a></td>
			<td head="ljzjqjyl" width="100"><a href="javascript:void(0)" target="self" class="sort" tag = "ljzjqjyl"> 总鉴权交易率</a></td>
			<td head="ljzykl" width="100"><a href="javascript:void(0)" target="self" class="sort" tag = "ljzykl"> 总验卡率</a></td>
		</tr>
	</thead>
	<tbody id="custChannelDetailBody">
		<#include "custChannelDetailTbody.ftl">
	</tbody>	
</table>
<script>
$(function(){
	/**添加渠道明细的指标样式*/
	$("#custChannelDetailThead a").each(function(){
		$(this).addClass("sort-down");
    });
    //渠道明细排序 
	$("#custChannelDetailThead a").click(function(){
		var currentClick = $(this).attr("tag");
		var up = $(this).hasClass("sort-up");
		var down = $(this).hasClass("sort-down");
		var currentOrder = "";
		if(up){
			currentOrder="desc";
		}else if(down){
			currentOrder="asc";
		}
		if(currentClick == lastClickChannel){
			if(lastChannelOrder === "asc")
			{
				currentOrder = "desc";
			}
			else if(lastChannelOrder === "desc"){
				currentOrder = "asc";
			}
		}
		orderby = $(this).attr("tag");
		var detailParams = detailChecked("#custchannelDetail","#custchannelDetailOpen input");
		if(detailParams == false){
			return false;
		}
		detailParams = detailParams + "&orderBy="+orderby+"&order="+currentOrder;
		
		ajaxRequest({
			//url:'${uri.context_path}/auth/customerData/custChannelDetail.htm',
			url:'${uri.context_path}/auth/customerData/custChannelDetailTbody.htm',
            postMethod:'POST',
            params:params + '&' + detailParams,
            callback:function(data){
            	//$("#custChannelDetail").html(data);
            	$("#custChannelDetailBody").html(data);
            	$("#showChannel").hide();
            	
		      	$("#custChannelDetailThead a[tag='"+orderby+"']").each(function(){
            		if($(this).attr("tag") === orderby)
            		{
            			if(currentOrder === "desc"){
            				if($(this).hasClass("sort-up")){
            					$(this).removeClass("sort-up");
	            				$(this).addClass("sort-down");
            				}
            			}
            			if(currentOrder === "asc"){
            				if($(this).hasClass("sort-down")){
            					$(this).removeClass("sort-down");
	            				$(this).addClass("sort-up");
            				}
            			}
            		}
	            });	
	            
	            lastClickChannel = currentClick;
				lastChannelOrder = currentOrder;
				$(".det-datalist table .n-open").click();
        	}
		});
		$("#showChannel").show();
	});
		
});
	
</script>
</#if>