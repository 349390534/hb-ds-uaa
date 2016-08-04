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
	
	shTitil();
};

var exportPenetrateData=function(){
	var start = $("#start").val();
	var end = $("#end").val();
	var params ="start="+start+"&end="+end;
	var path = window.uaa_context_path ||"";
	var url=path+'/auth/dcc/exportData.htm?'+params;
	window.open(encodeURI(url));
};

function drawIndex(){
	drawChart();
}


function shTitil(){
	var  num1 =0;
	var  num2 =0;
	var  num3 =0;
	$("#con_main table td[tag]").each(function(i,obj){
		var tag =$(obj).attr("tag");
		if(tag == "1"){
			//查找隐藏的数据
			var v =$(obj).is(":hidden");
			if(v){
				num1++;
			}
		}else if(tag == "2"){
			var v =$(obj).is(":hidden");
			if(v){
				num2++;
			}
		}else if(tag == "3"){
			var v =$(obj).is(":hidden");
			//var v =$(obj).is(":visible");
			if(v){
				num3++;
			}
		}
	});
	
	$("#title td[tag=1]").show();
	//隐藏数量
	if(num1<=2){
		var arr_t1 = $("#title td[tag=1]");
		for(var i=arr_t1.length-1;i>=arr_t1.length-num1;i--){
			$(arr_t1[i]).hide();
		}
	}else if(num1==3){
		$("#title td[tag=1]").hide();
	}
	
	$("#title td[tag=2]").show();
	//隐藏数量
	if(num2>=1 && num2<=2){
		var arr_t1 = $("#title td[tag=2]");
		for(var i=arr_t1.length-1;i>=arr_t1.length-num2;i--){
			$(arr_t1[i]).hide();
		}
	}else if(num2==3){
		$("#title td[tag=2]").hide();
	}
	
	$("#title td[tag=3]").show();
	//隐藏数量
	if(num3>=1 && num3<=2){
		var arr_t1 = $("#title td[tag=3]");
		for(var i=arr_t1.length-1;i>=arr_t1.length-num3;i--){
			$(arr_t1[i]).hide();
		}
	}else if(num3==3){
		$("#title td[tag=3]").hide();
	}
}

