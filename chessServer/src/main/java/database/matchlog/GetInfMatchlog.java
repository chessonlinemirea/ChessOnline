package database.matchlog;

import database.ConnectionMySQL;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class GetInfMatchlog {
    public static String getLastLog(int id_m) throws SQLException, ClassNotFoundException {

        Connection connection = ConnectionMySQL.getMySQLConnection();

        Statement statement = connection.createStatement();

        String sql = "SELECT log FROM matchlog WHERE id_m=" + id_m;

        ResultSet rs = statement.executeQuery(sql);

        if (rs.next()) {

            //System.out.println("getPlay = " + rs.getInt("play"));

            return rs.getString("log");

        } else return "-1";

    }

    public static int getLastId(String dark, String light) throws SQLException, ClassNotFoundException {

        Connection connection = ConnectionMySQL.getMySQLConnection();

        Statement statement = connection.createStatement();

        String sql = "select MAX(id_m) from matchlog WHERE dark ='" + dark + "' AND light ='" + light + "'";

        ResultSet rs = statement.executeQuery(sql);

        if (rs.next()) {

            //System.out.println("getPlay = " + rs.getInt("play"));

            return rs.getInt(1);

        } else return -1;

    }

}
