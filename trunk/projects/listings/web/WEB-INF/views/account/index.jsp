<%@ include file="/WEB-INF/views/partials/taglibs.jsp" %>
<c:url var="createAdUrl" value="/ad/new"/>
<head>
    <title>My Account</title>
</head>
<section id="account">
    <header class="page-header">
        <h2>Account Dashboard <small>List of ads created by the current user</small></h2>
    </header>
    <%@ include file="/WEB-INF/views/partials/messages.jsp" %>
    <c:choose>
        <c:when test="${not empty posts}">
            <div class="page-actions clearfix">
                <a class="btn" href="${createAdUrl}">New Ad</a>
            </div>
            <form>
                <table class="zebra-striped datagrid">
                    <thead>
                        <tr>
                            <th scope="col"><input type="checkbox"/></th>
                            <th scope="col">Ad Contents</th>
                            <th scope="col">Type</th>
                            <th scope="col">Date / Status</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="post" items="${posts}" varStatus="status">
                            <c:set var="count" value="${status.count}" />
                            <c:url var="bumpUrl" value="/ad/${post.guid}/bmp"/>
                            <c:url var="deleteUrl" value="/ad/${post.guid}/delete"/>
                            <c:url var="editUrl" value="/ad/${post.guid}/edit"/>
                            <c:url var="postUrl" value="/ad/${post.guid}"/>
                            <c:set var="name" value="${not empty post.displayName ? post.displayName : post.author.username}"/>
                            <tr class="${altClass}">
                                <td scope="row"><input type="checkbox" value="${post.guid}"/></td>
                                <td>
                                    <div class="avatar">
                                        <a href="${postUrl}"><img alt="${post.author.username}" src="${ctx}/img/${post.author.username}.jpg" width="60" height="60" border="0"/></a>
                                    </div>
                                    <div class="content">
                                        <header>
                                           <h5><a href="${postUrl}">${name}</a><span>&#8226;</span><span>${post.phone}</span><span>&#8226;</span><span>Near ${post.availability.location.zipCode}</span></h5>
                                        </header>
                                        <p><a href="${postUrl}">${post.summary}</a></p>
                                        <div class="datagrid-actions">
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
                    <tfoot>
                        <tr>
                            <td>&nbsp;</td>
                            <td colspan="3">
                                <input type="submit" class="btn" value="Renew">
                                <input type="submit" class="btn" value="Trash">
                            </td>
                        </tr>
                    </tfoot>
                </table>
            </form>
        </c:when>
        <c:otherwise>
            <div class="group">
                You haven't created any ads yet. Create a <a href="${newAdUrl}">new ad</a> now.
            </div>
        </c:otherwise>
    </c:choose>
</section>