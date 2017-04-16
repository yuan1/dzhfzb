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
.t_left{width:20%; height:auto; float:left;border-top:1px solid #000;border-left:1px solid #000;} 
.t_r_content{width:100%; height:420px; background:#fff; overflow:auto; border:none; } 
.t_r table{width:99%} 
.t_r_title{width:99%}
.cl_freeze{height:400px;overflow:hidden; width:100%;} 
.t_r{width:79%; height:auto; float:left;border-top:1px solid #000; border-right:#000 solid 1px;} 
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
var sum53=0
function isNumFormat2(s,n){
	var x=s.indexOf('.')+1;
	var y=s.indexOf('%')+1;	
	if(x>1){
		var aNew;
		aNew=s.substring(0,x+2);
		if(n==53){
			sum53+=parseInt(aNew);
		}
		return aNew+'%';
	}else if(IsNum(s)){
		var aNew;
	    aNew = Math.round(s*100)/100;
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
<div style="width: 100%; text-align: center; height:40px; line-height: 40px;">
<form action="" method="post" id="ffff">
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
	<th style="width:30%">序号</th>
	<th style="width:70%">公司名称</th>
</tr>
</table>
</div> 
<div class="cl_freeze" id="cl_freeze">
<table style="white-space:nowrap;table-layout: fixed;">
<c:set var="sum16" value="0"/>
<c:set var="sum117" value="0"/>
<c:set var="sum52" value="0"/>
<c:set var="sum53" value="0"/>
<c:set var="sum54" value="0"/>
<c:set var="sum60" value="0"/>
<c:set var="sum61" value="0"/>
<c:set var="sum62" value="0"/>
<c:set var="sum63" value="0"/>
<c:set var="sum66" value="0"/>
<c:set var="sum69" value="0"/>
<c:set var="sum70" value="0"/>
<c:set var="sum71" value="0"/>
<c:set var="sum72" value="0"/>
<c:set var="sum73" value="0"/>
<c:set var="sum77" value="0"/>
<c:set var="sum80" value="0"/>
<c:set var="sum142" value="0"/>
<c:set var="sum85" value="0"/>
<c:set var="sum92" value="0"/>
<c:set var="sum93" value="0"/>
<c:set var="sum86" value="0"/>
<c:set var="sum87" value="0"/>
<c:set var="sum88" value="0"/>
<c:set var="sum89" value="0"/>
<c:set var="sum90" value="0"/>
<c:set var="sum91" value="0"/>
<c:forEach items="${deptlist}" var="dept" varStatus="asd">
<tr>
<td width="30%" class="bordertop" style=" overflow: hidden;text-overflow:ellipsis;">${asd.index+1}</td>
<td class="bordertop" width="70%">
		<table style="white-space:nowrap;table-layout: fixed;">
			<tr><td width="100%" class="borderbom1"  style=" overflow: hidden;text-overflow:ellipsis;" >${dept.dept_name}</td></tr>
		</table>
	</td></tr>
</c:forEach>
<tr>
<th width="100%" colspan="2" class="bordertop" style=" overflow: hidden;text-overflow:ellipsis;">合计</th>
</tr>
</table> 
</div> 
</div> 
<div class="t_r"> 
<div class="t_r_t" id="t_r_t"> 
<div class="t_r_title"> 
<table style="white-space:nowrap;table-layout: fixed; float: left;" id="aaaaa"> 
<tr> 
	<c:forEach items="${tarlist }" var="tar" begin="0" end="${tarsize-2 }">
		<th width="150px" style=" overflow: hidden;text-overflow:ellipsis;"><a href="Res_tx?month=${month }&tarid=${tar.id}">${tar.value }</a></th> 
	</c:forEach>
	<th width="172px" id="ccccc" style=" overflow: hidden;text-overflow:ellipsis;"><a href="Res_tx?month=${month }&tarid=${tarlist[tarsize-1].id}">${tarlist[tarsize-1].value}</a></th>
</table> 
</div> 
</div>
<div class="t_r_content" id="t_r_content" onscroll="aa()"> 
<table style="white-space:nowrap;table-layout: fixed;" id="bbbbb">
<c:forEach items="${deptlist}" var="dept" varStatus="asd">
			<tr>
			<c:set var="datammmm" value=""/>
			<c:forEach items="${dataList}" var="datakey">
				<c:if test="${datakey.key.id==dept.id }">
					<c:set var="datammmm" value="${datakey.value }"/>	
				</c:if>
			</c:forEach>
			<td width="150px" style=" overflow: hidden;text-overflow:ellipsis;">
				<c:forEach items="${datammmm}" var="data">
						<c:if test="${data.targets.id==16 }">
							${data.value }
							<c:set var="sum16" value="${sum16+data.value}"/>
						</c:if>
					</c:forEach>
			</td>
			<td width="150px" style=" overflow: hidden;text-overflow:ellipsis;">
				<c:forEach items="${datammmm}" var="data">
						<c:if test="${data.targets.id==117 }">
							${data.value }
							<c:set var="sum117" value="${sum117+data.value}"/>
						</c:if>
					</c:forEach>
			</td>
			<td width="150px" style=" overflow: hidden;text-overflow:ellipsis;">
				<c:forEach items="${datammmm}" var="data">
						<c:if test="${data.targets.id==52 }">
							${data.value }
							<c:set var="sum52" value="${sum52+data.value}"/>
						</c:if>
					</c:forEach>
			</td>			
			<td width="150px" style=" overflow: hidden;text-overflow:ellipsis;">
				<c:forEach items="${datammmm}" var="data">
						<c:if test="${data.targets.id==53 }">
							<script type="text/javascript">
								document.write(isNumFormat2('${data.value}',53));
							</script>
							<c:set var="sum53" value="${sum53}"/>
						</c:if>
					</c:forEach>
			</td>			
			<td width="150px" style=" overflow: hidden;text-overflow:ellipsis;">
				<c:forEach items="${datammmm}" var="data">
						<c:if test="${data.targets.id==54 }">
							${data.value }
							<c:set var="sum54" value="${sum54+data.value}"/>
						</c:if>
					</c:forEach>
			</td>
			<td width="150px" style=" overflow: hidden;text-overflow:ellipsis;">
				<c:forEach items="${datammmm}" var="data">
						<c:if test="${data.targets.id==60 }">
							${data.value }
							<c:set var="sum60" value="${sum60+data.value}"/>
						</c:if>
					</c:forEach>
			</td>
			<td width="150px" style=" overflow: hidden;text-overflow:ellipsis;">
				<c:forEach items="${datammmm}" var="data">
						<c:if test="${data.targets.id==61 }">
							${data.value }
						</c:if>
					</c:forEach>
			</td>
<td width="150px" style=" overflow: hidden;text-overflow:ellipsis;">
	<c:forEach items="${datammmm}" var="data">
		<c:if test="${data.targets.id==62 }">
			${data.value }
		</c:if>
	</c:forEach>
</td>
<td width="150px" style=" overflow: hidden;text-overflow:ellipsis;">
	<c:forEach items="${datammmm}" var="data">
		<c:if test="${data.targets.id==63 }">
			${data.value }
			<c:if test="${data.value!='无' }">
				<c:set var="sum63" value="${sum63+data.value}"/>
			</c:if>
		</c:if>
	</c:forEach>
</td>
<td width="150px" style=" overflow: hidden;text-overflow:ellipsis;">
	<c:forEach items="${datammmm}" var="data">
		<c:if test="${data.targets.id==66 }">
			${data.value }
			<c:set var="sum66" value="${sum66+data.value}"/>
		</c:if>
	</c:forEach>
</td>
<td width="150px" style=" overflow: hidden;text-overflow:ellipsis;">
	<c:forEach items="${datammmm}" var="data">
		<c:if test="${data.targets.id==69 }">
			${data.value }
			<c:set var="sum69" value="${sum69+data.value}"/>
		</c:if>
	</c:forEach>
</td>
<td width="150px" style=" overflow: hidden;text-overflow:ellipsis;">
	<c:forEach items="${datammmm}" var="data">
		<c:if test="${data.targets.id==70 }">
			${data.value }
			<c:set var="sum70" value="${sum70+data.value}"/>
		</c:if>
	</c:forEach>
</td>
<td width="150px" style=" overflow: hidden;text-overflow:ellipsis;">
	<c:forEach items="${datammmm}" var="data">
		<c:if test="${data.targets.id==71 }">
			${data.value }
			<c:set var="sum71" value="${sum71+data.value}"/>
		</c:if>
	</c:forEach>
</td>
<td width="150px" style=" overflow: hidden;text-overflow:ellipsis;">
	<c:forEach items="${datammmm}" var="data">
		<c:if test="${data.targets.id==72 }">
			${data.value }
			<c:set var="sum72" value="${sum72+data.value}"/>
		</c:if>
	</c:forEach>
</td>
<td width="150px" style=" overflow: hidden;text-overflow:ellipsis;">
	<c:forEach items="${datammmm}" var="data">
		<c:if test="${data.targets.id==73 }">
			${data.value }
			<c:set var="sum73" value="${sum73+data.value}"/>
		</c:if>
	</c:forEach>
</td>
<td width="150px" style=" overflow: hidden;text-overflow:ellipsis;">
	<c:forEach items="${datammmm}" var="data">
		<c:if test="${data.targets.id==77 }">
			${data.value }
			<c:set var="sum77" value="${sum77+data.value}"/>
		</c:if>
	</c:forEach>
</td>
<td width="150px" style=" overflow: hidden;text-overflow:ellipsis;">
	<c:forEach items="${datammmm}" var="data">
		<c:if test="${data.targets.id==80 }">
			${data.value }
			<c:set var="sum80" value="${sum80+data.value}"/>
		</c:if>
	</c:forEach>
</td>
<td width="150px" style=" overflow: hidden;text-overflow:ellipsis;">
	<c:forEach items="${datammmm}" var="data">
		<c:if test="${data.targets.id==142 }">
			${data.value }
			<c:set var="sum142" value="${sum142+data.value}"/>
		</c:if>
	</c:forEach>
</td>
<td width="150px" style=" overflow: hidden;text-overflow:ellipsis;">
	<c:forEach items="${datammmm}" var="data">
		<c:if test="${data.targets.id==85 }">
			${data.value }
			<c:set var="sum85" value="${sum85+data.value}"/>
		</c:if>
	</c:forEach>
</td>
<td width="150px" style=" overflow: hidden;text-overflow:ellipsis;">
	<c:forEach items="${datammmm}" var="data">
		<c:if test="${data.targets.id==92 }">
			${data.value }
			<c:set var="sum92" value="${sum92+data.value}"/>
		</c:if>
	</c:forEach>
</td>

<td width="150px" style=" overflow: hidden;text-overflow:ellipsis;">
	<c:forEach items="${datammmm}" var="data">
		<c:if test="${data.targets.id==93 }">
			${data.value }
		</c:if>
	</c:forEach>
</td>

<td width="150px" style=" overflow: hidden;text-overflow:ellipsis;">
	<c:forEach items="${datammmm}" var="data">
		<c:if test="${data.targets.id==86 }">
			${data.value }
			<c:set var="sum86" value="${sum86+data.value}"/>
		</c:if>
	</c:forEach>
</td>

<td width="150px" style=" overflow: hidden;text-overflow:ellipsis;">
	<c:forEach items="${datammmm}" var="data">
		<c:if test="${data.targets.id==87 }">
			${data.value }
			<c:set var="sum87" value="${sum87+data.value}"/>
		</c:if>
	</c:forEach>
</td>
<td width="150px" style=" overflow: hidden;text-overflow:ellipsis;">
	<c:forEach items="${datammmm}" var="data">
		<c:if test="${data.targets.id==88 }">
			${data.value }
			<c:set var="sum88" value="${sum88+data.value}"/>
		</c:if>
	</c:forEach>
</td>
<td width="150px" style=" overflow: hidden;text-overflow:ellipsis;">
	<c:forEach items="${datammmm}" var="data">
		<c:if test="${data.targets.id==89 }">
			${data.value }
			<c:set var="sum89" value="${sum89+data.value}"/>
		</c:if>
	</c:forEach>
</td>
<td width="150px" style=" overflow: hidden;text-overflow:ellipsis;">
	<c:forEach items="${datammmm}" var="data">
		<c:if test="${data.targets.id==90 }">
			${data.value }
			<c:set var="sum90" value="${sum90+data.value}"/>
		</c:if>
	</c:forEach>
</td>
<td width="150px" style=" overflow: hidden;text-overflow:ellipsis;">
	<c:forEach items="${datammmm}" var="data">
		<c:if test="${data.targets.id==91 }">
			${data.value }
			<c:set var="sum91" value="${sum91+data.value}"/>
		</c:if>
	</c:forEach>
</td>

		</tr>
</c:forEach>
<tr>
<th  width="150px"><fmt:formatNumber value="${sum16 }" pattern="0.0000"/></th>
<th  width="150px"><fmt:formatNumber value="${sum117 }" pattern="0.0000"/></th>
<th  width="150px"><fmt:formatNumber value="${sum52 }" pattern="0.0000"/></th>
<th  width="150px">
<script type="text/javascript">
	document.write(sum53+'%');
</script></th>
<th  width="150px"><fmt:formatNumber value="${sum54 }" pattern="0.0000"/></th>
<th  width="150px"><fmt:formatNumber value="${sum60 }" pattern="0.0000"/></th>
<th  width="150px"><fmt:formatNumber value="${sum61 }" pattern="0.0000"/></th>
<th  width="150px"><fmt:formatNumber value="${sum62 }" pattern="0.0000"/></th>
<th  width="150px"><fmt:formatNumber value="${sum63 }" pattern="0.0000"/></th>
<th  width="150px"><fmt:formatNumber value="${sum66 }" pattern="0.0000"/></th>
<th  width="150px"><fmt:formatNumber value="${sum69 }" pattern="0.0000"/></th>
<th  width="150px"><fmt:formatNumber value="${sum70 }" pattern="0.0000"/></th>
<th  width="150px"><fmt:formatNumber value="${sum71 }" pattern="0.0000"/></th>
<th  width="150px"><fmt:formatNumber value="${sum72 }" pattern="0.0000"/></th>
<th  width="150px"><fmt:formatNumber value="${sum73 }" pattern="0.0000"/></th>
<th  width="150px"><fmt:formatNumber value="${sum77 }" pattern="0.0000"/></th>
<th  width="150px"><fmt:formatNumber value="${sum80 }" pattern="0.0000"/></th>
<th  width="150px"><fmt:formatNumber value="${sum142 }" pattern="0.0000"/></th>
<th  width="150px"><fmt:formatNumber value="${sum85 }" pattern="0.0000"/></th>
<th  width="150px"><fmt:formatNumber value="${sum92 }" pattern="0.0000"/></th>
<th  width="150px"><fmt:formatNumber value="${sum93 }" pattern="0.0000"/></th>
<th  width="150px"><fmt:formatNumber value="${sum86 }" pattern="0.0000"/></th>
<th  width="150px"><fmt:formatNumber value="${sum87 }" pattern="0.0000"/></th>
<th  width="150px"><fmt:formatNumber value="${sum88 }" pattern="0.0000"/></th>
<th  width="150px"><fmt:formatNumber value="${sum89 }" pattern="0.0000"/></th>
<th  width="150px"><fmt:formatNumber value="${sum90 }" pattern="0.0000"/></th>
<th  width="150px"><fmt:formatNumber value="${sum91 }" pattern="0.0000"/></th>
</tr>
</table>

</div> 
</div> </div> 
<br>
</div>
</div>
</div>
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