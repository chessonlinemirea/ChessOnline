package database.matchlog;

import database.ConnectionMySQL;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class UpdateInfMatchlog {
    public static void updateLog(int id_m, String log2) throws SQLException, ClassNotFoundException {

        Connection connection = ConnectionMySQL.getMySQLConnection();

        Statement statement = connection.createStatement();

        String sql = "UPDATE matchlog SET log='" + log2 + "' WHERE id_m=" + id_m;

        int rowCount = statement.executeUpdate(sql);
    }


}
