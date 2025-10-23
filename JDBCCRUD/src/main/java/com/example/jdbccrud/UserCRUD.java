package com.example.jdbccrud;

import java.sql.*;
import java.util.Scanner;

public class UserCRUD {

    public static void main(String[] args) {
        try (Connection con = DBConnection.getConnection();
             Scanner sc = new Scanner(System.in)) {

            System.out.println("Connected to database!");
            boolean exit = false;

            while (!exit) {
                System.out.println("\nCRUD Menu:");
                System.out.println("1. CREATE (Insert User)");
                System.out.println("2. READ (View Users)");
                System.out.println("3. UPDATE User");
                System.out.println("4. DELETE User");
                System.out.println("5. EXIT");
                System.out.print("Enter choice: ");
                int choice = sc.nextInt();
                sc.nextLine(); // consume newline

                switch (choice) {
                    case 1: insertUser(con, sc); break;
                    case 2: viewUsers(con); break;
                    case 3: updateUser(con, sc); break;
                    case 4: deleteUser(con, sc); break;
                    case 5: exit = true; break;
                    default: System.out.println("Invalid choice!");
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // CREATE
    public static void insertUser(Connection con, Scanner sc) throws SQLException {
        System.out.print("Enter name: ");
        String name = sc.nextLine();
        System.out.print("Enter age: ");
        int age = sc.nextInt();
        sc.nextLine();

        String sql = "INSERT INTO users (name, age) VALUES (?, ?)";
        try (PreparedStatement pst = con.prepareStatement(sql)) {
            pst.setString(1, name);
            pst.setInt(2, age);
            int rows = pst.executeUpdate();
            System.out.println("Inserted " + rows + " row(s).");
        }
    }

    // READ
    public static void viewUsers(Connection con) throws SQLException {
        String sql = "SELECT * FROM users";
        try (Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            System.out.println("\nID\tName\tAge");
            while (rs.next()) {
                System.out.println(rs.getInt("id") + "\t" + rs.getString("name") + "\t" + rs.getInt("age"));
            }
        }
    }

    // UPDATE
    public static void updateUser(Connection con, Scanner sc) throws SQLException {
        System.out.print("Enter ID to update: ");
        int id = sc.nextInt();
        sc.nextLine();
        System.out.print("Enter new name: ");
        String name = sc.nextLine();
        System.out.print("Enter new age: ");
        int age = sc.nextInt();
        sc.nextLine();

        String sql = "UPDATE users SET name=?, age=? WHERE id=?";
        try (PreparedStatement pst = con.prepareStatement(sql)) {
            pst.setString(1, name);
            pst.setInt(2, age);
            pst.setInt(3, id);
            int rows = pst.executeUpdate();
            System.out.println("Updated " + rows + " row(s).");
        }
    }

    // DELETE
    public static void deleteUser(Connection con, Scanner sc) throws SQLException {
        System.out.print("Enter ID to delete: ");
        int id = sc.nextInt();
        sc.nextLine();

        String sql = "DELETE FROM users WHERE id=?";
        try (PreparedStatement pst = con.prepareStatement(sql)) {
            pst.setInt(1, id);
            int rows = pst.executeUpdate();
            System.out.println("Deleted " + rows + " row(s).");
        }
    }
}
