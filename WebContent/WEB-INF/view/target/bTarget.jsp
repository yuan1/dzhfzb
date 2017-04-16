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
指标修改
<div class="bodytitleright"></div>
</div>
<div id="rightContent">
<table class="stable1" cellpadding="0" cellspacing="1">
	<c:forEach items="${tarList}" var="tar">
		<c:if test="${tar.parentid==null }">
			<tr><td width="10%">${tar.value }</td>
			<td>
				<c:forEach items="${tarList}" var="tar1">
					<c:if test="${tar1.parentid==tar.id }">
						<a href="Tar_seeTarget?p=${p }&tar_id=${tar1.id }">${tar1.value }</a>&nbsp;&nbsp;&nbsp;&nbsp;
					</c:if>
				</c:forEach>
			</td>
			</tr>
		</c:if>
	</c:forEach>
</table>
</div>
</div>
</div>
</div>
</body>
</html>