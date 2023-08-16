<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'querycoursedetail.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.2.6.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/style-table.js"></script>
	<link rel="stylesheet" type="text/css" media="screen" href="${pageContext.request.contextPath}/css/listteachcoursebyclass.css" />
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  <%	String userid=(String)request.getParameter("userid");
   %>
  <body>
    <div><input type="text" name="stuclass" id="stuclass" style="display: none;"></div>
    <div class="main">
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
    <th><div class="STYLE1" align="center">课程性质</div></th>
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
			var studentclass;
    		var stuuserid='<%=userid%>';
    		//解决中文乱码问题的方法1,页面端发出的数据作一次encodeURI，服务端使用new String(old.getBytes("iso8859-1"),"UTF-8");  
		    //解决中文乱码问题的方法2,页面端发出的数据作两次encodeURI,服务端使用String name = URLDecoder.decode(old,"UTF-8");  
			//解决中文乱码问题的方法1,页面端发出的数据作一次encodeURI，服务端使用new String(old.getBytes("iso8859-1"),"UTF-8");  
			//解决中文乱码问题的方法2,页面端发出的数据作两次encodeURI,服务端使用String name = URLDecoder.decode(old,"UTF-8");  
			var url = "QueryStuClass";//+encodeURI(encodeURI($("#username").val()));  
			url = convertURL(url);
			$.getJSON(url,{userid:stuuserid},function(data){
				showData(data);//我们仅做数据展示
			});
			//给url地址增加时间蒫，瞒过浏览器，不读取缓存  
			function convertURL(url){  
				    //获取时间戳  
				    var timstamp = (new Date()).valueOf();  
				    //将时间戳信息拼接到url上  
				    if(url.indexOf("?") >=0){  
				        url = url + "&t=" + timstamp;  
				    }else{  
				        url = url + "?t=" + timstamp;  
				    }  
				    return url;  
			}
			function showData(data){
				var stuuser=eval(data);
				//$.each(data,function(){
				studentclass=data.stuclass;
				document.getElementById("stuclass").value=data.stuclass;
				getByPageBean(1);
				//});
				/* for(var i=0;i<stuuser.length;i++){
					document.getElementById("stuclass").value=stuuser[i].stuclass;
				} */
			}
	function getByPageBean(pageNo){ 
		//设置表单的 页号参数 
		var pageno=pageNo; 
		/*表单数据 序列化-->表单中有个隐藏域 储存的是 页号
       	 	 * 每次异步刷新的是表格信息
        	 *表单中的数据不会变动
           	 *根据方法的  pageNo 参数的变动
           	*查询不同页号的 信息
         */ 
         var stuclass= $("#stuclass").val;
         //ajax请求 中间的data表示为{ pageNo: 1 }
         $.getJSON("ListTeacherCourseByClass",{pageNo:pageno,stuclass:studentclass},function(data){ 
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
				var createtime=this.create_time;
				var yearmonth=createtime.substr(0, 10);
				var tr="<tbody><tr height='32'>"+ "<td><span class='STYLE1'>"+this.courseteam+"</span></td><td><span class='STYLE1'> "+
				this.classofteach+ "</span></td><td><span class='STYLE1'> "+this.realname+" </span></td><td><span class='STYLE1'> "+this.incollege+" </span></td>"+ 
				"<td><span class='STYLE1'> "+this.subjectname+" </span></td>"+ "<td><span class='STYLE1'> "+this.credit/* (this.gender==1?"男":"女") */ +
				"学分</span></td><td><span class='STYLE1'> "+this.subjectproperty+"</span></td><td><span class='STYLE1'>"+this.coursetime+
				"课时</span></td><td><span class='STYLE1'>"+yearmonth+"</span></td><td><span class='STYLE1'> "+(this.coursestatus==1?"已结课":"进行中")+"</span></td>"+ 
				"<td><span class='STYLE1'>"+/* "<a class='updatebtn'>修改</a>"+ */"<a class='universalbtn' onclick='return firm();' href='QueryTeachCourseByClass?id="+this.id+"'>详情</a>"+"</span></td>"+ "</tr></tbody>"; 
				//把每次遍历的一行数据 添加到 表格中 
				$("#selectTable").append(tr); 
				}); 
			});
		}
    </script>
  </body>
</html>
