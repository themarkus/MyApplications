package behnke.mark.StudyApplication;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
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
            Map<String, String> errors = new HashMap<>();

            final String name = req.getParameter("name");
            final String link = req.getParameter("link");

            if (name == null || name.length() == 0) {
                errors.put("name", "Please enter a name");
            } else if (name.length() < 5) {
                errors.put("name", "This name is too short");
            }

            if (link == null || link.length() == 0) {
                errors.put("link", "Please enter a URL");
            } else {
                try {
                   URL url = new URL(link); 
                   
                   if (!url.getProtocol().equals("https")) {
                       errors.put("link", "Only secure links, please");
                   }
                } catch (MalformedURLException e) {
                    errors.put("link", e.getMessage());              
                }
            }

            if (errors.isEmpty()) {
                bookmark.setName(name);
                bookmark.setLink(link);

                bookmarkManager().updateBookmark(bookmark);

                req.getSession().setAttribute("flash", "The bookmark was updated, thanks!");
                resp.sendRedirect("/bookmarks/");
            } else {
                req.setAttribute("errors", errors);
                req.setAttribute("bookmark", bookmark);
                req.getRequestDispatcher("/WEB-INF/edit.jsp").forward(req, resp);
            }
        }
    }

}
