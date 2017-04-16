<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ include file="../../common/common_include.jsp"%>
<style type="text/css">
.stable td {
	text-align: left;
}

.stable th {
	width: 120px;
}

#Mystable td,#tableMy td {
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
角色管理
<div class="bodytitleright"></div>
</div>
<div id="rightContent"><input type="hidden" value="${p}"
	id="pValue" />
<div class="rightContentL">
<div class="company_list_title">角色列表</div>
<div class="tree">
<ul class="easyui-tree" style="margin-top: 5px;" id="roleTree"
	data-options="url:'Sym_getRoleTree',lines:true"></ul>
</div>
</div>
<div class="rightContentR" style="border: 0;"><c:if
	test="${o=='r'}">
	<table cellpadding="0" cellspacing="1" class="stable">
		<tr>
			<th>角色名称</th>
			<td>${roleMess.name}</td>
		</tr>
		<tr>
			<th>父角色</th>
			<td>${roleMess.role.name}</td>
		</tr>
		<tr>
			<th>排序</th>
			<td>${roleMess.sort}</td>
		</tr>
		<tr>
			<th>备注</th>
			<td>${roleMess.remark}</td>
		</tr>
		<tr>
			<th>创建时间</th>
			<td>${roleMess.create_time}</td>
		</tr>
		<tr>
			<th>操作</th>
			<td>
			<c:forEach items="${topMenusList}" var="top">
				<c:if test="${top.id=='182'}">
			<a href="javascript:void(0)"
				onclick="updateRoleView2('${p}','${roleMess.id}');"
				class="easyui-linkbutton" data-options="iconCls:'icon-ok'">修改</a>
				</c:if></c:forEach>
				</td>
		</tr>
	</table>
</c:if> <c:if test="${o=='c'}">
	<form method="post" id="addRoleForm">
	<table cellpadding="0" cellspacing="1" class="stable">
		<tr>
			<th>角色名称</th>
			<td><input id="roleName" class="easyui-validatebox"
				data-options="required:true" type="text" name="role.name"
				class="input_text" /></td>
		</tr>
		<tr>
			<th>父角色</th>
			<td><input id="rrr" name="role.role.id" value="${role_id}" /></td>
		</tr>
		<tr>
			<th>排序</th>
			<td><input id="roleSort" class="easyui-validatebox"
				data-options="required:true" type="text" name="role.sort"
				class="input_text" /></td>
		</tr>
		<tr>
			<th>备注</th>
			<td><input type="text" name="role.remark" class="input_text" /></td>
		</tr>
		<tr>
			<th>操作</th>
			<td><a href="javascript:void(0)" onclick="addRoleDo();"
				class="easyui-linkbutton" data-options="iconCls:'icon-ok'">添加</a>&nbsp;<a
				href="javascript:void(0)" onclick="history.go(-1);"
				class="easyui-linkbutton" data-options="iconCls:'icon-back'">返回</a></td>
		</tr>
	</table>
	</form>
</c:if> <c:if test="${o=='u'}">
	<form method="post" id="updateRoleForm">
	<table cellpadding="0" cellspacing="1" class="stable">
		<tr>
			<th>角色名称</th>
			<td><input id="roleName" class="easyui-validatebox"
				data-options="required:true" type="text" name="role.name"
				value="${roleMess.name}" /> <input type="hidden"
				value="${roleMess.id}" name="role.id" /></td>
		</tr>
		<tr>
			<th>父角色</th>
			<td>${roleMess.role.name}</td>
		</tr>
		<tr>
			<th>排序</th>
			<td><input id="roleSort" class="easyui-validatebox"
				data-options="required:true" type="text" name="role.sort"
				value="${roleMess.sort}" /></td>
		</tr>
		<tr>
			<th>备注</th>
			<td><input type="text" class="input_text" name="role.remark"
				value="${roleMess.remark}" /></td>
		</tr>
		<tr>
			<th>操作</th>
			<td><a href="javascript:void(0)"
				onclick="updateRole('${p}','${roleMess.id}');"
				class="easyui-linkbutton" data-options="iconCls:'icon-ok'">修改</a>&nbsp;<a
				href="javascript:void(0)" onclick="history.go(-1);"
				class="easyui-linkbutton" data-options="iconCls:'icon-back'">返回</a></td>
		</tr>
	</table>
	</form>
