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
历史指标修改
<div class="bodytitleright"></div>
</div>
<div id="rightContent">
<form action="Data_DataList?p=102" method="post" id="ffff">
<table class="stable" cellpadding="0" cellspacing="1">
	<tr>
		<td>
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
	<c:forEach items="${tarList}" var="tar">
		<c:if test="${tar.parentid==null }">
			<tr><th width="80px">${tar.value }</th>
			<td>
				<table class="stable1" cellpadding="0" cellspacing="1">
				<tr>
					<c:set var="a" value="0"/>
					<c:forEach items="${tarList}" var="tar1">
					
						<c:if test="${tar1.parentid==tar.id and tar1.parentid!=12}">
							<td width="35%">${tar1.value }
								<c:if test="${fn:length(tar1.tar_prompt)>0 }">
							[<font color="red">*</font>${tar1.tar_prompt }]
							</c:if>
							</td>
							<td width="15%">
							<form action="Data_addDataDo1" id="ffff${tar1.id}">
								<c:forEach items="${dataList }" var="dat">
									<c:if test="${dat.targets.id==tar1.id }">
										${dat.value }
									</c:if>
								</c:forEach>
							</form>
							</td>
							<c:set var="a" value="${a+1 }"/>
							<c:if test="${a%2==0 and a!=0}">
							<c:set var="b" value="0"/>
							<c:forEach items="${tarList}" var="tar1" varStatus="abc">
								<c:if test="${tar1.parentid==tar.id }">
									<c:set var="b" value="${b+1 }"/>
								</c:if>
							</c:forEach>
							<c:if test="${a!=b}">
								</tr><tr>
							</c:if>
						</c:if>
						</c:if>
						
						
						<c:if test="${tar1.parentid==tar.id and tar1.parentid==12}">
							<td width="80px">${tar1.value }
								<c:if test="${fn:length(tar1.tar_prompt)>0 }">
							[<font color="red">*</font>${tar1.tar_prompt }]
							</c:if>
							</td>
							<td>
							<form action="Data_addDataDo1" id="ffff${tar1.id}">
								<c:forEach items="${dataList }" var="dat">
									<c:if test="${dat.targets.id==tar1.id }">
										${dat.value }
									</c:if>
								</c:forEach>
							</form>
							</td>
							<c:set var="a" value="${a+1 }"/>
							<c:set var="b" value="0"/>
							<c:forEach items="${tarList}" var="tar1" varStatus="abc">
								<c:if test="${tar1.parentid==tar.id }">
									<c:set var="b" value="${b+1 }"/>
								</c:if>
							</c:forEach>
							<c:if test="${a!=b}">
								</tr><tr>
							</c:if>
						</c:if>
						
					</c:forEach>
					
					<c:if test="${tar.id!=12 }">
						<c:if test="${a==0 }">
							<td width="25%"></td><td width="25%"></td><td width="25%"></td><td width="25%"></td>
						</c:if>
						<c:if test="${a%2==1 }">
							<td width="25%"></td><td width="25%"></td>
						</c:if>
					</c:if>
					
				</tr>
				</table>
			</td>
			</tr>
		</c:if>
	</c:forEach>
	<c:forEach items="${topMenusList}" var="top">
			<c:if test="${top.id=='136' and status!='2'}">
	<tr>
	<td>操作</td>
	<td>
		<a href="Data_addData?p=102&month=${month }&deptid=${deptid}&depttypeid=${depttypeid }" class="easyui-linkbutton" data-options="iconCls:'icon-ok'">修改</a>
	</td>
	</tr>
	 </c:if></c:forEach>
</table>
<br>
</div>
</div>
</div>
</body>
</html>