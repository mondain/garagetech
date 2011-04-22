<%@ include file="/WEB-INF/views/partials/taglibs.jsp" %>
<c:url var="cancelUrl" value="/posts"/>
<head>
    <title>${label}</title>
    <script type="text/javascript" src="${ctx}/js/wufoo.js"></script>
    <script type="text/javascript" src="${ctx}/js/jquery.limitMaxLength.js"></script>
    <script type="text/javascript">
        $(function() {
            var $contentCounter = $("#contentCounter"),
                $titleCounter = $("#titleCounter");

            $("#content").limitMaxLength({
                onEdit: function(remaining) {
                    switch (remaining) {
                        case 1:
                            $contentCounter.html(remaining + " character remaining");
                            break;
                        default:
                            $contentCounter.html(remaining + " characters remaining");
                            break;
                    }
                    if (remaining > 0) {
                        $contentCounter.removeClass("error");
                    }
                },
                onLimit: function() {
                    $contentCounter.addClass("error");
                }
            });

            $("#title").limitMaxLength({
                onEdit: function(remaining) {
                    switch (remaining) {
                        case 1:
                            $titleCounter.html(remaining + " character remaining");
                            break;
                        default:
                            $titleCounter.html(remaining + " characters remaining");
                            break;
                    }
                    if (remaining > 0) {
                        $titleCounter.removeClass("error");
                    }
                },
                onLimit: function() {
                    $titleCounter.addClass("error");
                }
            });
        });
    </script>
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
    <form:hidden path="type"/>
    <ul>
        <form:errors path="*" cssClass="error" element="li"/>
        <li>
            <form:label path="title" cssClass="desc">Teaser <span class="req">*</span></form:label>
            <div>
                <form:textarea path="title" id="title" cssClass="field textarea" maxlength="140" tabindex="1"/>
                <form:label id="titleCounter" path="title">&nbsp;</form:label>
            </div>
        </li>
        <%--li>
            <section class="featured group">
                <article class="group">
                    <div class="feature">
                        <div class="feature-img">
                            <a href="">
                                <img alt="carlsz" src="${ctx}/img/carlsz.jpg" width="154" height="154" border="0"/>
                            </a>
                        </div>
                    </div>
                </article>
                <article class="group">
                    <div class="feature">
                        <div class="feature-img">
                            <a href="">
                                <img alt="carlsz" src="${ctx}/img/carlsz.jpg" width="154" height="154" border="0"/>
                            </a>
                        </div>
                    </div>
                </article>
                <article class="group">
                    <div class="feature">
                        <div class="feature-img">
                            <a href="">
                                <img alt="carlsz" src="${ctx}/img/carlsz.jpg" width="154" height="154" border="0"/>
                            </a>
                        </div>
                    </div>
                </article>
                <article class="group">
                    <div class="feature">
                        <div class="feature-img">
                            <a href="">
                                <img alt="carlsz" src="${ctx}/img/carlsz.jpg" width="154" height="154" border="0"/>
                            </a>
                        </div>
                    </div>
                </article>
                <article class="group last">
                    <div class="feature">
                        <div class="feature-img">
                            <a href="">
                                <img alt="carlsz" src="${ctx}/img/carlsz.jpg" width="154" height="154" border="0"/>
                            </a>
                        </div>
                    </div>
                </article>
            </section>
        </li--%>
        <li>
            <form:label path="content" cssClass="desc">Content <span class="req">*</span></form:label>
            <div>
                <form:textarea path="content" id="content" cssClass="field textarea large" maxlength="2000" tabindex="2"/>
                <form:label id="contentCounter" path="content">&nbsp;</form:label>
            </div>
        </li>
        <li class="leftHalf">
            <form:label path="phone" cssClass="desc">Phone Number <span class="req">*</span></form:label>
            <div>
                <form:input path="phone" id="phone" cssClass="field text medium" maxlength="32" tabindex="3" />
                <form:label path="phone">(###) ###-####</form:label>
            </div>
        </li>
        <li class="rightHalf">
            <form:label path="location" cssClass="desc">Location <span class="req">*</span></form:label>
            <div>
                <form:input path="location" id="location" cssClass="field text medium" maxlength="32" tabindex="4" />
                <form:label path="location">Example: Some Location, CA or 95129</form:label>
            </div>
        </li>
        <li class="buttons">
            <c:choose>
                <c:when test="${isEdit}">
                    <input id="saveBtn" class="btTxt" type="submit" tabindex="5" value="Update"/>
                </c:when>
                <c:otherwise>
                    <input id="saveBtn" class="btTxt" type="submit" tabindex="5" value="Create"/>
                </c:otherwise>
            </c:choose>
            <a href="${cancelUrl}">Or, cancel and return to the list of posts.</a>
        </li>
    </ul>
</form:form>