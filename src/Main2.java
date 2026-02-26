// INSERT OPERATION
import java.sql.*;

public class Main2 {
    public static void main(String[] args) throws ClassNotFoundException, SQLException{

        String url = "jdbc:mysql://localhost:3306/mydatabase";
        String username = "root";
        String password = "mSm@6951";

        String query = "INSERT INTO employees(id, name, job_title, salary) VALUES(4, 'Rahul', 'React Developer', 90000.0);";

        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("Drivers Loaded Successfully");
        } catch(ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }

        try{
            Connection con = DriverManager.getConnection(url, username, password);
            System.out.println("Connection Established Successfully");

            Statement stmt = con.createStatement();
            int rowAffected = stmt.executeUpdate(query);

            if (rowAffected > 0) {
                System.out.println("Insert Succesfull, "+ rowAffected + " row(s) affected.");
            }
            else {
                System.out.println("Insert Failed.");
            }



            stmt.close();
            con.close();
            System.out.println();
            System.out.println("Connection Closed Successfully.");

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }
}