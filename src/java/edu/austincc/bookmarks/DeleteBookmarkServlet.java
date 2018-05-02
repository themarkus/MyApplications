package edu.austincc.bookmarks;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/delete")
public class DeleteBookmarkServlet extends HttpServlet {
    private BookmarkManager bookmarkManager() {
        return (BookmarkManager) getServletContext().getAttribute("bookmarkManager");
    }
    
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));      
        bookmarkManager().deleteById(id);
        resp.sendRedirect("/bookmarks/");
    }


}
