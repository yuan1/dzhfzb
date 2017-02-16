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
<div class="bodytitletxt">指标设置</div>
指标设置
<div class="bodytitleright"></div>
</div>
<div id="rightContent">
<c:if test="${o=='l' }">
<table cellpadding="0" cellspacing="1" class="stable">
	<tr>
		<th width="100px">指标名称</th>
		<td style="text-align: left;">${tarMes.tar_name}</td>
	</tr>
	<tr>
		<th>指标内容</th>
		<td style="text-align: left;">${tarMes.value}</td>
	</tr>
	<tr>
			<th>指标类别</th>
			<td style="text-align: left;">
				<c:forEach items="${Tartype }" var="ttype">
					<c:if test="${tarMes.parentid==ttype.id }">
						${ttype.value }
					</c:if>
				</c:forEach>
			</td>
		</tr>
		<tr>
		<th>是否求和</th>
		<td style="text-align: left;"><c:if test="${tarMes.sum==1}">是</c:if><c:if test="${tarMes.sum!=1}">否</c:if></td>
	</tr>
	<tr>
		<th>输入提示</th>
		<td style="text-align: left;">${tarMes.tar_prompt}</td>
	</tr>
	<tr>
		<th>默认值</th>
		<td style="text-align: left;">${tarMes.indexvalue}</td>
	</tr>
	<tr>
		<th>备注</th>
		<td style="text-align: left;">${tarMes.remark}</td>
	</tr>
	<tr>
		<th>操作</th>
		<td colspan="7" style="text-align: left;"><a href="Tar_seeTarget?p=${p }&tar_id=${tarMes.id }&o=u"
			class="easyui-linkbutton" data-options="iconCls:'icon-edit'">修改</a></td>
	</tr>
</table>
</c:if>
<c:if test="${o=='u' }">
<form action="Tar_updateTarget?p=109" method="post" id="ffff">
	<table cellpadding="0" cellspacing="1" class="stable">
		<tr>
			<th width="100px">指标名称</th>
			<td style="text-align: left;">
				<input type="text" name="target.tar_name" value="${tarMes.tar_name}">
				<input type="hidden" name="target.id" value="${tarMes.id}">
				<input type="hidden" name="b" value="${b}">
			
			</td>
		</tr>
		<tr>
			<th>指标内容</th>
			<td style="text-align: left;">
				<input type="text" name="target.value" value="${tarMes.value}">
			</td>
		</tr>
		<tr>
			<th>指标类别</th>
			<td style="text-align: left;">
				<select name="target.parentid">
					<option value="${tarMes.parentid }">
						<c:forEach items="${Tartype }" var="ttype">
							<c:if test="${tarMes.parentid==ttype.id }">
								${ttype.value }
							</c:if>
						</c:forEach>
					</option>
					<c:forEach items="${Tartype }" var="ttype">
						<option value="${ttype.id }">${ttype.value }</option>
					</c:forEach>
				</select>
			</td>
		</tr>
		<tr>
			<th>指标排序</th>
			<td style="text-align: left;">
				<input type="text" name="target.sort" value="${tarMes.sort}">
			</td>
		</tr>
		<tr>
			<th>是否求和</th>
			<td style="text-align: left;">
				<select name="target.sum">
					<option value="${tarMes.sum }">
						<c:if test="${tarMes.sum==1 }">
							是
						</c:if>
						<c:if test="${tarMes.sum!=1 }">
							否
						</c:if>
					</option>
					<option value="1">是</option>
					<option value="0">否</option>
				</select>
			</td>
		</tr>
		<tr>
			<th>输入提示</th>
			<td style="text-align: left;">
				<input type="text" name="target.tar_prompt" value="${tarMes.tar_prompt}">
			</td>
		</tr>
		<tr>
			<th>默认值</th>
			<td style="text-align: left;">
				<input type="text" name="target.indexvalue" value="${tarMes.indexvalue}">
			</td>
		</tr>
		<tr>
			<th>备注</th>
			<td style="text-align: left;">
				<input type="text" name="target.remark" value="${tarMes.remark}">
			</td>
		</tr>
		<tr>
			<th>操作</th>
			<td colspan="7" style="text-align: left;"><a
				onclick="document.getElementById('ffff').submit()" href="javascript:void(0)"
				class="easyui-linkbutton" data-options="iconCls:'icon-back'">提交</a></td>
		</tr>
	</table>
</form>
</c:if>
</div>
</div>
</div>
</div>
</body>
</html>