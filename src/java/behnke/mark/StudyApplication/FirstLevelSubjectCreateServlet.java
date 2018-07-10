package behnke.mark.StudyApplication;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/FirstLevelSubject/Create")
public class FirstLevelSubjectCreateServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/FirstLevelSubjectCreate.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        FirstLevelSubjectManager manager = (FirstLevelSubjectManager) getServletContext().getAttribute("firstLevelSubjectManager");

        String name = req.getParameter("name");
        String link = req.getParameter("link");

        FirstLevelSubject firstLevelSubject = new FirstLevelSubject(name, link);
        manager.addFirstLevelSubject(firstLevelSubject);

        resp.sendRedirect("/StudyApplication/FirstLevelSubject/View");
    }
}