</c:if> <c:if test="${o=='q'}">
	<form action="Sym_roleEditDo" id="roleEd" method="post">
	<table id="tableMy" cellpadding="0" cellspacing="1" class="stable">
		<tr>
			<th style="width: 100px;">角色名称</th>
			<td>${roleM.name} <input type="hidden" name="role_id"
				value="${roleM.id}" /> <input type="hidden" name="permission"
				id="permission" value="${roleMenus}" /></td>
		</tr>
		<tr>
			<th>管理范围</th>
			<td><input style="width: 200px;" id="roleDept" name="" value="" />
			&nbsp;&nbsp;<font color="red">*</font>不选默认管理范围为所在单位及下级单位 <input
				type="hidden" name="manageScope" id="manageScope" /></td>
		</tr>
		<tr>
			<th>权限编辑</th>
			<td>
			<table cellpadding="0" cellspacing="1" class="stable">
				<c:forEach items="${menusList}" var="li">
					<c:if test="${li.menus.id==null}">
						<tr>
							<td style="width: 100px; text-align: center;"><label><c:choose>
								<c:when test="${roleMenus.contains((li.id).toString())==true}">
									<input class="role" type="checkbox" checked="checked"
										value="${li.id}" />
								</c:when>
								<c:otherwise>
									<input class="role" type="checkbox" value="${li.id}" />
								</c:otherwise>
							</c:choose>${li.menu_name}&nbsp;</label></td>
							<td>
							<c:forEach items="${menusList}" var="li2">
								<c:if test="${li2.menus.id==li.id}">
								<table cellpadding="0" cellspacing="1" class="stable">
									<tr><td width="80px;">
									<label> <c:choose>
										<c:when
											test="${roleMenus.contains((li2.id).toString())==true}">
											<input class="role" type="checkbox" checked="checked"
												value="${li2.id}" />
										</c:when>
										<c:otherwise>
											<input class="role" type="checkbox" value="${li2.id}" />
										</c:otherwise>
									</c:choose>${li2.menu_name}</label></td><td>
									<c:forEach items="${menusList}" var="li3">
										<c:if test="${li3.menus.id==li2.id}">
											<label> <c:choose>
												<c:when
													test="${roleMenus.contains((li3.id).toString())==true}">
													<input class="role" type="checkbox" checked="checked"
														value="${li3.id}" />
												</c:when>
												<c:otherwise>
													<input class="role" type="checkbox" value="${li3.id}" />
												</c:otherwise>
											</c:choose>${li3.menu_name}</font></label>&nbsp;(
											<c:forEach items="${menusList}" var="li4">
												<c:if test="${li4.menus.id==li3.id}">
													<label> <c:choose>
														<c:when
															test="${roleMenus.contains((li4.id).toString())==true}">
															<input class="role" type="checkbox" checked="checked"
																value="${li4.id}" />
														</c:when>
														<c:otherwise>
															<input class="role" type="checkbox" value="${li4.id}" />
														</c:otherwise>
													</c:choose> ${li4.menu_name}</label>
												</c:if>
											</c:forEach>)<br>
										</c:if>
									</c:forEach></td></tr></table>
								</c:if>
							</c:forEach></td>
						</tr>
					</c:if>
				</c:forEach>
			</table>
			</td>
		</tr>
		<tr>
			<th>操作</th>
			<td><a onclick="permissionUpdateDo();" href="javascript:void(0)"
				class="easyui-linkbutton" data-options="iconCls:'icon-ok'">提交修改</a>&nbsp;<a
				onclick="history.go(-1)" href="javascript:void(0)"
				class="easyui-linkbutton" data-options="iconCls:'icon-back'">返回</a></td>
		</tr>
	</table>
	</form>
</c:if></div>
</div>
</div>
</div>
</div>
<script type="text/javascript">
	var p = $("#pValue").val();
	$(function() {
		$('#roleTree').tree(
				{
					onClick : function(node) {
						var url = "Sym_role?p=" + p
								+ "&o=r&role_id=" + node.id;
						location.href = url;
					},
					onContextMenu : function(e, node) {
						contextMenuNode = node;
						e.preventDefault();
						$('#roleTree').tree('select', node.target);
						$('#Mymenus').menu('show', {
							left : e.pageX,
							top : e.pageY
						});
					}
				});
	});
	$(function() {
		$('#rrr').combotree({
			url : 'Sym_getRoleTree'
		});
	});
	$(function() {
		$('#roleDept').combotree({
			url : 'Sym_findDeptTreeForScope?role_id=${role_id}',
			multiple : true
		});
	});
</script>
<div id="Mymenus" class="easyui-menu">
<c:forEach items="${topMenusList}" var="top">
	<c:if test="${top.id=='181'}">
<div onClick="addRole('${p}');">添加</div>
<div class="menu-sep"></div>
</c:if><c:if test="${top.id=='182'}">
<div onClick="updateRoleView('${p}');">修改</div>
<div class="menu-sep"></div>
</c:if><c:if test="${top.id=='183'}">
<div onClick="delRoleDO();">删除</div>
<div class="menu-sep"></div>
</c:if><c:if test="${top.id=='184'}">
<div onClick="permissionUpdate('${p}');">权限编辑</div>
</c:if></c:forEach>
</div>
</body>
</html>