<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Add a new link!</title>
    </head>
    <body>
        <form method="POST" action="/bookmarks/new">
            <input type="text" name="name" placeholder="Site name" /><br />
            <input type="test" name="link" placeholder="URL" /> <br />
            
            <input type="submit" value="Save" />    
        </form>    
    </body>
</html>
