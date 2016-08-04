<#import "/uri.ftl" as uri />
<!DOCTYPE HTML>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<meta name="Description" content="" />
	<meta name="Keywords" content="" />
	<title>渠道管理</title>
	<@uri.link href = ["/change.css","/reset.css","/style.css"]/>
	
</head>
<body>

<!-- main begin-->
<div class="main">
	<div class="chartbox">
		<div class="title">
			<h3>渠道管理</h3>
		</div>
		<div class="chartbox_tab">
			<div class="box">
				<div class="qd_con fl">
					<div class="grade1">
						<p class="qd_name">一级渠道&emsp;<t id="channelType" style="display:none;"></t></p>
						<div id="qdOne">
						<#include "../include/routeLevelOne.ftl">
						</div>
						<div class="creat">
							<input name="" type="button" value="新建" class="btn-style-b fl" style="margin-left:0;" id="newOne"/>
							<input name="" type="button" value="修改" class="btn-style-b fl" id="changeOne"/>
							<input name="" type="button" value="删除" class="btn-style-b fr" style="display:none"/>
						</div>
						<div class="change" style="display:none">
							<div class="change_tt" id="qdOneName">输入修改渠道名称：</div>
							<div class="change_input">
								<input type="text" value="" class="change_name" id="channelOne" onclick="select();" />
								<input name="" type="button" value="确定" class="btn-style-b fr"  id="submitOne"/>
							</div>
						</div>
					</div>
					<div class="grade1">
						<p class="qd_name">二级渠道</p>
						<div id="qdTwo">
						<#include "../include/routeLevelTwo.ftl">
						</div>
						<div class="creat">
							<input name="" type="button" value="新建" class="btn-style-b fl" style="margin-left:0;" id="newTwo" />
							<input name="" type="button" value="修改" class="btn-style-b fl" id="changeTwo"/>
							<input name="" type="button" value="删除" class="btn-style-b fr" style="display:none" />
						</div>
						<div class="change" style="display:none">
							<div class="change_tt" id="qdTwoName">输入修改渠道名称：</div>
							<div class="change_input">
								<input type="text" value="" class="change_name" id="channelTwo" onclick="select();"/>
								<input name="" type="button" value="确定" class="btn-style-b fr" id="submitTwo"/>
							</div>
						</div>
					</div>
					<div class="grade1" style="padding-right:0;">
						<p class="qd_name">三级渠道</p>
						<div id="qdThree">
						<#include "../include/routeLevelThree.ftl">
						</div>
						<div class="creat">
							<input name="" type="button" value="新建" class="btn-style-b fl" style="margin-left:0;" id="newThree" />
							<input name="" type="button" value="修改" class="btn-style-b fl" id="changeThree"/>
							<input name="" type="button" value="删除" class="btn-style-b fr" style="display:none" />
						</div>
						<div class="change" style="display:none">
							<div class="change_tt" id="qdThreeName">输入修改渠道名称：</div>
							<div class="change_input">
								<input type="text" value="" class="change_name" id="channelThree" onclick="select();" />
								<input name="" type="button" value="确定" class="btn-style-b fr" id="submitThree"/>
							</div>
						</div>
					</div>
				</div>
				<div class="qd_show fl">
					<div id="routedetail">
						<#include "../include/routeDetail.ftl">
					</div>
					<div class="website">
						<p>生成访问链接：</p>
						<div style="font-size:14px;">输入网址：<input name="" type="text" value="" id="srwz" class="copysite"><input  name="" type="button" value="生成链接" id="sclj"class="btn-style-b fr"></div>
						<div style="margin:15px 0px;font-size:14px;" ><label style=" vertical-align:top;">推广地址：</label><textarea class="tgsite" disabled id="tgdz"></textarea><input name="" type="button" value="复制链接" id="fzlj" class="btn-style-b fr" style="float:right"></div>
					</div>
				</div>
			</div>
		</div>						
	</div>
	
</div>
<!-- main end-->

