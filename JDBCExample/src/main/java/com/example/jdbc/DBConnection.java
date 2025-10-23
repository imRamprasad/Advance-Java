package com.example.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

    private static final String URL = "jdbc:mysql://localhost:3306/mydb"; // your DB
    private static final String USER = "root"; // your DB username
    private static final String PASSWORD = "yourpassword"; // your DB password

    public static Connection getConnection() throws SQLException, ClassNotFoundException {
        // Load MySQL driver (optional in modern JDBC)
        Class.forName("com.mysql.cj.jdbc.Driver");
        // Create connection
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
