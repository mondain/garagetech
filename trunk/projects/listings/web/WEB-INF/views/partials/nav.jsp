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
<ul>
    <c:choose>
        <c:when test="${uri == '/' || uri == ''}">
            <li class="active"><a href="${homeUrl}">Ads</a></li>
        </c:when>
        <c:otherwise>
            <li><a href="${homeUrl}">Ads</a></li>
        </c:otherwise>
    </c:choose>
    <c:choose>
        <c:when test="${uri == '/directory'}">
            <li class="active"><a href="${directoryUrl}">Directory</a></li>
        </c:when>
        <c:otherwise>
            <li><a href="${directoryUrl}">Directory</a></li>
        </c:otherwise>
    </c:choose>
    <c:choose>
        <c:when test="${uri == '/reviews'}">
            <li class="active"><a href="${reviewsUrl}">Reviews</a></li>
        </c:when>
        <c:otherwise>
            <li><a href="${reviewsUrl}">Reviews</a></li>
        </c:otherwise>
    </c:choose>
    <c:choose>
        <c:when test="${uri == '/ad/new' || uri == '/ad/new/featured'  || uri == '/ad/new/bump'  || uri == '/ad/new/basic' || uri == '/ad/new/ad_hoc'}">
            <li class="active"><a href="${createAdUrl}">Create an Ad</a></li>
        </c:when>
        <c:otherwise>
            <li><a href="${createAdUrl}">Create an Ad</a></li>
        </c:otherwise>
    </c:choose>
</ul>