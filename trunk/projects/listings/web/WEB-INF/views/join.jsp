<%@ include file="/WEB-INF/views/partials/taglibs.jsp" %>
<c:url var="loginUrl" value="/login"/>
<head>
    <title>Create an account</title>
    <script type="text/javascript" src="${ctx}/js/wufoo.js"></script>
    <script type="text/javascript">
        $(function(){
            var confirm = $("#confirmWrapper");
            $("#email").focus().parents("li").addClass("focused");
            $("#password, #confirm").focus(function() {
                confirm.show();
            }).blur(function() {
                confirm.hide();
            });
            confirm.hide();
        });
    </script>
</head>
<header class="info">
    <h1>Create an account</h1>
    <h2>It's quick and easy. Best of all, it's free. Already a member? <a href="${loginUrl}">Sign in now.</a></h2>
</header>
<form:form modelAttribute="joinForm" cssClass="wufoo topLabel clearfix">
    <ul>
        <form:errors path="*" cssClass="error" element="li"/>        
        <li class="leftHalf">
            <form:label path="email" cssClass="desc">1. Enter your email address <span class="req">*</span></form:label>
            <div>
                <form:input path="email" id="email" cssClass="field text medium" maxlength="255" tabindex="1" />
                <form:label path="email">We will never sell or disclose your email address to anyone.</form:label>
            </div>
        </li>
        <li class="rightHalf"></li>
        <li class="leftHalf">
            <form:label path="password" cssClass="desc">2. Choose your password <span class="req">*</span></form:label>
            <div>
                <form:password path="password" id="password" cssClass="field text medium" maxlength="25" tabindex="2" />
                <form:label path="password">Must be at least 6 characters long and is case sensitive.</form:label>
            </div>
        </li>
        <li id="confirmWrapper" class="rightHalf">
            <form:label path="confirm" cssClass="desc">Verify your password <span class="req">*</span></form:label>
            <div>
                <form:password path="confirm" id="confirm" cssClass="field text medium" maxlength="25" tabindex="3" />
                <form:label path="confirm">&nbsp;</form:label>
            </div>
        </li>
        <li class="leftHalf">
            <form:label path="username" cssClass="desc">3. Pick your username <span class="req">*</span></form:label>
            <div>
                <form:input path="username" id="username" cssClass="field text medium" maxlength="32" tabindex="4" />
                <form:label path="username">Example: john123</form:label>
            </div>
        </li>
        <li class="rightHalf"></li>
        <li>
            <div>
                <form:checkbox path="agreeToTerms" cssClass="field checkbox" tabindex="5"/>
                <form:label path="agreeToTerms" cssClass="choice">I have read and agree to the <a href="#">Terms of Use</a>.</form:label>
            </div>
        </li>
        <li class="buttons">
            <input id="saveForm" class="btTxt" type="submit" tabindex="6" value="Create my account"/>
        </li>
    </ul>
</form:form>