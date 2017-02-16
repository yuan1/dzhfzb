<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ include file="../../common/common_include.jsp"%>
<script type="text/javascript">
	function restore(filename) {
		var date = new Date();
		$.post("Sym_restoreDB", {
			'timer' : date,
			'filename' : filename
		}, function(data) {
			if (data == 1) {
				$.messager.alert('提示','还原数据库成功!','info');
			} else {
				$.messager.alert('提示','还原数据库失败!','info');
			}
		});
	}
	function deletefile(filename2) {
		var date = new Date();
		$.post("Sym_deleteBackDb", {
			'timer' : date,
			'filename' : filename2
		}, function(data) {
			if (data == 1) {
				location.reload();
			} else {
				$.messager.alert('提示','删除失败!','info');
			}
		});
	}
</script>
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
备份还原
<div class="bodytitleright"></div>
</div>
<div id="rightContent">
<c:forEach items="${topMenusList}" var="top">
	<c:if test="${top.id=='197'}">
<table cellpadding="0" cellspacing="1" class="stable">
	<tr>
		<td style="text-align: left;"><a href="javascript:void(0)"
			onclick="backDB();" class="easyui-linkbutton"
			data-options="plain:true,iconCls:'icon-print'">开始备份</a></td>
	</tr>
</table>
</c:if></c:forEach>
<table id="tableMy" cellpadding="0" cellspacing="1" class="stable">
	<tr>
		<th>日期</th>
		<th>文件</th>
		<th>操作</th>
	</tr>
	<c:forEach var="entity" items="${backfilenames}">
		<tr>
			<td>${fn:substring(entity, 0, 10)}</td>
			<td>${entity }</td>
			<td>
			<c:forEach items="${topMenusList}" var="top">
				<c:if test="${top.id=='198'}">
			<a href="javascript:void(0)"
				onclick="javascript:restore('${fn:substring(entity, 0, 10)}')">还原此备份</a>
				</c:if><c:if test="${top.id=='199'}">
			<a href="javascript:void(0)"
				onclick="javascript:deletefile('${fn:substring(entity, 0, 10)}')">删除此备份</a>
				</c:if></c:forEach>
			</td>
		</tr>
	</c:forEach>
</table>
</div>
</div>
</div>
</div>
</body>
</html>