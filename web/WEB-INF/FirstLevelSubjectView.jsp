<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>My Bookmarks</title>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" integrity="sha384-WskhaSGFgHYWDcbwN70/dfYBj47jz9qbsMId/iRN3ewGhXQFZCSftd1LZCfmhktB" crossorigin="anonymous">
    </head>
    <body>
        <div class="containter">
            <div class="jumbotron">
                <h1 class="display-4">First Level Subjects</h1>
            </div>

            <c:if test="${not empty flash}">
                <div class="alert alert-info" role="alert">
                    <c:out value="${flash}" />
                </div> 
            </c:if>
            
            <ul class="list-group">
                <c:forEach items="${firstLevelSubjects}" var="firstLevelSubject">
                    <li class="list-group-item mb-2">

                        <a href="<c:out value='${firstLevelSubject.link}' />">
                            <c:out value="${firstLevelSubject.name}" />
                        </a>                  
                        [<fmt:formatDate pattern="EEE, d MMM yyyy HH:mm" value="${firstLevelSubject.createdAt}"/>]


                        <form action="/StudyApplication/FirstLevelSubject/Edit" method="GET">
                            <input type="hidden" name="id" 
                                   value="<c:out value='${firstLevelSubject.id}' />" />
                            <input type="submit" 
                                   class="btn btn-primary"
                                   value="edit"/>
                        </form>

                        <form action="/StudyApplication/FirstLevelSubject/Delete" method="POST">
                            <input type="hidden" name="id" 
                                   value="<c:out value='${firstLevelSubject.id}' />" />
                            <input type="submit" 
                                   class="btn btn-danger"
                                   value="delete"/>
                        </form>

                    </li>                
                </c:forEach>
            </ul>

            <a class="btn btn-primary" href="/StudyApplication/FirstLevelSubject/Create">Add a new First Level Subject</a>
        </div>

        <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js" integrity="sha384-smHYKdLADwkXOn1EmN1qk/HfnUcbVRZyYmZ4qpPea6sjB/pTJ0euyQp0Mk8ck+5T" crossorigin="anonymous"></script>
    </body>
</html>
