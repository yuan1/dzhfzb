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
<div class="bodytitletxt">指标审核</div>
${name}
<div class="bodytitleright"></div>
</div>
<div id="rightContent">
<table class="stable" cellpadding="0" cellspacing="1">
	<c:if test="${status!=3 }">
	<tr>
		<th>序号</th>
		<th>公司</th>
		<th>公司类型</th>
		<th width="80px;">操作</th>
	</tr>
	<c:forEach items="${deptlist}" var="dept" varStatus="m"
		begin="0">
		<tr>
			<td>${(pageIndex)*10+m.index+1}</td>
			<td>${dept.dept_name}</td>
			<td>${dept.dept_type.type}</td>
			<td>
			<c:if test="${status=='1' }">
				<c:forEach items="${topMenusList}" var="top">
					<c:if test="${top.id=='148'}">
					<a href="Review_review?p=103&dept_id=${dept.id }" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-ok'">查看</a> &nbsp; 
					</c:if>
				</c:forEach>
			</c:if>
			<c:if test="${status=='2' }">
				<c:forEach items="${topMenusList}" var="top">
					<c:if test="${top.id=='147'}">
					<a href="Review_review?p=103&dept_id=${dept.id }" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-ok'">查看</a> &nbsp; 
					</c:if>
				</c:forEach>
			</c:if>
			</td>
		</tr>
	</c:forEach>
	</c:if>
	<c:if test="${status==3 }">
		<tr>
		<th>序号</th>
		<th>公司</th>
		<th>公司类型</th>
		<th>负责人</th>
		<th>负责电话</th>
	</tr>
	<c:forEach items="${deptlist}" var="dept" varStatus="m"
		begin="0">
		<tr>
			<td>${(pageIndex)*10+m.index+1}</td>
			<td>${dept.dept_name}</td>
			<td>${dept.dept_type.type}</td>
			<td>${dept.duty_person }</td>
			<td>${dept.d_phone }</td>
		</tr>
	</c:forEach>
	</c:if>
</table>

<br>
<!--
<div align="center">当前:第${pageIndex+1}页| 总记录数:${counts}条|
每页显示:10条| <a href="Notice_noticeList?pageIndex=0&p=${p}">首页|</a> <s:if
	test="%{pageIndex-1<0}">
	<a style="color: gray; text-decoration: none;">上一页|</a>
</s:if> <s:else>
	<a href="Notice_noticeList?pageIndex=${pageIndex-1}&p=${p}">上一页|</a>
</s:else> <s:if test="%{pageIndex<#counts/10}">
	<a href="Notice_noticeList?pageIndex=${pageIndex+1}&p=${p}">下一页|</a>
</s:if> <s:else>
	<a style="color: gray; text-decoration: none;">下一页|</a>
</s:else> <a href="Notice_noticeList?pageIndex=${allPage}&p=${p}">尾页|</a></div>
--></div>
</div>
</div>
</div>
</body>
</html>