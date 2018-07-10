package behnke.mark.StudyApplication;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.sql.DataSource;

public class FirstLevelSubjectManager extends DBManager {

    private final DataSource dataSource;

    public FirstLevelSubjectManager(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    private FirstLevelSubject firstLevelSubjectFromDB(ResultSet resultSet) throws SQLException {
//        SimpleDateFormat format = new SimpleDateFormat("EEE, MMM d, ''yy");
//        Timestamp timestamp = resultSet.getTimestamp("created_at");
//        String formattedTimestamp = format.format(timestamp);   
//        System.out.println("-- " + formattedTimestamp); 

        return new FirstLevelSubject(
                resultSet.getInt("id"),
                resultSet.getString("name"),
                resultSet.getString("link"),
                resultSet.getTimestamp("created_at"));
    }

    public FirstLevelSubject firstLevelSubjectById(int id) {
        FirstLevelSubject firstLevelSubject = null;

        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            connection = dataSource.getConnection();
            statement = connection.prepareStatement("SELECT * FROM firstLevelSubjects where id=?");
            statement.setInt(1, id);
            resultSet = statement.executeQuery();

            while (resultSet.next()) {
                firstLevelSubject = firstLevelSubjectFromDB(resultSet);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();

        } finally {
            close(resultSet);
            close(statement);
            close(connection);
        }

        return firstLevelSubject;
    }

    public List<FirstLevelSubject> allFirstLevelSubjects() {
        ArrayList<FirstLevelSubject> firstLevelSubjects = new ArrayList<>();

        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            connection = dataSource.getConnection();
            statement = connection.prepareStatement("SELECT * FROM firstLevelSubjects");
            resultSet = statement.executeQuery();

            while (resultSet.next()) {
                firstLevelSubjects.add(firstLevelSubjectFromDB(resultSet));
            }

        } catch (SQLException ex) {
            ex.printStackTrace();

        } finally {
            close(resultSet);
            close(statement);
            close(connection);
        }

        return firstLevelSubjects;
    }

    void addFirstLevelSubject(FirstLevelSubject firstLevelSubject) {
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = dataSource.getConnection();
            statement = connection.prepareStatement("INSERT INTO firstLevelSubjects (NAME, LINK) VALUES (?, ?)");

            statement.setString(1, firstLevelSubject.getName());
            statement.setString(2, firstLevelSubject.getLink());

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
            statement = connection.prepareStatement("delete from firstLevelSubjects where id=?");
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

    public void updateFirstLevelSubject(FirstLevelSubject firstLevelSubject) {
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = dataSource.getConnection();
            // we should rename "created_at" to "last_modified"
            statement = connection.prepareStatement("update firstLevelSubjects set name=?, link=?, created_at = current_timestamp where id=?");
            statement.setString(1, firstLevelSubject.getName());
            statement.setString(2, firstLevelSubject.getLink());
            statement.setInt(3, firstLevelSubject.getId());

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
