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
<c:url var="changePasswordUrl" value="/password/change.do"/>
<c:url var="homeUrl" value="/"/>
<c:url var="joinUrl" value="/join.do"/>
<c:url var="loginUrl" value="/login.do"/>
<c:url var="logoutUrl" value="/signout/"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
	<head>
        <c:set var="pageTitle" value="Using BCrypt with Spring Security"/>
        <title>${pageTitle} - <decorator:title default="Welcome"/></title>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
        <meta name="author" content="Carl Sziebert"/>
        <meta name="copyright" content="Copyright (c) 2009 Carl Sziebert"/>
        <link rel="stylesheet" type="text/css" href="${ctx}/css/screen.css" />
        <script type="text/javascript" src="${ctx}/js/jquery-1.3.2.min.js"></script>
        <decorator:head/>
	</head>
	<body>
        <div class="utility clearfix">
            <div class="inner">
                <span>
                    <security:authorize ifAnyGranted="ROLE_USER">
                        Hello again, <b><security:authentication property="principal.username"/></b>!
                    </security:authorize>
                    <security:authorize ifNotGranted="ROLE_USER">
                        <a href="${loginUrl}">Sign in</a> or <a href="${joinUrl}">create an account</a>.
                    </security:authorize>
                </span>
                <ul class="clearfix">
                    <security:authorize ifAnyGranted="ROLE_USER">
                        <li><a href="${changePasswordUrl}">Change Password</a></li>
                        <li><a href="${logoutUrl}">Sign Out</a></li>
                    </security:authorize>
                    <security:authorize ifNotGranted="ROLE_USER">
                        <li><a href="${joinUrl}">Create an Account</a></li>
                        <li><a href="${loginUrl}">Sign In</a></li>
                    </security:authorize>
                </ul>
            </div>
        </div>
        <div class="header">
            <div class="inner">
                <h1>${pageTitle}</h1>
            </div>
        </div>
        <div class="navigation clearfix">
            <div class="inner">
                <ul>
                    <li><a href="${homeUrl}">Home</a></li>
                </ul>
            </div>
        </div>
        <div class="container clearfix">
            <div class="inner">
                <decorator:body/>
            </div>
        </div>
        <div class="footer clearfix">
            <div class="inner">
                <p>Copyright &#169; 2009 Carl Sziebert, All Rights Reserved.</p>
            </div>
        </div>
	</body>
</html>

