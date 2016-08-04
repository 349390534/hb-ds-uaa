<#import "/uri.ftl" as uri />
<!DOCTYPE HTML>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<meta name="Description" content="" />
	<meta name="Keywords" content="" />
	<title>权限管理</title>
	<@uri.link href = ["/reset.css","/style.css","/change.css"]/>
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
				<div class="info_show fl" style="padding-left:20px;">
                	<div class="info_user">权限管理<input id="shanchu" name="shanchu" type="button" value="删除权限" class="btn-style-b btn1 fr"><input name="bianji" type="button" value="编辑权限" class="btn-style-b mr10 btn1 fr" id="bianji"><input name="" type="button" value="新建权限" class="btn-style-b btn1 mr10 fr" id="xinjian"></div>
                    <div class="info_con">
						<div class="quanxian_list" style=" margin-top:15px;height: 390px;">
                    		<table id="perTable" >
                         
                    		</table>
                    	</div>
                    <!--<div class="qd_btn"><a href="" class="btn-style-b btn1 bianji" target="_self">确定</a></div>-->
					</div>
				</div>
			</div>
		</div>
        <div class="pop-dialog" id="dialog-order">
            <a href="javascript:void(0)" class="btnClose" title="关闭">关闭</a>
          <div class="pop-title">新建权限</div>
        <div class="success">
        	<table>
            	<tr>
                	<td width="22%">权限ID：</td>
                    <td width="78%"><span>*</span><input type="text" value="" class="chongfu" name="permissionID" /></td>
                </tr>
                <tr>
                	<td>权限名称：</td>
                    <td><span>*</span><input type="text" value="" name="permissionName"/></td>
                </tr>
                <tr>
                	<td>权限描述：</td>
                    <td><span></span><input type="text" value="" name="descripts" /></td>
                </tr>
                <tr>
                	<td>一级菜单： </td>
                    <td>
                    	<span>*</span>
                        <select name="" id="navMenu">
                        	
                    	</select>
                    </td>
                </tr>
                <tr>
                	<td>二级菜单：</td>
                    <td>
                    	<span>*</span>
                        <select name="" id="meMenu">
                        	
                    	</select>
                    </td>
                </tr>
            </table>
        </div>
        
        <a href="javascript:void();" class="close_btn btn-style-b btn1 queding2" target="_self" id="queding">确定</a>
        <a href="javascript:void();" class="close_btn btn-style-b btn1 queding2" target="_self" id="quedingbianji" style="display:none;">确定</a>
        <a href="javascript:void();" class="close_btn btn-style-b btn1 quxiao" target="_self">取消</a>
        
        
        </div>							
	</div>
	<!-- 分布图/趋势图 end -->
</div>
<!-- main end-->
<script src="http://www.howbuy.com/subject/js/jquery.blockUI.js" type="text/javascript"></script>
<@uri.script src=[
	"/gotop.js"
	]/>
