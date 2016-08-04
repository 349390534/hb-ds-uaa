<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="org.apache.log4j.Logger" %>    
<%
	String uri = (String) request.getAttribute("javax.servlet.error.request_uri");
	String queryString = (String) request.getAttribute("javax.servlet.forward.query_string");
	Logger logger = Logger.getLogger(this.getClass());
	logger.debug("SimuErrorUrl=>"+uri+"?"+queryString);
	String contextPath = request.getContextPath();
	if (contextPath.length()>0){
		contextPath = "/" + contextPath;
	}
%>     
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>出错啦!对不起您访问的页面不存在！400</title>
<link href="<%=contextPath%>/style/public.css" rel="stylesheet" type="text/css" />
<style type="text/css">
body {
	background:#eee;
	line-height:24px;
}
.column {
	width:500px;
	margin:150px auto;
	border:#b6c8d4 1px solid;
	background:#fff;
}
.column .body { }
.column .body .iocn {
	margin:10px 20px;
	font-size:14px;
	line-height:100px;
	background:url(/images/iocn/Iocn3.gif) no-repeat left center;
	padding-left:60px;
	height:100px;
	border-bottom:1px solid #ddd;
}
.column .footer {
	padding:5px 15px;
	text-align:left;
	clear:both;
}
.column ul {
	clear:both;
	line-height:24px;
}
.column ul li {
	float:left;
	width:90px;
	text-indent:2em;
	font-size:14px;
}
p {
	text-indent:2em;
	clear:both;
	line-height:24px;
}
</style>
<link type="image/x-icon" href="<%=contextPath%>/favicon.ico" rel="shortcut icon"/>
</head>
<body>
<div class="column">
  <div class="head"><b style="color:#d80012;">提示</b></div>

  <div class="body buletored">
    <div class="iocn">对不起，您访问的页面不存在！返回 <a href="http://www.howbuy.com/">好买首页</a></div>
    <div>
      <p>您可以快速地访问以下页面：</p>
      <ul>
        <li><a href="http://www.howbuy.com/research/">研究报告</a></li>
        <li><a href="http://www.howbuy.com/news/">新闻</a></li>

        <li><a href="http://www.howbuy.com/fund/">公募基金</a></li>
        <li><a href="http://simu.howbuy.com/">私募基金</a></li>
        <li><a href="http://www.howbuy.com/vcpe/">私募股权</a></li>
        <li><a href="http://www.howbuy.com/zhlc/">一对多</a></li>
        <li><a href="http://www.howbuy.com/forum">社区</a></li>
        <li><a href="http://blog.howbuy.com/">博客</a></li>

        <li><a href="http://www.howbuy.com/about/">关于好买</a></li>
      </ul>
      <p>&nbsp;</p>
    </div>
  </div>  
</div>
</body>
</html>