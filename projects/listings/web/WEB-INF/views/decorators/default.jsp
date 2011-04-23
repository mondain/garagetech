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
        <link rel="stylesheet" href="${ctx}/css/html5reset.css" type="text/css"/>
        <link rel="stylesheet" href="${ctx}/css/form.css" type="text/css"/>
        <link rel="stylesheet" href="${ctx}/css/screen.css" type="text/css"/>
    </head>
    <body>
        <header>
            <hgroup>
                <h1><a href="${homeUrl}">Listings</a></h1>
                <h2>This is a generic listings site and is used to experiment with Spring, Hibernate and jQuery.</h2>
            </hgroup>
        </header>
        <%@ include file="/WEB-INF/views/partials/nav.jsp" %>
        <section class="group">
            <decorator:body/>
        </section>
        <footer>
            <p>Copyright &copy; 2011</p>
        </footer>
        <script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.5.2/jquery.min.js"></script>
        <%-- Use decorator:head to consolidate JS files at the bottom of the page for faster page rendering. --%>
        <decorator:head/>
    </body>
</html>