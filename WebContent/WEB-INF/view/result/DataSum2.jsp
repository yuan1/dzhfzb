<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="js/echarts.js" charset="UTF-8"></script>
<%@include file="../common/common_include.jsp"%>
<script type="text/javascript">
	function chazhao() {
		document.getElementById('ffff').action = "Res_SelectDataSum?p=104";
		document.getElementById('ffff').submit();
	}
	function exportexcel() {
		document.getElementById('ffff').action = "Res_ExportDataSum?p=104";
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
					<div
						style="width: 100%; text-align: center; height: 40px; line-height: 40px;">
						<form action="" method="post" id="ffff">
							月份：<input readonly="readonly"
								onclick="WdatePicker({dateFmt:'yyyy-MM'})" type="text"
								class="input_text" name="month" value="${month }" /> <a
								href="javascript:void(0)" onclick="chazhao()"
								class="easyui-linkbutton"
								data-options="plain:true,iconCls:'icon-search'">查找</a> <a
								href="javascript:void(0)" onclick="exportexcel()"
								class="easyui-linkbutton"
								data-options="plain:true,iconCls:'icon-print'">导出</a>
						</form>
					</div>
					<c:set var="sum16" value="0" />
					<c:set var="sum117" value="0" />
					<c:set var="sum52" value="0" />
					<c:set var="sum53" value="0" />
					<c:set var="sum54" value="0" />
					<c:set var="sum60" value="0" />
					<c:set var="sum3" value="0" />
					<c:set var="sum4" value="0" />
					<c:set var="sum5" value="0" />
					<c:set var="sum6" value="0" />
					<c:set var="sum7" value="0" />
					<div style="width: 100%; min-width: 930px;">
					<table class="easyui-datagrid" data-options="fitColumns:true,singleSelect:true">						
							<thead  data-options="frozen:true">
								<tr>
									<th data-options="field:'xh',sortable:true">序号</th>
									<th data-options="field:'gsmc',sortable:true">公司名称</th>
								</tr>
							</thead>
							<thead>
								<tr>
									<c:forEach items="${tarlist }" var="tar">
										<th data-options="field:'${tar.id}',sortable:true">${tar.value }</th>
									</c:forEach>
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${deptlist}" var="dept" varStatus="asd">
									<tr>
										<td>${asd.index+1}</td>
										<td>${dept.dept_name}</td>
										<c:set var="datammmm" value="" />
										<c:forEach items="${dataList}" var="datakey">
											<c:if test="${datakey.key.id==dept.id }">
												<c:set var="datammmm" value="${datakey.value }" />
											</c:if>
										</c:forEach>
										<td><c:forEach items="${datammmm}" var="data">
												<c:if test="${data.targets.id==16 }">
					${data.value }
					<c:set var="sum16" value="${sum16+data.value}" />
												</c:if>
											</c:forEach></td>
										<td><c:forEach items="${datammmm}" var="data">
												<c:if test="${data.targets.id==117 }">
					${data.value }
					<c:set var="sum117" value="${sum117+data.value}" />
												</c:if>
											</c:forEach></td>
										<td><c:forEach items="${datammmm}" var="data">
												<c:if test="${data.targets.id==52 }">
					${data.value }
					<c:set var="sum52" value="${sum52+data.value}" />
												</c:if>
											</c:forEach></td>
										<td><c:forEach items="${datammmm}" var="data">
												<c:if test="${data.targets.id==53 }">
					${data.value }
					<c:set var="sum53" value="${sum53}" />
												</c:if>
											</c:forEach></td>
										<td><c:forEach items="${datammmm}" var="data">
												<c:if test="${data.targets.id==54 }">
					${data.value }
					<c:set var="sum54" value="${sum54+data.value}" />
												</c:if>
											</c:forEach></td>
										<td><c:forEach items="${datammmm}" var="data">
												<c:if test="${data.targets.id==60 }">
					${data.value }
					<c:set var="sum54" value="${sum60+data.value}" />
												</c:if>
											</c:forEach></td>
										<td><c:forEach items="${datammmm}" var="data">
												<c:if test="${data.targets.id==91 }">
					${data.value }
					<c:set var="sum3" value="${sum3+data.value}" />
												</c:if>
											</c:forEach></td>
										<td><c:forEach items="${datammmm}" var="data">
												<c:if test="${data.targets.id==117 }">
					${data.value }
					<c:set var="sum4" value="${sum4+data.value}" />
												</c:if>
											</c:forEach></td>
										<td><c:forEach items="${datammmm}" var="data">
												<c:if test="${data.targets.id==57 }">
					${data.value }
					<c:set var="sum5" value="${sum5+data.value}" />
												</c:if>
											</c:forEach></td>
										<td><c:forEach items="${datammmm}" var="data">
												<c:if test="${data.targets.id==59 }">
					${data.value }
					<c:set var="sum6" value="${sum6+data.value}" />
												</c:if>
											</c:forEach></td>
										<td><c:forEach items="${datammmm}" var="data">
												<c:if test="${data.targets.id==60 }">
					${data.value }
					<c:set var="sum7" value="${sum7+data.value}" />
												</c:if>
											</c:forEach></td>
									</tr>
								</c:forEach>
								<tr>
									<th colspan="2">合计</th>
									<th width="150px"><fmt:formatNumber value="${sum16 }"
											pattern="0.0000" /></th>
									<th width="150px"><fmt:formatNumber value="${sum117 }"
											pattern="0.0000" /></th>
									<th width="150px"><fmt:formatNumber value="${sum52 }"
											pattern="0.0000" /></th>
									<th width="150px"></th>
									<th width="150px"><fmt:formatNumber value="${sum54 }"
											pattern="0.0000" /></th>
									<th width="150px"><fmt:formatNumber value="${sum60 }"
											pattern="0.0000" /></th>
									<th width="150px"><fmt:formatNumber value="${sum3 }"
											pattern="0.0000" /></th>
									<th width="150px"><fmt:formatNumber value="${sum4 }"
											pattern="0.0000" /></th>
									<th width="150px"><fmt:formatNumber value="${sum5 }"
											pattern="0.0000" /></th>
									<th width="150px"><fmt:formatNumber value="${sum6}"
											pattern="0.0000" /></th>
									<th width="150px"><fmt:formatNumber value="${sum7 }"
											pattern="0.0000" /></th>
								</tr>
							</tbody>
						</table>

					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>