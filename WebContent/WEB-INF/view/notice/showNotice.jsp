<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@include file="../common/common_include.jsp"%>
</head>
<body>
<div id="bodycontent">
<div class="topnav"><%@include file="../common/header.jsp"%>
</div>
<div id="main">
<div id="main_left"><%@ include file="../common/left.jsp"%>
</div>
<div id="main_right">
<div class="bodytitle">
<div class="bodytitleleft"></div>
<div class="bodytitletxt">通知公告</div>
查看公告
<div class="bodytitleright"></div>
</div>
<div id="rightContent">
<table cellpadding="0" cellspacing="1" class="stable">
	<tr>
		<th width="100px">标题</th>
		<td style="text-align: left;">${noticeMessage.title}</td>
	</tr>
	<tr>
		<th>内容</th>
		<td style="text-align: left;">${noticeMessage.content}</td>
	</tr>
	<tr>
		<th>操作</th>
		<td colspan="7" style="text-align: left;"><a
			onclick="history.go(-1);" href="javascript:void(0)"
			class="easyui-linkbutton" data-options="iconCls:'icon-back'">返回</a></td>
	</tr>
</table>
</div>
</div>
</div>
</div>
</body>
</html>