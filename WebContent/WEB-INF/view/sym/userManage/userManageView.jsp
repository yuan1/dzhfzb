<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ include file="../../common/common_include.jsp"%>
<script type="text/javascript"
	src="resources/js/datepicker/WdatePicker.js" defer="defer"></script>
<script type="text/javascript" src="resources/js/js_my/photoUpload.js"></script>
<style type="text/css">
.stable th {
	width: 180px;
}

#patriarch input {
	width: 80px;
}

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
<div class="bodytitletxt">基础设置</div>
用户管理
<div class="bodytitleright"></div>
</div>
<div id="rightContent"><input type="hidden" value="${p}"
	id="pValue" />
<div class="rightContentL">
<div class="company_list_title">组织机构</div>
<div class="tree">
<ul class="easyui-tree" style="margin-top: 5px;" id="deptTrees2"
	data-options="url:'Sym_findDeptTree',lines:true"></ul>
</div>
</div>
<div class="rightContentR" style="border: 0;"><input type="hidden"
	value="${p}" id="pValue" /> <c:if test="${o=='r'}">
	<table cellpadding="0" cellspacing="1" class="stable">
		<tr>
			<th style="width: 40px;">序号</th>
			<th style="width: 50px;">用户名</th>
			<th style="width: 50px;">姓名</th>
			<th>身份证号</th>
			<th style="width: 60px;">账号状态</th>
			<th width="240px;">操作</th>
		</tr>
		<c:forEach items="${allUser}" var="allUser" begin="0" varStatus="u">
			<tr>
				<td>${(pageIndex)*10+u.index+1}</td>
				<td>${allUser.username}</td>
				<td>${allUser.realname}</td>
				<td>${allUser.id_card}</td>
				<td><c:if test="${allUser.del==1}">正常</c:if> <c:if
					test="${allUser.del==0}">异常</c:if></td>
				<td>
				<a href="Sym_user?p=${p}&user_id=${allUser.id}&o=s"
					class="easyui-linkbutton"
					data-options="plain:true,iconCls:'icon-ok'">查看</a> 
					<c:forEach items="${topMenusList}" var="top">
					<c:if test="${top.id=='179'}">
					<a
					href="Sym_user?p=${p}&user_id=${allUser.id}&o=u"
					class="easyui-linkbutton"
					data-options="plain:true,iconCls:'icon-edit'">修改</a>
					</c:if><c:if test="${top.id=='180'}"><a
					href="javascript:void(0)" onclick="delUser('${allUser.id}');"
					class="easyui-linkbutton"
					data-options="plain:true,iconCls:'icon-cancel'">删除</a>
					</c:if></c:forEach>
					</td>
			</tr>
		</c:forEach>
	</table>
	<br>
	<!-------------------------分页插件---------------------------->
	<div align="center">当前:第${pageIndex+1}页| 总记录数:${counts}条|
	每页显示:10条| <a href="Sym_user?pageIndex=0&p=${p}&o=r&d=${d}">首页|</a> <s:if
		test="%{pageIndex-1<0}">
		<a style="color: gray; text-decoration: none;">上一页|</a>
	</s:if> <s:else>
		<a href="Sym_user?pageIndex=${pageIndex-1}&p=${p}&o=r&d=${d}">上一页|</a>
	</s:else> <s:if test="%{pageIndex+1<#counts/10}">
		<a href="Sym_user?pageIndex=${pageIndex+1}&p=${p}&o=r&d=${d}">下一页|</a>
	</s:if> <s:else>
		<a style="color: gray; text-decoration: none;">下一页|</a>
	</s:else> <a href="Sym_user?pageIndex=${allPage}&p=${p}&o=r&d=${d}">尾页|</a></div>
	<!-- ------------分页-------------------------------- --></div>
