<%@ include file="/WEB-INF/views/partials/taglibs.jsp" %>
<c:url var="accountUrl" value="/account"/>
<c:url var="joinUrl" value="/join"/>
<c:url var="loginUrl" value="/login"/>
<c:url var="logoutUrl" value="/logout"/>
<div id="util-nav" class="group">
    <security:authorize ifAnyGranted="ROLE_USER">
        <span>Hello, <a href="${accountUrl}">${currentUser.username}</a>.</span>
        <span><a href="${accountUrl}">Account</a></span>
        <span><a href="${logoutUrl}">Sign Out</a></span>
    </security:authorize>
    <security:authorize ifNotGranted="ROLE_USER">
        <span><a href="${joinUrl}">Join for free!</a></span>
        <span><a href="${loginUrl}">Sign In</a></span>
    </security:authorize>
</div>