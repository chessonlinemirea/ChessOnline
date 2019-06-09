package database.player;

import database.ConnectionMySQL;

import java.sql.*;

public class UpdateInfPlayer {
    public static void updateOnline(int id, int online) throws SQLException, ClassNotFoundException {

        Connection connection = ConnectionMySQL.getMySQLConnection();

        Statement statement = connection.createStatement();

        String sql = "UPDATE player SET online=" + online + " WHERE id=" + id;

        int rowCount = statement.executeUpdate(sql);

        //System.out.println("update online = " + rowCount + " from " + id);

    }

    public static void updateMe_Invite(int who, String invite) throws SQLException, ClassNotFoundException {

        Connection connection = ConnectionMySQL.getMySQLConnection();

        Statement statement = connection.createStatement();

        String sql = "UPDATE player SET me_invite=" + who + " WHERE login='" + invite + "'";

        int rowCount = statement.executeUpdate(sql);

        //System.out.println("updateMe_Invite = " + rowCount + " from " + invite);

    }

    public static void updateI_Invite(String mylogin, int who) throws SQLException, ClassNotFoundException {

        Connection connection = ConnectionMySQL.getMySQLConnection();

        Statement statement = connection.createStatement();

        String sql = "UPDATE player SET i_invite=" + who + " WHERE login='" + mylogin + "'";

        int rowCount = statement.executeUpdate(sql);

        //System.out.println("updateMe_Invite = " + rowCount + " from " + mylogin);

    }

    public static void updatePlay(String login, int play) throws SQLException, ClassNotFoundException {

        Connection connection = ConnectionMySQL.getMySQLConnection();

        Statement statement = connection.createStatement();

        String sql = "UPDATE player SET play=" + play + " WHERE login='" + login + "'";

        int rowCount = statement.executeUpdate(sql);

        //System.out.println("updatePlay = " + rowCount + " from " + login);

    }

    public static void updateColor(String login, String color) throws SQLException, ClassNotFoundException {

        Connection connection = ConnectionMySQL.getMySQLConnection();

        Statement statement = connection.createStatement();

        String sql = "UPDATE player SET color='" + color + "' WHERE login='" + login + "'";

        int rowCount = statement.executeUpdate(sql);

    }

    public static void updateMove(String login, String move) throws SQLException, ClassNotFoundException {

        Connection connection = ConnectionMySQL.getMySQLConnection();

        Statement statement = connection.createStatement();

        String sql = "UPDATE player SET move='" + move + "' WHERE login='" + login + "'";

        int rowCount = statement.executeUpdate(sql);

    }

    public static void updateWin(String login) throws SQLException, ClassNotFoundException {

        Connection connection = ConnectionMySQL.getMySQLConnection();

        Statement statement = connection.createStatement();

        String sql = "UPDATE player SET win=win+1 WHERE login='" + login + "'";

        int rowCount = statement.executeUpdate(sql);

    }

    public static void updateLose(String login) throws SQLException, ClassNotFoundException {

        Connection connection = ConnectionMySQL.getMySQLConnection();

        Statement statement = connection.createStatement();

        String sql = "UPDATE player SET lose=lose+1 WHERE login='" + login + "'";

        int rowCount = statement.executeUpdate(sql);

    }

}
