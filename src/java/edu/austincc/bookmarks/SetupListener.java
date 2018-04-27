package edu.austincc.bookmarks;

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
        databaseName = "bookmarks",
        user = "app",
        password = "app")
@WebListener
public class SetupListener implements ServletContextListener {
    @Resource(lookup = "java:app/jdbc/db")
    DataSource dataSource;

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        System.out.println("+++++ STARTING BOOKMARKS");
        BookmarkManager manager = new BookmarkManager(dataSource);
        sce.getServletContext().setAttribute("bookmarkManager", manager);
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        System.out.println("----- STOPPING BOOKMARKS");

    }
}
