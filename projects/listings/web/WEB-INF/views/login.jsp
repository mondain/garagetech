<%@ include file="/WEB-INF/views/partials/taglibs.jsp" %>
<c:url var="joinUrl" value="/join"/>
<c:url var="loginUrl" value="/j_spring_security_check"/>
<c:url var="lostPasswordUrl" value="security"/>
<head>
    <title>Sign in</title>
</head>
<section id="form">
    <header class="page-header">
        <h2>Sign in</h2>
        <p>Sign in to join in the fun now! Not a member yet? <a href="${joinUrl}">Create your free account now.</a></p>
    </header>
    <c:if test="${pageContext.request.queryString == 'error'}">
        <div class="alert-message error">
            <!--a class="close" href="#">×</a-->
            <p><fmt:message key="error.bad.credentials"/></p>
        </div>
    </c:if>
    <form action="${loginUrl}" method="POST">
        <div class="clearfix">
            <label for="username" class="">Username or email</label>
            <div class="input">
                <input type="text" name="j_username" id="username" class="xlarge" tabindex="1"/>
                <span class="help-block"><strong>Example:</strong> john123 or john@example.com</span>
            </div>
        </div>
        <div class="clearfix">
            <label for="password" class="">Password</label>
            <div class="input">
                <input type="password" name="j_password" id="password" class="xlarge" maxlength="32" tabindex="2"/>
                <span class="help-block">Remember, your password is case sensitive.<br/><a href="${lostPasswordUrl}">Forgot your password?</a></span>
            </div>
        </div>
        <div class="clearfix">
            <div class="input">
                <ul class="inputs-list">
                    <li>
                        <label>
                            <input type="checkbox" name="j_persist" id="persist" tabindex="3"/>
                            <span>Remember Me?</span>
                        </label>
                    </li>
                </ul>
            </div>
        </div>
        <div class="actions">
            <input id="saveForm" class="btn primary" type="submit" tabindex="4" value="Sign In"/>
        </div>
    </form>
</section>