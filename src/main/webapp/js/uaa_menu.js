/**
 * uaa 菜单控制js
 */

var uaa_context_path = null;
/**
 * 初始化菜单map
 * */
var navMap = new HashMap();

var intervalOfWireless = null;
var intervalOfActivity = null;

/**
 * 判断是否包含权限id
 * @param id
 * @param allPerList
 * @returns {Boolean}
 */
function hasPerId(id, allPerList) {
	for (var i = 0; i < allPerList.length; i++) {
		var perId = allPerList[i].permissionId;
		if (id == perId) {
			return true;
		}
	}
	return false;
}
/**
 * 加载头部菜单
 * @param json
 * @param allPerList
 */
function loadHead(json, allPerList) {
	var instLen = json.length;
	var menuFl = '';
	for (var i = 0; i < instLen; i++) {
		var navLen = 0;
		if (json[i].list != null) {
			navLen = json[i].list.length;
		}
		for (var j = 0; j < navLen; j++) {
			var _listObj = json[i].list[j];
			var navId = _listObj.permissionId;
			var navName = _listObj.name;
			var navUrl = 'javascript:void(0);';
			var hasNav = hasPerId(navId, allPerList);
			if (hasNav) {
				var _url_tmp =_listObj.url;
				if (_url_tmp == '' || null == _url_tmp) {
					navUrl = 'javascript:void(0);';
				} else
					navUrl = uaa_context_path + _url_tmp;
			} else
				navUrl = 'javascript:void(0);';
			if (json[i].list[j].list != null)
				menuLen = json[i].list[j].list.length;
			menuFl = menuFl + '<li menuid="' + navId + '"><a url="' + navUrl
					+ '" target="_self" navid="' + navId + '" >' + navName + '</a></li>';

		}
	}
	$(".header .menu").html(menuFl);
}


/**
 * 初始化菜单
 * @param perAllList 所有权限
 * @param userPerList 用户权限
 */
function initSideNavMap(perAllList, userPerList){
	var _list = perAllList[0];
	if(_list.list){
		for(var i=0;i<_list.list.length;i++){
			var _obj = _list.list[i];
			var level = _obj.level;
			var perId  = _obj.permissionId;
			if(1==level){
				var leftHtml = loadSideNav(userPerList, perId);
				navMap.put(perId,leftHtml);
			}
		}
	}
}

/**
 * @deprecated
 * 拼接左侧菜单权限字符串
 * @param perAllList 所有的权限列表
 * @param  userPerList 用户权限列表
 * @param headMenuId 头部菜单id
 * @returns {String}
 */
function loadSideNav(perAllList, userPerList,headMenuId){
	var instLen = perAllList.length;
	//var menuFl = '';
	var sideNav = '';
	for (var i = 0; i < instLen; i++) {
		var navLen = 0;
		var  _json_list = perAllList[i].list;
		if (_json_list != null) {
			navLen = _json_list.length;
		}else{
			continue;
		}
		for (var j = 0; j < navLen; j++) {
			var _json_list2 = perAllList[i].list[j];
			var navId = _json_list2.permissionId;
			if(headMenuId != navId){
				continue;
			}
			var hasNav = hasPerId(navId, userPerList);
			if (hasNav) {
				var _url2 = _json_list2.url;
				if (_url2 == '' || null == _url2) {
					navUrl = 'javascript:void(0);';
				} else{
					navUrl = uaa_context_path + _url2;
				}
			} else
				navUrl = 'javascript:void(0);';
			var menuLen = 0;
			if (_json_list2.list != null)
				menuLen = _json_list2.list.length;
			sideNav = sideNav + '<div relegation="' + navId + '" style="display:none">';
			for (var k = 0; k < menuLen; k++) {
				var menuName = _json_list2.list[k].name;
				var pageLen = 0;
				if (_json_list2.list[k].list != null)
					pageLen = _json_list2.list[k].list.length;
				sideNav = sideNav + '<h3>' + menuName + '</h3><ul>';
				for (var l = 0; l < pageLen; l++) {
					var pageId = _json_list2.list[k].list[l].permissionId;
					var pageName = _json_list2.list[k].list[l].name;
					var hasPer = hasPerId(pageId, userPerList);
					var url = 'javascript:void(0);';
					if (hasPer) {
						url = uaa_context_path+ _json_list2.list[k].list[l].url;
					}
					if (_json_list2.list[k].list[l].url != ''
							&& null != _json_list2.list[k].list[l].url)
						sideNav = sideNav + '<li relegation="' + navId + '" refId="' + pageId
								+ '"><a url="' + url + '" target="_self" pageId="' + pageId
								+ '" navId="' + navId + '">' + pageName + '</a></li>';
				}
				sideNav = sideNav + '</ul>';
			}
			sideNav = sideNav + '</div>';

		}
	}
	return sideNav;
}
/**
 * 拼接左侧菜单权限字符串
 * @param  userPerList 用户权限列表
 * @param headMenuId 头部菜单id
 * @returns {String}
 */