<script>
	$(function(){
		
		var change = $(".change");//修改区域
		var level;//渠道等级

		
		
		/*
		 * 一级渠道      增、 改
		 */
		var flagOne = 0;
		var channelOne = $("#channelOne");
		var channelOneArr;
		$("#newOne").click(function(){
			change.eq(0).show();
			var html='<qd_radio id="qd_radio"><br>'+
			'<input type="radio" value="1" style="margin-top: 5px;" name="channelType" checked="checked"/>网站&nbsp;'+
			'<input type="radio" value="2" style="margin-top: 5px;" name="channelType" />无线'+
			'</qd_radio>';
			channelOne.after(html);
			flagOne = 0;
			$("#qdOneName").html("输入新建渠道名称");
			channelOne.attr("placeholder","新渠道名称").val("");
		})
		$("#changeOne").click(function(){
			var select = $("#selectOne").val();
			if(select == "" || select==null)
				alert("请选择需要修改的渠道！！");
			else{
				$("#qdOneName").html("输入修改渠道名称");
				change.eq(0).show();
				$("#qd_radio").detach();
				flagOne = 1;
				channelOneArr = $("#selectOne").val().split("$$")
				channelOne.val(channelOneArr[0]);
			}
		})
		$("#submitOne").click(function(){
			level = 1;
			if(channelOne.val()=="" || channelOne.val()==null){
				alert("请输入渠道名称！！");
				channelOne.focus();
				return false;
			}
			else{
				if(flagOne == 0){
				debugger;
					var channelType=$("qd_radio input[type=radio]:checked").val();
					
					var param = "level="+level+"&routeName="+channelOne.val()+"&parentId="+"&channelType="+channelType;
					ajaxRequest(
		                    {url:'${uri.context_path}/auth/routemanager/add.htm',
		                    postMethod:'POST',
		                    params:param,
		                    callback:function(data){
		                    	alert("添加成功！！");
		                    	$('#qdOne').html(data);
		                    	$("#selectOne option:last-child").attr("selected","selected");
		                    	var channelType =  $("#selectOne").find("option:selected").attr("type");
								var tp ="未知";
								if("1"==channelType){
									tp="网站";
								}else if("2"==channelType){
									tp="无线";
								}
								$("t#channelType").html(tp).show();
		                    	$("qd_radio#qd_radio").detach();
		                    	//隐藏输入框
		                    	change.eq(0).hide();
		                    	//二级三级渠道，表格初始化 
		                    	$("#qdTwo").html('<select name="" size="10" id="selectTwo" class="qd_list"></select>');
		                    	$("#qdThree").html('<select name="" size="10" id="selectThree" class="qd_list"></select>');
		                    	$("#routedetail").html('<table><tr><td width="18%" class="t tdlt">推广渠道</td><td width="82%" class="tdlt"></td></tr><tr><td class="t tdlt">HTAG</td><td class="tdlt"></td></tr><tr><td class="t tdlt">申请时间</td><td class="tdlt"></td></tr><tr><td class="t tdlt">申请人</td><td class="tdlt"></td></tr><tr><td class="t tdlt">备注</td><td></td></tr></table>')
		                    	}
		                    });
				}
				if(flagOne == 1){
					
					var param = "level="+level+"&routeName="+channelOne.val()+"&parentId="+"&id="+channelOneArr[1];
					ajaxRequest(
		                    {url:'${uri.context_path}/auth/routemanager/change.htm',
		                    postMethod:'POST',
		                    params:param,
		                    callback:function(data){
		                    	alert("修改成功！！");
		                    	$('#qdOne').html(data);
		                    	
		                    	$("#"+channelOneArr[1]).attr("selected","selected");
		                    	$("#selectOne").change();
		                    	
		                    	$("#qdThree").html('<select name="" size="10" id="selectThree" class="qd_list"></select>');
		                    	$("#routedetail").html('<table><tr><td width="18%" class="t tdlt">推广渠道</td><td width="82%" class="tdlt"></td></tr><tr><td class="t tdlt">HTAG</td><td class="tdlt"></td></tr><tr><td class="t tdlt">申请时间</td><td class="tdlt"></td></tr><tr><td class="t tdlt">申请人</td><td class="tdlt"></td></tr><tr><td class="t tdlt">备注</td><td></td></tr></table>')
		                    	//隐藏输入框
		                    	change.eq(0).hide();
		                    	}
		                    });
				}
				
			}
			
		})
		/*
		 * 二级渠道      增、 改
		 */
		var flagTwo = 0;
		var channelTwo = $("#channelTwo");
		var channelTwoArr;
		$("#newTwo").click(function(){
			var p = $("#selectOne").val();
			if( p!==""&&p!==null){
				change.eq(1).show();
				flagTwo = 0;
				$("#qdTwoName").html("输入新建渠道名称");
				channelTwo.attr("placeholder","新渠道名称").val("");
			}
			else{
				alert("请选择一级渠道!!");
			}
		})
		$("#changeTwo").click(function(){
			var select = $("#selectTwo").val();
			if(select == "" || select==null)
				alert("请选择需要修改的渠道！！");
			else{
				$("#qdTwoName").html("输入修改渠道名称");
				change.eq(1).show();
				flagTwo = 1;
				channelTwoArr = $("#selectTwo").val().split("$$")
				channelTwo.val(channelTwoArr[0]);
			}
		})
		$("#submitTwo").click(function(){
			level = 2;
			if(channelTwo.val()=="" || channelTwo.val()==null){
				alert("请输入渠道名称！！");
				channelTwo.focus();
				return false;
			}
			else{
				if(flagTwo == 0){
					var oneQid=null;
					var selectOne = $("#selectOne").val().split("$$");
					var parentId = selectOne[1];
					oneQid = selectOne[2];
					var channelType=$("#selectOne").find("option:selected").attr("type");
					var param = "level="+level+"&routeName="+channelTwo.val()+"&parentId="+parentId+"&oneQid="+oneQid+"&channelType="+channelType;
					ajaxRequest(
		                    {url:'${uri.context_path}/auth/routemanager/add.htm',
		                    postMethod:'POST',
		                    params:param,
		                    callback:function(data){
		                    	alert("添加成功！！");
		                    	$('#qdTwo').html(data);
		                    	$("#selectTwo option:last-child").attr("selected","selected");
		                    	
		                    	//隐藏输入框
		                    	change.eq(1).hide();
		                    	$("#qdThree").html('<select name="" size="10" id="selectThree" class="qd_list"></select>');
		                    	$("#routedetail").html('<table><tr><td width="18%" class="t tdlt">推广渠道</td><td width="82%" class="tdlt"></td></tr><tr><td class="t tdlt">HTAG</td><td class="tdlt"></td></tr><tr><td class="t tdlt">申请时间</td><td class="tdlt"></td></tr><tr><td class="t tdlt">申请人</td><td class="tdlt"></td></tr><tr><td class="t tdlt">备注</td><td></td></tr></table>')
		                    	}
		                    });
				}
				if(flagTwo == 1){
					
					var param = "level="+level+"&routeName="+channelTwo.val()+"&parentId="+$("#selectOne").val().split("$$")[1]+"&id="+channelTwoArr[1];
					ajaxRequest(
		                    {url:'${uri.context_path}/auth/routemanager/change.htm',
		                    postMethod:'POST',
		                    params:param,
		                    callback:function(data){
		                    	alert("修改成功！！");
		                    	$('#qdTwo').html(data);
		                    	
		                    	
		                    	$("#"+channelTwoArr[1]).attr("selected","selected");
		                    	$("#selectTwo").change();
		                    	$("#routedetail").html('<table><tr><td width="18%" class="t tdlt">推广渠道</td><td width="82%" class="tdlt"></td></tr><tr><td class="t tdlt">HTAG</td><td class="tdlt"></td></tr><tr><td class="t tdlt">申请时间</td><td class="tdlt"></td></tr><tr><td class="t tdlt">申请人</td><td class="tdlt"></td></tr><tr><td class="t tdlt">备注</td><td></td></tr></table>')
		                    	//隐藏输入框
		                    	change.eq(1).hide();
		                    	}
		                    });
				}
			}
			
		})
		
		/*
		 * 三级渠道增改
		 */
		var flagThree = 0;
		var channelThree = $("#channelThree");
		var channelThreeArr;
		$("#newThree").click(function(){
			var p = $("#selectTwo").val();
			if( p!==""&&p!==null){
				$("#qdThreeName").html("输入新建渠道名称");
				change.eq(2).show();
				flagThree = 0;
				channelThree.attr("placeholder","新渠道名称").val("");
			}
			else{
				alert("请选择二级渠道!!");
			}
		})
		$("#changeThree").click(function(){
			var select = $("#selectThree").val();
			if(select == "" || select==null)
				alert("请选择需要修改的渠道！！");
			else{
				$("#qdThreeName").html("输入修改渠道名称");
				change.eq(2).show();
				flagThree = 1;
				channelThreeArr = $("#selectThree").val().split("$$")
				channelThree.val(channelThreeArr[0]);
			}
		})
		$("#submitThree").click(function(){
			level = 3;
			if(channelThree.val()=="" || channelThree.val()==null){
				alert("请输入渠道名称！！");
				channelThree.focus();
				return false;
			}
			else{
				if(flagThree == 0){
					
					var selectOne = $("#selectOne").val().split("$$");
					var tag1 = selectOne[2];
					var tag2 = $("#selectTwo").val().split("$$")[2];
					var routedetail = $("#selectOne").val().split("$$")[0] +'-'+ $("#selectTwo").val().split("$$")[0]+ '-';
					var param = "level="+level+"&routeName="+channelThree.val()+"&parentId="+$("#selectTwo").val().split("$$")[1]+"&htag="+addZero(tag1,3)+addZero(tag2,3)+"&routeDetail="+routedetail;
					
					var oneQid=null;
					oneQid = selectOne[2];
					var twoQid = tag2;
					var channelType=$("#selectOne").find("option:selected").attr("type");
					param+="&oneQid="+oneQid +"&twoQid="+twoQid+"&channelType="+channelType;
					ajaxRequest(
		                    {url:'${uri.context_path}/auth/routemanager/add.htm',
		                    postMethod:'POST',
		                    params:param,
		                    callback:function(data){
		                    	alert("添加成功！！");
		                    	$('#qdThree').html(data);
		                    	var lastchild = $("#selectThree option:last-child");
		                    	lastchild.attr("selected","selected");
		                    	$("#selectThree").change();
		                    	//隐藏输入框
		                    	change.eq(2).hide();
		                    	}
		                    });
				}
				if(flagThree == 1){
					
					var param = "level="+level+"&routeName="+channelThree.val()+"&parentId="+$("#selectTwo").val().split("$$")[1]+"&id="+channelThreeArr[1];
					ajaxRequest(
		                    {url:'${uri.context_path}/auth/routemanager/change.htm',
		                    postMethod:'POST',
		                    params:param,
		                    callback:function(data){
		                    	alert("修改成功！！");
		                    	$('#qdThree').html(data);
		                    	$("#"+channelThreeArr[1]).attr("selected","selected");
		                    	$("#selectThree").change();
		                    	//隐藏输入框
		                    	change.eq(2).hide();
		                    	}
		                    });
				}
			}
			
		})
		
		
	})
	
	//生成复制链接
	$("#sclj").click(function(){
								
		var addr = $("#srwz").val();
//		var result = addr.match(/^http:\/\/www\..+$|/);
		if(addr == ""){
			alert("请输入网址！！");
			return false;
		}
		else{
			if(addr.indexOf("?")>=0)
				$("#tgdz").html(addr+"&HTAG="+$("#routehtag").text());
			else
				$("#tgdz").html(addr+"?HTAG="+$("#routehtag").text());
		}
		
		
	});
    
    $("#fzlj").zclip({
    	path: "${uri.context_path}/js/jquery/ZeroClipboard.swf",
    	copy: function(){
    	return $("#tgdz").text();
    			},
    	afterCopy:function(){
    		alert("复制成功！！推广地址为："+$("#tgdz").text());
    	}
});
    
    //补零操作
    function addZero(str,length){                 
        return new Array(length - str.length+1).join("0") + str;                
    } 
	//Ajax请求
	//ajaxRequest({'url':'',postMethod:'', 'params':'', 'callback':function(data){});
	function ajaxRequest(obj) {
	    var param = obj.params;
	    var postmethod = obj.postMethod;
	    
	    if(/\..*\?/g.test(obj.url)){
	        obj.url = obj.url + '&ajax=true';
	    }else{
	        obj.url = obj.url + '?ajax=true';
	    }
	    
	    if(!obj.dataType){
	        obj.dataType = 'html';
	    }
	    
	    if(!obj.timeout){
	        obj.timeout = 0;
	    }
	    $.ajax({
	           type: postmethod,
	           cache: false,
	           url: obj.url,
	           data : param,
	           dataType: obj.dataType,
	           timeout: obj.timeout,
	           success: function(data){
	              
	               if (typeof(obj.callback) == "function") {
	                   obj.callback(data);
	               }
	           },
	           error:function(xhr,err){ 
	               if(xhr.responseText == 'SESSION_TIME_OUT'){
	                   alert('您已退出系统，请重新登录.');
	                   if(window.loginURL){
	                       window.location.href = window.loginURL;
	                   }
	               }else if(xhr.statusText == 'timeout'){
	                   //这里的处理将影响全局功能
	               }else{
	                   alert('系统异常，请稍后再试.');
	               }
	            }
	            
	         });
	}
</script>
</body>
</html>