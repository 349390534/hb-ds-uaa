<#import "/uri.ftl" as uri />
<select name="" size="10" id="selectTwo" class="qd_list">
	<#if (option2??) && (option2?size gt 0)>
		<#list option2 as option>
			<option id="${option.id}" value="${option.routeName+'$$'+option.id+'$$'+option.qid}">${option.routeName}</option>
		</#list>
	</#if>	
</select>
<script>
	$(function(){
		$("#selectTwo").change(function(){
		var arr = $(this).val().split("$$");
		var channelType =  $("#selectOne").find("option:selected").attr("type");
		param = "channelType="+channelType+"&level=3&parentId="+arr[1];
		ajaxRequest(
                {url:'${uri.context_path}/auth/routemanager/initailQd.htm',
                postMethod:'POST',
                params:param,
                callback:function(data){
                	$('#qdThree').html(data);
                	$("#routedetail").html('<table><tr><td width="18%" class="t tdlt">推广渠道</td><td width="82%" class="tdlt"></td></tr><tr><td class="t tdlt">HTAG</td><td class="tdlt"></td></tr><tr><td class="t tdlt">申请时间</td><td class="tdlt"></td></tr><tr><td class="t tdlt">申请人</td><td class="tdlt"></td></tr><tr><td class="t tdlt">备注</td><td></td></tr></table>')
                	}
                });
		
	})
	})
</script>