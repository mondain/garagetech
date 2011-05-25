<%@ include file="/WEB-INF/views/partials/taglibs.jsp" %>
<%@ include file="/WEB-INF/views/partials/messages.jsp" %>
<c:url var="homeUrl" value="/"/>
<head>
    <title>near Some Location, CA</title>
    <script type="text/javascript" src="${ctx}/js/jquery.prettydate.js"></script>
    <script type="text/javascript">
        $(function() {
            $("time").prettyDate({
                interval: 15000
            });
            $("section.featured article:last").addClass("last");
        });
    </script>
</head>
<c:if test="${not empty featured}">
    <section class="featured group">
        <header class="info">
            <hgroup>
                <h1>Featured ads</h1>
                <h2>List of all featured ads. Mouse over for the details.</h2>
            </hgroup>
        </header>
        <c:forEach var="post" items="${featured}" varStatus="status">
            <c:set var="postUrl" value="/ad/${post.guid}"/>
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
    </section>
</c:if>
<div class="content">
    <section class="posts group">
        <header class="info">
            <hgroup>
                <h1>Recent ads</h1>
                <h2>List of most recent ads.</h2>
            </hgroup>
        </header>
        <c:choose>
            <c:when test="${not empty posts}">
                <c:forEach var="post" items="${posts}" varStatus="status">
                    <c:set var="postUrl" value="/ad/${post.guid}"/>
                    <c:set var="name" value="${not empty post.displayName ? post.displayName : post.author.username}"/>
                    <article class="group">
                        <a href="${postUrl}">
                            <img alt="${name}" src="${ctx}/img/${post.author.username}.jpg" width="75" height="75" border="0"/>
                        </a>
                        <section class="group">
                            <header>
                                <hgroup>
                                    <h1><strong>${name}</strong>
                                        <span class="rating">
                                            <img src="${ctx}/img/rate_on_16x16.png" alt="rating" border="0"/>
                                            <img src="${ctx}/img/rate_on_16x16.png" alt="rating" border="0"/>
                                            <img src="${ctx}/img/rate_on_16x16.png" alt="rating" border="0"/>
                                            <img src="${ctx}/img/rate_on_16x16.png" alt="rating" border="0"/>
                                            <img src="${ctx}/img/rate_off_16x16.png" alt="rating" border="0"/>
                                        </span>
                                        <em>32 reviews</em>
                                    </h1>
                                    <h2><a href="${postUrl}">${post.summary}</a></h2>
                                </hgroup>
                            </header>
                            <p><a href="${postUrl}">Call me at ${post.phone}. I'm available anytime. Some screening may be required.</a></p>
                            <footer>
                                posted by ${name} &#8226; <time datetime="<joda:format value="${post.created}" style="MS"/>" pubdate><joda:format value="${post.created}" style="MS"/></time> &#8226; near ${post.availability.location.zipCode}
                            </footer>
                        </section>
                    </article>
                </c:forEach>
            </c:when>
            <c:otherwise>
                <article class="post">There are no recent posts.</article>
            </c:otherwise>
        </c:choose>
    </section>
</div>
<div class="meta"></div>