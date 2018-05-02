package edu.austincc.bookmarks;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/edit")
public class EditBookmarkServlet extends HttpServlet {
    private BookmarkManager bookmarkManager() {
        return (BookmarkManager) getServletContext().getAttribute("bookmarkManager");
    }
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));

        Bookmark bookmark = (Bookmark) bookmarkManager().bookmarkById(id);
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

        Bookmark bookmark = bookmarkManager().bookmarkById(id);
        if (bookmark == null) {
            resp.sendError(404);
        } else {
            bookmark.setName(req.getParameter("name"));
            bookmark.setLink(req.getParameter("link"));

            bookmarkManager().updateBookmark(bookmark);

            resp.sendRedirect("/bookmarks/");
        }
    }

}
