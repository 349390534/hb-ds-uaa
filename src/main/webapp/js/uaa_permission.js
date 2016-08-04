var context_path=null;

var allPermissionJson= [];
var userRoleListJson = [];
var userName = '';
var chineseName = '';
var email = '';
var telphone = '';
var userId = null;
var version = null;
var rolePerListJson = [];
var perListJson = [];
var roleListJson = [];
var oldroleIds = '';
var oldperIds =  '';
var userperlistJson = [];

function giveValue(){
	
	//$("#userBaseInfo input[name='chineseName']").css("border");
	$("#userBaseInfo input[name='userName']").val(userName).attr("readonly",
	"readonly").css("border", "0");
	$("#userBaseInfo input[name='chineseName']").val(chineseName).attr("readonly",
	"readonly").css("border", "0");
	$("#userBaseInfo input[name='email']").val(email).attr("readonly", "readonly")
	.css("border", "0");
	$("#userBaseInfo input[name='telphone']").val(telphone).attr("readonly",
	"readonly").css("border", "0");
	$("#userBaseInfo input[name='version']").val(version);
	var pvalue = userName + '- 编辑用户角色';
	$("#userName").html(pvalue);
	var userquanxian = userName + '- 编辑用户权限';
	$("#userquanxian").html(userquanxian);
	var tbody = loadTable();
	$("#roleInfo").html(tbody);
	var userRoleId = '';
	for (var i = 0; i < userRoleListJson.length; i++) {
		userRoleId = userRoleId + userRoleListJson[i].roleId+'$$';
		oldroleIds = oldroleIds + userRoleListJson[i].id+":";
	}
	
	var userRoIds = userRoleId.split("$$");
	$("#roleInfo input[type=checkbox]").each(function() {
		var id = $(this).attr("id");
		for (var i = 0; i < userRoIds.length - 1; i++) {
			if (id == userRoIds[i]) {
				$(this).prop("checked", true);
				//oldroleIds = oldroleIds + id + ':';
			}
		}
	});
	var db = $(".selectUser ul li[class='current']").attr("isdb");	
	if(db == 'db'){
		$(".selectUser ul li[class='current']").removeAttr("isdb");
		$("#bianji").click();
	}
}


$("#bianji").click(
		function() {
			$("#bianjiUser").hide();
			$(".sure").show();
			$("#bianjiRole").removeClass("btn-style-b").addClass(
					"unable-style-b").attr("disabled", "disabled");
			$("#bianjiPer").removeClass("btn-style-b").addClass(
					"unable-style-b").attr("disabled", "disabled");
			$("#userBaseInfo input[name='chineseName']").removeAttr("readonly")
					.css("border", "#666 solid 1px").css("border-top","none").css("border-right","none").css("border-left","none");
			$("#userBaseInfo input[name='email']").val(email).removeAttr(
					"readonly").css("border", "#666 solid 1px").css("border-top","none").css("border-right","none").css("border-left","none");
			$("#userBaseInfo input[name='telphone']").val(telphone).removeAttr(
					"readonly").css("border", "#666 solid 1px").css("border-top","none").css("border-right","none").css("border-left","none");
			
		});
$("#quxiao").click(
		function() {
			$("#bianjiUser").show();
			$(".sure").hide();
			$("#bianjiRole").removeClass("unable-style-b").addClass(
					"btn-style-b").removeAttr("disabled");
			$("#bianjiPer").removeClass("unable-style-b").addClass(
					"btn-style-b").removeAttr("disabled");
			$("#userBaseInfo input[name='chineseName']").attr("readonly",
					"readonly").css("border", "0");
			$("#userBaseInfo input[name='email']").attr("readonly", "readonly")
					.css("border", "0");
			$("#userBaseInfo input[name='telphone']").attr("readonly",
					"readonly").css("border", "0");
		});
