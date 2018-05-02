package edu.austincc.bookmarks;


import edu.austincc.bookmarks.BookmarkManager;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/delete")
public class DeleteBookmarkServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        
        BookmarkManager manager = (BookmarkManager) getServletContext().getAttribute("bookmarkManager");
        manager.deleteById(id);
        
        resp.sendRedirect("/bookmarks/");
    }


}
