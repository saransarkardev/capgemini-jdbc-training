//PreparedStatement SELECT Example

import java.sql.*;

public class Main5 {

    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/mydatabase";
        String username = "root";
        String password = "mSm@6951";

        String sqlQuery = "SELECT * FROM employees WHERE name = ?";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("Drivers loaded successfully");
        } catch(ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }


        try {
            Connection connection = DriverManager.getConnection(url, username, password);
            PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);

            preparedStatement.setString(1, "Hemant");

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String jobTitle = resultSet.getString("job_title");
                double salary = resultSet.getDouble("salary");

                System.out.println("ID: "+ id);
                System.out.println("Name: "+ name);
                System.out.println("Job Title: "+ jobTitle);
                System.out.println("Salary: "+ salary);
            }

            resultSet.close();
            preparedStatement.close();
            connection.close();

            System.out.println();
            System.out.println("Connection Closed Successfully.");

        }  catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
