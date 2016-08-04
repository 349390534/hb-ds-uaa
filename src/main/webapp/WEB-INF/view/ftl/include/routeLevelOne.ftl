<#import "/uri.ftl" as uri />
<select name="" size="10" id="selectOne" class="qd_list">
	<#if (option1??) && (option1?size gt 0)>
		<#list option1 as option>
			<option id="${option.id}" value="${option.routeName+'$$'+option.id+'$$'+option.qid}" type="${option.channelType!}">${option.routeName}</option>
		</#list>
	</#if>					
</select>

<script>
$(function(){
	$("#selectOne").change(function(){
		var channelType =  $(this).find("option:selected").attr("type");
		var tp ="未知";
		if("1"==channelType){
			tp="网站";
		}else if("2"==channelType){
			tp="无线";
		}
		$("t#channelType").html(tp).show();
		var arr = $(this).val().split("$$");
		param = "channelType="+channelType+"&level=2&parentId="+arr[1];
		ajaxRequest(
                {url:'${uri.context_path}/auth/routemanager/initailQd.htm',
                postMethod:'POST',
                params:param,
                callback:function(data){
                	$('#qdTwo').html(data);
                	//三级渠道清空
                	$("#qdThree").html('<select name="" size="10" id="selectThree" class="qd_list"></select>');
                	//表格清空
                	$("#routedetail").html('<table><tr><td width="18%" class="t tdlt">推广渠道</td><td width="82%" class="tdlt"></td></tr><tr><td class="t tdlt">HTAG</td><td class="tdlt"></td></tr><tr><td class="t tdlt">申请时间</td><td class="tdlt"></td></tr><tr><td class="t tdlt">申请人</td><td class="tdlt"></td></tr><tr><td class="t tdlt">备注</td><td></td></tr></table>')
                	}
                });
		
	})
})
</script>