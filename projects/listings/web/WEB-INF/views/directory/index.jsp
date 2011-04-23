<%@ include file="/WEB-INF/views/partials/taglibs.jsp" %>
<head>
    <title>Directory</title>
</head>
<header class="info">
    <hgroup>
        <h1>Directory</h1>
        <h2>Directory of all active profiles</h2>
    </hgroup>
</header>
<%@ include file="/WEB-INF/views/partials/messages.jsp" %>
<section class="featured group">
    <c:choose>
        <c:when test="${not empty posts}">
            <c:forEach var="post" items="${posts}" varStatus="status">
                <c:set var="postUrl" value="/post/${post.id}"/>
                <article class="group">
                    <div class="feature">
                        <div class="feature-img">
                            <a href="${postUrl}">
                                <img alt="${post.author.username}" src="${ctx}/img/${post.author.username}.jpg" width="154" height="154" border="0"/>
                                <span>
                                    <strong>Carl Sziebert</strong>
                                    <strong class="rating">
                                        <img src="${ctx}/img/rate_on_16x16_white.png" alt="rating" border="0"/>
                                        <img src="${ctx}/img/rate_on_16x16_white.png" alt="rating" border="0"/>
                                        <img src="${ctx}/img/rate_on_16x16_white.png" alt="rating" border="0"/>
                                        <img src="${ctx}/img/rate_on_16x16_white.png" alt="rating" border="0"/>
                                        <img src="${ctx}/img/rate_off_16x16_white.png" alt="rating" border="0"/>
                                    </strong>
                                    <em>408-555-1212<br/>Some Location, CA</em>
                                </span>
                            </a>
                        </div>
                    </div>
                </article>
            </c:forEach>
        </c:when>
        <c:otherwise>
            <article class="post">There are no recent posts.</article>
        </c:otherwise>
    </c:choose>
</section>