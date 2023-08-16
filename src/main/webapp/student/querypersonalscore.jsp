<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'querypersonalscore.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.js"></script>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/querypersonalscore.css">
	
  </head>
  <%	String userid=(String)request.getParameter("userid");
   %>
  <body>
    <div class="search_div">
    <form action="" name="querybox" id="querybox">
    <input name="pageNo"  type="text" value="1" style="display: none;">
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
    <input type="text" name="stuid" id="stuid" value="<%=userid %>" style="display: none;">
    <input type="button" value="查询" id="findbtn" class="universalbtn" onclick="getByPageBean(1)">
    </form>
    </div>
    <div class="main_content">
    <table id="selectTable">
        <thead>
            <tr>
                <th>
                    <input type="checkbox" id="j_cbAll" />
                </th>
                <th>课程名</th>
                <th>学分</th>
                <th>课程类型</th>
                <th>课程性质</th>
                <th>考试类型</th>
                <th>考试学期</th>
                <th>考试分数</th>
                <th>添加时间</th>
            </tr>
        </thead>
        <tbody id="j_tb">
        </tbody>
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
    		/* function GetQueryString(name)
			{
			     var reg = new RegExp("(^|&)"+ name +"=([^&]*)(&|$)");
			     var r = window.location.search.substr(1).match(reg);
			     if(r!=null)
			     return  unescape(r[2]); 
			     return null;
			} */
			//var stuid=GetQueryString("userid");
			//document.getElementById("stuid").value=(stuid);
			//$("#stuid").val(stuid);
			//alert(document.getElementById("courseteam").value);
			// 调用方法
			//alert(GetQueryString("userid"));
			//var uname = GetQueryString("参数名");
		 $(function(){ 
			getByPageBean(1);
			$("#courseteam").change(function() {
				getByPageBean(1);
			});
		 });
	  	function getByPageBean(PageNo){
	  		//var page=PageNo;
	  		//设置表单的 页号参数 
			$("[name=pageNo]").val(PageNo); 
			/*表单数据 序列化-->表单中有个隐藏域 储存的是 页号
       	 	 * 每次异步刷新的是表格信息
        	 *表单中的数据不会变动
           	 *根据方法的  pageNo 参数的变动
           	*查询不同页号的 信息
         	*/ 
         	var params= $("#querybox").serialize();
	  		/* //解决中文乱码问题的方法1,页面端发出的数据作一次encodeURI，服务端使用new String(old.getBytes("iso8859-1"),"UTF-8");  
		    //解决中文乱码问题的方法2,页面端发出的数据作两次encodeURI,服务端使用String name = URLDecoder.decode(old,"UTF-8");  
			//解决中文乱码问题的方法1,页面端发出的数据作一次encodeURI，服务端使用new String(old.getBytes("iso8859-1"),"UTF-8");  
			//解决中文乱码问题的方法2,页面端发出的数据作两次encodeURI,服务端使用String name = URLDecoder.decode(old,"UTF-8");  
			var url = "QueryPersonalScore";//+encodeURI(encodeURI($("#username").val()));  
			url = convertURL(url);
			$.getJSON(url,params,function(data){
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
				showData(data.list);//我们仅做数据展示
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
		  	function showData(data) {
		  		//在表格添加数据 使用each方法遍历 控制器传回的数据中的list集合
				$.each(data.list,function(){
					var tr="<tr height='32'>"+ "<td><input type='checkbox' /></td><td><span class='STYLE1'>"+this.subjectname+"</span></td><td><span class='STYLE1'> " +this.credit+ "</span></td><td><span class='STYLE1'> "+
					this.subjectype+" </span></td><td><span class='STYLE1'> "+this.subjectproperty+" </span></td><td><span class='STYLE1'> "+this.examtype+" </span></td>"+ "<td><span class='STYLE1'>"+
					+this.examteam+"</span></td><td><span class='STYLE1'>"+this.subjectscore+"</span></td><td><span class='STYLE1'>"+this.create_time+"</span></td><td><span class='STYLE1'>等会吧</span></td></tr>"; 
					//把每次遍历的一行数据 添加到 表格中  return firmfinish(); href='UpdateCourseStatus?id="+this.id+"&status=1'//<div class='toolbar-circle' style='display: block;'></div>
					$("#selectTable tbody").append(tr);
				});
		  	} */
		  	$.ajax({
				url:"QueryPersonalScore",
				type:"post",
				dataType:'JSON',
				data:params,
				async:true,
				success:function(data){
						//删除表格之前数据(标题栏除外)--->防止出现几页的数据叠加的情况 
		        $("#selectTable tr").not(":first").remove(); 
		        //设置上一页 下一页连接 
		        $("a.bar:eq(0)").attr("href","javascript:getByPageBean(1)");
		        $("a.bar:eq(1)").attr("href","javascript:getByPageBean("+data.previous+")"); 
		        $("a.bar:eq(2)").attr("href","javascript:getByPageBean("+data.next+")"); 
		        $("a.bar:eq(3)").attr("href","javascript:getByPageBean("+data.totalpagesize+")");
		        //设置显示信息(当前页号,总页号,总条数) 
		        $("#pageNo").html(data.currentpage);
		        $("#totalPage").html(data.totalpagesize);
		        $("#totalCount").html(data.totalrows);
		        //$("#topagecount").val(data.currentpage);
		        //在表格添加数据 使用each方法遍历 控制器传回的数据中的list集合
				$.each(data.list,function(){
					var tr="<tr height='32'>"+ "<td><input type='checkbox' /></td><td><span class='STYLE1'>"+this.subjectname+"</span></td><td><span class='STYLE1'> " +this.credit+ "</span></td><td><span class='STYLE1'> "+
					this.subjectype+" </span></td><td><span class='STYLE1'> "+this.subjectproperty+" </span></td><td><span class='STYLE1'> "+this.examtype+" </span></td><td><span class='STYLE1'>"+
					+this.examteam+"</span></td><td><span class='STYLE1'>"+this.subjectscore+"</span></td><td><span class='STYLE1'>"+this.create_time+"</span></td></tr>"; 
					//把每次遍历的一行数据 添加到 表格中  return firmfinish(); href='UpdateCourseStatus?id="+this.id+"&status=1'//<div class='toolbar-circle' style='display: block;'></div>
					$("#selectTable tbody").append(tr);
					});
				}
			});
		}
		var all = document.getElementById("j_cbAll");
		 var tbody = document.getElementById("j_tb");
		 var checkboxs = tbody.getElementsByTagName("input");
		 all.onclick = function() {
		     for (var i = 0; i < checkboxs.length; i++) {
		         var checkbox = checkboxs[i];
		         checkbox.checked = this.checked;
		     }
		 };
		 for (var i = 0; i < checkboxs.length; i++) {
		     checkboxs[i].onclick = function() {
		         var isCheckedAll = true;
		         for (var i = 0; i < checkboxs.length; i++) {
		             if (!checkboxs[i].checked) {
		                 isCheckedAll = false;
		                 break;
		             }
		         }
		         all.checked = isCheckedAll;
		     };
		 }
	 </script>
  </body>
</html>
