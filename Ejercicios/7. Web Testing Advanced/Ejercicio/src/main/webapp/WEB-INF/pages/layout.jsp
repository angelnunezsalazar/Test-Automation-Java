<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8" />
        <title>Fourth Coffee</title>
        <link href="/resources/site.css" rel="stylesheet" type="text/css" />
    </head>
    
    <body>
        <div id="page">
            <div id="header">
                <p class="site-title">
                    <a href="/">Fourth Coffee</a></p>
                <ul id="menu">
                    <li><a href="/">Home</a></li>
                </ul>
            </div>
            <div id="body">
                <tiles:insertAttribute name="body" />
            </div>
            <div id="footer">
                &copy;2013- Fourth Coffee
            </div>
        </div>
    </body>
</html>