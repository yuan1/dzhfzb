<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@include file="../../common/common_include.jsp"%>
<script type="text/javascript">
	function searchMess() {
		document.getElementById("searchMess").submit();
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
<div class="bodytitletxt">属性管理</div>
属性列表
<div class="bodytitleright"></div>
</div>
<div id="rightContent">
<table class="stable" cellpadding="0" cellspacing="1">
	<tr>
		<th>序号</th>
		<th>名称</th>
		<th>类型</th>
		<th>备注</th>
		<th>操作</th>
	</tr>
	<c:forEach items="${proList}" var="proList" varStatus="pp" begin="0">
		<tr>
			<td>${pp.index+1}</td>
			<td>${proList.p_name}</td>
			<td>${proList.type}</td>
			<td>${proList.remark}</td>
			<td>
			<c:forEach items="${topMenusList}" var="top">
				<c:if test="${top.id=='194'}">
			<a href="Sym_uPro?p=${p}&pro_id=${proList.id}"
				class="easyui-linkbutton"
				data-options="plain:true,iconCls:'icon-edit'">修改</a>&nbsp;
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