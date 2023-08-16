<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'addstucoursescore.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" type="text/css" media="screen" href="${pageContext.request.contextPath}/css/addstudentscore.css" />
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/tablecss1.css">
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.2.6.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/style-table.js"></script>
	
  </head>
  <%
  	//String stuclass=(String)request.getParameter("stuclass");
  	String stuclass=(String)request.getAttribute("stuclass");
  	String subjectid=(String)request.getAttribute("subjectid");
  	String courseteam=(String)request.getAttribute("courseteam");
   %>
  <body onload="init()">
  	<div class="main_container">
    <table id="travel">
    <thead>
    <tr class="titlerow">
  	<th>学号</th><th>学院</th><th>班级</th><th>姓名</th><th>操作</th>
  	</tr>
    </thead>
    </table>
    </div>
    <div class="displaybar">
    	<a href="">首页</a> 
    	<a href="">上一页</a> 
    	<a href="">下一页</a> 
    	<a href="">末页</a>
    	第<span id="pageNo"></span>页/<span id="totalPage"></span>页,跳转到<input type="text" value="1" id="topagecount" style="min-width: 10px;border:1px #454545 solid;" size="2">页
    	<input type="button" value="跳转" onclick="getByPageBean(document.getElementById('topagecount').value)">
    	总条数:<span id="totalCount"></span>
    </div>
    <div id="light" class="white_content" align="center">
    		<a href="javascript:void(0)" onclick = "closeDialog()" 
            style="display: block;width:50px;height:30px;line-height: 30px;float: right;">关闭</a>
            <form method="post" name="addcoursedetail" id="addcoursedetail"><!-- enctype="multipart/form-data" -->
			 <table border="1" class="bordered" id="bordered">
			    <tr>
			    	<th colspan="2" colspan="2">课程成绩添加</th>
			    </tr>
			    <tr>
			    	<td>课程内容</td>
			    	<td>
			    	<input type="text" name="stuid" id="stuid" style="display: none;">
			    	<input type="text" name="subjectid" id="subjectid" style="display: none;">
			    	<select name="examtype">
			    		<option value="首修">首修</option>
			    		<option value="补考">补考</option>
			    		<option value="重修">重修</option>
			    	</select>
			    	</td>
			    </tr>
			    <tr>
			    	<td>课程考试分数</td>
			    	<td><input type="text" name="coursescore" id="coursescore" ></td>
			    </tr>
			    <tr>
			    	<td>课程考试分数</td>
			    	<td>
			    	<select name="subjecttime" id="subjecttime">
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
			    <tr>
			    	<td colspan="2">
			    		<div id="errormsg"></div>
			    		<input type="button" value="添加" class="universalbtn" onclick="addcoursescore()">
			    		<!-- onclick="addcoursedetail()" -->
			    		&nbsp;&nbsp;&nbsp;&nbsp;
			    		<input type="reset" value="重置" class="universalbtn">
			    	</td>
			    </tr>
			 </table>
			</form>
    </div> 
    <div id="fade" class="black_overlay"></div>
    <script type="text/javascript">
    	function init(){
    			var s='<%=courseteam%>';
			 	var courseteams = $("#subjecttime").options;
			 	//var opt=document.getElementsByClassName("status");
			 	for(var i = 0; i < courseteams.length; i++){
			 		if(courseteams[i].value == s){
			 			courseteams[i].selected=true;
			 			return ;
			 		}
			 	}
    	}
    	$(function(){ 
			getByPageBean(1);
		});
		function getByPageBean(pageno){ 
		//设置表单的 页号参数 
		var PageNo=pageno;
		/*表单数据 序列化-->表单中有个隐藏域 储存的是 页号
       	 	 * 每次异步刷新的是表格信息
        	 *表单中的数据不会变动
           	 *根据方法的  pageNo 参数的变动
           	*查询不同页号的 信息
         */ 
         //ajax请求 中间的data表示为{ pageNo: 1 }
         $.getJSON("QueryStuByClass",
         		{
         			stuclass:'<%=stuclass%>',
         			pageNo:PageNo
         		},
         	 function(data){ 
         	 //删除表格之前数据(标题栏除外)--->防止出现几页的数据叠加的情况 
	         $("#travel tr").not(":first").remove(); 
	         //设置上一页 下一页连接 
	         $("a:eq(0)").attr("href","javascript:getByPageBean(1)"); 
	         $("a:eq(1)").attr("href","javascript:getByPageBean("+data.previous+")"); 
	         $("a:eq(2)").attr("href","javascript:getByPageBean("+data.next+")"); 
	         $("a:eq(3)").attr("href","javascript:getByPageBean("+data.totalpagesize+")"); 
	         //设置显示信息(当前页号,总页号,总条数) 
	         $("#pageNo").html(data.currentpage);
	         $("#totalPage").html(data.totalpagesize);
	         $("#totalCount").html(data.totalrows);
	         //$("#topagecount").val(data.currentpage);
	         //在表格添加数据 使用each方法遍历 控制器传回的数据中的list集合
		$.each(data.list,function(){
			var tr="<tbody><tr height='32'>"+ "<td><span class='STYLE1'>"+this.id+"</span></td>"+ 
			"<td><span class='STYLE1'> " +this.incollege+ "</span></td><td><span class='STYLE1'> "+this.stuclass+
			"</span></td>"+ "<td><span class='STYLE1'> "+this.realname+"</span></td><td><span class='STYLE1'> "+
			"<button class='universalbtn' onclick='openDialog("+this.id+")'>添加成绩</button></span></td></tr></tbody>"; 
			//把每次遍历的一行数据 添加到 表格中 
			$("#travel").append(tr); 
			}); 
		});
		}
		function openDialog(stuid){
            document.getElementById('light').style.display='block';
            document.getElementById('fade').style.display='block'
            $("#stuid").val(stuid);
            $("#subjectid").val('<%=subjectid%>');
            //document.getElementById('bordered').style.display='block';
        }
        function closeDialog(){
            document.getElementById('light').style.display='none';
            document.getElementById('fade').style.display='none'
        }
        function addcoursescore(){
        		var param=$("#addcoursedetail").serialize();
        		//创建XMLHttpRequest对象
		        var xmlHttp = new XMLHttpRequest();
		        //获取值
		        var coursescore = document.getElementById("coursescore").value;
		        if(coursescore==""||coursescore==null){
				document.getElementById("errormsg").innerHTML="成绩不能为空！请重新输入！";
				}else{
				document.getElementById("errormsg").innerHTML="";
		        //如果是false
		        /* xmlhttp.open("GET", "IsExistAdminUserUserid?userid="+username,false);
				xmlhttp.send();
				document.getElementById("resultmsg").innerHTML=xmlhttp.responseText; */
		        //设置回调函数
		        xmlHttp.onreadystatechange = function () {
		            if (xmlHttp.readyState == 4 && xmlHttp.status == 200) {
		            	if(xmlHttp.responseText=="yes"){
		            		document.getElementById("errormsg").innerHTML ="上传成功！";
		            	}else if(xmlHttp.responseText=="no"){
		            		//document.getElementById("usernameerrormsg").innerHTML="密码错误！";
		            		document.getElementById("errormsg").innerHTML = "上传失败！";//xmlHttp.responseText;
		            	}else{
		       				$("#addcoursedetail").submit();
		            	}
		            }
		        }
		        //配置XMLHttpRequest对象
		        xmlHttp.open("post", "AddStudentScore",true);
		        xmlHttp.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
		        //发送请求
		        xmlHttp.send(param);
		        }
        }
    </script>
   </body>
</html>