$("#queding").click(
		function() {
			var name = $("#userBaseInfo input[name='userName']").val();
			var cname = $("#userBaseInfo input[name='chineseName']").val();
			var em = $("#userBaseInfo input[name='email']").val();
			var tel = $("#userBaseInfo input[name='telphone']").val();
			var version = $("#userBaseInfo input[name='version']").val();
			if (cname != null && "" != cname) {
				var reg = /^([\u4E00-\u9FA5]+，?)+$/;
				var yesorno = cname.match(reg) != null;
				if (!yesorno) {
					alert("请输入中文字符!");
					return false;
				}
			}
			if (em != null && "" != em) {
											  var pattern =
											  /^\w+((-\w+)|(\.\w+))*\@[A-Za-z0-9]+((\.|-)[A-Za-z0-9]+)*\.[A-Za-z0-9]+$/;
											  var flag = pattern.test(em);
											  if(!flag){ alert("邮箱格式不正确!");
											  return false; }
											 
			}
			if (tel != null && tel != "") {
				var pattern = /^[0-9]*$/;
				if (!pattern.test(tel)) {
					alert("请输入数字！");
					return false;
				}
				if (tel.length != 11) {
					alert("手机长度不对!");
					return false;
				}
			}

			var params = "userName=" + userName + "&chineseName=" + cname
					+ "&email=" + em + "&telphone=" + tel + "&version="
					+ version;
			ajaxRequest({
				url : context_path+'/auth/userManager/updateUser.htm',
				postMethod : 'POST',
				params : params,
				callback : function(data) {
					var res = String($.trim(data));
					if (res == 'updateError') {
						alert("更新失败!");
						return false;
					} else if (res == 'success') {
						alert("用户信息编辑成功！");

						// $("#allUserSelect").click();
						$("#searchButton").click();
					}

				}
			});
		});

$("#bianjiRole").click(
		function() {
			
			$("#bianji").removeClass("btn-style-b").addClass("unable-style-b").attr("disabled", "disabled");
			$("#bianjiRole").removeClass("btn-style-b").addClass(
					"unable-style-b").attr("disabled", "disabled");
			$("#bianjiPer").removeClass("btn-style-b").addClass(
					"unable-style-b").attr("disabled", "disabled");
			$(".user_list").show();
			$("#qd_btn_role").show();
			$("#userName").show();
			$(".flo_nav_wrap").show();
			$("#quanxianquan").hide();
		});
$("#quxiaoRole").click(
		function() {
			$("#bianji").removeClass("unable-style-b").addClass("btn-style-b").removeAttr("disabled");
			$("#bianjiRole").removeClass("unable-style-b").addClass(
					"btn-style-b").removeAttr("disabled");
			$("#bianjiPer").removeClass("unable-style-b").addClass(
					"btn-style-b").removeAttr("disabled");
			$(".user_list").hide();
			$("#qd_btn_role").hide();
			$("#userName").hide();
			$(".flo_nav_wrap").hide();
			$("#quanxianquan").hide();
		});
$("#quedingRole").click(
		function() {
			var roleIds = '';

			$("#roleInfo input[type=checkbox]").each(function() {
				var id = $(this).attr("id");
				if ($(this).prop("checked")) {
					roleIds = roleIds + id + ':';
				}
			});
			var params = "roleIds=" + roleIds + "&userId=" + userId
					+ "&oldroleIds=" + oldroleIds;
			ajaxRequest({
				url : context_path+'/auth/userManager/updateUserRole.htm',
				postMethod : 'POST',
				params : params,
				callback : function(data) {
					var res = String($.trim(data));
					if (res == 'updateError') {
						alert("更新失败!");
						return false;
					} else if (res == 'success') {
						alert("角色修改成功！");
						// $("#allUserSelect").click();
						$("#searchButton").click();
					}
				}
			});
		});
//var allPermissionJson = null;


$("#bianjiPer").click(
		function() {
			$("#bianji").removeClass("btn-style-b").addClass("unable-style-b")
					.attr("disabled", "disabled");
			$("#bianjiRole").removeClass("btn-style-b").addClass(
					"unable-style-b").attr("disabled", "disabled");
			$("#bianjiPer").removeClass("btn-style-b").addClass(
					"unable-style-b").attr("disabled", "disabled");
			$(".quanxian_list").show();
			$("#qd_btn_per").show();
			$("#userName").hide();
			$(".flo_nav_wrap").hide();
			$("#quanxianquan").show();
			// 全选框
			var allSize = $("#allPer ul li input").size();
			var allCnt = 0;
			$("#allPer ul li input").each(function() {
				if ($(this).prop("checked")) {
					allCnt = allCnt + 1;
				}
			});
			if (allCnt != allSize && allCnt != 0) {
				$("#quanxuanImage").show();
			} else if (allCnt == 0) {
				$("#quanxuan").prop("checked", false);
				$("#quanxuanImage").hide();
			} else {
				$("#quanxuan").prop("checked", true);
				$("#quanxuanImage").hide();
			}
			checkeach();
		});
