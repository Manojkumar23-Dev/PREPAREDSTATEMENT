package javadatabase;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class CALLABELSTATEMENT {
	private static final String URL = "jdbc:mysql://localhost:3306/jdbc";
    private static final String USER = "root";
    private static final String PASSWORD = "Manoj@2002";
	public static void main(String[] args) throws SQLException {
		// TODO Auto-generated method stub
		Connection connection = null;
        connection = DriverManager.getConnection(URL, USER, PASSWORD);

	      //Getting the connection
	      System.out.println("Connection established......");
	      //Preparing a CallableStateement
	      CallableStatement cstmt = connection.prepareCall("insert into emp values(?, ?, ?)");

	      cstmt.setString(1, "Raghav");
	      cstmt.setInt(2, 3000);
	      cstmt.setString(3, "Hyderabad");

	      cstmt.setString(1, "Kalyan");
	      cstmt.setInt(2, 4000);
	      cstmt.setString(3, "Vishakhapatnam");

	      cstmt.setString(1, "Rukmini");
	      cstmt.setInt(2, 5000);
	      cstmt.setString(3, "Delhi");

	      cstmt.setString(1, "Archana");
	      cstmt.setInt(2, 15000);
	      cstmt.setString(3, "Mumbai");

	      cstmt.execute();
	      System.out.println("Rows inserted ....");
	   }
	}