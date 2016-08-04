<#import "/uri.ftl" as uri />
<table id="allPermission">
                         
</table>
<script>
$(function(){
		var allPermission = ${allPermission!}; 
		var per = loadTable(allPermission);
		$("#allPermission").html(per);
		var map = {};
		var value = "";
			<#if roleList??>
				<#list roleList as role> 
					 value = "";
					<#list role.permission as permissionList >
						 value =value + ${permissionList.id!} +"$$";
					</#list>
					map[${role.id!}] = value;
				</#list>
			</#if>
			var $option = $(".selectRole ul li[class='current']");
			var id = $option.attr("roleId");
			var perId = map[id];
			 var allPerId = perId.split("$$");
			$("#allPermission input").each(function(){
				$(this).prop("checked",false);
				var id = $(this).attr("id");
				for(var i=0;i<allPerId.length-1;i++){
					if(id == allPerId[i]){
						$(this).prop("checked",true);
					}
				}
				$(this).attr("disabled","disabled");
				$(this).parent().css("color","#c0c0c0");
			});
			;
			var db = $(".selectRole ul li[class='current']").attr("isdb");	
			if(db == 'db'){
				$(".selectRole ul li[class='current']").removeAttr("isdb");
				$("#bianji").click();
			}
			$(".navType").each(function(){
			
				var name = $(this).attr("name");
				var navimageId = "#" + name+"Image";
				
				var grandSize = $("#allPermission ul li input[grand='"+name+"']").size();
				var grandCnt = 0;				
				$("#allPermission ul li input[grand='"+name+"']").each(function(){
					if($(this).prop("checked")){
						grandCnt = grandCnt + 1;
					}
				});
				
				var childSize = $("#allPermission input[parent='"+name+"']").size();
				var childCnt = 0;				
				$("#allPermission input[parent='"+name+"']").each(function(){
					if($(this).prop("checked")){
						childCnt = childCnt + 1;
					}
				});
				if((grandCnt == grandSize) && (childSize==childCnt) && (grandSize != 0 && childSize!=0)){
					$(this).prop("checked",true);
					$(navimageId).hide();
				}else if(grandCnt!=0){
					$(this).prop("checked",true);
					$(navimageId).show();
				}else if(grandSize == 0 && childSize==0){
					
				}else{
					$(this).prop("checked",false);
					$(navimageId).hide();
				}
				
				 
			});
			$(".menuType").each(function(){
				var name = $(this).attr("name");
				var menuImageId = "#"+name+"Image";
				var childsize = $("#allPermission ul li input[parent='"+name+"']").size();
				var chidcnt = 0;
				$("#allPermission ul li input[parent='"+name+"']").each(function(){
					if($(this).prop("checked")){
						chidcnt = chidcnt + 1;
					}
				});
				if(chidcnt != childsize && chidcnt != 0 ){
					$(menuImageId).show();
				}else if(chidcnt == 0 ){
					$(menuImageId).hide();
				}
				else{
				 	$(menuImageId).hide();
				}
			});
		
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
			if(typeof($(".quanxuanlabel input").attr("disabled"))=="undefined"){
			 	$("#allPermission input").each(function(){				
					$(this).removeAttr("disabled");
				});
		 }
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