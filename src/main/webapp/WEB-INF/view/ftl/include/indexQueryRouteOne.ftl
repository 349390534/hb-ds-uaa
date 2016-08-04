<#import "/uri.ftl" as uri />
<#import "/console.ftl" as console />
<select style="width:100%;" id="queryRouteOne" name="">
	
	<#if (routeOne??) && (routeOne?size gt 0)>
		<option value="-1">全部</option>
		<#list routeOne as route>
			<option id="${route.id}" root="${route.tagCode}" value="${route.routeName+'$$'+route.id+'$$'+route.qid}">${route.routeName}</option>
		</#list>
	</#if>
</select>

<script>
	$(function(){
		$("#queryRouteOne").change(function(){
			$("#queryRouteTwo").html("");
			$("#queryRouteThree").html("");
			if($(this).val() != -1){
				var arr = $(this).val().split("$$");
				param = "level=2&parentId="+arr[1];
				ajaxRequest(
		                {url:'${uri.context_path}/${console.howbuywebsite}/initailQueryQd.htm',
		                postMethod:'POST',
		                params:param,
		                callback:function(data){
		                	$('#indexQueryRouteTwo').html(data);
		                	}
		                });
			}
		})
	});
</script>