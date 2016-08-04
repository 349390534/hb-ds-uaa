
<#include "header.ftl" />
<!-- 侧栏 begin -->
<div class="sidenav">
</div>
<!-- 侧栏 end -->
<@uri.script src=["/uaa_menu.js"]/>
<script type="text/javascript">
uaa_context_path = "${uri.context_path}";
  $(function(){
  
  	var allPermission = [];
  	var allPerList = [];
  	<#if Session.headPermission??>
  		allPermission = ${Session.headPermission};
  	</#if>
  	<#if Session.headPermission??>
  		allPerList = ${Session.allPerList};
  	</#if>
	var allPerm = loadPermission(allPermission,allPerList);
	var allPerms = allPerm.split("$$");
	var menuFl = allPerms[0];
	var sideNav = allPerms[1];
	if("0001"!=navid){//核心数据不展示左侧菜单
		$(".sidenav").html(sideNav);
	}
	$(".sidenav").append('<form name="submitRequest" method="post" style="display:none;"id="submitRequest" >'+
		'<input type="hidden" name="pageId" /><input type="hidden" name="navId" /></form>');	
	var navid = '';
	<#if Session?? && Session.pageMap??>
		navid='${Session.pageMap.navId!}';
	</#if>
  	var pageid = '';
  	<#if Session?? && Session.pageMap??>
		pageid='${Session.pageMap.pageId!}';
	</#if>
	if(""!=navid){
		var nav = $(".header .menu").find("li[refId='"+navid+"']");
		if(nav.length==0){
			nav = $(".header .menu").find("li[relegation='"+navid+"']");
		}
		nav.addClass("current");
	}else{
		$(".header .menu").find("li:eq(0)").addClass("current");
	}
	var _refLi = $(".header .menu").find("li[refId='"+navid+"']");
	if(_refLi.length==0){
		_refLi = $(".header .menu").find("li[relegation='"+navid+"']");
	}
	if("0001"!=navid){//核心数据不展示左侧菜单
		var relegation = _refLi.attr("relegation");
		$(".sidenav").find("div[relegation='"+relegation+"']").show().siblings().hide();
		if(""!=pageid){
			var sideNav =  $(".sidenav").find("li[refId='"+pageid+"']");
			if(sideNav.length==0){
				sideNav = $(".sidenav").find("li[relegation='"+pageid+"']");
			}
			sideNav.addClass("current");
		}
	}
	allEvent(navid,pageid);

  });

 </script> 