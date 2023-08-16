<%@page import="com.maike.entity.AdminOnline"%>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'allonlineadminuser.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<style type="text/css">
	*{padding: 0;margin: 0;}
	.main_div{width:99%;margin: 0 auto;}
	.container{width:50%;margin: 0 auto; text-align: center;}
	</style>

  </head>
  
  <body>
    <div class="main_div">
    	<div class="container">
    	<%
    		 	ArrayList<AdminOnline> userlist = (ArrayList<AdminOnline>) request.getServletContext().getAttribute("userlist"); 
  				if (userlist != null) { 
  				for (int i = 0; i < userlist.size(); i++) { 
  				AdminOnline user = userlist.get(i);
  			%>
  			sessionID:<%=user.getSessionID()%>
    		<br>
    		IP:<%=user.getIp()%>
    		<br>
   			FirstName:<%=user.getFirstTime()%>
    		<hr>
  			<%}}%>
    	</div>
    </div>
  </body>
</html>
