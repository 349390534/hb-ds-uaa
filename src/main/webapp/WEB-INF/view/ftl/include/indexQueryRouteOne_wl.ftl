<#import "/uri.ftl" as uri />
<#import "/console.ftl" as console />
<#if (routeOne??) && (routeOne?size gt 0)>
	<option value="-1">全部</option>
	<#list routeOne as route>
		<option id="${route.id}" tagcode="${route.tagCode}" value="${route.routeName+'$$'+route.id+'$$'+route.qid}">${route.routeName}</option>
	</#list>
</#if>
<script>
	$(function(){
		$("#queryRouteOne").change(function(){
			$("#queryRouteTwo").html("");
			$("#queryRouteThree").html("");
			if($(this).val() != -1){
				var arr = $(this).val().split("$$");
				param = "channelType=2&level=2&parentId="+arr[1];
				ajaxRequest(
		                {url:'${uri.context_path}/auth/at/initailQueryQd.htm',
		                postMethod:'POST',
		                params:param,
		                callback:function(data){
		                	$('#queryRouteTwo').html(data);
		                	}
		                });
			}
		})
	});
</script>