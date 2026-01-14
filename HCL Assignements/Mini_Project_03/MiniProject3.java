package Mini_Project_03;

import java.sql.*;
import java.util.*;

public class MiniProject3 {
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {

        System.out.println("===== Student Management System =====");
        System.out.print("Enter Username: ");
        String user = sc.next();
        System.out.print("Enter Password: ");
        String pass = sc.next();

        if (!Login.validate(user, pass)) {
            System.out.println("Invalid Login Credentials");
            return;
        }

        System.out.println("Login Successful");

        while (true) {
            System.out.println("\n1. Add Student");
            System.out.println("2. Display All Students");
            System.out.println("3. Search Student by Eno");
            System.out.println("4. Update Student Branch");
            System.out.println("5. Delete Student by Eno");
            System.out.println("6. Display Sorted Students");
            System.out.println("7. Exit");

            System.out.print("Enter choice: ");
            int ch = sc.nextInt();

            try {
                switch (ch) {
                    case 1: addStudent(); break;
                    case 2: displayAll(); break;
                    case 3: searchStudent(); break;
                    case 4: updateBranch(); break;
                    case 5: deleteStudent(); break;
                    case 6: displaySorted(); break;
                    case 7: System.exit(0);
                    default: System.out.println("Invalid choice");
                }
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }

    // 1 Add Student
    static void addStudent() throws Exception {
        Connection con = DBConnection.getConnection();

        System.out.print("Enter Eno: ");
        int eno = sc.nextInt();

        PreparedStatement check = con.prepareStatement("SELECT * FROM students WHERE eno=?");
        check.setInt(1, eno);
        if (check.executeQuery().next())
            throw new Exception("Eno must be unique");

        System.out.print("Enter Name: ");
        String name = sc.next();

        System.out.print("Enter Branch: ");
        String branch = sc.next();
        if (branch.isEmpty())
            throw new EmptyFieldException("Branch cannot be empty");

        System.out.print("Enter Semester: ");
        int sem = sc.nextInt();

        System.out.print("Enter Percentage: ");
        double per = sc.nextDouble();
        if (per <= 0)
            throw new InvalidPercentageException("Percentage must be positive");

        PreparedStatement ps = con.prepareStatement("INSERT INTO students VALUES(?,?,?,?,?)");
        ps.setInt(1, eno);
        ps.setString(2, name);
        ps.setString(3, branch);
        ps.setInt(4, sem);
        ps.setDouble(5, per);

        ps.executeUpdate();
        System.out.println("Student Added Successfully");
    }

    // 2 Display
    static void displayAll() throws Exception {
        Connection con = DBConnection.getConnection();
        ResultSet rs = con.createStatement().executeQuery("SELECT * FROM students");

        while (rs.next()) {
            System.out.println(rs.getInt(1) + " " + rs.getString(2) + " " +
                               rs.getString(3) + " " + rs.getInt(4) + " " +
                               rs.getDouble(5));
        }
    }

    // 3 Search
    static void searchStudent() throws Exception {
        System.out.print("Enter Eno: ");
        int eno = sc.nextInt();

        Connection con = DBConnection.getConnection();
        PreparedStatement ps = con.prepareStatement("SELECT * FROM students WHERE eno=?");
        ps.setInt(1, eno);
        ResultSet rs = ps.executeQuery();

        if (!rs.next())
            System.out.println("Student Not Found");
        else
            System.out.println(rs.getInt(1) + " " + rs.getString(2) + " " + rs.getString(3));
    }

    // 4 Update Branch
    static void updateBranch() throws Exception {
        System.out.print("Enter Eno: ");
        int eno = sc.nextInt();
        System.out.print("Enter New Branch: ");
        String branch = sc.next();

        Connection con = DBConnection.getConnection();
        PreparedStatement ps = con.prepareStatement("UPDATE students SET branch=? WHERE eno=?");
        ps.setString(1, branch);
        ps.setInt(2, eno);

        if (ps.executeUpdate() == 0)
            System.out.println("Student Not Found");
        else
            System.out.println("Branch Updated");
    }

    // 5 Delete
    static void deleteStudent() throws Exception {
        System.out.print("Enter Eno: ");
        int eno = sc.nextInt();

        Connection con = DBConnection.getConnection();
        PreparedStatement ps = con.prepareStatement("DELETE FROM students WHERE eno=?");
        ps.setInt(1, eno);

        if (ps.executeUpdate() == 0)
            System.out.println("Student Not Found");
        else
            System.out.println("Student Deleted");
    }

    // 6 Sorted Display
    static void displaySorted() throws Exception {
        Connection con = DBConnection.getConnection();
        ResultSet rs = con.createStatement().executeQuery(
                "SELECT * FROM students ORDER BY percentage DESC");

        while (rs.next()) {
            System.out.println(rs.getInt(1) + " " + rs.getString(2) + " " + rs.getDouble(5));
        }
    }
}
