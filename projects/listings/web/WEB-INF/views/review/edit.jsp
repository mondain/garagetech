<%@ include file="/WEB-INF/views/partials/taglibs.jsp" %>
<c:url var="cancelUrl" value="/reviews"/>
<c:set var="title" value="Create a new review"/>
<head>
    <title>${title}</title>
    <script type="text/javascript" src="${ctx}/js/wufoo.js"></script>
    <script type="text/javascript" src="${ctx}/js/jquery.limitMaxLength.js"></script>
    <script type="text/javascript" src="${ctx}/js/ICanHaz.min.js"></script>
    <script type="text/javascript" src="${ctx}/js/review/edit.js"></script>
</head>
<header class="info">
    <h1>${title}</h1>
    <h2>Write a review, increase your reputation.</h2>
</header>
<form:form modelAttribute="editReviewForm" cssClass="wufoo topLabel group">
    <form:hidden path="guid"/>
    <form:hidden path="postGuid"/>
    <ul>
        <form:errors path="*" cssClass="error" element="li"/>
        <li>
            <form:label path="rating" cssClass="desc">Rating <span class="req">*</span></form:label>
            <div>
                <form:select path="rating" id="rating" cssClass="field select">
                    <form:option value="1"/>
                    <form:option value="2"/>
                    <form:option value="3"/>
                    <form:option value="4"/>
                    <form:option value="5"/>
                </form:select>
            </div>
        </li>
        <li>
            <form:label path="content" cssClass="desc">Content <span class="req">*</span></form:label>
            <div>
                <form:textarea path="content" id="content" cssClass="field textarea large" maxlength="2000" tabindex="2"/>
                <form:label id="contentCounter" path="content">&nbsp;</form:label>
            </div>
        </li>
        <li class="buttons">
            <c:choose>
                <c:when test="${isEdit}">
                    <input id="saveBtn" class="btTxt" type="submit" tabindex="6" value="Update"/>
                </c:when>
                <c:otherwise>
                    <input id="saveBtn" class="btTxt" type="submit" tabindex="6" value="Create"/>
                </c:otherwise>
            </c:choose>
            <a href="${cancelUrl}">Or, cancel and return to the list of reviews.</a>
        </li>
    </ul>
</form:form>