<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'listTeachCourseDetail.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" type="text/css" media="screen" href="${pageContext.request.contextPath}/css/teachcoursedetail.css" />
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/tablecss1.css">
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/style-table.js"></script>
	<style>
        .bd-filter__select{
            width: 200px;
        }
        .btntrtd{text-align: center;}
        table input.input_content{height:32px; line-height: 32px; border: #0080ff 1px solid; border-radius:5px;}
        table select{height:30px; line-height: 30px; border: #0080ff 1px solid; border-radius:5px;}
        table tr{height:33px; line-height: 33px;}
        .universalbtn{border-radius: 10px; border-color: #556677;border-size: 2px; 
			background: #ededed;color: #010101; width:100px;height:40px; line-height: 40px;}
    </style>
  </head>
  <%
	//TeacherCourse course = (TeacherCourse)request.getAttribute("teachcourse");
	String id=request.getAttribute("id").toString().trim();
	//String id=(String)request.getParameter("id");
 %>
  <body>
  	<table id="travel">
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
    </tr>
    </thead>
    </table>
    <div class="dividebar">下面是每节课的详细内容与资源下载</div>
    <div class="coursedetail">
    	<table class="list" id="selectTable">
		    <thead>
		    <tr>
		    <th><div class="STYLE1" align="center">课程名</div></th>
		    <th><div class="STYLE1" align="center">班级</div></th>
		    <th><div class="STYLE1" align="center">所在学院/系</div></th>
		    <th><div class="STYLE1" align="center">教师名</div></th>
		    <th><div class="STYLE1" align="center">课程内容</div></th>
		    <!-- <th><div class="STYLE1" align="center">课程性质</div></th> -->
		    <th><div class="STYLE1" align="center">课程资源</div></th>
		    <th><div class="STYLE1" align="center">时间</div></th>
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
    	<a class="bar" href="">首页</a> 
    	<a class="bar" href="">上一页</a> 
    	<a class="bar" href="">下一页</a> 
    	<a class="bar" href="">末页</a>
    	第<span id="pageNo"></span>页/<span id="totalPage"></span>页,跳转到<input type="text" value="1" id="topagecount" style="min-width: 10px;border:1px #454545 solid;" size="2">页
    	<input type="button" value="跳转" onclick="getByPageBean(document.getElementById('topagecount').value)">
    	总条数:<span id="totalCount"></span>
       </div>
    </div>
    <script type="text/javascript">
    	var teacherids;
    	var subjectids;
    	var tcid='<%=id%>';
        // 创建
		var form_data = new FormData();
		// 获取文件
		var file_data = $("#courseresource").prop("files");
		// 把所以表单信息
		form_data.append("teacherid", teacherids);
		form_data.append("subjectid", subjectids);
		form_data.append("courseresource", file_data);
		form_data.append("teachcourseid", tcid);
		form_data.append("coursecontent", $("#coursecontent").val);
		<%-- {
		  		coursecontent:$("#coursecontent").val,
		  		courseresource:$("#courseresource").val,
		  		teacherid:teacherids,
		  		subjectid:subjectids,
		  		teachcourseid:'<%=id%>'
			} --%>
	  	function addcoursedetail(){
	  		if($("#coursecontent").val==""||$("#courseresource").val==""){
	  			$("#errormsg").html("不能为空！");
	  		}
	  		$ajax({
		  	url:"UploadCourseDetail",
		  	type:"post",
		  	dataType: "text",//json
		  	crossDomain: true,
		  	data:form_data,
		  	cache: false,
		  	async: false,	//同步
		  	processData: false,  // 注意：让jQuery不要处理数据
    		contentType: false,  // 注意：让jQuery不要设置contentType
		  	success: function(data){
		  	//这个方法里是ajax发送请求成功之后执行的代码
		  	//showData(data);//我们仅做数据展示
		  			$("#errormsg").html(data);
		  	},
		  	error: function(msg){
		  	alert("ajax连接异常："+msg);
		  	}
		  	});
        }
        function check(thisform){
	       with(thisform){
        	if($("#coursecontent").val==""||$("#courseresource").val==""){
	  			$("#errormsg").html("不能为空！");
	  			return false;
	  		}else{
	  			return true;
	  		}
	  	   }
        }
        /* $.ajax({
                method: "post",
                url: "QBaiduSend",
                data: {
                	id:id,
                	name:$('#pushPerson'+id+'').val()
                },
                success: function (array2) {
                	alert('推送成功!');
                }
        }); */
		    //解决中文乱码问题的方法1,页面端发出的数据作一次encodeURI，服务端使用new String(old.getBytes("iso8859-1"),"UTF-8");  
		    //解决中文乱码问题的方法2,页面端发出的数据作两次encodeURI,服务端使用String name = URLDecoder.decode(old,"UTF-8");  
			//解决中文乱码问题的方法1,页面端发出的数据作一次encodeURI，服务端使用new String(old.getBytes("iso8859-1"),"UTF-8");  
			//解决中文乱码问题的方法2,页面端发出的数据作两次encodeURI,服务端使用String name = URLDecoder.decode(old,"UTF-8");  
			var url = "QueryTeachCourseServlet";//+encodeURI(encodeURI($("#username").val()));  
			url = convertURL(url);
			$.getJSON(url,
				{
					id:tcid
				},
			function(data){
				$("#travel tr").not(":first").remove();
				showData(data);//我们仅做数据展示
			});
			/* $ajax({
		  	url:url,
		  	type:"get",
		  	dataType: "json",
		  	data:{
					id:tcid
				},
		  	success: function(data){
		  	$("#travel tr").not(":first").remove();
		  	//这个方法里是ajax发送请求成功之后执行的代码
		  		showData(data);//我们仅做数据展示
		  	},
		  	error: function(msg){
		  		alert("连接异常："+msg);
		  	}
		  	}); */
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
	  	function showData(datalist) {
	  	  var obj = eval(datalist);
		  //var latlngarray=new Array();
		  //var MAX = obj.length;
          $.each(datalist,function(){
             	var coursestatus=this.coursestatus;
             	teacherids=this.teacherid;
             	subjectids=this.subjectid;
				var tr="<tbody><tr height='32'>"+ "<td><span class='STYLE1'>"+this.courseteam+"</span></td>"+ 
				"<td><span class='STYLE1'> " +this.classofteach+ "</span></td><td><span class='STYLE1'> "+this.realname+" </span></td><td><span class='STYLE1'> "+this.incollege+" </span></td>"+ 
				"<td><span class='STYLE1'> "+this.subjectname+" </span></td>"+ "<td><span class='STYLE1'> "+this.credit/* (this.gender==1?"男":"女") */ +"学分</span></td><td><span class='STYLE1'> "+this.subjectproperty+"</span></td>"+
				"<td><span class='STYLE1'>"+this.coursetime+"课时</span></td><td><span class='STYLE1'>"+this.create_time+"</span></td><td><span class='STYLE1'> "+(this.coursestatus==1?"已结课":"进行中")+"</span></td>"+ 
				"</tr></tbody>";
				/* if(coursestatus=="0"){
					document.getElementsByClassName("STYLE1").style.color = "red"; // 直接设置样式属性
				} */
				$("#teacherid").val(teacherids);
	  			$("#subjectid").val(subjectids);
				//把每次遍历的一行数据 添加到 表格中  return firmfinish(); href='UpdateCourseStatus?id="+this.id+"&status=1'//<div class='toolbar-circle' style='display: block;'></div>
				$("#travel").append(tr);
			});
	  	}
	  	$(function(){ 
		getByPageBean(1);
		});
	  	function getByPageBean(PageNo){
	  		var BasePath='<%=basePath%>';
	  		var page=PageNo;
		  	$.ajax({
				url:"ListCourseDetailServlet",
				type:"post",
				dataType:'JSON',
				data:{
					teachercourseid:tcid,
					pageNo:page
				},
				async:false,
				success:function(data){
						//删除表格之前数据(标题栏除外)--->防止出现几页的数据叠加的情况 
		        $("#selectTable tr").not(":first").remove(); 
		        //设置上一页 下一页连接 
		        $(".bar:eq(0)").attr("href","javascript:getByPageBean(1)"); 
		        $(".bar:eq(1)").attr("href","javascript:getByPageBean("+data.previous+")"); 
		        $(".bar:eq(2)").attr("href","javascript:getByPageBean("+data.next+")"); 
		        $(".bar:eq(3)").attr("href","javascript:getByPageBean("+data.totalpagesize+")"); 
		        //设置显示信息(当前页号,总页号,总条数) 
		        $("#pageNo").html(data.currentpage);
		        $("#totalPage").html(data.totalpagesize);
		        $("#totalCount").html(data.totalrows);
		        //$("#topagecount").val(data.currentpage);
		        //在表格添加数据 使用each方法遍历 控制器传回的数据中的list集合
				$.each(data.list,function(){
					var tr="<tbody><tr height='32'>"+ "<td><span class='STYLE1'>"+this.subjectname+"</span></td><td><span class='STYLE1'> " +this.classofteach+ "</span></td><td><span class='STYLE1'> "+
					this.incollege+" </span></td><td><span class='STYLE1'> "+this.realname+" </span></td><td><span class='STYLE1'> "+(this.coursecontent.length>10?this.coursecontent.substr(0, 9)+"。。。":this.coursecontent)+" </span></td>"+ "<td>"+
					"<a href='"+BasePath+this.courseresource+"'>下载文件</a></span></td><td><span class='STYLE1'>"+this.create_time+"</span></td>"+"</tr></tbody>"; 
					/* if(coursestatus=="0"){
						document.getElementsByClassName("STYLE1").style.color = "red"; // 直接设置样式属性
					} */
					//把每次遍历的一行数据 添加到 表格中  return firmfinish(); href='UpdateCourseStatus?id="+this.id+"&status=1'//<div class='toolbar-circle' style='display: block;'></div>
					$("#selectTable").append(tr);
					});
				}
			});
		}
    </script>
  </body>
</html>
