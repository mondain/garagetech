<%@ include file="/WEB-INF/views/partials/taglibs.jsp" %>
<c:url var="accountUrl" value="/account"/>
<c:url var="directoryUrl" value="/directory"/>
<c:url var="homeUrl" value="/"/>
<c:url var="joinUrl" value="/join"/>
<c:url var="loginUrl" value="/login"/>
<c:url var="logoutUrl" value="/logout"/>
<c:url var="postsUrl" value="/posts"/>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <title>Listings - <decorator:title default="Welcome"/></title>
        <meta name="description" content=""/>
        <meta name="keywords" content=""/>
        <meta name="robots" content=""/>
        <!--[if lt IE 9]>
        <script type="text/javascript" src="http://html5shiv.googlecode.com/svn/trunk/html5.js"></script>
        <![endif]-->
        <link rel="stylesheet" href="${ctx}/css/bootstrap-1.1.0.css" type="text/css"/>
        <link rel="stylesheet" href="http://ajax.googleapis.com/ajax/libs/jqueryui/1.8.16/themes/smoothness/jquery-ui.css"/>
        <link rel="stylesheet" href="${ctx}/css/screen.css" type="text/css"/>
        <link rel="stylesheet" href="${ctx}/css/enriched.css" type="text/css"/>
    </head>
    <body>
        <div class="topbar">
            <div class="fill">
                <div class="container">
                    <h3><a href="${homeUrl}">Listings</a></h3>
                    <%@ include file="/WEB-INF/views/partials/nav.jsp" %>
                    <%@ include file="/WEB-INF/views/partials/utilNav.jsp" %>
                </div>
            </div>
        </div>
        <div class="container">
            <decorator:body/>
            <footer>
                <p>Copyright &copy; 2011</p>
            </footer>
        </div>
        <script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.6.2/jquery.min.js"></script>
        <script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jqueryui/1.8.16/jquery-ui.min.js"></script>
        <script type="text/javascript" src="${ctx}/js/application.js"></script>
        <%-- Use decorator:head to consolidate JS files at the bottom of the page for faster page rendering. --%>
        <decorator:head/>
    </body>
</html>