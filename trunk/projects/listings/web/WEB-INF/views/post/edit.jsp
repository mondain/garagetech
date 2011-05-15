<%@ include file="/WEB-INF/views/partials/taglibs.jsp" %>
<c:url var="cancelUrl" value="/ads"/>
<c:choose>
    <c:when test="${isEdit}">
        <c:set var="title" value="Update my ${fn:toLowerCase(editPostForm.type)} ad"/>
    </c:when>
    <c:otherwise>
        <c:set var="title" value="Create a new ${fn:toLowerCase(editPostForm.type)} ad"/>
    </c:otherwise>
</c:choose>
<head>
    <title>${title}</title>
    <script type="text/javascript" src="${ctx}/js/wufoo.js"></script>
    <script type="text/javascript" src="${ctx}/js/jquery.limitMaxLength.js"></script>
    <script type="text/javascript" src="${ctx}/js/ICanHaz.min.js"></script>
    <script type="text/javascript" src="${ctx}/js/post/edit.js"></script>
</head>
<header class="info">
    <h1>${title}</h1>
    <h2>No hassle classifieds</h2>
</header>
<form:form modelAttribute="editPostForm" cssClass="wufoo topLabel group">
    <form:hidden path="guid"/>
    <form:hidden path="type"/>
    <ul>
        <form:errors path="*" cssClass="error" element="li"/>
        <!--li class="section">
            <section>
                <h3>The Basics</h3>
                <div>&nbsp;</div>
            </section>
        </li-->
        <li class="leftThird">
            <form:label path="displayName" cssClass="desc">Display Name</form:label>
            <div>
                <form:input path="displayName" id="displayName" cssClass="field text small" maxlength="255" tabindex="1"/>
                <form:label path="displayName">Displays instead of your username within the ad</form:label>
            </div>
        </li>
        <li class="middleThird">
            <form:label path="phone" cssClass="desc">Phone Number <span class="req">*</span></form:label>
            <span class="symbol">(</span>
            <span>
                <form:input path="phone.areaCode" id="phone.areaCode" cssClass="field text" size="3" maxlength="3" tabindex="2"/>
                <form:label path="phone.areaCode">###</form:label>
            </span>
            <span class="symbol">)</span>
            <span>
                <form:input path="phone.prefix" id="phone.prefix" cssClass="field text" size="3" maxlength="3" tabindex="3"/>
                <form:label path="phone.prefix">###</form:label>
            </span>
            <span class="symbol">-</span>
            <span>
                <form:input path="phone.suffix" id="phone.suffix" cssClass="field text" size="4" maxlength="4" tabindex="4"/>
                <form:label path="phone.suffix">####</form:label>
            </span>
        </li>
        <li class="rightThird">
            <form:label path="location" cssClass="desc">Zip Code <span class="req">*</span></form:label>
            <div>
                <form:input path="location" id="location" cssClass="field text" size="5" maxlength="5" tabindex="5"/>
                <form:label path="location">#####</form:label>
            </div>
        </li>
        <!--li class="section">
            <section>
                <h3>Content</h3>
                <div>&nbsp;</div>
            </section>
        </li-->
        <li>
            <form:label path="summary" cssClass="desc">Teaser <span class="req">*</span></form:label>
            <div>
                <form:textarea path="summary" id="summary" cssClass="field textarea" maxlength="200" tabindex="6"/>
                <form:label id="summaryCounter" path="summary">&nbsp;</form:label>
            </div>
        </li>
        <li>
            <form:label path="content" cssClass="desc">Content <span class="req">*</span></form:label>
            <div>
                <form:textarea path="content" id="content" cssClass="field textarea large" maxlength="2000" tabindex="7"/>
                <form:label id="contentCounter" path="content">&nbsp;</form:label>
            </div>
        </li>
        <!--li class="section">
            <section>
                <h3>Details, Links & Tags</h3>
                <div>&nbsp;</div>
            </section>
        </li-->
        <li class="leftThird complex">
            <form:label path="attributes" cssClass="desc">Details | <a id="newAttribute" href="#">Add details</a></form:label>
            <div id="attributeEditor">
            <c:forEach var="attribute" items="${editPostForm.attributes}" varStatus="status">
                <div class="editor-row">
                    <spring:bind path="editPostForm.attributes[${status.index}].type">
                        <input type="hidden" name="<c:out value="${status.expression}"/>" id="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}"/>"/>
                    </spring:bind>
                    <spring:bind path="editPostForm.attributes[${status.index}].name">
                    <span class="left">
                        <input type="text" name="<c:out value="${status.expression}"/>" id="<c:out value="${status.expression}"/>" class="field text addr" value="<c:out value="${status.value}"/>"/>
                        <label for="<c:out value="${status.expression}"/>">Name</label>
                    </span>
                    </spring:bind>
                    <spring:bind path="editPostForm.attributes[${status.index}].stringValue">
                    <span class="middle">
                        <input type="text" name="<c:out value="${status.expression}"/>" id="<c:out value="${status.expression}"/>" class="field text addr" value="<c:out value="${status.value}"/>"/>
                        <label for="<c:out value="${status.expression}"/>">Value</label>
                    </span>
                    </spring:bind>
                    <span class="delete">
                        <a class="ui-icon ui-icon-trash" href="#"></a>
                    </span>
                </div>
            </c:forEach>
            </div>
        </li>
        <li class="middleThird">
            <form:label path="tagInput" cssClass="desc">Tags</form:label>
            <div>
                <form:input path="tagInput" id="tagInput" cssClass="field text medium" maxlength="32" tabindex="8" />
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
        <li class="rightThird complex">
            <form:label path="links" cssClass="desc">Links | <a id="newLink" href="#">Add link</a></form:label>
            <div id="linkEditor">
            <c:forEach var="link" items="${editPostForm.links}" varStatus="status">
                <div class="editor-row">
                    <spring:bind path="editPostForm.links[${status.index}].type">
                        <input type="hidden" name="<c:out value="${status.expression}"/>" id="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}"/>"/>
                    </spring:bind>
                    <spring:bind path="editPostForm.links[${status.index}].name">
                    <span class="left">
                        <input type="text" name="<c:out value="${status.expression}"/>" id="<c:out value="${status.expression}"/>" class="field text addr" value="<c:out value="${status.value}"/>"/>
                        <label for="<c:out value="${status.expression}"/>">Alias</label>
                    </span>
                    </spring:bind>
                    <spring:bind path="editPostForm.links[${status.index}].stringValue">
                    <span class="middle">
                        <input type="text" name="<c:out value="${status.expression}"/>" id="<c:out value="${status.expression}"/>" class="field text addr" value="<c:out value="${status.value}"/>"/>
                        <label for="<c:out value="${status.expression}"/>">Url</label>
                    </span>
                    </spring:bind>
                    <span class="delete">
                        <a class="ui-icon ui-icon-trash" href="#"></a>
                    </span>
                </div>
            </c:forEach>
            </div>
        </li>
        <li class="buttons">
            <c:choose>
                <c:when test="${isEdit}">
                    <input id="saveBtn" class="btTxt" type="submit" tabindex="9" value="Update"/>
                </c:when>
                <c:otherwise>
                    <input id="saveBtn" class="btTxt" type="submit" tabindex="9" value="Create"/>
                </c:otherwise>
            </c:choose>
            <a href="${cancelUrl}">Or, cancel and return to the list of your ads.</a>
        </li>
    </ul>
