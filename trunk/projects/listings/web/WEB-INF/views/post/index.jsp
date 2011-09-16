<%@ include file="/WEB-INF/views/partials/taglibs.jsp" %>
<c:url var="homeUrl" value="/"/>
<c:url var="editUrl" value="/ad/${post.guid}/edit"/>
<c:url var="newReviewUrl" value="/review/new?post=${post.guid}"/>
<c:url var="photosUrl" value="/ad/${post.guid}/photos"/>
<c:url var="reviewsUrl" value="/ad/${post.guid}/reviews"/>
<c:set var="name" value="${not empty post.displayName ? post.displayName : post.author.username}"/>
<%@ include file="/WEB-INF/views/partials/messages.jsp" %>
<section id="profile">
    <div class="row breadcrumbs">
        <a class="home" href="${homeUrl}">Ads</a> &rsaquo; ${name}
    </div>
    <div class="row">
        <div class="span3">
            <a href="">
                <img alt="${name}" src="${ctx}/img/${post.author.username}.jpg" width="160" height="160" border="0"/>
            </a>
        </div>
        <div class="span11 profile-header">
            <h2>${name}</h2>
            <div class="row">
                <span class="rating">
                    <img src="${ctx}/img/rate_on_16x16.png" alt="rating" border="0"/>
                    <img src="${ctx}/img/rate_on_16x16.png" alt="rating" border="0"/>
                    <img src="${ctx}/img/rate_on_16x16.png" alt="rating" border="0"/>
                    <img src="${ctx}/img/rate_on_16x16.png" alt="rating" border="0"/>
                    <img src="${ctx}/img/rate_off_16x16.png" alt="rating" border="0"/>
                </span>
            </div>
            <div class="row">${post.phone}</div>
            <div class="row">
                Available from <strong><joda:format value="${post.availability.start}" style="M-"/></strong> to <strong><joda:format value="${post.availability.end}" style="M-"/></strong> near <strong>${post.availability.location.zipCode}</strong>
                <br/>Prebooking from <strong><joda:format value="${post.availability.prebookStart}" style="M-"/></strong> to <strong><joda:format value="${post.availability.prebookEnd}" style="M-"/></strong> near <strong>${post.availability.prebookLocation.zipCode}</strong>
            </div>
            <p><em>${post.summary}</em></p>
        </div>
        <div class="span2">
            <ul class="unstyled">
                <li><a href="${editUrl}">Edit your profile</a></li>
                <li><a href="${newReviewUrl}">Add your review</a></li>
                <li><a class="delete" href="">Report</a></li>
            </ul>
        </div>
    </div>
    <div class="row">
        <div class="span10">
            <ul class="tabs">
                <li class="active">
                    <a href="">Profile</a>
                </li>
                <li>
                    <a href="${photosUrl}">Photos (8)</a>
                </li>
                <li>
                    <a href="${reviewsUrl}">Reviews (32)</a>
                </li>
            </ul>
            <p><c:out value="${post.content}" escapeXml="true"/></p>
            <p>I'm available anytime. Some screening may be required.</p>
            <p>Have you seen ${name} recently? <a href="${newReviewUrl}">Add your review now!</a></p>
        </div>
        <div class="span6">
            <c:if test="${not empty post.attributes}">
                <div class="row">
                    <header class="page-header">
                        <h4>Details</h4>
                    </header>
                    <dl>
                        <c:forEach var="attribute" items="${post.attributes}" varStatus="status">
                            <c:if test="${attribute.type == 'DETAIL'}">
                                <dt>${attribute.name}</dt>
                                <dd>${attribute.stringValue}</dd>
                            </c:if>
                        </c:forEach>
                    </dl>
                </div>
            </c:if>
            <div class="row">
                <header class="page-header">
                    <h4>Tags</h4>
                </header>
                <ul class="tag-list">
                    <li><a href=""><em>53</em> Blonde</a> <span style="width: 63%">&nbsp;</span></li>
                    <li><a href=""><em>46</em> Tall</a> <span style="width: 47%">&nbsp;</span></li>
                    <li><a href=""><em>31</em> Skinny</a> <span style="width: 31%">&nbsp;</span></li>
                    <li><a href=""><em>14</em> Hot</a> <span style="width: 14%">&nbsp;</span></li>
                </ul>
                <p><a href="">Add your tags too!</a></p>
            </div>
            <c:if test="${not empty post.attributes}">
                <div class="row">
                    <header class="page-header">
                        <h4>Links</h4>
                    </header>
                    <ul class="links">
                        <c:forEach var="link" items="${post.attributes}" varStatus="status">
                            <c:if test="${link.type == 'LINK'}">
                                <li><a href="${link.stringValue}">${link.name}</a></li>
                            </c:if>
                        </c:forEach>
                    </ul>
                </div>
            </c:if>
        </div>
    </div>


        <!--section class="reviews group">
            <header>
                <h1>Selected Reviews | <a href="${reviewsUrl}">Read all reviews</a></h1>
            </header>
            <article class="review">
                <header>
                    <h1>
                        <span class="rating">
                            <img src="${ctx}/img/rate_on_16x16.png" alt="rating" border="0"/>
                            <img src="${ctx}/img/rate_on_16x16.png" alt="rating" border="0"/>
                            <img src="${ctx}/img/rate_on_16x16.png" alt="rating" border="0"/>
                            <img src="${ctx}/img/rate_on_16x16.png" alt="rating" border="0"/>
                            <img src="${ctx}/img/rate_off_16x16.png" alt="rating" border="0"/>
                        </span>
                        <span> awarded by somejohn on Nov 21, 2010</span>
                    </h1>
                </header>
                <p>Pellentesque habitant morbi tristique senectus et netus et malesuada fames ac turpis egestas. Vestibulum tortor quam, feugiat vitae, ultricies eget, tempor sit amet, ante. Donec eu libero sit amet quam egestas semper. Aenean ultricies mi vitae est. Mauris placerat eleifend leo.</p>
            </article>
            <article class="review">
                <header>
                    <h1>
                        <span class="rating">
                            <img src="${ctx}/img/rate_on_16x16.png" alt="rating" border="0"/>
                            <img src="${ctx}/img/rate_on_16x16.png" alt="rating" border="0"/>
                            <img src="${ctx}/img/rate_on_16x16.png" alt="rating" border="0"/>
                            <img src="${ctx}/img/rate_on_16x16.png" alt="rating" border="0"/>
                            <img src="${ctx}/img/rate_off_16x16.png" alt="rating" border="0"/>
                        </span>
                        <span> awarded by somejohn on Nov 21, 2010</span>
                    </h1>
                </header>
                <p>Pellentesque habitant morbi tristique senectus et netus et malesuada fames ac turpis egestas. Vestibulum tortor quam, feugiat vitae, ultricies eget, tempor sit amet, ante. Donec eu libero sit amet quam egestas semper. Aenean ultricies mi vitae est. Mauris placerat eleifend leo.</p>
            </article>
            <article class="review">
                <header>
                    <h1>
                        <span class="rating">
                            <img src="${ctx}/img/rate_on_16x16.png" alt="rating" border="0"/>
                            <img src="${ctx}/img/rate_on_16x16.png" alt="rating" border="0"/>
                            <img src="${ctx}/img/rate_on_16x16.png" alt="rating" border="0"/>
                            <img src="${ctx}/img/rate_on_16x16.png" alt="rating" border="0"/>
                            <img src="${ctx}/img/rate_off_16x16.png" alt="rating" border="0"/>
                        </span>
                        <span> awarded by somejohn on Nov 21, 2010</span>
                    </h1>
                </header>
                <p>Pellentesque habitant morbi tristique senectus et netus et malesuada fames ac turpis egestas. Vestibulum tortor quam, feugiat vitae, ultricies eget, tempor sit amet, ante. Donec eu libero sit amet quam egestas semper. Aenean ultricies mi vitae est. Mauris placerat eleifend leo.</p>
            </article>
            <p>Have you seen ${name} recently? <a href="${newReviewUrl}">Add your review now!</a></p>
        </section-->
</section>
