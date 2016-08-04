<#import "/util.ftl" as util />
<#if (websiteDataList??) && (websiteDataList?size gt 0)>
	<#list websiteDataList as wd>
		<tr>
			<td>${wd_index + 1}</td>
			<td>${wd.dt}</td>
			<td head="enter">${util.formatNum(wd.enter!0)}</td>
			<td head="pv">${util.formatNum(wd.pv!0)}</td>
			<td head="uv">${util.formatNum(wd.uv!0)}</td>
			<td head="validuv">${util.formatNum(wd.validuv!0)}</td>
			<td head="gmuv">${util.formatNum(wd.gmuv!0)}</td>
			<td head="simuuv">${util.formatNum(wd.simuuv!0)}</td>
			<td head="drkh">${util.formatNum(wd.drkh!0)}</td>
			<td head="drbk">${util.formatNum(wd.drbk!0)}</td>
			<td head="persons">${util.formatNum(wd.persons!0)}</td>
			<td head="bills">${util.formatNum(wd.bills!0)}</td>
			<td head="amt">${util.formatNum(wd.amt!0,',##0.00')}</td>
			<td head="xdzhl">
			<#if wd.xdzhl??>
			${util.formatNum(wd.xdzhl*100,',##0.00')}%
			<#else>
			0.00%
			</#if></td>
			<td head="drxdcjrs">${util.formatNum(wd.drxdcjrs!0)}</td>
			<td head="drxdcjbs">${util.formatNum(wd.drxdcjbs!0)}</td>
			<td head="drxdcjje">${util.formatNum(wd.drxdcjje!0,',##0.00')}</td>
			<td head="cjzhl"><#if wd.cjzhl??>
				${util.formatNum(wd.cjzhl*100,',##0.00')}%
				<#else>
				0.00%
				</#if></td>
		</tr>
	</#list>
</#if>
<script>
$(function(){
		
		$("table#trendDataTab tr").find("td[head]:not(:has(a))").each(function(i,obj){
			$(obj).css({"text-align": "right"});
		});
		
		$("#trendDataTab thead td[head] a").each(function(i,obj){
			var a = $(obj);
			var head =a.parent().attr("head");
			if(head!=orderby){
				if(a.hasClass("sort-up")){
					a.removeClass("sort-up").addClass("sort-down");
				}
			}
		});
		
		
		//前一页
	$("#prepage").click(function(){
		curpage = curpage - 1;
		var formParam = formdata();
		formParam+="&pageRows=" + pagerows + "&curPage="
		+ curpage + "&orderBy=" + orderby
		+ "&order=" + order;
		if(curpage>0){
			pageshow(curpage,maxpage,function(data){
				$("#pagecontrol").html(data);
			});
			var reqObj = {
				url : context_path + "/" + _path + '/trendDetailTbody.htm',
				postMethod : 'POST',
				params : formParam,
				callback : function(data) {
					$('#tableshow').html(data);
				}
			};
			ajaxRequest(reqObj);
		}
		else{
			curpage = 1;
		}
	});
	
	//后一页
	$("#nextpage").click(function() {
		curpage = curpage + 1;
		if (curpage <= maxpage) {
			pageshow(curpage, maxpage, function(data) {
				$("#pagecontrol").html(data);
				var formParam = formdata();
				formParam+="&pageRows=" + pagerows + "&curPage="
				+ curpage + "&orderBy=" + orderby
				+ "&order=" + order;
				
				var reqObj = {
					url : context_path + "/" + _path + '/trendDetailTbody.htm',
					postMethod : 'POST',
					params : formParam,
					callback : function(data) {
						$('#tableshow').html(data);
					}
				};
				ajaxRequest(reqObj);
			});

		} else {
			curpage = maxpage;
		}

	});
	
	
	//点击某一页
	$("#pagecontrol li a").click(function() {
		curpage = Number($(this).text());
		pageshow(curpage, maxpage, function(data) {
			$("#pagecontrol").html(data);
		});
		var formParam = formdata();
		formParam+="&pageRows=" + pagerows + "&curPage="
		+ curpage + "&orderBy=" + orderby
		+ "&order=" + order;
		var reqObj = {
			url : context_path + "/" + _path + '/trendDetailTbody.htm',
			postMethod : 'POST',
			params : formParam,
			callback : function(data) {
				$('#tableshow').html(data);
			}
		};
		ajaxRequest(reqObj);
	});
});
</script>