</c:if> <c:if test="${o=='s'}">
	<table cellpadding="0" cellspacing="1" class="stable">
		<tr>
			<th>用户名</th>
			<td style="width: 200px;">${usersMessage.username}</td>
			<th>真实姓名</th>
			<td>${usersMessage.realname}</td>
		</tr>
		<tr>
			<th>性别</th>
			<td>${usersMessage.sex}</td>
			<th>出生年月</th>
			<td>${usersMessage.birthday}</td>
		</tr>
		<tr>
			<th>身份证号</th>
			<td>${usersMessage.id_card}</td>
			<th>职务</th>
			<td>${usersMessage.position}</td>
		</tr>
		<tr>
			<th>住址</th>
			<td>${usersMessage.address}</td>
			<th>手机</th>
			<td>${usersMessage.phone}</td>
		</tr>
		<tr>
			<th>单位</th>
			<td>${usersMessage.dept.dept_name}</td>
			<th>用户组</th>
			<td>${usersMessage.role.name}</td>
		</tr>
		<tr>
			<th>操作</th>
			<td colspan="3"><a href="javascript:void(0)"
				onclick="history.go(-1);" class="easyui-linkbutton"
				data-options="iconCls:'icon-back'">返回</a></td>

		</tr>
	</table>
</c:if><c:if test="${o=='u'}">
	<form id="updateUsers" action="Sym_updateUserDo" method="post"
		enctype="multipart/form-data">
	<table cellpadding="0" cellspacing="1" class="stable">
		<tr>
			<th>用户名</th>
			<td style="width: 200px;"><input class="easyui-validatebox"
				data-options="required:true"
				onblur="checkName('${usersMessage.username}');" id="userName"
				type="text" value="${usersMessage.username}" name="users.username" />
			<input type="hidden" name="users.id" value="${usersMessage.id}" /> <input
				type="hidden" name="users.creat_time"
				value="${usersMessage.creat_time}" /></td>
			<th>密码</th>
			<td><input type="password" name="users.password" value=""
				class="input_text" />&nbsp;<font color="red">*</font>更改密码时填写 <input
				type="hidden" name="old_pass" value="${usersMessage.password}" /></td>
		</tr>
		<tr>
			<th>性别</th>
			<td><select name="users.sex" class="input_select">
				<c:forTokens items="男-女" delims="-" var="varSex">
					<c:if test="${varSex==usersMessage.sex}">
						<option value="${varSex}" selected="selected">${varSex}</option>
					</c:if>
					<c:if test="${varSex!=usersMessage.sex}">
						<option value="${varSex}">${varSex}</option>
					</c:if>
				</c:forTokens>
			</select></td>
			<th>真实姓名</th>
			<td><input type="text" class="input_text"
				value="${usersMessage.realname}" name="users.realname" /></td>
		</tr>
		<tr>
			<th>出生年月</th>
			<td><input readonly="readonly"
				onclick="WdatePicker({dateFmt:'yyyy-MM'})" type="text"
				class="input_text" value="${usersMessage.birthday}"
				name="users.birthday" /></td>
			<th>身份证号</th>
			<td><input type="text"
				class="easyui-validatebox" data-options="required:true"
				value="${usersMessage.id_card}" name="users.id_card" /></td>
		</tr>
		<tr>
			<th>职务</th>
			<td><input type="text" class="input_text"
				value="${usersMessage.position}" name="users.position" /></td>
			<th>住址</th>
			<td><input type="text" class="input_text"
				value="${usersMessage.address}" name="users.address" /></td>
		</tr>
<c:if test="${role=='6' }">
		<tr>
			<th>单位</th>
			<td><input name="users.dept.id" id="cc"
				value="${usersMessage.dept.id}" /></td>
			<th>用户组</th>
			<td><input name="users.role.id" id="roleTreeXl"
				value="${usersMessage.role.id}" /></td>
		</tr></c:if>
		<tr>
			<th>账号状态</th>
			<c:if test="${role=='6' }">
			<td><select name="users.del" class="input_select">
				<c:if test="${usersMessage.del==1}">
					<option value="1" selected="selected">允许</option>
					<option value="0">禁止</option>
				</c:if>
				<c:if test="${usersMessage.del==0}">
					<option value="0" selected="selected">禁止</option>
					<option value="1">允许</option>
				</c:if>
			</select></td>
			</c:if>
			<c:if test="${role!='6' }">
			<td>
				<c:if test="${usersMessage.del==1}">
					允许
				</c:if>
				<c:if test="${usersMessage.del==0}">
					禁止
				</c:if>
			</td>
			</c:if>
			<th>手机</th>
			<td><input type="text" class="input_text"
				value="${usersMessage.phone}" name="users.phone" /></td>
		</tr>
		<tr>
			<th>备注</th>
			<td colspan="3"><input type="text" class="input_text"
				value="${usersMessage.remark}" name="users.remark" /></td>

		</tr>
		<tr>
			<th>操作</th>
			<td colspan="3"><a href="javascript:void(0)"
				onclick="updateUsersDo();" class="easyui-linkbutton"
				data-options="iconCls:'icon-ok'">修改</a> <a
				href="javascript:void(0)" onclick="history.go(-1);"
				class="easyui-linkbutton" data-options="iconCls:'icon-back'">返回</a></td>

		</tr>
	</table>
	</form>
