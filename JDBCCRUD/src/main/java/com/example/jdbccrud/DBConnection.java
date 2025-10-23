package com.example.jdbccrud;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

    private static final String URL = "jdbc:mysql://localhost:3306/mydb";
    private static final String USER = "root"; // your DB username
    private static final String PASSWORD = "yourpassword"; // your DB password

    public static Connection getConnection() throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver"); // load driver
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
