<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ include file="../../common/common_include.jsp"%>
<style type="text/css">
#tableMy td {
	text-align: left;
}
</style>
</head>
<body>
<div id="bodycontent">
<div class="topnav"><%@include file="../../common/header.jsp"%>
</div>
<div id="main">
<div id="main_left"><%@ include file="../../common/left.jsp"%>
</div>
<div id="main_right">
<div class="bodytitle">
<div class="bodytitleleft"></div>
<div class="bodytitletxt">系统设置</div>
系统环境
<div class="bodytitleright"></div>
</div>
<div id="rightContent">
	<table id="tableMy" cellpadding="0" cellspacing="1" class="stable">
		<tr>
		<th style="width: 140px;">操作系统</th>
		<td>${sysinfo.osname }</td>
	</tr>
	<tr>
		<th>服务器地址</th>
		<td>${sysinfo.serIP }</td>
	</tr>
	<tr>
		<th>访问端地址</th>
		<td>${sysinfo.hostIP }</td>
	</tr>
	<tr>
		<th>服务器</th>
		<td><%=application.getServerInfo()%></td>
	</tr>
	<tr>
		<th>文件目录</th>
		<td>${sysinfo.appDir}</td>
	</tr>
	</table>
</div>
</div>
</div>
</div>
</body>
</html>