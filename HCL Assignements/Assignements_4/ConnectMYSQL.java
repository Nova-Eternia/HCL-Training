

//Write a program to demonstrate JDBC connection establishment to MySQL. If connections is established then "Connection Successful" message will displayed otherwise "Unable to connect" message should be displayed.

package Assignement_4;

import java.sql.*;

public class ConnectMYSQL {

        public static void main(String[] args) {

                Connection con = null;
                Statement stmt = null;
                ResultSet rs = null;

                try {
                        // Load MySQL Driver
                        Class.forName("com.mysql.cj.jdbc.Driver");

                        // Connect to DB
                        con = DriverManager.getConnection(
                                        "jdbc:mysql://localhost:3306/StudentsDetail", // your database
                                        "root", // Username
                                        "Origin@123" // Passward
                        );
                        System.out.println("Connection Successful");

                        // Create Statement
                        stmt = con.createStatement();

                        // Execute query
                        rs = stmt.executeQuery("SELECT * FROM Student");

                        // Fetch data
                        System.out.println();
                        while (rs.next()) {
                                int id = rs.getInt("id");
                                String name = rs.getString("name");
                                String enroll = rs.getString("enrollment_no");
                                String branch = rs.getString("branch");

                                System.out.println(id + " | " + name + " | " + enroll + " | " + branch);
                        }
                } catch (ClassNotFoundException e) {
                        System.out.println("MySQL Driver not found");
                } catch (SQLException e) {
                        System.out.println("Connection failed: " + e.getMessage());
                } finally {
                        try {
                                if (rs != null)
                                        rs.close();
                                if (stmt != null)
                                        stmt.close();
                                if (con != null)
                                        con.close();
                        } catch (SQLException e) {
                                e.printStackTrace();
                        }
                }

        }
}
