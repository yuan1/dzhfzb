<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@include file="../../common/common_include.jsp"%>
<style type="text/css">
.stable td {
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
<div class="bodytitletxt">属性管理</div>
属性列表
<div class="bodytitleright"></div>
</div>
<div id="rightContent">
<form method="post" id="uPro">
<table class="stable" cellpadding="0" cellspacing="1">
	<tr>
		<th>名称</th>
		<td><input type="hidden" class="input_text" name="property.p_name"
			value="${pro.p_name}" />${pro.p_name} <input type="hidden" name="property.id"
			value="${pro.id}" /></td>
	</tr>
	<c:if test="${pro.id=='1'}">
	<tr>
		<th>状态</th>
		<td>
			<select name="property.type">
				<option value="${pro.type}">${pro.type}</option>
				<option value="开放">开放</option>
				<option value="关闭">关闭</option>
			</select>	
		</td>
	</tr>
	</c:if>
	<c:if test="${pro.id!='1'}">
		<tr>
		<th>内容</th>
		<td>
			<textarea rows="6" style="width:80%" name="property.type">${pro.type}</textarea>
		</td>
	</tr>
	</c:if>
	<tr>
		<th>备注</th>
		<td><input type="text" class="input_text" name="property.remark"
			value="${pro.remark}" /></td>
	</tr>
	<tr>
		<th>操作</th>
		<td><a onclick="uPro();" href="javascript:void(0)"
			class="easyui-linkbutton" data-options="iconCls:'icon-ok'">修改</a>&nbsp;<a
			onclick="history.go(-1);" href="javascript:void(0)"
			class="easyui-linkbutton" data-options="iconCls:'icon-back'">返回</a></td>
	</tr>
</table>
</form>
</div>
</div>
</div>
</div>
</body>
</html>