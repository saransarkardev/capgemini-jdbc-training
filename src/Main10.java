//JDBC Batch Processing Example – Insert Multiple Employees Using PreparedStatement

import java.sql.*;
import java.util.Scanner;

public class Main10 {

    public static void main(String[] args) {

        String url = "jdbc:mysql://localhost:3306/mydatabase";
        String username = "root";
        String password = "mSm@6951";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("Drivers loaded successfully!!");
        } catch (ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }

        try {
            Connection connection = DriverManager.getConnection(url, username, password);
            System.out.println("Connection Established Successfully!!");
            System.out.println();

            connection.setAutoCommit(false);

            String query = "INSERT INTO employees(id, name, job_title, salary) VALUES(?,?,?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(query);

            Scanner scanner = new Scanner(System.in);

            while (true) {
                System.out.print("ID: ");
                int id = scanner.nextInt();
                scanner.nextLine();

                System.out.print("Name: ");
                String name = scanner.nextLine();

                System.out.print("Job Title: ");
                String jobTitle = scanner.nextLine();

                System.out.print("Salary: ");
                double salary = scanner.nextDouble();
                scanner.nextLine();  // 🔥 FIXED

                preparedStatement.setInt(1, id);
                preparedStatement.setString(2, name);
                preparedStatement.setString(3, jobTitle);
                preparedStatement.setDouble(4, salary);

                preparedStatement.addBatch();

                System.out.print("Add more values Y/N: ");
                String decision = scanner.nextLine();

                if (decision.equalsIgnoreCase("N")) {
                    break;
                }
            }

            int[] batchResult = preparedStatement.executeBatch();
            connection.commit();

            System.out.println("Batch Executed Successfully..");

            preparedStatement.close();
            connection.close();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}