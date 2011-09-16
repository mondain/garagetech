<%@ include file="/WEB-INF/views/partials/taglibs.jsp" %>
<c:url var="newAdUrl" value="/ad/new"/>
<head>
    <title>My ads</title>
</head>
<header class="info">
    <hgroup>
        <h1>My ads</h1>
        <h2>List of ads created by the current user</h2>
    </hgroup>
</header>
<%@ include file="/WEB-INF/views/partials/messages.jsp" %>
<c:choose>
    <c:when test="${not empty posts}">
        <div class="page-actions group">
            <a href="${newAdUrl}">New Ad</a>
        </div>
        <table class="zebra-striped">
            <thead>
                <tr>
                    <th class="checkbox" scope="col"><input type="checkbox"/></th>
                    <th class="name" scope="col">Ad Contents</th>
                    <th scope="col">Type</th>
                    <th scope="col">Date / Status</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="post" items="${posts}" varStatus="status">
                    <c:set var="count" value="${status.count}" />
                    <c:choose>
                        <c:when test="${count % 2 == 0}">
                            <c:set var="altClass" value="alt"/>
                        </c:when>
                        <c:otherwise>
                            <c:set var="altClass" value=""/>
                        </c:otherwise>
                    </c:choose>
                    <c:url var="bumpUrl" value="/ad/${post.guid}/bmp"/>
                    <c:url var="deleteUrl" value="/ad/${post.guid}/delete"/>
                    <c:url var="editUrl" value="/ad/${post.guid}/edit"/>
                    <c:url var="postUrl" value="/ad/${post.guid}"/>
                    <tr class="${altClass}">
                        <td class="checkbox"><input type="checkbox" value="${post.guid}"/></td>
                        <td class="name">
                            <a href="${postUrl}"><img class="profile" alt="${post.author.username}" src="${ctx}/img/${post.author.username}.jpg" width="52" height="52" border="0"/></a>
                            <a href="${postUrl}">${post.summary}</a>
                            <div class="">
                                <a href="${editUrl}">Edit</a> |
                                <c:if test="${post.status == 'ACTIVE' && (post.type == 'BUMP' || post.type == 'FEATURED')}">
                                    <a href="${bumpUrl}">Bump</a> |
                                </c:if>
                                <c:if test="${post.status == 'DRAFT'}">
                                    <a href="${bumpUrl}">Publish</a> |
                                </c:if>
                                <a href="">Renew</a> |
                                <a class="delete" href="${deleteUrl}">Trash</a>
                           </div>
                        </td>
                        <td>
                            <c:choose>
                                <c:when test="${post.type == 'BASIC'}">Basic</c:when>
                                <c:when test="${post.type == 'BUMP'}">Bump</c:when>
                                <c:when test="${post.type == 'FEATURED'}">Featured</c:when>
                            </c:choose>
                        </td>
                        <td class="date">
                            <time datetime="<joda:format value="${post.created}" style="MS"/>" pubdate><joda:format value="${post.created}" style="MS"/></time>
                            <div class="status">
                                <c:choose>
                                    <c:when test="${post.status == 'ACTIVE'}">Active</c:when>
                                    <c:when test="${post.status == 'ARCHIVED'}">Archived</c:when>
                                    <c:when test="${post.status == 'FLAGGED'}">Flagged for review</c:when>
                                    <c:when test="${post.status == 'SUSPENDED'}">Suspended</c:when>
                                    <c:otherwise>Draft</c:otherwise>
                                </c:choose>
                            </div>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </c:when>
    <c:otherwise>
        <div class="group">
            You haven't created any ads yet. Create a <a href="${newAdUrl}">new ad</a> now.
        </div>
    </c:otherwise>
</c:choose>