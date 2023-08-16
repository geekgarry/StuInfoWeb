<%@page import="com.maike.entity.AdminOnline"%>
<%@page import="com.maike.listener.CountUtils"%>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%String username=(String)session.getAttribute("username"); %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'msmain.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	
	<link rel="stylesheet" type="text/css" href="css/msmain2.css">

  </head>
  <body onload="jiaru()">
  		<div class="header">
  			<div class="header_bar">
  				<div id="showdescription">学生信息管理系统</div>
  				<div id="showdate">当前日期时间：<span id="showDate"></span></div>
  			</div>
  		</div>
  		<div class="main_container" id="main_container">
  		<div class="aside">
  			<div class="msstatus" id="msstatus">
  			<div >当前状态</div>
  			<div >账号：<%=username %></div>
  			<% 
  				HashSet<HttpSession> sessions=(HashSet<HttpSession>)application.getAttribute("sessions");
  				//CountUtils.getLoginCount()
  				/* ArrayList<AdminOnline> userlist = (ArrayList<AdminOnline>) request.getServletContext().getAttribute("userlist"); 
  				if (userlist != null) { 
  				for (int i = 0; i < userlist.size(); i++) { 
  				AdminOnline user = userlist.get(i); */
  			%>
  			<%-- sessionID:<%=user.getSessionID()%>
    		<br>
    		IP:<%=user.getIp()%>
    		<br>
   			FirstName:<%=user.getFirstTime()%>
    		<hr>
  			<%}}%> --%>
  			<div >在线人数：<a href="allonlineadminuser.jsp" target="_blank"><%=sessions.size() %></a><%-- ** ${number} --%> </div>
  			<div ><a href="LogoutAdminUser">退出</a></div>
  			</div>
  			<%
  			if(username==null||username.equals("")){
  				out.print("<script>alert('登录失效，请重新登陆！');window.location='mslogin.html';</script>");
  				//response.sendRedirect("mslogin.html");
  			}
  			 %>
  			<div class="aside_div">
  			<iframe src="msleft.html" id="left_iframe" name="left_iframe">
    		</iframe>
    		</div>
  		</div>
  		<div class="section" id="section">
  			<div class="section_div">
  			<iframe src="msright.jsp" id="right_iframe" name="right_iframe">
    		</iframe>
    		</div>
  		</div>
  		</div>
  		<div class="footer">
  			<div class="footer_inc">学生信息管理系统 ©2014-2018 MAIKE (geekcjj.top)问题求助|产品建议请上<a href="http://geekcjj.top">新工科Lab</a>&nbsp;&nbsp;&nbsp;京ICP备16037175号</div>
			<div class="footer_other">版权所有：MAIKE工作室   联系电话：000-000-000</div>
		</div>
		<script type="text/javascript" src="js/jquery-3.3.1.js"></script>
		<script type="text/javascript">
		 	autodivheight();
			function autodivheight(){ //函数：获取尺寸
				var statusheight=document.getElementById("msstatus").offsetHeight;
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
				document.getElementById("right_iframe").style.height= winHeight-110+"px";
				document.getElementById("right_iframe").style.lineHeight= winHeight-110+"px";
				document.getElementById("left_iframe").style.height= winHeight-110-statusheight+"px";
				document.getElementById("left_iframe").style.lineHeight= winHeight-110-statusheight+"px";
			}
			window.onresize=function(){
				autodivheight(); //浏览器窗口发生变化时同时变化DIV高度
			}
			/* window.onbeforeunload = function () { 
			return "退出?"; 
			//return null; 
			}; */ 
			//取消监测调用下面的方法 
			/* function unbindunload() { 
			window.onbeforeunload=null;//取消事件绑定 
			} */
			window.onbeforeunload = function() //author: meizz   
       		{   
              var n = window.event.screenX - window.screenLeft;   
              var b = n > document.documentElement.scrollWidth-20;
			//判断是关闭还是刷新
			if(event.clientX>document.body.clientWidth&&event.clientY<0||event.altKey)
			{
			    window.location="LogoutAdminUser";
			}
			}
			function jiaru()
			{
			  	checkloginstatus();
			  	var winHeight=window.innerHeight;
				var statusheight=document.getElementById("msstatus").offsetHeight;
				document.getElementById("right_iframe").style.height= winHeight-110+"px";
				document.getElementById("right_iframe").style.lineHeight= winHeight-110+"px";
				document.getElementById("left_iframe").style.height= winHeight-110-statusheight+"px";
				document.getElementById("left_iframe").style.lineHeight= winHeight-110-statusheight+"px";
				//alert(statusheight);
			}
			function checkloginstatus(){
			  var a='<%=username %>';
			  if(a=="null"||a=="")
			  {
			  alert("您还未登陆，请先登录！");
			  window.location.href="mslogin.html";
			  return false;
			  }
			  setTimeout("checkloginstatus()", 4000);// 每隔1秒自动访问服务器，只访问一次
			  //alert("test");
			}
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
		</script>
  </body>
</html>
