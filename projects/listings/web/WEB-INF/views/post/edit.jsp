<%@ include file="/WEB-INF/views/partials/taglibs.jsp" %>
<c:url var="cancelUrl" value="/posts"/>
<head>
    <title>${label}</title>
    <script type="text/javascript" src="${ctx}/js/wufoo.js"></script>
    <script type="text/javascript" src="${ctx}/js/jquery.limitMaxLength.js"></script>
    <script type="text/javascript" src="${ctx}/js/ICanHaz.min.js"></script>
    <script type="text/javascript" src="${ctx}/js/post/edit.js"></script>
</head>
<header class="info">
    <h1>
        <c:choose>
            <c:when test="${isEdit}">Update my ${fn:toLowerCase(editPostForm.type)} ad</c:when>
            <c:otherwise>Create a new ${fn:toLowerCase(editPostForm.type)} ad</c:otherwise>
        </c:choose>
    </h1>
    <h2>No hassle postings.</h2>
</header>
<form:form modelAttribute="editPostForm" cssClass="wufoo topLabel group">
    <form:hidden path="postId"/>
    <form:hidden path="guid"/>
    <form:hidden path="type"/>
    <ul>
        <form:errors path="*" cssClass="error" element="li"/>
        <li class="leftHalf">
            <form:label path="displayName" cssClass="desc">Display Name</form:label>
            <div>
                <form:input path="displayName" id="displayName" cssClass="field text medium" maxlength="255" tabindex="1"/>
                <form:label path="displayName">If provided, this will display instead of your username within the ad.</form:label>
            </div>
        </li>
        <li class="rightHalf"></li>
        <li class="leftHalf">
            <form:label path="phone" cssClass="desc">Phone Number <span class="req">*</span></form:label>
            <div>
                <form:input path="phone" id="phone" cssClass="field text medium" maxlength="32" tabindex="4"/>
                <form:label path="phone">(###) ###-####</form:label>
            </div>
        </li>
        <li class="rightHalf">
            <form:label path="location" cssClass="desc">Location <span class="req">*</span></form:label>
            <div>
                <form:input path="location" id="location" cssClass="field text medium" maxlength="32" tabindex="5"/>
                <form:label path="location">Example: Some Location, CA or 95129</form:label>
            </div>
        </li>
        <li>
            <form:label path="summary" cssClass="desc">Teaser <span class="req">*</span></form:label>
            <div>
                <form:textarea path="summary" id="summary" cssClass="field textarea" maxlength="200" tabindex="2"/>
                <form:label id="summaryCounter" path="summary">&nbsp;</form:label>
            </div>
        </li>
        <li>
            <form:label path="content" cssClass="desc">Content <span class="req">*</span></form:label>
            <div>
                <form:textarea path="content" id="content" cssClass="field textarea large" maxlength="2000" tabindex="3"/>
                <form:label id="contentCounter" path="content">&nbsp;</form:label>
            </div>
        </li>
        <li class="leftHalf complex">
            <form:label path="phone" cssClass="desc">Attributes <a id="newAttribute" href="#">New attribute</a></form:label>
            <div id="attributeEditor">
            <c:forEach var="attribute" items="${editPostForm.attributes}" varStatus="status">
                <div class="editor-row">
                    <span class="delete">
                        <a class="ui-icon ui-icon-trash" href="#"></a>
                    </span>
                    <spring:bind path="editPostForm.attributes[${status.index}].name">
                    <span class="left">
                        <input type="text" name="<c:out value="${status.expression}"/>" id="<c:out value="${status.expression}"/>" class="field text addr" value="<c:out value="${status.value}"/>"/>
                        <label for="<c:out value="${status.expression}"/>">Name</label>
                    </span>
                    </spring:bind>
                    <spring:bind path="editPostForm.attributes[${status.index}].stringValue">
                    <span class="right">
                        <input type="text" name="<c:out value="${status.expression}"/>" id="<c:out value="${status.expression}"/>" class="field text addr" value="<c:out value="${status.value}"/>"/>
                        <label for="<c:out value="${status.expression}"/>">Value</label>
                    </span>
                    </spring:bind>
                </div>
            </c:forEach>
            </div>
        </li>
        <li class="rightHalf">
            <form:label path="tagInput" cssClass="desc">Tags</form:label>
            <div>
                <form:input path="tagInput" id="tagInput" cssClass="field text medium" maxlength="32" tabindex="6" />
                <form:label path="tagInput">Keywords or labels that categorize your ad with other, similar ads. Max of 10 tags.</form:label>
            </div>
            <div>
                <ul class="tags">
                    <li><a href="#">Blonde</a></li>
                    <li><a href="#">Tall</a></li>
                    <li><a href="#">Hot</a></li>
                    <li><a href="#">Blonde</a></li>
                    <li><a href="#">Tall</a></li>
                    <li><a href="#">Hot</a></li>
                    <li><a href="#">Blonde</a></li>
                    <li><a href="#">Tall</a></li>
                    <li><a href="#">Hot</a></li>
                </ul>
            </div>
        </li>
        <li class="buttons">
            <c:choose>
                <c:when test="${isEdit}">
                    <input id="saveBtn" class="btTxt" type="submit" tabindex="7" value="Update"/>
                </c:when>
                <c:otherwise>
                    <input id="saveBtn" class="btTxt" type="submit" tabindex="7" value="Create"/>
                </c:otherwise>
            </c:choose>
            <a href="${cancelUrl}">Or, cancel and return to the list of posts.</a>
        </li>
    </ul>
</form:form>
<script id="newAttributeTmpl" type="text/html">
    <div class="editor-row">
        <span class="delete">
            <a class="ui-icon ui-icon-trash" href="#"></a>
        </span>
        <span class="left">
            <input type="text" name="attributes[{{idx}}].name" id="attributes[{{idx}}].name" class="field text addr"/>
            <label for="attributes[{{idx}}].name">Name</label>
        </span>
        <span class="right">
            <input type="text" name="attributes[{{idx}}].stringValue" id="attributes[{{idx}}].stringValue" class="field text addr"/>
            <label for="attributes[{{idx}}].stringValue">Value</label>
        </span>
    </div>
</script>