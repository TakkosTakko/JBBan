package de.evikookie.jbban.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Set;

import de.evikookie.jbban.JBBan;
import org.bukkit.Bukkit;

public class MySQL {
    public static String username = SetupMySQL.username();

    public static String password = SetupMySQL.password();

    public static String database = SetupMySQL.database();

    public static String host = "localhost";

    public static String port = "3306";

    public static Connection con;

    public static void connect() {
        if (!isConnected())
            try {
                con = DriverManager.getConnection("jdbc:mysql://" + host + ":" + port + "/" + database, username, password);
                Bukkit.getConsoleSender().sendMessage(Config.Prefix() + "MySQL Verbindung aufgebaut!");
                JBBan.save = true;
            } catch (SQLException e) {
                JBBan.save = false;
                Bukkit.getConsoleSender().sendMessage(Config.Prefix() + "MySQL Verbindung NICHT aufgebaut!");
            }
    }

    public static void close() {
        if (isConnected())
            try {
                con.close();
                Bukkit.getConsoleSender().sendMessage(Config.Prefix() + "MySQL Verbindung geschlossen!");
            } catch (SQLException e) {
                e.printStackTrace();
            }
    }

    public static boolean isConnected() {
        return (con != null);
    }

    public static void createTable() {
        if (isConnected())
            try {
                con.createStatement().execute("CREATE TABLE IF NOT EXISTS BannedPlayers (SpielerName VARCHAR(100), UUID VARCHAR(100), Ende VARCHAR(100), Grund VARCHAR(100))");
            } catch (SQLException e) {
                e.printStackTrace();
            }
    }

    public static void update(String qry) {
        if (isConnected())
            try {
                con.createStatement().executeUpdate(qry);
            } catch (SQLException e) {
                e.printStackTrace();
            }
    }

    public static ResultSet getResult(String qry) {
        if (isConnected())
            try {
                return con.createStatement().executeQuery(qry);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        return null;
    }
}
