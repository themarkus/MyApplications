<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Edit Bookmark</title>
    </head>
    <body>
        <form action="/bookmarks/edit" method="POST">  
            <input type="hidden" name="id" 
                   value='<c:out value="${bookmark.id}" />' />
            Name: <input type="text" name="name" 
                         value='<c:out value="${bookmark.name}"/>'/>
            <br />
            Link: <input type="text" name="link" 
                         value='<c:out value="${bookmark.link}"/>'/>
            <br />
            <input type="submit" value="save" />
        </form>
    </body>
</html>
