
// Write a program to increase 5% to percentage of all Students whose branch is CSE.


package Assignement_4;

import java.sql.*;

public class IncreaseStudentPercentage {

	public static void main(String[] args) {
		try {
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/StudentsDetail", "root", "Origin@123");

			String query = "UPDATE Students SET percentage = percentage + (percentage * 0.05) WHERE branch='CSE'";

			Statement st = con.createStatement();
			int rows = st.executeUpdate(query);

			System.out.println(rows + " CSE student records updated");

			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
