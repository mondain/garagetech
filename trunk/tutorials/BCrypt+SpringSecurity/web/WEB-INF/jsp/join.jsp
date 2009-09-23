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
<c:url var="loginUrl" value="/login.do"/>
<head>
    <title>Create an Account</title>
</head>
<div class="info">
    <h3>Create an Account</h3>
    <p>It's quick and easy. Best of all, it's free. Already a member? <a href="${loginUrl}">Click here</a> to sign in.</p>
</div>
<div id="form-wrapper" style="width:50%;">
    <form:form modelAttribute="form" cssClass="wufoo topLabel clearfix">
        <form:errors path="*" cssClass="errorBox" element="div" />
        <ul>
            <li>
                <form:label path="email" cssClass="desc">1. Enter Your Email Address <span class="req">*</span></form:label>
                <div>
                    <form:input path="email" id="email" cssClass="field text large" maxlength="255" tabindex="1" />
                    <form:label path="email">We will never sell or disclose your email address to third parties.</form:label>
                </div>
            </li>
            <li class="leftHalf">
                <form:label path="password" cssClass="desc">2. Choose a Password <span class="req">*</span></form:label>
                <div>
                    <form:password path="password" id="password" cssClass="field text medium" maxlength="255" tabindex="2" />
                    <form:label path="password">Must be at least 6 characters long.</form:label>
                </div>
            </li>
            <li class="rightHalf">
                <form:label path="confirm" cssClass="desc">Verify Password <span class="req">*</span></form:label>
                <div>
                    <form:password path="confirm" id="confirm" cssClass="field text medium" maxlength="255" tabindex="3" />
                    <form:label path="confirm"></form:label>
                </div>
            </li>
            <li>
                <form:label path="username" cssClass="desc">3. Pick Your Username <span class="req">*</span></form:label>
                <div>
                    <form:input path="username" id="username" cssClass="field text large" maxlength="32" tabindex="4" />
                    <form:label path="username">Example: john123</form:label>
                </div>
            </li>
            <li>
                <div>
                    <form:checkbox path="agreeToTerms" cssClass="field checkbox" tabindex="5"/>
                    <form:label path="agreeToTerms" cssClass="choice">I have read and agree to the <a href="#">Terms of Use</a>.</form:label>
                </div>
            </li>
            <li class="buttons">
                <input id="saveForm" class="btTxt" type="submit" tabindex="6" value="Create Account"/>
            </li>
        </ul>
    </form:form>
    <script type="text/javascript">
        $(function(){
            $("#email").focus();
        });
    </script>
</div>
