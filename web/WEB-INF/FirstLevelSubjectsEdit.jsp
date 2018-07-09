<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Edit Bookmark</title>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" integrity="sha384-WskhaSGFgHYWDcbwN70/dfYBj47jz9qbsMId/iRN3ewGhXQFZCSftd1LZCfmhktB" crossorigin="anonymous">
    </head>
    <!-- https://getbootstrap.com/docs/4.1/getting-started/introduction/#starter-template -->
    <!-- https://getbootstrap.com/docs/4.0/components/forms/ -->
    <body>
        <div class="container">
            <form action="/bookmarks/edit" method="POST">  
                <input type="hidden" name="id" 
                       value="<c:out value='${bookmark.id}'/>" />

                <div class="form-row">
                    <div class="col-md-4 mb-3"> 
                        <label>Name</label>
                        <input type="text" name="name" 
                               class="form-control <c:if test='${!empty errors.name}'>is-invalid</c:if>" 
                               value="<c:out value='${param.name == null ? bookmark.name : param.name}'/>"/>
                        <div class="invalid-feedback">
                            <c:out value="${errors.name}" />
                        </div>
                    </div>
                </div>

                <div class="form-row">
                    <div class="col-md-4 mb-3">
                        <label>Link</label>
                        <input type="text" name="link" 
                               class="form-control <c:out value='${empty errors.link ? "":"is-invalid"}'/>"
                               value="<c:out value='${param.link == null ? bookmark.link : param.link}'/>"/>
                        <div class="invalid-feedback">
                            <c:out value="${errors.link}" />
                        </div>
                    </div>
                </div>
                        
                <div class="form-group">
                    <input type="submit" 
                           class="btn btn-primary"
                           value="Save" />
                </div>
            </form>
        </div>

        <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js" integrity="sha384-smHYKdLADwkXOn1EmN1qk/HfnUcbVRZyYmZ4qpPea6sjB/pTJ0euyQp0Mk8ck+5T" crossorigin="anonymous"></script>
    </body>
</html>