$("#quxiaoquanxian").click(
		function() {
			$("#bianji").removeClass("unable-style-b").addClass("btn-style-b")
					.removeAttr("disabled");
			$("#bianjiRole").removeClass("unable-style-b").addClass(
					"btn-style-b").removeAttr("disabled");
			$("#bianjiPer").removeClass("unable-style-b").addClass(
					"btn-style-b").removeAttr("disabled");
			$(".quanxian_list").hide();
			$("#qd_btn_per").hide();
			$("#quanxianquan").hide();
		});

function checkAllPer(){
	$("#allPer input[type=checkbox]").each(function() {
       
		$(this).prop("checked", false);
		var id = $(this).attr("id");
		
		for (var i = 0; i < rolePerListJson.length; i++) {
			var pid = rolePerListJson[i].id; 
			if(id == rolePerListJson[i].id)
			{ 
				$(this).prop("checked",true);
				  var name = $(this).attr("name"); 
				  if(name == 'page'){
					  $(this).attr("disabled","disabled");
					  $(this).parent().css("color","#c0c0c0");
				  } 
			 }
		}
		 oldperIds = ''; 
		 for (var i = 0; i < userperlistJson.length; i++){
			 oldperIds = oldperIds + userperlistJson[i].id+":";
		 }
		 
		for (var i = 0; i < perListJson.length; i++) {
			var pid = perListJson[i].id; 
			if(id == perListJson[i].id){
				  $(this).prop("checked",true);
			}
		}
		
	});
}	  


$("#quedingquanxian").click(
		function() {
			var flag = false;

			var perIDs = '';
			var parens = '';
			var arr = [];
			var i = 0;
			$("#allPer input[type='checkbox']").each(function() {
				var id = $(this).attr("id");
				var disabled = $(this).attr("disabled");
				var parentId = $(this).attr("parent");
				var grandId = $(this).attr("grand");
				var $parent = $("#allPer input[name='" + parentId + "']");
				var $grand = $("#allPer input[name='" + grandId + "']");
				var parentId = $parent.attr("id");
				var grandId = $grand.attr("id");
				if (id != "quanxuan") {
					if ($(this).prop("checked") && disabled != 'disabled') {
						
						perIDs = perIDs + id + ':';
						arr[i] = parentId;
						i = i + 1;
						arr[i] = grandId;
						i = i + 1;

					}
				}

			});
			arr = getNoRepeat(arr);

			for (var j = 0; j < arr.length; j++) {
				perIDs = perIDs + arr[j] + ":";
			}
			var params = "perIDs=" + perIDs + "&userId=" + userId
					+ "&oldperIds=" + oldperIds;
			ajaxRequest({
				url : context_path+'/auth/userManager/updateUserPer.htm',
				postMethod : 'POST',
				params : params,
				callback : function(data) {
					var res = String($.trim(data));
					if (res == 'updateError') {
						alert("更新失败!");
						return false;
					} else if (res == 'success') {
						alert("用户权限修改成功！");
						// $("#allUserSelect").click();
						$("#searchButton").click();
					}
				}
			});

			
		});
