<%@ include file="/WEB-INF/views/partials/taglibs.jsp" %>
<c:url var="editUrl" value="/ad/${post.guid}/edit"/>
<c:url var="newReviewUrl" value="/review/new?post=${post.guid}"/>
<c:url var="reviewsUrl" value="/ad/${post.guid}/reviews"/>
<c:set var="name" value="${not empty post.displayName ? post.displayName : post.author.username}"/>
<%@ include file="/WEB-INF/views/partials/messages.jsp" %>
<header class="info">
    <hgroup>
        <h1>
            ${name}
            <span class="group">
                <a href="${editUrl}">Edit</a>
                <a href="${newReviewUrl}">Add your review</a>
                <a class="delete" href="">Report</a>
            </span>
        </h1>
        <h2>
            <span class="rating">
                <img src="${ctx}/img/rate_on_16x16.png" alt="rating" border="0"/>
                <img src="${ctx}/img/rate_on_16x16.png" alt="rating" border="0"/>
                <img src="${ctx}/img/rate_on_16x16.png" alt="rating" border="0"/>
                <img src="${ctx}/img/rate_on_16x16.png" alt="rating" border="0"/>
                <img src="${ctx}/img/rate_off_16x16.png" alt="rating" border="0"/>
            </span>
            <span>${post.phone}</span>
            <span>${post.location}</span>
        </h2>
    </hgroup>
</header>
<section class="profile">
    <p>${post.summary}</p>
    <section class="featured group">
        <article class="group">
            <div class="feature">
                <div class="feature-img">
                    <a href="">
                        <img alt="${name}" src="${ctx}/img/${post.author.username}.jpg" width="154" height="154" border="0"/>
                    </a>
                </div>
            </div>
        </article>
        <article class="group">
            <div class="feature">
                <div class="feature-img">
                    <a href="">
                        <img alt="${name}" src="${ctx}/img/${post.author.username}.jpg" width="154" height="154" border="0"/>
                    </a>
                </div>
            </div>
        </article>
        <article class="group">
            <div class="feature">
                <div class="feature-img">
                    <a href="">
                        <img alt="${name}" src="${ctx}/img/${post.author.username}.jpg" width="154" height="154" border="0"/>
                    </a>
                </div>
            </div>
        </article>
        <article class="group">
            <div class="feature">
                <div class="feature-img">
                    <a href="">
                        <img alt="${name}" src="${ctx}/img/${post.author.username}.jpg" width="154" height="154" border="0"/>
                    </a>
                </div>
            </div>
        </article>
        <article class="group last">
            <div class="feature">
                <div class="feature-img">
                    <a href="">
                        <img alt="${name}" src="${ctx}/img/${post.author.username}.jpg" width="154" height="154" border="0"/>
                    </a>
                </div>
            </div>
        </article>
    </section>
    <div class="content">
        <p>${post.content}</p>
        <p>I'm available anytime. Some screening may be required.</p>
        <section class="reviews group">
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
        </section>
    </div>
    <div class="meta">
        <c:if test="${not empty post.attributes}">
            <section class="group">
                <header>
                    <h1>Details</h1>
                </header>
                <dl>
                    <c:forEach var="attribute" items="${post.attributes}" varStatus="status">
                        <c:if test="${attribute.type == 'DETAIL'}">
                            <dt>${attribute.name}</dt>
                            <dd>${attribute.stringValue}</dd>
                        </c:if>
                    </c:forEach>
                </dl>
            </section>
        </c:if>
        <section class="group">
            <header>
                <h1>Tags</h1>
            </header>
            <ul class="tag-list">
                <li><a href=""><em>53</em> Blonde</a> <span style="width: 63%">&nbsp;</span></li>
                <li><a href=""><em>46</em> Tall</a> <span style="width: 47%">&nbsp;</span></li>
                <li><a href=""><em>31</em> Skinny</a> <span style="width: 31%">&nbsp;</span></li>
                <li><a href=""><em>14</em> Hot</a> <span style="width: 14%">&nbsp;</span></li>
            </ul>
            <p><a href="">Add your tags too!</a></p>
        </section>
        <c:if test="${not empty post.attributes}">
            <section class="group">
                <header>
                    <h1>Links</h1>
                </header>
                <ul class="links">
                    <c:forEach var="link" items="${post.attributes}" varStatus="status">
                        <c:if test="${link.type == 'LINK'}">
                            <li><a href="${link.stringValue}">${link.name}</a></li>
                        </c:if>
                    </c:forEach>
                </ul>
            </section>
        </c:if>
    </div>
</section>
