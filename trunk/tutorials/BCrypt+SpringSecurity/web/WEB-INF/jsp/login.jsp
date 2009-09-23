<%--
  ~ Copyright (c) 2009 Carl Sziebert
  ~
  ~ Permission is hereby granted, free of charge, to any person obtaining a copy
  ~ of this software and associated documentation files (the "Software"), to deal
  ~ in the Software without restriction, including without limitation the rights
  ~ to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
  ~ copies of the Software, and to permit persons to whom the Software is
  ~ furnished to do so, subject to the following conditions:
  ~
  ~ The above copyright notice and this permission notice shall be included in
  ~ all copies or substantial portions of the Software.
  ~
  ~ THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
  ~ IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
  ~ FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
  ~ AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
  ~ LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
  ~ OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
  ~ THE SOFTWARE.
  ~
  ~ $Id$
  --%>
<%@ include file="/WEB-INF/jsp/partials/taglibs.jsp" %>
<c:url var="joinUrl" value="/join.do"/>
<head>
    <title>Welcome. Please sign in.</title>
</head>
<div class="info">
    <h3>Back for more fun?</h3>
    <p>Sign in to join in the fun now! Remember that your password is case sensative. Not a member yet? <a href="${joinUrl}">Click here</a> to create your free account now.</p>
</div>
<%@ include file="/WEB-INF/jsp/partials/messages.jsp" %>
<div id="form-wrapper" style="width:50%;">
    <form action="<c:url value='/j_spring_security_check'/>" method="POST">
        <c:if test="${pageContext.request.queryString == 'error'}">
            <div class="errorBox">
                <fmt:message key="error.bad.credentials" />
            </div>
        </c:if>
        <ul>
            <li>
                <label for="username" class="desc">Username <span class="req">*</span></label>
                <div>
                    <input type="text" name="j_username" id="username" class="field text medium" tabindex="1" />
                    <label for="username">Example: john123</label>
                </div>
            </li>
            <li>
                <label for="password" class="desc">Password <span class="req">*</span></label>
                <div>
                    <input type="password" name="j_password" id="password" class="field text medium" maxlength="32" tabindex="2" />
                    <label for="password">Note: Your password is case sensative.</label>
                </div>
            </li>
            <li>
                <span>
                    <input type="checkbox" name="j_persist" id="persist" tabindex="3" />
                    <label for="persist" class="choice">Remember Me?</label>
                </span>
            </li>
            <li class="buttons">
                <input id="saveForm" class="btTxt" type="submit" tabindex="4" value="Sign In"/>
            </li>
            <li class="cancel">
                <a href="<c:url value='/password/lost.do'/>">Forgot your password?</a>
            </li>
        </ul>
    </form>
    <script type="text/javascript">
        $(function(){
            $("#username").focus();
        });
    </script>
</div>
