<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'stuleft.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.2.6.min.js"></script>
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/msleft.css">
  </head>
  <%	
  String userid=(String)request.getParameter("userid");
   %>
  <body onload="changeFrameHeight()">  
  <div class="container">
		<div
			style="background: #FFF url('images/gradients.png') 0 -280px repeat-x;
			border: 1px solid #CCC;
			border-radius: 5px;
			margin: 0 auto;
			height:95%;
			min-width: 140px;
			max-width:220px;
			font-size: 12px;
			"
			id="leftmenu">
			 <br>
			<h2>
				<b>
				<font color="#ff6600" style="font-family:'华文行楷';" >
				<p>学生信息管理系统</p>
				</font>
				</b>
			</h2>
			<br>
			<ul class="YOUR_SELECTOR_Menu">
				<li><span><h4> <b><font color="#006600" >课程成绩查询</font></b></h4></span>
					<ul>
						<li><a id="queryScore" href="student/querypersonalscore.jsp?userid=<%=userid %>" target="right_iframe"><b><font color="#5eaeae">成绩查询</font></b></a>
						</li>
						<li><a id="queryScore" href="student/querycoursedetail.jsp?userid=<%=userid %>" target="right_iframe"><b><font color="#5eaeae">课程查询</font></b></a>
						</li>
					</ul>
				</li>
				<!-- <li><span><h4> <b><font color="#006600">学生成绩管理</font></b></h4></span>
					<ul>
						<li><a href="listCourseScore.html" target="right_iframe"><b><font color="#5eaeae">查询学生成绩</font></b></a>
						</li>
						<li><a href="listcourse.html" target="right_iframe"><b><font color="#5eaeae">所有学科信息</font></b></a>
						</li>
						<li><a href="addCourse.html" target="right_iframe"><b><font color="#5eaeae">添加新的学科信息</font></b></a>
						</li>
					</ul>
				</li>
				<li><span><h4> <b><font color="#006600">管理员信息管理</font></b></h4></span>
					<ul>
						<li><a href="listalladminuser.html" target="right_iframe"><b><font color="#5eaeae">所有管理员</font></b></a>
						</li>
						<li><a href="addadminuser.html" target="right_iframe"><b><font color="#5eaeae">添加新管理员</font></b></a>
						</li>
					</ul>
				</li> -->
				<li><span><h4> <b><font color="#006600">其他功能</font></b></h4> </span>
					<ul>			 
					<!-- 	<li><a href="./myflash/myflash.html" target="mainFrame"><b> <font color="#5eaeae">关于我们</font></b></a> -->
					  	<li><a href="aboutus.html" target="right_iframe"><b> <font color="#5eaeae">关于我们</font></b></a>  
						</li> 
						<li><a href="help.html" target="right_iframe"><b><font color=#5eaeae>帮助</font></b></a></li>
					<!-- 	<li><a href="#" target="mainFrame"><b><font color="#5eaeae">退出</font></b></a></li> -->
					</ul>
				</li>
			</ul>
		</div>
	</div>
	<script type="text/javascript">
		/* function GetQueryString(name)
			{
			     var reg = new RegExp("(^|&)"+ name +"=([^&]*)(&|$)");
			     var r = window.location.search.substr(1).match(reg);
			     if(r!=null)
			     return  unescape(r[2]); 
			     return null;
			} */
			// 调用方法
			//alert(GetQueryString("userid"));
			//var uname = GetQueryString("参数名");
		 function changeFrameHeight(){
    		var leftmenu= document.getElementById("leftmenu");
			leftmenu.height=document.documentElement.clientHeight-100;
			//$("a #queryScore").attr("href","student/querypersonalgrade.html?userid="+GetQueryString("userid"));
			//$("#queryScore").href="student/querypersonalgrade.html?userid="+GetQueryString("userid");
			}
			window.onresize=function(){  
     		changeFrameHeight();
			}
	</script>
  </body>
</html>
