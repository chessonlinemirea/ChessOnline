package database.matchlog;

import database.ConnectionMySQL;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class AddMatchlog {
    public static void main(String loginLight, String loginDark) throws SQLException, ClassNotFoundException {

        Connection connection = ConnectionMySQL.getMySQLConnection();

        Statement statement = connection.createStatement();

        String sql = "INSERT INTO matchlog (light, dark) VALUES('" + loginLight + "', '" + loginDark+ "')";

        int rowCount = statement.executeUpdate(sql);
    }
}
