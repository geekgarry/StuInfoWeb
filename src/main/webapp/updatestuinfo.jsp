<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ page import="com.maike.entity.StuUser"%>
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
    
    <title>My JSP 'updatestuinfo.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" type="text/css" href="css/tablecss3.css">
	<style type="text/css">
	.updatebtn{text-decoration: none;text-align: center;color: #020202; background: #e5e5e5;
	border: 1px #010101 solid; border-radius: 6px; padding-left: 8px; padding-right: 8px; 
	height:30px; line-height: 30px; margin-left: 3px;}
	</style>
  </head>
  <%
	StuUser stu = (StuUser)request.getAttribute("stuinfo");
	String borndate=stu.getBorndate();
	//String borndate2 = new SimpleDateFormat("yyyy-MM-dd").format(borndate);
  %>
   <body onload="init();">
	 <form action="UpdateStuInfo" name="inputForm" method="post">
	 	<%-- <input type="hidden" name="orderId" value="<%=stu.getId() %>"> --%>
		<table width="100%"  align="center" border="1" bordercolor="#234567" cellspacing="0" cellpadding="0" class="bordered">
	    	<thead>
	    		<tr height="50px;">
	    			<th style="padding-top: 10px;" colspan="2">
	    				<h2>修改</h2>
	    			</th>
	    		</tr>
	    	</thead>
	    	<tbody>
	    		<tr>
	    			<td align="right">
						学号：
					</td>
	    			<td class="tdright">
						<input type="text" name="stuid" value="<%=stu.getId() %>" readonly="readonly">
					</td>
	    		</tr>
	    		<tr>
	    			<td align="right">
						姓名：
					</td>
	    			<td class="tdright">
						<input type="text" name="realname" value="<%=stu.getRealname() %>">
					</td>
	    		</tr>
	    		<tr>
	    			<td align="right">
						年级：
					</td>
	    			<td class="tdright">
						<input type="text" name="stugrade" value="<%=stu.getStugrade() %>">
					</td>
	    		</tr>
	    		<tr>
	    			<td align="right">
						身份证号：
					</td>
	    			<td class="tdright">
	    				<input type="text" name="identityid" value="<%=stu.getIdentity()%>">
					</td>
	    		</tr>
	    		<tr>
	    			<td align="right">
						政治面貌：
					</td>
	    			<td class="tdright">
	    				<div class="selectpolitics">
	    				<select name="politicsstatus" id="politicsstatus">
	    				<option value="0" class="status">共产党员</option>
				    	<option value="1" class="status">共青团员</option>
				    	<option value="2" class="status">其他民主党派人士</option>
				    	<option value="3" class="status">无党派民主人士</option>
				    	<option value="4" class="status">无党派人士</option>
	    				</select>
	    				</div>
					</td>
	    		</tr>
	    		<tr>
	    			<td align="right">
						出生日期：
					</td>
	    			<td class="tdright">
	    				<input name="borndatetime" id="borndatetime" data-ideal="date" type="date" placeholder="1997-09-24" value="<%=borndate %>" size="40"/>
					</td>
	    		</tr>
	    		<tr>
	    			<td align="right">
						性别：
					</td>
	    			<td class="tdright">
	    				<input type="radio" name="gender" value="0">女
	    				<input type="radio" name="gender" value="1">男
					</td>
	    		</tr>
	    		<tr>
	    			<td align="right">
						学籍地址：
					</td>
	    			<td class="tdright">
	    				<input type="text" name="address" size="40" value="<%=stu.getAddress()%>">
	    				<!-- <select id="province_selector" class="bd-filter__select" name="province"></select>
						<select id="city_selector" class="bd-filter__select" name="city"></select>
						<select id="district_selector" class="bd-filter__select" name="district"></select> -->
					</td>
	    		</tr>
	    		<tr>
	    			<td colspan="2" align="center">
						<input type="button" class="updatebtn" value="修改" onclick="updateForm();"> &nbsp;&nbsp;&nbsp;&nbsp;
						<input type="reset" class="updatebtn" value="重置">
					</td>
	    		</tr>
	    	</tbody>
	    </table>
	    <script src="js/jquery.js" type="text/javascript"></script>
		<script type="text/javascript">
			var add='<%=stu.getAddress()%>';
			var arryass=new Array();
			arryass=add.split(",");		
		    /** 省市区三级代码查询 **/
		    var DISTRICTS;
		    var provinceCode = '',
		        cityCode = '',
		        districtCode = '';
		    //筛选的地区
		    var province_selector = $('#province_selector'),
		        city_selector = $('#city_selector'),
		        district_selector = $('#district_selector');
		    var htm = '<option value="">----请选择-----</option>';
		    $.ajax({
		        type: 'GET',
		        url: 'json/districts.json',
		        dataType: 'json'
		    }).done(function (res) {
		        DISTRICTS = res;
		        htm = '<option value="">----请选择-----</option>';
		        for(var key in DISTRICTS['100000']) {
		            htm += '<option value="'+key+'">'+DISTRICTS['100000'][key]+'</option>';
		        }
		        province_selector.html(htm);
		        city_selector.html('<option value="">----请选择-----</option>');
		        district_selector.html('<option value="">----请选择-----</option>');
		    }).fail(function () {
		        console.log('获取地区json数据失败');
		    });
		
		    province_selector.change(function () {
		        provinceCode =province_selector.find('option:selected').val();
		        console.log(provinceCode);
		        htm = '<option value="">----请选择-----</option>';
		        for(var key in DISTRICTS[provinceCode]) {
		            htm += '<option value="'+key+'">'+DISTRICTS[provinceCode][key]+'</option>';
		        }
		        city_selector.html(htm);
		        district_selector.html('<option value="">----请选择-----</option>');
		    });
		    city_selector.change(function () {
		        cityCode =city_selector.find('option:selected').val();
		        console.log(cityCode);
		        htm = '<option value="">----请选择-----</option>';
		        for(var key in DISTRICTS[cityCode]) {
		            htm += '<option value="'+key+'">'+DISTRICTS[cityCode][key]+'</option>';
		            district_selector.html(htm);
		        }
		    });
		    district_selector.change(function () {
		        districtCode =district_selector.find('option:selected').val();
		        console.log(districtCode);
		    });
		    function updateForm(){
				var frm = document.inputForm;
				frm.submit();
			}
			
			function init(){
			 	var v = '<%=stu.getGender()%>';
			 	var type = document.getElementsByName("gender");
			 	for(var i = 0; i < type.length; i = i + 1){
			 		if(type[i].value == v){
			 			type[i].checked = "checked";
			 			return ;
			 		}
			 	}
			 	var s='<%=stu.getPoliticsstatus()%>';
			 	var politics = document.getElementById("politicsstatus").options;
			 	var opt=document.getElementsByClassName("status");
			 	for(var i = 0; i < politics.length; i=i+1){
			 		if(politics[i].value == s){
			 			politics[i].selected=true;
			 			//return ;
			 		}
			 	}
			 }
			 for(var i=0;i<province_selector;i++){
			 	if(province_selector.options[i].value==arryass[0]){
			 		province_selector.options[i].selected=true;
			 	}
			 }
			 for(var i=0;i<city_selector;i++){
			 	if(city_selector.options[i].value==arryass[1]){
			 		city_selector.options[i].selected=true;
			 	}
			 }
			 for(var i=0;i<district_selector;i++){
			 	if(district_selector.options[i].value==arryass[2]){
			 		district_selector.options[i].selected=true;
			 	}
			 }
		    //var a=province_selector.selectedIndex,b=city_selector.selectedIndex,c=district_selector.selectedIndex;
			//address=province_selector.options[a].text+city_selector.options[b].text+district_selector.options[c].text; */
		</script>
	  </form>
	</body>
</html>
