<%@ include file="/WEB-INF/views/partials/taglibs.jsp" %>
<c:url var="adHocUrl" value="/post/new/ad_hoc"/>
<c:url var="basicUrl" value="/post/new/basic"/>
<c:url var="bumpUrl" value="/post/new/bump"/>
<c:url var="cancelUrl" value="/posts"/>
<c:url var="featuredUrl" value="/post/new/featured"/>
<header class="info">
    <h1>Type &amp; Pricing</h1>
    <h2>List of posts types and their prices</h2>
</header>
<%@ include file="/WEB-INF/views/partials/messages.jsp" %>
<table class="ad-types" cellspacing="0">
    <thead>
        <tr>
            <th>
                <h3>Featured (Ad Libitum)</h3>
                <p>$69.95 per month</p>
                <a class="cta" href="${featuredUrl}">Create</a>
            </th>
            <th>
                <h3>Bump (Carpe Diem)</h3>
                <p>$29.95 per month</p>
                <a class="cta" href="${bumpUrl}">Create</a>
            </th>
            <th>
                <h3>Basic (Bona Fide)</h3>
                <p>$19.95 per month</p>
                <a class="cta" href="${basicUrl}">Create</a>
            </th>
            <th class="last">
                <h3>Ad Hoc</h3>
                <p>$4.95 per ad</p>
                <a class="cta" href="${adHocUrl}">Create</a>
            </th>
        </tr>
    </thead>
    <tbody>
        <tr>
            <td>
                <ol>
                    <li>Featured blah...</li>
                    <li>Runs for 30 days</li>
                    <li>Bumps automatically every hour</li>
                    <li>Manually bumped at will</li>
                    <li>Companion bump ad</li>
                    <li>Searchable profile</li>
                    <li>Ratings and reviews</li>
                    <li>Vanity URL (http://listings.com/me)</li>
                </ol>
            </td>
            <td>
                <ol>
                    <li>Bump blah...</li>
                    <li>Runs for 30 days</li>
                    <li>Bumps automatically every 4 hours</li>
                    <li>Manually bumped at will</li>
                    <li>Searchable profile</li>
                    <li>Ratings and reviews</li>
                </ol>
            </td>
            <td>
                <ol>
                    <li>Basic blah...</li>
                    <li>Runs for 30 days</li>
                    <li>Bumps automatically every 12 hours</li>
                    <li>Searchable profile</li>
                    <li>Ratings and reviews</li>
                </ol>
            </td>
            <td class="last">
                <ol>
                    <li>Ad Hoc blah...</li>
                    <li>Runs for 24 hours</li>
                </ol>
            </td>
        </tr>
    </tbody>
</table>