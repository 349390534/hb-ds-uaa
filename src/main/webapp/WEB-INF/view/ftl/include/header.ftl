<#import "/uri.ftl" as uri />
<div class="header" style="position: fixed;z-index: 9999;">
	<div class="logobox fl"><a alt="" class="pngFix" href=""></a></div>
	<ul class="menu fl">
		
	</ul>
	<div class="tool fr">
		<a title="设置" href="javascript:void(0);"><img class="pngFix" alt="" src="${uri.context_path}/images/gear.png"></a>
		<a title="用户" href="javascript:void(0);" ><img class="pngFix" alt="" src="${uri.context_path}/images/user.png"></a>
		<a href="javascript:void(0);" title="退出" id="loginout"><img class="exit_sys" alt="" src="${uri.context_path}/images/exit.png"></a>
	</div>
	
</div>
<@uri.script src=["/common.js",
	"/jquery/jquery.blockUI.js"
	]/>
<script type="text/javascript">
window.uaa_context_path = "${uri.context_path}";
 function loadHead(json,allPerList){
	var instLen = json.length;
	var menuFl = '';
	var sideNav = '';
	for (var i =  0; i < instLen; i++){
		var navLen = 0;
		if(json[i].list != null){
			navLen = json[i].list.length;
		}
		for(var j=0;j<navLen;j++){
			var navId = json[i].list[j].permissionId;
			var navName = json[i].list[j].name; 
			var navPermissionID = json[i].list[j].permissionId;
			var navUrl = 'javascript:void(0);';
			var hasNav = hasPerId(navId,allPerList);
			 if(hasNav){
			 	if(json[i].list[j].url=='' || null ==json[i].list[j].url){
					navUrl = 'javascript:void(0);';
				}else
			 		navUrl = '${uri.context_path}'+json[i].list[j].url;			 	
			 }					 	
			 else
			 	navUrl = 'javascript:void(0);';	
			var menuLen = 0;
			if(json[i].list[j].list!=null)
				 menuLen = json[i].list[j].list.length;
			menuFl = menuFl + '<li relegation="'+navPermissionID+'" refId="'+navId+'"><a url="'+navUrl +'" target="_self" navid="'+navId+'" >'+navName+'</a></li>';
				
				
			}
		}
		return menuFl;
  }

  $(function(){
  	var allPermission =[]; 
  	var allPerList = [];
  	<#if Session.headPermission??>
  		allPermission = ${Session.headPermission};
  	</#if>
  	<#if Session.headPermission??>
  		allPerList = ${Session.allPerList};
  	</#if>
	var allPerm = loadHead(allPermission,allPerList);
	$(".header .menu").html(allPerm);
	
	$("#loginout").click(function(){
		if(confirm("是否退出系统?")){
			var url = '${uri.context_path}/login/loginout.htm';
			location.href=url;		
		}
	});
  });
  
 </script> 
