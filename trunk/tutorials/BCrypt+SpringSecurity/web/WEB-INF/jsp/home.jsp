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
<c:url var="loginUrl" value="/login.do"/>
<head>
    <title>Home</title>
</head>
<div class="info">
    <h3>Welcome</h3>
    <p>This application serves as a simple demonstration of using BCrypt with Spring Secruity.</p>
</div>
<%@ include file="/WEB-INF/jsp/partials/messages.jsp" %>
<security:authorize ifAnyGranted="ROLE_USER">
    Hello again, <b><security:authentication property="principal.username"/></b>!
</security:authorize>
<security:authorize ifNotGranted="ROLE_USER">
    <a href="${loginUrl}">Sign in</a> or <a href="${joinUrl}">create an account</a>.
</security:authorize>
