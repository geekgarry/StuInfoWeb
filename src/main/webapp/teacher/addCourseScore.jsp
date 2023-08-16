<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'addCourseScore.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" type="text/css" media="screen" href="${pageContext.request.contextPath}/css/css-table.css" />
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.2.6.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/style-table.js"></script>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/addCourseScore.css">
	<style type="text/css">
	.toolbar-circle{display: block;background-color: #EB3F1C;top: 1px;left: 39px;position: relative;}
	.nofinish{color:#EB3F1C;}
	</style>
  </head>
  <%String incollege=request.getParameter("incollege");
  	String teacherid=request.getParameter("teacherid");
  	String realname=request.getParameter("realname");
  %>
  <body>
  <div class="menu"> 
    <table> 
    <tr><td> 
    <form method="post" id="selectName">
    <input name="teacherid"  type="text" value="<%=teacherid%>" style="display: none;"> 
    <input name="pageNo"  type="text" value="1" style="display: none;"> 
    	<select name="typeStr">
    		<option value="1">全部查询</option>
    		<option value="2">学院查询</option>
    		<option value="3">课程名查询</option>
    		<option value="4">班级查询</option>
    		<option value="5">教师姓名查询</option>
    	</select>
    	<input name="keyStr" class="input-text" type="text"> &nbsp;&nbsp;&nbsp;&nbsp; 
    	<input value="查询课程" type="button" id="queryBut"> 
    </form> 
    </td></tr> 
    </table> 
    </div> 
    <div class="main"> 
    <div class="optitle clearfix">
    <span class="title">教授课程管理&gt;&gt;</span>
    <em>
    <input value="添加新课" class="input-button" onclick="window.location='teacher/addteachcourse.jsp?incollege=<%=incollege %>&teacherid=<%=teacherid %>'" type="button"> 
    </em>
    </div> 
    <div class="content">
    <table class="list" id="selectTable">
    <thead>
    <tr>
    <th><div class="STYLE1" align="center">开课学期</div></th>
    <th><div class="STYLE1" align="center">授课班级</div></th>
    <th><div class="STYLE1" align="center">任课老师</div></th>
    <th><div class="STYLE1" align="center">所在学院/系</div></th>
    <th><div class="STYLE1" align="center">课程名称</div></th>
    <th><div class="STYLE1" align="center">课程学分</div></th>
    <!-- <th><div class="STYLE1" align="center">课程性质</div></th> -->
    <th><div class="STYLE1" align="center">课时数</div></th>
    <th><div class="STYLE1" align="center">时间</div></th>
    <th><div class="STYLE1" align="center">课程状态</div></th>
    <th><div class="STYLE1" align="center">操作</div></th>
    </tr>
    </thead>
    <!-- <tbody>
    </tbody> -->
    <!-- <tfoot>
    <tr>
    <td></td>
    <td></td>
    <td></td>
    <td></td>
    <td></td>
    <td></td>
    <td></td>
    </tr>
    </tfoot> -->
    </table>
       <div class="displaybar">
    	<a href="">首页</a> 
    	<a href="">上一页</a> 
    	<a href="">下一页</a> 
    	<a href="">末页</a>
    	第<span id="pageNo"></span>页/<span id="totalPage"></span>页,跳转到<input type="text" value="1" id="topagecount" style="min-width: 10px;border:1px #454545 solid;" size="2">页
    	<input type="button" value="跳转" onclick="getByPageBean(document.getElementById('topagecount').value)">
    	总条数:<span id="totalCount"></span>
       </div>
    </div>
	</div>
	<script type="text/javascript">
	$(function(){ 
		getByPageBean(1); 
		$("#queryBut").click(function(){ 
		getByPageBean(1); 
		});
	});
	function getByPageBean(pageNo){ 
		//设置表单的 页号参数 
		$("[name=pageNo]").val(pageNo); 
		/*表单数据 序列化-->表单中有个隐藏域 储存的是 页号
       	 	 * 每次异步刷新的是表格信息
        	 *表单中的数据不会变动
           	 *根据方法的  pageNo 参数的变动
           	*查询不同页号的 信息
         */ 
         var params= $("#selectName").serialize();
         //ajax请求 中间的data表示为{ pageNo: 1 }
         $.getJSON("ListTeacherCourse",params,function(data){
         	 //删除表格之前数据(标题栏除外)--->防止出现几页的数据叠加的情况 
	         $("#selectTable tr").not(":first").remove(); 
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
			//var coursestatus=this.coursestatus;
			//var status="";
			/* if(coursestatus=="1"){
				status="已结课";
			}else{
				status="进行中";
			} */
			var tr="<tbody><tr height='32'>"+ "<td><span class='STYLE1'>"+this.courseteam+"</span></td>"+ 
			"<td><span class='STYLE1'> " +this.classofteach+ "</span></td><td><span class='STYLE1'> "+this.realname+" </span></td><td><span class='STYLE1'> "+this.incollege+" </span></td>"+ 
			"<td><span class='STYLE1'> "+this.subjectname+" </span></td>"+ "<td><span class='STYLE1'> "+this.credit/* (this.gender==1?"男":"女") */ +"学分</span></td>"+//<td><span class='STYLE1'> "+this.subjectproperty+"</span></td>
			"<td><span class='STYLE1'>"+this.coursetime+"课时</span></td><td><span class='STYLE1'>"+this.create_time+"</span></td><td><span class='STYLE1'> "+(this.coursestatus==1?"已结课":"进行中")+"</span></td>"+ 
			"<td><span class='STYLE1'> "+"<a class='updatebtn' onclick='updatestatus("+this.id+")'>结课</a>"+"<a class='universalbtn' href='QueryTeachCourse?id="+this.id+"'>详情</a></span></td>"+ "</tr></tbody>"; 
			/* if(coursestatus=="0"){
				document.getElementsByClassName("STYLE1").style.color = "red"; // 直接设置样式属性
			} */
			//把每次遍历的一行数据 添加到 表格中  return firmfinish(); href='UpdateCourseStatus?id="+this.id+"&status=1'//<div class='toolbar-circle' style='display: block;'></div>
			$("#selectTable").append(tr);
			}); 
		});
		}
		//弹出一个询问框，有确定和取消按钮
    	function firm() {
	        //利用对话框返回的值 （true 或者 false）
	        if (confirm("详情是查看每节课程的详细内容！点击确定查看详情！")) {
	            //alert("点击了确定");
	            return true;
	        }else {
	            //alert("点击了取消");
	            return false;
	        }
 
    	}
    	function firmfinish(){
    		//利用对话框返回的值 （true 或者 false）
	        if (confirm("你确定全部课程已经结束了吗？点击确定结课！")) {
	            //alert("点击了确定");
	            return true;
	        }else {
	            //alert("点击了取消");
	            return false;
	        }
    	}
    	function updatestatus(tcid){
    		$.ajax({
	           dataType:"text",    //数据类型为text格式json
	           contentType: "application/x-www-form-urlencoded; charset=utf-8", 
	           type:"GET",  
	           url:"UpdateCourseStatus",
	           async: false,
	           data:{
	        	   	id:tcid,
	           		status:1
	           },
	           statusCode: {404: function() {  
	                alert('page not found'); }  
	             },      
	           success:function(data,textStatus){ 
	        	   if(data=="1"){
	        	   		alert("确认结课成功！");
	        	   }else{
	        	   		alert("结课失败！");
	        	   }
	           }  
	       	});
    	}
	</script>
  </body>
</html>
