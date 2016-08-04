<#import "/uri.ftl" as uri />
<div class="info_list">
    <table id="userBaseInfo">
        <tr>
           <td width="10%">用户名：</td>
            <td width="39%"><input value="" type="text" name="userName" class="user_name1" style=" border: 1px solid #666;border-top:none;;border-right:none;;border-left:none;"/></td>
            <td width="10%">中文名：</td>
            <td width="9%"><input value="" type="text" name="chineseName"  class="user_name2" style=" border: 1px solid #666;border-top:none;;border-right:none;;border-left:none;"/></td>
            <td width="32%" colspan="2" class="submit_btn" id="bianjiUser"><input name="" type="button" value="编辑基本信息" class="btn-style-b btn2" id="bianji"/>
			<input name="" type="button" value="新建用户信息" class="btn-style-b btn2" id="xinjianyonghu" style="display:none;"/></td>
            <td width="16%" class="submit_btn sure" style="display:none;"><input name="" type="button" value="确  定" class="btn-style-b btn1" id="queding" /></td>
			<td width="16%" class="sure" style="display:none;"><input name="" type="button" value="取 消" class="btn-style-b btn1" id="quxiao"/></td>	
       </tr>
       <tr>
          <td>邮   箱：</td>
          <td><input value="" type="text" name="email" class="user_mail" style=" border: 1px solid #666;border-top:none;;border-right:none;;border-left:none;"/></td>
          <td>手   机：</td>
          <td><input value="" type="text" name="telphone" class="user_phone" style=" border: 1px solid #666;border-top:none;;border-right:none;;border-left:none;"/><input value="" type="text" name="version" class="version" style="display:none;"></td>
          <td width="16%" class="submit_btn"><input name="" type="button" value="编辑用户角色" class="btn-style-b btn2"  id="bianjiRole" /></td>
          <td width="16%"><input name="" type="button" value="编辑用户权限" class="btn-style-b btn2" id="bianjiPer"/></td>
       </tr>
   </table>
   <p id="userName" style="padding: 10px 0;display:none;"> </p>
   <div class="flo_nav_wrap" style="display:none;">
         <table>
             <tr>
                <th width="25%">角色名称</th>
                <th width="50%">角色描述</th>
                <th width="25%">备注</th>
             </tr>
         </table>

 	</div>
 	
</div>

<!--编辑角色-->
<div class="user_list" style="display:none;padding: 0;margin: 0;">
   
       <table id="roleInfo">                        
       </table>
</div>
<div class="qd_btn" style="display:none;" id="qd_btn_role"><a href="javascript:void(0);" class="btn-style-b btn1" target="_self" " id="quxiaoRole">取消</a>&nbsp;&nbsp;<a href="javascript:void(0);" class="btn-style-b btn1" target="_self" id="quedingRole">确定</a></div>
<!--编辑角色end-->
<!--编辑权限-->
<div class="quan clearfix" style="display:none;" id="quanxianquan">
                        <p class="p1" id="userquanxian"></p>
                        <label for="" class="fl quanxuanlabel"><img src="${uri.context_path}/images/03.jpg" class="tianChong" id="quanxuanImage" style="display:none;"><input type="checkbox" name="" id="quanxuan">全选</label>
    </div>
<div class="quanxian_list" style="display:none;height: 210px;"">
    <table id="allPer">            
     </table>
 </div>
 <div class="qd_btn" id="qd_btn_per" style="display:none;"><a href="javascript:void(0);" class="btn-style-b btn1" target="_self"  id="quxiaoquanxian">取消</a>&nbsp;&nbsp;<a href="javascript:void(0);" class="btn-style-b btn1" target="_self" id="quedingquanxian">确定</a></div>
