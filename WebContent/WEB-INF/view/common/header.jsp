<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<div class="topnav">
<div class="topLogo"></div>
<%@include file="title.jsp"%>
<div class="leftnav">
  <ul class="nav_menu">
  	<li class="navleft"></li>
  		<c:forEach items="${topMenusList}" var="top" varStatus="a">
		<c:if test="${top.menus.id==null}">
			<li class="nav_menu-item"><a href="${top.url}">${top.menu_name}</a>
				<ul class="nav_submenu">
					<c:forEach items="${topMenusList}" var="top2">
						<c:if test="${top.id==top2.menus.id}">
							<c:forEach items="${topMenusList}" var="top3">
								<c:if test="${top2.id==top3.menus.id }">
									<li class="nav_submenu-item"><a href="${top3.url}">${top3.menu_name}</a></li>
								</c:if>
							</c:forEach>
						</c:if>
					</c:forEach>
			     </ul>
			</li>
		</c:if>
	</c:forEach>
    <li class="navright"></li>
  </ul>
</div>
</div>

