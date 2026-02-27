//PreparedStatement SELECT Example

import java.sql.*;
import java.util.Scanner;

public class Main6 {

    public static void main(String[] args) {

        String url = "jdbc:mysql://localhost:3306/mydatabase";
        String username = "root";
        String password = "mSm@6951";

        String sqlQuery = "INSERT INTO employees(id, name, job_title, salary) VALUES (?,?,?,?)";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("Drivers loaded successfully");
        } catch(ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }


        try {
            Connection connection = DriverManager.getConnection(url, username, password);
            PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);

            Scanner scanner = new Scanner(System.in);
            System.out.print("Enter Id: ");
            int id = scanner.nextInt();
            scanner.nextLine();
            System.out.print("Enter Name: ");
            String name = scanner.nextLine();
            System.out.print("Enter Job Title: ");
            String jobTitle = scanner.nextLine();
            System.out.print("Enter Salary: ");
            double salary = scanner.nextDouble();


            preparedStatement.setInt(1, id);
            preparedStatement.setString(2, name);
            preparedStatement.setString(3, jobTitle);
            preparedStatement.setDouble(4, salary);


            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Data Inserted Successfully.");
            }
            else {
                System.out.println("Data Insertion Failed!!!");
            }

            preparedStatement.close();
            connection.close();

            System.out.println();
            System.out.println("Connection Closed Successfully.");

        }  catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
