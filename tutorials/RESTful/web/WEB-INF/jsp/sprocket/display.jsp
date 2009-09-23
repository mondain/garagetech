<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
	<head>
		<title>RESTful URLs with Spring MVC 2.5 and UrlRewriteFilter</title>
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/reset-fonts.css" media="screen" />
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/main.css" media="screen" />
    </head>
	<body>
        <div id="wrapper">
            <c:choose>
                <c:when test="${not empty sprocket}">
                    <h1>Details: ${sprocket.name}</h1>
                    <p>${sprocket.description}</p>
                    <br/>
                    <p><a href="<c:url value='/sprockets/list.do'/>">Return to the list.</a></p>
                </c:when>
                <c:otherwise>
                    <h1>Opps!</h1>
                    <p>The requested sprocket could not be found!</p>
                </c:otherwise>
            </c:choose>
        </div>
    </body>
</html>