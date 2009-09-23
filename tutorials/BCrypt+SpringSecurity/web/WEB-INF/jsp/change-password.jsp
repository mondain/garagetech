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
    <title>Change Password</title>
</head>
<div class="info">
    <h3>Change Password</h3>
    <p>Use the form below to update your password.</p>
</div>
<div id="form-wrapper" style="width:50%;">
    <form:form modelAttribute="form" cssClass="wufoo topLabel clearfix">
        <form:errors path="*" cssClass="errorBox" element="div" />
        <ul>
            <li>
                <form:label path="original" cssClass="desc">Enter your original password <span class="req">*</span></form:label>
                <div>
                    <form:password path="original" id="original" cssClass="field text large" maxlength="32" tabindex="1" />
                    <form:label path="original">Note: Your password is case sensative.</form:label>
                </div>
            </li>
            <li class="leftHalf">
                <form:label path="password" cssClass="desc">Choose a new password <span class="req">*</span></form:label>
                <div>
                    <form:password path="password" id="password" cssClass="field text medium" maxlength="255" tabindex="2" />
                    <form:label path="password">Must be at least 6 characters long.</form:label>
                </div>
            </li>
            <li class="rightHalf">
                <form:label path="confirm" cssClass="desc">Verify your new password <span class="req">*</span></form:label>
                <div>
                    <form:password path="confirm" id="confirm" cssClass="field text medium" maxlength="255" tabindex="3" />
                    <form:label path="confirm"></form:label>
                </div>
            </li>
            <li class="buttons">
                <input id="saveForm" class="btTxt" type="submit" tabindex="4" value="Change Password"/>
            </li>
            <li class="cancel">
                <a href="<c:url value='/'/>">Or, return to the home page.</a>
            </li>
        </ul>
    </form:form>
    <script type="text/javascript">
        $(function(){
            $("#original").focus();
        });
    </script>
</div>
