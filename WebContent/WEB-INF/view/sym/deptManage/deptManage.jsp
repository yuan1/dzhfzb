<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ include file="../../common/common_include.jsp"%>
<script type="text/javascript"
	src="resources/js/datepicker/WdatePicker.js" defer="defer"></script>
<style type="text/css">
.stable td {
	text-align: left;
}

.stable th {
	width: 120px;
}

#addDeptTable input {
	width: 200px;
}
#updateDeptTable input{
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
组织机构
<div class="bodytitleright"></div>
</div>
<div id="rightContent"><input type="hidden" value="${p}"
	id="pValue" />
<div class="rightContentL">
<div class="company_list_title">组织机构</div>
<div class="tree">
<ul class="easyui-tree" style="margin-top: 5px;" id="deptTrees"
	data-options="url:'Sym_findDeptTree',lines:true"></ul>
</div>
</div>
<div class="rightContentR" style="border: 0;"><c:if
	test="${o=='r'}">
	<table cellpadding="0" cellspacing="1" class="stable" id="addDeptTable">
		<tr>
			<th>上级单位</th>
			<c:if test="${d==101}">
				<td></td>
			</c:if>
			<c:if test="${d!=101}">
				<td>${deptMess.dept.dept_name}</td>
			</c:if>
		</tr>
		<tr>
			<th>单位名称</th>
			<td>${deptMess.dept_name}</td>
		</tr>
		<tr>
			<th>单位性质</th>
			<td>${deptMess.dept_type.type}</td>
		</tr>
		<tr>
			<th>排序</th>
			<td>${deptMess.sort}</td>
		</tr>
		<tr>
			<th>责任地点</th>
			<td>${deptMess.d_address}</td>
		</tr>
		<tr>
			<th>责任人</th>
			<td>${deptMess.duty_person}</td>
		</tr>
		<tr>
			<th>责任电话</th>
			<td>${deptMess.d_phone}</td>
		</tr>
		<tr>
			<th>备注</th>
			<td>${deptMess.remark}</td>
		</tr>
		<tr>
			<th>操作</th>
			<td>
			<c:forEach items="${topMenusList}" var="top">
				<c:if test="${top.id=='175'}">
			<a href="javascript:void(0)"
				onclick="updateDept2('${deptMess.id}','${p}');"
				class="easyui-linkbutton" data-options="iconCls:'icon-ok'">修改</a>
				</c:if></c:forEach>
				</td>
		</tr>
	</table>
</c:if> <c:if test="${o=='c'}">
	<form id="addDept" method="post">
	<table cellpadding="0" cellspacing="1" class="stable" id="addDeptTable">
		<tr>
			<th>上级单位</th>
			<td>${deptMess.dept_name}<input type="hidden"
				name="depts.dept.id" value="${deptMess.id}" /> <input type="hidden"
				name="depts.status" value="1" /></td>
		</tr>
		<tr>
			<th>单位名称</th>
			<td><input id="deptNameValue" type="text"
				class="easyui-validatebox" data-options="required:true"
				id="deptName" name="depts.dept_name" /></td>
		</tr>
		<tr>
			<th>单位性质</th>
			<td>
				<select name="depts.dept_type.id">
					<c:forEach items="${depttype}" var="dep">
						<option value="${dep.id }">${dep.type }</option>
					</c:forEach>
				</select>
			</td>
		</tr>
		<tr>
			<th>排序</th>
			<td><input id="sortID" type="text" class="input_text"
				name="depts.sort" /></td>
		</tr>
		<tr>
			<th>责任地点</th>
			<td><input type="text" class="input_text" name="depts.d_address" /></td>
		</tr>
		<tr>
			<th>责任人</th>
			<td><input type="text" class="input_text"
				name="depts.duty_person" /></td>
		</tr>
		<tr>
			<th>责任电话</th>
			<td><input type="text" class="input_text" name="depts.d_phone" /></td>
		</tr>
		<tr>
			<th>备注</th>
			<td><input type="text" class="input_text" name="depts.remark" /></td>
		</tr>
		<tr>
			<th>操作</th>
			<td><a href="javascript:void(0)" onclick="addDeptDo()"
				class="easyui-linkbutton" data-options="iconCls:'icon-ok'">添加</a></td>
		</tr>
	</table>
	</form>
