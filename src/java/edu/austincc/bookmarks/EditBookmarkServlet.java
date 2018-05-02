package edu.austincc.bookmarks;


import edu.austincc.bookmarks.Bookmark;
import edu.austincc.bookmarks.BookmarkManager;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/edit")
public class EditBookmarkServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));

        BookmarkManager manager = (BookmarkManager) getServletContext().getAttribute("bookmarkManager");

        Bookmark bookmark = (Bookmark) manager.bookmarkById(id);

        if (bookmark == null) {
            resp.sendError(404);
        } else {
            req.setAttribute("bookmark", bookmark);
            req.getRequestDispatcher("/WEB-INF/edit.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        BookmarkManager manager = (BookmarkManager) getServletContext().getAttribute("bookmarkManager");

        Bookmark bookmark = (Bookmark) manager.bookmarkById(id);
        if (bookmark == null) {
            resp.sendError(404);
        } else {
            bookmark.setName(req.getParameter("name"));
            bookmark.setLink(req.getParameter("link"));

            manager.updateBookmark(bookmark);
       
            resp.sendRedirect("/bookmarks/");
        }
    }

}
