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
<head>
    <title>Forgot your password?</title>
</head>
<div class="info">
    <h3>Forgot your password?</h3>
    <p>Simply enter your username and we'll email instructions to you on how to reset your password.</p>
</div>
<div id="form-wrapper" style="width:50%;">
    <form:form modelAttribute="form" cssClass="wufoo topLabel clearfix">
        <form:errors path="*" cssClass="errorBox" element="div" />
        <ul>
            <li>
                <label for="username" class="desc">Username <span class="req">*</span></label>
                <div>
                    <form:input path="username" id="username" cssClass="field text medium" maxlength="32" tabindex="1" />
                    <form:label path="username">Example: john123</form:label>
                </div>
            </li>
            <li class="buttons">
                <input id="saveForm" class="btTxt" type="submit" tabindex="2" value="Submit"/>
            </li>
            <li class="cancel">
                <a href="<c:url value='/login.do'/>">Or, return to the sign in page.</a>
            </li>
        </ul>
    </form:form>
    <script type="text/javascript">
        $(function(){
            $("#username").focus();
        });
    </script>
</div>