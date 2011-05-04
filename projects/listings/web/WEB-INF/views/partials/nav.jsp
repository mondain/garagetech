<%@ include file="/WEB-INF/views/partials/taglibs.jsp" %>
<c:url var="accountUrl" value="/account"/>
<c:url var="directoryUrl" value="/directory"/>
<c:url var="homeUrl" value="/"/>
<c:url var="joinUrl" value="/join"/>
<c:url var="loginUrl" value="/login"/>
<c:url var="logoutUrl" value="/logout"/>
<c:url var="postsUrl" value="/posts"/>
<c:url var="reviewsUrl" value="/reviews"/>
<c:set var="uri" value="${pageContext.request.requestURI}"/>
<nav class="group">
    <ul>
        <li><a href="${homeUrl}">Ads</a></li>
        <li><a href="${directoryUrl}">Directory</a></li>
        <li><a href="${reviewsUrl}">Reviews</a></li>
        <security:authorize ifAnyGranted="ROLE_USER">
            <li><a href="${accountUrl}">Account</a></li>
            <li class="right"><a href="${logoutUrl}">Sign Out</a></li>
            <li class="right">Hello again, <a href="${accountUrl}">${currentUser.username}</a>.</li>
        </security:authorize>
        <security:authorize ifNotGranted="ROLE_USER">
            <li><a href="${joinUrl}">Join for free!</a></li>
            <li class="right"><a href="${loginUrl}">Sign In</a></li>
        </security:authorize>
    </ul>
</nav>
<nav class="subnav group">
    <ul>
        <c:if test="${uri == '/'}">
            <li><a href="${homeUrl}">Popular</a></li>
            <li><a href="${homeUrl}">Search</a></li>
        </c:if>
        <c:if test="${uri == '/directory'}">
            <li><a href="${directoryUrl}">Popular</a></li>
            <li><a href="${directoryUrl}">Search</a></li>
        </c:if>
        <c:if test="${uri == '/reviews'}">
            <li><a href="${reviewsUrl}">Recent</a></li>
            <li><a href="${reviewsUrl}">Search</a></li>
        </c:if>
        <security:authorize ifAnyGranted="ROLE_USER">
            <c:if test="${uri == '/account' || uri == '/posts'}">
                <li><a href="${accountUrl}">Dashboard</a></li>
                <li><a href="${postsUrl}">My Ads</a></li>
            </c:if>
        </security:authorize>
        <li>&nbsp;</li>
    </ul>
</nav>