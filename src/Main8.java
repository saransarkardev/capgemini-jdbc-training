//JDBC BLOB Retrieval Example – Extract Image from Database Using PreparedStatement

import java.io.*;
import java.sql.*;

public class Main8 {

    public static void main(String[] args) {

        String url = "jdbc:mysql://localhost:3306/mydatabase";
        String username = "root";
        String password = "mSm@6951";

        String folder_path = "/Users/saran/Creative Cloud Files/";

        String sqlQuery = "SELECT image_data FROM image_table WHERE image_id = ?";





        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("Drivers loaded successfully!!");
        } catch(ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }


        try {
            Connection connection = DriverManager.getConnection(url, username, password);
            System.out.println("Connection Established Successfully!!");



            PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
            preparedStatement.setInt(1, 1);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                byte[] image_data = resultSet.getBytes("image_data");
                String image_path = folder_path+"extractedImage.jpg";
                OutputStream outputStream = new FileOutputStream(image_path);
                outputStream.write(image_data);


                System.out.println();
                System.out.println("Image Extracted Successfully.");
            }
            else {
                System.out.println("Image Not Found!!");
            }


            resultSet.close();
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
