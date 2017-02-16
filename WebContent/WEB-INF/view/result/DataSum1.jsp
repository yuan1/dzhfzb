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
<style> 
table{border-collapse:collapse;border-spacing:0px; width:100%; border:#000 solid 0px;} 
table td{border:1px solid #000;height:25px; text-align:center; border-left:0px; overflow: hidden;text-overflow:ellipsis;} 
table th{ background:#F2FAFF; color:#000000; border:#000 solid 1px; white-space:nowrap; height:21px; border-top:0px;border-left:0px;} 
.t_left{width:24%; height:auto; float:left;border-top:1px solid #000;border-left:1px solid #000;} 
.t_r_content{width:100%; height:420px; background:#fff; overflow:auto; border:none; } 
.t_r table{width:99%} 
.t_r_title{width:99%}
.cl_freeze{height:400px;overflow:hidden; width:100%;} 
.t_r{width:75%; height:auto; float:left;border-top:1px solid #000; border-right:#000 solid 1px;} 
.t_r_t{width:100%; overflow:hidden;} 
.bordertop{ border-top:0px;} 
.bordertop1{ border-right:0px; border-top:0px;} 
.borderbom{ border-bottom:0px; border-right:0px;} 
.bordermid{ border-right:0px;}  
</style> 
<script>
function aa(){ 
var a=document.getElementById("t_r_content").scrollTop; 
var b=document.getElementById("t_r_content").scrollLeft; 
document.getElementById("cl_freeze").scrollTop=a; 
document.getElementById("t_r_t").scrollLeft=b; 
} 
</script> 
<script type="text/javascript">
	function chazhao() {
		document.getElementById('ffff').action="Res_SelectDataSum1?p=104";
		document.getElementById('ffff').submit();
	}
	function exportexcel() {
		document.getElementById('ffff').action="Res_ExportDataSum1?p=104";
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
公司累计
<div class="bodytitleright"></div>
</div>
<div id="rightContent">
<form action="" method="post" id="ffff">
<table class="stable" cellpadding="0" cellspacing="1">
	<tr>
		<td>
			部门：<input class="input_text"
			style="width: 200px;" name="deptid" id="cc" value="${deptid }"/>
			开始月份：<input readonly="readonly"
			onclick="WdatePicker({dateFmt:'yyyy-MM'})" type="text"
			class="input_text" name="stime" value="${stime }"/>
			结束月份：<input readonly="readonly"
			onclick="WdatePicker({dateFmt:'yyyy-MM'})" type="text"
			class="input_text" name="etime" value="${etime }"/>
			<a href="javascript:void(0)" onclick="chazhao()"
			class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-search'">查找</a>
			<a href="javascript:void(0)" onclick="exportexcel()"
			class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-print'">导出</a>
		</td>
	</tr>
</table>
</form>
<div style="width:100%;min-width: 930px;"> 
<div class="t_left"> 
<div style="width:100%;"> 
<table> 
<tr> 
<th style="width:30%">指标类别</th> 
<th style="width:70%">指标名称</th> 
</tr> 
</table> 
</div> 
<div class="cl_freeze" id="cl_freeze"> 
<table style="white-space:nowrap;table-layout: fixed;">
<c:forEach items="${tarlist }" var="tar">
	<tr><td width="30%" class="bordertop" style=" overflow: hidden;text-overflow:ellipsis;" onmouseover="m1('${tar.key.id}')" onmouseout="q()" id="valuediv${tar.key.id }">${tar.key.value}</td>
	<td class="bordertop" width="70%">
		<table style="white-space:nowrap;table-layout: fixed;">
		<c:forEach items="${tar.value }" var="tar1" varStatus="abd">
			<c:if test="${!abd.last}">
				<tr><td class="bordertop1" onmouseover="m1(${tar1.id})" onmouseout="q()" style=" overflow: hidden;text-overflow:ellipsis;" id="valuediv${tar1.id }">
					<a href="Res_tx1?stime=${stime }&etime=${etime}&deptid=${deptid}&tarid=${tar1.id}">${tar1.value}</a>
				</td></tr>
			</c:if>
			<c:if test="${abd.last }">
				<tr><td class="borderbom" onmouseover="m1(${tar1.id})" onmouseout="q()" style=" overflow: hidden;text-overflow:ellipsis;" id="valuediv${tar1.id }">
					<a href="Res_tx1?stime=${stime }&etime=${etime}&deptid=${deptid}&tarid=${tar1.id}">${tar1.value}</a>
				</td></tr>
			</c:if>
		</c:forEach>
		</table>
	</td></tr>
</c:forEach>
</table> 
</div> 
</div> 
<div class="t_r"> 
<div class="t_r_t" id="t_r_t"> 
<div class="t_r_title"> 
<table style="white-space:nowrap;table-layout: fixed; float: left;" id="aaaaa"> 
<tr> 
<c:forEach items="${monthlist }" var="month" varStatus="d">
	<th width="150px">${month}</th> 
</c:forEach>
<th width="172px" id="ccccc">累计</th>
</tr> 
</table> 
</div> 
</div>
<div class="t_r_content" id="t_r_content" onscroll="aa()"> 
<table style="white-space:nowrap;table-layout: fixed;" id="bbbbb"> 
	<c:forEach items="${tarlist }" var="tar">
		<c:forEach items="${tar.value }" var="tar1" varStatus="abd">
			<tr>
			<c:set var="s" value="0"/>
			<c:forEach items="${monthlist }" var="month" varStatus="d">
						<c:if test="${!abd.first}">
							<td width="150px" style=" overflow: hidden;text-overflow:ellipsis;">
								<c:forEach items="${dataList }" var="datakey">
									<c:if test="${datakey.key.id==tar1.id }">
										<c:forEach items="${datakey.value }" var="data">
											<c:if test="${data.month==month }">
												<c:if test="${tar1.parentid==12}">
												<div style="height:100%; width:100%; line-height: 25px;" onmouseover="m2(${data.id})" onmouseout="q()" id="valuediv1${data.id }">
													${data.value}
												</div>
												</c:if>
												<c:if test="${tar1.parentid!=12}">
													${data.value}
												</c:if>
												<c:if test="${tar1.sum=='1' }">
													<c:set var="s" value="${s+data.value}"/>
												</c:if>
											</c:if>
										</c:forEach>
									</c:if>
								</c:forEach>
							</td>
						</c:if>
						<c:if test="${abd.first}">
							<td width="150px" class="bordertop" style="overflow: hidden;text-overflow:ellipsis;">
								<c:forEach items="${dataList }" var="datakey">
									<c:if test="${datakey.key.id==tar1.id }">
										<c:forEach items="${datakey.value }" var="data">
											<c:if test="${data.month==month }">
												<c:if test="${tar1.parentid==12}">
												<div style="height:100%; width:100%; line-height: 25px;" onmouseover="m2(${data.id})" onmouseout="q()" id="valuediv1${data.id }">
													${data.value}
												</div>
												</c:if>
												<c:if test="${tar1.parentid!=12}">
													${data.value}
												</c:if>
												<c:if test="${tar1.sum=='1' }">
													<c:set var="s" value="${s+data.value}"/>
												</c:if>
											</c:if>
										</c:forEach>
									</c:if>
								</c:forEach>
							</td>
						</c:if>
					</c:forEach>
						<c:if test="${abd.first}">
						<td width="150px"  class="bordertop" style=" overflow: hidden;text-overflow:ellipsis;">
							<c:if test="${tar1.sum=='1' }">
								<fmt:formatNumber value="${s }" pattern="0.00"/>
							</c:if>
						</td>
					</c:if>
					<c:if test="${!abd.first}">
						<td width="150px" style=" overflow: hidden;text-overflow:ellipsis;">
							<c:if test="${tar1.sum=='1' }">
								<fmt:formatNumber value="${s }" pattern="0.00"/>
							</c:if>
						</td>
					</c:if>
					</tr>
		</c:forEach>
	</c:forEach>
</table>
</div>
<br>
</div>
</div>
</div>
</div>
</div>
</div>
<script type="text/javascript">
$(function() {
	$('#cc').combotree({
		url : 'Sym_findDeptTreeForScope?role_id=${role}'
	});
});
</script>
<div id="div1" style="position: fixed; width:auto; height:auto; word-wrap:break-word; border:1px solid #ccc; display:none; background-color:#F2FAFF; padding:10px 10px 10px 10px;"></div>
<script language="javascript" type="text/javascript">
function m1(a){
	document.getElementById("div1").style.display="block";
	document.getElementById("div1").style.top=event.clientY+"px";
	document.getElementById("div1").style.left=event.clientX+"px";
	document.getElementById("div1").innerHTML=document.getElementById("valuediv"+a).innerHTML;
	} 
function m2(a){
	document.getElementById("div1").style.display="block";
	document.getElementById("div1").style.top=event.clientY+"px";
	document.getElementById("div1").style.left=event.clientX+"px";
	document.getElementById("div1").innerHTML=document.getElementById("valuediv1"+a).innerHTML;
	} 
function q(){
document.getElementById("div1").style.display="none";
} 
</script>
<script type="text/javascript">
	var seewidth=document.getElementById("t_r_content").clientWidth;
	var h=$(window).height();
	if(seewidth<$('#bbbbb').width()){
		$('#aaaaa').width($('#bbbbb').width()+20);
		$('#cl_freeze').height(h-226);
		$('#t_r_content').height(h-209);
	}
	else{
		$('#aaaaa').width($('#bbbbb').width());
		$('#ccccc').width(150);
		$('#cl_freeze').height(h-200);
		$('#t_r_content').height(h-200);
	}
</script>
</body>
</html>