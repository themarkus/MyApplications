package behnke.mark.StudyApplication;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/FirstLevelSubject/View")
public class FirstLevelSubjectViewServlet extends HttpServlet {

    private FirstLevelSubjectManager findManager() {
        return (FirstLevelSubjectManager) getServletContext().getAttribute("firstLevelSubjectManager");
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

        req.setAttribute("firstLevelSubjects", findManager().allFirstLevelSubjects());
        req.getRequestDispatcher("/WEB-INF/FirstLevelSubjectView.jsp").forward(req, resp);
    }

}
