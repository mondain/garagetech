<%@ include file="/WEB-INF/views/partials/taglibs.jsp" %>
<c:url var="newReviewUrl" value="/review/new"/>
<head>
    <title>Reviews</title>
</head>
<header class="info">
    <hgroup>
        <h1>Reviews</h1>
        <h2>List of recent reviews</h2>
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
            <article class="review">There are no recent reviews.</article>
        </c:otherwise>
    </c:choose>
</section>