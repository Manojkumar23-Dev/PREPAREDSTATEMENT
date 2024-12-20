package javadatabase;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class STATEMENT {
    private static final String URL = "jdbc:mysql://localhost:3306/travel";
    private static final String USER = "root";
    private static final String PASSWORD = "Manoj@2002";

    public static void main(String[] args) 
    {
        Connection connection = null;
        Statement statement = null;

        try {
            // Step 1: Establish a connection
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("Database connected successfully!");

            // Step 2: Create a Statement
            statement = connection.createStatement();

            // Step 3: Execute a SQL query
            String query = "SELECT * FROM travel.buses";
            ResultSet resultSet = statement.executeQuery("SELECT * FROM buses where busno=1423");

            // Step 4: Process the results
            while (resultSet.next()) {
                int busno= resultSet.getInt("busno");
                String busdriver = resultSet.getString("busdriver");
                String buslocation = resultSet.getString("buslocation");
                String bustiming = resultSet.getString("bustiming");
                System.out.println("BUSNO: " + busno + ", BUSDRIVER: " + busdriver + ", BUSLOCATION: " + buslocation +", BUSTIMING: "+ bustiming);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                // Step 5: Close resources
                if (statement != null) statement.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}