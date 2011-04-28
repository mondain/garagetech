<%@ include file="/WEB-INF/views/partials/taglibs.jsp" %>
<c:set var="editUrl" value="/post/${post.guid}/edit"/>
<header class="info">
    <hgroup>
        <h1>
            Carl Sziebert
            <em>(${post.author.username})</em>
            <span class="group">
                <a href="${editUrl}">Edit</a>
                <a href="">Add your review</a>
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
            <span>408-555-1212</span>
            <span>Some Location, CA</span>
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
                        <img alt="${post.author.username}" src="${ctx}/img/${post.author.username}.jpg" width="154" height="154" border="0"/>
                    </a>
                </div>
            </div>
        </article>
        <article class="group">
            <div class="feature">
                <div class="feature-img">
                    <a href="">
                        <img alt="${post.author.username}" src="${ctx}/img/${post.author.username}.jpg" width="154" height="154" border="0"/>
                    </a>
                </div>
            </div>
        </article>
        <article class="group">
            <div class="feature">
                <div class="feature-img">
                    <a href="">
                        <img alt="${post.author.username}" src="${ctx}/img/${post.author.username}.jpg" width="154" height="154" border="0"/>
                    </a>
                </div>
            </div>
        </article>
        <article class="group">
            <div class="feature">
                <div class="feature-img">
                    <a href="">
                        <img alt="${post.author.username}" src="${ctx}/img/${post.author.username}.jpg" width="154" height="154" border="0"/>
                    </a>
                </div>
            </div>
        </article>
        <article class="group last">
            <div class="feature">
                <div class="feature-img">
                    <a href="">
                        <img alt="${post.author.username}" src="${ctx}/img/${post.author.username}.jpg" width="154" height="154" border="0"/>
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
                <h1>Selected Reviews</h1>
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
            <p>Have you seen Carl Sziebert recently? <a href="">Add your review now!</a></p>
        </section>
    </div>
    <div class="meta">
        <c:if test="${not empty post.attributes}">
            <section class="group">
                <header>
                    <h1>Attributes</h1>
                </header>
                <dl>
                    <c:forEach var="attribute" items="${post.attributes}" varStatus="status">
                        <dt>${attribute.name}</dt>
                        <dd>${attribute.stringValue}</dd>
                    </c:forEach>
                </dl>
            </section>
        </c:if>
        <section class="group">
            <header>
                <h1>Tags</h1>
            </header>
            <ul>
                <li><a href=""><em>53</em> Blonde</a> <span style="width: 63%">&nbsp;</span></li>
                <li><a href=""><em>46</em> Tall</a> <span style="width: 47%">&nbsp;</span></li>
                <li><a href=""><em>31</em> Skinny</a> <span style="width: 31%">&nbsp;</span></li>
                <li><a href=""><em>14</em> Hot</a> <span style="width: 14%">&nbsp;</span></li>
            </ul>
            <p><a href="">Add your tags too!</a></p>
        </section>
    </div>
</section>
