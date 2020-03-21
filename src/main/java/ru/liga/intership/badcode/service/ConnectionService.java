package ru.liga.intership.badcode.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class ConnectionService {
    private static final String DB = "jdbc:hsqldb:mem:test";
    private static final String USER = "sa";
    private static final String PASS = "";


    public static Statement getStatement(Connection conn) throws SQLException {
        return conn.createStatement();
    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB, USER, PASS);
    }

    public static void closeConnection(Connection connection) throws SQLException {
        connection.close();
    }
}
