<#import "/uri.ftl" as uri />
<select name="" size="10" id="selectThree" class="qd_list">
	<#if (option3??) && (option3?size gt 0)>
		<#list option3 as option>
			<option id="${option.id}" value="${option.routeName+'$$'+option.id+'$$'+option.qid}">${option.routeName}</option>
		</#list>
	</#if>
</select>
<script>
	$(function(){
		$("#selectThree").change(function(){
			var arr = $(this).val().split("$$");
			var channelType =  $("#selectOne").find("option:selected").attr("type");
			param ="channelType="+channelType+"&level=3&id="+arr[1];
			ajaxRequest(
                {url:'${uri.context_path}/auth/routemanager/routeDetail.htm',
                postMethod:'POST',
                params:param,
                callback:function(data){
                	$('#routedetail').html(data);
                	}
                });
			
		});
	})
</script>