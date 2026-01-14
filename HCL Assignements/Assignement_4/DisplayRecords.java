
//	Write a program to display records of all Students whose semester is 7 and branch is EC.


package Assignement_4;

import java.sql.*;

public class DisplayRecords {

	public static void main(String[] args) {
		try {
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/StudentsDetail", "root", "Origin@123");

			String query = "SELECT * FROM Students WHERE semester=7 AND branch='EC'";

			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(query);

			while (rs.next()) {
				System.out.println(rs.getInt("id") + "  " + rs.getString("name") + "  " + rs.getString("branch") + "  "
						+ rs.getInt("semester") + "  " + rs.getDouble("percentage") + "  "
						+ rs.getInt("year_of_passing"));
			}

			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
