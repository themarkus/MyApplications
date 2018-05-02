<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
                <!-- small injection problem here -->
                <li><a href="${bookmark.link}">
                        <c:out value="${bookmark.name}" />
                    </a>                  
                    [<fmt:formatDate pattern="EEE, d MMM yyyy HH:mm" value="${bookmark.createdAt}"/>]

                    <form action="/bookmarks/edit" method="GET">
                        <input type="hidden" name="id" value="${bookmark.id}" />
                        <input type="submit" value="edit"/>
                    </form>
                        
                    <form action="/bookmarks/delete" method="POST">
                        <input type="hidden" name="id" value="${bookmark.id}" />
                        <input type="submit" value="delete"/>
                    </form>

                </li>                
            </c:forEach>
        </ul>

        <a href="/bookmarks/new">Add a new Link</a>
    </body>
</html>
