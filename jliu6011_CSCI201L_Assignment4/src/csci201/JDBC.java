package csci201;
import java.sql.*;
// Code template from https://www.tutorialspoint.com/jdbc/jdbc-create-database.htm

public class JDBC {
	// JDBC driver name and database URL
	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	static final String DB_URL = "jdbc:mysql://localhost/";

	// Database credentials
	static final String USER = "root";
	static final String PASS = "root";

	public static void main(String[] args) {
		Connection conn = null;
		Statement stmt = null;
		try {
			// STEP 2: Register JDBC driver
			Class.forName("com.mysql.jdbc.Driver");

			// STEP 3: Open a connection
			System.out.println("Connecting to database...");
			conn = DriverManager.getConnection(DB_URL, USER, PASS);

			System.out.println("Creating database...");
			stmt = conn.createStatement();
			String sql = "CREATE DATABASE assignment4";
			stmt.executeUpdate(sql);
			
			sql = "CREATE TABLE Users " + "(email VARCHAR(30) not NULL, " + " username VARCHAR(15) not NULL, " + 
	                   " password VARCHAR(30))"; 
			stmt.executeUpdate(sql);
			sql = "INSERT INTO Users (email, username, password) VALUES ('";
		} catch (SQLException se) {
			// Handle errors for JDBC
			se.printStackTrace();
		} catch (Exception e) {
			// Handle errors for Class.forName
			e.printStackTrace();
		} finally {
			// finally block used to close resources
			try {
				if (stmt != null)
					stmt.close();
			} catch (SQLException se2) {
			} // nothing we can do
			try {
				if (conn != null)
					conn.close();
			} catch (SQLException se) {
				se.printStackTrace();
			} // end finally try
		} // end try
		System.out.println("Goodbye!");
	}// end main
}// end JDBCExample