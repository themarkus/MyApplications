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

@WebServlet("/FirstLevelSubject/Edit")
public class FirstLevelSubjectEditServlet extends HttpServlet {

    private FirstLevelSubjectManager firstLevelSubjectManager() {
        return (FirstLevelSubjectManager) getServletContext().getAttribute("firstLevelSubjectManager");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));

        FirstLevelSubject firstLevelSubject = (FirstLevelSubject) firstLevelSubjectManager().firstLevelSubjectById(id);
        if (firstLevelSubject == null) {
            resp.sendError(404);
        } else {
            req.setAttribute("firstLevelSubject", firstLevelSubject);
            req.getRequestDispatcher("/WEB-INF/FirstLevelSubjectEdit.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));

        FirstLevelSubject firstLevelSubject = firstLevelSubjectManager().firstLevelSubjectById(id);
        if (firstLevelSubject == null) {
            resp.sendError(404);
        } else {
            Map<String, String> errors = new HashMap<>();

            final String name = req.getParameter("name");
            final String link = req.getParameter("link");

            if (name == null || name.length() == 0) {
                errors.put("name", "Please enter a name");
            }

            if (link == null || link.length() == 0) {
                errors.put("link", "Please enter a URL");
            }

            if (errors.isEmpty()) {
                firstLevelSubject.setName(name);
                firstLevelSubject.setLink(link);

                firstLevelSubjectManager().updateFirstLevelSubject(firstLevelSubject);

                req.getSession().setAttribute("flash", "The First Level Subject was updated, thanks!");
                resp.sendRedirect("/StudyApplication/FirstLevelSubject/View");
            } else {
                req.setAttribute("errors", errors);
                req.setAttribute("firstLevelSubject", firstLevelSubject);
                req.getRequestDispatcher("/WEB-INF/FirstLevelSubjectEdit.jsp").forward(req, resp);
            }
        }
    }

}
