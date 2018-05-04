package edu.austincc.bookmarks;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("")
public class HomeServlet extends HttpServlet {

    private BookmarkManager findManager() {
        return (BookmarkManager) getServletContext().getAttribute("bookmarkManager");
    }
    
    private void setFlash(HttpServletRequest req) {
        String flash = (String) req.getSession().getAttribute("flash");
        if (flash != null) {
            req.getSession().removeAttribute("flash");
            req.setAttribute("flash", flash);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        setFlash(req);

        req.setAttribute("bookmarks", findManager().allBookmarks());
        req.getRequestDispatcher("/WEB-INF/home.jsp").forward(req, resp);
    }

}
