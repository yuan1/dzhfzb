<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@include file="../common/common_include.jsp"%>
<script type="text/javascript">
	function chazhao() {
		document.getElementById('ffff').action="Res_SelectData?p=104&c=1";
		document.getElementById('ffff').submit();
	}
	function exportexcel() {
		document.getElementById('ffff').action="Res_ExportData1?p=104";
		document.getElementById('ffff').submit();
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
<div class="bodytitletxt">统计查询</div>
亮点暗点查询
<div class="bodytitleright"></div>
</div>
<div id="rightContent">
<form action="" method="post" id="ffff">
<div style="width:100%; text-align: center; height: 40px; line-height: 40px;">
			月份：<input readonly="readonly"
			onclick="WdatePicker({dateFmt:'yyyy-MM'})" type="text"
			class="input_text" name="month" value="${month }"/>
			<a href="javascript:void(0)" onclick="chazhao()"
			class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-search'">查找</a>
			<a href="javascript:void(0)" onclick="exportexcel()"
			class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-print'">导出</a>
		</div>
</form>
<table  class="stable" cellpadding="0" cellspacing="1" id="aaaaa"> 
<tr> 
	<th width="5%">序号</th>
	<th width="10%">公司名称</th>
	<th width="30%">亮点</th> 
	<th width="30%">暗点</th>
	<th width="30%">需协调问题</th>
</tr> 
</table>
<div style="height:400px; overflow-y: scroll;" id="scdiv">
<table  class="stable" cellpadding="0" cellspacing="1"   id="bbbbb"> 
<c:forEach items="${deptlist}" var="dept" varStatus="asd">
		<tr>
			<td width="5%">${asd.index+1 }</td>
			<td width="10%">${dept.dept_name }</td>
			<c:set var="datammmm" value=""/>
			<c:forEach items="${dataList}" var="datakey">
				<c:if test="${datakey.key.id==dept.id }">
					<c:set var="datammmm" value="${datakey.value }"/>	
				</c:if>
			</c:forEach>
			<td width="30%">
				<c:forEach items="${datammmm}" var="data">
						<c:if test="${data.targets.id==107 }">
							${data.value }
						</c:if>
					</c:forEach>
			</td>
			<td width="30%">
				<c:forEach items="${datammmm}" var="data">
						<c:if test="${data.targets.id==108 }">
							${data.value }
						</c:if>
					</c:forEach>
			</td><td width="30%">
				<c:forEach items="${datammmm}" var="data">
						<c:if test="${data.targets.id==109 }">
							${data.value }
						</c:if>
					</c:forEach>
			</td>
</c:forEach>
</table> 
</div>
<div id="div1" style="position: fixed; width:300px; height:auto; word-wrap:break-word; border:1px solid #ccc; display:none; background-color:#F2FAFF; padding:10px 10px 10px 10px;"></div>
<script language="javascript" type="text/javascript">
function m(a){
document.getElementById("div1").style.display="block";
document.getElementById("div1").style.top=event.clientY+"px";
document.getElementById("div1").style.left=event.clientX+"px";
document.getElementById("div1").innerHTML=a;
} 
function q(){
document.getElementById("div1").style.display="none";
} 
</script>
<script type="text/javascript">
	var seewidth=document.getElementById("bbbbb").clientWidth;
	var h=$(window).height();
	$('#aaaaa').width(seewidth);
	$('#scdiv').height(h-220);
</script>
</div>
</div>
</div>
</body>
</html>