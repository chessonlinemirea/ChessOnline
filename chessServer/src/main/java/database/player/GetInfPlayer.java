package database.player;

import database.ConnectionMySQL;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class GetInfPlayer {
    public static int getMeInvite(int id) throws SQLException, ClassNotFoundException {

        Connection connection = ConnectionMySQL.getMySQLConnection();

        Statement statement = connection.createStatement();

        String sql = "SELECT me_invite FROM player WHERE id=" + id;

        ResultSet rs = statement.executeQuery(sql);

        if (rs.next()) {

            //System.out.println("getMeInvite = " + rs.getInt("me_invite"));

            return rs.getInt("me_invite");

        } else return -2;
    }

    public static int getIInvite(int id) throws SQLException, ClassNotFoundException {

        Connection connection = ConnectionMySQL.getMySQLConnection();

        Statement statement = connection.createStatement();

        String sql = "SELECT i_invite FROM player WHERE id=" + id;

        ResultSet rs = statement.executeQuery(sql);

        if (rs.next()) {

            //System.out.println("getIInvite = " + rs.getInt("i_invite"));

            return rs.getInt("i_invite");

        } else return -2;
    }

    public static int getPlay(int id) throws SQLException, ClassNotFoundException {

        Connection connection = ConnectionMySQL.getMySQLConnection();

        Statement statement = connection.createStatement();

        String sql = "SELECT play FROM player WHERE id=" + id;

        ResultSet rs = statement.executeQuery(sql);

        if (rs.next()) {

            //System.out.println("getPlay = " + rs.getInt("play"));

            return rs.getInt("play");

        } else return -2;
    }

    public static String getColor(int id) throws SQLException, ClassNotFoundException {

        Connection connection = ConnectionMySQL.getMySQLConnection();

        Statement statement = connection.createStatement();

        String sql = "SELECT color FROM player WHERE id=" + id;

        ResultSet rs = statement.executeQuery(sql);

        if (rs.next()) {

            //System.out.println("getPlay = " + rs.getInt("play"));

            return rs.getString("color");

        } else return "-2";
    }

    public static String getMove(int id) throws SQLException, ClassNotFoundException {

        Connection connection = ConnectionMySQL.getMySQLConnection();

        Statement statement = connection.createStatement();

        String sql = "SELECT move FROM player WHERE id=" + id;

        ResultSet rs = statement.executeQuery(sql);

        if (rs.next()) {

            //System.out.println("getPlay = " + rs.getInt("play"));

            return rs.getString("move");

        } else return "-2";
    }

    public static int getWin(int id) throws SQLException, ClassNotFoundException {

        Connection connection = ConnectionMySQL.getMySQLConnection();

        Statement statement = connection.createStatement();

        String sql = "SELECT win FROM player WHERE id=" + id;

        ResultSet rs = statement.executeQuery(sql);

        if (rs.next()) {

            //System.out.println("getPlay = " + rs.getInt("play"));

            return rs.getInt("win");

        } else return -2;
    }

    public static int getLose(int id) throws SQLException, ClassNotFoundException {

        Connection connection = ConnectionMySQL.getMySQLConnection();

        Statement statement = connection.createStatement();

        String sql = "SELECT lose FROM player WHERE id=" + id;

        ResultSet rs = statement.executeQuery(sql);

        if (rs.next()) {

            //System.out.println("getPlay = " + rs.getInt("play"));

            return rs.getInt("lose");

        } else return -2;
    }

    public static int getOnline(int id) throws SQLException, ClassNotFoundException {

        Connection connection = ConnectionMySQL.getMySQLConnection();

        Statement statement = connection.createStatement();

        String sql = "SELECT online FROM player WHERE id=" + id;

        ResultSet rs = statement.executeQuery(sql);

        if (rs.next()) {

            //System.out.println("getPlay = " + rs.getInt("play"));

            return rs.getInt("online");

        } else return -1;

    }

}
