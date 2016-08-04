<#import "/console.ftl" as con />
<#import "/util.ftl" as util />
<#import "/uri.ftl" as uri />
<#if cnt??>
<table class="det-d-qdmx" style="table-layout:fixed ;" id="fundChannelDetailTab">
		<thead id="fundChannelDetailThead">
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
				<#if theadlist??>
					<#list theadlist as head>
						<#if wd == '1' && head != '-1'>
						<td head="${head!}"><a href="javascript:void(0)" target="self" class="sort" tag = "${head}">${con.opennorms[head!]!}</a></td>
						</#if>
						<#if wd == '2' && head != '-1'>
						<td head="${head!}"><a href="javascript:void(0)" target="self" class="sort" tag = "${head}">${con.tradenorms[head!]!}</a></td>
						</#if>
					</#list>
				</#if>
			</tr>
			
		</thead>
		<tbody>
		<#if coll??>
		<tr class="n-list" id="weidupath">
		    <#list coll as co>
			    <#list theadlist as head>
			    	<#if head != '-1'>
			    		<td style="text-align:right;" head="${head!}">
			    		<#if (co[con.normsum[head]])?? && co[con.normsum[head]]?is_number>
			    			<@util.formatData head co[con.normsum[head]]/>
			    		</#if>
			    		</td>
			    	</#if>
			    </#list>
		    </#list>
		</tr>
	    </#if>
		
		<#if cnt != 4>
		<#if colllist??>
		<#list colllist as col>
			<tr class = " n-tgqd hide">
				<#if cnt == 3 && discode != "HB000A001">
					<td></td>
					<#--<td></td>-->
				<#elseif cnt != 0>
					<#list 0..cnt-1 as n>
						<td></td>
					</#list>
				</#if>
				<#if childname != "none">
					<td style="padding-left: 30px;text-align:left;">
						<#--<#if col["disname"]??>${col["disname"]}</#if>
						<#if col["channame"]??>${col["channame"]}</#if>
						<#if col["hzlx"]??>${col["hzlx"]}</#if>
						<#if col["outletname"]??>${col["outletname"]}</#if>
						<#if col["fundtypename"]??>${col["fundtypename"]}</#if>-->
						<#if col["outletname"]??>
							${col["outletname"]}
						<#elseif col["hzlx"]??>${col["hzlx"]}
						<#elseif col["channame"]??>${col["channame"]}
						<#elseif col["disname"]??>${col["disname"]}
						</#if>
						<#if col["fundtypename"]??>${col["fundtypename"]}</#if>

					</td>
				</#if>
				
				<#list theadlist as head>
			    	<#if head != '-1'>
			    		<td style="text-align:right;" head="${head!}"><#if (col[con.normsum[head]])??><@util.formatData head col[con.normsum[head]]/></#if></td>
			    	</#if>
			    </#list>
			</tr>
		</#list>
		
		</#if>
		
		</#if>
		
		</tbody>						
	</table>
	
	<script type="text/javascript">
	
	$(function(){
		var str="";
		var cnt = ${cnt};
		var discode = "${discode}";
		
		<#if childname == 'discode'>
			str = str + '<td style="text-align: left;"><a href="javascript:void(0)" target="_self" class="n-open" value="n-tgqd">全部</a></td>';
		<#else>
			var child = $("#qudaoChoose li.current");
			var childlength = child.length;
			child.each(function(i,obj){
				var text = $(obj).find("a").text();
				if(text==null || ""==text){
					text = $(obj).text();
				}
				if(i == childlength -1 && cnt != 4){
					if(cnt == 3 && discode=="HB000A001")
						str = str + '<td>'+text+'</td>' + '<td style="text-align: left;"><a href="javascript:void(0)" target="_self" class="n-open" value="n-tgqd">'+text+'</a></td>';
					else
						str = str + '<td style="text-align: left;"><a href="javascript:void(0)" target="_self" class="n-open" value="n-tgqd">'+text+'</a></td>';
				}
				else{
					str = str + '<td>'+text+'</td>'
				}
				
			});
			if(cnt == 4 && discode=="HB000A001"){
				$("#qudaoChoose select").each(function(i,obj){
					var it = $(obj).val();
					var text = $("#"+it).find("a").text();
					if(text==null || ""==text){
						text = $("#"+it).text();
					}
					if(it !== "-1")
						str = str + '<td>'+text+'</td>';
				});
			}
		</#if>
		
		$("#weidupath").prepend(str);
		//渠道类别竖型结构
		$(".det-datalist table .n-open").click(function(){
			var _n = $(this).attr("value");
			if($(this).hasClass("n-open")){
				$(this).addClass("n-close").removeClass("n-open");
				$(this).parent().parent().siblings("."+_n).show();
			}else{
				$(this).addClass("n-open").removeClass("n-close");
				$(this).parent().parent().siblings("."+_n).hide();
				$(this).parent().parent().nextUntil(".n-list").hide();
				$(this).parent().parent().siblings().find(".name-2 a").addClass("n-open").removeClass("n-close");
			}
			return false;
		});
		$(".det-datalist table .n-open").click();//默认展开
		
		
		//渠道明细排序 
		$("#fundChannelDetailThead a").click(function(){
		
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
			var detailParams = detailChecked("#channelDetailAll","#channelDetailOpen input","#channelDetailTrade input");
			if(detailParams == false){
				return false;
			}
			detailParams = detailParams + "&orderBy="+orderby+"&order="+currentOrder;
			
			ajaxRequest({
				url:'${uri.context_path}/auth/fund/channelDetail.htm',
	            postMethod:'POST',
	            params:params + '&' + detailParams,
	            callback:function(data){
	            	$('#fundChannelDetail').html(data);
	            	$("#showChannel").hide();
			      	$("#fundChannelDetailThead a[tag='"+orderby+"']").each(function(){
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
		
		
		/**添加渠道明细的指标样式*/
		$("#fundChannelDetailThead a").each(function(){
			$(this).addClass("sort-down");
		 	/*var wd = $("#fenxiweidu li.current").index()+1;
		 	var tag =$(this).attr("tag");
		 	var drkh ="drkh";
		 	var drxdrs ="drxdrs";
		 	if(wd=="1"){
		 		if(tag==drkh){
		 			$(this).addClass("sort-down");
		 		}else{
		 			$(this).addClass("sort-up");
		 		}
		 	}else if(wd=="2"){
		 		if(tag==drxdrs){
		 			$(this).addClass("sort-down");
		 		}else{
		 			$(this).addClass("sort-up");
		 		}
		 	}*/
	    });
	    
		
	});
	
	var wd =$("#fenxiweidu li.current").index()+1;
	checkClickQuota(wd,"2");

	</script>
</#if>