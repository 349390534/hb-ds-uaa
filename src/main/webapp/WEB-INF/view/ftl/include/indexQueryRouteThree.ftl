<#import "/uri.ftl" as uri />
<#import "/console.ftl" as console />
<select style="width:100%;" id="queryRouteThree">
	<#if (routeThree??) && (routeThree?size gt 0)>
		<option value="-1">全部</option>
		<#list routeThree as route>
			<option id="${route.id}" root="${route.tagCode}" value="${route.routeName+'$$'+route.id+'$$'+route.qid}">${route.routeName}</option>
		</#list>
	</#if>
</select>

