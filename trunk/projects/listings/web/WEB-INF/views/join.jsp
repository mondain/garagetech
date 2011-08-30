<%@ include file="/WEB-INF/views/partials/taglibs.jsp" %>
<c:url var="loginUrl" value="/login"/>
<head>
    <title>Create an account</title>
</head>
<section id="join">
    <header class="page-header">
        <h2>Create an account</h2>
        <p>It's quick and easy. Best of all, it's free. Already a member? <a href="${loginUrl}">Sign in now.</a></p>
    </header>
    <form:form modelAttribute="joinForm" cssClass="">
        <form:errors path="*" cssClass="alert-message error" element="div"/>
        <div class="clearfix<form:errors path="email"> error</form:errors>">
            <form:label path="email" cssClass="">Email Address</form:label>
            <div class="input">
                <form:input path="email" id="email" cssClass="xlarge" maxlength="255" tabindex="1" />
                <span class="help-block">We will never sell or disclose your email address to anyone.</span>
            </div>
        </div>
        <div class="clearfix<form:errors path="password"> error</form:errors>">
            <form:label path="password" cssClass="">Password</form:label>
            <div class="input">
                <form:password path="password" id="password" cssClass="xlarge" maxlength="25" tabindex="2" />
                <span class="help-block">Must be at least 6 characters long and is case sensitive.</span>
            </div>
        </div>
        <div class="clearfix<form:errors path="username"> error</form:errors>">
            <form:label path="username" cssClass="">Username</form:label>
            <div class="input">
                <form:input path="username" id="username" cssClass="xlarge" maxlength="32" tabindex="4" />
                <span class="help-block"><strong>Example:</strong> john123</span>
            </div>
        </div>
        <div class="clearfix<form:errors path="agreeToTerms"> error</form:errors>">
            <div class="input">
                <ul class="inputs-list">
                    <li>
                        <form:label path="agreeToTerms">
                            <form:checkbox path="agreeToTerms" id="agreeToTerms" tabindex="5"/>
                            <span>I have read and agree to the <a href="#">Terms of Use</a>.</span>
                        </form:label>
                    </li>
                </ul>
            </div>
        </div>
        <div class="actions">
            <input id="saveForm" class="btn primary" type="submit" tabindex="6" value="Create my account"/>
        </div>
    </form:form>
</section>
