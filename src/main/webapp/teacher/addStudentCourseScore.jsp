<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'addStudentCourseScore.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<style>
        tr {
            background-color: rgb(255, 255, 255);
        }

        td {
            text-align: center;
        }

        button {
            width: 100px;
            height: 40px;
            line-height: 30px;
            cursor: pointer;
            color: #fff;
            font-size: 14px;
            font-weight: bold;
            background-color: #27a9e3;
            border-radius: 3px;
            border: none;
            -webkit-appearance: none;
            outline: none;
            margin: 10px;
        }
    </style>
  </head>
  
  <body>
  		<table id="main-tab" width="800px" style="margin: 0 auto;">
            <tr>
                <th colspan="2" style="font-size: 2em;">设备报修信息</th>
            </tr>

            <tr onmouseout="this.style.backgroundColor='#ffffff'" onmouseover="this.style.backgroundColor='#edf5ff'">
                <th align="right" valign="middle" class="bggray" width="200px">设备编号：</th>
                <td>
                    <input type="text" id="deviceNo" name="deviceNo"  class="mustInput"  onkeyup="hintDeviceNo()"  onchange="selectDeviceName(this.value)"/>
                </td>
            </tr>
            <tr onmouseout="this.style.backgroundColor='#ffffff'" onmouseover="this.style.backgroundColor='#edf5ff'">
                <th align="right" valign="middle" class="bggray" width="200px">设备名称：</th>
                <td>
                    <span id="DeviceName"></span>
                </td>
            </tr>
            <tr onmouseout="this.style.backgroundColor='#ffffff'" style="display:none;"  onmouseover="this.style.backgroundColor='#edf5ff'">
                <th align="right" valign="middle" class="bggray" width="200px">异常内容：</th>
                <td>
                    <input type="text" name="content"  class=""/>
                </td>
            </tr>
            <tr onmouseout="this.style.backgroundColor='#ffffff'" onmouseover="this.style.backgroundColor='#edf5ff'">
                <th align="right" valign="middle" class="bggray" width="200px">报修来源：</th>
                <td>
                    <select name="source" id="">
                        <option value="PM卡">PM卡</option>
                        <option value="设备报修单">设备报修单</option>
                        <option value="突发">突发</option>
                        <option value="巡检">巡检</option>
                    </select>
                </td>
            </tr>
            <tr onmouseout="this.style.backgroundColor='#ffffff'"  style="display:none;" onmouseover="this.style.backgroundColor='#edf5ff'">
                <th align="right" valign="middle" class="bggray" width="200px">图片1：</th>
                <td>
                    <img src="" alt="" name="path1">
                </td>
            </tr>
            <tr onmouseout="this.style.backgroundColor='#ffffff'" style="display:none;" onmouseover="this.style.backgroundColor='#edf5ff'">
                <th align="right" valign="middle" class="bggray" width="200px">图片2：</th>
                <td>
                    <img src="" alt="" name="path2">
                </td>
            </tr>
            <tr onmouseout="this.style.backgroundColor='#ffffff'" style="display:none;" onmouseover="this.style.backgroundColor='#edf5ff'">
                <th align="right" valign="middle" class="bggray" width="200px">图片3：</th>
                <td>
                    <img src="" alt="" name="path3">
                </td>
            </tr>
            <tr onmouseout="this.style.backgroundColor='#ffffff'" style="display:none;" onmouseover="this.style.backgroundColor='#edf5ff'">
                <th align="right" valign="middle" class="bggray" width="200px">图片4：</th>
                <td>
                    <img src="" alt="" name="path4">
                </td>
            </tr>
            <tr onmouseout="this.style.backgroundColor='#ffffff'" style="display:none;" onmouseover="this.style.backgroundColor='#edf5ff'">
                <th align="right" valign="middle" class="bggray" width="200px">图片5：</th>
                <td>
                    <img src="" alt="" name="path5">
                </td>
            </tr>
            <tr onmouseout="this.style.backgroundColor='#ffffff'" onmouseover="this.style.backgroundColor='#edf5ff'">
                <th align="right" valign="middle" class="bggray" width="200px">报修日期：</th>
                <td>
                    <input type="text" name="repairDate" class="datePick" readonly/>
                </td>
            </tr>
            <tr onmouseout="this.style.backgroundColor='#ffffff'" onmouseover="this.style.backgroundColor='#edf5ff'">
                <th align="right" valign="middle" class="bggray" width="200px">报修部门：</th>
                <td>
                    <input type="text" name="repairDepartment" />
                </td>
            </tr>
            <tr onmouseout="this.style.backgroundColor='#ffffff'" onmouseover="this.style.backgroundColor='#edf5ff'">
                <th align="right" valign="middle" class="bggray" width="200px">报修时间：</th>
                <td>
                    <input type="text" name="repairTime" class="datePickTime mustInput" readonly/>
                </td>
            </tr>
            <tr onmouseout="this.style.backgroundColor='#ffffff'" onmouseover="this.style.backgroundColor='#edf5ff'">
                <th align="right" valign="middle" class="bggray" width="200px">故障现象描述：</th>
                <td>
                    <textarea name="faultPhenomenon" id="" cols="40" rows="5" maxlength="100"  class="mustInput"></textarea>
                </td>
            </tr>
            <tr onmouseout="this.style.backgroundColor='#ffffff'" style="display:none;" onmouseover="this.style.backgroundColor='#edf5ff'">
                <th align="right" valign="middle" class="bggray" width="200px">领取人：</th>
                <input type="hidden" name="maintenancePerson" />
                <td id="maintenancePersonTd">
                </td>
            </tr>
            <tr onmouseout="this.style.backgroundColor='#ffffff'" style="display:none;" onmouseover="this.style.backgroundColor='#edf5ff'">
                <th align="right" valign="middle" class="bggray" width="200px">领取时间：</th>
                <td>
                    <input type="text" name="date1" class="" readonly/>
                </td>
            </tr>
            <tr onmouseout="this.style.backgroundColor='#ffffff'" style="display:none;" onmouseover="this.style.backgroundColor='#edf5ff'">
                <th align="right" valign="middle" class="bggray" width="200px">计划领取人：</th>
                <input type="hidden" name="person2" />
                <td id="person2Td">
                </td>
            </tr>
            <tr onmouseout="this.style.backgroundColor='#ffffff'" style="display:none;" onmouseover="this.style.backgroundColor='#edf5ff'">
                <th align="right" valign="middle" class="datePickTime" width="200px">计划领取时间：</th>
                <td>
                    <input type="text" name="date2" class="" readonly/>
                </td>
            </tr>
            <tr onmouseout="this.style.backgroundColor='#ffffff'" style="display:none;" onmouseover="this.style.backgroundColor='#edf5ff'">
                <th align="right" valign="middle" class="bggray" width="200px">班组：</th>
                <td>
                    <input type="text" name="className" />
                </td>
            </tr>
            <tr onmouseout="this.style.backgroundColor='#ffffff'" onmouseover="this.style.backgroundColor='#edf5ff'">
                <th align="right" valign="middle" class="bggray" width="200px">挂牌人：</th>
                <td id="personTd">
	                <input type="text" name="person" />
                </td>
            </tr>
            <tr onmouseout="this.style.backgroundColor='#ffffff'" onmouseover="this.style.backgroundColor='#edf5ff'">
                <th align="right" valign="middle" class="bggray" width="200px">挂牌日期：</th>
                <td>
                    <input type="text" name="date" class="datePick" readonly/>
                </td>
            </tr>
            <tr onmouseout="this.style.backgroundColor='#ffffff'" style="display:none;" onmouseover="this.style.backgroundColor='#edf5ff'">
                <th align="right" valign="middle" class="bggray" width="200px">报修等级：</th>
                <td>
                    <select name="auditLevel" id="">
                        <option value="PM">PM</option>
                        <option value="突发">突发</option>
                    </select>
                </td>
            </tr>
            <tr onmouseout="this.style.backgroundColor='#ffffff'" style="display:none;" onmouseover="this.style.backgroundColor='#edf5ff'">
                <th align="right" valign="middle" class="bggray" width="200px">故障类别：</th>
                <td>
                    <select name="faultCategory" id="">
                        <option value=""></option>
                    </select>
                </td>
            </tr>
            <tr onmouseout="this.style.backgroundColor='#ffffff'" style="display:none;" onmouseover="this.style.backgroundColor='#edf5ff'">
                <th align="right" valign="middle" class="bggray" width="200px">审核人：</th>
                <input type="hidden" name="reviewer" />
                <td id="reviewerTd">
                </td>
            </tr>
            <tr onmouseout="this.style.backgroundColor='#ffffff'" onmouseover="this.style.backgroundColor='#edf5ff'">
                <th align="right" valign="middle" class="bggray" width="200px">备注：</th>
                <td>
                    <input type="text" name="mark" />
                </td>
            </tr>
            <tr onmouseout="this.style.backgroundColor='#ffffff'" onmouseover="this.style.backgroundColor='#edf5ff'">
                <td colspan="2">
                    <input type="button" value="确定" onclick="addData()" />
                </td>
            </tr>
        </table>
  </body>
</html>
