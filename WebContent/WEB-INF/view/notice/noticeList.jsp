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
<div class="bodytitletxt">通知公告</div>
公告列表
<div class="bodytitleright"></div>
</div>
<div id="rightContent">
<table class="stable" cellpadding="0" cellspacing="1">
	<tr>
		<th>序号</th>
		<th>标题</th>
		<th>发布时间</th>
		<th width="240px;">操作</th>
	</tr>
	<c:forEach items="${noticeList}" var="noticeList" varStatus="m"
		begin="0">
		<tr>
			<td>${(pageIndex)*10+m.index+1}</td>
			<td>${noticeList.title}</td>
			<td>${noticeList.date}</td>
			<td>
			<c:forEach items="${topMenusList}" var="top">
				<c:if test="${top.url=='Notice_showNotice'}">
				<a href="Notice_showNotice?p=${p}&&nt_id=${noticeList.id}" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-ok'">查看</a> &nbsp; 
				</c:if><c:if test="${top.url=='Notice_updateNoticeShow;Notice_updateNoticeDo'}">
				<a href="Notice_updateNoticeShow?p=${p}&&nt_id=${noticeList.id}" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-edit'">修改</a> &nbsp; 
				</c:if><c:if test="${top.url=='Notice_deleteNotice'}">
				<a href="javascript:void(0)" onclick="delNotice('${noticeList.id}','${p}');" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-cancel'">删除</a>
				</c:if>
			</c:forEach>
			</td>
		</tr>
	</c:forEach>
</table>

<br>
<!-------------------------分页插件---------------------------->
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
<!-- ------------分页-------------------------------- --></div>
</div>
</div>
</div>
</body>
</html>