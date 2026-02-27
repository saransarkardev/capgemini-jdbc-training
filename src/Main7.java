//JDBC BLOB Insert Example – Store Image in Database Using PreparedStatement

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.*;
import java.util.Scanner;

public class Main7 {

    public static void main(String[] args) {

        String url = "jdbc:mysql://localhost:3306/mydatabase";
        String username = "root";
        String password = "mSm@6951";

        String image_path = "/Users/saran/Creative Cloud Files/saran.PNG";

        String sqlQuery = "INSERT INTO image_table(image_data) VALUES(?)";



        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("Drivers loaded successfully!!");
        } catch(ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }


        try {
            Connection connection = DriverManager.getConnection(url, username, password);
            System.out.println("Connection Established Successfully!!");

            FileInputStream fileInputStream = new FileInputStream(image_path);
            byte[] imageData = new byte[fileInputStream.available()];
            fileInputStream.read(imageData);

            PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
            preparedStatement.setBytes(1, imageData);

            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println();
                System.out.println("Image Inserted Successfully.");
            }
            else {
                System.out.println("Image Insertion Failed!!");
            }


            fileInputStream.close();
            preparedStatement.close();
            connection.close();


            System.out.println();
            System.out.println("Connection Closed Successfully.");

        }  catch (SQLException e) {
            System.out.println(e.getMessage());
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
