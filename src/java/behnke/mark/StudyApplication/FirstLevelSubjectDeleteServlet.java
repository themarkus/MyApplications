package behnke.mark.StudyApplication;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/FirstLevelSubject/Delete")
public class FirstLevelSubjectDeleteServlet extends HttpServlet {
    private FirstLevelSubjectManager firstLevelSubjectManager() {
        return (FirstLevelSubjectManager) getServletContext().getAttribute("firstLevelSubjectManager");
    }
    
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));      
        firstLevelSubjectManager().deleteById(id);
        resp.sendRedirect("/StudyApplication/FirstLevelSubject/View");
    }


}
