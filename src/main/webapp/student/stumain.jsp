<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'stumain.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/stumain.css">
  </head>
  <%
  	String userid=(String)session.getAttribute("username");
   %>
  <body>
  		<div class="header">
  			<div class="header_bar">
  				<div>学生信息管理系统</div>
  				<div>
  				欢迎你！<span id="userid"><%=userid %></span><a href="LogoutStudent">退出</a>
  				<%
	  			if(userid==null||userid.equals("")){
	  				out.print("<script>alert('登录失效，请重新登陆！');window.location='stulogin.html';</script>");
	  				//response.sendRedirect("mslogin.html");
	  			}
	  			 %>
  				</div>
  			</div>
  		</div>
  		<div class="main_container" id="main_container">
  			<div class="left_content">
  				<iframe src="${pageContext.request.contextPath}/student/stuleft.jsp?userid=<%=userid%>" id="left_iframe" name="left_iframe">
    			</iframe>
  			</div>
  			<div class="right_maincontent">
  				<iframe src="${pageContext.request.contextPath}/welcome.jsp" id="right_iframe" name="right_iframe" height="100%">
    			</iframe>
    			<!-- <object data="../welcome.jsp" width="100%" height="100%">
        				<embed src="../welcome.jsp" width="100%" height="100%"> </embed>
        				Error: Embedded data could not be displayed.
    			</object> -->
  			</div>
  		</div>
  		<div class="footer">
  			<div class="footer_inc"><span>学生信息管理系统 ©2014-2018 MAIKE (geekcjj.top)问题求助|产品建议请上<a href="http://geekcjj.top">新工科Lab</a>&nbsp;&nbsp;&nbsp;京ICP备16037175号</span></div>
			<div class="footer_other"><span>版权所有：MAIKE工作室   联系电话：000-000-000</span></div>
		</div>
		<script type="text/javascript">
			window.onload=function(){
				//var userid=;
				//autodivheight();
				var ifheight= document.getElementById("main_container").offsetHeight;
		    	//获取浏览器窗口高度
				var winHeight=window.innerHeight;
				//container.style.height=winHeight-120+"px";
				//container.style.lineHeight=winHeight-120+"px";
				document.getElementById("main_container").style.height= winHeight-160+"px";
				document.getElementById("main_container").style.lineHeight= winHeight-160+"px";
			 	document.getElementById("left_iframe").style.height= winHeight-164+"px";
				document.getElementById("right_iframe").style.height= winHeight-164+"px";
				document.getElementById("right_iframe").style.lineHeight= winHeight-164+"px";
				//alert(ifheight);
				//document.getElementById("userid").innerHTML="没有";
			}
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
				document.getElementById("main_container").style.height= winHeight-160+"px";
				document.getElementById("main_container").style.lineHeight= winHeight-160+"px";
				document.getElementById("left_iframe").style.height= winHeight-164+"px";
				document.getElementById("right_iframe").style.height= winHeight-164+"px";
				document.getElementById("right_iframe").style.lineHeight= winHeight-164+"px";
			}
			window.onresize=function(){
				autodivheight(); //浏览器窗口发生变化时同时变化DIV高度
			}
		</script>
  </body>
</html>