</form:form>
<script id="newAttributeTmpl" type="text/html">
    <div class="editor-row">
        <input type="hidden" name="attributes[{{idx}}].type" id="attributes[{{idx}}].type" value="DETAIL"/>
        <span class="left">
            <input type="text" name="attributes[{{idx}}].name" id="attributes[{{idx}}].name" class="field text addr"/>
            <label for="attributes[{{idx}}].name">Name</label>
        </span>
        <span class="middle">
            <input type="text" name="attributes[{{idx}}].stringValue" id="attributes[{{idx}}].stringValue" class="field text addr"/>
            <label for="attributes[{{idx}}].stringValue">Value</label>
        </span>
        <span class="delete">
            <a class="ui-icon ui-icon-trash" href="#"></a>
        </span>
    </div>
</script>
<script id="newLinkTmpl" type="text/html">
    <div class="editor-row">
        <input type="hidden" name="links[{{idx}}].type" id="links[{{idx}}].type" value="LINK"/>
        <span class="left">
            <input type="text" name="links[{{idx}}].name" id="links[{{idx}}].name" class="field text addr"/>
            <label for="links[{{idx}}].name">Alias</label>
        </span>
        <span class="middle">
            <input type="text" name="links[{{idx}}].stringValue" id="links[{{idx}}].stringValue" class="field text addr"/>
            <label for="links[{{idx}}].stringValue">Url</label>
        </span>
        <span class="delete">
            <a class="ui-icon ui-icon-trash" href="#"></a>
        </span>
    </div>
</script>