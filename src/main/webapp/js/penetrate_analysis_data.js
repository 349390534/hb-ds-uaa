/**
 * 穿透分析js
 */
/**
 * 选中所有指标
 */
function checkBox(b){
	$("#quato input[type=checkbox]").prop("checked",b);
}

var checkBoxClickEvent=function(){
	var _this =$(this);
	var q = _this.attr("q");
	var ch = $(this).prop("checked");
	if("0"==q){
		checkBox(ch);
		//全选按钮
		$("div#con_main td[q]").each(function(i,obj){
			if(ch){
				$(obj).show();
			}else{
				$(obj).hide();
			}
		});
	}else {
		if(ch){
			$("div#con_main td[q='"+q+"']").show();
		}else{
			$("div#con_main td[q='"+q+"']").hide();
		}
	}
};

var exportPenetrateData=function(){
	var start = $("#start").val();
	var end = $("#end").val();
	var params ="start="+start+"&end="+end;
	var path = window.uaa_context_path ||"";
	var url=path+'/auth/pta/exportData.htm?'+params;
	window.open(encodeURI(url));
};

var tabMenu=function(){

	$("ul.tab_menu2 li.current").removeClass("current");
	var  _this = $(this);
	_this.addClass("current");
	var tag = _this.attr("tag");
	var categoriesArray =loadChartCategoriesArray("statdt");
	var yAxisArray = yAxisTitleArrayMap.get(tag);
	if("f2b_rate_ctfx6r"==tag){//公转私六象限分析
		var seriesDataArray = loadStatckColumn();
		drawcolumn(categoriesArray, seriesDataArray, yAxisArray);
	}else{
		var dataArray = loadCombinationChart(tag);
		if(dataArray && dataArray.length==2){
			var columnDataMap = dataArray[0];
			var splineDataMap = dataArray[1];
			//画图
			drawCombinationChart(tag,categoriesArray, columnDataMap, splineDataMap,yAxisArray);
		}
	}
};


function drawIndex(){
	//画金字塔图
	var pyramidData = loadPyramidData(dataTopJson||[]);
	drawPyramid(pyramidData);
	//画六象限图
	drawCtfx6r();
	
	//默认画图20W以上客户占比
	var tag ="up20w";
	var categoriesArray =loadChartCategoriesArray("statdt");
	var dataArray = loadCombinationChart(tag);
	if(dataArray && dataArray.length==2){
		var columnDataMap = dataArray[0];
		var splineDataMap = dataArray[1];
		//画图
		var yAxisArray = yAxisTitleArrayMap.get(tag);
		drawCombinationChart(tag,categoriesArray, columnDataMap, splineDataMap,yAxisArray);
	}
}

