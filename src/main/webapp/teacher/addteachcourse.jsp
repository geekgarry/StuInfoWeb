<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'addteachcourse.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	
	<style type="text/css">
    	#universalbtn{width:42px;height:34px;line-height:34px; color:#ffffff; background:#58b4eb; 
    					border: #ff8000 1px solid; border-radius:6px; }
    	table input{height:30px; line-height: 30px; border: #0080ff 1px solid; border-radius:5px;}
    	table select{height:30px; line-height: 30px; border: #0080ff 1px solid; border-radius:5px;}
    	table tr{height:33px; line-height: 33px;}
    </style>
    <link rel="stylesheet" type="text/css" media="screen" href="${pageContext.request.contextPath}/css/css-table.css" />
	<script src="${pageContext.request.contextPath}/js/jquery.js" type="text/javascript"></script>
	<script src="${pageContext.request.contextPath}/js/style-table.js" type="text/javascript"></script>

  </head>
  <%
  	String incollege=request.getParameter("incollege");
  	String teacherid=request.getParameter("teacherid");
   %>
  <body onload="loadhtml()">
  <div>
  <form action="" method="post" name="addcourse" id="addcourse">
    <table id="travel">
    <thead>
    <tr>
    <th scope="col" colspan="2">添加教学课程</th>
    </tr>
    </thead>
    <tbody>
    <tr>
    <td>课程</td>
    <td>
    	<input type="hidden" value="<%=teacherid %>" name="teacherid">
    	<select name="subjectnames" id="subjectnames">
    	</select>
    </td>
    </tr>
    <tr>
    <td>专业班级</td>
    <td>
    	<input type="text" id="incollege">
    	<select name="majors" id="majors">
    	</select>
    	<!-- <select name="classid">
    		<option value="1501">1501</option>
    		<option value="1502">1502</option>
    		<option value="1503">1503</option>
    		<option value="1504">1504</option>
    		<option value="1402">1402</option>
    	</select> -->
    </td>
    </tr>
    <tr>
    <td>开课学期</td>
    <td>
    	<select name="courseteam" id="courseteam">
    		<option value="大一上学期">大一上学期</option>
    		<option value="大一下学期">大一下学期</option>
    		<option value="大二上学期">大二上学期</option>
    		<option value="大二下学期">大二下学期</option>
    		<option value="大三上学期">大三上学期</option>
    		<option value="大三下学期">大三下学期</option>
    		<option value="大四上学期">大四上学期</option>
    		<option value="大四下学期">大四下学期</option>
    	</select>
    </td>
    </tr>
    </tbody>
    <tfoot>
    <tr>
    <td colspan="2"><input type="button" value="添加" onclick="addteachcourse()" id="universalbtn">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="reset" value="重置" id="universalbtn"></td>
    </tr>
    </tfoot>
    </table>
    </form>
    </div>
    <script type="text/javascript">
    		var myincollege='<%=incollege %>';
    		var teacherid='<%=teacherid %>';
    		//document.getElementById("subjectnames").style.display="block";
        	//selectoption.onclick=null;
			var selector = $("#subjectnames");//document.getElementById("subjectnames");
		    var htm='<option>请选择教授的课程</option>';
		    $.ajax({
		        type: 'GET',
		        url: '${pageContext.request.contextPath}/ListCourse',
		        contentType: "application/json",
		        dataType: 'json'
		    }).done(function (res) {
		        var courses = eval(res);
		        for(var i=0;i<courses.length;i++) {//var i in courses
		            htm += '<option value="'+courses[i].id+'">'+courses[i].subjectname+'</option>';
		            //alert(courses[i].id);
		        }
		        selector.html(htm);
		    }).fail(function () {
		        console.log('获取课程json数据失败');
		    });
    	function GetQueryString(name)
			{
			     var reg = new RegExp("(^|&)"+ name +"=([^&]*)(&|$)");
			     var r = window.location.search.substr(1).match(reg);
			     if(r!=null)
			     return  unescape(r[2]); 
			     return null;
			}
			function getQueryString(name) { 
			var result = window.location.search.match(new RegExp("[\?\&]" + name + "=([^\&]+)", "i")); 
				if (result == null || result.length < 1) { 
					return ""; 
				} 
					return result[1];
			}
			function loadhtml(){
				//var college=GetQueryString("incollege");
				//document.getElementById("#incollege").value=college;
				$("#incollege").val('<%=incollege %>')
				//alert()
				//alert(college);
				//获取Location对象的search属性值
				/* var searchStr = location.search;
				 
				//由于searchStr属性值包括“?”，所以除去该字符
				searchStr = searchStr.substr(1);
				 
				//将searchStr字符串分割成数组，数组中的每一个元素为一个参数和参数值
				var searchs = searchStr.split("&");
				 
				//获得第一个参数和值
				var address = searchs[0].split("=");
				alert(address); */
			}
			function addteachcourse(){
				var subjectid=$("#subjectnames").val;
				var major=$("#majors").val;
				var Date
				if(subjectid==""||major==""){
					alert("不能为空！");
				}else{
				var params=$("#addcourse").serialize();
				$.ajax({
		           dataType:"json",    //数据类型为json格式
		           contentType: "application/x-www-form-urlencoded; charset=utf-8",
		           type:"post",  
		           url:"${pageContext.request.contextPath}/AddTeachCourse",
		           async: true,
		           data:params,
		           statusCode: {404: function() {  
		                alert('data not found'); }  
		             },     
		           success:function(data,textStatus){ 
		        	    if(data="1"){
		        	    	alert("success");
		        	    }else{
		        	    	alert("fail");
		        	    }
		           }  
		       });
		       }
			}
			<%-- var slecthmtl='<option>---选择专业---</option>';
			var majors;
			var collegecode;
			var college;
			var selectermajor=$("#majors");
			$.ajax({
		           dataType:"json",    //数据类型为json格式
		           contentType: "application/x-www-form-urlencoded; charset=utf-8",
		           type:"GET",  
		           url:"json/college.json",
		           async: false,
		           data:{
		        	   	//type:"selectDeviceNoOnly",
		           		//deviceNo:$("#deviceNo").val()
		           },
		           statusCode: {404: function() {  
		                alert('data not found'); }  
		             },      
		           success:function(data,textStatus){ 
		        	    majors = data;
				        for(var i in majors['10000000']) {//var i in courses
				          if(majors['10000000'][i]=='<%=incollege %>')
				          	collegecode=i;
				          	college=majors['10000000'][collegecode];
				            //alert(courses[i].subjectname);
				        }
				        for(var j in majors[collegecode]){
				            slecthmtl += '<option value="'+majors[collegecode][j]+'">'+majors[collegecode][j]+'</option>';
				        }
				        selectermajor.html(slecthmtl);
		           }  
		       }); --%>
		    var majors;
			var selectermajor=$("#majors");
			var selecthtml='<option>请选择教授的班级</option>';
			$.ajax({
		           dataType:"json",    //数据类型为json格式
		           contentType: "application/x-www-form-urlencoded; charset=utf-8",
		           type:"GET",  
		           url:"${pageContext.request.contextPath}/ListStuClass",
		           async: true,
		           data:{
		        	   	//type:"selectDeviceNoOnly",
		           		incollege:myincollege
		           },
		           statusCode: {404: function() {  
		                alert('data not found'); }  
		             },      
		           success:function(data,textStatus){ 
		        	    majors = eval(data);
				        for(var i=0; i<majors.length;i++) {//var i in courses
				          selecthtml+='<option value="'+majors[i].stuclass+'">'+majors[i].stuclass+'</option>';
				        }
				        selectermajor.html(selecthtml);
		           }  
		       });
    </script>
  </body>
</html>
