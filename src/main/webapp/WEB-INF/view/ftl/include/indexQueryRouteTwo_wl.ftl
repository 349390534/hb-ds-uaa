<#import "/uri.ftl" as uri />
<#import "/console.ftl" as console />
<#if (routeTwo??) && (routeTwo?size gt 0)>
	<option value="-1">全部</option>
	<#list routeTwo as route>
		<option id="${route.id}" tagcode="${route.tagCode}" value="${route.routeName+'$$'+route.id+'$$'+route.qid}">${route.routeName}</option>
	</#list>
</#if>

<script>
	$(function(){
		$("#queryRouteTwo").change(function(){
			if($(this).val() == -1){
				$("#queryRouteThree").html("");
			}
			else{
				var arr = $(this).val().split("$$");
				param = "channelType=2&level=3&parentId="+arr[1];
				ajaxRequest(
		                {url:'${uri.context_path}/auth/at/initailQueryQd.htm',
		                postMethod:'POST',
		                params:param,
		                callback:function(data){
		                	$('#queryRouteThree').html(data);
		                	}
		                });
			}
			
		})
	});
</script>