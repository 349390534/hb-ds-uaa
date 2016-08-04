<#import "/util.ftl" as util />
<#import "/uri.ftl" as uri />
	<#if coll??>
		<tr class="n-list" id="weidupath">
		    <#list coll as co>
			    <td head="ljkhs" style="text-align:right;">${util.formatNum(co.ljzkhs!0)}</td>
				<td head="ljbks" style="text-align:right;">${util.formatNum(co.ljzbks!0)}</td>
				<td head="ljjqs"style="text-align:right;">${util.formatNum(co.ljzjqs!0)}</td>
				<td head="ljscjys"style="text-align:right;">${util.formatNum(co.ljscjyzs!0)}</td>
				<td head="ljyks"style="text-align:right;">${util.formatNum(co.ljzyks!0)}</td>
				<td head="cys"style="text-align:right;">${util.formatNum(co.zcys!0)}</td>
				<td head="ljzjql"style="text-align:right;">
					<#if co.ljzjql??>
						${util.formatNum(co.ljzjql*100,',##0.00')}%
					<#else>
						0.00%
					</#if>
				</td>
				<td head="ljzjyl"style="text-align:right;">
					<#if co.ljzjyl??>
						${util.formatNum(co.ljzjyl*100,',##0.00')}%
					<#else>
						0.00%
					</#if>
				</td>
				<td head="ljzjqjyl"style="text-align:right;">
					<#if co.ljzjqjyl??>
						${util.formatNum(co.ljzjqjyl*100,',##0.00')}%
					<#else>
						0.00%
					</#if>
				</td>
				<td head="ljzykl"style="text-align:right;">
					<#if co.ljzykl??>
						${util.formatNum(co.ljzykl*100,',##0.00')}%
					<#else>
						0.00%
					</#if>
				</td>
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
						<#if col["outletname"]??>
							${col["outletname"]}
						<#elseif col["hzlx"]??>${col["hzlx"]}
						<#elseif col["channame"]??>${col["channame"]}
						<#elseif col["disname"]??>${col["disname"]}
						</#if>
						<#if col["fundtypename"]??>${col["fundtypename"]}</#if>

					</td>
				</#if>
	
				<td head="ljkhs"style="text-align:right;">${util.formatNum(col.ljzkhs!0)}</td>
				<td head="ljbks"style="text-align:right;">${util.formatNum(col.ljzbks!0)}</td>
				<td head="ljjqs"style="text-align:right;">${util.formatNum(col.ljzjqs!0)}</td>
				<td head="ljscjys"style="text-align:right;">${util.formatNum(col.ljscjyzs!0)}</td>
				<td head="ljyks"style="text-align:right;">${util.formatNum(col.ljzyks!0)}</td>
				<td head="cys" style="text-align:right;">${util.formatNum(col.zcys!0)}</td>
				<td head="ljzjql"style="text-align:right;">
					<#if col.ljzjql??>
						${util.formatNum(col.ljzjql*100,',##0.00')}%
					<#else>
						0.00%
					</#if>
				</td>
				<td head="ljzjyl"style="text-align:right;">
					<#if col.ljzjyl??>
						${util.formatNum(col.ljzjyl*100,',##0.00')}%
					<#else>
						0.00%
					</#if>
				</td>
				<td head="ljzjqjyl"style="text-align:right;">
					<#if col.ljzjqjyl??>
						${util.formatNum(col.ljzjqjyl*100,',##0.00')}%
					<#else>
						0.00%
					</#if>
				</td>
				<td head="ljzykl"style="text-align:right;">
					<#if col.ljzykl??>
						${util.formatNum(col.ljzykl*100,',##0.00')}%
					<#else>
						0.00%
					</#if>
				</td>
			</tr>
			</#list>
		
		</#if>
		
		</#if>
	
<script>
	$(function(){
		var str="";
		var cnt = ${cnt};
		var discode = "${discode}";
		<#if childname == 'discode'>
			str = str + '<td style="text-align: left;"><a href="javascript:void(0)" target="_self" class="n-open" value="n-tgqd">全部</a></td>';
		<#else>
			var child = $("#qdChoose li.current");
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
				$("#qdChoose select").each(function(i,obj){
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
	});
	
</script>