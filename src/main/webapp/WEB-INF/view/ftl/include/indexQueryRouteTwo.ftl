<#import "/uri.ftl" as uri />
<#import "/console.ftl" as console />
<select style="width:100%;" id="queryRouteTwo">
	<#if (routeTwo??) && (routeTwo?size gt 0)>
		<option value="-1">全部</option>
		<#list routeTwo as route>
			<option id="${route.id}" root="${route.tagCode}" value="${route.routeName+'$$'+route.id+'$$'+route.qid}">${route.routeName}</option>
		</#list>
	</#if>
</select>

<script>
	$(function(){
		$("#queryRouteTwo").change(function(){
//			alert(1);
			if($(this).val() == -1){
				$("#queryRouteThree").html("");
			}
			else{
				var arr = $(this).val().split("$$");
				param = "level=3&parentId="+arr[1];
				ajaxRequest(
		                {url:'${uri.context_path}/${console.howbuywebsite}/initailQueryQd.htm',
		                postMethod:'POST',
		                params:param,
		                callback:function(data){
		                	$('#indexQueryRouteThree').html(data);
		                	}
		                });
			}
			
		})
	});
</script>