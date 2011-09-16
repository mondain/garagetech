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
        });
    </script>
</head>
<section id="home">
    <c:if test="${not empty featured}">
        <div id="featured">
            <header class="page-header">
                <h2>Featured Ads <small>List of all featured ads. Mouse over for the details.</small></h2>
            </header>
            <div class="row">
                <div class="span15">
                    <c:forEach var="post" items="${featured}" varStatus="status">
                        <c:set var="postUrl" value="/ad/${post.guid}"/>
                        <c:set var="name" value="${not empty post.displayName ? post.displayName : post.author.username}"/>
                        <div class="span3">
                            <div class="avatar">
                                <a href="${postUrl}">
                                    <img alt="${name}" src="${ctx}/img/${post.author.username}.jpg" width="158" height="158" border="0"/>
                                    <span style="display: none;">
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
                    </c:forEach>
                </div>
                <div class="span1"></div>
            </div>
        </div>
    </c:if>
    <div class="row">
        <div class="span10">
            <div id="posts">
                <header class="page-header">
                    <h2>Recent Ads <small>List of most recent ads.</small></h2>
                </header>
                <div class="alert-message block-message info">
                    <a class="close" href="#">×</a>
                    <p>4 new ads</p>
                </div>
                <c:choose>
                    <c:when test="${not empty posts}">
                        <c:forEach var="post" items="${posts}" varStatus="status">
                            <c:set var="postUrl" value="/ad/${post.guid}"/>
                            <c:set var="name" value="${not empty post.displayName ? post.displayName : post.author.username}"/>
                            <article class="clearfix">
                                <div class="avatar">
                                    <a href="${postUrl}">
                                        <img alt="${name}" src="${ctx}/img/${post.author.username}.jpg" width="60" height="60" border="0"/>
                                    </a>
                                </div>
                                <div class="content">
                                    <header>
                                       <h5><a href="${postUrl}">${name}</a> <span>&#8226;</span><span>${post.phone}</span><span>&#8226;</span><span>Near ${post.availability.location.zipCode}</span></h5>
                                    </header>
                                    <p><a href="${postUrl}">${post.summary}</a></p>
                                    <p><a href="${postUrl}">I'm available anytime. Some screening may be required.</a></p>
                                    <!--span class="rating">
                                        <img src="${ctx}/img/rate_on_16x16.png" alt="rating" border="0"/>
                                        <img src="${ctx}/img/rate_on_16x16.png" alt="rating" border="0"/>
                                        <img src="${ctx}/img/rate_on_16x16.png" alt="rating" border="0"/>
                                        <img src="${ctx}/img/rate_on_16x16.png" alt="rating" border="0"/>
                                        <img src="${ctx}/img/rate_off_16x16.png" alt="rating" border="0"/>
                                    </span>
                                    <em>32 reviews</em-->
                                    <footer>
                                        <p>Posted <time datetime="<joda:format value="${post.created}" style="MS"/>" pubdate><joda:format value="${post.created}" style="MS"/></time></p>
                                    </footer>
                                </div>
                            </article>
                        </c:forEach>
                    </c:when>
                    <c:otherwise>
                        <article>There are no recent posts.</article>
                    </c:otherwise>
                </c:choose>
            </div>
        </div>
        <div class="span6">
            <div class="row">
                <header class="page-header">
                    <h4>Activity Stream</h4>
                </header>
            </div>
            <div class="row">
                <header class="page-header">
                    <h4>Recent Reviews</h4>
                </header>
            </div>
            <div class="row">
                <header class="page-header">
                    <h4>Popular Tags</h4>
                </header>
            </div>
        </div>
    </div>
</section>