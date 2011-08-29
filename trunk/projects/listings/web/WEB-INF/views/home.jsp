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
    <section id="featured">
        <header class="page-header">
            <h2>Featured ads</h2>
            <p>List of all featured ads. Mouse over for the details.</p>
        </header>
        <div class="row show-grid">
            <c:forEach var="post" items="${featured}" varStatus="status">
                <c:set var="postUrl" value="/ad/${post.guid}"/>
                <c:set var="name" value="${not empty post.displayName ? post.displayName : post.author.username}"/>
                <div class="span3 columns">
                    <a href="${postUrl}">
                        <img alt="${name}" src="${ctx}/img/${post.author.username}.jpg" width="160" height="160" border="0"/>
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
            </c:forEach>
            <div class="span3 columns"></div>
            <div class="span3 columns"></div>
            <div class="span3 columns"></div>
            <div class="span3 columns"></div>
            <div class="span1 column"></div>
        </div>
    </section>
</c:if>
<div class="row">
    <div class="span11">
        <section id="posts">
            <header class="page-header">
                <h2>Recent ads</h2>
                <p>List of most recent ads.</p>
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
                    <article>There are no recent posts.</article>
                </c:otherwise>
            </c:choose>
        </section>
    </div>
    <div class="span5">
        <div class="row">
            <h3>Tags</h3>
        </div>
        <div class="row">
            <h3>Activity Stream</h3>
        </div>
    </div>
</div>