/**
 * 穿透分析 评分js
 */

function bindToggleTab(custTypeJsonData){
	$("#custTypeUl li").click(function(){
		var _this = $(this);
		var custType = _this.attr("custType");
		_this.addClass("current").siblings("li").removeClass("current");
		drawCustChart(custTypeJsonData,custType);
	});
}


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
		$("table.g_head td[q]").each(function(i,obj){
			if(ch){
				$(obj).show();
			}else{
				$(obj).hide();
			}
		});
		$("table.det-d-qdmx2 td[q]").each(function(i,obj){
			if(ch){
				$(obj).show();
			}else{
				$(obj).hide();
			}
		});
	}else {
		if(ch){
			$("table.g_head td[q='"+q+"']").show();
			$("table.det-d-qdmx2 td[q='"+q+"']").show();
		}else{
			$("table.g_head td[q='"+q+"']").hide();
			$("table.det-d-qdmx2 td[q='"+q+"']").hide();
		}
	}
	
};

var exportPenetrateData=function(){
	var path = window.uaa_context_path ||"";
	var url=path+'/auth/dcg/exportData.htm';
	window.open(encodeURI(url));
};
 
 