<!--编辑权限end-->
<@uri.script src=["/uaa_permission.js"]/>
<script type="text/javascript">
$(function(){
	context_path = '${uri.context_path}';
	<#if userInfo??>
		 userName = '${userInfo.userName!}';
		 chineseName = '${userInfo.chineseName!}';
		 email = '${userInfo.email!}';
		 telphone = '${userInfo.telphone!}';
		 userId = ${userInfo.id!0};

		 version = ${userInfo.version!0};
		 $("#xinjianyonghu").hide();
		$("#bianji").show();
   </#if>
   <#if userLdap??>
		 chineseName = '${userLdap.userName!}';
		 userName = '${userLdap.account!}';
		 email = '${userLdap.email!}';
		 telphone = '${userLdap.telphone!}';
		 userId = ${userLdap.id!0};
		 version = ${userLdap.version!0};
		$("#xinjianyonghu").show();
		$("#bianji").hide();
		$("#bianjiRole").removeClass("btn-style-b").addClass(
					"unable-style-b").attr("disabled", "disabled");
		$("#bianjiPer").removeClass("btn-style-b").addClass(
					"unable-style-b").attr("disabled", "disabled");
	</#if>
		
	<#if userRoleList??>
		userRoleListJson = ${userRoleList};
	</#if>
	<#if rolePerList??>
		rolePerListJson = ${rolePerList};
	</#if>
	<#if perList??>
		perListJson = ${perList};
	</#if>
	<#if roleList??>
		roleListJson = ${roleList};
	</#if>
	<#if userperlist??>
		userperlistJson = ${userperlist};
	</#if>
	
	<#if allPermission??>
		allPermissionJson=${allPermission!};
		loadquanxianTable(allPermissionJson);
		checkAllPer();
	</#if>
	 //赋值
	 giveValue();
	 <#if userLdap??>
	 $("#userBaseInfo input[name='chineseName']").removeAttr("readonly")
					.css("border", "#666 solid 1px").css("border-top","none").css("border-right","none").css("border-left","none");
		$("#userBaseInfo input[name='email']").removeAttr(
					"readonly").css("border", "#666 solid 1px").css("border-top","none").css("border-right","none").css("border-left","none");
		$("#userBaseInfo input[name='telphone']").removeAttr(
					"readonly").css("border", "#666 solid 1px").css("border-top","none").css("border-right","none").css("border-left","none");
	</#if>
	 
	  $(".quanxuanlabel").click(function(){ 
	 	 var checked = $(".quanxuanlabel input").prop("checked");
	 	 var display = $("#quanxuanImage").css("display");
	 	 
		 if(display !="none"){				
		 	$(".quanxuanlabel input").prop("checked",true);
			checked = $(".quanxuanlabel input").prop("checked");
			} 
	  	$("#quanxuanImage").hide();  	
	  	
	  	// var checked = $(this).prop("checked");
	  	  $("#allPer input").each(function(){ 
	  	  		var name = $(this).attr("disabled");
	  	  		 if(name != 'disabled'){
	  				$(this).prop("checked",checked);
	  			}
	  	});
	  
	  		var allSize =  $("#allPer input[type=checkbox]").size();
			var allCnt = 0;
			var quanxuanImage = "#quanxuanImage";
			$("#allPer input[type=checkbox]").each(function(){
				if($(this).prop("checked")){
					allCnt = allCnt + 1;
				}
			});		
			if(allSize != allCnt && allCnt!=0){
				$(".quanxuanlabel input[type=checkbox]").prop("checked",true);
				$(quanxuanImage).show();
			}else if(allCnt ==0){
				$(".quanxuanlabel input[type=checkbox]").prop("checked",false);
				$(quanxuanImage).hide();
			}else{
				$(".quanxuanlabel input[type=checkbox]").prop("checked",true);
				$(quanxuanImage).hide();
			}
	  checkeach();
});
// 导航栏全选
$(".navType").click(function(){
	var checked = $(this).prop("checked");
	var name = $(this).attr("name");
	var selfImageId = "#" + name + "Image";
	var display = $(selfImageId).css("display");
	if(display !="none"){				
		$(this).prop("checked",true);
		checked = $(this).prop("checked");
	}
	$(selfImageId).hide();
	var count = 0;
	var countsize = $("#allPer input[grand='" + name + "']").size();

	// $("#allPer
	// input[parent='"+name+"']").prop("checked",$(this).prop("checked"));
	$("#allPer input[grand='" + name + "']").each(function() {
		var name = $(this).attr("disabled");
		if (name != 'disabled') {
			$(this).prop("checked", checked);
		}
		if ($(this).prop("checked")) {
			count = count + 1;
		}
	});
	$("#allPer input[parent='" + name + "']").each(function() {
		var imageId = "#" + $(this).attr("name") + "Image";
		$(imageId).hide();
		var name = $(this).attr("disabled");
		if (name != 'disabled') {
			$(this).prop("checked", checked);
		}
		var chname = $(this).attr("name");
		var ccount = 0;
		var ccountsize = $("#allPer input[parent='" + chname + "']").size();
		$("#allPer input[parent='" + chname + "']").each(function() {
			if ($(this).prop("checked")) {
				ccount = ccount + 1;
			}
		});
		if (ccountsize == ccount && ccountsize != 0) {
			// $(this).prop("checked","checked");
			$(imageId).hide();
		} else if (ccount != 0) {
			$(imageId).show();
		}
	});

	var size = $(".navType").size();
	var cnt = 0;
	$(".navType").each(function() {
		if ($(this).prop("checked")) {
			cnt = cnt + 1;
		}
	});

	if (countsize == count && countsize != 0) {
		$(this).prop("checked", "checked");
		$(selfImageId).hide();
	} else if (count != 0) {
		$(selfImageId).show();
	}
	
	var allSize =  $("#allPer input[type=checkbox]").size();
			var allCnt = 0;
			var quanxuanImage = "#quanxuanImage";
			$("#allPer input[type=checkbox]").each(function(){
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

// 菜单栏全选
$(".menuType").click(function() {
	var checked = $(this).prop("checked");
	var name = $(this).attr("name");
	var count = 0;
	var selfImageId = "#" + name + "Image";
	var display = $(selfImageId).css("display");
	if(display !="none"){				
		$(this).prop("checked",true);
		checked = $(this).prop("checked");
	}
	$(selfImageId).hide();
	var countsize = $("#allPer input[parent='" + name + "']").size();
	$("#allPer input[parent='" + name + "']").each(function() {
		var name = $(this).attr("disabled");
		if (name != 'disabled') {

			$(this).prop("checked", checked);
		}
		if ($(this).prop("checked")) {
			count = count + 1;
		}
	});
	// prop("checked",$(this).prop("checked"));
	var parentId = $(this).attr("parent");
	var $parent = $("#allPer input[name='" + parentId + "']");
	var size = $("#allPer input[parent='" + parentId + "']").size();

	var parentCnt = 0;
	$("#allPer input[parent='" + parentId + "']").each(function() {
		if ($(this).prop("checked")) {
			parentCnt = parentCnt + 1;
		}
	});

	var parentimageId = "#" + parentId + "Image";
	if (parentCnt != size && parentCnt != 0) {
		$(parentimageId).show();
		$parent.prop("checked", true);
	} else if (parentCnt == 0) {
		if (count != 0) {
			$(parentimageId).show();
		} else {
			$parent.prop("checked", false);
			$(parentimageId).hide();
		}

	} else {
		$parent.prop("checked", true);
		$(parentimageId).hide();
	}
	var allSize = $("#allPer input").size() - 1;
	var allCnt = 0;
	$("#allPer input").each(function() {
		var id = $(this).attr("id");
		if (id != "quanxuan") {
			if ($(this).prop("checked")) {
				allCnt = allCnt + 1;
			}
		}

	});
	// alert(count);
	if (countsize == count && countsize != 0) {
		$(this).prop("checked", "checked");
		$(selfImageId).hide();
	} else if (count != 0) {
		$(selfImageId).show();
	}
	
			var allSize =  $("#allPer input[type=checkbox]").size();
			var allCnt = 0;
			var quanxuanImage = "#quanxuanImage";
			$("#allPer input[type=checkbox]").each(function(){
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
// 权限
$("#allPer ul li input").click(function() {
			var parentId = $(this).attr("parent");
			var $parent = $("#allPer input[name='"+parentId+"']");
			var parentSize = $("#allPer ul li input[parent='"+parentId+"']").size();
			var parentCnt = 0;
			var parentimageId = "#"+parentId+"Image";
			$("#allPer ul li input[parent='"+parentId+"']").each(function(){
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
			var $grand = $("#allPer input[name='"+grandId+"']");
			var grandSize = $("#allPer ul li input[grand='"+grandId+"']").size();
			var grandCnt = 0;
			var grandimageeId = "#"+grandId+"Image";
			var grandChildSize = $("#allPer input[parent='"+grandId+"']").size();
			var grandChildCnt = 0;
			$("#allPer ul li input[grand='"+grandId+"']").each(function(){
				if($(this).prop("checked"))
					grandCnt = grandCnt + 1;
			});
			$("#allPer input[parent='"+grandId+"']").each(function(){
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
			
			var allSize =  $("#allPer input[type=checkbox]").size();
			var allCnt = 0;
			var quanxuanImage = "#quanxuanImage";
			$("#allPer input[type=checkbox]").each(function(){
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
</script>
</body>
</html>