function loadSideNav(userPerList,headMenuId){
	var len = userPerList.length;
	var sideNav = '';
	var smapHtml = new HashMap();
	for (var i = 0; i < len; i++) {
		var _obj = userPerList[i];
		var rescourcelevel = _obj.rescourcelevel;
		var perId  = _obj.permissionId;
		var menuName = _obj.name;
		var linkType = _obj.urlChannel;
		if(perId.indexOf(headMenuId)==0 && rescourcelevel>1){
			if(rescourcelevel==2){
				var h = '<h3>' + menuName + '</h3><ul>';
				smapHtml.put(perId,h);
			}else if(rescourcelevel==3){
				var html = getSmapHtml(smapHtml, perId);
				if(""==html){
					continue;
				}
				var navId =getSmapKey(smapHtml, perId);
				if(null ==navId){
					continue;
				}
				var url = 'javascript:void(0);';
				if(_obj.url){
					url = _obj.url;
				}
				var sh = '<li relegation="' + navId + '" refId="' + perId+ '" linkType="'+linkType+'">'
				+'<a url="' + url + '" target="_self" pageId="' + perId
				+ '" navId="' + navId + '">' + menuName + '</a></li>';
				html += sh;
				smapHtml.put(navId,html);
			}
		}
	}
	sideNav = '<div relegation="' + headMenuId + '">';
	for(var j=0;j<smapHtml.keySets().length;j++){
		var key = smapHtml.keySets()[j];
		var ht = smapHtml.get(key);
		sideNav = sideNav +ht+'</ul>';
	}
	sideNav = sideNav + '</div>';
	return sideNav;
}

/**
 * 获取对应的key
 * @param smapHtml
 * @param perId
 * @returns
 */
function getSmapKey(smapHtml,perId){
	var keysets = smapHtml.keySets();
	for(var i=0;i<keysets.length;i++){
		var key = keysets[i];
		if(perId.indexOf(key)>=0)
			return key;
	}
	return null;
}
/**
 * 获取对应的html
 * @param smapHtml
 * @param perId
 * @returns
 */
function getSmapHtml(smapHtml,perId){
	var keysets = smapHtml.keySets();
	for(var i=0;i<keysets.length;i++){
		var key = keysets[i];
		if(perId.indexOf(key)>=0){
			return smapHtml.get(key);
		}
	}
	return "";
}

/**
 * 判断是否有系统权限
 * @param allPerList
 * @returns {Boolean}
 */
function hasPermission(allPerList) {
	if (allPerList.length == 0) {
		return false;
	} else if (allPerList.length == 3) {
		for (var i = 0; i < allPerList.length; i++) {
			var permissionId = allPerList[i].permissionId;
			if (permissionId == '000111') {// 000111 是导航页面的权限id,默认用户都有该权限
				return false;
			}
		}
	} else if (allPerList.length > 3) {
		return true;
	}
	return false;
}


