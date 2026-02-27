//JDBC Transaction Management Example – Fund Transfer Between Accounts

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.*;

public class Main9 {

    public static void main(String[] args) {

        String url = "jdbc:mysql://localhost:3306/mydatabase";
        String username = "root";
        String password = "mSm@6951";

       String withdrawQuery = "UPDATE accounts SET balance = balance - ? WHERE account_number = ?";
       String depositQuery = "UPDATE accounts SET balance = balance + ? WHERE account_number = ?";






        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("Drivers loaded successfully!!");
        } catch(ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }


        try {
            Connection connection = DriverManager.getConnection(url, username, password);
            System.out.println("Connection Established Successfully!!");
            System.out.println();

            connection.setAutoCommit(false);

            try{
                PreparedStatement withdrawStatement = connection.prepareStatement(withdrawQuery);
                PreparedStatement depositStatement = connection.prepareStatement(depositQuery);

                withdrawStatement.setDouble(1, 500.00);
                withdrawStatement.setString(2, "account456");

                depositStatement.setDouble(1, 500.00);
                depositStatement.setString(2, "account123");

                int rowsAffectedWithdraw = withdrawStatement.executeUpdate();
                int rowsAffectedDeposit = depositStatement.executeUpdate();

                if (rowsAffectedWithdraw > 0 && rowsAffectedDeposit > 0) {
                    connection.commit();
                    System.out.println("Transaction Successful..");
                }
                else {
                    connection.rollback();
                    System.out.println("Transaction Failed!!!");
                }

                withdrawStatement.close();
                depositStatement.close();

            } catch (SQLException e) {
                connection.rollback();
                System.out.println(e.getMessage());
            } finally {
                connection.close();
            }

        }  catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
