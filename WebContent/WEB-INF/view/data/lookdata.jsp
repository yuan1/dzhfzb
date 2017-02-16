<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@include file="../common/common_include.jsp"%>
<script type="text/javascript">
function submitf() {
	document.getElementById("ffffa").submit();
}
</script>
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
 ${month}期指标录入
<div class="bodytitleright"></div>
</div>
<div id="rightContent">
<form action="" method="post" id="ffff">
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
							<td width="25%">${tar1.value }</td>
							<td width="25%">
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
							<td width="80px">${tar1.value }</td>
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
			<c:if test="${top.id=='136'}">
	<tr>
	<td>操作</td>
	<td>
	<form action="Data_addDataDo?p=102" method="post" id="ffffa">
		<input type="hidden" name="month" value="${month }">
		<input type="hidden" name="depttypeid" value="${depttypeid }">
	</form>
			<a href="javascript:void(0)" onclick="submitf()"
				 class="easyui-linkbutton" data-options="iconCls:'icon-ok'">提交</a>
	</td>
	</tr>
	 </c:if></c:forEach>
</table>
</form>
<br>
</div>
</div>
</div>
</body>
</html>