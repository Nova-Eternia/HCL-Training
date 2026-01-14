
//	Write a program to delete of all Students whose records whose year of passing is 2024. and branch is Civil.


package Assignement_4;

import java.sql.*;

public class DeleteRecords {

	public static void main(String[] args) {
		try {
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/StudentsDetail", "root", "Origin@123");

			String query = "DELETE FROM Students WHERE year_of_passing=2024 AND branch='Civil'";

			Statement st = con.createStatement();
			int rows = st.executeUpdate(query);

			System.out.println(rows + " records deleted");

			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
