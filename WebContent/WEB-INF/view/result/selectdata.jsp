<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
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
.borderbom1{ border-bottom:0px; border-right:0px; border-top: 0px;} 
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
		document.getElementById('ffff').action="Res_SelectData?p=104&c=0";
		document.getElementById('ffff').submit();
	}
	function exportexcel() {
		document.getElementById('ffff').action="Res_ExportData?p=104&c=0";
		document.getElementById('ffff').submit();
	}
	
</script>
<script type="text/javascript">
function isNumFormat2(s){
	var x=s.indexOf('.')+1;
	var y=s.indexOf('%')+1;	
	if(x>1&&y>6){
		var aNew;
		aNew=s.substring(0,x+2)+"%";
		return aNew;
	}else if(IsNum(s)){
		var aNew;
	    aNew = Math.round(s*100)/100;
		return aNew;
	}else{
		return s;
	}
}
function isNumFormat0(s){
	if(IsNum(s)){
		var aNew;
	    aNew =Math.round(s);
		return aNew;
	}else{
		return s;
	}
}
//判断是否为数字
function IsNum(s){
	if (s!=null && s!=""){
		return !isNaN(s);
	}
	return false;
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
经营管理指标
<div class="bodytitleright"></div>
</div>
<div id="rightContent">
<div style="width: 100%; text-align: center; height:40px; line-height: 40px;">
<form action="" method="post" id="ffff">
			公司类型：<select name="type">
				<option value="${depttype.id }">
					<c:forEach items="${depttypeList }" var="de1">
						<c:if test="${de1.id==type }">${de1.type }</c:if>
					</c:forEach>
				</option>
				<c:forEach items="${depttypeList }" var="de">
					<option value="${de.id }">${de.type }</option>
				</c:forEach>
			</select>
			月份：<input readonly="readonly"
			onclick="WdatePicker({dateFmt:'yyyy-MM'})" type="text"
			class="input_text" name="month" value="${month }"/>
			<a href="javascript:void(0)" onclick="chazhao()"
			class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-search'">查找</a>
			<a href="javascript:void(0)" onclick="exportexcel()"
			class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-print'">导出</a>
</form>
</div>
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
	<c:if test="${tar.key.id!=12}">
	<tr><td width="30%" class="bordertop" onmouseover="m1('${tar.key.id}')" onmouseout="q()" style=" overflow: hidden;text-overflow:ellipsis;" id="valuediv${tar.key.id}">${tar.key.value}</td><td class="bordertop" width="70%">
		<table style="white-space:nowrap;table-layout: fixed;">
		<c:forEach items="${tar.value }" var="tar1" varStatus="abd">
			<c:if test="${fn:length(tar.value)==1}">
				<tr><td width="100%" class="borderbom1" onmouseover="m1('${tar1.id}')" onmouseout="q()" style=" overflow: hidden;text-overflow:ellipsis;" id="valuediv${tar1.id}">${tar1.value}</td></tr>
			</c:if>
			<c:if test="${fn:length(tar.value)>1}">
				<c:if test="${!abd.last}">
					<tr><td width="100%" class="bordertop1" onmouseover="m1('${tar1.id}')" onmouseout="q()" style=" overflow: hidden;text-overflow:ellipsis;" id="valuediv${tar1.id}">${tar1.value}</td></tr>
				</c:if>
				<c:if test="${abd.last }">
					<tr><td width="100%" class="borderbom" onmouseover="m1('${tar1.id}')" onmouseout="q()" style=" overflow: hidden;text-overflow:ellipsis;" id="valuediv${tar1.id}">${tar1.value}</td></tr>
				</c:if>
			</c:if>
		</c:forEach>
		</table>
	</td></tr></c:if>
</c:forEach>
</table> 
</div> 
</div> 
<div class="t_r"> 
<div class="t_r_t" id="t_r_t"> 
<div class="t_r_title"> 
<table style="white-space:nowrap;table-layout: fixed; float: left;" id="aaaaa"> 
<tr> 
<c:forEach items="${deptlist }" var="dept" varStatus="d">
		<th width="150px" style=" overflow: hidden;text-overflow:ellipsis;">${dept.dept_name }</th> 
</c:forEach>
<th width="172px" id="ccccc" style=" overflow: hidden;text-overflow:ellipsis;">合计</th>
</tr> 
</table> 
</div> 
</div>
<div class="t_r_content" id="t_r_content" onscroll="aa()"> 
<table style="white-space:nowrap;table-layout: fixed;" id="bbbbb">
<c:forEach items="${tarlist }" var="tar">
	<c:if test="${tar.key.id!=12 and tar.key.id!=5}">
		<c:forEach items="${tar.value }" var="tar1" varStatus="abd">
			<tr><c:set var="sum" value="0"/>
			<c:forEach items="${deptlist }" var="dept" varStatus="d">
						<c:if test="${!abd.first}">
							<td width="150px" style=" overflow: hidden;text-overflow:ellipsis;">
								<c:forEach items="${dataList }" var="datakey">
									<c:if test="${datakey.key.id==tar1.id }">
										<c:forEach items="${datakey.value }" var="data">
											<c:if test="${data.dept.id==dept.id }">
											<script type="text/javascript">
												document.write(isNumFormat2('${data.value}'));
											</script>
												<c:if test="${tar1.sum==1 }">
													<c:set var="sum" value="${data.value+sum}"/>
												</c:if>
											</c:if>
										</c:forEach>
									</c:if>
								</c:forEach>
							</td>
						</c:if>
						<c:if test="${abd.first}">
							<td width="150px" class="bordertop" style=" overflow: hidden;text-overflow:ellipsis;">
								<c:forEach items="${dataList }" var="datakey">
									<c:if test="${datakey.key.id==tar1.id }">
										<c:forEach items="${datakey.value }" var="data">
											<c:if test="${data.dept.id==dept.id }">
												<script type="text/javascript">
													document.write(isNumFormat2('${data.value}'));
												</script>
												<c:if test="${tar1.sum==1 }">
													<c:set var="sum" value="${data.value+sum}"/>
												</c:if>
											</c:if>
										</c:forEach>
									</c:if>
								</c:forEach>
							</td>
						</c:if>
					</c:forEach> 
					<c:if test="${!abd.first}">
					<td width="150px;">
						<c:if test="${tar1.sum==1 }">
							<fmt:formatNumber value="${sum }" pattern="0.00"/>
						</c:if>
					</td>
					</c:if>
					<c:if test="${abd.first}">
					<td width="150px;" class="bordertop">
						<c:if test="${tar1.sum==1 }">
							<fmt:formatNumber value="${sum }" pattern="0.00"/>
						</c:if>
					</td>
					</c:if>
					</tr>
		</c:forEach>
		</c:if>
	<c:if test="${tar.key.id==5}">
		<c:forEach items="${tar.value }" var="tar1" varStatus="abd">
			<tr><c:set var="sum" value="0"/>
			<c:forEach items="${deptlist }" var="dept" varStatus="d">
						<c:if test="${!abd.first}">
							<td width="150px" style=" overflow: hidden;text-overflow:ellipsis;">
								<c:forEach items="${dataList }" var="datakey">
									<c:if test="${datakey.key.id==tar1.id }">
										<c:forEach items="${datakey.value }" var="data">
											<c:if test="${data.dept.id==dept.id }">
												<script type="text/javascript">
													document.write(isNumFormat0('${data.value}'));
												</script>
												<c:if test="${tar1.sum==1 }">
													<c:set var="sum" value="${data.value+sum}"/>
												</c:if>
											</c:if>
										</c:forEach>
									</c:if>
								</c:forEach>
							</td>
						</c:if>
						<c:if test="${abd.first}">
							<td width="150px" class="bordertop" style=" overflow: hidden;text-overflow:ellipsis;">
								<c:forEach items="${dataList }" var="datakey">
									<c:if test="${datakey.key.id==tar1.id }">
										<c:forEach items="${datakey.value }" var="data">
											<c:if test="${data.dept.id==dept.id }">
												<script type="text/javascript">
													document.write(isNumFormat0('${data.value}'));
												</script>
												<c:if test="${tar1.sum==1 }">
													<c:set var="sum" value="${data.value+sum}"/>
												</c:if>
											</c:if>
										</c:forEach>
									</c:if>
								</c:forEach>
							</td>
						</c:if>
					</c:forEach> 
					<c:if test="${!abd.first}">
					<td width="150px;">
						<c:if test="${tar1.sum==1 }">
							<fmt:formatNumber value="${sum }" pattern="0"/>
						</c:if>
					</td>
					</c:if>
					<c:if test="${abd.first}">
					<td width="150px;" class="bordertop">
						<c:if test="${tar1.sum==1 }">
							<fmt:formatNumber value="${sum }" pattern="0"/>
						</c:if>
					</td>
					</c:if>
					</tr>
		</c:forEach>
		</c:if>
</c:forEach>
</table> 
</div> 
</div> </div> 
<br>
</div>
</div>
</div>
<div id="div1" style="position: fixed; width:auto; height:auto; word-wrap:break-word; border:1px solid #ccc; display:none; background-color:#F2FAFF; padding:10px 10px 10px 10px;"></div>
<script language="javascript" type="text/javascript">
function m1(a){
	document.getElementById("div1").style.display="block";
	document.getElementById("div1").style.top=event.clientY+"px";
	document.getElementById("div1").style.left=event.clientX+"px";
	document.getElementById("div1").innerHTML=document.getElementById("valuediv"+a).innerHTML;
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