/**
 * 头部菜单事件
 */
function eventHead(){
	$(".header .menu li").click(function() {
		cancleInterval();
		var _thise = $(this);
		var $a = _thise.find("a");
		var url = $a.attr('url');
		var relegation = _thise.attr("menuid");
		
		var html = navMap.get(relegation);
		if (('' == html || html == null) && url.indexOf(_void0) >= 0) {
			return;
		}else if (url.indexOf(_void0) == -1) {
			//首级菜单有URL，则默认不显示左侧菜单
			$(".sidenav").html("").hide();
			//触发URL
			loadMainData(url);
			_thise.addClass("current").siblings().removeClass("current");
			return;
		}else{
			var $li = null;
			$(html).find("ul li").each(function(index, obj) {
				var _thisli = $(obj);
				var url = _thisli.find("a").attr("url");
				if (url && url.indexOf(_void0) == -1) {
					// 寻找有URL的第一个菜单
					$li = _thisli;
					return false;
				}
			});
			if ($li == null) {
				//并没有找到有效菜单
				return;
			} else {
				_thise.addClass("current").siblings().removeClass("current");
				
				$(".sidenav").html(html).show();
				var refid = $li.attr("refid");
				$(".sidenav ul").find("li[refid='"+refid+"']").addClass("current");
				var url = $li.find("a").attr('url');
				loadMainData(url, null,function(){
					//添加左侧菜单点击事件
					eventMenuLeftLi();
				});
				/*if (showLeft) {} else {
					$(".sidenav").html("");
					if ($(".sidenav").is(":visible")) {
						$(".sidenav").hide();
					}
				}*/
			}
		}
	});
}

/**
 *外部链接map key:url,value:1启用，0不启用
 */
var outLinkMap = new HashMap();
outLinkMap.put("http://datax.intelnal.howbuy.com","1");
outLinkMap.put("http://10.50.50.27","1");
outLinkMap.put("http://192.168.220.220:9000","1");

/**
 * 判断是否是外部链接 true/false
 * @param url
 * @returns {Boolean}
 */
function isOutLink(url,linkType){
	if(linkType){
		if(1!=linkType){//不等于本地链接
			return true;
		}
	}
	if(!outLinkMap.isEmpty()){
		var keysets = outLinkMap.keySets();
		for(var i=0;i<keysets.length;i++){
			var key = keysets[i];
			if(url.indexOf(key)==0){//开头
				var value = outLinkMap.get(key);
				if(value=="1"){
					return true;
				}
			}
			
		}
	}
	return false;
}
function cancleInterval(){
	if(intervalOfWireless){
		clearInterval(intervalOfWireless);
		//取消无线页面刷新定时器
	}
	if(intervalOfActivity){
		clearInterval(intervalOfActivity);
		//取消无线页面刷新定时器
	}
}
/**
 * 左侧菜单事件添加
 */
function eventMenuLeftLi(){
	$(".sidenav div ul li").click(function() {
		var _thisli = $(this);
		var linkType=_thisli.attr("linkType");
		var $a = _thisli.find("a");
		var url = $a.attr('url');
		if (url.indexOf(_void0) > -1 || url == ""|| "undefined" == url) {
			return;
		}
		cancleInterval();
		$(".sidenav div ul li").removeClass("current");
		_thisli.addClass("current");//.siblings()
		//判断是否属于外部链接
		var pid = _thisli.attr("refid");
		var data={pid:pid};
		if(isOutLink(url,linkType)){
			loadOutData(url, data, function(){});
		}
		else{
			loadMainData(url);
		}
	});
}

function loginOutEvent(){
	$("#loginout").click(function(){
		if(confirm("是否退出系统?")){
			var url = uaa_context_path+'/login/loginout.htm';
			location.href=url;
		}
	});
}
