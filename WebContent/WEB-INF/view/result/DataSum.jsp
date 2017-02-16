<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="js/echarts.js" charset="UTF-8"></script>
<%@include file="../common/common_include.jsp"%>
<script type="text/javascript">
	function chazhao() {
		document.getElementById('ffff').action="Res_SelectDataSum?p=104";
		document.getElementById('ffff').submit();
	}
	function exportexcel() {
		document.getElementById('ffff').action="Res_ExportDataSum?p=104";
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
集团汇总
<div class="bodytitleright"></div>
</div>
<div id="rightContent">
<form action="" method="post" id="ffff">
<table class="stable" cellpadding="0" cellspacing="1">
	<tr>
		<td>
			月份：<input readonly="readonly"
			onclick="WdatePicker({dateFmt:'yyyy-MM'})" type="text"
			class="input_text" name="month" value="${month }"/>
			<a href="javascript:void(0)" onclick="chazhao()"
			class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-search'">查找</a>
			<a href="javascript:void(0)" onclick="exportexcel()"
			class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-print'">导出</a>
		</td>
	</tr>
</table>
</form>
<table class="stable" cellpadding="0" cellspacing="1" id="aaaaa"> 
<tr> 
	<th width="5%">序号</th>
	<th width="11%">公司名称</th>
	<c:forEach items="${tarlist }" var="tar">
		<th width="10%"><a href="Res_tx?month=${month }&tarid=${tar.id}">${tar.value }</a></th> 
	</c:forEach>
</tr>
</table>
<div style="height:400px; overflow-y:scroll;" id="scdiv">
<table class="stable" cellpadding="0" cellspacing="1" id="bbbbb"> 
<c:set var="sum1" value="0"/>
<c:set var="sum2" value="0"/>
<c:set var="sum3" value="0"/>
<c:set var="sum4" value="0"/>
<c:set var="sum5" value="0"/>
<c:set var="sum6" value="0"/>
<c:set var="sum7" value="0"/>
<c:forEach items="${deptlist}" var="dept" varStatus="asd">
		<tr>
			<td width="5%">${asd.index+1}</td>
			<td width="11%">
				${dept.dept_name}
			</td>
			<c:set var="datammmm" value=""/>
			<c:forEach items="${dataList}" var="datakey">
				<c:if test="${datakey.key.id==dept.id }">
					<c:set var="datammmm" value="${datakey.value }"/>	
				</c:if>
			</c:forEach>
			<td class="bordertop" width="10%" style="white-space:nowrap;word-break:keep-all;text-overflow:ellipsis; overflow:hidden;">
				<c:forEach items="${datammmm}" var="data">
						<c:if test="${data.targets.id==52 }">
							${data.value }
							<c:set var="sum1" value="${sum1+data.value}"/>
						</c:if>
					</c:forEach>
			</td>
			<td class="bordertop" width="10%" style="white-space:nowrap;word-break:keep-all;text-overflow:ellipsis; overflow:hidden;">
				<c:forEach items="${datammmm}" var="data">
						<c:if test="${data.targets.id==54 }">
							${data.value }
							<c:set var="sum2" value="${sum2+data.value}"/>
						</c:if>
					</c:forEach>
			</td><td class="bordertop" width="10%" style="white-space:nowrap;word-break:keep-all;text-overflow:ellipsis; overflow:hidden;">
				<c:forEach items="${datammmm}" var="data">
						<c:if test="${data.targets.id==91 }">
							${data.value }
							<c:set var="sum3" value="${sum3+data.value}"/>
						</c:if>
					</c:forEach>
			</td>
			<td class="bordertop" width="10%" style="white-space:nowrap;word-break:keep-all;text-overflow:ellipsis; overflow:hidden;">
				<c:forEach items="${datammmm}" var="data">
						<c:if test="${data.targets.id==117 }">
							${data.value }
							<c:set var="sum4" value="${sum4+data.value}"/>
						</c:if>
					</c:forEach>
			</td>
			<td class="bordertop" width="10%" style="white-space:nowrap;word-break:keep-all;text-overflow:ellipsis; overflow:hidden;">
				<c:forEach items="${datammmm}" var="data">
						<c:if test="${data.targets.id==57 }">
							${data.value }
							<c:set var="sum5" value="${sum5+data.value}"/>
						</c:if>
					</c:forEach>
			</td><td class="bordertop" width="10%" style="white-space:nowrap;word-break:keep-all;text-overflow:ellipsis; overflow:hidden;">
				<c:forEach items="${datammmm}" var="data">
						<c:if test="${data.targets.id==59 }">
							${data.value }
							<c:set var="sum6" value="${sum6+data.value}"/>
						</c:if>
					</c:forEach>
			</td>
			<td class="bordertop" width="10%" style="white-space:nowrap;word-break:keep-all;text-overflow:ellipsis; overflow:hidden;">
				<c:forEach items="${datammmm}" var="data">
						<c:if test="${data.targets.id==60 }">
							${data.value }
							<c:set var="sum7" value="${sum7+data.value}"/>
						</c:if>
					</c:forEach>
			</td>
		</tr>
</c:forEach>
</table>
</div>
<table class="stable" cellpadding="0" cellspacing="1" id="ccccc"><tr>
<th width="5%"></th>
<th width="11%">合计</th>
<th width="10%"><fmt:formatNumber value="${sum1 }" pattern="0.0000"/></th>
<th width="10%"><fmt:formatNumber value="${sum2 }" pattern="0.0000"/></th>
<th width="10%"><fmt:formatNumber value="${sum3 }" pattern="0.0000"/></th>
<th width="10%"><fmt:formatNumber value="${sum4 }" pattern="0.0000"/></th>
<th width="10%"><fmt:formatNumber value="${sum5 }" pattern="0.0000"/></th>
<th width="10%"><fmt:formatNumber value="${sum6}" pattern="0.0000"/></th>
<th width="10%"><fmt:formatNumber value="${sum7 }" pattern="0.0000"/></th>
</tr>
</table>
<script type="text/javascript">
	var seewidth=document.getElementById("bbbbb").clientWidth;
	$('#aaaaa').width(seewidth);
	$('#ccccc').width(seewidth);
	var h=$(window).height();
	$('#scdiv').height(h-240);
</script>
</div>
</div></div>
</div>
</body>
</html>