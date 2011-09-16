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
            <div class="clearfix">
                <form:label path="">Photo</form:label>
                <div class="input">
                    
                </div>
            </div>
            <div class="clearfix<form:errors path="displayName"> error</form:errors>">
                <form:label path="displayName" cssClass="">Display Name</form:label>
                <div class="input">
                    <form:input path="displayName" id="displayName" cssClass="xlarge" maxlength="255" tabindex="1" />
                    <span class="help-block">Displays instead of your username within the ad</span>
                </div>
            </div>
            <div class="phone clearfix<form:errors path="phone"> error</form:errors>">
                <form:label path="phone" cssClass="desc">Phone Number <span class="req">*</span></form:label>
                <div class="input">
                    <span>
                        <form:input path="phone.areaCode" id="phone.areaCode" cssClass="mini" size="3" maxlength="3" tabindex="2"/>
                        <form:label path="phone.areaCode">###</form:label>
                    </span>
                    <span class="symbol">-</span>
                    <span>
                        <form:input path="phone.prefix" id="phone.prefix" cssClass="mini" size="3" maxlength="3" tabindex="3"/>
                        <form:label path="phone.prefix">###</form:label>
                    </span>
                    <span class="symbol">-</span>
                    <span>
                        <form:input path="phone.suffix" id="phone.suffix" cssClass="small" size="4" maxlength="4" tabindex="4"/>
                        <form:label path="phone.suffix">####</form:label>
                    </span>
                </div>
            </div>
        </fieldset>
        <fieldset>
            <legend>Availability</legend>
            <div class="clearfix<form:errors path="availability.location.zipCode"> error</form:errors>">
                <form:label path="availability.location.zipCode">Currently Available <span class="req">*</span></form:label>
                <div class="input availability">
                    <div class="inline-inputs">
                        From&nbsp;&nbsp;<form:input path="availability.start" id="availability.start" cssClass="small date" size="11" maxlength="11" tabindex="5"/>
                        &nbsp;To&nbsp;&nbsp;<form:input path="availability.end" id="availability.end" cssClass="small date" size="11" maxlength="11" tabindex="6"/>
                        &nbsp;Near&nbsp;&nbsp;<form:input path="availability.location.zipCode" id="availability.location.zipCode" cssClass="mini" size="5" maxlength="5" tabindex="7"/>
                    </div>
                </div>
            </div>
            <div class="clearfix<form:errors path="availability.prebookLocation.zipCode"> error</form:errors>">
                <form:label path="availability.prebookLocation.zipCode">Prebooking</form:label>
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
            <div class="clearfix tags">
                <form:label path="tagInput">Tags</form:label>
                <div class="clearfix input tag-list">
                    <ul class="unstyled">
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
                <div class="clearfix input tag-input">
                    <span>
                        <form:input path="tagInput" id="tagInput" cssClass="xxlarge" maxlength="32" tabindex="13"/>
                        <form:label path="tagInput">Keywords or labels that categorize your ad with other, similar ads<br>Max of 10 tags</form:label>
                    </span>
                    <span>
                       <a href="" class="btn">Add</a>
                    </span>
                </div>
            </div>
            <div class="clearfix details">
                <form:label path="attributes">Details</form:label>
                <div id="attributeEditor" class="input">
                    <c:forEach var="attribute" items="${editPostForm.attributes}" varStatus="status">
                        <div class="clearfix row">
                            <spring:bind path="editPostForm.attributes[${status.index}].type">
                                <input type="hidden" name="<c:out value="${status.expression}"/>" id="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}"/>"/>
                            </spring:bind>
                            <spring:bind path="editPostForm.attributes[${status.index}].name">
                            <span>
                                <input type="text" name="<c:out value="${status.expression}"/>" id="<c:out value="${status.expression}"/>" class="" value="<c:out value="${status.value}"/>"/>
                                <label for="<c:out value="${status.expression}"/>">Name</label>
                            </span>
                            </spring:bind>
                            <spring:bind path="editPostForm.attributes[${status.index}].stringValue">
                            <span>
                                <input type="text" name="<c:out value="${status.expression}"/>" id="<c:out value="${status.expression}"/>" class="" value="<c:out value="${status.value}"/>"/>
                                <label for="<c:out value="${status.expression}"/>">Value</label>
                            </span>
                            </spring:bind>
                            <span>
                                <a class="ui-icon ui-icon-trash" href="#"></a>
                            </span>
                        </div>
                    </c:forEach>
                    <div class="detail-input">
                        <span>
                            <input type="text" id="detailName" name="detailName">
                            <label for="detailName">Name</label>
                        </span>
                        <span>
                            <input type="text" id="detailValue" name="detailValue">
                            <label for="detailValue">Value</label>
                        </span>
                        <span>
                           <a id="newAttribute" class="btn" href="#">Add</a>
                        </span>
                    </div>
                </div>
            </div>
            <div class="clearfix links">
                <form:label path="links">Links</form:label>
                <div id="linkEditor" class="input">
                    <c:forEach var="link" items="${editPostForm.links}" varStatus="status">
                        <div class="clearfix row">
                            <spring:bind path="editPostForm.links[${status.index}].type">
                                <input type="hidden" name="<c:out value="${status.expression}"/>" id="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}"/>"/>
                            </spring:bind>
                            <spring:bind path="editPostForm.links[${status.index}].name">
                            <span>
                                <input type="text" name="<c:out value="${status.expression}"/>" id="<c:out value="${status.expression}"/>" class="field text addr" value="<c:out value="${status.value}"/>"/>
                                <label for="<c:out value="${status.expression}"/>">Alias</label>
                            </span>
                            </spring:bind>
                            <spring:bind path="editPostForm.links[${status.index}].stringValue">
                            <span>
                                <input type="text" name="<c:out value="${status.expression}"/>" id="<c:out value="${status.expression}"/>" class="field text addr" value="<c:out value="${status.value}"/>"/>
                                <label for="<c:out value="${status.expression}"/>">Url</label>
                            </span>
                            </spring:bind>
                            <span class="delete">
                                <a class="ui-icon ui-icon-trash" href="#"></a>
                            </span>
                        </div>
                    </c:forEach>
                    <div class="links-input">
                        <span>
                            <input type="text" id="linksName" name="detailName">
                            <label for="detailName">Alias</label>
                        </span>
                        <span>
                            <input type="text" id="linksValue" name="detailValue">
                            <label for="detailValue">Url</label>
                        </span>
                        <span>
                           <a id="newLink" class="btn" href="#">Add</a>
                        </span>
                    </div>
                </div>
            </div>
        </fieldset>
        <fieldset>
            <legend>Options</legend>
            <div class="clearfix">
                <div class="input">
                    <ul class="inputs-list">
                        <li>
                            <label>
                                <input type="checkbox">
                                <span>Include a boilerplate disclaimer</span>
                            </label>
                        </li>
                        <li>
                            <label>
                                <input type="checkbox">
                                <span>Some screening is required</span>
                            </label>
                        </li>
                        <li>
                            <label>
                                <input type="checkbox">
                                <span>Accepting new clients</span>
                            </label>
                        </li>
                    </ul>
                </div>
            </div>
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
    </form:form>
    <script id="newAttributeTmpl" type="text/html">
        <div class="clearfix editor-row">
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
        <div class="clearfix editor-row">
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
    <script id="newTagTmpl" type="text/html">

    </script>
</section>
