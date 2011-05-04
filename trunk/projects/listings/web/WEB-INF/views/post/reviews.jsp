<%@ include file="/WEB-INF/views/partials/taglibs.jsp" %>
<c:url var="newReviewUrl" value="/review/new"/>
<c:url var="postUrl" value="/ad/${post.guid}"/>
<c:set var="title" value="Reviews for ${post.author.username}"/>
<head>
    <title>${title}</title>
</head>
<header class="info">
    <hgroup>
        <h1>Reviews for <a href="${postUrl}">${post.author.username}</a></h1>
        <h2>List of all reviews for the selected provider</h2>
    </hgroup>
</header>
<%@ include file="/WEB-INF/views/partials/messages.jsp" %>
<div class="page-actions group">
    <a href="${newReviewUrl}">Add your review</a>
</div>
<section class="group">
    <c:choose>
        <c:when test="${not empty reviews}">
            <c:forEach var="review" items="${reviews}" varStatus="status">
                <article class="review">${review.content}</article>
            </c:forEach>
        </c:when>
        <c:otherwise>
            <article class="review">There are no reviews for ${post.author.username}. <a href="${newReviewUrl}">Add yours now!</a></article>
        </c:otherwise>
    </c:choose>
</section>