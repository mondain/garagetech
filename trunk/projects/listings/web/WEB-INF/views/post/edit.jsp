<%@ include file="/WEB-INF/views/partials/taglibs.jsp" %>
<c:url var="cancelUrl" value="/account"/>
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
<section id="editPost">
    <header class="page-header">
        <h2>${title}</h2>
        <p>No hassle classifieds</p>
    </header>
    <form:form modelAttribute="editPostForm" cssClass="">
        <form:hidden path="guid"/>
        <form:hidden path="type"/>
        <form:errors path="*" cssClass="alert-message error" element="div"/>
        <%-- ${requestScope['org.springframework.validation.BindingResult.editPostForm'].hasFieldErrors('summary')} --%>
        <fieldset>
            <legend>Basics</legend>
            <div class="clearfix<form:errors path="displayName"> error</form:errors>">
                <form:label path="displayName" cssClass="">Display Name</form:label>
                <div class="input">
                    <form:input path="displayName" id="displayName" cssClass="xlarge" maxlength="255" tabindex="1" />
                    <span class="help-block">Displays instead of your username within the ad</span>
                </div>
            </div>
            <div class="clearfix<form:errors path="phone"> error</form:errors>">
                <form:label path="phone" cssClass="desc">Phone Number <span class="req">*</span></form:label>
                <div class="input phone">
                    <div class="inline-inputs">
                        ( <form:input path="phone.areaCode" id="phone.areaCode" cssClass="mini" size="3" maxlength="3" tabindex="2"/> )
                        <form:input path="phone.prefix" id="phone.prefix" cssClass="mini" size="3" maxlength="3" tabindex="3"/> -
                        <form:input path="phone.suffix" id="phone.suffix" cssClass="small" size="4" maxlength="4" tabindex="4"/>
                    </div>
                    <span class="help-block">(###) ###-####</span>
                </div>
            </div>
        </fieldset>
        <fieldset>
            <legend>Content</legend>
            <div class="clearfix<form:errors path="summary"> error</form:errors>">
                <form:label path="summary" cssClass="">Summary / Teaser <span class="req">*</span></form:label>
                <div class="input">
                    <form:textarea path="summary" id="summary" cssClass="xxlarge" rows="3" maxlength="200" tabindex="11"/>
                    <span id="summaryCounter" class="help-block"></span>
                </div>
            </div>
            <div class="clearfix<form:errors path="content"> error</form:errors>">
                <form:label path="content" cssClass="">Content <span class="req">*</span></form:label>
                <div class="input">
                    <form:textarea path="content" id="content" cssClass="xxlarge" rows="10" maxlength="2000" tabindex="11"/>
                    <span id="contentCounter" class="help-block"></span>
                </div>
            </div>
        </fieldset>
        <fieldset>
            <legend>Availability</legend>
            <div class="clearfix<form:errors path="availability.location.zipCode"> error</form:errors>">
                <form:label path="availability.location.zipCode" cssClass="desc">Currently Available <span class="req">*</span></form:label>
                <div class="input availability">
                    <div class="inline-inputs">
                        From&nbsp;&nbsp;<form:input path="availability.start" id="availability.start" cssClass="small date" size="11" maxlength="11" tabindex="5"/>
                        &nbsp;To&nbsp;&nbsp;<form:input path="availability.end" id="availability.end" cssClass="small date" size="11" maxlength="11" tabindex="6"/>
                        &nbsp;Near&nbsp;&nbsp;<form:input path="availability.location.zipCode" id="availability.location.zipCode" cssClass="mini" size="5" maxlength="5" tabindex="7"/>
                    </div>
                </div>
            </div>
            <div class="clearfix<form:errors path="availability.prebookLocation.zipCode"> error</form:errors>">
                <form:label path="availability.prebookLocation.zipCode" cssClass="desc">Prebooking</form:label>
                <div class="input availability">
                    <div class="inline-inputs">
                        From&nbsp;&nbsp;<form:input path="availability.prebookStart" id="availability.prebookStart" cssClass="small date" size="11" maxlength="11" tabindex="5"/>
                        &nbsp;To&nbsp;&nbsp;<form:input path="availability.prebookEnd" id="availability.prebookEnd" cssClass="small date" size="11" maxlength="11" tabindex="6"/>
                        &nbsp;Near&nbsp;&nbsp;<form:input path="availability.prebookLocation.zipCode" id="availability.prebookLocation.zipCode" cssClass="mini" size="5" maxlength="5" tabindex="7"/>
                    </div>
                </div>
            </div>
        </fieldset>
        <fieldset>
            <legend>Details</legend>
        </fieldset>
        <fieldset>
            <legend>Tags</legend>
        </fieldset>
        <fieldset>
            <legend>Links</legend>
        </fieldset>
        <div class="actions">
            <c:choose>
                <c:when test="${isEdit}">
                    <input id="saveBtn" class="btn primary" type="submit" tabindex="14" value="Update"/>
                </c:when>
                <c:otherwise>
                    <input id="saveBtn" class="btn primary" type="submit" tabindex="14" value="Create"/>
                </c:otherwise>
            </c:choose>
            <a href="${cancelUrl}" class="btn">Cancel</a>
        </div>

        <ul>
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
                    <form:input path="tagInput" id="tagInput" cssClass="field text medium" maxlength="32" tabindex="13"/>
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
</section>
