/**
 * 无线使用js
 */

/**
 * 储-APP:HZLX_HOWBUY_CAPP,掌-APP:HZLX_HOWBUY_ZAPP
 */
var wirelessAppCodeArray = [ "HZLX_HOWBUY_CAPP", "HZLX_HOWBUY_ZAPP" ];

var wirelessOutletJson = null;

/**
 * 网点号对应名称map
 */
var outletCodeNameMap = new HashMap();

var outletMap = new HashMap();
/**加载渠道数据
 * @param json
 */
function loadOutlet(json) {
	var capp_array = [];
	var zapp_array = [];
	var instLen = json.length;
	for (var i = 0; i < instLen; i++) {
		var platLen = json[i].chan.length;
		if (json[i].discode == HB) {// 好买
			// 无线
			for (var j = 0; j < platLen; j++) {
				var tradechan = json[i].chan[j].tradechan;
				var channame = json[i].chan[j].channame;
				if ("4" == tradechan || "无线" == channame) {
					// 合作类型
					var coopLen = json[i].chan[j].hzlxname.length;
					for (var k = 0; k < coopLen; k++) {
						var hzlxcode = json[i].chan[j].hzlxname[k].hzlxcode;
						// 网点号
						if (hzlxcode == wirelessAppCodeArray[0]) {// 储-APP
							for (var m = 0; m < json[i].chan[j].hzlxname[k].outlet.length; m++) {
								var outletcode = json[i].chan[j].hzlxname[k].outlet[m].outletcode;
								var outletname = json[i].chan[j].hzlxname[k].outlet[m].outletname;
								var opc = '<option value="'+ outletcode+ '" id="'+ outletcode+ '">'+ outletname+ '</option>';
								capp_array.push(opc);
								outletCodeNameMap.put(outletcode,outletname);
							}
						} else if (hzlxcode == wirelessAppCodeArray[1]) {// 掌-APP
							for (var m = 0; m < json[i].chan[j].hzlxname[k].outlet.length; m++) {
								var outletcode = json[i].chan[j].hzlxname[k].outlet[m].outletcode;
								var outletname = json[i].chan[j].hzlxname[k].outlet[m].outletname;
								var opz = '<option value="'+ outletcode+ '" id="'+ outletcode+ '">'+ outletname+ '</option>';
								zapp_array.push(opz);
								outletCodeNameMap.put(outletcode,outletname);
							}
						}
					}
				}
			}
		}
	}

	if (capp_array.length >= 1) {
		opcList = capp_array.join("");
		outletMap.put(wirelessAppCodeArray[0], opcList);
	}

	if (zapp_array.length >= 1) {
		opzList = zapp_array.join("");
		outletMap.put(wirelessAppCodeArray[1], opzList);
	}
	
	
}


/**
 * 注册访问渠道点击事件
 */
function bindFwqdClick(){
	var outlet = $("select#outlet");
	var allop = '<option value="">全部</option>';
	$("#ul_outlet li").click(function(){
		var _this = $(this);
		if(_this.hasClass("all")){
			outlet.html(allop);
		}else if(_this.hasClass("cxg")){
			outlet.html(allop + outletMap.get(wirelessAppCodeArray[0]));
		}else if(_this.hasClass("zj")){
			outlet.html(allop + outletMap.get(wirelessAppCodeArray[1]));
		}
		$(this).addClass("current").siblings("li").removeClass("current");
	});
}

function bindDateClick(){
	/* 对比日期 */
	$(".db_date").hide();
	$(".compare_date").on("click", function() {
		$(".db_date").toggle();
	});
}


