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
<div class="bodytitletxt">指标管理</div>
指标预览
<div class="bodytitleright"></div>
</div>
<div id="rightContent">
<form action="Tar_LookTar?p=109" method="post" id="ffff">
<table class="stable" cellpadding="0" cellspacing="1">
	<tr>
		<td>
			公司类型：<select name="depttypeid">
				<option value="${depttypeid }">
					<c:forEach items="${depttypeList }" var="de1">
						<c:if test="${de1.id==depttypeid }">${de1.type }</c:if>
					</c:forEach>
				</option>
				<c:forEach items="${depttypeList }" var="de">
					<option value="${de.id }">${de.type }</option>
				</c:forEach>
			</select>
			月份：<input readonly="readonly"
			onclick="WdatePicker({dateFmt:'yyyy-MM'})" type="text"
			class="input_text" name="month" value="${month }"/>
			<a href="javascript:void(0)" onclick="document.getElementById('ffff').submit();"
			class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-search'">查找</a> 
		</td>
	</tr>
</table>
</form>
<table class="stable1" cellpadding="0" cellspacing="1">
	<c:forEach items="${tarList}" var="tar1">
	<c:if test="${tar1.parentid==null }">
			<tr><td width="15%">
						${tar1.value}
				</td>
			<td>
				<table class="stable" cellpadding="0" cellspacing="1">
				<tr>
				<c:set var="a" value="0"/>
				<c:forEach items="${tarList}" var="tar2">
					<c:if test="${tar2.parentid==tar1.id }">
						<td width="25%">
						${tar2.value }</td>
						<c:set var="a" value="${a+1 }"/>
						<c:if test="${a%4==0 and a!=0 }">
						<c:set var="b" value="0"/>
						<c:forEach items="${tarList}" var="tar2">
							<c:if test="${tar2.parentid==tar1.id }">
								<c:set var="b" value="${b+1 }"/>
							</c:if>
						</c:forEach>
						<c:if test="${a!=b }">
							</tr><tr>
						</c:if>
					</c:if>
					</c:if>
				</c:forEach>
				<c:if test="${a%4==1 }">
					<td width="25%"></td><td width="25%"></td><td width="25%"></td>
				</c:if>
				<c:if test="${a%4==2 }">
					<td width="25%"></td><td width="25%"></td>
				</c:if>
				<c:if test="${a%4==3 }">
					<td width="25%"></td>
				</c:if>
				<c:if test="${a==0 }">
					<td width="25%"></td><td width="25%"></td><td width="25%"></td><td width="25%"></td>
				</c:if>
				</tr>
				</table>
			</td>
			</tr>
		</c:if>
	</c:forEach>
</table>
<br>
</div>
</div>
</div>
</body>
</html>