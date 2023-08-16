<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%	request.setCharacterEncoding("utf-8");
response.setCharacterEncoding("utf-8"); 
%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'teachermain.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/teachermain.css">

  </head>
  
  <body>
  <%
  	String userid=(String)session.getAttribute("userid");
  	String incollege=(String)session.getAttribute("incollege");
  	String realname=(String)session.getAttribute("realname");
   %>
    <div class="header">
    	<div class="top_bar">
    		<div class="top_bar_description">MK教师端管理系统&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;当前日期时间：<span id="showDate"></span></div>
    		<div class="top_bar_opretion">欢迎您!<%=userid %>&nbsp;&nbsp;姓名：<%=realname %>&nbsp;&nbsp;所在学院：<span id="myincollege"><%=incollege %></span>&nbsp;&nbsp;&nbsp;<a href="LogoutTeacher">退出</a></div>
    		<%
  			if(userid==null||userid.equals("")){
  				out.print("<script>alert('登录失效，请重新登陆！');window.location='teacherlogin.html';</script>");
  				//response.sendRedirect("mslogin.html");
  			}
  			%>
    	</div>
    	<div class="menu_bar">
    	<ul>
    	<li><a href="teacher/teachermainpage.html" target="main_iframe">首页</a></li>
    	<li><a href="teacher/teacherpersinal.jsp?incollege=<%=incollege %>&teacherid=<%=userid %>" target="main_iframe">教师页面</a></li>
    	<li><a href="teacher/addCourseScore.jsp?incollege=<%=incollege %>&teacherid=<%=userid %>&realname=<%=realname %>" target="main_iframe">学生课程成绩管理</a></li>
    	<li><a href="teacher/addteachcourse.jsp?incollege=<%=incollege %>&teacherid=<%=userid %>" target="main_iframe">我的课程管理</a></li>
    	<li><a>关于教师</a></li>
    	</ul>
    	</div>
    </div>
    <div class="container" id="container">
    	<iframe src="teacher/teachermainpage.html" width="90%" height="100%" align="middle" id="ifcontainer" frameborder="0" name="main_iframe"></iframe>
    </div>
    <div class="footer">
    	<div class="footer_inc">学生信息管理系统 ©2014-2018 MAIKE (geekcjj.top)问题求助|产品建议请上<a href="http://geekcjj.top">新工科Lab</a>&nbsp;&nbsp;&nbsp;京ICP备16037175号</div>
		<div class="footer_other">版权所有：MAIKE工作室   联系电话：000-000-000<span id="test"></span></div>
    </div>
    <script type="text/javascript" src="./js/jquery-3.3.1.js"></script>
    <script type="text/javascript">
	$(function(){
		setInterval("getTime();",1000); //每隔一秒运行一次
	})
	//取得系统当前时间
	function getTime(){
		var myDate = new Date();
		var date = myDate.toLocaleDateString();
		var hours = myDate.getHours();
		var minutes = myDate.getMinutes();
		var seconds = myDate.getSeconds();
		$("#showDate").html(date+" "+hours+":"+minutes+":"+seconds); //将值赋给div
	}
	/* function initload(){
		var container= document.getElementById("container");
    	//获取浏览器窗口高度
		var winHeight=document.documentElement.clientHeight;
		container.style.height=winHeight-120+"px";
		container.style.lineHeight=winHeight-120+"px";
	} */
	window.onload=function(){
		//autodivheight();
		//var container= document.getElementById("container");
    	//获取浏览器窗口高度
		var winHeight=window.innerHeight;
		//container.style.height=winHeight-120+"px";
		//container.style.lineHeight=winHeight-120+"px";
		document.getElementById("container").style.height= winHeight-120+"px";
		document.getElementById("container").style.lineHeight= winHeight-120+"px";
		//alert(winHeight);
	}
	//$(window).onresize=function(){
			//var winHeight=document.documentElement.clientHeight;
			//changedivHeight(winHeight);
	     	//var dheight=document.getElementById("container").style.height;
	     	//var windowheight=winHeight.substr(0, winHeight.length-2);
	     	//alert(winHeight);
	     	//document.getElementById("container").style.height=winHeight-120+"px";
	     	//document.getElementById("container").style.lineHeight=winHeight-120+"px";
	//}
	/* var resizeTimer = null; 
	$(window).bind('resize', function (){ 
	if (resizeTimer) clearTimeout(resizeTimer); 
	resizeTimer = setTimeout(function(){ 
			//var winHeight=document.documentElement.clientHeight;
			changedivHeight();
	} , 100); 
	}); */
	autodivheight();
	function autodivheight(){ //函数：获取尺寸
		//获取浏览器窗口高度
		var winHeight=window.innerHeight;
		/* if (window.innerHeight)
			winHeight = window.innerHeight;
		else if ((document.body) && (document.body.clientHeight))
			winHeight = document.body.clientHeight;
		//通过深入Document内部对body进行检测，获取浏览器窗口高度
		if (document.documentElement && document.documentElement.clientHeight)
			winHeight = document.documentElement.clientHeight; */
		//DIV高度为浏览器窗口的高度
		document.getElementById("container").style.height= winHeight-120+"px";
		document.getElementById("container").style.lineHeight= winHeight-120+"px";
	}
	window.onresize=function(){
		autodivheight(); //浏览器窗口发生变化时同时变化DIV高度
	}
	</script>
  </body>
</html>
