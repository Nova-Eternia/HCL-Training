
//	Write a program to create a table called Students using JDBC and insert records into it.


package Assignement_4;

import java.sql.*;

public class CreateTABLE {
    public static void main(String[] args) {
        try {
            Connection con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/StudentsDetail", "root", "Origin@123");

            Statement st = con.createStatement();

            String createTable = "CREATE TABLE IF NOT EXISTS Students (" +
                    "id INT PRIMARY KEY AUTO_INCREMENT," +
                    "name VARCHAR(50)," +
                    "branch VARCHAR(20)," +
                    "semester INT," +
                    "percentage DOUBLE," +
                    "year_of_passing INT)";

            st.executeUpdate(createTable);

            String insert = "INSERT INTO Students (name, branch, semester, percentage, year_of_passing) VALUES" +
                    "('Rahul','CSE',7,75,2024)," +
                    "('Ankit','EC',7,70,2025)," +
                    "('Neha','Civil',6,68,2024)";

            st.executeUpdate(insert);

            System.out.println("Table created and records inserted");

            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
