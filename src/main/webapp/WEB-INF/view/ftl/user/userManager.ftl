<#import "/uri.ftl" as uri />
<!DOCTYPE HTML>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<meta name="Description" content="" />
	<meta name="Keywords" content="" />
	<title>用户管理</title>
	<@uri.link href = ["/reset.css","/style.css","/change.css","/jquery/ui/jquery-ui.css"]/>
	<#--<@uri.script src=["/jquery/jquery.autocomplete.js"]/>-->
</head>
<body>


<!-- main begin-->
<div class="main">
	<!-- 分布图/趋势图 begin -->
	<div class="chartbox">
		
		<div class="chartbox_tab">
			<div class="box">
				<div class="qd_con fl">
					<div class="grade2">
						
                        
                        	<div class="info_user" style="border-bottom:none;font-size: 14px;">搜索用户<input data-brackets-id="9293" value=""inputTip="请输入待搜索用户" style="width:230px;margin: 0 1px 0;height:24px;line-height:24px;outline:none; padding-left:6px;color:#333;  border: 1px solid #666;border-top:none;;border-right:none;;border-left:none;font-family: 微软雅黑;" type="text" id = "serchInput">
							<input type="button" value="" class="bt_bar fl" id="searchButton" style="display:none;"></div>
                        <#--<select name="" id="allUserSelect" class="qd_list" type="false"></select> --> 
						<#if allUser??>
							<div style='padding-top: 50px;' class="selectUser">
							<ul  style='width: 235px;height: 400px;overflow-y: auto;font-family: "微软雅黑";font-size: 14px;outline: none;margin-top: -13px;border:#666 solid 1px;'>
							<#list allUser as user>
								<li chineseName="${user.chineseName!}" email="${user.email!}"><a href="javascript:void(0);" name="${user.userName!}"> ${user.userName!}</a> </li>	
							</#list>
							</ul>
							</div>
						</#if>
						
					</div>
				</div>
				<div class="info_show fl">
					
                    <div class="info_con" >
					
					</div>
				</div>
			</div>
		</div>						
	</div>
	<!-- 分布图/趋势图 end -->
	
</div>
<!-- main end-->

<script>
	$(function(){
		
		var selectBody = ''		<#if allUser??>
			<#list allUser as user>
				selectBody= selectBody + '<option id="${user.userName!}" chineseName="${user.chineseName!}" email="${user.email!}">'+"${user.userName!}"+'</option>'
			</#list>
		</#if>
		$("#allUserSelect").html(selectBody);
		var data=${json};
		//$("#serchInput").val($("#serchInput").attr("inputTip"));
		$("#serchInput").focus(function(){
			if($(this).attr("inputTip") == $(this).val()){
				$(this).val("").css("color","");
			}
			
		}).blur(function(){
			if($.trim($(this).val()).length == 0){
				$(this).val($(this).attr("inputTip")).css("color","#c0c0c0");
			}
		}).blur();
		$("#serchInput").autocomplete({
			source: data,
			select:function(event, ui ){
				var userName = ui.item.value;
        		var chineseName='';
        		var email='';
        		var telphone='';
        		$("#searchButton").val(userName);
        		//$("#allUserSelect").find("option:selected").removeAttr("selected");
        		$(".selectUser ul li[class='current']").removeClass("current");
	    		var params = "userName="+userName+"&chineseName="+chineseName+"&email="+email+"&telphone="+telphone;
				ajaxRequest({
					url:'${uri.context_path}/auth/userManager/queryUserByName.htm',
           	 		postMethod:'POST',
            		params:params,
            		callback:function(data){
            			$(".info_con").html(data);
        			}
				});
			}
		});
		 $('#serchInput').bind('keypress',function(event){
            if(event.keyCode == "13")    
            {
                
	    		var userName = $('#serchInput').val()
        		var chineseName='';
        		var email='';
        		var telphone='';
        		$("#searchButton").val(userName);
				//$("#allUserSelect").find("option:selected").removeAttr("selected");
				$(".selectUser ul li[class='current']").removeClass("current");
	    		var params = "userName="+userName+"&chineseName="+chineseName+"&email="+email+"&telphone="+telphone;
				ajaxRequest({
					url:'${uri.context_path}/auth/userManager/queryUserByName.htm',
           	 		postMethod:'POST',
            		params:params,
            		callback:function(data){
            		;
            			$(".info_con:eq(0)").html(data);
        			}
				});
            }
        });
        $("#searchButton").click(function(){
        	var userName = $(this).val()
        	var chineseName='';
        	var email='';
        	var telphone='';
        	
	    		var params = "userName="+userName+"&chineseName="+chineseName+"&email="+email+"&telphone="+telphone;
				ajaxRequest({
					url:'${uri.context_path}/auth/userManager/queryUserByName.htm',
           	 		postMethod:'POST',
            		params:params,
            		callback:function(data){
            			$(".info_con").html(data);
        			}
				});
        });
		
		
	    $("#allUserSelect").click(function(){
	    	var $option = $("#allUserSelect").find("option:selected"); 
			
	    	
	    	if(typeof($option.val())!="undefined"){
	    		//$option = $option.first();
	    		
				//$option.siblings().removeAttr("selected");
				var userName = $option.val();				
	    		var params = "userName="+userName;
	    		$("#searchButton").val(userName);
				$("#serchInput").val("");
				$("#serchInput").focus();
				$("#serchInput").blur();
				ajaxRequest({
					url:'${uri.context_path}/auth/userManager/queryUserByName.htm',
           	 		postMethod:'POST',
            		params:params,
            		callback:function(data){
            			$(".info_con").html(data);
        			}
				});
	    	}	    
	    });
	$(".selectUser ul li").click(function(){
		$(this).addClass("current").siblings().removeClass("current");
		var userName = $(this).find("a").attr("name");	
	    var params = "userName="+userName;
	    $("#searchButton").val(userName);
		$("#serchInput").val("");
		$("#serchInput").focus();
		$("#serchInput").blur();
		ajaxRequest({
				url:'${uri.context_path}/auth/userManager/queryUserByName.htm',
       		 	postMethod:'POST',
        		params:params,
            	callback:function(data){
            		$(".info_con").html(data);
        		}
		});
	   
	});
	 $("#allUserSelect").dblclick(function(){
	 		var $option = $("#allUserSelect").find("option:selected").attr("isdb","db"); 
	  		//$("#bianji").click();
	 });
	 $(".selectUser ul li").dblclick(function(){
		$(this).attr("isdb","db");
	}); 
});
</script>
</body>
</html>