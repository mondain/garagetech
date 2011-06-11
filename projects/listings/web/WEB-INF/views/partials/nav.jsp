<%@ include file="/WEB-INF/views/partials/taglibs.jsp" %>
<c:url var="accountUrl" value="/account"/>
<c:url var="createAdUrl" value="/ad/new"/>
<c:url var="directoryUrl" value="/directory"/>
<c:url var="homeUrl" value="/"/>
<c:url var="joinUrl" value="/join"/>
<c:url var="loginUrl" value="/login"/>
<c:url var="logoutUrl" value="/logout"/>
<c:url var="postsUrl" value="/ads"/>
<c:url var="reviewsUrl" value="/reviews"/>
<c:set var="uri" value="${pageContext.request.requestURI}"/>
<nav id="main-nav" class="tabs">
    <ul class="group">
        <c:choose>
            <c:when test="${uri == '/ad/new'}">
                <li class="selected"><a href="${createAdUrl}">Create an ad</a></li>
            </c:when>
            <c:otherwise>
                <li class="create"><a href="${createAdUrl}">Create an ad</a></li>
            </c:otherwise>
        </c:choose>
        <c:choose>
            <c:when test="${uri == '/reviews'}">
                <li class="selected"><a href="${reviewsUrl}">Reviews</a></li>
            </c:when>
            <c:otherwise>
                <li><a href="${reviewsUrl}">Reviews</a></li>
            </c:otherwise>
        </c:choose>
        <c:choose>
            <c:when test="${uri == '/directory'}">
                <li class="selected"><a href="${directoryUrl}">Directory</a></li>
            </c:when>
            <c:otherwise>
                <li><a href="${directoryUrl}">Directory</a></li>
            </c:otherwise>
        </c:choose>
        <c:choose>
            <c:when test="${uri == '/'}">
                <li class="selected"><a href="${homeUrl}">Ads</a></li>
            </c:when>
            <c:otherwise>
                <li><a href="${homeUrl}">Ads</a></li>
            </c:otherwise>
        </c:choose>
    </ul>
</nav>