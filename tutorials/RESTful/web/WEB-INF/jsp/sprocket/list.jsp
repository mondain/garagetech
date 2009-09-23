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
            <h1>List Sprockets</h1>
            <p>The available sprockets are listed below. Click on an item in the list to view it's details.</p>
            <br/>
            <ul>
                <c:forEach items="${sprockets}" var="sprocket">
                    <c:url var="detailsUrl" value='/sprocket/display.do?sprocketId=${sprocket.id}'/>
                    <c:url var="editUrl" value='/sprocket/edit.do?sprocketId=${sprocket.id}'/>
                    <li><a href="${detailsUrl}">${sprocket.name}</a> <a href="${editUrl}">[ Edit ]</a></li>
                </c:forEach>
            </ul>
        </div>
    </body>
</html>