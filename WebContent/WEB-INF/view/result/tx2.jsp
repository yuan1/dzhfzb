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
<%
	String zhibiaoname=request.getAttribute("zhibiaostr").toString();
	String monthl=request.getAttribute("monthl").toString();
	String json=request.getAttribute("json").toString();
%>
<script type="text/javascript">  
        require.config({  
            packages: [  
                {  
                    name: 'zrender',  
                    location: 'js', // zrender与echarts在同一级目录  
                    main: 'zrender'
                },  
                {  
                    name: 'echarts',  
                    location: 'js',  
                    main: 'echarts'  
                }  
            ]  
        }); 
    /***/  
var option = {
	    title : {
	        text: '公司累计折线图',
	        subtext: '${deptname}'
	    },
	    tooltip : {
	        trigger: 'axis'
	    },
	    legend: {
	        data:<%=zhibiaoname%>
	    },
	    toolbox: {
	        show : true,
	        feature : {
	            mark : {show: true},
	            dataView : {show: true, readOnly: false},
	            magicType : {show: true, type: ['line', 'bar']},
	            restore : {show: true},
	            saveAsImage : {show: true}
	        }
	    },
	    dataZoom : {
	        show : true,
	        realtime : true,
	        start : 0,
	        end : 100
	    },
	    calculable : true,
	    xAxis : [
	        {
	            type : 'category',
	            boundaryGap : false,
	            data : <%=monthl%>
	        }
	    ],
	    yAxis : [
	        {
	            type : 'value',
	            axisLabel : {
	                formatter: '{value}'
	            }
	        }
	    ],
	    series : <%=json%>
	};
	                    
        /* 
        *按需加载 
        */  
        require(  
            [  
                'echarts',  
                'echarts/chart/bar',  
                'echarts/chart/line',
            ],  
            //渲染ECharts图表  
            function DrawEChart(ec) {  
                //图表渲染的容器对象  
                var chartContainer = document.getElementById("main1");  
                //加载图表  
                var myChart = ec.init(chartContainer);  
                myChart.setOption(option);  
            }  
        );  
    </script>
	<div id="main1" style="height:450px;width:100%;repeat:scroll;"></div>
    <table cellpadding="0" cellspacing="1" class="stable">
	<tr>
		<td style="text-align: center;"><a
			onclick="history.go(-1);" href="javascript:void(0)"
			class="easyui-linkbutton" data-options="iconCls:'icon-back'">返回</a></td>
	</tr>
</table>
</div>
</div>
</div>
</div>
</body>
</html>