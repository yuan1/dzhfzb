<%@ page language="java" contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
	String formName = request.getParameter("formName");
	String editorId = request.getParameter("editorId");
	String myPath = "/editor/";
%>
<textarea id="<%=editorId%>" name="<%=editorId%>" cols="60" rows="10"
	style="width: 800px; height: 250px; visibility: hidden;"
	onpropertychange="if(value.length>65535) value=value.substr(0,65535)">
		<s:if test="#request.content != null">
	<s:text name="content" />
</s:if></textarea>
<script type="text/javascript">
	 var editor;
     KindEditor.ready(function(K) {
             editor = K.create('#<%=editorId%>', {
                    resizeType : 2,
                    uploadJson : 'kindEditor/jsp/upload_json.jsp', // 相对于当前页面的路径
               	 fileManagerJson : 'kindEditor/jsp/file_manager_json.jsp',
        			allowFileManager : true,
             			afterChange : function() {
             				K('#word_count').html(65535 - this.count());
					}
             
            });
     });
	</script>
<%!private String htmlspecialchars(String str) {
		str = str.replaceAll("&", "&amp;");
		str = str.replaceAll("<", "&lt;");
		str = str.replaceAll(">", "&gt;");
		str = str.replaceAll("\"", "&quot;");
		return str;
	}%>