// });
$("#xinjianyonghu").click(function(){
	$(".sure").hide();
	var name = $("#userBaseInfo input[name='userName']").val();
	var cname = $("#userBaseInfo input[name='chineseName']").val();
	var em = $("#userBaseInfo input[name='email']").val();
	var tel = $("#userBaseInfo input[name='telphone']").val();
	var version = $("#userBaseInfo input[name='version']").val();
	if (cname != null && "" != cname) {
		var reg = /^([\u4E00-\u9FA5]+，?)+$/;
		var yesorno = cname.match(reg) != null;
		if (!yesorno) {
			alert("请输入中文字符!");
			return false;
		}
	}
	if (em != null && "" != em) {
									  var pattern =
									  /^\w+((-\w+)|(\.\w+))*\@[A-Za-z0-9]+((\.|-)[A-Za-z0-9]+)*\.[A-Za-z0-9]+$/;
									  var flag = pattern.test(em);
									  if(!flag){ alert("邮箱格式不正确!");
									  return false; }
									 
	}
	if (tel != null && tel != "") {
		var pattern = /^[0-9]*$/;
		if (!pattern.test(tel)) {
			alert("请输入数字！");
			return false;
		}
		if (tel.length != 11) {
			alert("手机长度不对!");
			return false;
		}
	}

	var params = "userName=" + userName + "&chineseName=" + cname
			+ "&email=" + em + "&telphone=" + tel + "&version="
			+ version;
	ajaxRequest({
		url : context_path+'/auth/userManager/addUser.htm',
		postMethod : 'POST',
		params : params,
		callback : function(data) {
			var res = String($.trim(data));
			if (res == 'addError') {
				alert("添加失败!");
				return false;
			} else if (res == 'success') {
				alert("用户信息添加成功！");

				// $("#allUserSelect").click();
				$("#allUserSelect").append('<option id="'+userName+'">'+userName+'</option>');
				$("#searchButton").click();
			}

		}
	});
	
});
	
function loadTable() {
	var tbody = '';
	for (var i = 0; i < roleListJson.length; i++) {
		if(i== 0){
			tbody =  tbody +'<tr><td width="25%"><input type="checkbox" name="" id="'+roleListJson[i].id+'">'+roleListJson[i].name+
			'</td>'+ '<td width="50%">'+roleListJson[i].descriptions+'</td><td width="25%">'+roleListJson[i].content+'</td></tr>';
		}else{
			tbody =  tbody +'<tr><td><input type="checkbox" name="" id="'+roleListJson[i].id+'">'+roleListJson[i].name+
			'</td>'+ '<td >'+roleListJson[i].descriptions+'</td><td width="25%">'+roleListJson[i].content+'</td></tr>';
		}
	}
	return tbody;
}

