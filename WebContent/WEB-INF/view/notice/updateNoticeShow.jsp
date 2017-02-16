<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ include file="../common/common_include.jsp"%>
<link rel="stylesheet" href="kindEditor/themes/default/default.css" />
<link rel="stylesheet" href="kindEditor/plugins/code/prettify.css" />
<script charset="utf-8" src="kindEditor/kindeditor.js"></script>
<script charset="utf-8" src="kindEditor/lang/zh_CN.js"></script>
<script charset="utf-8" src="kindEditor/plugins/code/prettify.js"></script>
<script>
	KindEditor.ready(function(K) {
		var editor1 = K.create('textarea[name="nt.content"]', {
			cssPath : 'kindEditor/plugins/code/prettify.css',
			uploadJson : 'kindEditor/jsp/upload_json.jsp',
			fileManagerJson : 'kindEditor/jsp/file_manager_json.jsp',
			allowFileManager : true,
			afterCreate : function() {
				var self = this;
				K.ctrl(document, 13, function() {
					self.sync();
					document.forms['updateForm'].submit();
				});
				K.ctrl(self.edit.doc, 13, function() {
					self.sync();
					document.forms['updateForm'].submit();
				});
			},
			afterBlur : function() {
				this.sync();
			}
		});
		prettyPrint();
	});
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
<div class="bodytitletxt">通知公告</div>
修改公告
<div class="bodytitleright"></div>
</div>
<div id="rightContent">
<form id="updateNotice" name="updateForm" method="post">
<table cellspacing="1" cellpadding="0" class="stable">
	<tr>
		<th width="100px">标题</th>
		<td style="text-align: left;"><input id="noticeTitle"
			class="easyui-validatebox" data-options="required:true" type="text"
			name="nt.title" value="${updateNoticeMessage.title}"
			class="input_text" size="100"> <input type="hidden"
			name="nt.id" value="${updateNoticeMessage.id}" /></td>
	</tr>
	<tr>
		<th>内容</th>
		<td><textarea id="content" name="nt.content" cols="100" rows="8"
			style="width: 700px; height: 200px; visibility: hidden;">${updateNoticeMessage.content}</textarea></td>
	</tr>
	<tr>
		<th>操作</th>
		<td colspan="7" style="text-align: left;">
		<a onclick="updateNotice();" href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-ok'">修改</a>&nbsp;
		<a onclick="history.go(-1);" href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-back'">返回</a></td>
	</tr>
</table>
</form>
</div>
</div>
</div>
</div>
</body>
</html>
<%!private String htmlspecialchars(String str) {
		str = str.replaceAll("&", "&amp;");
		str = str.replaceAll("<", "&lt;");
		str = str.replaceAll(">", "&gt;");
		str = str.replaceAll("\"", "&quot;");
		return str;
	}%>