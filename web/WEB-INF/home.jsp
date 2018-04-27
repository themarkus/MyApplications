<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>My Bookmarks!</title>
    </head>
    <body>
        <h1>Bookmarks:</h1>

        <ul>
            <c:forEach items="${bookmarks}" var="bookmark">
                <li><a href="${bookmark.link}">
                        <c:out value="${bookmark.name}" />
                    </a></li>
                </c:forEach>
        </ul>
        
        
        <a href="/bookmarks/new">Add a new Link</a>
    </body>
</html>