<script type="text/javascript">
  $(function(){
  //	var navid = '${navId!}';   
  	var pageid = '${Session.pageId!}'; 
	//$(".header .menu").find("li[refId='"+navid+"']").addClass("current");
	//var relegation = $(".header .menu").find("li[refId='"+navid+"']").attr("relegation");
	//$(".sidenav").find("div[relegation='"+relegation+"']").show().siblings().hide();
	//$(".header .menu").find("li[refId='"+navid+"']").click();
	//$(".sidenav").find("li[refId='"+pageid+"']").addClass("current");
  	var json = ${permission};
	var all = loadTable(json);
	$("#perTable").html(all);

    $('#xinjian').click(function() {
      $.blockUI({ 
        message: $('#dialog-order'), 
        css: { 
          top:  ($(window).height() - 390) /2 + 'px', 
          left: ($(window).width() - 400) /2 + 'px', 
          width: '400px' 
        } 
      });
       $(".pop-title").html("新建权限");
      	$(".success table input[name='permissionID']").val("");
		$(".success table input[name='permissionName']").val("");
		$(".success table input[name='descripts']").val("");
		
     	var allselect = loadSelect(json);
		var selects = allselect.split("$$");
		var navSelect = selects[0];
		var meSelect = selects[1];
		$("#navMenu").html(navSelect);
		$("#meMenu").html(meSelect);
		$("#queding").show();
		$("#quedingbianji").hide();
      return false;
    }); 
	
    $('.btnClose').click(function() {
      $.unblockUI();
      return false;
    }); 
	$('.quxiao').click(function() {
      $.unblockUI();
      return false;
    });  
    //新增权限
	$("#queding").click(function() {
		var permissionID = $(".success table input[name='permissionID']").val();
		var permissionName = $(".success table input[name='permissionName']").val();
		var descripts = $(".success table input[name='descripts']").val();
		var navMenuId = $("#navMenu option:selected").attr("id");
		var meMenuId = $("#meMenu option:selected").attr("id");
		if(permissionID == null || "" == permissionID){
			alert("权限ID为空！");
			return false;
		}
		if(permissionName == null || "" == permissionName){
			alert("权限名称为空！");
			return false;
		}
		if(navMenuId == "navSelectId"){
			alert("请选择一级菜单！");
			return false;
		}
		if(meMenuId == "meSelectId"){
			alert("请选择二级菜单！");
			return false;
		}
		var rescourcelevel = "3";
		var status = "0";
		var params = "permissionId="+permissionID+"&name="+permissionName+"&descriptions="
		+descripts+"&rescourcelevel="+rescourcelevel+"&status="+status+"&parentId="+meMenuId;
		ajaxRequest({
			url:'${uri.context_path}/auth/permManager/addPermission.htm',
            postMethod:'POST',
            params:params,
            callback:function(data){
            	var res = String($.trim(data));
				if(res =='error'){
					alert("权限ID与现有权限ID重复!");
					return false;
				} 
				else if(res == 'success'){
					alert("新建权限成功");
					$('.btnClose').click();
					
					var li = $('.sidenav',parent.document).find('ul li[class="current"]');
					li.click();
					
					/*var $a = $(".sidenav").find("li[refId='"+pageid+"']").find('a');
					var url = $a.attr('href');
					$a.click(function(){
						location.href=url;
					});	
					$a.click();*/		
				}
        	}
		});
	});
	
	//编辑权限
	$('#bianji').click(function() {
		var cnt = 0;
		$("#perTable input[type=radio]").each(function(i){
			if($(this).prop("checked")){
				cnt = i + 1;
			}
		});
		if(cnt == 0){
			alert("请选择待编辑权限!");
			return false;
		}
		
		var $input = $("#perTable input[type=radio]:checked");
		var permissionId = $input.attr("permissionId");
		var id = $input.attr("id");
		var name = $input.val();
		var descriptions = $input.attr("descriptions");
		var grandId =  $input.attr("grand");
		var parentId = $input.attr("parent");
		
		$(".success table input[name='permissionID']").val(permissionId);
		$(".success table input[name='permissionName']").val(name);
		$(".success table input[name='descripts']").val(descriptions);
		
		var navMenuId = $("#navMenu option:selected").attr("id");
		var meMenuId = $("#meMenu option:selected").attr("id");
		
		
      $.blockUI({ 
        message: $('#dialog-order'), 
        css: { 
          top:  ($(window).height() - 390) /2 + 'px', 
          left: ($(window).width() - 400) /2 + 'px', 
          width: '400px' 
        } 
      });
       
      $(".pop-title").html("编辑权限");
     	var allselect = loadSelect(json);
		var selects = allselect.split("$$");
		var navSelect = selects[0];
		var meSelect = selects[1];
		$("#navMenu").html(navSelect);
		$("#meMenu").html(meSelect);
		$("#queding").hide();
		$("#quedingbianji").show();
		$("#navMenu option[value="+grandId+"]").attr("selected","selected");
	 	$("#meMenu option[value="+parentId+"]").attr("selected","selected");
	 	var relegation = $("#meMenu option[value="+parentId+"]").attr("relegation");
	 	$("#meMenu").find("option[relegation='"+relegation+"']").show();
		
      return false;
    }); 

 //编辑权限
	$("#quedingbianji").click(function() {
		
		var $input = $("#perTable input[type=radio]:checked");
		var permissionId = $input.attr("permissionId");
		var id = $input.attr("id");
		var permissionID = $(".success table input[name='permissionID']").val();
		var permissionName = $(".success table input[name='permissionName']").val();
		var descripts = $(".success table input[name='descripts']").val();
		var navMenuId = $("#navMenu option:selected").attr("id");
		var meMenuId = $("#meMenu option:selected").attr("id");
		var version = $input.attr("version");
		if(permissionID == null || "" == permissionID){
			alert("权限ID为空！");
			return false;
		}
		if(permissionName == null || "" == permissionName){
			alert("权限名称为空！");
			return false;
		}
		if(navMenuId == "navSelectId"){
			alert("请选择一级菜单！");
			return false;
		}
		if(meMenuId == "meSelectId"){
			alert("请选择二级菜单！");
			return false;
		}
		var rescourcelevel = "3";
		var status = "0";
		var params = "permissionId="+permissionID+"&name="+permissionName+"&descriptions="
		+descripts+"&rescourcelevel="+rescourcelevel+"&status="+status+"&parentId="+meMenuId;
		
		params = params +"&id="+id+"&oldPermissionId="+permissionId+"&version="+version;
		ajaxRequest({
			url:'${uri.context_path}/auth/permManager/updatePermission.htm',
            postMethod:'POST',
            params:params,
            callback:function(data){
            	var res = String($.trim(data));
				if(res =='error'){
					alert("权限ID与现有权限ID重复!");
					return false;
				} else if(res == 'updateError'){
					alert("更新失败!");
					return false;
				}
				else if(res == 'success'){
					alert("编辑权限成功");
					$('.btnClose').click();
					var li = $('.sidenav',parent.document).find('ul li[class="current"]');
					li.click();
					
					/*var $a = $(".sidenav").find("li[refId='"+pageid+"']").find('a');
					var url = $a.attr('href');
					$a.click(function(){
						location.href=url;					
					});	
					$a.click();*/		
				}
        	}
		});
	});

	//删除权限
	$('#shanchu').click(function() {
		var cnt = 0;
		$("#perTable input[type=radio]").each(function(i){
			if($(this).prop("checked")){
				cnt = i + 1;
			}
		});
		if(cnt == 0){
			alert("请选择待删除权限!");
			return false;
		}
		
		var $input = $("#perTable input[type=radio]:checked");
		var permissionId = $input.attr("permissionId");
		var id = $input.attr("id");
		var name = $input.val();
		var descriptions = $input.attr("descriptions");
		var grandId =  $input.attr("grand");
		var parentid = $input.attr("parent");
		var parentPerId = $("#perTable tr td[name="+parentid+"]").attr("id");
		var params = "permissionId="+permissionId+"&name="+name+"&descriptions="
		+descriptions+"&parentId="+parentPerId+"&id="+id;
		ajaxRequest({
			url:'${uri.context_path}/auth/permManager/queryDeletePermission.htm',
            postMethod:'POST',
            params:params,
            callback:function(data){
            	var res = String($.trim(data));
            	if(res == 'error'){
            		alert("所选权限下有未解绑用户或角色,请将用户和角色全部解绑后重试!");
					return false;
            	}
				else if(res == 'success'){
					if(confirm("确定删除该权限吗？")){
						ajaxRequest({
							url:'${uri.context_path}/auth/permManager/deletePermission.htm',
            				postMethod:'POST',
            				params:params,
            				callback:function(data){
            					var ress = String($.trim(data));
            					if(ress == 'deleteError'){
            						alert("删除失败!");
									return false;
            					}else if(ress == 'success'){
            						alert("删除权限成功!");
									
									var li = $('.sidenav',parent.document).find('ul li[class="current"]');
									li.click();
									
									/*var $a = $(".sidenav").find("li[refId='"+pageid+"']").find('a');
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
	//根据一级菜单加载二级菜单
    $("#navMenu").change(function(){
    	var navValue = $(this).val();
    	$("#meMenu option").hide();
    	$("#meMenu option[id='meSelectId']").show().attr("selected","selected");
    	$("#meMenu").find("option[relegation='"+navValue+"']").show();
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
				tablebodyFirst = tablebodyFirst + '<tr><td colspan="2" class="line" name="'+navPermissionID+'" id="'+navId+'">'+navName+'</td>';
				for(var k=0;k<menuLen;k++){
					var menuId = json[i].list[j].list[k].id;
					var menuName = json[i].list[j].list[k].name; 
					var menuPermissionID = json[i].list[j].list[k].permissionId;
					var pageLen = 0;
					if(json[i].list[j].list[k].list!=null)
						pageLen = json[i].list[j].list[k].list.length;
					if(k==0)
						tablebodyFirst = tablebodyFirst +'<tr><td width="20%" rowspan="'+menuLen+'"></td><td name="'+menuPermissionID+'" id="'+menuId+'">'+menuName+'<ul>';
					else{
						tablebodyFirst = tablebodyFirst +'<tr><td name="'+menuPermissionID+'" id="'+menuId+'">'+menuName+'<ul>';
					}
					for(var l=0;l<pageLen;l++){
						var pageId = json[i].list[j].list[k].list[l].id;
						var pageName = json[i].list[j].list[k].list[l].name; 
						var pagePermissionID = json[i].list[j].list[k].list[l].permissionId;
						var pagedescriptions = json[i].list[j].list[k].list[l].descriptions;
						var version = json[i].list[j].list[k].list[l].version;
						tablebodyFirst = tablebodyFirst +'<li><input type="radio" permissionId = "' +pagePermissionID+'" name="page" id="'+pageId+'" value="'+pageName+'" descriptions="'+pagedescriptions+'" grand="'+navPermissionID+'" parent="'+menuPermissionID+'" version="'+version+'" />'+pageName+'</li>';
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

function loadSelect(json){
  		var instLen = json.length;
		var navMenu = '<option id="navSelectId" selected="selected">请选择一级菜单</option>';
		var meMenu = '<option id="meSelectId" selected="selected">请选择二级菜单</option>';
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
				navMenu = navMenu + '<option id="'+navId+'" value="'+navPermissionID+'">'+navName+'</option>';
				for(var k=0;k<menuLen;k++){
					var menuId = json[i].list[j].list[k].id;
					var menuName = json[i].list[j].list[k].name; 
					var menuPermissionID = json[i].list[j].list[k].permissionId;
					meMenu = meMenu +'<option id="'+menuId+'" value ="'+menuPermissionID+'" relegation="'+navPermissionID+'" style="display:none;">'+menuName+'</option>';
				}
			}
		}
		return navMenu+"$$"+meMenu;
  }
  function genParams(){
  		var permissionID = $(".success table input[name='permissionID']").val();
		var permissionName = $(".success table input[name='permissionName']").val();
		var descripts = $(".success table input[name='descripts']").val();
		var navMenuId = $("#navMenu option:selected").attr("id");
		var meMenuId = $("#meMenu option:selected").attr("id");
		if(permissionID == null || "" == permissionID){
			alert("权限ID为空！");
			return false;
		}
		if(permissionName == null || "" == permissionName){
			alert("权限名称为空！");
			return false;
		}
		if(navMenuId == "navSelectId"){
			alert("请选择一级菜单！");
			return false;
		}
		if(meMenuId == "meSelectId"){
			alert("请选择二级菜单！");
			return false;
		}
		var rescourcelevel = "3";
		var status = "0";
		var params = "permissionId="+permissionID+"&name="+permissionName+"&descriptions="
		+descripts+"&rescourcelevel="+rescourcelevel+"&status="+status+"&parentId="+meMenuId;
		
		return params;
  }
</script>
</body>
</html>