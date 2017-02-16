<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<div class="menuu" id="menuu"><c:forEach items="${topMenusList}" var="top">
	<c:if test="${top.menus.id==p&&top.status==1}">
		<ul>
			<li class="title">
			<ul>
				<li class="menuu_title_text">${top.menu_name}</li>
				<li class="menuu_title_img"><img
					src="resources/images/row_up.png" /></li>
			</ul>
			</li>
			<li class="option"><c:forEach items="${topMenusList}" var="top2">
				<c:if test="${top.id==top2.menus.id}">
					<ul>
						<li><img src="resources/images/option_list.gif" /><a
							href="${top2.url}">${top2.menu_name}</a></li>
					</ul>
				</c:if>
			</c:forEach></li>
		</ul>
	</c:if>
</c:forEach>
</div>