function loadquanxianTable(json) {
	if(null==json){
		return ;
	}
	var instLen = json.length;

	var tablebodyFirst = '';
	var imgUrl = context_path + '/images/03.jpg';
	for (var i = 0; i < instLen; i++) {
		var navLen = 0;
		if (json[i].list != null) {
			navLen = json[i].list.length;
		}
		for (var j = 0; j < navLen; j++) {
			var navId = json[i].list[j].id;
			var navName = json[i].list[j].name;
			var navPermissionID = json[i].list[j].permissionId;
			var menuLen = 0;
			if (json[i].list[j].list != null)
				menuLen = json[i].list[j].list.length;
			tablebodyFirst = tablebodyFirst
					+ '<tr class="navTr" nav="'
					+ navPermissionID
					+ '"><td colspan="2" class="line"><label><img src="'+imgUrl+'" class="tianChong" id="'
					+ navPermissionID
					+ 'Image" style="display:none;"><input type="checkbox" name="'
					+ navPermissionID + '" id="' + navId
					+ '" class="navType" />' + navName + '</label></td></tr>';

			for (var k = 0; k < menuLen; k++) {
				var menuId = json[i].list[j].list[k].id;
				var menuName = json[i].list[j].list[k].name;
				var menuPermissionID = json[i].list[j].list[k].permissionId;
				var pageLen = 0;
				if (json[i].list[j].list[k].list != null)
					pageLen = json[i].list[j].list[k].list.length;
				if (k == 0)
					tablebodyFirst = tablebodyFirst
							+ '<tr><td width="20%" rowspan="'
							+ menuLen
							+ '"></td><td width="80%" class="menuTd" menu="'
							+ menuPermissionID
							+ '"><label><img src="'+imgUrl+'" class="tianChong" id="'
							+ menuPermissionID
							+ 'Image" style="display:none;"><input type="checkbox" name="'
							+ menuPermissionID + '" id="' + menuId
							+ '" parent="' + navPermissionID
							+ '" class="menuType" />' + menuName
							+ '</label><ul>';
				else {
					tablebodyFirst = tablebodyFirst
							+ '<tr><td class="menuTd" menu="'
							+ menuPermissionID
							+ '"><label><img src="'+imgUrl+'" class="tianChong" id="'
							+ menuPermissionID
							+ 'Image" style="display:none;"><input type="checkbox" name="'
							+ menuPermissionID + '" id="' + menuId
							+ '" parent="' + navPermissionID
							+ '" class="menuType"/>' + menuName
							+ '</label><ul>';
				}
				for (var l = 0; l < pageLen; l++) {
					var pageId = json[i].list[j].list[k].list[l].id;
					var pageName = json[i].list[j].list[k].list[l].name;
					var pagePermissionID = json[i].list[j].list[k].list[l].permissionId;
					var pagedescriptions = json[i].list[j].list[k].list[l].descriptions;
					tablebodyFirst = tablebodyFirst
							+ '<li><input type="checkbox" permissionId = "'
							+ pagePermissionID + '" name="page" id="' + pageId
							+ '" value="' + pageName + '" descriptions="'
							+ pagedescriptions + '" grand="' + navPermissionID
							+ '" parent="' + menuPermissionID + '" />'
							+ pageName + '</li>';
				}
				if (k == 0)
					tablebodyFirst = tablebodyFirst + '</ul></td></tr>';
				else {
					tablebodyFirst = tablebodyFirst + '</ul></td></tr>';
				}
			}
			//if (menuLen == 0)
				//tablebodyFirst = tablebodyFirst + '<td></td></tr>';
		}
	}
	$("#allPer").html(tablebodyFirst);
}
function getNoRepeat(s) {
	return s.sort().join(",,").replace(/(,|^)([^,]+)(,,\2)+(,|$)/g, "$1$2$4")
			.replace(/,,+/g, ",").replace(/,$/, "").split(",");
}
function checkeach(){
	$(".navType").each(
			function() {
				var name = $(this).attr("name");
				var childSize = $(
						"#allPer input[parent='" + name + "']").size();
				var grandsize = $(
						"#allPer ul li input[grand='" + name + "']")
						.size();
				var grandCnt = 0;
				var childCnt = 0;
				var discount = 0;

				$("#allPer input[parent='" + name + "']").each(
						function() {
							if ($(this).prop("checked")) {
								childCnt = childCnt + 1;
							}
						});
				$("#allPer ul li input[grand='" + name + "']").each(
						function() {
							if ($(this).prop("checked")) {
								grandCnt = grandCnt + 1;
							}
							var disabled = $(this).attr("disabled");
							if ($(this).prop("checked")
									&& disabled == 'disabled') {
								discount = discount + 1;
							}

						});
				var navimageId = "#" + name + "Image";
				if (grandCnt != grandsize && grandCnt != 0) {
					$(navimageId).show();
				} else if (grandCnt == 0) {
					// $("#quanxuan").prop("checked",false);
					$(navimageId).hide();
				} else {
					if (childCnt == childSize) {
						$(navimageId).hide();
						if (discount == grandCnt){
							$(this).attr("disabled", "disabled");
							$(this).parent().css("color","#c0c0c0");
						}
							//$(this).attr("disabled", "disabled");
							
					}

					else
						$(navimageId).show();
				}
				if (childSize == 0 && grandsize == 0) {
					
					var id = $(this).attr("id");
					for (var i = 0; i < rolePerListJson.length; i++) {
						
						if(rolePerListJson[i].id == id){
							$(this).attr("disabled", "disabled");
							$(this).parent().css("color","#c0c0c0");
						}
							
					}
				}
			});
	$(".menuType").each(
			function() {
				var name = $(this).attr("name");
				var menuImageId = "#" + name + "Image";
				var childsize = $(
						"#allPer ul li input[parent='" + name + "']")
						.size();
				var chidcnt = 0;
				var discount = 0;
				$("#allPer ul li input[parent='" + name + "']").each(
						function() {
							var disabled = $(this).attr("disabled");
							if ($(this).prop("checked")) {
								chidcnt = chidcnt + 1;
							}
							if ($(this).prop("checked")
									&& disabled == 'disabled') {
								discount = discount + 1;
							}
						});
				if (chidcnt != childsize && chidcnt != 0) {
					$(menuImageId).show();
				} else if (chidcnt == 0) {

					$(menuImageId).hide();
				} else {
					if (discount == childsize){
						$(this).attr("disabled", "disabled");
						$(this).parent().css("color","#c0c0c0");
					}
						
					$(menuImageId).hide();
				}
				if (childsize == 0 && $(this).prop("checked")) {
					$(this).attr("disabled", "disabled");
					$(this).parent().css("color","#c0c0c0");
				}
			});
}