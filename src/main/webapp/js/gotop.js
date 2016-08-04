// JavaScript Document
//返回顶部
$(function(){
	$("body").append('<div id="gotoTop"><a href="javascript:void(null)" target="_self"></a></div>');
	$("body").append('<style type="text/css">#gotoTop{display:none;position:fixed;z-index:10000;right:10px;bottom:120px;_position:absolute;_top:expression(eval(document.documentElement.scrollTop+document.documentElement.clientHeight-this.offsetHeight-(parseInt(this.currentStyle.marginTop,10)||120)-(parseInt(this.currentStyle.marginBottom,10)||0)));}#gotoTop a{-ms-filter:"progid:DXImageTransform.Microsoft.Alpha(Opacity=50)";filter: alpha(opacity=50);-moz-opacity:0.5;-khtml-opacity: 0.5;opacity: 0.5;display:block;width:60px;height:50px;background:url(http://www.howbuy.com/subject/images/icon_top.png) center center no-repeat #777;border-radius:2px;box-shadow:0 1px 3px rgba(0,0,0,.2);cursor:pointer;-webkit-transition:all .4s;-moz-transition:all .4s;-ms-transition:all .4s;-o-transition:all .4s;outline:none;}#gotoTop a:hover{background-color:#333}</style>');
	var $topID = $('#gotoTop');
	$(window).scroll(function() {
		if($(window).scrollTop() >= 150){
			$topID.show();
			$topID.stop().animate({opacity:1},50);
	    }
	    else {
		    $topID.stop().animate({opacity:0},50);
		    $topID.hide();
	    }
	});
    $topID.click(function(){
        $('html,body').stop().animate({scrollTop: '0'},200);
        return false;
    });
});