</c:if> <c:if test="${o=='u'}">
	<form id="updateDept" method="post">
	<table cellpadding="0" cellspacing="1" class="stable" id="updateDeptTable">
		<tr>
			<th>上级单位<input type="hidden" name="depts.id"
				value="${deptMess.id}" /></th>
			<c:if test="${d==101}">
				<td></td>
			</c:if>
			<c:if test="${d!=101}">
				<td>${deptMess.dept.dept_name}</td>
			</c:if>
		</tr>
		<tr>
			<th>单位名称</th>
			<td><input type="text" class="easyui-validatebox"
				data-options="required:true" id="deptName2"
				value="${deptMess.dept_name}" name="depts.dept_name" /></td>
		</tr>
		<tr>
			<th>单位性质</th>
			<td>
				<select name="depts.dept_type.id">
					<option value="${deptMess.dept_type.id }">${deptMess.dept_type.type }</option>
					<c:forEach items="${depttype}" var="dep">
						<option value="${dep.id }">${dep.type }</option>
					</c:forEach>
				</select>
			</td>
		</tr>
		<tr>
			<th>排序</th>
			<td><input id="sortID" class="input_text" type="text"
				class="intput_text" value="${deptMess.sort}" name="depts.sort" /></td>
		</tr>
		<tr>
			<th>责任地点</th>
			<td><input class="input_text" type="text"
				value="${deptMess.d_address}" name="depts.d_address" /></td>
		</tr>
		<tr>
			<th>责任人</th>
			<td><input class="input_text" type="text"
				name="depts.duty_person" value="${deptMess.duty_person}" /></td>
		</tr>
		<tr>
			<th>责任电话</th>
			<td><input class="input_text" type="text" name="depts.d_phone"
				value="${deptMess.d_phone}" /></td>
		</tr>
		<tr>
			<th>备注</th>
			<td><input type="text" class="input_text" name="depts.remark"
				value="${deptMess.remark}" /></td>
		</tr>
		<tr>
			<th>操作</th>
			<td><a href="javascript:void(0)"
				onclick="updateDeptNameDo('${deptMess.id}','${p}');"
				class="easyui-linkbutton" data-options="iconCls:'icon-ok'">修改</a></td>
		</tr>
	</table>
	</form>
</c:if></div>
</div>
</div>
<div class="moveDialog" style="width: 320px; height: 400px;">
<ul style="margin-top: 5px;" id="deptMoveTree" lines="true"></ul>
</div>
</div>
</div>
<script type="text/javascript">
	var contextMenuNode = null;
	var romoveToNode = null;
	var p = $("#pValue").val();
	$(function() {
		$('#deptTrees').tree({
			onClick : function(node) {
				var url = "Sym_dept?p=" + p + "&&o=r&&d=" + node.id;
				location.href = url;
			},
			onContextMenu : function(e, node) {
				contextMenuNode = node;
				e.preventDefault();
				$('#deptTrees').tree('select', node.target);
				$('#Mymenus').menu('show', {
					left : e.pageX,
					top : e.pageY
				});
			}
		});
		$('#deptMoveTree').tree({
			onClick : function(node) {
				romoveToNode = node;
			}
		});
	});
	function MoveDept(parent) {
		//alert(Mynode.id);
		romoveToNode = null;
		var rootNode = $('#deptTrees').tree('getRoot');
		var data = new Array(1);
		data[0] = $('#deptTrees').tree('getData', rootNode.target);
		$('#deptMoveTree').tree('loadData', data);
		if (rootNode.id == contextMenuNode.id) {
			$.messager.alert('提示', '根节点不可移动', 'info');
			return;
		}
		var currentNode = $('#deptMoveTree').tree('find', contextMenuNode.id);
		//alert(currentNode.id);
		if (null == currentNode) {
			$.messager.alert('提示', '请选择需要移动的组织', 'info');
			return;
		} else {
			$('#deptMoveTree').tree('remove', currentNode.target);
			$('.moveDialog')
					.dialog(
							{
								resizable : true,
								modal : true,
								iconCls : 'icon-edit',
								title : '将[' + contextMenuNode.text + ']移动到',
								cache : false,
								buttons : [
										{
											text : '确定',
											iconCls : 'icon-ok',
											handler : function() {
												if (null == romoveToNode) {
													$.messager.alert('提示',
															'请选择组织', 'info');
													return;
												}
												$
														.post(
																"Sym_moveDept",
																{
																	"d" : contextMenuNode.id,
																	"pid" : romoveToNode.id
																},
																function(data2) {
																	if (data2 == 1) {
																		//$.messager.alert('提示','移动成功','info');
																		$(
																				'.moveDialog')
																				.dialog(
																						'close');
																		location.href = "Sym_dept?o=r&d=101&p="
																				+ parent;
																	}
																});
											}
										},
										{
											text : '关闭',
											iconCls : 'icon-cancel',
											handler : function() {
												$('.moveDialog')
														.dialog('close');
											}
										} ]
							});
		}

	}
</script>
<div id="Mymenus" class="easyui-menu">
<c:forEach items="${topMenusList}" var="top">
	<c:if test="${top.id=='174'}">
<div onClick="addDept('${p}');">添加</div>
<div class="menu-sep"></div>
</c:if><c:if test="${top.id=='175'}">
<div onClick="updateDept('${p}');">修改</div>
<div class="menu-sep"></div>
</c:if><c:if test="${top.id=='176'}">
<div onClick="delDept('${p}');">撤销</div>
<div class="menu-sep"></div>
</c:if><c:if test="${top.id=='177'}">
<div onClick="MoveDept('${p}');">移动</div>
</c:if></c:forEach>
</div>
</body>
</html>