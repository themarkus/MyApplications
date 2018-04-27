package edu.austincc.bookmarks;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.sql.DataSource;

public class BookmarkManager extends DBManager {

    private final DataSource dataSource;

    public BookmarkManager(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    private Bookmark bookmarkFromDB(ResultSet resultSet) throws SQLException {
        return new Bookmark(resultSet.getString("name"),
                resultSet.getString("link"));
    }
   
    public List<Bookmark> allBookmarks() {
        ArrayList<Bookmark> bookmarks = new ArrayList<>();

        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            connection = dataSource.getConnection();
            statement = connection.prepareStatement("SELECT * FROM bookmarks");
            resultSet = statement.executeQuery();

            while (resultSet.next()) {
                bookmarks.add(bookmarkFromDB(resultSet));
            }

        } catch (SQLException ex) {
            ex.printStackTrace();

        } finally {
            close(resultSet);
            close(statement);
            close(connection);
        }

        return bookmarks;
    }

    void addBookmark(Bookmark bookmark) {
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = dataSource.getConnection();
            statement = connection.prepareStatement("INSERT INTO BOOKMARKS (NAME, LINK) VALUES (?, ?)");

            statement.setString(1, bookmark.getName());
            statement.setString(2, bookmark.getLink());
            
            boolean ok = statement.execute();
            System.out.println("OK? " + ok);

        } catch (SQLException ex) {
            ex.printStackTrace();

        } finally {
            close(statement);
            close(connection);
        }
    }

}
