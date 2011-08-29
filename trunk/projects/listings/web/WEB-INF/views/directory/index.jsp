<%@ include file="/WEB-INF/views/partials/taglibs.jsp" %>
<head>
    <title>Directory</title>
</head>
<%@ include file="/WEB-INF/views/partials/messages.jsp" %>
<section class="featured group">
    <header class="page-header">
        <h2>Directory</h2>
        <p>A to Z and then some</p>
    </header>
    <c:choose>
        <c:when test="${not empty posts}">
            <c:forEach var="post" items="${posts}" varStatus="status">
                <c:url var="postUrl" value="/ad/${post.guid}"/>
                <c:set var="name" value="${not empty post.displayName ? post.displayName : post.author.username}"/>
                <article class="group">
                    <div class="feature">
                        <div class="feature-img">
                            <a href="${postUrl}">
                                <img alt="${name}" src="${ctx}/img/${post.author.username}.jpg" width="154" height="154" border="0"/>
                                <span>
                                    <strong>${name}</strong>
                                    <strong class="rating">
                                        <img src="${ctx}/img/rate_on_16x16_white.png" alt="rating" border="0"/>
                                        <img src="${ctx}/img/rate_on_16x16_white.png" alt="rating" border="0"/>
                                        <img src="${ctx}/img/rate_on_16x16_white.png" alt="rating" border="0"/>
                                        <img src="${ctx}/img/rate_on_16x16_white.png" alt="rating" border="0"/>
                                        <img src="${ctx}/img/rate_off_16x16_white.png" alt="rating" border="0"/>
                                    </strong>
                                    <em>${post.phone}<br/>${post.availability.location.zipCode}</em>
                                </span>
                            </a>
                        </div>
                    </div>
                </article>
            </c:forEach>
        </c:when>
        <c:otherwise>
            <article class="post">There are no recent ads.</article>
        </c:otherwise>
    </c:choose>
</section>