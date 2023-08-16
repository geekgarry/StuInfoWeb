<%@page import="com.maike.entity.AdminUser"%>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ page import="java.text.SimpleDateFormat"%>
<% request.setCharacterEncoding("utf-8"); %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'updateadminuser.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" type="text/css" href="css/updateadmin.css">
	<link rel="stylesheet" type="text/css" href="css/tablecss2.css">

  </head>
  <%
	AdminUser admin = (AdminUser)request.getAttribute("admin");
	String username=admin.getUsername();
	String id=admin.getId();
	String password=admin.getPassword();
	//String borndate2 = new SimpleDateFormat("yyyy-MM-dd").format(borndate);
  %>
  <body>
  	<div class="container">
  	<form action="UpdateAdminUser" name="updateadmin" enctype="application/x-www-form-urlencoded">
    <table class="dataintable">
    	<thead>
    	<tr><td colspan="2">修改管理员信息</td></tr>
    	</thead>
    	<tbody>
    	<tr><td>用户名：</td>
    	<td>
    	<input type="text" name="username" id="usr" size="40" value="<%=username%>">
    	<input type="hidden" name="id" value="<%=id%>" ><!-- style="visibility: hidden;" -->
    	</td></tr>
    	<tr><td>密码：</td><td><input type="password" name="password" id="pwd" size="40" value="<%=password%>"></td></tr>
    	</tbody>
    	<tfoot>
    	<tr><td colspan="2"><input type="button" value="修改" onclick="submitclick()" id="universalbtn">&nbsp;&nbsp;&nbsp;&nbsp;<input type="button" value="返回" id="universalbtn" onclick="backtovender()"></td></tr>
    	</tfoot>
    </table>
    </form>
    <script type="text/javascript">
	    var updateadmin=document.updateadmin;
	    var username=document.getElementById("usr").value;
	    var password=document.getElementById("pwd").value;
	    function submitclick(){
	    	if(username==""||password==""){
	    		alert("输入不能为空！");
	    	}else{
	    		updateadmin.submit();
	    	}
	    }
	    function backtovender(){
	    	window.location="listalladminuser.html";
	    }
    </script>
    </div>
  </body>
</html>
