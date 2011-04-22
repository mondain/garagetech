<%@ include file="/WEB-INF/views/partials/taglibs.jsp" %>
<c:url var="joinUrl" value="/join"/>
<c:url var="loginUrl" value="/j_spring_security_check"/>
<c:url var="lostPasswordUrl" value="security"/>
<head>
    <title>Sign in</title>
    <script type="text/javascript" src="${ctx}/js/wufoo.js"></script>
    <script type="text/javascript">
        $(function(){
            $("#username").select().parents("li").addClass("focused");
        });
    </script>
</head>
<header class="info">
    <h1>Sign in</h1>
    <h2>Sign in to join in the fun now! Not a member yet? <a href="${joinUrl}">Create your free account now.</a></h2>
</header>
<form action="${loginUrl}" method="POST" class="wufoo topLabel page">
    <ul>
        <c:if test="${pageContext.request.queryString == 'error'}">
            <li class="error">
                <fmt:message key="error.bad.credentials" />
            </li>
        </c:if>
        <li class="leftHalf">
            <label for="username" class="desc">Username or email <span class="req">*</span></label>
            <div>
                <input type="text" name="j_username" id="username" class="field text medium" tabindex="1"/>
                <label for="username">Example: john123 or john@example.com</label>
            </div>
        <li class="rightHalf"></li>
        <li class="leftHalf">
            <label for="password" class="desc">Password <span class="req">*</span></label>
            <div>
                <input type="password" name="j_password" id="password" class="field text medium" maxlength="32" tabindex="2" />
                <label for="password">Remember, your password is case sensitive. <a href="${lostPasswordUrl}">Forgot your password?</a></label>
            </div>
        <li class="rightHalf"></li>
        <li class="leftHalf">
            <div>
                <input type="checkbox" name="j_persist" id="persist" class="field checkbox" tabindex="3" />
                <label for="persist" class="choice">Remember Me?</label>
            </div>
        </li>
        <li class="buttons">
            <input id="saveForm" class="btTxt" type="submit" tabindex="4" value="Sign In"/> 
        </li>
    </ul>
</form>