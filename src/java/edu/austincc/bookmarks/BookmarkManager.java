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
//        SimpleDateFormat format = new SimpleDateFormat("EEE, MMM d, ''yy");
//        Timestamp timestamp = resultSet.getTimestamp("created_at");
//        String formattedTimestamp = format.format(timestamp);   
//        System.out.println("-- " + formattedTimestamp); 

        return new Bookmark(
                resultSet.getInt("id"),
                resultSet.getString("name"),
                resultSet.getString("link"),
                resultSet.getTimestamp("created_at"));
    }

    public Bookmark bookmarkById(int id) {
        Bookmark bookmark = null;

        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            connection = dataSource.getConnection();
            statement = connection.prepareStatement("SELECT * FROM bookmarks where id=?");
            statement.setInt(1, id);
            resultSet = statement.executeQuery();

            while (resultSet.next()) {
                bookmark = bookmarkFromDB(resultSet);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();

        } finally {
            close(resultSet);
            close(statement);
            close(connection);
        }

        return bookmark;
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
            // we should really do something with this...

        } catch (SQLException ex) {
            ex.printStackTrace();

        } finally {
            close(statement);
            close(connection);
        }
    }

    public void deleteById(int id) {
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = dataSource.getConnection();
            statement = connection.prepareStatement("delete from bookmarks where id=?");
            statement.setInt(1, id);

            boolean ok = statement.execute();
            //return this to the caller
            System.out.println("DELETE OK? " + ok);

        } catch (SQLException ex) {
            ex.printStackTrace();

        } finally {
            close(connection);
            close(statement);
        }
    }

    public void updateBookmark(Bookmark bookmark) {
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = dataSource.getConnection();
            // we should rename "created_at" to "last_modified"
            statement = connection.prepareStatement("update bookmarks set name=?, link=?, created_at = current_timestamp where id=?");
            statement.setString(1, bookmark.getName());
            statement.setString(2, bookmark.getLink());
            statement.setInt(3, bookmark.getId());

            boolean ok = statement.execute();
            // return this to the caller
            System.out.println("UPDATE OK? " + ok);

        } catch (SQLException ex) {
            ex.printStackTrace();

        } finally {
            close(connection);
            close(statement);
        }
    }
}
