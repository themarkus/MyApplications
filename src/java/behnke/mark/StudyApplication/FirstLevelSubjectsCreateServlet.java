package behnke.mark.StudyApplication;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/new")
public class FirstLevelSubjectsCreateServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/newlink.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        BookmarkManager manager = (BookmarkManager) getServletContext().getAttribute("bookmarkManager");

        String name = req.getParameter("name");
        String link = req.getParameter("link");

        Bookmark bookmark = new Bookmark(name, link);
        manager.addBookmark(bookmark);

        resp.sendRedirect("/bookmarks");
    }
}
