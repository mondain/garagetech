<%@ include file="/WEB-INF/views/partials/taglibs.jsp" %>
<c:url var="accountUrl" value="/account"/>
<c:url var="joinUrl" value="/join"/>
<c:url var="loginUrl" value="/login"/>
<c:url var="logoutUrl" value="/logout"/>
<ul class="nav secondary-nav">
    <security:authorize ifAnyGranted="ROLE_USER">
        <li class="menu">
            <a href="#" class="menu">${currentUser.username}</a>
            <ul class="menu-dropdown">
                <li><a href="${accountUrl}">Account</a></li>
                <li class="divider"></li>
                <li><a href="${logoutUrl}">Sign Out</a></li>
            </ul>
        </li>
    </security:authorize>
    <security:authorize ifNotGranted="ROLE_USER">
        <li><a href="${joinUrl}">Join for free!</a></li>
        <li><a href="${loginUrl}">Sign In</a></li>
    </security:authorize>
</ul>