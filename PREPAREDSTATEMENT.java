package javadatabase;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class PREPAREDSTATEMENT {
    // Database credentials
    private static final String URL = "jdbc:mysql://localhost:3306/employee";
    private static final String USER = "root";
    private static final String PASSWORD = "Manoj@2002";

    public static void main(String[] args) {
        Connection connection = null;
        PreparedStatement pst = null;
        Scanner scanner = new Scanner(System.in);

        try {
            // Step 1: Establish a connection
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("Database connected successfully!");

            while (true) {
                System.out.println("Choose an operation:");
                System.out.println("1. Insert Record");
                System.out.println("2. Update Record");
                System.out.println("3. Delete Record");
                System.out.println("4. View All Records");
                System.out.println("5. Exit");
                int choice = scanner.nextInt();

                switch (choice) {
                    case 1:
                        // Insert record
                        String insertQuery = "INSERT INTO emp (id, name, age, location) VALUES (?, ?, ?, ?)";
                        pst = connection.prepareStatement(insertQuery);

                        System.out.print("Enter Employee ID: ");
                        int id = scanner.nextInt();
                        System.out.print("Enter Employee Name: ");
                        String name = scanner.next();
                        System.out.print("Enter Employee Age: ");
                        int age = scanner.nextInt();
                        System.out.print("Enter Employee Location: ");
                        String location = scanner.next();

                        pst.setInt(1, id);
                        pst.setString(2, name);
                        pst.setInt(3, age);
                        pst.setString(4, location);

                        pst.executeUpdate();
                        System.out.println("Record inserted successfully!");
                        System.out.println("Do you want add records:[Yes/No]");
                        String option=scanner.next();
                        if(option.equalsIgnoreCase("NO"))
                        {
                        	break;
                        }
                        break;

                    case 2:
                        // Update record
                        String updateQuery = "UPDATE emp SET name = ?, age = ?, location = ? WHERE id = ?";
                        pst = connection.prepareStatement(updateQuery);

                        System.out.print("Enter Employee ID to update: ");
                        id = scanner.nextInt();
                        System.out.print("Enter New Name: ");
                        name = scanner.next();
                        System.out.print("Enter New Age: ");
                        age = scanner.nextInt();
                        System.out.print("Enter New Location: ");
                        location = scanner.next();

                        pst.setString(1, name);
                        pst.setInt(2, age);
                        pst.setString(3, location);
                        pst.setInt(4, id);

                        int rowsUpdated = pst.executeUpdate();
                        if (rowsUpdated > 0) {
                            System.out.println("Record updated successfully!");
                        } else {
                            System.out.println("Employee ID not found.");
                        }
                        break;

                    case 3:
                        // Delete record
                        String deleteQuery = "DELETE FROM emp WHERE id = ?";
                        pst = connection.prepareStatement(deleteQuery);

                        System.out.print("Enter Employee ID to delete: ");
                        id = scanner.nextInt();
                        int rowsDeleted = pst.executeUpdate();
                        if (rowsDeleted > 0) {
                            System.out.println("Record deleted successfully!");
                        } else {
                            System.out.println("Employee ID not found.");
                        }
                        break;

                    case 4:
                        // View all records
                        String selectQuery = "SELECT * FROM emp";
                        pst = connection.prepareStatement(selectQuery);
                        ResultSet resultSet = pst.executeQuery();

                        System.out.println("Employee Records:");
                        while (resultSet.next()) {
                            System.out.println("ID: " + resultSet.getInt("id") + 
                                               ", Name: " + resultSet.getString("name") +
                                               ", Age: " + resultSet.getInt("age") + 
                                               ", Location: " + resultSet.getString("location"));
                        }
                        break;

                    case 5:
                        // Exit
                        System.out.println("Exiting program.");
                        return;

                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (pst != null) pst.close();
                if (connection != null) connection.close();
                System.out.println("Database connection closed.");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
