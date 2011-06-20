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
<nav id="main-nav">
    <ul class="group">
        <c:choose>
            <c:when test="${uri == '/' || uri == ''}">
                <li class="selected"><a href="${homeUrl}"><strong>Ads<em>The latest and greatest</em></strong></a></li>
            </c:when>
            <c:otherwise>
                <li><a href="${homeUrl}"><strong>Ads<em>The latest and greatest</em></strong></a></li>
            </c:otherwise>
        </c:choose>
        <c:choose>
            <c:when test="${uri == '/directory'}">
                <li class="selected"><a href="${directoryUrl}"><strong>Directory<em>A to Z and then some</em></strong></a></li>
            </c:when>
            <c:otherwise>
                <li><a href="${directoryUrl}"><strong>Directory<em>A to Z and then some</em></strong></a></li>
            </c:otherwise>
        </c:choose>
        <c:choose>
            <c:when test="${uri == '/reviews'}">
                <li class="selected"><a href="${reviewsUrl}"><strong>Reviews<em>Get the scoop</em></strong></a></li>
            </c:when>
            <c:otherwise>
                <li><a href="${reviewsUrl}"><strong>Reviews<em>Get the scoop</em></strong></a></li>
            </c:otherwise>
        </c:choose>
        <c:choose>
            <c:when test="${uri == '/ad/new' || uri == '/ad/new/featured'  || uri == '/ad/new/bump'  || uri == '/ad/new/basic' || uri == '/ad/new/ad_hoc'}">
                <li class="last create selected"><a href="${createAdUrl}"><strong>Create an ad<em>Starting at $4.95</em></strong></a></li>
            </c:when>
            <c:otherwise>
                <li class="last create"><a href="${createAdUrl}"><strong>Create an ad<em>Starting at $4.95</em></strong></a></li>
            </c:otherwise>
        </c:choose>
    </ul>
</nav>