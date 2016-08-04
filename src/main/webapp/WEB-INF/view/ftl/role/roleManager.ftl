<#import "/uri.ftl" as uri />
<!DOCTYPE HTML>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<meta name="Description" content="" />
	<meta name="Keywords" content="" />
	<title>角色管理</title>
	<@uri.link href = ["/reset.css","/style.css","/change.css"]/>
	<script>
		$(function(){
			$('.tab_list').Tabs({
				event:'click'
			});
		})
	</script>
	<!--[if IE 6]><script type="text/javascript" src="/js/global/DD_belatedPNG.js"></script><![endif]-->
	<!--[if IE 6]><script>$(function(){ DD_belatedPNG.fix(".pngFix,.pngFix:hover");})</script><![endif]-->
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
						<p class="info_name">角色列表</p>
						<#--<select name="" multiple="multiple" id="role_list" class="qd_list"></select>-->
					<#if roleList??>
						<div style='padding-top: 50px;' class="selectRole">
							<ul  style='width: 235px;height: 405px;overflow-y: auto;font-family: "微软雅黑";font-size: 14px;outline: none;margin-top: -30px;border:#666 solid 1px;'>
							<#list roleList as role>
								<li roleId="${role.id!}" descriptions="${role.descriptions!}" content="${role.content!}" version="${role.version!}"><a href="javascript:void(0);" name="${role.name}"> ${role.name}</a> </li>	
							</#list>
							</ul>
							</div>
						
					</#if>

                        <div class="creat_user">
                        	<input name="" type="button" value="删除角色" class="btn-style-b fr"id ="shanchu" >
                        	<input id="xinjian" name="" type="button" value="新建角色" class="btn-style-b fr" style="margin-left:0;">
                        </div>
					</div>
				</div>
				<div class="info_show fl">
                	<div class="info_user" id="default">角色管理</div>
                    <div class="info_con" id="defaultShow">
						<p>请在角色列表选择角色。</p>
					</div>
					<div class="info_user" id="clickShow" style="display:none;">角色管理<input name="" type="button" value="编辑角色" class="btn-style-b btn1 fr" id="bianji"></div>
					<div class="quan clearfix">
                       	<div class="bj_js" style="line-height: auto !important;">
                            <ul>
                                <li>角色名称：</li>
                                <li><span class="cRed">*</span> <input name="roleName" type="text" value="" class="js_1" style=" border: 1px solid #666;border-top:none;;border-right:none;;border-left:none;" /></li>
                                <li>角色描述：</li>
                                <li><input name="descripts" type="text" value="" class="js_2" style=" border: 1px solid #666;border-top:none;;border-right:none;;border-left:none;" /></li>
                                <li>备注：</li>
                                <li><input name="content" type="text" value="" class="js_3" style=" border: 1px solid #666;border-top:none;;border-right:none;;border-left:none;" /></li>
                            </ul>
                             <label for="" class="fl quanxuanlabel" style="margin: 10px 0 0 0;" ><div class="quanxuandiv"><img src="${uri.context_path}/images/03.jpg" class="tianChongQuanxuan" style="display:none;"><input type="checkbox" name="" id="quanxuan">全选</div></label>
                        </div>
                   </div>
                    <div class="info_con" id="clickShowAll" style="display:none;">
						<div class="quanxian_list">
                    	
                        <div id="loadTable">
                    	<table id="allPermission">
                         
                    	</table></div>
                    </div>
                    <div class="qd_btn">
                    	<a href="javascript:void(0);" class="btn-style-b btn1 quxiao" target="_self" id="quxiaoRole"   >取消</a>	
                    	<a href="javascript:void(0);" class="btn-style-b btn1 queding" target="_self" id="quedingRole">确定</a>
                    	<a href="javascript:void(0);" class="btn-style-b btn1 bianji" target="_self" id="bianjiRole"style="display:none;" >确定</a>
                    	         	
                    </div>
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
		var flag = false;
		//var navid = '${navId!}';   
  		//var pageid = '${pageId!}'; 
		//$(".header .menu").find("li[refId='"+navid+"']").addClass("current");
		//$(".header .menu").find("li[refId='"+navid+"']").click();
		//var relegation = $(".header .menu").find("li[refId='"+navid+"']").attr("relegation");
		//$(".sidenav").find("div[relegation='"+relegation+"']").show().siblings().hide();
		//$(".sidenav").find("li[refId='"+pageid+"']").addClass("current");
	    var allOption = '';
		var allPermission = ${allPermission!}; 
		var alldata = loadTable(allPermission);
		var backColor = $("#bianji").css("background");
		$("#allPermission").html(alldata);
			var map = {};
		
			<#if roleList??>
				<#list roleList as role> 
					var value = "";
					<#list role.permission as permissionList >
						 value =value + ${permissionList.id!} +"$$";
					</#list>
					map[${role.id!}] = value;
				</#list>
			</#if>
			//for(var prop in map){
    			//if(map.hasOwnProperty(prop)){
        			//alert('key is ' + prop +' and value is' + map[prop]);
    			//}
			//}
		$("#shanchu").css("background","#cbcbcb").attr("disabled","disabled");
		//$(".qd_list").html(allOption);
		$(".clearfix").hide();
		//点击角色列表
		$("#role_list").click(function(){
			var $option = $("#role_list").find("option:selected");	
			if(typeof($option.val())!="undefined"){
				$("#default").hide();
				$("#defaultShow").hide();
				$("#clickShow").show();
				$("#clickShowAll").show();
				$("#bianji").css("background",backColor).removeAttr("disabled");
				$("#shanchu").css("background",backColor).removeAttr("disabled");
				$("#quxiaoRole").hide();
				$("#bianjiRole").hide();
				$("#quedingRole").hide()
				var id = $option.attr("id");
				var name = $option.val();
				var desc = $option.attr("descriptions");
				var content = $option.attr("content");
				$(".bj_js ul li input[name='roleName']").val(name).attr("readonly","readonly").css("border","0");
				$(".bj_js ul li input[name='descripts']").val(desc).attr("readonly","readonly").css("border","0");
				$(".bj_js ul li input[name='content']").val(content).attr("readonly","readonly").css("border","0");
				var perId = map[id];
				var allPerId = perId.split("$$");
				$(".quanxuanlabel input").attr("disabled","disabled");
				$(".clearfix").show();
				var params = '';
				ajaxRequest({
					url:'${uri.context_path}/auth/roleManager/queryAllPer.htm',
           	 		postMethod:'POST',
            		params:params,
            		callback:function(data){
            			$("#loadTable").html(data);
        			}
				});
				flag = false;
			}		
			
			 
		});
		$(".selectRole ul li").click(function(){
				$(this).addClass("current").siblings().removeClass("current");
				$("#default").hide();
				$("#defaultShow").hide();
				$("#clickShow").show();
				$("#clickShowAll").show();
				$("#bianji").css("background",backColor).removeAttr("disabled");
				$("#shanchu").css("background",backColor).removeAttr("disabled");
				$("#quxiaoRole").hide();
				$("#bianjiRole").hide();
				$("#quedingRole").hide();
				var $a = $(this).find("a");
				var id = $(this).attr("roleId");
				var name = $a.attr("name");
				var desc = $(this).attr("descriptions");
				var content = $(this).attr("content");
				$(".bj_js ul li input[name='roleName']").val(name).attr("readonly","readonly").css("border","0");
				$(".bj_js ul li input[name='descripts']").val(desc).attr("readonly","readonly").css("border","0");
				$(".bj_js ul li input[name='content']").val(content).attr("readonly","readonly").css("border","0");
				var perId = map[id];
				var allPerId = perId.split("$$");
				$(".quanxuanlabel input").attr("disabled","disabled");
				$(".quanxuanlabel .quanxuandiv").hide();
				
				$(".clearfix").show();
				var params = '';
				ajaxRequest({
					url:'${uri.context_path}/auth/roleManager/queryAllPer.htm',
           	 		postMethod:'POST',
            		params:params,
            		callback:function(data){
            			$("#loadTable").html(data);
        			}
				});
				flag = false;
	   
		});
		$("#role_list").dblclick(function(){
			$("#bianji").click();
		});
		$(".selectRole ul li").dblclick(function(){
			//$("#bianji").click();
			$(this).attr("isdb","db");
		});
	    //新建角色
		$("#xinjian").click(function(){
			flag = true;
			$(".clearfix").show();
			$(".quanxuanlabel input").removeAttr("disabled");
			$(".quanxuanlabel input").prop("checked",false);
			$("#bianjiRole").hide();
			$("#quedingRole").show();
			$("#quxiaoRole").show();
			$(".tianChong").hide();
			$(".bj_js ul li input[name='roleName']").val("").removeAttr("readonly").css("border","#666 solid 1px").css("border-top","none").css("border-right","none").css("border-left","none");
			$(".bj_js ul li input[name='descripts']").val("").removeAttr("readonly").css("border","#666 solid 1px").css("border-top","none").css("border-right","none").css("border-left","none");
			$(".bj_js ul li input[name='content']").val("").removeAttr("readonly").css("border","#666 solid 1px").css("border-top","none").css("border-right","none").css("border-left","none");
			$("#default").hide();
			$("#defaultShow").hide();
			$("#clickShow").show();
			$("#clickShowAll").show();
			$("#bianji").css("background","#cbcbcb").attr("disabled","disabled");
			$("#shanchu").css("background","#cbcbcb").attr("disabled","disabled");
			$("#allPermission input").each(function(){
				$(this).prop("checked",false);
				$(this).removeAttr("disabled");
				$(this).parent().css("color","");
			});
		});
		//编辑角色
		$("#bianji").click(function(){
			$("#xinjian").css("background","#cbcbcb").attr("disabled","disabled");
			$("#shanchu").css("background","#cbcbcb").attr("disabled","disabled");
			$("#bianjiRole").show();
			$("#quedingRole").hide();
			$("#quxiaoRole").show();
			$(".bj_js ul li input[name='roleName']").removeAttr("readonly").css("border","#666 solid 1px").css("border-top","none").css("border-right","none").css("border-left","none");
			$(".bj_js ul li input[name='descripts']").removeAttr("readonly").css("border","#666 solid 1px").css("border-top","none").css("border-right","none").css("border-left","none");
			$(".bj_js ul li input[name='content']").removeAttr("readonly").css("border","#666 solid 1px").css("border-top","none").css("border-right","none").css("border-left","none");
			$(".quanxuanlabel .quanxuandiv").show();
			
			$("#allPermission input").each(function(){				
				$(this).removeAttr("disabled");
				$(this).parent().css("color","");
			});
			$(".quanxuanlabel input").removeAttr("disabled");
			flag = true;
		});
		//编辑角色确定
		$("#bianjiRole").click(function(){
			//$("#shanchu").css("background","#cbcbcb").attr("disabled","disabled");
			var id = $(".selectRole ul li[class='current']").attr("roleId");
			var version = $(".selectRole ul li[class='current']").attr("version");
			var oldName = $(".selectRole ul li[class='current']").find("a").attr("name");
			//var oldName = $("#role_list").find("option:selected").val();
			//var version = $("#role_list").find("option:selected").attr("version");
			//var id = $("#role_list").find("option:selected").attr("id");
			var roleName = $(".bj_js ul li input[name='roleName']").val();
			var descripts = $(".bj_js ul li input[name='descripts']").val();
			var content = $(".bj_js ul li input[name='content']").val();
			if(roleName == null || "" == roleName){
				alert("角色名称为空!");
				return false;
			}
			var allCnt = 0;
			var allPerId = "";
			$("#allPermission input").each(function(){
				if($(this).prop("checked")){
					var id = $(this).attr("id");
					if(id!="quanxuan"){
						allCnt = allCnt + 1;
						allPerId = allPerId +id+":";
					}
					
				}
			});
			if(allCnt == 0){
				alert("请给角色赋（至少一个）权限!");
				return false;
			}
			var status = "0";
			var params = "name="+roleName+"&descriptions="+descripts+"&content="+content+"&allPerId="+allPerId+"&status="+status+"&id="+id+"&oldName="+oldName+"&version="+version;
			ajaxRequest({
				url:'${uri.context_path}/auth/roleManager/updateRole.htm',
           	 	postMethod:'POST',
            	params:params,
            	callback:function(data){
            		var res = String($.trim(data));
					if(res =='error'){
						alert("角色名称与现有角色名称重复!");
						return false;
					} else if(res == 'updateError'){
						alert("更新失败!");
						return false;
					}
					else if(res == 'success'){
						alert("更新角色成功");
						var li = $('.sidenav',parent.document).find('ul li[class="current"]');
						li.click();
						/*var $a = $('.sidenav',parent.document).find('ul li[class="current"]').find('a');
						var url = $a.attr('url');
						$a.click(function(){
							window.location.href=url;
						});	
						$a.click();*/
					}
        		}
			});
		});
	//删除权限
	$("#shanchu").click(function() {
		var id = $(".selectRole ul li[class='current']").attr("roleId");
		var oldName = $(".selectRole ul li[class='current']").find("a").attr("name");
		//var oldName = $("#role_list").find("option:selected").val();
		//var id = $("#role_list").find("option:selected").attr("id");
		var roleName = $(".bj_js ul li input[name='roleName']").val();
		var descripts = $(".bj_js ul li input[name='descripts']").val();
		var content = $(".bj_js ul li input[name='content']").val();
		
		
		var params = "name="+roleName+"&descriptions="+descripts+"&content="+content+"&id="+id;
		ajaxRequest({
			url:'${uri.context_path}/auth/roleManager/queryDeleteRole.htm',
            postMethod:'POST',
            params:params,
            callback:function(data){
            	var res = String($.trim(data));
            	if(res == 'error'){
            		alert("所选角色下有未解绑用户，请将用户全部解绑后重试!");
					return false;
            	}
				else if(res == 'success'){
					if(confirm("确定删除该角色吗？")){
						ajaxRequest({
							url:'${uri.context_path}/auth/roleManager/deleteRole.htm',
            				postMethod:'POST',
            				params:params,
            				callback:function(data){
            					var ress = String($.trim(data));
            					if(ress == 'deleteError'){
            						alert("删除失败!");
									return false;
            					}else if(ress == 'success'){
            						alert("删除角色成功!");
            						var li = $('.sidenav',parent.document).find('ul li[class="current"]');
									li.click();
									/*var $a = $('.sidenav',parent.document).find('ul li[class="current"]').find('a');
									var url = $a.attr('href');
									$a.click(function(){
										location.href=url;					
									});	
									$a.click();*/	
            					}
            				}
            			});
					}
						
				}
        	}
		});
		
	});
		
		$(".quanxuanlabel").click(function(){
			if(flag){
				var checked = $(".quanxuanlabel input").prop("checked");
				var display = $(".tianChongQuanxuan").css("display");
				if(display !="none"){				
					$(".quanxuanlabel input").prop("checked",true);
					checked = $(".quanxuanlabel input").prop("checked");
				}
	  			$("#allPermission input").prop("checked",checked);
	  
	  			$(".tianChongQuanxuan").hide();
	  			$(".navType").each(function(){
					var name = $(this).attr("name");					
					var navimageId = "#" + name+"Image"; 				
					$(navimageId).hide();
				
				});
				$(".menuType").each(function(){
					var name = $(this).attr("name");
					var menuImageId = "#"+name+"Image";				
					$(menuImageId).hide();		
				});
			}
			
		});
		$("#quxiaoRole").click(function(){
			$("#default").show();
			$("#defaultShow").show();
			$("#clickShow").hide();
			$("#clickShowAll").hide();
			$("#xinjian").css("background",backColor).removeAttr("disabled");
			$(".clearfix").hide();
			
		});
		$("#quedingRole").click(function(){
			var roleName = $(".bj_js ul li input[name='roleName']").val();
			var descripts = $(".bj_js ul li input[name='descripts']").val();
			var content = $(".bj_js ul li input[name='content']").val();
			if(roleName == null || "" == roleName){
				alert("角色名称为空!");
				return false;
			}
			var allCnt = 0;
			var allPerId = "";
			$("#allPermission input").each(function(){
				if($(this).prop("checked")){
					var id = $(this).attr("id");
					if(id!="quanxuan"){
						allCnt = allCnt + 1;
						allPerId = allPerId +id+":";
					}
				}
			});
			if(allCnt == 0){
				alert("请给角色赋（至少一个）权限!");
				return false;
			}
			var status = "0";
			var params = "name="+roleName+"&descriptions="+descripts+"&content="+content+"&allPerId="+allPerId+"&status="+status;
			ajaxRequest({
				url:'${uri.context_path}/auth/roleManager/addRole.htm',
           	 	postMethod:'POST',
            	params:params,
            	callback:function(data){
            		var res = String($.trim(data));
					if(res =='error'){
						alert("角色名称与现有角色名称重复!");
						return false;
					} 
					else if(res == 'success'){
						alert("新建角色成功");
						var li = $('.sidenav',parent.document).find('ul li[class="current"]');
						li.click();
						/*$('.btnClose').click();
						var $a = $('.sidenav',parent.document).find('ul li[class="current"]').find('a');
						var url = $a.attr('href');
						$a.click(function(){
							location.href=url;					
						});	
						$a.click();*/		
					}
        		}
			});
		});
		//导航栏全选
		$(".navType").click(function(){
			var name = $(this).attr("name");
			var selfImageId = "#"+name+"Image";
			var display = $(selfImageId).css("display");
			if(display !="none"){				
				$(this).prop("checked",true);
			}
			$(selfImageId).hide();
			$("#allPermission input[parent='"+name+"']").prop("checked",$(this).prop("checked"));
			$("#allPermission input[parent='"+name+"']").each(function(){
				var imageId = "#" + $(this).attr("name") +"Image";
				$(imageId).hide();
			});
			$("#allPermission input[grand='"+name+"']").prop("checked",$(this).prop("checked"));
			
			var allSize =  $("#allPermission input[type=checkbox]").size();
			var allCnt = 0;
			var quanxuanImage = ".tianChongQuanxuan";
			$("#allPermission input[type=checkbox]").each(function(){
				if($(this).prop("checked")){
					allCnt = allCnt + 1;
				}
			});		
			if(allSize != allCnt && allCnt!=0){
				$(".quanxuanlabel input[type=checkbox]").prop("checked",false);
				$(quanxuanImage).show();
			}else if(allCnt ==0){
				$(".quanxuanlabel input[type=checkbox]").prop("checked",false);
				$(quanxuanImage).hide();
			}else{
				$(".quanxuanlabel input[type=checkbox]").prop("checked",true);
				$(quanxuanImage).hide();
			}
			
		});
		//菜单栏全选
		$(".menuType").click(function(){
			
			var name = $(this).attr("name");
			var selfImageId = "#"+name+"Image";
			var display = $(selfImageId).css("display");
			if(display !="none"){				
				$(this).prop("checked",true);
			}
			$(selfImageId).hide();
			$("#allPermission input[parent='"+name+"']").prop("checked",$(this).prop("checked"));
			
			var parentId = $(this).attr("parent");
			var $parent = $("#allPermission input[name='"+parentId+"']");
			var parentSize = $("#allPermission input[parent='"+parentId+"']").size();	
			var parentCnt = 0;	
			var parentimageId = "#"+parentId+"Image";
			$("#allPermission input[parent='"+parentId+"']").each(function(){
				if($(this).prop("checked")){
					parentCnt = parentCnt + 1;
				}
			});	
						
			if(parentCnt != parentSize && parentCnt != 0 ){
				$(parentimageId).show();
				$parent.prop("checked",true);
			}else if(parentCnt == 0){
				$parent.prop("checked",false);
				$(parentimageId).hide();
			}
			else{
				$parent.prop("checked",true);
				$(parentimageId).hide();
			}
			
			var grandSize = $("#allPermission input[grand='"+parentId+"']").size();
			var grandCnt = 0;
			$("#allPermission input[grand='"+parentId+"']").each(function(){
				if($(this).prop("checked")){
					grandCnt = grandCnt + 1;
				}
			});
			if(grandSize!=grandCnt && grandCnt!=0){
				$(parentimageId).show();
				$parent.prop("checked",true);
			}else if(grandCnt == 0){
				$parent.prop("checked",false);
				$(parentimageId).hide();
			}else{
				$parent.prop("checked",true);
				$(parentimageId).hide();
			}
			
			var allSize =  $("#allPermission input[type=checkbox]").size();
			var allCnt = 0;
			var quanxuanImage = ".tianChongQuanxuan";
			$("#allPermission input[type=checkbox]").each(function(){
				if($(this).prop("checked")){
					allCnt = allCnt + 1;
				}
			});		
			if(allSize != allCnt && allCnt!=0){
				$(".quanxuanlabel input[type=checkbox]").prop("checked",false);
				$(quanxuanImage).show();
			}else if(allCnt ==0){
				$(".quanxuanlabel input[type=checkbox]").prop("checked",false);
				$(quanxuanImage).hide();
			}else{
				$(".quanxuanlabel input[type=checkbox]").prop("checked",true);
				$(quanxuanImage).hide();
			}
			
		});
		//权限
		$("#allPermission ul li input").click(function(){
			var parentId = $(this).attr("parent");
			var $parent = $("#allPermission input[name='"+parentId+"']");
			var parentSize = $("#allPermission ul li input[parent='"+parentId+"']").size();
			var parentCnt = 0;
			var parentimageId = "#"+parentId+"Image";
			$("#allPermission ul li input[parent='"+parentId+"']").each(function(){
				if($(this).prop("checked")){
					parentCnt = parentCnt+1;
				}
			});
			if(parentCnt != parentSize && parentCnt!=0){
				$parent.prop("checked",true);
				$(parentimageId).show();
			}else if(parentCnt == 0){
				$parent.prop("checked",false);
				$(parentimageId).hide();
			}else{
				$parent.prop("checked",true);
				$(parentimageId).hide();
			}
			
			var grandId  = $(this).attr("grand");
			var $grand = $("#allPermission input[name='"+grandId+"']");
			var grandSize = $("#allPermission ul li input[grand='"+grandId+"']").size();
			var grandCnt = 0;
			var grandimageeId = "#"+grandId+"Image";
			var grandChildSize = $("#allPermission input[parent='"+grandId+"']").size();
			var grandChildCnt = 0;
			$("#allPermission ul li input[grand='"+grandId+"']").each(function(){
				if($(this).prop("checked"))
					grandCnt = grandCnt + 1;
			});
			$("#allPermission input[parent='"+grandId+"']").each(function(){
				if($(this).prop("checked"))
					grandChildCnt = grandChildCnt + 1;
			});
			if((grandCnt == grandSize) && (grandChildSize==grandChildCnt)){
				$grand.prop("checked",true);
				$(grandimageeId).hide();
			}else if(grandCnt!=0){
				$grand.prop("checked",true);
				$(grandimageeId).show();
			}else{
				$grand.prop("checked",false);
				$(grandimageeId).hide();
			}
						
			var allSize =  $("#allPermission input[type=checkbox]").size();
			var allCnt = 0;
			var quanxuanImage = ".tianChongQuanxuan";
			$("#allPermission input[type=checkbox]").each(function(){
				if($(this).prop("checked")){
					allCnt = allCnt + 1;
				}
			});		
			if(allSize != allCnt && allCnt!=0){
				$(".quanxuanlabel input[type=checkbox]").prop("checked",false);
				$(quanxuanImage).show();
			}else if(allCnt ==0){
				$(".quanxuanlabel input[type=checkbox]").prop("checked",false);
				$(quanxuanImage).hide();
			}else{
				$(".quanxuanlabel input[type=checkbox]").prop("checked",true);
				$(quanxuanImage).hide();
			}
		});
	 });

 function loadTable(json){
  	var instLen = json.length;
		var tablebodyFirst = '';
  		for (var i =  0; i < instLen; i++){
  			var navLen = 0;
  			if(json[i].list != null){
				navLen = json[i].list.length;
  			} 
			for(var j=0;j<navLen;j++){
				var navId = json[i].list[j].id;
				var navName = json[i].list[j].name; 
				var navPermissionID = json[i].list[j].permissionId;
				var menuLen = 0;
				if(json[i].list[j].list!=null)
					 menuLen = json[i].list[j].list.length;
				tablebodyFirst = tablebodyFirst + '<tr class="navTr" nav="'+navPermissionID+'"><td colspan="2" class="line"><label><img src="${uri.context_path}/images/03.jpg" class="tianChong" id="'+navPermissionID+'Image" style="display:none;"><input type="checkbox" name="'+navPermissionID+'" id="'+navId+'" class="navType" >  '+navName+'</input></label></td></tr>';
				 
				for(var k=0;k<menuLen;k++){
					var menuId = json[i].list[j].list[k].id;
					var menuName = json[i].list[j].list[k].name; 
					var menuPermissionID = json[i].list[j].list[k].permissionId;
					var pageLen = 0;
					if(json[i].list[j].list[k].list!=null)
						pageLen = json[i].list[j].list[k].list.length;
					if(k==0)
						tablebodyFirst = tablebodyFirst +'<tr><td width="20%" rowspan="'+menuLen+'"></td><td class="menuTd" menu="'+menuPermissionID+'" ><label><img src="${uri.context_path}/images/03.jpg" class="tianChong" id="'+menuPermissionID+'Image" style="display:none;"><input type="checkbox" name="'+menuPermissionID+'" id="'+menuId+'" parent="'+navPermissionID+'" class="menuType">'+menuName+'</input></label><ul>';
					else{
						tablebodyFirst = tablebodyFirst +'<tr><td class="menuTd" menu="'+menuPermissionID+'"><label><img src="${uri.context_path}/images/03.jpg" class="tianChong" id="'+menuPermissionID+'Image" style="display:none;"><input type="checkbox" name="'+menuPermissionID+'" id="'+menuId+'" parent="'+navPermissionID+'" class="menuType">'+menuName+'</input></label><ul>';
					}
					for(var l=0;l<pageLen;l++){
						var pageId = json[i].list[j].list[k].list[l].id;
						var pageName = json[i].list[j].list[k].list[l].name; 
						var pagePermissionID = json[i].list[j].list[k].list[l].permissionId;
						var pagedescriptions = json[i].list[j].list[k].list[l].descriptions;
						tablebodyFirst = tablebodyFirst +'<li><input type="checkbox" permissionId = "' +pagePermissionID+'" name="page" id="'+pageId+'" value="'+pageName+'" descriptions="'+pagedescriptions+'" grand="'+navPermissionID+'" parent="'+menuPermissionID+'" />'+pageName+'</li>';
					}
					if(k==0)
						tablebodyFirst = tablebodyFirst +'</ul></td></tr>';
					else{
						tablebodyFirst = tablebodyFirst +'</ul></td></tr>';
					}
				}
				
			}
		}
		return tablebodyFirst;
  }

</script>
</body>
</html>