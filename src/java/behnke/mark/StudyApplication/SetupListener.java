package behnke.mark.StudyApplication;

import javax.annotation.Resource;
import javax.annotation.sql.DataSourceDefinition;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import javax.sql.DataSource;

@DataSourceDefinition(
        name = "java:app/jdbc/db",
        className = "org.apache.derby.jdbc.ClientDataSource",
        url = "jdbc:derby://localhost:1527/",
        databaseName = "StudyDB",
        user = "app",
        password = "app")

@WebListener
public class SetupListener implements ServletContextListener {

    @Resource(lookup = "java:app/jdbc/db")
    DataSource dataSource;

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        System.out.println("+++++ STARTING First Level Subjects");
        FirstLevelSubjectManager manager = new FirstLevelSubjectManager(dataSource);
        sce.getServletContext().setAttribute("firstLevelSubjectManager", manager);
        
        System.out.println("+++++ STARTING Bookmarks");
        BookmarkManager bmmanager = new BookmarkManager(dataSource);
        sce.getServletContext().setAttribute("bookmarkManager", bmmanager);
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        System.out.println("----- STOPPING First Level Subjects");

    }
}