</c:if> <c:if test="${o=='c'}">
	<form id="addUsers" action="Sym_addUsers" method="post">
	<table cellpadding="0" cellspacing="1" class="stable">
		<tr>
			<th>用户名</th>
			<td style="width: 200px;"><input class="easyui-validatebox"
				data-options="required:true" onblur="checkName('');" id="userName"
				type="text" name="users.username" /></td>
			<th>密码</th>
			<td><input id="pass" type="password" name="users.password"
				value="" class="easyui-validatebox" data-options="required:true" /></td>
		</tr>
		<tr>
			<th>性别</th>
			<td><select name="users.sex" class="input_select">
				<option value="男">男</option>
				<option value="女">女</option>
			</select></td>
			<th>真实姓名</th>
			<td><input type="text" class="input_text" name="users.realname" /></td>
		</tr>
		<tr>
			<th>出生年月</th>
			<td><input readonly="readonly"
				onclick="WdatePicker({dateFmt:'yyyy-MM'})" type="text"
				class="input_text" name="users.birthday" /></td>
			<th>身份证号</th>
			<td><input type="text"
				class="easyui-validatebox" data-options="required:true"
				name="users.id_card" /></td>
		</tr>
		<tr>
			<th>职务</th>
			<td><input type="text" class="input_text" name="users.position" /></td>
			<th>住址</th>
			<td><input type="text" class="input_text" name="users.address" /></td>
		</tr>
		<tr>
			<th>单位</th>
			<td><input name="users.dept.id" id="cc" value="${d}" /></td>
			<th>用户组</th>
			<td><input name="users.role.id" id="roleTreeXl"
				value="${users.role.id}" /></td>
		</tr>
		<tr>
			<th>账号状态</th>
			<td><select name="users.del" class="input_select">
				<option value="1">允许</option>
				<option value="0">禁止</option>
			</select></td>
			<th>手机</th>
			<td><input type="text" class="input_text" name="users.phone" /></td>
		</tr>
		<tr>
			<th>备注</th>
			<td colspan="3"><input type="text" class="input_text"
				name="users.remark" /></td>
		</tr>
		<tr>
			<th>操作</th>
			<td colspan="3"><a href="javascript:void(0)"
				onclick="addUsersDo();" class="easyui-linkbutton"
				data-options="iconCls:'icon-ok'">添加</a> <a
				href="javascript:void(0)" onclick="history.go(-1);"
				class="easyui-linkbutton" data-options="iconCls:'icon-back'">返回</a></td>

		</tr>
	</table>
	</form>
</c:if></div>
</div>
</div>
</div>
<script type="text/javascript">
	var p = $("#pValue").val();
	$('#deptTrees2').tree({
		onClick : function(node) {
			var url = "Sym_user?p=" + p + "&&o=r&&d=" + node.id;
			location.href = url;
		},
		onContextMenu : function(e, node) {
			contextMenuNode = node;
			e.preventDefault();
			$('#deptTrees2').tree('select', node.target);
			$('#Mymenus2').menu('show', {
				left : e.pageX,
				top : e.pageY
			});
		}
	});
	$(function() {
		$('#cc').combotree({
			url : 'Sym_findDeptTree'
		});
		$('#roleTreeXl').combotree({
			url : 'Sym_getRoleTree'
		});
	});
	var checkName = function(oldName) {
		var usernameValue = $("#userName").val();
		$.post("Sym_checkUserName", {
			"username" : usernameValue,
			"old_name" : oldName
		}, function(data) {
			if (data != 1) {
				$.messager.alert('提示', '用户已存在', 'info');
				$("#userName").val("");
			}
		});
	};
</script>
<c:forEach items="${topMenusList}" var="top">
	<c:if test="${top.id=='178'}">
<div id="Mymenus2" class="easyui-menu">
<div onClick="addUserView('${p}');">添加用户</div>
</div>
</c:if></c:forEach>
</body>
</html>