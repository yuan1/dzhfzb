<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script type="text/javascript" src="js/jquery-1.8.3.js"></script>
<script type="text/javascript">
	function submitf() {
		var jx=0;
		var ob=document.getElementById("ff").getElementsByTagName("input");
		for(var j=0;j<ob.length;j++)
		{
		  if(ob[j].value=="")
		  {
			 alert("内容填写不完整");
		     return false;
		  }
		}
		document.getElementById("ffffa").action="Data_addDataDo2?p=102";
		document.getElementById("ffffa").submit();
	}
	
	var savadatado = function(tar_id) {
		$(tar_id).form('submit', {
  			url : "Data_addDataDo1",
  			onSubmit : function() {
  			},
  		});
	};
	
	function totab(){
            window.event.keyCode=9;  
        }
</script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@include file="../common/common_include.jsp"%>
</head>
<body>
<div id="bodycontent">
<div class="topnav"><%@include file="../common/header.jsp"%>
</div>
<div id="main">
<div id="main_left">
</div>
<div id="main_right">
<div class="bodytitle">
<div class="bodytitleleft"></div>
<div class="bodytitletxt">指标管理</div>
 ${month}期指标录入
<div class="bodytitleright"></div>
</div>
<div id="rightContent">
<c:if test="${l!=0 }">
</c:if>
<table class="stable1" cellpadding="0" cellspacing="1" id="ff">
	<c:if test="${zhuangtai=='开放' }">
	<c:if test="${status=='2' }">
		<tr><td>本月数据已经提交审核，不能继续添加或修改。</td></tr>
	</c:if>
	<c:if test="${status!='2' }">
	<c:forEach items="${tarList}" var="tar">
		<c:if test="${tar.parentid==null}">
			<tr><th width="80px">${tar.value }</th>
			<td>
				<table class="stable1" cellpadding="0" cellspacing="1">
				<tr>
					<c:set var="a" value="0"/>
					<c:forEach items="${tarList}" var="tar1">
						<c:if test="${tar1.parentid==tar.id and tar1.parentid!=12}">
							<td width="41%">${tar1.value }
								<c:if test="${fn:length(tar1.tar_prompt)>0 }">
							[<font color="red">*</font>${tar1.tar_prompt }]
							</c:if>
							</td>
							<td width="9%">
							<c:if test="${tar1.isgongshi==0 }">
								<form action="Data_addDataDo1" id="ffff${tar1.id}" method="post" onsubmit="return false;totab();">
									<c:set var="asd" value="0" />
									<c:forEach items="${dataList }" var="dat">
										<c:if test="${dat.targets.id==tar1.id }">
											<input type="text" name="datainfo.value" value="${dat.value }" onblur="savadatado(ffff${tar1.id})">
											<c:set var="asd" value="1"/>
										</c:if>
									</c:forEach>
									<c:if test="${asd==0 }">
										<input type="text" name="datainfo.value" value="${tar1.indexvalue }" onblur="savadatado(ffff${tar1.id})">
									</c:if>
									<input type="hidden" name="datainfo.month" value="${month }">
									<input type="hidden" name="datainfo.targets.id" value="${tar1.id}">
									<input type="hidden" name="datainfo.dept.id" value="${deptid}">
								</form>
							</c:if>
							<c:if test="${tar1.isgongshi==1 }">
								待计算
							</c:if>
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
							<form action="Data_addDataDo1" id="ffff${tar1.id}" method="post">
								<c:set var="asd" value="0" />
								<c:forEach items="${dataList }" var="dat">
									<c:if test="${dat.targets.id==tar1.id }">
										<textarea rows="5" cols="" style="width: 100%" name="datainfo.value" onblur="savadatado(ffff${tar1.id})">${dat.value }</textarea>
										<c:set var="asd" value="1"/>
									</c:if>
								</c:forEach>
								<c:if test="${asd==0 }">
										<textarea rows="5" cols="" style="width: 100%" name="datainfo.value" onblur="savadatado(ffff${tar1.id})">${tar1.indexvalue }</textarea>
								</c:if>
								<input type="hidden" name="datainfo.month" value="${month }">
								<input type="hidden" name="datainfo.targets.id" value="${tar1.id}">
								<input type="hidden" name="datainfo.dept.id" value="${deptid}">
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
	<form action="Data_addDataDo2?p=102" method="post" id="ffffa">
		<input type="hidden" name="month" value="${month }">
		<input type="hidden" name="deptid" value="${deptid }">
		<input type="hidden" name="l" value="${l }">
	</form>
			<a href="javascript:void(0)" onclick="submitf()"
				 class="easyui-linkbutton" data-options="iconCls:'icon-ok'">
				 保存</a>
	</td>
	</tr>
	 </c:if></c:forEach>
	</c:if>
	</c:if>
	<c:if test="${zhuangtai=='关闭' }">
		<tr>
			<td>
				当前模块未开放，不能进行录入和修改。
			</td>
		</tr>
	</c:if>
</table>
<br>
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
</body>
</html>