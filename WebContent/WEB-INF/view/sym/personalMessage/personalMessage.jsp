<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript"
	src="resources/js/datepicker/WdatePicker.js" defer="defer"></script>
<%@include file="../../common/common_include.jsp"%>
<style type="text/css">
#myTable td {
	text-align: left;
}

input {
	width: 200px;
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
<div class="bodytitletxt">基础设置</div>
个人信息
<div class="bodytitleright"></div>
</div>
<div id="rightContent">
<form id="updateUserMess" action="Sym_updateUserDo" method="post">
<table id="myTable" class="stable" cellpadding="0" cellspacing="1">
	<tr>
		<th style="width: 130px;">用户名</th>
		<td><input id="username" class="easyui-validatebox"
			data-options="required:true" name="users.username"
			value="${userSee3.username}" /> <input type="hidden" name="users.id"
			value="${userSee3.id}" /></td>
		<th>密码</th>
		<td><input class="input_text" type="password" name="users.password" value="" />
		 <font color="red">*</font>更改密码时填写 <input type="hidden" name="old_pass" value="${usersMessage.password}" /></td>
	</tr>
	<tr>
		<th>姓名</th>
		<td><input class="input_text" type="text" name="users.realname"
			value="${userSee3.realname}" /></td>
		<th>性别</th>
		<td><select class="input_select" name="users.sex">
			<c:if test="${userSee3.sex=='男'}">
				<option value="男" selected="selected">男</option>
				<option value="女">女</option>
			</c:if>
			<c:if test="${userSee3.sex=='女'}">
				<option value="男">男</option>
				<option value="女" selected="selected">女</option>
			</c:if>
		</select></td>
	</tr>
		<tr>
		<th>电话</th>
		<td><input class="input_text" type="text" name="users.phone"
			value="${userSee3.phone}" /> <input type="hidden"
			name="users.dept.id" value="${userSee3.dept.id}" /> <input
			type="hidden" name="users.role.id" value="${userSee3.role.id}" />
			<input type="hidden" name="users.del" value="${userSee3.del}" /></td>
			<th>出生年月</th>
			<td><input readonly="readonly"
				onclick="WdatePicker({dateFmt:'yyyy-MM'})" type="text"
				class="input_text" name="users.birthday" value="${userSee3.birthday }"/></td>
	</tr><tr>
			<th>身份证号</th>
			<td><input id="idCard" onblur="checkIdCard('');" type="text"
				class="easyui-validatebox" data-options="required:true"
				name="users.id_card" value="${userSee3.id_card }"/></td>
			<th>职务</th>
			<td><input type="text" class="input_text" name="users.position"  value="${userSee3.position }"/></td>
	</tr><tr>
			<th>住址</th>
			<td><input type="text" class="input_text" name="users.address"  value="${userSee3.address }"/></td>
		<th>单位</th>
		<td>${userSee3.dept.dept_name}</td>
	</tr>
	<tr>
		<th>用户组</th>
		<td>${userSee3.role.name}</td>
		<th>备注</th>
		<td><input class="input_text" type="text" name="users.remark"
			value="${userSee3.remark}" /></td>
	</tr>
	<tr>
		<th>操作</th>
		<td colspan="3">
		<c:forEach items="${topMenusList}" var="top">
				<c:if test="${top.id=='201'}">
		<a href="javascript:void(0)" onclick="updateUserMess();"
			class="easyui-linkbutton" data-options="iconCls:'icon-ok'">修改</a>&nbsp;&nbsp;
			</c:if></c:forEach>
			<a
			href="javascript:void(0)" class="easyui-linkbutton"
			onclick="history.go(-1)" data-options="iconCls:'icon-back'">返回</a></td>
	</tr>
</table>
</form>
</div>
</div>
</div>
</div>
</body>
</html>